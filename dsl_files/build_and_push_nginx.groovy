job('build and pull nginx') {
    scm {
        git {
            remote {
                github('danydavidov1/albit_test', 'https')
            }
        }
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('danielavidov/nginx-test-daniel-new')
            tag('nginx_proxy')
            DockerfilePath('./nginx/Dockefile')
            registryCredentials('docker-hub-daniel')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}

