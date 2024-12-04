FROM tomcat:10.1-jdk17-corretto

# RUN rm -rf D:\apache-tomcat-10.1.33\apache-tomcat-10.1.33\webapps\*

COPY target/first-service-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps
# COPY target/first-service-0.0.1-SNAPSHOT.war D:/apache-tomcat-10.1.33/apache-tomcat-10.1.33/webapps

EXPOSE 8080

CMD ["catalina.sh", "run"]