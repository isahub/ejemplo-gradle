def buildMaven() {
    echo 'maven_build_test ./mvn clean install -e'
    sh './mvnw clean install -e'
}

def buildMaven2() {
    sh './mvnw clean compile -e'
    sh './mvnw clean test -e'
    sh './mvnw clean package -e'           
    sh 'nohup bash ./mvnw spring-boot:run &'            
}

def sonarMaven() {
    sh './mvnw clean verify sonar:sonar -Dsonar.projectKey=ejemplo-gradle' //-Dsonar.java.binaries=build'
}

def runTestMaven() {
    sh './mvnw spring-boot:run &'
    sh 'sleep 5'
    //sh "curl -X GET 'http://localhost:8081/rest/mscovid/test?msg=testing'"
    sh 'sleep 5'
    def response = sh(script: "echo \$(curl --write-out '%{http_code}' --silent --output /dev/null http://localhost:8081/rest/mscovid/test?msg=testing)", returnStdout: true);   
    echo "status ${response}"
}

return this