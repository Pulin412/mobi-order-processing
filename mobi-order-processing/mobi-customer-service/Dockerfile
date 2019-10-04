FROM openjdk:8
ADD target/mobi-customer-service-0.0.1-SNAPSHOT.jar /mobi-customer-service-0.0.1-SNAPSHOT.jar
EXPOSE 8083
COPY target/mobi-customer-service-0.0.1-SNAPSHOT.jar /home/
VOLUME /var/lib/
WORKDIR /home/
ENTRYPOINT ["java","-jar","/mobi-customer-service-0.0.1-SNAPSHOT.jar"]