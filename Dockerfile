# ETAPA 1: Compilacion (Build)
FROM maven:3-eclipse-temurin-23-alpine AS build
WORKDIR /app

# Copiamos el pom.xml y el código fuente
COPY pom.xml .
COPY src ./src

# Ejecutamos el empaquetado (esto genera el .jar dentro de Render)
RUN mvn clean package -DskipTests

# ETAPA 2: Ejecucion (Run)
FROM eclipse-temurin:23-jre-alpine
WORKDIR /app

# Copiamos el .jar desde la etapa de "build" a esta imagen final
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-Duser.timezone=UTC", "-jar", "app.jar"]