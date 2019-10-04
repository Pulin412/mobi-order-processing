FROM openjdk:8
ADD target/mobi-order-service-0.0.1-SNAPSHOT.jar /mobi-order-service-0.0.1-SNAPSHOT.jar
COPY target/mobi-order-service-0.0.1-SNAPSHOT.jar /home/
VOLUME /var/lib/
WORKDIR /home/
EXPOSE 8082
ENTRYPOINT ["java","-jar","/mobi-order-service-0.0.1-SNAPSHOT.jar"]