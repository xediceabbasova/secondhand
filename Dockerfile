#FROM eclipse-temurin:17-jdk-jammy AS build
#COPY gradlew /app/
#WORKDIR /app
#RUN chmod +x gradlew
#RUN ./gradlew build
#
#FROM eclipse-temurin:17-jdk-jammy
#WORKDIR secondhand
#COPY --from=build build/libs/*.jar secondhand.jar
#ENTRYPOINT ["java","-jar","secondhand.jar"]
FROM eclipse-temurin:17-jdk-jammy AS build

WORKDIR /app

# Proje dosyalarını Docker konteynerine kopyala
COPY . .

# Uygulamayı derle
RUN ./gradlew build

FROM eclipse-temurin:17-jdk-jammy

WORKDIR /secondhand

# Derlenmiş uygulamayı Docker konteynerine kopyala
COPY --from=build /app/build/libs/*.jar secondhand.jar

# Uygulamayı başlat
ENTRYPOINT ["java", "-jar", "secondhand.jar"]
