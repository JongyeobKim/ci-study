pipeline {
    environment {
        registry = "kittenplanet/kp_admin"
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
                    docker.build registry
                }
            }
        }

        stage('Remote Run') {
            steps {
                sh 'TARGET_DEPLOY_TCP=tcp://192.168.0.45'
                sh 'DOCKER_HOST=${TARGET_DEPLOY_TCP} docker-compose -f ./docker-compose-server.yml down'
                sh 'DOCKER_HOST=${TARGET_DEPLOY_TCP} docker-compose -f ./docker-compose-server.yml up -d'
            }
        }
    }
}