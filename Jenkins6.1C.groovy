pipeline {
    agent any  // Designates any available agent to run the pipeline

    stages {
        stage('Code Compilation') {
            steps {
                echo "Retrieving source code based on SCM configuration."
                echo "Executing build process to compile source files and create deployable artifacts."
                sh 'mvn clean package'  // Replace with your build system command, e.g., for Maven
            }
        }

        stage('Testing Phase') {
            steps {
                echo "Initiating unit tests to validate individual components."
                echo "Conducting integration tests to ensure components interact correctly."
                sh 'mvn test'  // Use appropriate test command for your setup
            }
        }

        stage('Quality Check') {
            steps {
                echo "Performing code quality analysis to adhere to quality standards."
                sh 'mvn sonar:sonar'  // Assumes SonarQube integration; modify as needed
            }
        }

        stage('Vulnerability Audit') {
            steps {
                echo "Running security vulnerability scans to detect potential risks."
                sh 'mvn dependency-check:check'  // Example for Maven with OWASP
            }
        }

        stage('Staging Deployment') {
            steps {
                echo "Deploying application to staging area for pre-production testing."
                // Add deployment command here, e.g., rsync or another secure method
            }
        }

        stage('Staging Testing') {
            steps {
                echo "Executing tests in staging to simulate the production environment."
                // Script for running tests on your staging server
            }
        }

        stage('Live Deployment') {
            steps {
                echo "Final deployment to the live production server."
                // Deployment command for production, ensure security and accuracy
            }
        }
    }

    post {
        success {
            emailext(
                to: 'Samraat10052024@gmail.com',
                subject: 'Deployment Complete - Success',
                body: """<p>Deployment to the production environment completed successfully.</p>
                         <p>Details and logs are available here: ${env.BUILD_URL}</p>""",
                attachLog: true,
                compressLog: true
            )
        }
        failure {
            emailext(
                to: 'Samraat10052024@gmail.com',
                subject: 'Deployment Alert - Failure',
                body: """<p>Deployment to production encountered issues and failed.</p>
                         <p>For detailed error information, please review: ${env.BUILD_URL}</p>""",
                attachLog: true,
                compressLog: true
            )
        }
    }
}
