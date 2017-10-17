docker run -it --rm -v $(pwd):/src --link some-redis:redis -w /src \
    maven:3.5-jdk-8-alpine mvn verify
