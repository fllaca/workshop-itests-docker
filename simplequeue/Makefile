.PHONY: setup
setup:
	docker-compose up -d

.PHONY: clean
clean:
	docker-compose down

.PHONY: test
test: clean setup
	docker-compose run test-runner mvn -f /src verify
	
.PHONY: run
run: clean setup
	docker-compose run -p 8080:8080 test-runner mvn -f /src spring-boot:run
