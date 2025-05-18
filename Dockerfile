# Use an official Tomcat base image
FROM tomcat:9.0

# Remove the default ROOT app
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copy your built WAR file into the ROOT webapp folder
COPY target/student-course-enrollment-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Expose default Tomcat port
EXPOSE 8080
