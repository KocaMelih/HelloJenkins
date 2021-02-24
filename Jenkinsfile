pipeline {
    agent any
    environment {
        JAR_NAME = '**/HelloJenkins-jar-with-dependencies.jar'
        IMAGE_NAME    = 'hellojenkins'
    }
    stages {
        stage ('Build Servlet Project') {
            steps {
                /*For windows machine */
               //bat  'mvn clean package'

                /*For Mac & Linux machine */
               sh  'mvn clean package'
            }

            post{
                success{
                    echo 'Now Archiving ....'
                    archiveArtifacts artifacts : '${JAR_NAME}'
                }
            }
        }
        stage('Remove old Docker images'){
            steps{
                def x = env.BUILD_ID - 2
                sh 'docker rmi @\$(docker images -q --filter \"before=${IMAGE_NAME}:${x}\" ${IMAGE_NAME})'
            }
        }
        stage('Create Hello Jenkins Docker Image'){
            steps{
                sh "docker build . -t ${IMAGE_NAME}:${env.BUILD_ID}"
            }
        }
        stage('Run Hello Jenkins'){
            steps{
                sh "docker run ${IMAGE_NAME}:${env.BUILD_ID}"
            }
        }
    }
}