pipeline {
    agent any

    stages {
        stage('Compile') {
            steps {
                echo "Retrieving source code based on the specified directory path in environment variables."
                echo "Compiling the source code to produce the required executables and artifacts."
            }
        }

        stage('Testing') {
            steps {
                echo "Executing unit tests to validate individual parts of the code."
                echo "Performing integration tests to ensure various components function together properly."
            }
        }

        stage('Quality Check') {
            steps {
                echo "Conducting code quality analysis using a static analysis tool to ensure best practices."
            }
        }

        stage('Vulnerability Scan') {
            steps {
                echo "Scanning the code to detect security vulnerabilities with a specified security tool."
            }
        }

        stage('Staging Tests') {
            steps {
                echo "Conducting further integration tests in a staging environment to simulate production conditions."
            }
        }

        stage('Production Deployment') {
            steps {
                echo "Deploying the final version of the code to the production server."
            }
        }
    }

    post {
        success {
            emailext attachLog: true,
            compressLog: true,
            to: 'Samraat10052024@gmail.com',
            body: 'A detailed execution log is available at $JENKINS_HOME/jobs/$JOB_NAME/builds/lastSuccessfulBuild/log',
            subject: 'Successful Deployment to Production - Jenkins Pipeline'
        }
        failure {  
            emailext attachLog: true,
            compressLog: true,
            to: 'Samraat10052024@gmail.com',
            body: 'Please review the execution log at $JENKINS_HOME/jobs/$JOB_NAME/builds/lastSuccessfulBuild/log for details.',
            subject: 'Failed Deployment to Production - Jenkins Pipeline'  
        }
    }
}
