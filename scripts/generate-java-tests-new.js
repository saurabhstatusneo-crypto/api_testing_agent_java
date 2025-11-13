/**
 * 🚀 AI-Powered JUnit Test Generator with Mockito Support
 * ------------------------------------------------------
 * - Works for both plain Java & Spring Boot projects
 * - Detects database interactions (EntityManager, JdbcTemplate, Repositories, etc.)
 * - Adds Mockito mocks only for DB-related methods
 * - Generates complete JUnit 5 test classes
 *
 * Author: You + GPT-5 😎
 */

import fs from "fs";
import path from "path";
import Groq from "groq-sdk";

const groq = new Groq({ apiKey: process.env.GROQ_API_KEY });

/**
 * --- Utility: Clean AI output ---
 * Removes Markdown wrappers like ```java ... ```
 */
function cleanJavaCode(code) {
    return code.replace(/^```java\s*/, "").replace(/```$/, "").trim();
}

/**
 * --- Detect database interaction patterns ---
 * Looks for common DB-related keywords and classes
 */
function hasDatabaseInteraction(javaCode) {
    const dbPatterns = [
        /EntityManager/,
        /JdbcTemplate/,
        /Connection/,
        /PreparedStatement/,
        /ResultSet/,
        /@Repository/,
        /@Entity/,
        /JpaRepository/,
        /CrudRepository/,
        /\.save\(/,
        /\.find/,
        /\.persist/,
        /\.merge/,
        /\.query/,
    ];
    return dbPatterns.some((pattern) => pattern.test(javaCode));
}

/**
 * --- Extract methods from a Java class ---
 * Rough regex parser that captures method names and bodies
 */
function extractMethods(javaCode) {
    const methodRegex =
        /(?:public|protected|private)\s+[\w\<\>\[\]]+\s+(\w+)\s*\([^)]*\)\s*\{([\s\S]*?)(?=\n\s*(?:public|protected|private|$))/g;
    const methods = [];
    let match;
    while ((match = methodRegex.exec(javaCode)) !== null) {
        methods.push({
            name: match[1],
            body: match[2],
        });
    }
    return methods;
}

/**
 * --- Detect DB-interacting methods ---
 * Returns a list of method names that have DB-related operations
 */
function detectDbMethods(javaCode) {
    const methods = extractMethods(javaCode);
    const dbMethods = methods
        .filter((m) => hasDatabaseInteraction(m.body))
        .map((m) => m.name);
    return dbMethods;
}

/**
 * --- AI Test Generator ---
 * Builds a smart prompt based on class + method analysis
 */
async function generateTestWithAI(javaCode, className, packageName) {
    const classHasDb = hasDatabaseInteraction(javaCode);
    const dbMethods = detectDbMethods(javaCode);

    let mockitoInstructions = "";

    if (classHasDb || dbMethods.length > 0) {
        mockitoInstructions = `
Some methods in this class interact with a database.
Specifically, the following methods have DB interactions: ${dbMethods.join(", ") || "Entire class"}.

👉 For those methods ONLY:
- Use Mockito to mock database-related dependencies (e.g., JdbcTemplate, EntityManager, Repositories)
- Annotate mocks with @Mock
- Use @InjectMocks for the class under test
- Use @ExtendWith(MockitoExtension.class)
- Mock behavior with when(...).thenReturn(...) and verify(...) as needed

👉 For other methods:
- Write regular JUnit 5 tests without mocks.
`;
    }

    const prompt = `
You are a senior Java developer.
Given the following Java class, generate a **complete** JUnit 5 test class.
${mockitoInstructions}
Follow best practices:
- Maintain the same package structure
- Name the test class as ${className}Test
- Include import statements
- Cover all public methods with relevant test cases

Java class:
${javaCode}

Respond ONLY with the Java test class code (no markdown wrappers).
`;

    const completion = await groq.chat.completions.create({
        model: "llama-3.1-8b-instant",
        messages: [{ role: "user", content: prompt }],
        temperature: 0.6,
        max_tokens: 4096,
    });

    return cleanJavaCode(completion.choices[0].message.content);
}

/**
 * --- Recursive directory processor ---
 * Finds all .java files under a directory and generates tests
 */
async function processJavaFiles(dir) {
    const entries = fs.readdirSync(dir, { withFileTypes: true });

    for (const entry of entries) {
        const fullPath = path.join(dir, entry.name);

        if (entry.isDirectory()) {
            await processJavaFiles(fullPath);
        } else if (entry.name.endsWith(".java")) {
            const javaCode = fs.readFileSync(fullPath, "utf8");
            const className = path.basename(entry.name, ".java");
            const pkgMatch = javaCode.match(/package\s+([\w\.]+);/);
            const packageName = pkgMatch ? pkgMatch[1] : "default";

            console.log(`🧠 Generating test for: ${className} (${packageName})`);

            try {
                const testCode = await generateTestWithAI(javaCode, className, packageName);

                const testDir = path.join("src", "test", "java", ...packageName.split("."));
                fs.mkdirSync(testDir, { recursive: true });

                const testPath = path.join(testDir, `${className}Test.java`);
                fs.writeFileSync(testPath, testCode);

                console.log(`✅ Saved test for ${className} → ${testPath}`);
            } catch (err) {
                console.error(`❌ Failed for ${className}:`, err.message);
            }
        }
    }
}

/**
 * --- Run the generator ---
 */
await processJavaFiles("src/main/java");
console.log("\n🎉 All AI-generated test cases created successfully with Mockito where needed!");



//import fs from "fs";
//import path from "path";
//import Groq from "groq-sdk";
//
//const groq = new Groq({ apiKey: process.env.GROQ_API_KEY });
//
//// --- Strip Markdown ```java wrapper if present ---
//function cleanJavaCode(code) {
//    return code.replace(/^```java\s*/, "").replace(/```$/, "").trim();
//}
//
//async function generateTestWithAI(javaCode, className, packageName) {
//    const prompt = `
//You are a senior Java developer.
//Given this Java class, generate a complete JUnit 5 test class that tests all public methods.
//Use best practices and the same package structure.
//
//Java class:
//${javaCode}
//
//Respond ONLY with the Java test class code (no markdown wrappers).
//`;
//
//    const completion = await groq.chat.completions.create({
//        model: "llama-3.1-8b-instant",
//        messages: [{ role: "user", content: prompt }],
//    });
//
//    // Clean up any ```java wrapper that AI might still include
//    return cleanJavaCode(completion.choices[0].message.content);
//}
//
//async function processJavaFiles(dir) {
//    const entries = fs.readdirSync(dir, { withFileTypes: true });
//
//    for (const entry of entries) {
//        const fullPath = path.join(dir, entry.name);
//
//        if (entry.isDirectory()) {
//            await processJavaFiles(fullPath);
//        } else if (entry.name.endsWith(".java")) {
//            const javaCode = fs.readFileSync(fullPath, "utf8");
//            const className = path.basename(entry.name, ".java");
//            const pkgMatch = javaCode.match(/package\s+([\w\.]+);/);
//            const packageName = pkgMatch ? pkgMatch[1] : "default";
//
//            console.log(`🧠 Generating test for: ${className} (${packageName})`);
//
//            try {
//                const testCode = await generateTestWithAI(javaCode, className, packageName);
//
//                const testDir = path.join("src", "test", "java", ...packageName.split("."));
//                fs.mkdirSync(testDir, { recursive: true });
//
//                const testPath = path.join(testDir, `${className}Test.java`);
//                fs.writeFileSync(testPath, testCode);
//
//                console.log(`✅ Saved test for ${className} → ${testPath}`);
//            } catch (err) {
//                console.error(`❌ Failed for ${className}:`, err.message);
//            }
//        }
//    }
//}
//
//await processJavaFiles("src/main/java");
//console.log("\n🎉 All AI-generated test cases created successfully!");
