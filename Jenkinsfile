pipeline {
    agent any 
    environment {
      DEALABS_PROJECT_PATH = 'jee/dealabs/server'
    }
    stages {
        stage('Build') {
            steps {
              dir("$DEALABS_PROJECT_PATH") {
                 sh '~/software/apache-maven-3.6.3/bin/mvn clean compile'
              }
            }
        }
    }
}
