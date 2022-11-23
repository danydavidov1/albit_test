job('RunAndTestEnvironment') {
  triggers {
        upstream('build and pull nginx', 'SUCCESS')
    }
    steps {
      	copyArtifacts('pull from repo') {
            includePatterns('check_env.sh, docker/docker-compose.yml')
            targetDirectory('.')
            flatten()
            optional()
            buildSelector {
            	workspace()
            }
        }
        dockerComposeBuilder {
            useCustomDockerComposeFile(true)
            dockerComposeFile('docker-compose.yml')
            option {
                startAllServices()
                }
        }
        shell {
            command("./check_env.sh")
        }
        dockerComposeBuilder {
            useCustomDockerComposeFile(true)
            dockerComposeFile("docker-compose.yml")
            option {
                stopAllServices()
                }
        }         
    }
}
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
job('build and pull nginx') {
  triggers {
        upstream('clone to repo and build image', 'SUCCESS')
    }
    steps {
        copyArtifacts('pull from repo') {
            includePatterns('nginx/Dockerfile, nginx/default.conf')
            targetDirectory('.')
            flatten()
            optional()
            buildSelector {
            	workspace()
            }
        }
        dockerBuildAndPublish {
            repositoryName('danielavidov/nginx-test-daniel-new')
            tag('nginx_proxy')
            registryCredentials('docker-hub-daniel')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}