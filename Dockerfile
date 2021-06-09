FROM ubuntu:20.04

WORKDIR /work

RUN apt-get update
RUN apt-get install openjdk-8-jdk -y
RUN apt-get install maven -y

ADD RESTAir RESTAir/
RUN cd /work/RESTAir; mvn clean package

ENTRYPOINT java -jar /work/RESTAir/target/RESTAir-0.0.1.jar
