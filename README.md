# Jenkins Project 

# Project 1:Install Jenkins on a digital ocean droplet
##### Create a Server (Droplet) on DigitalOcean
##### Configure Firewall Rules to open port 22 and port 8080 for our new server
##### Install Docker on DigitalOcean Droplet
##### Start Jenkins Docker container with named volume: 
docker run -p 8080:8080 -p 50000:50000 -d 
-v jenkins_home:/var/jenkins_home(where the volume is mounted) jenkins/jenkins:lts (name of image and tag)

# Project 2:Docker in Jenkins

##### Make Docker available in Jenkins container (mount docker runtime inside container as a volume)

##### create jenkins container with mounted docker:
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
