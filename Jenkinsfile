def MAIL_TO = "webops@visitscotland.net"

def thisAgent
def VS_CONTAINER_BASE_PORT_OVERRIDE
cron_string = ""

// set any environment-specific environment variables here using the format: env.MY_VAR = "conditional_value" }
// please see ci/README_PIPELINE_VARIABLES.md or consult Web Operations for details on environment variables and their purposes
echo "== Setting conditional environment variables"
if (BRANCH_NAME == "develop" && (JOB_NAME ==~ "develop.visitscotland.(com|org)(-mb)?/develop")) {
    echo "=== Setting conditional environment variables for branch $BRANCH_NAME in job $JOB_NAME"
    thisAgent = "xvcdocker"
    env.VS_CONTAINER_BASE_PORT_OVERRIDE = "8099"
    env.VS_RELEASE_SNAPSHOT = "FALSE"
} else if (BRANCH_NAME == "develop" && (JOB_NAME ==~ "develop-nightly.visitscotland.(com|org)(-mb)?/develop")) {
    echo "=== Setting conditional environment variables for branch $BRANCH_NAME in job $JOB_NAME"
    thisAgent = "xvcdocker"
    env.VS_CONTAINER_BASE_PORT_OVERRIDE = "8098"
    env.VS_CONTAINER_PRESERVE = "FALSE"
    cron_string = "@midnight"
} else if (BRANCH_NAME == "develop" && (JOB_NAME ==~ "develop-stable.visitscotland.(com|org)(-mb)?/develop")) {
    echo "=== Setting conditional environment variables for branch $BRANCH_NAME in job $JOB_NAME"
    thisAgent = "xvcdocker"
    env.VS_CONTAINER_BASE_PORT_OVERRIDE = "8100"
} else if (BRANCH_NAME == "develop" && (JOB_NAME ==~ "feature.visitscotland.(com|org)(-mb)?/develop")) {
    echo "=== Setting conditional environment variables for branch $BRANCH_NAME in job $JOB_NAME"
    thisAgent = "xvcdocker"
    env.VS_CONTAINER_BASE_PORT_OVERRIDE = "8097"
} else if (BRANCH_NAME ==~ "ops/feature-environment(s)?-enhancements" && (JOB_NAME ==~ "feature.visitscotland.(com|org)(-mb)?/ops%2Ffeature-environment(s)?-enhancements")) {
    echo "=== Setting conditional environment variables for branch $BRANCH_NAME in job $JOB_NAME"
    thisAgent = "xvcdocker"
    env.VS_CONTAINER_BASE_PORT_OVERRIDE = "8091"
} else {
    env.VS_RELEASE_SNAPSHOT = "FALSE"
    // thisAgent should always be set to "xvcdocker" unless you have been informed otherwise!
    thisAgent = "xvcdocker"
}
echo "==/Setting conditional environment variables"

// set or override any default environment variables here using the format: if (!env.MY_VAR) { env.MY_VAR = "default_value" }
// please see ci/README_PIPELINE_VARIABLES.md or consult Web Operations for details on environment variables and their purposes
// NOTE: these values will only be set if currently null, they may have been set by the "conditional environment variables" section above
echo "== Setting default environment variables"
if (!env.VS_SSR_PROXY_ON) { env.VS_SSR_PROXY_ON = "TRUE" }
if (!env.VS_CONTAINER_PRESERVE) { env.VS_CONTAINER_PRESERVE = "TRUE" }
if (!env.VS_BRXM_PERSISTENCE_METHOD) { env.VS_BRXM_PERSISTENCE_METHOD = "h2" }
if (!env.VS_BRXM_DSSR_SITES) { env.VS_BRXM_DSSR_SITES = "feature-businessevents.visitscotland.com feature-support.visitscotland.com" }
if (!env.VS_SKIP_BUILD_FOR_BRANCH) { env.VS_SKIP_BUILD_FOR_BRANCH = "feature/VS-1865-feature-environments-enhancements-log4j" }
if (!env.VS_BRC_API_STACK_NAME) { env.VS_BRC_API_STACK_NAME = "visitscotland" }
if (!env.VS_BRC_ENV) { env.VS_BRC_ENV = "demo" }
if (!env.VS_BRC_API_STACK_NAME) { env.VS_BRC_API_STACK_NAME = "visitscotland" }
if (!env.VS_BRC_STACK_API_VERSION) { env.VS_BRC_STACK_API_VERSION = "v3" }
if (!env.VS_DOCKER_IMAGE_NAME) { env.VS_DOCKER_IMAGE_NAME = "vs/vs-brxm15:node18" }
if (!env.VS_DOCKER_BUILDER_IMAGE_NAME) { env.VS_DOCKER_BUILDER_IMAGE_NAME = "vs/vs-brxm15-builder:node18" }
if (!env.VS_USE_DOCKER_BUILDER) { env.VS_USE_DOCKER_BUILDER = "TRUE" }
if (!env.VS_RUN_BRC_STAGES) { env.VS_RUN_BRC_STAGES = "FALSE" }
if (!env.VS_BRANCH_PROPERTIES_DIR) { env.VS_BRANCH_PROPERTIES_DIR = "ci/properties" }
if (!env.VS_BRANCH_PROPERTIES_FILE) { env.VS_BRANCH_PROPERTIES_FILE = env.BRANCH_NAME.substring(env.BRANCH_NAME.lastIndexOf('/') + 1) + ".properties" }
echo "==/Setting default environment variables"

