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
                sh 'ls'
                sh 'pwd'
                sh 'DOCKER_HOST=tcp://192.168.0.45:2375 docker ps -a'
                sh 'DOCKER_HOST=tcp://192.168.0.45:2375 docker-compose -f docker-compose-server.yml down'
            }
        }
    }
}