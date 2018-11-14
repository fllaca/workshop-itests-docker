.PHONY: teardown
teardown:
	docker-compose down

.PHONY: setup
setup:
	docker-compose up -d --build

.PHONY: selenium-test
selenium-test:
	docker-compose run selenium sh -c "/usr/local/bin/python /code/test.py"
