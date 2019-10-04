FROM openjdk:8
ADD target/mobi-inventory-service-0.0.1-SNAPSHOT.jar /mobi-inventory-service-0.0.1-SNAPSHOT.jar
COPY target/mobi-inventory-service-0.0.1-SNAPSHOT.jar /home/
VOLUME /var/lib/
WORKDIR /home/
EXPOSE 8081
ENTRYPOINT ["java","-jar","/mobi-inventory-service-0.0.1-SNAPSHOT.jar"]