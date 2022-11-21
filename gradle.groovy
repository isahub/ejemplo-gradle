def buildGradle(){
        sh './gradlew build'
}

def sonarGradle(){
        sh './gradlew sonarqube -Dsonar.projectKey=ejemplo-gradle -Dsonar.projectName=ejemplo-gradle -Dsonar.java.binaries=build'
}

def runTest(){
        sh './gradlew bootrun &'
        sh 'sleep 5'
        sh "curl -X GET 'http://localhost:8081/rest/mscovid/test?msg=testing'"
}

return this