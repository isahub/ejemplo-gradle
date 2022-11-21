def maven_build_test() {
    echo 'maven_build_test ./mvn clean install -e'
    sh './mvnw clean install -e'
}

def maven_completo() {
    steps{
        step{
            sh './mvnw clean compile -e'
        }
        step{
            sh './mvnw clean test -e'
            sh './mvnw clean package -e'           
            sh 'nohup bash ./mvnw spring-boot:run &'    
        }
    }
    /*sh './mvnw clean compile -e'
    sh './mvnw clean test -e'
    sh './mvnw clean package -e'           
    sh 'nohup bash ./mvnw spring-boot:run &'     */       
}

return this