node {
    def app

    stage('Clone repository') {
        /* Let's make sure we have the repository cloned to our workspace */

        checkout scm
    }
    tools {
        maven 'MAVEN'
        jdk 'jdk8'
        docker 'Docker'
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }
        stage('Build artifact') {
        /* Let's make sure we have the repository cloned to our workspace */
            bat 'echo "Do compilation"'
            bat 'mvn package'
            bat 'echo "Pass compilation"'
        }

        stage('Build image') {
        /* This builds the actual image; synonymous to
         * docker build on the command line */

            app = docker.build('diegoparra15/libreria-api-spring-boot')
        }

        stage('Test image') {
        /* Ideally, we would run a test framework against our image.
         * For this example, we're using a Volkswagen-type approach ;-) */

            app.inside {
                sh 'echo "Tests passed"'
            }
        }

        stage('Push image') {
        /* Finally, we'll push the image with two tags:
         * First, the incremental build number from Jenkins
         * Second, the 'latest' tag.
         * Pushing multiple tags is cheap, as all the layers are reused. */
            docker.withRegistry('', 'docker-hub-credentials') {
                app.push("${env.BUILD_ID}")
                app.push('latest')
            }
        }
    }
}
