FROM openjdk:17-jdk-alpine as builder
LABEL authors="juliano"
WORKDIR /build

RUN apk add --no-cache maven

COPY pom.xml .
RUN mvn dependency:go-offline

COPY data ./data
COPY src ./src

RUN mvn package -DskipTests && cd target && mv *.jar app.jar

FROM openjdk:17-jdk-alpine as runtime
WORKDIR /app

COPY --from=builder /build/target/app.jar .
COPY --from=builder /build/data ./data

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]