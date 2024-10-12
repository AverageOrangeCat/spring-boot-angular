FROM node:20.17.0-alpine3.20 AS frontend

WORKDIR /frontend
COPY ./frontend /frontend

RUN yarn install
RUN yarn run build

FROM maven:3.9-eclipse-temurin-22 AS backend

WORKDIR /backend
COPY ./backend /backend
COPY --from=frontend /frontend/dist/frontend /backend/src/main/resources/webapp

RUN mvn clean
RUN mvn package

CMD java -jar target/Program.jar
