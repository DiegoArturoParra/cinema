pipeline {

    agent any

    tools {
        // Install the Maven version configured and add it to the path.
        maven "MAVEN"
        jdk "jdk8"
    }

    stages {
        stage('Build Maven') {
            steps {
                // Get some code from a GitHub repository
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: '2e563532-13d9-4f55-9711-f60ca7df705f', url: 'https://github.com/DiegoArturoParra/cinema.git']]])

                // Run Maven on a Unix agent.
                //sh "mvn -Dmaven.test.failure.ignore=true clean package"

                // To run Maven on a Windows agent, use
                bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }
        stage('build image') {
            steps {
                
                script {
                     bat "docker build -t libreria-api-${env.BUILD_ID} ."
                }
                
            }
        }

        // Remove previouvs image docker 
        stage('Remove previous image docker ') {
            steps {
                script {
                     def ID = ${env.BUILD_ID}	
                     def remove = ID-1
                    echo "el anterior es: ${remove}"
                     bat "docker rm -f libreria-api-${remove}"
                     bat "docker rmi libreria-api-${remove}"
                }
            }
        }

        // Running Docker container, make sure port 8096 is opened in 
        stage('Docker Run') {
            steps {
                script {
                     bat "docker run -d --name libreria-api-${env.BUILD_ID} -p 9000:9000 libreria-api-${env.BUILD_ID}:latest"
                }
            }
        }
        stage('Docker terminated') {
            steps {
                script {
                     echo 'ha subido satisfactoriamente los cambios.'
                  
                }
            }
        }
    }
}
