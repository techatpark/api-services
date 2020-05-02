#!/bin/sh
cd ui
npm i
cp -r build/* ../src/main/resources/static
cd ..
docker-compose up -d
./mvnw clean package
java -jar target/*.jar
