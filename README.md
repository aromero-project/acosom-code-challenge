# Acosom code challenge


### Pre-requirements 📋

_Needs install following_

```
* Jdk 11
* Maven 
* Git
* Docker
* Docker Compose
```
## Docker execution ⚙️

```
docker network create --attachable -d bridge acosomNet
docker-compose run -d
docker run -it -d --name acosom-mongodb -p 27017 --network acosomNet --restart always -v acosom_mongodb_data:/data/db mongo:latest
```

## Kafka Producer/Consumer execution ⚙️

```
docker network create --attachable -d bridge acosomNet
docker-compose run -d
docker run -it -d --name acosom-mongodb -p 27017 --network acosomNet --restart always -v acosom_mongodb_data:/data/db mongo:latest
```
## Create Kafka topics ⚙️

```
docker-compose exec kafka kafka-topics.sh --create --topic user.interaction.data --partitions 3 --replication-factor 1 --bootstrap-server kafka:9092
docker-compose exec kafka kafka-topics.sh --create --topic user.information.data --partitions 3 --replication-factor 1 --bootstrap-server kafka:9092
docker-compose exec kafka kafka-topics.sh --create --topic user.presentation.data --partitions 3 --replication-factor 1 --bootstrap-server kafka:9092

docker-compose exec kafka kafka-topics.sh --bootstrap-server kafka:9092 --list

```
docker-compose exec kafka kafka-topics.sh --create --topic baeldung_linux --partitions 1 --replication-factor 1 --bootstrap-server kafka:9092


## Flink app Execution ⚙️

```
 * Execute 
```

## Build with 🛠️


* [Spring inizializer](https://start.spring.io/)
* [Maven](https://maven.apache.org/)


## Authors ✒️

* **Abrahan Romero** - *Initial work* - [aromero-alvarez](https://github.com/aromero-alvarez)

---