pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "clinicbackend:latest"
        BRANCH_NAME = "dev"
        DOCKER_HOST = "tcp://10.10.0.154:2375"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: BRANCH_NAME,
                    url: 'https://github.com/thpeace/clinicapi.git',
                    credentialsId: 'github-credentials'
            }
        }

        stage('Build Maven Project') {
            steps {
                dir('clinicbackend') {
                    echo "Building Spring Boot project inside Maven Docker container..."
                    sh """
                        docker run --rm -v \$PWD:/app -w /app maven:3.9.0-openjdk-17 mvn clean package -DskipTests
                    """
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                dir('clinicbackend') {
                    sh "docker build -t ${DOCKER_IMAGE} ."
                }
            }
        }

        stage('Run Docker Container') {
            steps {
                dir('clinicbackend') {
                    sh "docker rm -f clinicbackend || true"
                    sh "docker run -d --name clinicbackend -p 8080:8080 ${DOCKER_IMAGE}"
                }
            }
        }
    }

    post {
        success {
            echo "Pipeline completed successfully!"
        }
        failure {
            echo "Pipeline failed. Check logs."
        }
    }
}