COMMIT_HASH := $(shell git log -1 --format="%H")

.PHONY: setup
setup:
	docker-compose -p $(COMMIT_HASH) up -d

.PHONY: clean
clean:
	docker-compose -p $(COMMIT_HASH) down

.PHONY: test
test: clean setup
	docker-compose -p $(COMMIT_HASH) run test-runner mvn -f /src verify
	
.PHONY: run
run: clean setup
	docker-compose -p $(COMMIT_HASH) run -p 8080:8080 test-runner mvn -f /src spring-boot:run
