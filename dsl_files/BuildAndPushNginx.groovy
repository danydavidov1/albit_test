job('build and pull nginx') {
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