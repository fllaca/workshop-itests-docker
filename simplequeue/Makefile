COMMIT_HASH := $(shell git log -1 --format="%h")

.PHONY: setup
setup:
	docker-compose -p $(COMMIT_HASH) up -d

.PHONY: clean
clean:
	docker-compose -p $(COMMIT_HASH) down

.PHONY: unit-test
unit-test: clean setup
	# Actual tests:
	docker-compose -p $(COMMIT_HASH) run test-runner mvn -f /src/pom.xml test

.PHONY: integration-test
integration-test: clean setup
	# Avoids race conditions:
	docker-compose -p $(COMMIT_HASH) run waiter dockerize -wait tcp://redis:6379 -timeout 10s
	# Actual tests:
	docker-compose -p $(COMMIT_HASH) run test-runner mvn -f /src/pom.xml verify

.PHONY: run
run: clean setup
	# Runs the application just for local testing purposes
	docker-compose -p $(COMMIT_HASH) run -p 8080:8080 test-runner mvn -f /src spring-boot:run

.PHONY: e2e-test
e2e-test:
	docker-compose -p $(COMMIT_HASH) -f test-e2e.dc.yaml up -d redis backend
	docker-compose -p $(COMMIT_HASH) -f test-e2e.dc.yaml run wait-for-api
	docker-compose -p $(COMMIT_HASH) -f test-e2e.dc.yaml run test

.PHONY: e2e-clean
e2e-clean:
	docker-compose -p $(COMMIT_HASH) -f test-e2e.dc.yaml down

