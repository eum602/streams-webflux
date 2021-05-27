# Streaming with WebFlux

## Streams

* Fetch a body of quotes
```shell script
 curl http://localhost:8080/quotes -i -H "Accept: application/json"
```

* Fetch a stream of quotes
```shell script
 curl http://localhost:8080/quotes -i -H "Accept: application/x-ndjson"
```
