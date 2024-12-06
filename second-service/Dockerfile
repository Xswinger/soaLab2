FROM payara/server-full

# COPY /src/main/resources/second-service.p12 /opt/payara/appserver/glassfish/domains/domain1/config
COPY /src/main/resources/payara-truststore.jks /opt/payara

# USER root
# RUN keytool -importcert -keystore /opt/payara/appserver/glassfish/domains/domain1/config/cacerts.p12 -storepass password -alias first-service -trustcacerts -file first-service.p12 -noprompt
# USER payara

COPY target/second-service-1.0-SNAPSHOT.war /opt/payara

CMD ["asadmin", "start-domain", "--verbose", "domain1"]
