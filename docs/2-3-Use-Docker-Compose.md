# Use Docker Compose

## 2.3.1 Write the docker-compose.yaml

Transform the Wordpress deployment in [Lesson 1](1-1-What-Are-Containers.md) to a `docker-compose.yaml`. Once you have it, start the app with:

```
docker-compose up -d
```

**Docker commands:**
```shell
docker network create wordpress
docker run -d --name wp-database \
  -e MYSQL_ROOT_PASSWORD=somewordpress \
  -e MYSQL_DATABASE=wordpress \
  -e MYSQL_USER=wordpress \
  -e MYSQL_PASSWORD=wordpress \
  --network wordpress \
  mysql:5.7

docker run -d --name wp-wordpress \
  -e WORDPRESS_DB_HOST=wp-database:3306 \
  -e WORDPRESS_DB_USER=wordpress \
  -e WORDPRESS_DB_PASSWORD=wordpress \
  -p 8000:80 \
  --network wordpress \
  wordpress:latest
```


<details>
<summary>Solution</summary>

```yaml
version: '2.1'

services:
  wp-database:
    image: mysql:5.7
    environment:
      MYSQL_ROOT_PASSWORD: somewordpress
      MYSQL_DATABASE: wordpress
      MYSQL_USER: wordpress
      MYSQL_PASSWORD: wordpress
  wordpress:
    image: wordpress:latest
    environment:
      WORDPRESS_DB_HOST: wp-database:3306
      WORDPRESS_DB_USER: wordpress
      WORDPRESS_DB_PASSWORD: wordpress
    ports: 
    - 8000:80
```
</details>


## 2.3.2 Write the test environment in a docker-compose.yaml

Now, create a `docker-compose.yaml` for the tests. This will be shorter that the one in the previous exercies.

<details>
<summary>Solution</summary>

```yaml
version: '2.1'

services:
  redis:
    image: redis:4-alpine
  test-runner:
    image: maven:3.5-jdk-8-alpine
    command: mvn verify
    working_dir: /code
    volumes:
      - .:/code
```
</details>

## 2.3.3 Improve the docker-compose.yaml a little

Add all the enhancements about caching local dependencies.

<details>
<summary>Solution</summary>

```yaml
version: '2.1'

services:
  redis:
    image: redis:4-alpine
  test-runner:
    image: maven:3.5-jdk-8-alpine
    command: mvn -Dmaven.repo.local=/tmp/.m2/repository verify
    working_dir: /code
    volumes:
      - .:/code
      - /tmp/dockerm2:/tmp/.m2/repository
```
</details>

## 2.3.4 An easy one: --abort-on-container-exit

![relief](images/relief.gif)

Just run `docker-compose` with `--abort-on-container-exit` ;D 

```
docker-compose up --abort-on-container-exit
```
