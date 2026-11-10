
pipeline {
    agent any

    environment {
        MAVEN_HOME = 'C:\\Program Files\\Apache\\maven'
        PATH = "${env.PATH};${env.MAVEN_HOME}\\bin"
    }

    stages {
        stage('Build') {
            steps {
                bat 'mvn compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package -DskipTests'
            }
        }

        stage('Inspect Target') {
            steps {
                bat 'dir target'
                bat 'if not exist "target\\car-service-1.0-SNAPSHOT.jar" exit /b 1'
            }
        }
    }
}
