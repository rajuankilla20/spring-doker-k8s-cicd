# Spring Boot cicd with Docker using maven-release-plugin and docker-maven-plugin
* In this project with the help of maven-release-plugin we are releasing the current version and updating the pom with next development version and with the release version with the help of docker-maven-plugin we are pushing the image with name <project.name>:<project:Version> in docker hub. I did not find correct solution with this apporach, so creating this which will useful for many people.

# Prerequisite
    - Java 8+
    - Maven
    - Spring boot sample app
    - Dockerfile knowledge
    - Git hub account 
    - Docker hub account 
    - Git actions ( basic idea)
# Pros 
    - With this approach we can identify the Docker image with the service release version as we are using the same version for service and docker image.
    - We easily identify which version we are using by just looking at the version.
    - Easy to revert when ever we want using the service version.
# Cons
    - Maven release plugin will compile, build, test cases before and after releasing the version, it is time taking.
    - It will not work if any commited code is present in the project.
    - It wont allow if any snapshot depenency in pom.
    - If something goes wrong it updates the scm tag and release version withtout snapshot, which we have to take care.
    - It's having more valid pros than cons.
# Important problem solutions 
#### Could not build image: When using ADD with more than one source file, the destination must be a directory and end with a 
    - When we use this line in  Dockerfile  ADD or COPY target/*.jar app.jar
    
    
