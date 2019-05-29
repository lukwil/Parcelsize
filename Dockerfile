FROM openjdk:8-jre-alpine

WORKDIR /etc

ADD ./build/libs /etc
ADD ./java.properties /etc

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/etc/main.Parcelsize-0.1.jar"]
