FROM quay.io/wildfly/wildfly:33.0.2.Final-jdk21

RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent

COPY standalone.xml /opt/jboss/wildfly/standalone/configuration/

COPY deploy/second-service.jks /opt/jboss/wildfly/standalone/configuration/
COPY deploy/first-service.crt first-service.crt
COPY deploy/second-service.crt second-service.crt

USER root
RUN keytool -importcert -keystore $JAVA_HOME/lib/security/cacerts -storepass password -alias second-service -trustcacerts -file second-service.crt -noprompt
RUN keytool -importcert -keystore $JAVA_HOME/lib/security/cacerts -storepass password -alias first-service -trustcacerts -file first-service.crt -noprompt
USER jboss

COPY ejb-module/target/ejb-module-1.0-SNAPSHOT.jar /opt/jboss/wildfly/standalone/deployments/ejb-module-1.0-SNAPSHOT.jar
COPY web-module/target/web-module-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/web-module-1.0-SNAPSHOT.war
COPY rest-layer/target/rest-layer-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/rest-layer-1.0-SNAPSHOT.war