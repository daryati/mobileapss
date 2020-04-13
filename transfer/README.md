# BUKOPIN-MOBILE - Transfer Module

***

## A. Running the application

### A.1. How to run the application (default)

1. Compile & install Asyst-Foundation version 3.0.x:  
    `mvn clean install`  
  
2. Go to main module of this project:  
    `cd bukopinmobile-transfer`  
  
3. Compile and install all modules:  
    * Online mode (use it for the first time to download dependencies):  
    `mvn clean install`  
    * _(Optional)_ or use the Offline mode, if you already have all dependencies in your Maven local repository:  
    `mvn -o clean install`  
  
4. Run the application:  
    a) localhost Environment:  
    `java -jar  bukopinmobile-transfer-web/target/bukopinmobile-transfer-web-*.jar`  
  
    b) DEV Environment (development server):  
    `java -jar  -Dspring.profiles.active=dev  bukopinmobile-transfer-web/target/bukopinmobile-transfer-web-1.0.Alpha1-SNAPSHOT.jar`

5. Access the application using this URL (this will generate database structure as well):  
[http://localhost:8081/bukopinmobile-transfer/](http://localhost:8081/bukopinmobile-transfer/)
