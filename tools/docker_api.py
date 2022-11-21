import docker


client = docker.from_env()

def get_all_containers():
    containers = client.containers.list()
    return (len(containers))

print(get_all_containers())

