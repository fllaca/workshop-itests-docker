# Continuous Integration

## 3.1.1 Create a Makefile

Create a Makefile that will orchestrate the `docker-compose` commands.
* Setup
* Test
* Clean

<details>
<summary>Solution</summary>

Makefile:
```makefile

.PHONY: clean
clean:
	docker-compose down

.PHONY: test
test: clean
	docker-compose up --abort-on-container-exit

```

powershell:
```powershell
$cmd=$args[0]

function clean {
	docker-compose down -v
}

function test {
	clean
	docker-compose up --abort-on-container-exit
}


& $cmd

```
</details>

## 3.1.2 Race conditions

Let's simulate that Redis is taking too long to start. For instance, running it like this:

```
docker run -it --rm redis sh -c "sleep 30 && redis-server"
```

Change `docker-compose.yaml` to run Redis with that `sleep` command and see what happens when running the tests.

### Wait for a TCP port to be available - dockerize

We'll make use of [jwilder/dockerize](https://github.com/jwilder/dockerize) to implement an active wait for the Redis service, so we don't break the tests. Add a new `dockerize` container to `docker-compose.yaml` and modify the Makefile to perform an extra "wait" step.

> **Hint:** one way of accomplishing this can be using the `docker-compose run` subcommand

<details>
<summary>Solution</summary>

docker-compose.yml:
```yaml
version: '2.1'

services:
  redis:
    image: redis:4-alpine
    command: sh -c "sleep 30 && redis-server"
  waiter:
    image: jwilder/dockerize
    command: "true"
  test-runner:
    image: maven:3.5-jdk-8-alpine
    command: "true"
    working_dir: /code
    volumes:
      - .:/code
      - ./.m2:/root/.m2
```

Makefile:
```makefile

.PHONY: clean
clean:
	docker-compose down

setup:
	docker-compose up -d

wait:
	docker-compose run waiter dockerize -wait tcp://redis:6379 -timeout 30s

verify:
	docker-compose run test-runner mvn verify

.PHONY: test
test: clean setup wait verify
```

powershell:
```powershell
$cmd=$args[0]

function clean {
	docker-compose down -v
}

function setup {
	docker-compose up -d
}

function verify {
	docker-compose run test-runner mvn verify
}

function wait {
	docker-compose run waiter dockerize -wait tcp://redis:6379 -timeout 30s
}


& $cmd

```
</details>


