
echo "api-utils: Re-bulding and pushing to maven local"
./api-utils/gradlew clean build publishToMavenLocal -p ./api-utils

echo "user-manager: Re-bulding and pushing to maven local"
./user-manager/gradlew clean build publishToMavenLocal -p ./user-manager

echo "micro-services-cloud: Re-bulding"
./microservices-cloud/gradlew clean build -p ./microservices-cloud

echo "parcel-delivery-service: Re-bulding"
./parcel-delivery-service/gradlew clean build -p ./parcel-delivery-service

echo "Applications running up on docker environemnt"
docker-compose up -d