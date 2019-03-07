pipeline {
    environment {
        registry = "kittenplanet/admin_age"
    }
    agent any

    stages {
        stage('Test') {
            steps {
                sh './gradlew check'
            }
        }

        stage('Building image') {
            steps {
                script {
                    docker.build registry + ":$BUILD_NUMBER"
                }
            }
        }
    }
}