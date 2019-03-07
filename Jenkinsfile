pipeline {
    agent {
        dockerfile true
    }
    stages {
        stage('Example') {
            steps {
                echo 'ci test'
                sh 'DOCKER_BUILDKIT=1 docker build -t myorg/myapp .'
            }
        }
    }
}