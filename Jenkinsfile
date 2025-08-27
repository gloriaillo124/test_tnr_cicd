pipeline{
    triggers {
        upstream 'build-and-deploy-integration, '
    }
    agent{
        docker{
            image 'markhobson/maven-chrome'
        }
    }
    stages{
        stage('clean and compile'){
            steps{
                sh 'mvn clean compile'
            }
        }
        stage('Run Tests'){
            steps{
                sh 'mvn test'
            }
        }
    }
    post {
        always {
            junit 'target/surefire-reports/**/*.xml'
        }
    }
}