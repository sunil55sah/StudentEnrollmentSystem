# Stage 1: Build the WAR using Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the WAR on Tomcat
FROM tomcat:9.0
COPY --from=build /app/target/student-course-enrollment-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
