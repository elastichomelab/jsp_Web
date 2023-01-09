FROM openjdk:17-ea-alpine3.14 as build

COPY . /app

WORKDIR /app

COPY ./gradlew /app 
RUN ./gradlew build

FROM tomcat:9.0.65

COPY --from=build /app/build/libs/fundmentals200412345-1.0.war /usr/local/tomcat/webapps/ROOT.war