# Etapa de construcción (Build)
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copia solo los archivos esenciales primero para aprovechar la caché
COPY mvnw ./
COPY .mvn .mvn
COPY pom.xml .
RUN ./mvnw dependency:go-offline

# Luego copia el resto del código fuente y compila la aplicación
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Etapa final (Runtime)
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copia el JAR generado en la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Expone el puerto 9090
EXPOSE 9090

# Define las variables de entorno por defecto para la JVM
ENV JAVA_OPTS="-Xms512m -Xmx1024m"

# Comando para ejecutar la aplicación
CMD ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
