job('RunAndTestEnvironment') {
    scm {
        git {
            remote {
                github('danydavidov1/albit_test', 'https')
            }
        }
    }
    steps {
        dockerComposeBuilder {
            useCustomDockerComposeFile(true)
            dockerComposeFile("./docker/docker-compose.yml")
            option {
                startAllServices()
                }
        }        
    }
}