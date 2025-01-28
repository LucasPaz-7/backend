# Usar uma imagem oficial do Java
FROM eclipse-temurin:21-jdk

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copiar o arquivo JAR para o container
COPY target/*.jar app.jar

# Expor a porta em que a aplicação será executada
EXPOSE 8080

# Comando para iniciar a aplicação
CMD ["java", "-jar", "app.jar"]