import groovy.json.JsonSlurper

pipeline {
    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        disableConcurrentBuilds()
        // to-do
        // gp: investigate milestone caclulation to cancel current build if a new one starts
        // - see: https://stackoverflow.com/questions/40760716/jenkins-abort-running-build-if-new-one-is-started/44326216
        // - see: https://www.jenkins.io/doc/pipeline/steps/pipeline-milestone-step/#pipeline-milestone-step
        timestamps()
    }
    agent {label thisAgent}
    triggers { cron( cron_string ) }
    environment {
        MAVEN_SETTINGS = credentials('maven-settings')
    }

    tools {
        maven 'Maven 3.5.4'
        jdk 'jdk11'
    }

    stages {

		stage ('prepare') {
            steps {
                // Set any defined build property overrides for this work-in-progress branch
                sh '''
                    printenv | sort > printenv.prepare
                    ./ci/infrastructure/scripts/infrastructure.sh setvars
                    printenv | sort > printenv.pre-build.setvars
                '''
                sh '''
                    set +x
                    echo; echo "running stage $STAGE_NAME on $HOSTNAME"
                    echo; echo "looking for branch specific properties file at $WORKSPACE/$VS_BRANCH_PROPERTIES_DIR/$VS_BRANCH_PROPERTIES_FILE"
                    echo " - if the pipeline fails at this point please check the format of your properties file!"
                '''
                // make all branch-specific variables available to pipeline, load file must be in env.VARIABLE="VALUE" format
                script {
                    if (fileExists("$WORKSPACE/$VS_BRANCH_PROPERTIES_DIR/$VS_BRANCH_PROPERTIES_FILE")) {
                        echo "loading environment variables from $WORKSPACE/$VS_BRANCH_PROPERTIES_DIR/$VS_BRANCH_PROPERTIES_FILE"
                        load "$WORKSPACE/$VS_BRANCH_PROPERTIES_DIR/$VS_BRANCH_PROPERTIES_FILE"
                        sh 'set +x; printenv | sort > printev.prepare.loadvars'
                    } else {
                        echo "branch specific properties won't be loaded, file $WORKSPACE/$VS_BRANCH_PROPERTIES_DIR/$VS_BRANCH_PROPERTIES_FILE does not exist"
                    }
                }
            }
        } // end stage

		stage ('vs compile & package in docker') {
			when {
				allOf {
					expression {return env.VS_RUN_BRC_STAGES != 'TRUE'}
					expression {return env.VS_SKIP_VS_BLD != 'TRUE'}
					expression {return env.VS_USE_DOCKER_BUILDER == 'TRUE'}
					expression {return env.BRANCH_NAME != env.VS_SKIP_BUILD_FOR_BRANCH}
				}
			}
			agent {
				docker {
					image '$VS_DOCKER_BUILDER_IMAGE_NAME'
					args '-v $JENKINS_HOME/.m2:$WORKSPACE/.m2:rw,z --env VS_AGENT_IS_DOCKER=TRUE'
					label 'thisAgent'
					reuseNode true
				}
			}
			steps {
				//sh './ci/infrastructure/scripts/infrastructure.sh setvars'
				sh '''
					set +x
					echo; echo "running stage $STAGE_NAME on $HOSTNAME"
					export HOME=$WORKSPACE
					export MAVEN_OPTS="-Duser.home=$HOME"
					mvn --batch-mode clean package
        '''
			}
			post {
				success {
					sh '''
						set +x
						echo; echo "stage $STAGE_NAME succeeded on $HOSTNAME"
						echo; echo "entering post-success steps for $STAGE_NAME on $HOSTNAME"
						export HOME=$WORKSPACE
						export MAVEN_OPTS="-Duser.home=$HOME"
						echo; echo "running mvn --batch-mode -Pdist-with-development-data on $HOSTNAME"
						mvn --batch-mode install -Pdist-with-development-data
					'''
					stash allowEmpty: true, includes: 'target/*', name: 'brxm-artifact'
					stash allowEmpty: true, includes: 'ui-integration/ssr/server/*,ui-integration/node_modules/**/*,ui-integration/build/*', name: 'ssr-files'
					mail bcc: '', body: "<b>Notification</b><br>Project: ${env.JOB_NAME} <br>Build Number: ${env.BUILD_NUMBER} <br> build URL: ${env.BUILD_URL}", cc: '', charset: 'UTF-8', from: '', mimeType: 'text/html', replyTo: '', subject: "Maven build succeeded at ${env.STAGE_NAME} for ${env.JOB_NAME}", to: "${MAIL_TO}";
				}
				failure {
					mail bcc: '', body: "<b>Notification</b><br>Project: ${env.JOB_NAME} <br>Build Number: ${env.BUILD_NUMBER} <br> build URL: ${env.BUILD_URL}", cc: '', charset: 'UTF-8', from: '', mimeType: 'text/html', replyTo: '', subject: "Maven build FAILED at ${env.STAGE_NAME} for  ${env.JOB_NAME}", to: "${MAIL_TO}";
				}
			}
		} // end stage

		// -- 2020-07-12: The three 'brxm' and the two 'brc' stages are based on https://developers.bloomreach.com/blog/2019/set-up-continuous-deployment-of-your-brxm-project-in-brcloud-using-jenkins.html
		// --           in time, the connect, upload and deploy stages will be moved into bash scripts and run from a different Jenkins server

    // -- 20200712: QUESTION FOR SE, "why do each of the next three profiles run a UI step that takes ~3.5 minutes?"

		stage ('brxm compile') {
			when {
				allOf {
					expression {return env.VS_RUN_BRC_STAGES == 'TRUE'}
					expression {return env.VS_SKIP_BRC_BLD != 'TRUE'}
					expression {return env.BRANCH_NAME != env.VS_SKIP_BUILD_FOR_BRANCH}
				}
			}
			steps {
				withMaven(maven: 'Maven 3.5.0', options: [artifactsPublisher(disabled: true)]) {
					sh '$MVN_CMD clean compile -Pdefault -DskipTests'
				}
			}
		} //end stage

		stage ('brxm unit-test') {
			when {
				allOf {
					expression {return env.VS_RUN_BRC_STAGES == 'TRUE'}
					expression {return env.VS_SKIP_BRC_TST != 'TRUE'}
					expression {return env.BRANCH_NAME != env.VS_SKIP_BUILD_FOR_BRANCH}
				}
			}
			steps {
				sh 'mvn test -Pdefault -P!fed-build'
			}
		} //end stage

		stage ('brxm package') {
			when {
				allOf {
					expression {return env.VS_RUN_BRC_STAGES == 'TRUE'}
					expression {return env.VS_SKIP_BRC_PKG != 'TRUE'}
					expression {return env.BRANCH_NAME != env.VS_SKIP_BUILD_FOR_BRANCH}
				}
			}
			steps {
				// -- 20200712: QUESTION FOR SE, "brC does not recognise the package, maybe it needs Enterprise Features?"
				sh 'mvn verify && mvn -Pdist-with-development-data -P!fed-build -DskipTests'
			}
			post {
				success {
				//sh 'mvn -f pom.xml install -P !default'
				// -- 20200712: extra install step removed
				//sh 'mvn -f pom.xml install -Pdist-with-development-data'
				mail bcc: '', body: "<b>Notification</b><br>Project: ${env.JOB_NAME} <br>Build Number: ${env.BUILD_NUMBER} <br> build URL: ${env.BUILD_URL}", cc: '', charset: 'UTF-8', from: '', mimeType: 'text/html', replyTo: '', subject: "SUCCESS CI: Project name -> ${env.JOB_NAME}", to: "${MAIL_TO}";
				}
				failure {
					mail bcc: '', body: "<b>Notification</b><br>Project: ${env.JOB_NAME} <br>Build Number: ${env.BUILD_NUMBER} <br> build URL: ${env.BUILD_URL}", cc: '', charset: 'UTF-8', from: '', mimeType: 'text/html', replyTo: '', subject: "ERROR CI: Project name -> ${env.JOB_NAME}", to: "${MAIL_TO}";
				}
			}
		} //end stage

		stage ('build feature env') {
			when {
				anyOf {
					branch 'develop'
					branch 'release/*'
					changeRequest()
					environment name: 'VS_BUILD_FEATURE_ENVIRONMENT', value: 'true'
					expression {return env.VS_BUILD_FEATURE_ENVIRONMENT ==~ /(TRUE|true)/}
				}
			}
			steps{
				dir("${env.WORKSPACE}") {
					script {
						try {
							unstash 'brxm-artifact'
						} catch (e) {
							print "Unstash brxm-artifact failed, ignoring"
						}
						try {
							unstash 'ssr-files'
						} catch (e) {
							print "Unstash ssr-files failed, ignoring"
						}
					}
				}
				script{
					sh './ci/infrastructure/scripts/infrastructure.sh'
				}
				// make all VS_ variables available to pipeline, load file must be in env.VARIABLE="VALUE" format
				script {
					if (fileExists("$WORKSPACE/ci/vs-last-env.quoted")) {
						echo "loading environment variables from $WORKSPACE/ci/vs-last-env.quoted"
						load "$WORKSPACE/ci/vs-last-env.quoted"
						echo "found ${env.VS_COMMIT_AUTHOR}"
						sh 'set +x; printenv | sort > printenv.buildfeatureenv.loadvars'
					} else {
						echo "cannot load environment variables, file does not exist"
					}
				}
			}
		} //end stage

		stage ('post-build actions'){
			parallel {
				stage('SonarQube BE Scan') {
					when {
						branch 'develop'
					}
					steps {
						catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
							withSonarQubeEnv(installationName: 'SonarQube', credentialsId: 'sonarqube') {
								sh "PATH=/usr/bin:$PATH; mvn sonar:sonar -Dsonar.host.url=http://172.28.87.209:9000 -s $MAVEN_SETTINGS"
								// setting PATH=/usr/bin:$PATH; above allows NodeJS 10.16.3 to be the default and prevents and error at the CSS scan
							}
						}
					}
				}

				stage('SonarQube FE scan') {
					when {
						branch 'develop'
					}
					environment {
						scannerHome = tool 'SonarQube_4.0'
					}
					steps {
						catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
							withSonarQubeEnv(installationName: 'SonarQube', credentialsId: 'sonarqube') {
								sh '''
									PATH=/usr/bin:$PATH; ${scannerHome}/bin/sonar-scanner \
									-Dsonar.sources=./ui-integration \
									-Dsonar.projectKey=VS2019-FE \
									-Dsonar.host.url=http://172.28.87.209:9000 \
									-Dsonar.login=9fa63cfd51d94fb8e437b536523c15a9b45ee2c1
								'''
							}
							// setting PATH=/usr/bin:$PATH; above allows NodeJS 10.16.3 to be the default and prevents and error at the CSS scan
						}
					}
				}
			} // end parallel stages
		} // end stage post-build actions

  } //end stages

	post{
		success{
			script{
				sh 'sh ./ci/infrastructure/scripts/infrastructure.sh displayreport'
			}
		}
		aborted{
			script{
				try{
					sh ' '
				}catch(err){
					sh 'echo "an error occurred in abort script"'
				}
			}
		}
	} //end post
} //end pipeline

// function to read in a properties file (see https://medium.com/@dhamodharakkannan/jenkins-loading-variables-from-a-file-for-different-environments-d442a2a48bce)
// may not be required if simple "load" command works
def readEnvironmentVariables(path){
	def properties = readProperties file: path
	keys= properties.keySet()
	for(key in keys) {
		value = properties["${key}"]
		env."${key}" = "${value}"
	}
}

// TO-DO:
// gp: change sonarqube project target to a short version of the project name

// DONE:
// gp: investigate the use of stash/unstash to make build artefacts available to other nodes
//     - see: https://www.cloudbees.com/blog/parallelism-and-distributed-builds-jenkins
//     - this could potentially allow the running of all Lighthouse tests on a separate node
//     - experiment with a simple echo on a different node (stash/unstash)