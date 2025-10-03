pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "clinicapi:latest"
        BRANCH_NAME  = "dev"
        REMOTE_HOST  = "10.10.0.154"
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
                sh "mvn clean package -DskipTests"
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE} ."
            }
        }

        stage('Push Image to Remote Server') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'remote-server-ssh-pass',
                                                  usernameVariable: 'SSH_USER',
                                                  passwordVariable: 'SSH_PASS')]) {
                    sh """
                        # Save docker image to tarball
                        docker save ${DOCKER_IMAGE} -o clinicapi.tar

                        # Copy tarball to remote host
                        sshpass -p "$SSH_PASS" scp -o StrictHostKeyChecking=no clinicapi.tar $SSH_USER@${REMOTE_HOST}:/home/docker/

                        # On remote: load image, remove old container, start new one
                        sshpass -p "$SSH_PASS" ssh -o StrictHostKeyChecking=no $SSH_USER@${REMOTE_HOST} '
                            docker load -i /home/docker/clinicapi.tar &&
                            docker rm -f clinicapi || true &&
                            docker run -d --name clinicapi -p 8080:8080 clinicapi:latest
                        '
                    """
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