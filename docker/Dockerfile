FROM openjdk:8-jre-alpine

WORKDIR /etc

ADD ../build/libs /etc

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/etc/Parcelsize-0.1.jar"]
