# Usamos una imagen de Java liviana (Eclipse Temurin es excelente)
FROM eclipse-temurin:23-jre-alpine

# Definimos el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el archivo .jar que generamos en el Paso 1 al contenedor
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto 8080 que es donde corre Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación
# Pasamos el parámetro de TimeZone por las dudas para evitar el error anterior
ENTRYPOINT ["java", "-Duser.timezone=UTC", "-jar", "app.jar"]