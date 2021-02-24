# Alpine Linux with OpenJDK JRE
FROM openjdk:8-jre-alpine
# copy WAR into image
COPY **/HelloJenkins-jar-with-dependencies.jar /hello-jenkins.jar
# run application with this command line
CMD ["/usr/bin/java", "-jar", "/hello-jenkins.jar"]