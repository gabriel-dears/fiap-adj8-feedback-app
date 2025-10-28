# ============================================
# Stage 1: Build the application
# ============================================
FROM maven:3.9.8-eclipse-temurin-21 AS builder

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
COPY src src

# Build Spring Boot fat jar without tests
RUN mvn clean package -DskipTests

# ============================================
# Stage 2: Runtime image
# ============================================
FROM eclipse-temurin:21-jre-jammy AS runtime

# Create non-root user
RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring

WORKDIR /app

# Copy jar from builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose HTTP and debug ports
EXPOSE 8080 5005

# JVM optimizations
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

# Debug configuration (disabled by default)
ENV DEBUG_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"
ENV ENABLE_DEBUG="true"

# Conditional debug entrypoint
ENTRYPOINT ["sh", "-c", "if [ \"$ENABLE_DEBUG\" = 'true' ]; then java $JAVA_OPTS $DEBUG_OPTS -jar app.jar; else java $JAVA_OPTS -jar app.jar; fi"]
