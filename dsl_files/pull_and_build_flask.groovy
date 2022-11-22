job('pull from repo') {
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
            tag('flask_app')
            registryCredentials('docker-hub-daniel')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}

