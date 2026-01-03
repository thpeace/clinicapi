pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "clinicapi:latest"
        BRANCH_NAME  = "main"
        REMOTE_USER  = "docker"
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

        stage('SonarQube Analysis') {
            steps {
                script {
                    def mvnHome = tool 'Default Maven'
                    withSonarQubeEnv('SonarQube') {   // <-- Name must match Jenkins Sonar config
                        sh "${mvnHome}/bin/mvn clean verify sonar:sonar " +
                           "-Dsonar.projectKey=clinic_api " +
                           "-Dsonar.projectName=clinic_api"
                    }
                }
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
                    sh '''
                        set -e
                        docker save ${DOCKER_IMAGE} -o clinicapi.tar
                        sshpass -p "$SSH_PASS" scp -o StrictHostKeyChecking=no clinicapi.tar $SSH_USER@${REMOTE_HOST}:/home/docker/
                        sshpass -p "$SSH_PASS" ssh -o StrictHostKeyChecking=no $SSH_USER@${REMOTE_HOST} "
                            docker load -i /home/docker/clinicapi.tar &&
                            docker rm -f clinicapi || true &&
                            docker run -d --name clinicapi -p 8080:8080 -e "SPRING_PROFILES_ACTIVE=prod" ${DOCKER_IMAGE}
                        "
                    '''
                }
            }
        }
    }

    post {
        success {
            echo "✅ Pipeline completed successfully!"
        }
        failure {
            echo "❌ Pipeline failed. Check the logs."
        }
    }
}
