FROM maven:latest as build
WORKDIR /secretaria
COPY pom.xml /secretaria/
RUN mvn dependency:resolve
COPY . /secretaria/
RUN mvn clean install -DskipTests

FROM openjdk:21 as runner
WORKDIR /app
COPY --from=build /secretaria/target/igreja-0.0.1-SNAPSHOT.jar /app/igreja-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "igreja-0.0.1-SNAPSHOT.jar"]
