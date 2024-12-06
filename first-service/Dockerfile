FROM tomcat:10.1-jdk17-corretto

COPY target/first-service-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps

COPY src/main/resources/keystore/keystore.p12 /usr/local/tomcat/conf/

COPY server.xml /usr/local/tomcat/conf/

EXPOSE 8080 8443

CMD ["catalina.sh", "run"]