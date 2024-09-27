run-env:
	docker-compose up redis

test:
	docker-compose up -d redis
	docker-compose up --abort-on-container-exit wait
	docker-compose up --abort-on-container-exit maven

clean:
	docker-compose down

