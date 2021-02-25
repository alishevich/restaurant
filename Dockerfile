FROM maven:3.6.3-jdk-8-slim AS MAVEN_BUILD

COPY ./ ./

RUN mvn clean package

FROM tomcat:9.0.41-jdk8

COPY --from=MAVEN_BUILD /target/restaurant.war /usr/local/tomcat/webapps/

EXPOSE 8080

CMD ["catalina.sh", "run"]
add