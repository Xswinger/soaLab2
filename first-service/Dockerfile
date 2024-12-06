FROM tomcat:10.1-jdk17-corretto

COPY target/first-service-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps

COPY server.xml /usr/local/tomcat/conf/
COPY src/main/resources/keystore/keystore.jks /usr/local/tomcat/conf/

COPY src/main/resources/keystore/second-service.crt second-service.crt

RUN keytool -importcert -keystore $JAVA_HOME/lib/security/cacerts -storepass changeit -alias second-service -trustcacerts -file second-service.crt -noprompt

EXPOSE 8080 8443

CMD ["catalina.sh", "run"]