pipeline {
    agent any 
    environment {
      DEALABS_PROJECT_PATH = 'jee/dealabs/server'
    }
    stages {
        stage('Build') {
            steps {
              dir("$DEALABS_PROJECT_PATH") {
                 sh '/home/rolfie/software/apache-maven-3.6.3/bin/mvn clean compile'
              }
            }
        }
    }
}
