./api-utils/gradlew clean build publishToMavenLocal -p ./api-utils

./user-manager/gradlew clean build publishToMavenLocal -p ./user-manager

./microservices-cloud/gradlew clean build -p ./microservices-cloud

./parcel-delivery-service/gradlew clean build -p ./parcel-delivery-service

./docker-compose up -d