# Routing Service

finds the best users / groups for a routing strategy

## Test & Build

run 
```bash
mvn clean package
docker build -t routingservice:beta .
```

or simply type
```bash
sh build.sh
```

## Run

### Run standalone

run
```bash
docker run -d --rm -p "8080:8080" routingservice:beta
```

or simple type
```bash
sh run.sh
```

### Run in network 

1. create a network
    ```bash
    docker network create routing
    ```
    
2. publishStart each service in the network and give them a dns name
    ```bash
    docker run --rm -d --net=routing --network-alias=availabilityservice -v routing_availabilityservice:/persistence availabilityservice:beta
    docker run --rm -d --net=routing --network-alias=skillservice -v routing_skillservice:/persistence skillservice:beta
    docker run --rm -d --net=routing -p "8080:8080" routingservice:beta  
    ```

## Access

The simplest use case is to route a task directly which requires no further services
```bash
curl -d '{"strategy": {"type": "http://routing.com/routingservice/v1/types/strategies/direct","user": "1"}}' -H 'Content-Type: application/json' http://localhost:8080/api/v1/routing
```

More complex is a skill based routing. this require the skill- and availabilityservice to be up. Look at 'Run' to start them. 

```bash
curl -d '{"strategy": {"type": "http://routing.com/routingservice/v1/types/strategies/requirement","requirements": [{"type": "http://routing.com/routingservice/v1/types/requirement/skill/integer","skillKey": "banking","value": 10}]}}' -H 'Content-Type: application/json' http://localhost:8080/api/v1/routing
```

## Enable security

1. make sure to have a running keycloak instance in the docker network which is accessable (dns 'keycloak')
2. export keycloak.json of routingservice and replace it with the existing one under /resources/webapp/WEB-INF
3. uncomment security section in /resources/project-defaults.yml
4. create a keycloak client that has access to availability-, skill- and eventually taskservice and export its keycloak.json to src/main/resources

## Enable TaskService plugin

To include the count of open task an user has you need to enable the taskservice plugin. 

Just uncomment the dependency routingservice-plugin-task in pom.xml of routingservice and rebuild

## Implement own plugin

tbd