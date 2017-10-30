FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/showings-*.jar showings.jar
RUN mkdir -p /srv/data/assets/images/
VOLUME /srv/data/assets/images
ENV JAVA_OPTS=""
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /showings.jar