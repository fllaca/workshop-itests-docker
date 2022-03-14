# Run integration tests in Docker

## 2.2.1 First execution of the integration tests (Will fail!!)

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

bash:
```shell
docker run -it --rm \
  --volume $PWD:/code \
  --volume $PWD/.m2:/root/.m2 \
  --workdir /code \
  maven:3.5-jdk-8-alpine mvn verify
```

powershell:
```powershell
docker run -it --rm `
  --volume $PWD:/code `
  --volume $PWD/.m2:/root/.m2 `
  --workdir /code `
  maven:3.5-jdk-8-alpine mvn verify
```
</details>

## 2.2.2 Create a Redis container

Create a network for integration tests, and create a Redis container connected to that network.

<details>
<summary>Solution</summary>

bash:
```shell
docker network create simplequeue-integration-tests
docker run -d --name redis \
  --network simplequeue-integration-tests \
  -p 6379:6379 \
  redis:4-alpine
```

powershell:
```powershell
docker network create simplequeue-integration-tests
docker run -d --name redis `
  --network simplequeue-integration-tests `
  -p 6379:6379 `
  redis:4-alpine
```
</details>

## 2.2.3 Run the test again, but in the same network as Redis


<details>
<summary>Solution</summary>

bash:
```shell
docker run -it --rm \
  --volume $PWD:/code \
  --volume $HOME/.m2:/root/.m2 \
  --workdir /code \
  --network simplequeue-integration-tests \
  maven:3.5-jdk-8-alpine mvn verify
```

powershell:
```powershell
docker run -it --rm `
  --volume $PWD:/code `
  --volume $HOME/.m2:/root/.m2 `
  --workdir /code `
  --network simplequeue-integration-tests `
  maven:3.5-jdk-8-alpine mvn verify
```
</details>
