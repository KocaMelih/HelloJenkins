def x = (env.BUILD_ID as Integer) - 2

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
                    archiveArtifacts artifacts : "${env.JAR_NAME}"
                }
            }
        }
        stage('Remove old Docker images'){
            steps{
                sh "docker image prune -f -a --filter \"until=1h\""
            }
        }
        stage('Create Hello Jenkins Docker Image'){
            steps{
                sh "docker build . -t ${env.IMAGE_NAME}:${env.BUILD_ID}"
            }
        }
        stage('Run Hello Jenkins'){
            steps{
                sh "docker run ${env.IMAGE_NAME}:${env.BUILD_ID}"
            }
        }
    }
}