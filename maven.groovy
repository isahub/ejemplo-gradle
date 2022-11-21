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

return this