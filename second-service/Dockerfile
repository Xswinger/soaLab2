FROM payara/server-full

COPY target/second-service-1.0-SNAPSHOT.war /opt/payara

COPY src/main/resources/first-service-cert.crt /opt/payara

USER root
RUN keytool -import -trustcacerts -alias first-service -file /opt/payara/first-service-cert.crt -keystore $JAVA_HOME/lib/security/cacerts -storepass changeit -noprompt

USER payara