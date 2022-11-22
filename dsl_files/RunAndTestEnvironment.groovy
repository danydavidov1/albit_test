job('RunAndTestEnvironment') {
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