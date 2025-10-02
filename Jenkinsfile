pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "clinicapi:latest"
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
                echo "Building Spring Boot project with Maven..."
                sh "mvn clean package -DskipTests"
            }
        }

        stage('Build Docker Image') {
            steps {
                echo "Building Docker image..."
                sh "docker build -t ${DOCKER_IMAGE} ."
            }
        }

        stage('Run Docker Container') {
            steps {
                echo "Deploying Docker container..."
                // Stop old container if exists
                sh "docker rm -f clinicapi || true"
                // Run new container
                sh "docker run -d --name clinicapi -p 8080:8080 ${DOCKER_IMAGE}"
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