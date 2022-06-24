FROM adoptopenjdk/openjdk15:ubi
COPY target/mediScreenDoctorsNote-0.0.1-SNAPSHOT.jar mediScreenDoctorsNote.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "mediScreenDoctorsNote.jar"]