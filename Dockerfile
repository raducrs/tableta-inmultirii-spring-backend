FROM amazoncorretto/amazoncorretto:11-alpine-jdk

RUN apk --no-cache add curl

RUN mkdir -p /opt/ti/modules
WORKDIR /opt/ti

# USE Layer cache and build cache
COPY application/target/modules/3rdparty/*.jar /opt/ti/modules
COPY application/target/application*.jar /opt/ti/modules/application.jar
COPY application/target/modules/application/security*.jar /opt/ti/modules/
COPY application/target/modules/application/s3cache*.jar /opt/ti/modules/
COPY application/target/modules/application/domain-schools*.jar /opt/ti/modules/
COPY application/target/modules/application/domain-stats*.jar /opt/ti/modules/
COPY application/target/modules/application/repository-schools-dynamodb*.jar /opt/ti/modules/
COPY application/target/modules/application/repository-stats-dynamodb*.jar /opt/ti/modules/
COPY application/target/modules/application/repository-test*.jar /opt/ti/modules/
COPY application/target/modules/application/infrastructure*.jar /opt/ti/modules/
COPY application/target/modules/application/controller*.jar /opt/ti/modules/

CMD java -p /opt/ti/:/opt/ti/modules/ -m ro.tabletainmultirii.api.application --spring.profiles.active=local

HEALTHCHECK --start-period=60s --interval=60s CMD curl --fail -H "API_KEY_AUTH: $(echo $API_ACCESS_TOKEN)" http://localhost:8080/actuator/health/ || exit 1
EXPOSE 8080