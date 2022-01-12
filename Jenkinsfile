pipeline {
    agent any 
    environment {
      DEALABS_PROJECT_PATH = 'jee/dealabs/server'
    }
    stages {
        stage('Build') {
            steps {
              dir("$DEALABS_PROJECT_PATH") {
                 sh 'mvn clean compile'
              }
            }
        }
    }
}
