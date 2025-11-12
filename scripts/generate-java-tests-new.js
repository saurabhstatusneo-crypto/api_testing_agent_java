import fs from "fs";
import path from "path";
import Groq from "groq-sdk";

const groq = new Groq({ apiKey: process.env.GROQ_API_KEY });

// --- Strip Markdown ```java wrapper if present ---
function cleanJavaCode(code) {
    return code.replace(/^```java\s*/, "").replace(/```$/, "").trim();
}

async function generateTestWithAI(javaCode, className, packageName) {
    const prompt = `
You are a senior Java developer.
Given this Java class, generate a complete JUnit 5 test class that tests all public methods.
Use best practices and the same package structure.

Java class:
${javaCode}

Respond ONLY with the Java test class code (no markdown wrappers).
`;

    const completion = await groq.chat.completions.create({
        model: "llama-3.1-8b-instant",
        messages: [{ role: "user", content: prompt }],
    });

    // Clean up any ```java wrapper that AI might still include
    return cleanJavaCode(completion.choices[0].message.content);
}

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

await processJavaFiles("src/main/java");
console.log("\n🎉 All AI-generated test cases created successfully!");
