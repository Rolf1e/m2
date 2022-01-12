pipeline {
    agent any 
    stages {
        stage('Build') {
            steps {
                mvn clean compile
            }
        }
        stage('Test') {
            steps {
                mvn clean test
            }
        }
    }
}
