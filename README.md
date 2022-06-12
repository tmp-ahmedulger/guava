## Guava Parcel Delivery Application

Developer: Ahmet Ülger
<br>
Base Github Account: https://github.com/ahmedulger
<br>
Second Github Account: https://github.com/tmp-ahmedulger

<br>

**Application consist of 2 main modules.**

| Name | Git URI | 
|--|--|
| guava | https://github.com/tmp-ahmedulger/guava |
| guava-config-repo | https://github.com/tmp-ahmedulger/guava-config-repo |

<br>
<br>

**Guava Module**
| Name | Description | Port |
|--|--| -- |
| eureka-server | Service registry | 8761
| config-server | Gives configurations depend on various environments | 8888
| apigw | Central request routing application | 8080
| authentcation-server | Central authorization and resource server | 9090
| parcel-delivery-service | This module is created to serve base parcel functions  | 7070
| api-utils | A library contains useful components |
| user-manager | A library that auth-server used to store and retrieve users |



<br>
<br>

**API Documentation**
| API | Url |
|--|--|
| Authentication Server | http://localhost:9090/swagger-ui/ |
| Parcel Delivery Service | http://localhost:7070/swagger-ui/ |


<br>
<br>


**To Run Application**

 1. To run application, you need to install docker and Java 11
 2. You need to change config-repo option on docker-compose.yaml as given below
 3. On base directory ./run.sh file is created to build all dependent modules and run them on docker. Basicly type ./run.sh (give required permissions by typing chmod) on base directory of guava folder that you pull the repository.
 4. After all docker contains run, please wait for 30seconds to health checks complete
 5. You can use postman collection under parcel-delivery-service/additional-files folder


Config Repo Location
    
    environment:
	    SPRING_PROFILES_ACTIVE: default
	    SPRING_CLOUD_CONFIG_SERVER_GIT_URI: file://Users/ahulger/dev-env/repositories/me/guava-config-repo
		eureka.client.serviceUrl.defaultZone: http://host.docker.internal:8761/eureka
	volumes:
		- /Users/ahulger/dev-env/repositories/me/guava-config-repo/:/Users/ahulger/dev-env/repositories/me/guava-config-repo/


<br>
<br>

**Tech Stack**

 1. Spring Boot 
 2. Spring Cloud with APIGW, Eureka, Config Server
 3. Spring JPA
 4. Spring Security
 5. Spring OAuth2 Authorization Server
 6. Spring Web
 7. Map Struct
 8. Lombok
 9. PostgresSQL
 10. JUnit and Mockito
 11. Swagger
 12. Docker


<br>
<br>


**Objects**
| Name | Status |
|--|--|
| Message Brokers | Skipped |
| Unit Tests | Not written all cases |
| Endpoints | Not written all endpoints |

