# Musical-Jukebox Assignment

## Tools/Framework Used to Develop the Project
- JDK 8
- Maven 3.x
- Spring Boot 2.x
- H2 in-memory database

## Note:
- Create Log file accordingly
### For Linux/Ubuntu/Mac Machine
```
/var/log/musical-jukebox/jukebox.log
```
### For Windows Machine
- Please uncomment LOG_PATH for Windows in logback-dev.properties and comment default LOG_PATH which is for Linux
```
C:\log\musical-jukebox\jukebox.log
```

## Run Musical-Jukebox Project
- Goto musical-jukebox root directory

### Run using mvn
```
mvn spring-boot:run
```

### Run as jar
- Create a jar by running: `mvn clean install`
- Run the generated jar file using `java -jar target/musical-jukebox-1.0.0.jar `

### Swagger URL for API Documentation
-`http://localhost:7777/musical-jukebox/swagger-ui.html#`

### H2 Database URL for DB Access
- `http://localhost:7777/musical-jukebox/h2`
