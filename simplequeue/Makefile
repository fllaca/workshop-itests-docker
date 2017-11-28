COMMIT_HASH := $(shell git log -1 --format="%h")
USER_ID := $(shell id -u)

.PHONY: setup
setup:
	docker-compose -p $(COMMIT_HASH) up -d

.PHONY: clean
clean:
	docker-compose -p $(COMMIT_HASH) down

.PHONY: test
test: clean setup
	# Avoids race conditions:
	docker-compose -p $(COMMIT_HASH) run -u $(USER_ID) waiter dockerize -wait tcp://redis:6379 -timeout 10s
	# Actual tests:
	docker-compose -p $(COMMIT_HASH) run -u $(USER_ID) test-runner mvn -Duser.home=/home/user -f /src/pom.xml verify
	
.PHONY: run
run: clean setup
	# Runs the application just for local testing purposes
	docker-compose -p $(COMMIT_HASH) run -u $(USER_ID) -p 8080:8080 test-runner mvn -Duser.home=/home/user -f /src spring-boot:run
