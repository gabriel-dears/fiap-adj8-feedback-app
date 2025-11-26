# ============================================
# Stage 1: Build the application
# ============================================
FROM maven:3.9.8-eclipse-temurin-21 AS builder

WORKDIR /app

# Copia pom.xml e baixa dependências offline
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia código-fonte
COPY src src

# Build fat jar do Spring Boot sem testes
RUN mvn clean package -DskipTests

# ============================================
# Stage 2: Runtime image
# ============================================
FROM eclipse-temurin:21-jre-jammy AS runtime

WORKDIR /app

# Copia jar do stage de build
COPY --from=builder /app/target/*.jar app.jar

# Baixar Cloud SQL Proxy antes de trocar para usuário não-root
RUN curl -fSL https://dl.google.com/cloudsql/cloud_sql_proxy.linux.amd64 -o cloud_sql_proxy \
    && chmod +x cloud_sql_proxy

# Criar usuário não-root
RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring

# Porta da aplicação e debug
EXPOSE 5005

# JVM otimizations
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

# Debug opcional
ENV DEBUG_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"
ENV ENABLE_DEBUG="true"

# Entrypoint condicional
ENTRYPOINT ["sh", "-c", "if [ \"$ENABLE_DEBUG\" = 'true' ]; then java $JAVA_OPTS $DEBUG_OPTS -jar app.jar; else java $JAVA_OPTS -jar app.jar; fi"]
