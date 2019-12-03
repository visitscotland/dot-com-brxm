pipeline {
 options {
    buildDiscarder(logRotator(numToKeepStr: '5'))
  }
 agent {label 'docker-02'}
 tools {
        maven 'Maven 3.3.9'
        jdk 'jdk1.8.0'
    }
    stages {

		stage ('Checkout dependencies') {
			steps {
              	// create a directory for the checkout then run the Git command within that directory, the package.json file must be aware of this location which introduces fragility/cross-dependency, could this be improved?
        		sh 'mkdir -p design-system'
        		dir('design-system') {
                    //git branch: 'feature/VS-560-ui-meganav-with-build-products', credentialsId: '12a55ebf-608d-4b3e-811c-e4ad04f61f43', url: 'https://bitbucket.visitscotland.com/scm/vscom/design-system.git'
                  	checkout([$class: 'GitSCM', branches: [[name: '*/feature/VS-851-breadcrumb-with-build-products']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'SparseCheckoutPaths',  sparseCheckoutPaths:[[$class:'SparseCheckoutPath', path:'dist/']]]], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '12a55ebf-608d-4b3e-811c-e4ad04f61f43',url: 'https://bitbucket.visitscotland.com/scm/vscom/design-system.git']]])
                }
			}
		}

      stage ('Build Application') {
            steps {
                sh 'mvn -f pom.xml clean package'
            }
            post {
                success {
                    //sh 'mvn -f pom.xml install -P !default'
                  	sh 'mvn -f pom.xml install -P dist'
                	mail bcc: '', body: "<b>Notification</b><br>Project: ${env.JOB_NAME} <br>Build Number: ${env.BUILD_NUMBER} <br> build URL: ${env.BUILD_URL}", cc: '', charset: 'UTF-8', from: '', mimeType: 'text/html', replyTo: '', subject: "SUCCESS CI: Project name -> ${env.JOB_NAME}", to: "gavin.park@visitscotland.com";
                }
                failure {
                    mail bcc: '', body: "<b>Notification</b><br>Project: ${env.JOB_NAME} <br>Build Number: ${env.BUILD_NUMBER} <br> build URL: ${env.BUILD_URL}", cc: '', charset: 'UTF-8', from: '', mimeType: 'text/html', replyTo: '', subject: "ERROR CI: Project name -> ${env.JOB_NAME}", to: "gavin.park@visitscotland.com";
                }
            }
        }

        stage ('Build environment'){
            steps{
                script{
                    sh 'sh ./infrastructure/scripts/docker.sh'
                }
            }
        }

        stage ('Availability notice'){
// "input" section commented out for now - useful for when there is genuinely a need to pause for an answer
/*
            input{
                message "This environment will run until the next push is made the bitbucket repo."
            }
*/
            steps {
                sh 'echo "This environment will run until the next commit to bitbucket is detected."'
            }
        }
    }
	post{
		aborted{
			script{
                try{
                    sh ' '
                }catch(err){
                    sh 'echo "an error occurred in abort script"'
                }
            }
        }
    }
}
