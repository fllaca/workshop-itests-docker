# Simple Queue

List Topics:

```
curl localhost:8080/topics
```

Create Topic:
```
curl -X POST \
	-H "Content-Type: application/json" \
	-d '{"name": "test"}' \
	localhost:8080/topics
```

```
curl -X PUT \
	-H "Content-Type: application/json" \
	-d '{"message": "test"}' \
	localhost:8080/topics/test/push
```
