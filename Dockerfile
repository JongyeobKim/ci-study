## syntax=docker/dockerfile:experimental
FROM openjdk:8-jdk-alpine as build
WORKDIR /workspace/app

COPY gradlew .
COPY gradle gradle
COPY .gradle .gradle
COPY build.gradle .
COPY src src

#RUN --mount=type=cache,target=/root/.m2 ./gradlew build -DskipTests
RUN ./gradlew build -DskipTests
RUN mkdir -p build/dependency && (cd build/dependency; jar -xf ../libs/*.jar)

FROM openjdk:8-jdk-alpine
VOLUME /tmp
VOLUME /var/run/docker.sock:/var/run/docker.sock
ARG DEPENDENCY=/workspace/app/build/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.jongyeob.kim.cistudy.CiStudyApplication"]