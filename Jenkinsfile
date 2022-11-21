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
        booleanParam(name: 'PushToNexus', defaultValue: false, description: '')
    }
    /*environment {
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "nexus:8081"
        NEXUS_REPOSITORY = "devops-usach-nexus"
        NEXUS_CREDENTIAL_ID = "nexus2"
    }*/
    stages {
        stage('Load_Scripts'){
            steps{
                script{
                    mvn_script = load "maven.groovy"
                    grd_script = load "gradle.groovy"
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
                script{
                    mvn_script.maven_build_test()
                }
            }
        }
        stage('build-gradle2'){
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
        stage('build-gradle'){
            steps{
                sh './gradlew build'
            }
        }
        stage('pushToNexus'){
            when {
                expression { params.PushToNexus }
            }
            steps {
                script {
                   nexusPublisher nexusInstanceId: 'mxs01', nexusRepositoryId: 'devops-usach-nexus', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: "${WORKSPACE}/build/DevOpsUsach2020-1.0.0.jar"]], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '1.0.0']]]
                }
                /*script {
                    pom = readMavenPom file: "./pom.xml";
                    filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
                    echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                    artifactPath = filesByGlob[0].path;
                    artifactExists = fileExists artifactPath;
                    if(artifactExists) {
                        echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
                        nexusArtifactUploader(
                            nexusVersion: NEXUS_VERSION,
                            protocol: NEXUS_PROTOCOL,
                            nexusUrl: NEXUS_URL,
                            groupId: pom.groupId,
                            version: pom.version,
                            repository: NEXUS_REPOSITORY,
                            credentialsId: NEXUS_CREDENTIAL_ID,
                            artifacts: [
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: artifactPath,
                                type: pom.packaging],
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: "pom.xml",
                                type: "pom"]
                            ]
                        );
                    } else {
                        error "*** File: ${artifactPath}, could not be found";
                    }
                }*/
            }
        }
    }
}