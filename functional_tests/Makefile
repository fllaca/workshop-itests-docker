.PHONY: teardown
teardown:
	docker-compose down

.PHONY: setup
setup:
	docker-compose up -d --build

.PHONY: selenium-test
selenium-test:
	docker-compose run selenium sh -c "nosetests --nologcapture -v /code/test.py"
