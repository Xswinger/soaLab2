FROM payara/server-full

WORKDIR /opt/payara/deployments

COPY ejb-module/target/ejb-module-1.0-SNAPSHOT.jar /opt/payara/deployments
COPY web-module/target/web-module-1.0-SNAPSHOT.war /opt/payara/deployments

# COPY web-module/src/main/resources/keystore.jks /opt/payara/appserver/glassfish/domains/domain1/config

# COPY web-module/src/main/resources/first-service.crt first-service.crt

# USER root
# RUN keytool -importcert -keystore /opt/payara/appserver/glassfish/domains/domain1/config/cacerts.p12 -storepass changeit -alias first-service -trustcacerts -file first-service.crt -noprompt
# USER payara
