pipeline {
    agent any
    tools {
        maven 'MAVEN'
        jdk 'jdk8'
        docker 'Docker'
    }
    stages {

        stage('Clone repository') {
        /* Let's make sure we have the repository cloned to our workspace */
            checkout scm
        }

        stage('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

        stage('Build') {
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true install' 
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml' 
                }
            }
        } 
    }
}
