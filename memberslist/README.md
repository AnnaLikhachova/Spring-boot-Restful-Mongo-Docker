# Springboot CRUD Rest Api with MongoDB
This is a sample appliction for springboot employee management api with following Integrations:
1.  Springboot Cloud configurations. # mvn spring-boot:run -Dprofile=dev -q
2.  Swagger-UI Integration.
3.  Docker Maven Integration ( with Dockerfile and docker-compose.yml)

Following Methods available:
1.  GET /api/getMembers -  Get all member details
2.  GET /api/getMember/{member_id} - Get specific member detail.
3.  POST /api/createMember with data - Add New Member to MongoDB
4.  PUT /api/updateMember/{member_id} with data- Update existing member
5.  DELETE /api/deleteMember/{member_id} - Remove member details from MongoDB.

# Prequisites
1.  Have Maven downloded and is in Path.
2.  Java 7+
3.  MongoDB Installled.

# How to run this sample
1.  Download and extract this project.
2.  Start MongoDB with 
    ```
    mongod --dbpath data_directory_path
    ```
3.  Change your MongoDb configuration in src/java/resource/application.properties  
4.  Since this project is integration with Config Server, It is fetching configurations from git uri :
5.  Go to CMD and run 
    1. By default if you don't pass any profile, it will take 'dev' profile
    ```
    mvn spring-boot:run
    ```
    2.  Start with specific profile.
    ```
    mvn spring-boot:run -Dprofile=docker
    ```
6.  Go to browser type http://localhost:8080/swagger-ui.html
