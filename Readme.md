# Spring Boot cicd with Docker using maven-release-plugin and docker-maven-plugin
* In this project with the help of **maven-release-plugin** we are releasing the current service version and updating the pom with next development version and with the help of **docker-maven-plugin** we are pushing the image name with format *<project.name>:<project:Version>* in dockerhub registry. 
* With the help of gitactions workflow doing the below sequence of steps when Pull request merge to master. Please check [github actions with docker](https://www.youtube.com/watch?v=09lZdSpeHAk&t=487s), with this you will know how to add secrects to github for loggin into docker and github.

* I did not find correct solution with this apporach after spending couple of days in google, so created this article which save many people valuable time.

# Prerequisite
    - Java 8+
    - IDE (Eclips or Intellij)
    - Maven (3+ )
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
# How to run this project
   * Below changes are required to run this project
     * **gitactions-wf.yml** : Change  secrets.MY_GITHUB_TOKEN, secrets.MY_DOCKER_HUB_TOKEN, email
     * **pom.xml** : <dockerfile-maven-plugin>.<username>${project.groupId}</username> , groupId should be dockerhub userid or directly hard code here.
     * Copy the code from this branch to your respective master repository
     * Create a feature branch from master and do some modifications and create a Pullrequest merge to master. Once its merged goto actions tab in github and your build will be triggerd and the docker image will be build and pushed to docker hub. Login to docker hub and check the created tag with version number and one with latest.
	
# Troubleshooting
#### Problem 1: Could not build image: When using ADD with more than one source file, the destination must be a directory and end with a /
   * When we use the below line in  **Dockerfile** file, we will get the above error. 
   > ADD or COPY target/*.jar app.jar
#### Solution:  : It means we have to give the exact generted jar file in target folder. But it will generate <project.name>-<project.verion> which we cannot manually enter.
   * We have to control the jar file name using  **maven-jar-plugin**  in pom.xml, which  will generate jar with project.name and we can directly use ** ADD OR COPY target/<project.name>.jar app.ar** in docerfile
        ````pom.xml
        <build>
		    <plugins>
                <plugin>
                        <groupId>org.apache.maven.plugins</groupId>
                        <artifactId>maven-jar-plugin</artifactId>
                        <version>2.3.2</version>
                        <configuration>
                            <finalName>${project.name}</finalName>
                        </configuration>
                </plugin>
             </plugins>
         <build>    
        ````   
#### Problem 2:  Deployment failed: repository element was not specified in the POM inside distributionManagement element or in -DaltDeploymentRepository=id::layout::url parameter ->
   * This will occur when we do **mvn release:perform**, as it will look for repository location to deploy the released artifact which we didn't configured in pom.xml. We may not have distributed repository or we may not want to deploy the artifact to repository.
   > ADD or COPY target/*.jar app.jar
#### Solution: To skip the deployment of artifact to distributed repo we can use maven-deploy-plugin with *skip* option. This will avoid this issue and it wont check for distributionManagement tag in pom.xml
````pom.xml
 <build>
    <plugins>
	<plugin>
		<artifactId>maven-deploy-plugin</artifactId>
		<version>3.0.0-M1</version>
		<configuration>
		    <skip>true</skip>
		</configuration>
      </plugin>	
     </plugins>
 <build>    
````   
