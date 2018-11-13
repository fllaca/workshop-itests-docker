# Run unit tests in Docker

## 2.1.1 First execution of tests in Docker

Go the `simplequeue_incomplete` project:

```
cd simplequeue_incomplete
```

There's a maven project there:

```
.
├── pom.xml
├── README.md
└── src
```

Unit tests can be run with the command `mvn test`. But... you'll need Maven... Let's run the unit tests inside a container usind `docker run`!

> **Hint 1:** Okay... I'll tell you the Docker image you need: `maven:maven:3.5-jdk-8-alpine`.
>
> **Hint 2:** in shell commands, `$(pwd)` is an "alias" of the current directory.

<details>
<summary>Solution</summary>

```shell
docker run -it --rm \
  --volume $(pwd):/code \
  --workdir /code \
    maven:3.5-jdk-8-alpine mvn test
```
</details>

## 2.1.2 Cache local dependencies

As you can see, `mvn` downloaded all the dependencies, even if we already get that locally. Let's make things faster by mounting the `/root/.m2` directory.

<details>
<summary>Solution</summary>

```shell
mkdir -p /tmp/dockerm2
docker run -it --rm \
  --volume $(pwd):/code \
  --volume /tmp/dockerm2:/root/.m2 \
  --workdir /code \
    maven:3.5-jdk-8-alpine mvn test
```
</details>

## 2.1.3 Run with a non-root user

By default, the `maven` containers are run as `root`, which is a bad practice. Run the container using a **non-root** user.

> **Hint:** `$(id -u)` will give you the current user Id.

Also, you'll need to use the `-Dmaven.repo.local=/tmp/.m2/repository` option to override the default `/root/.m2` repo location:

```shell
mvn -Dmaven.repo.local=/tmp/.m2/repository test
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
    maven:3.5-jdk-8-alpine mvn -Dmaven.repo.local=/tmp/.m2/repository test
```
</details>