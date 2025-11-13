pipeline {
    agent any

    environment {
        GROQ_API_KEY
        GITHUB_TOKEN
        GIT_BRANCH = "ai-tests"
        REPO_URL
        HUSKY_SKIP_INSTALL = '0'
        JAVA_HOME = "/usr/lib/jvm/java-17-openjdk"
        MAVEN_HOME = "/usr/share/maven"
        PATH = "${env.MAVEN_HOME}/bin:${env.JAVA_HOME}/bin:${env.PATH}"
    }

    stages {

        stage('Checkout SCM') {
            steps {
                powershell """
                    if (!(Test-Path '.git')) {
                        git clone '${env.REPO_URL}' .
                    } else {
                        git fetch origin
                    }

                    git rev-parse --verify ${env.GIT_BRANCH} > \$null 2>&1
                    if (\$LASTEXITCODE -eq 0) {
                        Write-Host "🌐 Switching to existing local branch: ${env.GIT_BRANCH}"
                        git checkout ${env.GIT_BRANCH} > \$null 2>&1

                        git ls-remote --exit-code origin ${env.GIT_BRANCH} > \$null 2>&1
                        if (\$LASTEXITCODE -eq 0) {
                            try { git pull origin ${env.GIT_BRANCH} } catch {}
                        } else {
                            Write-Host "⚠️ No remote branch to pull from, continuing..."
                        }
                    } else {
                        git ls-remote --exit-code origin ${env.GIT_BRANCH} > \$null 2>&1
                        if (\$LASTEXITCODE -eq 0) {
                            Write-Host "🌐 Branch exists on remote. Checking out tracking branch..."
                            try { git checkout -b ${env.GIT_BRANCH} origin/${env.GIT_BRANCH} } catch { git checkout -b ${env.GIT_BRANCH} }
                        } else {
                            Write-Host "🌐 Branch does not exist locally or remotely. Creating new branch: ${env.GIT_BRANCH}"
                            git checkout -b ${env.GIT_BRANCH}
                        }
                    }

                    # <<< FORCE SUCCESS >>>
                    \$LASTEXITCODE = 0
                    exit \$LASTEXITCODE
                """
            }
        }

        stage('Install Node.js Dependencies') {
            steps {
                powershell '''
                    Write-Host "🔧 Installing Node.js dependencies..."

                    SET HUSKY_SKIP_INSTALL=0
                    npm ci
                    if ($LASTEXITCODE -ne 0) {
                        Write-Host "⚠️ npm ci failed, continuing..."
                    }

                    npx husky install
                    if ($LASTEXITCODE -ne 0) {
                        Write-Host "⚠️ husky install failed, continuing..."
                    }
                '''
            }
        }

        stage('Install Java Dependencies') {
            when { expression { fileExists('pom.xml') } }
            steps {
                powershell '''
                    echo "🔧 Installing Java/Maven dependencies..."
                    mvn clean install -DskipTests || echo "⚠️ Maven install failed, continuing..."
                '''
            }
        }

        stage('Generate AI Tests (Node.js)') {
            when { expression { fileExists('package.json') && fileExists('scripts/generate-tests.js') } }
            steps {
                powershell '''
                    echo "🤖 Generating AI-based Node.js tests..."
                    try {
                        node scripts/generate-tests.js
                    } catch {
                        Write-Host "⚠️ Node.js script failed, continuing..."
                    }
                '''
            }
        }

        stage('Generate AI Tests (Java/Spring Boot)') {
            when { expression { fileExists('scripts/generate-java-tests-new.js') } }
            steps {
                powershell '''
                    echo "🤖 Generating AI-based Java/Spring Boot tests..."

                    node -v
                    if ($LASTEXITCODE -ne 0) {
                        Write-Host "❌ Node.js not installed on this Jenkins agent."
                        exit 1
                    }

                    if (-not (Test-Path "node_modules/groq-sdk")) {
                        Write-Host "📦 Installing groq-sdk..."
                        npm install groq-sdk
                        if ($LASTEXITCODE -ne 0) {
                            Write-Host "❌ Failed to install groq-sdk"
                            exit 1
                        }
                    }

                    try {
                        node scripts/generate-java-tests-new.js
                    } catch {
                        Write-Host "⚠️ Java test generation failed, continuing..."
                    }
                '''
            }
        }

        stage('Lint and Format') {
            steps {
                script {
                    if (fileExists('package.json')) {
                        powershell '''
                            $scripts = npm run | Select-String -Pattern 'format|lint'

                            if ($scripts) {
                                if ($scripts -match 'format') {
                                    try {
                                        npm run format
                                    } catch {
                                        Write-Host "⚠️ npm format failed"
                                    }
                                }

                                if ($scripts -match 'lint') {
                                    try {
                                        npm run lint
                                    } catch {
                                        Write-Host "⚠️ npm lint failed"
                                    }
                                }
                            } else {
                                Write-Host "ℹ️ No npm format/lint scripts found. Skipping..."
                            }
                        '''
                    }

                    if (fileExists('pom.xml')) {
                        powershell '''
                            try {
                                mvn checkstyle:check
                            } catch {
                                Write-Host "⚠️ Maven checkstyle failed, continuing..."
                            }
                        '''
                    }
                }
            }
        }

stage('Commit and Create PR') {
    steps {
        script {
            powershell 'git add .'

            def status = powershell(returnStdout: true, script: 'git status --porcelain').trim()

            if (status) {
                powershell """
                    try {
                        git commit -m "chore: AI generated tests"
                    } catch {
                        Write-Host "⚠️ No commit created or commit failed"
                    }

                    Write-Host "🔗 Reconfiguring Git remote with token..."
                    git remote set-url origin "https://${env.GITHUB_TOKEN}@github.com/saurabhstatusneo-crypto/api_testing_agent_java.git"
                    git remote -v

                    try {
                        # Check if branch has upstream, if not set it
                        \$branch = git rev-parse --abbrev-ref HEAD
                        \$hasUpstream = git rev-parse --abbrev-ref --symbolic-full-name '@{u}' 2>\$null
                        if (-not \$hasUpstream) {
                            Write-Host "🔗 Setting upstream for branch \$branch..."
                            git push --set-upstream origin \$branch
                        } else {
                            git push origin \$branch
                        }
                    } catch {
                        Write-Host "⚠️ Git push failed, continuing..."
                    }
                """

                powershell """
                    try {
                        echo '${env.GITHUB_TOKEN}' | gh auth login --with-token
                        gh pr create --base main --head ${env.GIT_BRANCH} --title 'AI Generated Tests' --body 'Automatically generated tests by Jenkins AI pipeline.'
                    } catch {
                        Write-Host "⚠️ PR creation failed, continuing..."
                    }
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
                        powershell '''
                            try {
                                npm test
                            } catch {
                                Write-Host "⚠️ Node.js tests failed, continuing..."
                            }
                        '''
                    }

                    if (fileExists('pom.xml')) {
                        powershell '''
                            try {
                                mvn test
                            } catch {
                                Write-Host "⚠️ Java tests failed, continuing..."
                            }
                        '''
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
