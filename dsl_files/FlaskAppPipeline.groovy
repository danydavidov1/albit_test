pipelineJob('clone to repo and build image') {
  definition {
    cps {
      script('''
        pipeline {
			environment {
			imagename = "danielavidov/nginx-test-daniel-new"
			registryCredential = 'docker-hub-daniel'
			dockerImage = ''
			tag = 'flask_app'
			}
            agent any
                stages {
                    stage('pull from repo') {
                        steps {
                            git branch: 'main', credentialsId: 'github', url: 'https://github.com/danydavidov1/albit_test.git'
                        }
                    }
                    stage('build flask application') {
                        steps {
                            script {
            					dockerImage = docker.build imagename
            				}
                        }
                    }
					stage('push flask application to docker hub'){
						steps{
							script{
								docker.withRegistry( '', registryCredential ) {
								dockerImage.push(tag)
							}
						}
					}
                }
            }
        }
      '''.stripIndent())
      sandbox()     
    }
  }
}