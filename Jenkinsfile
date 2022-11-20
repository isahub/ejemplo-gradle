def mvn_script

pipeline{
    agent any
    tools{
        gradle 'grdl'
        maven 'maven'
    }
    parameters{
        choice(name: 'Build_Tool', choices: ['maven', 'gradle'], description: '')
        booleanParam(name: 'PushToNexus', defaultValue: false, description: '')
    }
    stages {
        stage('Load_Scripts'){
            steps{
                script{
                    mvn_script = load "maven.groovy"
                }
            }
        }
        stage('build-mvn'){
            when {
                expression {
                    params.Build_Tool == 'maven'
                }
            }
            steps {
                //sh 'mvn clean install -e'
                script{
                    mvn_script.maven_build_test()
                }
            }
        }
        stage('build-gradle'){
            steps{
                sh 'gradle build'
            }
        }
        stage('pushToNexus'){
            when {
                expression { params.PushToNexus }
            }
            steps {
                sh 'echo ptn'
            }
        }
    }
}