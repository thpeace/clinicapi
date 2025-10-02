pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "myapp:latest"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/thpeace/clinicapi.git',
                    credentialsId: 'github-credentials'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build(DOCKER_IMAGE)
                }
            }
        }

        stage('Run Docker Container') {
            steps {
                script {
                    // Stop old container if exists
                    sh "docker rm -f myapp || true"
                    // Run new container
                    sh "docker run -d --name myapp -p 8080:8080 ${DOCKER_IMAGE}"
                }
            }
        }
    }
}