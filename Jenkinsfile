pipeline {
    agent any


    stages {
       stage('Build') {
          steps {
             sh 'gradle clean compileJava'
             sh './gradlew clean assemble'
          }
       }
       stage('Deploy'){
                  steps{
                      sh 'cf push order -p ./build/libs/order-0.0.1-SNAPSHOT.jar'
                  }
       }
    }
}