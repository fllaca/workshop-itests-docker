FROM maven:3.5-jdk-8-alpine AS build

WORKDIR /workspace

COPY pom.xml .
# this is a trick to do all dependencies download
RUN mvn verify --fail-never

COPY . .

RUN mvn package

RUN mv target/simplequeue-*.jar target/app.jar

FROM openjdk:8

RUN mkdir -p /opt/simplequeue

COPY --from=build /workspace/target/app.jar /opt/simplequeue

CMD ["sh", "-c", "java -jar /opt/simplequeue/app.jar"]

