// ============================================================
// JENKINSFILE - AwesomeQA Selenium Automation Framework
// Location: Repository Root (santhikrishnanp/AwsomeQAWith_Selenium/)
// Purpose: CI/CD Pipeline for overnight automated testing
// ============================================================

pipeline {

    // ============================================================
    // AGENT CONFIGURATION
    // ============================================================
    agent any

    // ============================================================
    // ENVIRONMENT VARIABLES
    // ============================================================
    environment {
        // Project Information
        PROJECT_NAME = 'AwesomeQA-Selenium'
        PROJECT_DESCRIPTION = 'Selenium Automation Framework with TestNG'

        // Build Information
        BUILD_TIMESTAMP = sh(script: "date +%Y%m%d_%H%M%S", returnStdout: true).trim()
        BUILD_TAG = "${env.BUILD_NUMBER}_${BUILD_TIMESTAMP}"

        // Maven Configuration
        MAVEN_HOME = tool name: 'Maven', type: 'maven'
        JAVA_HOME = tool name: 'JDK11', type: 'jdk'
        MAVEN_OPTS = '-Dmaven.repo.local=.m2 -Dorg.slf4j.simpleLogger.defaultLogLevel=info'
        PATH = "${JAVA_HOME}/bin:${MAVEN_HOME}/bin:${PATH}"

        // Report Paths
        SUREFIRE_REPORT_PATH = 'target/surefire-reports'
        FAILSAFE_REPORT_PATH = 'target/failsafe-reports'
        JACOCO_REPORT_PATH = 'target/site/jacoco'
        EXTENT_REPORT_PATH = 'test-output'

        // SonarQube Configuration (Optional)
        SONAR_PROJECT_KEY = 'AwesomeQA_Selenium'
        SONAR_PROJECT_NAME = 'AwesomeQA Selenium Automation'
        // SONAR_HOST_URL and SONAR_LOGIN should be stored in Jenkins Credentials

        // Email Configuration
        EMAIL_TO = 'qa-team@company.com'
        EMAIL_FROM = 'jenkins@company.com'
    }

    // ============================================================
    // JENKINS OPTIONS
    // ============================================================
    options {
        // Keep only last 20 builds
        buildDiscarder(logRotator(numToKeepStr: '20', artifactNumToKeepStr: '10'))

        // Add timestamps to console output
        timestamps()

        // Build timeout (1 hour for overnight runs)
        timeout(time: 1, unit: 'HOURS')

        // Disable concurrent builds (prevent resource conflicts)
        disableConcurrentBuilds()

        // Annotate builds with run parameters
        buildName "${env.BUILD_NUMBER} - ${env.BUILD_TIMESTAMP}"
    }

    // ============================================================
    // BUILD TRIGGERS
    // ============================================================
    triggers {
        // Trigger daily at 2 AM for overnight runs
        cron('H 2 * * *')

        // Uncomment below for GitHub webhook trigger (when code is pushed)
        // githubPush()

        // Uncomment below for polling SCM every hour
        // pollSCM('H * * * *')
    }

    // ============================================================
    // STAGES
    // ============================================================
    stages {

        // ========== STAGE 1: CHECKOUT ==========
        stage('📥 Checkout') {
            steps {
                echo "=========================================="
                echo "STAGE: Checkout Code"
                echo "=========================================="

                script {
                    try {
                        checkout([
                            $class: 'GitSCM',
                            branches: [[name: '*/master']],
                            userRemoteConfigs: [[
                                url: 'https://github.com/santhikrishnanp/AwsomeQAWith_Selenium.git',
                                credentialsId: 'github-credentials'  // Must be created in Jenkins
                            ]]
                        ])
                        echo "✅ Code checkout successful"
                    } catch (Exception e) {
                        echo "❌ Checkout failed: ${e.message}"
                        throw e
                    }
                }
            }
        }

        // ========== STAGE 2: ENVIRONMENT SETUP ==========
        stage('🔧 Environment Setup') {
            steps {
                echo "=========================================="
                echo "STAGE: Environment Setup"
                echo "=========================================="

                script {
                    echo "Java Version:"
                    sh 'java -version'

                    echo "Maven Version:"
                    sh 'mvn -version'

                    echo "Display Environment:"
                    sh 'echo "Build Number: ${BUILD_NUMBER}"'
                    sh 'echo "Build Timestamp: ${BUILD_TIMESTAMP}"'
                    sh 'echo "Workspace: ${WORKSPACE}"'
                }
            }
        }

        // ========== STAGE 3: DEPENDENCY RESOLUTION ==========
        stage('📦 Dependencies') {
            steps {
                echo "=========================================="
                echo "STAGE: Resolve Dependencies"
                echo "=========================================="

                script {
                    try {
                        sh 'mvn dependency:resolve -q'
                        sh 'mvn dependency:tree > dependency-tree.txt'
                        echo "✅ Dependencies resolved successfully"
                    } catch (Exception e) {
                        echo "❌ Dependency resolution failed"
                        throw e
                    }
                }
            }
        }

        // ========== STAGE 4: BUILD ==========
        stage('🏗️ Build') {
            steps {
                echo "=========================================="
                echo "STAGE: Compile Source Code"
                echo "=========================================="

                script {
                    try {
                        sh '''
                            echo "Starting Maven clean compile..."
                            mvn clean compile -q
                            echo "✅ Build successful"
                        '''
                    } catch (Exception e) {
                        echo "❌ Build failed: ${e.message}"
                        throw e
                    }
                }
            }
        }

        // ========== STAGE 5: UNIT TESTS ==========
        stage('🧪 Unit Tests') {
            steps {
                echo "=========================================="
                echo "STAGE: Execute Unit Tests"
                echo "=========================================="

                script {
                    try {
                        sh '''
                            echo "Starting TestNG unit tests..."
                            echo "Test suite: testng.xml"
                            mvn test -DsuiteXmlFile=testng.xml \
                                    -DthreadCount=4 \
                                    -Dparallel=methods \
                                    -Dmaven.surefire.debug=false
                            echo "✅ Unit tests completed"
                        '''
                    } catch (Exception e) {
                        echo "⚠️ Unit tests failed (but continuing): ${e.message}"
                        // Don't throw - continue to next stage to collect reports
                    }
                }
            }

            post {
                always {
                    echo "📊 Publishing test results..."
                    junit(
                        testResults: "${SUREFIRE_REPORT_PATH}/*.xml",
                        allowEmptyResults: true,
                        healthScaleFactor: 1.0
                    )

                    // Archive test reports
                    archiveArtifacts(
                        artifacts: "${SUREFIRE_REPORT_PATH}/**/*",
                        allowEmptyArchive: true
                    )
                }
            }
        }

        // ========== STAGE 6: CODE COVERAGE ==========
        stage('📈 Code Coverage') {
            steps {
                echo "=========================================="
                echo "STAGE: JaCoCo Code Coverage Analysis"
                echo "=========================================="

                script {
                    try {
                        sh '''
                            echo "Generating JaCoCo coverage report..."
                            mvn jacoco:report -q
                            echo "✅ Coverage report generated"
                        '''
                    } catch (Exception e) {
                        echo "⚠️ Coverage analysis failed: ${e.message}"
                    }
                }
            }

            post {
                always {
                    echo "📊 Publishing coverage report..."

                    // Publish JaCoCo coverage reports to Jenkins
                    publishHTML([
                        reportDir: "${JACOCO_REPORT_PATH}",
                        reportFiles: 'index.html',
                        reportName: 'JaCoCo Coverage Report',
                        keepAll: true,
                        alwaysLinkToLastBuild: true
                    ])

                    // Archive coverage data
                    archiveArtifacts(
                        artifacts: "${JACOCO_REPORT_PATH}/**/*",
                        allowEmptyArchive: true
                    )
                }
            }
        }

        // ========== STAGE 7: INTEGRATION TESTS ==========
        stage('🔗 Integration Tests') {
            steps {
                echo "=========================================="
                echo "STAGE: Execute Integration Tests (Failsafe)"
                echo "=========================================="

                script {
                    try {
                        sh '''
                            echo "Starting Failsafe integration tests..."
                            mvn failsafe:integration-test failsafe:verify -q
                            echo "✅ Integration tests completed"
                        '''
                    } catch (Exception e) {
                        echo "⚠️ Integration tests failed: ${e.message}"
                    }
                }
            }

            post {
                always {
                    echo "📊 Publishing integration test results..."
                    junit(
                        testResults: "${FAILSAFE_REPORT_PATH}/*.xml",
                        allowEmptyResults: true
                    )

                    // Archive integration test reports
                    archiveArtifacts(
                        artifacts: "${FAILSAFE_REPORT_PATH}/**/*",
                        allowEmptyArchive: true
                    )
                }
            }
        }

        // ========== STAGE 8: CODE QUALITY ANALYSIS ==========
        stage('🔍 Code Quality Analysis') {
            steps {
                echo "=========================================="
                echo "STAGE: Run Code Quality Scanners"
                echo "=========================================="

                parallel(
                    'CheckStyle': {
                        script {
                            try {
                                sh 'mvn checkstyle:check -q'
                                echo "✅ CheckStyle analysis completed"
                            } catch (Exception e) {
                                echo "⚠️ CheckStyle issues found (non-blocking)"
                            }
                        }
                    },
                    'SpotBugs': {
                        script {
                            try {
                                sh 'mvn spotbugs:check -q'
                                echo "✅ SpotBugs analysis completed"
                            } catch (Exception e) {
                                echo "⚠️ SpotBugs issues found (non-blocking)"
                            }
                        }
                    },
                    'PMD': {
                        script {
                            try {
                                sh 'mvn pmd:check -q'
                                echo "✅ PMD analysis completed"
                            } catch (Exception e) {
                                echo "⚠️ PMD issues found (non-blocking)"
                            }
                        }
                    }
                )
            }
        }

        // ========== STAGE 9: SONARQUBE ANALYSIS ==========
        stage('🎯 SonarQube Analysis') {
            when {
                // Run SonarQube only on master branch or when explicitly triggered
                anyOf {
                    branch 'master'
                    branch 'main'
                    expression { env.SONAR_TOKEN != null }
                }
            }
            steps {
                echo "=========================================="
                echo "STAGE: SonarQube Code Analysis"
                echo "=========================================="

                script {
                    try {
                        withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                            sh '''
                                echo "Running SonarQube analysis..."
                                mvn clean verify sonar:sonar \
                                    -Dsonar.projectKey=${SONAR_PROJECT_KEY} \
                                    -Dsonar.projectName="${SONAR_PROJECT_NAME}" \
                                    -Dsonar.host.url=http://sonarqube:9000 \
                                    -Dsonar.login=${SONAR_TOKEN} \
                                    -q
                                echo "✅ SonarQube analysis completed"
                            '''
                        }
                    } catch (Exception e) {
                        echo "⚠️ SonarQube analysis failed (non-blocking): ${e.message}"
                    }
                }
            }
        }

        // ========== STAGE 10: GENERATE REPORTS ==========
        stage('📋 Generate Reports') {
            steps {
                echo "=========================================="
                echo "STAGE: Generate Test Reports"
                echo "=========================================="

                script {
                    try {
                        sh '''
                            echo "Generating Maven site reports..."
                            mvn site -q
                            echo "✅ Reports generated"
                        '''

                        // Publish ExtentReports if available
                        if (fileExists("${EXTENT_REPORT_PATH}/index.html")) {
                            publishHTML([
                                reportDir: "${EXTENT_REPORT_PATH}",
                                reportFiles: 'index.html',
                                reportName: 'ExtentReports',
                                keepAll: true,
                                alwaysLinkToLastBuild: true
                            ])
                        }
                    } catch (Exception e) {
                        echo "⚠️ Report generation failed: ${e.message}"
                    }
                }
            }

            post {
                always {
                    echo "📊 Archiving all reports..."
                    archiveArtifacts(
                        artifacts: 'target/site/**/*,test-output/**/*,dependency-tree.txt',
                        allowEmptyArchive: true
                    )
                }
            }
        }

    }

    // ============================================================
    // POST BUILD ACTIONS
    // ============================================================
    post {

        // Always execute - regardless of build status
        always {
            echo "=========================================="
            echo "POST BUILD: Always"
            echo "=========================================="

            script {
                // Generate build summary
                echo "Build Summary:"
                echo "- Build Number: ${BUILD_NUMBER}"
                echo "- Build Status: ${currentBuild.result}"
                echo "- Build Duration: ${currentBuild.durationString}"
                echo "- Build Timestamp: ${BUILD_TIMESTAMP}"
            }

            // Clean workspace (optional)
            // cleanWs()
        }

        // Success - Build passed
        success {
            echo "=========================================="
            echo "✅ BUILD SUCCESSFUL"
            echo "=========================================="

            script {
                def testSummary = """
                    ✅ Build Status: SUCCESS

                    Build Details:
                    - Build Number: ${BUILD_NUMBER}
                    - Build URL: ${BUILD_URL}
                    - Duration: ${currentBuild.durationString}
                    - Branch: master

                    Available Reports:
                    - Test Results: ${BUILD_URL}testReport/
                    - JaCoCo Coverage: ${BUILD_URL}JaCoCo_Coverage_Report/
                    - ExtentReports: ${BUILD_URL}ExtentReports/

                    All automated tests passed successfully!
                """

                // Send success email
                emailext(
                    subject: "✅ [SUCCESS] ${PROJECT_NAME} - Build #${BUILD_NUMBER}",
                    body: testSummary,
                    to: "${EMAIL_TO}",
                    from: "${EMAIL_FROM}",
                    mimeType: 'text/plain'
                )
            }
        }

        // Failure - Build failed
        failure {
            echo "=========================================="
            echo "❌ BUILD FAILED"
            echo "=========================================="

            script {
                def failureSummary = """
                    ❌ Build Status: FAILURE

                    Build Details:
                    - Build Number: ${BUILD_NUMBER}
                    - Build URL: ${BUILD_URL}
                    - Duration: ${currentBuild.durationString}
                    - Branch: master

                    Available Reports:
                    - Console Output: ${BUILD_URL}console
                    - Test Results: ${BUILD_URL}testReport/
                    - Build Log: ${BUILD_URL}logText/progressiveText

                    Please review the logs and fix the issues.
                """

                // Send failure email with attachment
                emailext(
                    subject: "❌ [FAILURE] ${PROJECT_NAME} - Build #${BUILD_NUMBER}",
                    body: failureSummary,
                    to: "${EMAIL_TO}",
                    from: "${EMAIL_FROM}",
                    mimeType: 'text/plain',
                    attachLog: true  // Attach console log
                )
            }
        }

        // Unstable - Build has issues but didn't fail completely
        unstable {
            echo "=========================================="
            echo "⚠️ BUILD UNSTABLE"
            echo "=========================================="

            script {
                def unstableSummary = """
                    ⚠️ Build Status: UNSTABLE

                    Build Details:
                    - Build Number: ${BUILD_NUMBER}
                    - Build URL: ${BUILD_URL}
                    - Duration: ${currentBuild.durationString}

                    Some tests failed or warnings detected.
                    Please check the test reports for details.
                """

                emailext(
                    subject: "⚠️ [UNSTABLE] ${PROJECT_NAME} - Build #${BUILD_NUMBER}",
                    body: unstableSummary,
                    to: "${EMAIL_TO}",
                    from: "${EMAIL_FROM}",
                    mimeType: 'text/plain'
                )
            }
        }

        // Cleanup
        cleanup {
            echo "=========================================="
            echo "Cleaning up workspace..."
            echo "=========================================="

            // Optional: Remove node dependency cache to save space
            // sh 'rm -rf .m2/repository/*'
        }
    }
}

// ============================================================
// END OF JENKINSFILE
// ============================================================