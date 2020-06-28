# Writting Dockerfiles

The [Dockerfile reference](https://docs.docker.com/engine/reference/builder/) docs can be useful for the following exercises

## 1.2.1 Write a Dockerfile based on Debian

This container will run just an `echo` command

Build and run it with:

```shell
docker build -t helloworld .
docker run --rm helloworld
```

<details>
<summary>Solution</summary>

```Dockerfile
FROM debian

CMD echo "hello world!"
```
</details>

## 1.2.2 Use environment variables

Modify the Dockerfile with an Environment Variable to specify the person to greet.

Build and run it with:

```shell
docker build -t helloworld .
docker run --rm helloworld
docker run --rm -e WHO=Fer helloworld
```

<details>
<summary>Solution</summary>

```Dockerfile
FROM debian

ENV WHO=world

CMD echo "hello $WHO"
```
</details>



## 1.2.3 Install Third Party Software

Build and run it with:

```shell
docker build -t helloworld .
docker run -e WHO=Fer --rm -it helloworld
```

<details>
<summary>Solution</summary>

```Dockerfile
FROM debian

ENV WHO=world

RUN apt-get update && apt-get install -y python-pip && \
  pip install coloredlogs

CMD python -c "print \"hello $WHO\""
```
</details>


## 1.2.4 Add your code!

Let's write a (really) simple application in python: create a `sample-app.py` file with the following content:
```python
import coloredlogs, logging, sys

# Create a logger object.
logger = logging.getLogger(__name__)
coloredlogs.install(level='DEBUG')
# Some examples.
logger.info("Hello {}!".format(sys.argv[1]))
logger.warning("this is a warning message")
logger.error("this is an error message")

```

We'll add that file to our Docker image using the `COPY` or `ADD` instruction. As always, build and run it with:

```shell
docker build -t helloworld .
docker run -e WHO=Fer --rm -it helloworld
```

<details>
<summary>Solution</summary>

```Dockerfile
FROM debian

ENV WHO=world

RUN apt-get update && apt-get install -y python-pip && \
  pip install coloredlogs

COPY sample-app.py /sample-app.py

CMD python /sample-app.py $WHO
```
</details>
