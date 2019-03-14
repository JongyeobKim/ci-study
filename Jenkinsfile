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
                sh 'DOCKER_HOST=tcp://192.168.0.45:2375 sudo docker-compose -f ./docker-compose-server.yml down'
                sh 'DOCKER_HOST=tcp://192.168.0.45:2375 sudo docker-compose -f ./docker-compose-server.yml up -d'
            }
        }
    }
}