# Run integration tests in Docker

## 1. First execution of the integration tests (Will fail!!)

Run integration tests in a container, executing now `mvn verify`.

**Don't worry** if the test fail, that's expected: it is going to run integration tests against a Redis instance, but now it will say that canno connect to it:

```
Results :

Tests in error: 
  RedisTopicRepositoryTest.setup:29 » RedisConnectionFailure Cannot get Jedis co...
  RedisTopicRepositoryTest.setup:29 » RedisConnectionFailure Cannot get Jedis co...
  RedisTopicRepositoryTest.setup:29 » RedisConnectionFailure Cannot get Jedis co...

Tests run: 3, Failures: 0, Errors: 3, Skipped: 0
```

<details>
<summary>Solution</summary>

```shell
mkdir -p /tmp/dockerm2
docker run -it --rm \
  --volume $(pwd):/code \
  --volume /tmp/dockerm2:/tmp/.m2/repository \
  --workdir /code \
  --user $(id -u) \
  maven:3.5-jdk-8-alpine mvn -Dmaven.repo.local=/tmp/.m2/repository verify
```
</details>

## 2. Create a Redis container

Create a network for integration tests, and create a Redis container connected to that network.

<details>
<summary>Solution</summary>

```shell
docker network create simplequeue-integration-tests
docker run -d --name redis \
  --network simplequeue-integration-tests \
  -p 6379:6379 \
  redis:4-alpine
```
</details>

## 1. Run the test again, but in the same network as Redis


<details>
<summary>Solution</summary>

```shell
mkdir -p /tmp/dockerm2
docker run -it --rm \
  --volume $(pwd):/code \
  --volume /tmp/dockerm2:/tmp/.m2/repository \
  --workdir /code \
  --user $(id -u) \
  --network simplequeue-integration-tests \
  maven:3.5-jdk-8-alpine mvn -Dmaven.repo.local=/tmp/.m2/repository verify
```
</details>
