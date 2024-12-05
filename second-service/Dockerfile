FROM payara/server-full
USER root
USER payara

COPY ./target/second-service-1.0-SNAPSHOT.war D:/payara-6.2024.11/payara6

# COPY /src/main/resources/keystore.p12 /opt/payara/appserver/glassfish/domains/domain1/config/keystore.p12
#CMD ["start-servlet-container"]
#CMD ["start-domain", "--verbose"]
