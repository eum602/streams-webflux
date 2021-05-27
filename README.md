# Streaming with WebFlux

## Requirements

Java 11

## Streams

* Fetch a body of quotes
```shell script
 curl http://localhost:8080/quotes -i -H "Accept: application/json"
```

* Fetch a stream of quotes
```shell script
 curl http://localhost:8080/quotes -i -H "Accept: application/x-ndjson"
```

## Consuming from stream - Saves into mongodb

* Start a mongodb instance by simply running

```shell script
./start_mongo.sh
```

* Run the project with

```shell script
mvn spring-boot:run
```