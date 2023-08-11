# Jenkins Project 

# Project 1:Install Jenkins on a digital ocean droplet
##### Create a Server (Droplet) on DigitalOcean
##### Configure Firewall Rules to open port 22 and port 8080 for our new server
##### Installed Docker on DigitalOcean Droplet
        apt update 
        apt  install docker.io
   
##### Started Jenkins Docker container with named volume: 

      docker run -p 8080:8080 -p 50000:50000 -d -v 
      jenkins_home:/var/jenkins_home -v/var/run/docker.sock:/var/run/docker.sock -v $(which docker):/usr/bin/docker 
      jenkins/jenkins:lts

##### Login into Jenkins into and get password to access Jenkins UI
        docker exec -it 82fc4b4c68f8 bash \
        jenkins@82fc4b4c68f8:/$ cat /var/jenkins_home/secrets/initialAdminPassword

##### Installed Build Tools:
Configured Plugin for Maven
Installed npm and node in Jenkins container from CLI:
 - Login as root user:
   
        docker exec -u 0 -it 82fc4b4c68f8 bash

        apt update
        apt install curl
        curl -sL https://deb.nodesource.com/setup_10.x -o nodesource_setup.sh 
        bash nodesource_setup.sh
        apt install nodejs


# Create a simple Freestyle Job

##### Configured Git Repository to checkout the code from:
- Add git credentials and build
  
  <img width="286" alt="image" src="https://github.com/fomar123/Jenkins/assets/90075757/79f5e9e7-b015-4fa4-8ab1-9650fdc745ac">
- Change branch name 
- Add permission so Jenkins can  run scipt
- Print out npm –version  from freestyle-build.sh
- Command executed:

  <img width="320" alt="image" src="https://github.com/fomar123/Jenkins/assets/90075757/fb934ef1-ae27-4031-afc2-ece9cf1060ce">

##### Configured Job to run tests and build Java Application
- Create freestyle job
- Add git repository
- Add maven plugin
- Add test and package command
- Run Build Now
- Check on command line to if Java App was installed:
- Log into Jenkins user:
  
       docker exec -it 82fc4b4c68f8 bash
       ls /var/Jenkins_home/jobs/

<img width="452" alt="image" src="https://github.com/fomar123/Jenkins/assets/90075757/690d01d2-1fce-4e27-9b92-a14284659f62">

<img width="452" alt="image" src="https://github.com/fomar123/Jenkins/assets/90075757/92a17b15-84fb-4fcc-867e-064bcfbe59b0">



# Project 2:Docker in Jenkins

##### Make Docker available in Jenkins container (mount docker runtime inside container as a volume)

##### Create jenkins container with mounted docker:
docker run -p 8080:8080 -p 50000:50000 -d  \ 
-v jenkins_home:/var/jenkins_home \
-v var/run/docker.sock:/var/run/docker.sock \
-v $(which docker):/usr/bin/docker jenkins/jenkins:lts \
##### Fix permissions on docker.sock
##### enter as root and modify docker.sock permission:
docker exec -u 0 -it 0c73a1692b75 bash
chmod 666 /var/run/docker.sock

##### Build ‘java-maven-app’   Docker Image
##### Create a dockerfile :
FROM openjdk:8-jre-alpine

EXPOSE 8080

COPY ./target/java-maven-app-*.jar /usr/app/
WORKDIR /usr/app

CMD java -jar java-maven-app-*.jar

##### Add docker command on java-maven-build on Jenkins:
Docker build . -t java-maven-app:1.0

##### Push image to Dockerhub 
##### Configure Job to push Image to DockerHub
##### Create a private repository on DockerHub
##### Create Credentials for DockerHub in Jenkins UI
##### Tag Docker Image with your DockerHub repository, login and push to repository: 
docker build  -t fomar123/my-rep:jma-1.0 .
docker login -u $USERNAME -p $PASSWORD 
docker push fomar123/my-rep:jma-1.0

# Project 3: Pipeline Job
##### Create a basic Pipeline Job
##### Created a valid Jenkinsfile with required fields on git

# Project 4: Pipeline creating in Jenkins using Parameters

# Project 5: Pipeline created in Jenkins using external groovy script 
##### Create a Jenkinsfile and a groovy script

# Project 6: Pipeline created in Jenkins using input parameters

# Project 7: Create full Pipeline with groovy script
##### Build Jar
##### Build Docker Image
##### Push to private Repository DockerHub
