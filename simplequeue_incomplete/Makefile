clean:
	docker-compose down

wait:
	docker-compose run waiter dockerize -wait tcp://redis:6379

setup:
	docker-compose up -d

test: clean setup wait
	docker-compose run test-runner mvn verify

