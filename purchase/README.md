# BUKOPIN-MOBILE - Purchase Module

***

## A. Running the application

### A.1. How to run the application (default)

1. Compile & install Asyst-Foundation version 3.0.x:  
    `mvn clean install`  
  
2. Go to main module of this project:  
    `cd bukopinmobile-purchase`  
  
3. Compile and install all modules:  
    * Online mode (use it for the first time to download dependencies):  
    `mvn clean install`  
    * _(Optional)_ or use the Offline mode, if you already have all dependencies in your Maven local repository:  
    `mvn -o clean install`  
  
4. Run the application:  
    a) localhost Environment:  
    `java -jar  bukopinmobile-purchase-web/target/bukopinmobile-purchase-web-1.0.Alpha1-SNAPSHOT.jar`  
  
    b) DEV Environment (development server):  
    `java -jar  -Dspring.profiles.active=dev  bukopinmobile-purchase-web/target/bukopinmobile-purchase-web-1.0.Alpha1-SNAPSHOT.jar`

5. Access the application using this URL (this will generate database structure as well):  
[http://localhost:8080/wfmopt/](http://localhost:8080/wfmopt/)

### A.2. How to run the application (using Docker)

Follow these steps to run the application using Docker (requires Docker and MySQL already **installed** and **running** in localhost machine):  

1. Compile & install Asyst-Foundation version 3.0.x:  
    `mvn clean install`  
  
2. Go to main module of this project:  
    `cd bukopinmobile-purchase`  
  
3. Compile and install all modules:  
    * Online mode (use it for the first time to download dependencies):  
    `mvn clean install`  
    * _(Optional)_ or use the Offline mode, if you already have all dependencies in your Maven local repository:  
    `mvn -o clean install`  
  
4. Build the Docker image:  
    * Format:  
    `docker image build --tag helium.asyst.co.id:8448/<PROJECT_CODE>/<TAG_NAME><:VERSION> .`  

    * Build the Docker image (using _latest_ tag version):  
    `cd bukopinmobile-purchase-web`  
    `docker image build --tag helium.asyst.co.id:8448/wfm/bukopinmobile-user-web .`  
  
    * Or build the Docker image (using specific tag version, recommended):  
    `cd bukopinmobile-purchase-web`  
    `docker image build --tag helium.asyst.co.id:8448/wfm/bukopinmobile-user-web:1.0.Alpha1-SNAPSHOT .`  
    , or  
    `docker image build --tag 172.25.230.182:8448/wfm/bukopinmobile-user-web:1.0.Alpha1-SNAPSHOT .`  
  
    _Be sure to include period (.) at the end of the command._  
  
    View Docker image list:  
    `docker image list`  
  
5. Start a new container from the image created in the previous step:  
    `docker container run  -e "SPRING_PROFILES_ACTIVE=docker"  -e "APP_ENCRYPTION_PASSWORD=foundation*1jasypt"  --publish 8080:8080  --name bukopinmobile-user-web   helium.asyst.co.id:8448/bukopinmobile-user-web:1.0.Alpha1-SNAPSHOT`  
  
    Parameters description:
    * `SPRING_PROFILES_ACTIVE=docker` Use **`docker`** profile to activate special application configuration for Docker environment.
    * `APP_ENCRYPTION_PASSWORD` Asyst-Foundation encryption password.
    * `--publish 8080:8080` Publish port 8080 inside the container onto port 8080 on the host.
    * `--name bukopinmobile-user-web` Define container name.
  
6. Access the application using this URL (this will generate database structure as well):  
[http://localhost:8080/wfmopt/](http://localhost:8080/wfmopt/)

7. (Optional) View list of running Docker containers:
    `docker container list`

    ```text
    CONTAINER ID        IMAGE                                                           COMMAND                CREATED              STATUS              PORTS                            NAMES
    606995fedac1        helium.asyst.co.id:8448/bukopinmobile-user-web:1.0.Alpha1-SNAPSHOT   "java -jar /app.jar"   About a minute ago   Up About a minute   80/tcp, 0.0.0.0:8080->8080/tcp   bukopinmobile-user-web
    ```

8. (Optional) Open a shell in to a container:  
    `docker container exec -it ${CONTAINER_ID} /bin/sh`  
    `docker container exec -it 606995fedac1 /bin/sh`

9. (Once you’ve accessed your web application) To shutdown and remove the application, run this command:  
    `docker container rm --force bukopinmobile-user-web`  
  
### A.3. How to run the application (using Maven Dockerfile plugin)

Follow these steps to run the application using **Maven Dockerfile plugin** (requires Docker and MySQL already **installed** and **running** in localhost machine):  

1. Compile & install Asyst-Foundation version 3.0.x:  
    `mvn clean install`  
  
2. Go to main module of this project:  
    `cd bukopinmobile-purchase`  
  
3. Compile and install all modules:  
    * Online mode (use it for the first time to download dependencies):  
    `mvn clean install`  
    * _(Optional)_ or use the Offline mode, if you already have all dependencies in your Maven local repository:  
    `mvn -o clean install`  
  
4. Build the Docker image:  
    * Build the Docker image (using pom configured tag version):  
    `cd bukopinmobile-purchase-web`  
    `mvn -Dmaven.test.skip=true  clean package dockerfile:build`  
  
    View Docker image list to review your new Docker image:  
    `docker image list`  
  
5. Start a new container from the image created in the previous step:  
    `docker container run  -e "SPRING_PROFILES_ACTIVE=docker"  -e "APP_ENCRYPTION_PASSWORD=foundation*1jasypt"  --publish 8080:8080  --name bukopinmobile-user-web   172.25.230.182:8448/wfm/bukopinmobile-user-web:1.0.Alpha1-SNAPSHOT`  
  
    Parameters description:
    * `SPRING_PROFILES_ACTIVE=docker` Use **`docker`** profile to activate special application configuration for Docker environment. This will use the `application-docker.yaml` configuration file.
    * `APP_ENCRYPTION_PASSWORD` Asyst-Foundation encryption password.
    * `--publish 8080:8080` Publish port 8080 inside the container onto port 8080 on the host.
    * `--name bukopinmobile-user-web` Define container name.
  
6. Access the application using this URL (this will generate database structure as well):  
[http://localhost:8080/bukopinmobile-user-web/](http://localhost:8080/bukopinmobile-user-web/)

7. (Once you’ve accessed your web application) To shutdown and remove the application, run this command:  
    `docker container rm --force bukopinmobile-purchase-web`  
  
8. (Optional) Push Docker image:  
    `mvn dockerfile:push -Ddockerfile.username=... -Ddockerfile.password=...`

***

## B. Pushing and Pulling Docker Images

### B.1. Login to Asyst-Docker-Repository

Follow these steps to login to Asyst-Docker-Repository (requires Docker already **installed** and **running** in localhost machine):  
  
1. (Optional) Register internal Asyst-Docker-Repository into your Docker Daemon Configuration, put: `172.25.230.182:8448`. Apply & Restart.

2. Login to Asyst-Docker-Repository using your username & password (use console):  
    `docker login -u stephen -p "<PASSWORD>" helium.asyst.co.id:8448`  
    `docker login -u stephen -p "<PASSWORD>" 172.25.230.182:8448`  
  
### B.2. Pushing Docker images

Follow these steps to push Docker images to Asyst-Docker-Repository:  

1. Login to Asyst-Docker-Repository.  
  
2. Re-compile, clean, and install all modules (make sure you do this before building a clean Docker image):  
    `mvn -o -Dmaven.test.skip=true  clean install`  

3. Build and Tag the Docker image:  
    * Build the Docker image (using specific tag version):  
    `cd bukopinmobile-purchase-web`  
    `docker image build --tag helium.asyst.co.id:8448/wfm/bukopinmobile-user-web:1.0.Alpha1-SNAPSHOT .`  
    Or:  
    `docker image build --tag 172.25.230.182:8448/wfm/bukopinmobile-user-web:1.0.Alpha1-SNAPSHOT .`
  
    _Be sure to include period (.) at the end of the command._  
  
    View Docker image list:  
    `docker image list`  
  
4. Push the Docker image:  
    `docker push 172.25.230.182:8448/wfm/bukopinmobile-user-web:1.0.Alpha1-SNAPSHOT`  
    Or using domain:  
    `docker push helium.asyst.co.id:8448/wfm/bukopinmobile-user-web:1.0.Alpha1-SNAPSHOT`  

### B.3. Pulling Docker images

Follow these steps to pull Docker images from Asyst-Docker-Repository:  

1. Login to Asyst-Docker-Repository.  
  
2. Pull the Docker image:  
    `docker pull helium.asyst.co.id:8448/wfm/bukopinmobile-user-web:1.0.Alpha1-SNAPSHOT`  

    `docker pull 172.25.230.182:8448/wfm/bukopinmobile-user-web:1.0.Alpha1-SNAPSHOT`  

3. Test by starting a new container from the image you have pulled in the previous step:  
    `docker container run  -e "SPRING_PROFILES_ACTIVE=docker"  --publish 8080:8080  --name bukopinmobile-user-web   172.25.230.182:8448/wfm/bukopinmobile-user-web:1.0.Alpha1-SNAPSHOT`  

### B.4. (Optional) Removing specific Docker image

1. Shutdown and remove any running application(s), using this command:  
    `docker container rm --force bukopinmobile-user-web`  

2. Use the `docker images` command with the `-a` flag to locate the ID of the image you want to remove. e.g.  
`172.25.230.182:8448/wfm/bukopinmobile-user-web                 1.0.Alpha1-SNAPSHOT   ae93c31777f3        12 minutes ago      129MB`

3. Remove image:  
`docker rmi ae93c31777f3`

***

## C. Asyst-Docker-Repository (Nexus)

### Accessing the Asyst-Docker-Repository (Nexus)

1. Go to URL:  
`https://helium.asyst.co.id:8441/`

2. Login using provided credentials (or use your own credentials):

    * Username (read only): `public`.
    * Pswd: `DEVpublic*x9`.

***

## D. Database Configuration

### MySQL Notes
  
#### How to load Initial data

1. Go to bukopinmobile-user-core module:  
    `cd bukopinmobile-user-core/`  
2. Run Liquibase Update:  
    `mvn liquibase:update`  
  
#### How to reset Initial data in local database

1. Remove all records from DATABASECHANGES table:  
    `delete from wfm_opt_local.DATABASECHANGELOG;`  
2. Remove all existing records from CSV Data Holder tables:  
    `delete from  wfm_opt_local.aircraft_type_group;`  
3. Reset Liquibase CheckSums:  
    `mvn liquibase:clearCheckSums`  
4. Re-run Liquibase Update:  
    `mvn liquibase:update`  
  
**Notes:**  
When _"Error setting up or running Liquibase: Validation Failed"_ error occurred in DEV Environment, run this command to clear CheckSums:  
    `mvn liquibase:clearCheckSums`

***

## Tips

Tips to correctly display this Markdown file for various IDEs:

* **Visual Studio Code** (recommended): Use the default Markdown Preview feature.
* **Eclipse**: Install [Markdown Text Editor](https://marketplace.eclipse.org/content/markdown-text-editor) from Eclipse Marketplace.
* **JetBrains IntelliJ IDEA**: Install [Markdown](https://www.jetbrains.com/help/idea/markdown.html) plugin.

### How to access Swagger:
Please visit: http://localhost:8080/bukopinmobile-user/swagger-ui.html
***

***

```text
================================
 MYSQL-DEV (localhost)
================================
compile with : mvn clean install -Dmaven.test.skip=true -Dproguard.skip=true -PDEV-MYSQL

================================
 DEV-POSTGRE (localhost)
================================
compile with : mvn clean install -Dmaven.test.skip=true -Dproguard.skip=true -PDEV-POSTGRE
```
