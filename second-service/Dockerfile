FROM payara/server-full

COPY target/second-service-1.0-SNAPSHOT.war /opt/payara

COPY src/main/resources/keystore.jks /opt/payara/appserver/glassfish/domains/domain1/config

COPY src/main/resources/first-service.crt first-service.crt

USER root
RUN keytool -importcert -keystore /opt/payara/appserver/glassfish/domains/domain1/config/cacerts.p12 -storepass changeit -alias first-service -trustcacerts -file first-service.crt -noprompt
USER payara