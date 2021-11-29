pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
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
                     bat 'docker build -t libreria-api-1.0 .'
                }
                
            }
        }

        // Running Docker container, make sure port 8096 is opened in 
        stage('Docker Run') {
            steps {
                
                script {
                    dockerImage.run("-p 9000:9000 --rm --name libreria-api-1.0")
                }
            }
        }
    }
}
