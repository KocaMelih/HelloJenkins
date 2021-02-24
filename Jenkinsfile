pipeline {
    agent any
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
                    archiveArtifacts artifacts : '**/HelloJenkins-jar-with-dependencies.jar'
                }
            }
        }
        stage('Create Hello Jenkins Docker Image'){
            steps{
                sh "docker build . -t hellojenkins:${env.BUILD_ID}"
            }
        }
        stage('Run Hello Jenkins'){
            steps{
                sh "docker run hellojenkins:${env.BUILD_ID}"
            }
        }
    }
}