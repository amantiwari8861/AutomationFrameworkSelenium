pipeline {
    agent any

    tools {
        maven 'MVN3'
        jdk 'JDK21'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/amantiwari8861/AutomationFrameworkSelenium'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
            }
        }
    }
     post {
        always {
                    junit allowEmptyResults: true,
                          testResults: 'target/surefire-reports/*.xml'

                    allure([
                        includeProperties: false,
                        results: [[path: 'allure-results']]
                    ])
                }

        success {
            echo 'All Selenium tests passed.'
        }

        failure {
            echo 'Some Selenium tests failed.'
        }
    }
}