pipeline {
    agent any

    environment {

        GIT_BRANCH = "ai-tests"
        REPO_URL = "https://github.com/saurabhstatusneo-crypto/api_testing_agent_java"
        HUSKY_SKIP_INSTALL = '0'
        JAVA_HOME = "/usr/lib/jvm/java-17-openjdk" // Adjust if needed
        MAVEN_HOME = "/usr/share/maven"           // Adjust if needed
        PATH = "${env.MAVEN_HOME}/bin:${env.JAVA_HOME}/bin:${env.PATH}"
    }
    stages {
        stage('Checkout SCM') {
            steps {
                script {
                    if (!fileExists(".git")) {
                        powershell "git clone ${env.REPO_URL} ."
                    } else {
                        powershell "git fetch origin"
                    }

                    powershell """
                        try {
                            git rev-parse --verify ${env.GIT_BRANCH} | Out-Null
                            Write-Host "🌐 Switching to existing branch: ${env.GIT_BRANCH}"
                            git checkout ${env.GIT_BRANCH}
                            git pull origin ${env.GIT_BRANCH}
                        } catch {
                            Write-Host "🌐 Branch does not exist. Creating new branch: ${env.GIT_BRANCH}"
                            git checkout -b ${env.GIT_BRANCH}
                        }
                    """
                }
            }
        }

        stage('Install Node.js Dependencies') {
            when { expression { fileExists('package.json') } }
            steps {
                powershell """
                    echo "🔧 Installing Node.js dependencies..."
                    SET HUSKY_SKIP_INSTALL=0
                    npm ci
                    npx husky install
                """
            }
        }

        stage('Install Java Dependencies') {
            when { expression { fileExists('pom.xml') } }
            steps {
                powershell """
                    echo "🔧 Installing Java/Maven dependencies..."
                    mvn clean install -DskipTests
                """
            }
        }

        stage('Generate AI Tests (Node.js)') {
            when { expression { fileExists('package.json') } }
            steps {
                powershell "node scripts/generate-tests.js"
            }
        }

        stage('Generate AI Tests (Java/Spring Boot)') {
            when { expression { fileExists('pom.xml') } }
            steps {
                powershell """
                    echo "🤖 Generating AI-based Java/Spring Boot tests..."
                    // Example: assuming you have a script to generate Java tests
                    node scripts/generate-java-tests.js
                """
            }
        }

        stage('Lint and Format') {
            steps {
                script {
                    if (fileExists('package.json')) {
                        powershell """
                            npm run format
                            npm run lint
                        """
                    }
                    if (fileExists('pom.xml')) {
                        powershell """
                            mvn checkstyle:check
                        """
                    }
                }
            }
        }

        stage('Commit and Create PR') {
            steps {
                script {
                    powershell "git add ."
                    def status = powershell(returnStdout: true, script: 'git status --porcelain').trim()
                    if (status) {
                        powershell """
                            git commit -m "chore: AI generated tests"
                            git push origin ${env.GIT_BRANCH}
                        """
                        powershell """
                            echo '${env.GITHUB_TOKEN}' | gh auth login --with-token
                            gh pr create --base main --head ${env.GIT_BRANCH} --title "AI Generated Tests" --body "Automatically generated tests"
                        """
                    } else {
                        echo "✅ No changes to commit. Skipping PR creation."
                    }
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    if (fileExists('package.json')) {
                        powershell "npm test"
                    }
                    if (fileExists('pom.xml')) {
                        powershell "mvn test"
                    }
                }
            }
        }
    }

    post {
        always { echo "Pipeline finished" }
        success { echo "✅ Pipeline succeeded!" }
        failure { echo "❌ Pipeline failed — check logs." }
    }
}
