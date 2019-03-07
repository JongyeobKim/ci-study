pipeline {
    environment {
        registry = "kittenplanet/admin_age"
    }
    agent any
    stages {
        stages {
            stage('Test') {
                steps {
                    sh './gradlew check'
                }
            }
        }
        post {
            always {
                junit 'build/reports/**/*.xml'
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