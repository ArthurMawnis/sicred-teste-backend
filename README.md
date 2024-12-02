# SICREDI backend test

## Description

Cooperative can setup assemblies, so it's members decide the direction of specific agendas through vote sessions.
The API describes the agenda's voting proccess. It's capabilities are:
1. Create a agenda
2. Open a voting session with a default timeout of 1 minute
3. Support the voting session
4. Calculate the votes at the ending of each session

Notes:
Some business rules were assumed due to the simplicity requirement on project scope. A few of those rules are:

1. Any agenda CAN HAVE more than one voting session. Eg.: On a 200 member assembly, 100 can vote before the lunch and 100 will vote after their lunch
2. CPF validation uses the following rules: If this conversion to number is ODD, then, the CPF is UNABLE_TO_VOTE; if its even, the CPF is ABLE_TO_VOTE.
3. On API versioning (bonus task no. 4): The base URL path does specify the API's version: api/v1, eg. This approach requires that breaking changes come into
 a whole new API. Since most of API resources are heavily dependent on others, this approach isnt prejudicial on most cases.
4. Accordingly to the requirements, auth as abstract and all endpoint access are allowed.
5. Due to the simplicity requirement: HATEOAS wasn't implemented. Also, we should use messaging brokers on the vote creation endpoint, so we don't lost the vote data, but this implementation was ommited.

## Technical decisions

Spring: Efficient on startup and shutdown proccess and can support reactive api model.

Java 23: The latest version. Using latest versions improve security and perfomance, since those version have fixed issues on previous versions.

mongoDB: The API should support millions of operations. The most common and intense use-case is voting on a session, it is: a db primarily writing operation.
Due to being a NOSQL db, mongo offer us a faster writing operation on cost of the reading operations. Alternatively we could use Cassanda and postgres, reserving
 Cassandra only for votes inserions. DUE TO THE SIMPLICITY REQUIREMENT, mongodb had the best cost/benefit.

RabbitMQ: It's reliable and has less latency than kafka. 

## Installation

Clone the repository:
```git clone https://github.com/ArthurMawnis/sicred-teste-backend.git```

On application.properties, set either 'dev' or 'test' as your environment profile:
```spring.profiles.active=dev```

Then, specify your LOCAL mongodb server:

```spring.data.mongodb.host=localhost```
```spring.data.mongodb.port=27017```
```spring.data.mongodb.database=sicredi```

Or a remote one:
```spring.data.mongodb.uri=${MONGODB_URI}```
```spring.data.mongodb.authentication-database=${MONGODB_AUTH_DB}```

Setup your rabbitMQ server connection config:

```spring.rabbitmq.host=127.0.0.1```
```spring.rabbitmq.port=5672```
```spring.rabbitmq.username=guest```
```spring.rabbitmq.password=guest```

Create your queue and exchange, then set on application properties:

```rabbitmq.queue=votes_queue```
```rabbitmq.exchange=votes_exchange```

Tip: Avoid a local installation of rabbitMQ with a docker container: https://hub.docker.com/_/rabbitmq

Execute:
```mvn spring-boot:run```
