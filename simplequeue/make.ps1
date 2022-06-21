$cmd=$args[0]
$commit_hash=$(git log -1 --format="%h")

function setup {
	docker-compose -p $commit_hash up -d
}

function clean {
	docker-compose -p $commit_hash down
}

function unit-test {
	clean
	setup
	docker-compose -p $commit_hash run test-runner mvn -f /src/pom.xml test
}

function integration-test {
	clean
	setup
	docker-compose -p $commit_hash run waiter dockerize -wait tcp://redis:6379 -timeout 10s
	# Actual tests:
	docker-compose -p $commit_hash run test-runner mvn -f /src/pom.xml verify
}


& $cmd

