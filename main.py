from flask import Flask
from tools.docker_api import get_all_containers

app = Flask(__name__)

@app.route('/container_list', methods=["GET"])
def get_containers():
    container_list = get_all_containers()

    return(str(container_list))

if __name__ == '__main__':
    app.run(debug = True)
