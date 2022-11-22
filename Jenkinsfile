def mvn_script
def grd_script

pipeline{
    agent any
    tools{
        gradle 'grdl'
        maven 'maven'
    }
    parameters{
        choice(name: 'Build_Tool', choices: ['maven', 'gradle'], description: '')
        booleanParam(name: 'PushToNexus', defaultValue: true, description: '')
    }
    stages {
        stage('Load_Scripts'){
            steps{
                script{
                    mvn_script = load "maven.groovy"
                    grd_script = load "gradle.groovy"
                }
            }
        }
        stage('build-maven'){
            when {
                expression {
                    params.Build_Tool == 'maven'
                }
            }
            steps {
                script{
                    mvn_script.buildMaven()
                }
            }
        }
        stage('build-gradle'){
            when {
                expression {
                    params.Build_Tool == 'gradle'
                }
            }
            steps {
                script{
                    grd_script.buildGradle()
                }
            }
        }
        stage('sonar-maven') {
            when { expression { params.Build_Tool == 'maven' } }
            steps {
                withSonarQubeEnv(credentialsId: 'sonartoken', installationName: 'Sonita') {
                    script {
                        mvn_script.sonarMaven();
                    }
                }
            }
        }
        stage('sonar-gradle') {
            when { expression { params.Build_Tool == 'gradle' } }
            steps {
                withSonarQubeEnv(credentialsId: 'sonartoken', installationName: 'Sonita') {
                    script {
                        grd_script.sonarGradle();
                    }
                }
            }
        }
        stage('runtest-maven') {
            when { expression { params.Build_Tool == 'maven' } }
            steps {
                     script {
                        mvn_script.runTestMaven();
                }
            }
        }
        stage('runtest-gradle') {
            when { expression { params.Build_Tool == 'gradle' } }
            steps {
                    script {
                        grd_script.runTestGradle();
                }
            }
        }
        stage('pushToNexus-Upload'){
            when { expression { params.PushToNexus} }
            steps { script { nexusPublisher nexusInstanceId: 'mxs01', nexusRepositoryId: 'devops-usach-nexus', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: "${WORKSPACE}/build/DevOpsUsach2020-0.0.1.jar"]], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.1']]]
            }    }
        }
        stage('pushToNexus-Download'){
            when { expression { params.PushToNexus} }
            steps {
                    script {
                        sh 'curl -X GET http://nexus:8081/repository/devops-usach-nexus/com/devopsusach2020/DevOpsUsach2020/1.0.0/DevOpsUsach2020-1.0.0.jar -O'
                        sh 'ls -ltr'                
                }
            }
        }
        stage('notif-Slack'){
            steps { script { slackSend channel: 'C04BV16891T'
                slackUserIdsFromCommitters tokenCredentialId: 'slack'
            }    }
        }
    }
}