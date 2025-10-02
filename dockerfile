# ---- Build Stage ----
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

# copy pom.xml and download dependencies first (cache layer)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# copy source and build
COPY src ./src
RUN mvn clean package -DskipTests

# ---- Run Stage ----
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

# expose port
EXPOSE 8080

# run app
ENTRYPOINT ["java","-jar","/app/app.jar"]