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
                //sh "docker image prune -f -a --filter \"until=\$(date +'%Y-%m-%dT%H:%M:%S' --date='-15 days')\""
            }
        }
        stage('Create Hello Jenkins Docker Image'){
            steps{
                sh "docker build . -t ${env.IMAGE_NAME}:${env.BUILD_ID}"
            }
        }
        stage('Clearing previous containers'){
            steps{
                script{

                    def doc_containers = sh(returnStdout: true, script: 'docker container ps -aq -f "name=${env.IMAGE_NAME}"').replaceAll("\n", " ")
                    if (doc_containers) {
                        sh "docker stop ${doc_containers}"
                    }

                }
            }
        }
        stage('Run Hello Jenkins'){
            steps{
                sh "docker run --name \"${env.IMAGE_NAME}\" ${env.IMAGE_NAME}:${env.BUILD_ID}"
            }
            post{
                success{
                    echo 'Deployment on staging environment succeeded!'
                    //archiveArtifacts artifacts : "${env.JAR_NAME}"
                }
                failure{
                    echo 'Deployment on staging environment failed!'
                }
            }
        }
    }
}