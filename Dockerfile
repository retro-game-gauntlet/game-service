FROM eclipse-temurin:17-jdk-jammy as game-service-builder
WORKDIR /opt/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install -DskipTests


FROM eclipse-temurin:17-jre-jammy
WORKDIR /opt/app
EXPOSE 8081
COPY --from=game-service-builder /opt/app/target/*.jar /opt/app/game-service.jar
ENTRYPOINT ["java", "-jar", "/opt/app/game-service.jar" ]