pipeline {
    agent {
        docker { image 'maven:3.9.0-openjdk-17' } // Maven + Java 17
    }

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
                dir('clinicbackend') {   // <-- change to subdirectory
                    echo "Building Spring Boot project with Maven..."
                    sh "mvn clean package -DskipTests"
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                dir('clinicbackend') {   // Dockerfile must be in clinicbackend
                    echo "Building Docker image..."
                    sh "docker build -t ${DOCKER_IMAGE} ."
                }
            }
        }

        stage('Run Docker Container') {
            steps {
                dir('clinicbackend') {
                    echo "Deploying Docker container..."
                    // Stop old container if exists
                    sh "docker rm -f clinicbackend || true"
                    // Run new container
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
            echo "Pipeline failed. Check the logs."
        }
    }
}