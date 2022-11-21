from flask import Flask
import docker

client = docker.from_env()

app = Flask(__name__)

@app.route('/container_list', methods=["GET"])
def get_containers():
    containers = client.containers.list()
    return (str(len(containers)))

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug = True)