# Cargo Tracker 0.1

This sample Spring Boot project is used to illustrate the patterns of Domain-Driven Design. 
It is based on the source code<sup id="a1">[1](#f1)</sup> in
Chapter 4 of the book _Practical Domain-Driven Design in Enterprise Java_ with modification.

#### All Bounded Contexts of Cargo Tracker
<img src="fig1_cargo_tracker.png" alt="cargo tracker" style="width:750px">

#### Booking Bounded Context - Domain Model
<img src="fig2_booking_bounded_context_domain_model.png" alt="booking domain model" style="width:750px">

__Command classes in Booking__

Commands are similar to domain events, but they explicitly model 
what update is made to the system.
They play an important role in the CQRS architecture.

<img src="fig3_booking_bounded_context_commands.png" alt="booking domain service" style="width:750px">

___TODO:___
_The [Delivery.java](./bookingms/src/main/java/csci318/demo/cargotracker/bookingms/domain/model/valueobjects/Delivery.java) 
class (value object) includes some business logic. Consider how to separate the logic in a separate 
Domain Service class_

## Apache Kafka Setup
This Spring Boot project uses Apache Kafka as a messaging platform.
To run this project, you need to set up Kafka first.

#### Linux and MacOS
Download a **binary package** of Apache Kafka (e.g., `kafka_2.13-3.7.0.tgz`) from 
[https://kafka.apache.org/downloads](https://kafka.apache.org/downloads)
and upzip it.
In the Terminal, `cd` to the unzip folder, and start Kakfa with the following commands (each in a separate Terminal session):
```bash
./bin/zookeeper-server-start.sh ./config/zookeeper.properties
```
```bash
./bin/kafka-server-start.sh ./config/server.properties
```

#### Windows
Download a **binary package** of Apache Kafka (e.g., `kafka_2.13-2.8.0.tgz`) from 
[https://kafka.apache.org/downloads](https://kafka.apache.org/downloads) 
and unzip it to a directory, e.g., `C:\kafka`&mdash;Windows does not like a complex path name (!).

<!--
In the configuration file `C:\kafka\config\zookeeper.properties`, comment out the line `"dataDir=/tmp/zookeeper"`. In `C:\kafka\config\server.properties`, change the line `"log.dirs=/tmp/kafka-logs"` to `"log.dirs=.kafka-logs"`.
-->

Use the following two commands in the Windows CMD (one in each window) to start Kafka:
```bash
C:\kafka\bin\windows\zookeeper-server-start.bat C:\kafka\config\zookeeper.properties
```
```bash
C:\kafka\bin\windows\kafka-server-start.bat C:\kafka\config\server.properties
```

### Run the Application ##
Book and check cargoes with the following command:
(Linux/MacOS)
```shell
curl -X POST -H "Content-Type:application/json" -d '{"bookingAmount":20,"originLocation":"HK","destLocation":"NY","destArrivalDeadline":"2010-08-01"}' http://localhost:8787/cargobooking
```
```shell
curl -X GET -H "Content-Type:application/json" http://localhost:8787/cargobooking/findAllBookingIds
```
(windows)
```shell
curl -X POST -H "Content-Type:application/json" -d "{\"bookingAmount\":20,\"originLocation\":\"HK\",\"destLocation\":\"NY\",\"destArrivalDeadline\":\"2010-08-01\"}" http://localhost:8787/cargobooking
```
```shell
curl -X GET -H "Content-Type:application/json" http://localhost:8787/cargobooking/findAllBookingIds
```

### View Kafka Topics
After running the `bookingms`'s main class, check the Kafka topics with the following command:

(Linux/MacOS)
```shell
./bin/kafka-topics.sh --bootstrap-server=localhost:9092 --list
```
(Windows)
```shell
C:\kafka\bin\windows\kafka-topics.bat --bootstrap-server=localhost:9092 --list
```
You should see two topic names `cargobookings` and `cargoroutings`. You can consume data in the `cargobookings` topic:

(Linux/MacOS)
```shell
./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic cargobookings --from-beginning
```
(Windows)
```shell
c:\kafka\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic cargobookings --from-beginning
```

### Trouble Shooting
If you cannot start Kafka, try to clean up data in the Kafka topics to start over. 
For this purpose, in Linux/MacOS, delete the folders `/tmp/zookeeper`, `/tmp/kafka-logs` 
and `/tmp/kafka-streams` (if any). In Windows, delete the folders `C:\tmp\zookeeper`, 
`C:\tmp\kafka-logs` and `C:\kafka\kafka-streams` (if any).

---
<a id="f1">1.</a>
[https://github.com/practicalddd/practicaldddbook/tree/master/Chapter5](https://github.com/practicalddd/practicaldddbook/tree/master/Chapter5) [↩](#a1)



