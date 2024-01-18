pipeline {
    agent any
        tools {
            jdk 'jdk-21'
            maven 'Maven'
        }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean test'
            }
        }
    }
    post {
        always {
            junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
        }
        success {
            jacoco(
                execPattern: '**/build/jacoco/*.exec',
                classPattern: '**/build/classes/java/main',
                sourcePattern: '**/src/main'
            )
        }
    }
}
