FROM tomcat:10.1-jdk17-corretto

# RUN rm -rf D:\apache-tomcat-10.1.33\apache-tomcat-10.1.33\webapps\*

COPY target/first-service-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps

COPY src/main/resources/keystore/keystore.p12 /usr/local/tomcat/conf/

COPY server.xml /usr/local/tomcat/conf/

# COPY target/first-service-0.0.1-SNAPSHOT.war D:/apache-tomcat-10.1.33/apache-tomcat-10.1.33/webapps

EXPOSE 8080 8443

CMD ["catalina.sh", "run"]