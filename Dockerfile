FROM java:8-jdk-alpine
ADD target/telegrambotTest-1.0-SNAPSHOT.jar telegrambotTest.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /telegrambotTest.jar" ]
