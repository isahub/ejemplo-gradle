pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'TODO: build'
                sh './mvnw clean compile -e'
            }
        }
        stage('Test') {
            steps {
                echo 'TODO: test'
                sh './mvnw clean test -e'
            }
        }
        stage('Package') {
            steps {
                echo 'TODO: package'
                sh './mvnw clean package -e'           
            }
        }           
        stage('Sonar') {
            steps {
                echo 'TODO: sonar'
                withSonarQubeEnv(credentialsId: 'tokensonarqube', installationName: 'Sonita') {
                sh './mvnw clean verify sonar:sonar -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build'  
                }
            }
        }            
        stage('Run') {
            steps {
                echo 'TODO: run'
                sh 'nohup bash ./mvnw spring-boot:run &'            
            }
        }
        stage('Mexus') {
            steps {
                echo 'TODO: nexus'
                nexusPublisher nexusInstanceId: 'mxs01', nexusRepositoryId: 'devops-usach-nexus', packages: []
            }
        }
    }
}
