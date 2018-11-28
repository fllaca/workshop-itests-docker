# Docker Cheat Sheet

This is just a quick reference to most common `docker` commands used during the workshop. For a full list of commands and options please refer to the [Official Docker CLI commands reference](https://docs.docker.com/engine/reference/run/) and [Official Docker-Compose CLI commands reference](https://docs.docker.com/compose/reference/down/)

## Docker

|Command|Description|COmmon options|Examples|
|-|-|-|-|
|`docker run <options>  <image-name> <command>`  | start a container from a image |- **-d:** run in background <br> - **-p:** map ports <br> - **-v:** mount a volume <br> - **--rm:** autoremove the container at exit <br> - **--network:** connect the container to a network <br> - **--name:** give a name to the container <br> - **-e:** set environment variables <br> - **-w:** specify the current directory for the container|```docker run ubuntu:16.04``` <br> ```docker run ubuntu:16.04 echo hello``` <br> ```docker run -p 80:80 nginx``` <br> ```docker run -v /home/myuser/.m2:/root/.m2 maven``` <br> ```docker run -d redis``` <br> ```docker run -d --network integration-tests redis``` <br>  ```docker run --name database mysql``` <br>  ```docker run -e MYSQL_ROOT_PASSWORD=1234 mysql``` |
|`docker network list/create/delete <network-name>`| list/create/delete a Docker network| | ```docker network create integration-tests``` <b> ```docker network delete integration-tests``` <br> ```docker network list```|
|`docker build -t <image-name> <folder>` |build a Docker image from the Dockerfile inside `<folder>` and name it `<image-name>` | |```docker build -t helloworld .``` |
|`docker rm <name-or-id>`|remove container(s) |- **-f:** force |```docker rm database``` <br> ```docker rm e2e744446cb5``` <br> ```docker rm -f database``` |
|`docker ps <option>`| list containers | - **-a:** list all, including exited containers <br> - **-q:** "quiet", display only the container id | ```docker ps -a``` <br> ```docker ps -a -q``` |

## Docker-compose

|Command|Description|Common options|Examples|
|-|-|-|-|
|`docker-compose up`| start the containers |- **-d:** run in background| ```docker-compose up -d``` |
|`docker-compose down`| kill and remove the containers |-| ```docker-compose down``` |
|`docker-compose ps`| show the running containers |-| ```docker-compose ps``` |


## Useful commands

|Command|Description|
|-|-|
|`docker rm -f $(docker ps -aq)`| remove ALL the containers |
|`docker run -v $(pwd):/dest/path some-image`|mount the current directory into `/dest/path`|

