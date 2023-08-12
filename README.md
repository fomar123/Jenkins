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

# Docker in Jenkins – Pushed Docker Image 

##### Push Image to DockerHub Repository:

Made Docker available in Jenkins container (mount docker runtime inside container as a volume):
- create jenkins container with mounted docker:
  
        docker run -p 8080:8080 -p 50000:50000 -d -v jenkins_home:/var/jenkins_home -v/var/run/docker.sock:/var/run/docker.sock -v $(which 
        docker):/usr/bin/docker 
        jenkins/jenkins:lts	



Fixed permissions on docker.sock:
- Enter as root user:
  
       docker exec -u 0 -it b00e2f4f4df3  bash
- Change permission:
  
       chmod 666 /var/run/docker.sock
Pull redis image:

        docker pull redis 
<img width="399" alt="image" src="https://github.com/fomar123/Jenkins/assets/90075757/7c5bfe2e-03d6-4a03-bba2-f2e99547617a">

 Build Docker Image from Jar File:
 - Add docker command in job configuration 
<img width="452" alt="image" src="https://github.com/fomar123/Jenkins/assets/90075757/e7fda5a0-7f6c-49fa-8474-823b302bce1a">

 - Check docker to see image has been pulled:
 <img width="452" alt="image" src="https://github.com/fomar123/Jenkins/assets/90075757/ba86527d-4504-4b6d-b83c-9cb9ce2f5ae0">

 Push Image to DockerHub:
 - Create credentials for Docker
   
   <img width="452" alt="image" src="https://github.com/fomar123/Jenkins/assets/90075757/229e6547-2ed7-4a8c-a33d-33c1944c5af3">

 - Add plugin to login into docker hub in binding section in Building Enviroment Section, add env variable
 - Image pushed to DockerHub:

<img width="452" alt="image" src="https://github.com/fomar123/Jenkins/assets/90075757/6be45cf0-7a9c-4d83-bf62-2e1d96ebba1d">

# Create a basic Pipeline Job:
##### Configured Git Repository:
- Add git branch 
<img width="452" alt="image" src="https://github.com/fomar123/Jenkins/assets/90075757/e2b08117-12d8-423f-aa60-0542f0035879">

##### Created a valid Jenkinsfile with required fields:
- Execute Build Now
<img width="285" alt="image" src="https://github.com/fomar123/Jenkins/assets/90075757/048e8879-1294-44a5-8d01-4440857ef1a9">


# Jenkinsfile Syntax

##### Used Post attribute❏
##### Defined a Condition:
- Define boolean expression and check which branch your on
##### Used an environment variable:
- To check environmental variable go to path /env-vars.html
- Define server credentials to connect to the  development server to build artifact
##### Used Tools Attribute:
- Provides build tools for your project 
- Jenkins support build tools like gradle, maven and jdk
##### Used a Parameter:
- Used Parameters to pass data in Pipeline job 
- Parameter type use: Boolean and String 
- Execute Parameter:
<img width="452" alt="image" src="https://github.com/fomar123/Jenkins/assets/90075757/8d5aa01a-b1df-4a8c-81c5-452a2c4267b3">

##### Used an external Groovy Script	
- Groovy is a very powerful language which offers the ability to do practically anything Java can do including: Create sub-processes and execute arbitrary commands on the Jenkins controller and agents.
- Write groovy script and include it in the Jenkinsfile 
<img width="452" alt="image" src="https://github.com/fomar123/Jenkins/assets/90075757/5213d13c-c7c7-4909-bfbb-b1d9a35592d3">

##### Used an Input Parameter
- Paramtise build to make it more reusable 
- Allows the user to interact and control the flow of the build.
- Use input in your script and assign it to a variable
- You can use string , choice or BooleanParam
- You can choose which environment you want to build, e.g dev, staging or prod
<img width="413" alt="image" src="https://github.com/fomar123/Jenkins/assets/90075757/7e98e9b5-090e-49ac-a6f5-8e000d3b5858">

- Build will be paused until user inputs a value

 <img width="452" alt="image" src="https://github.com/fomar123/Jenkins/assets/90075757/538731bc-9497-448e-9b75-500a73486ff4">


# Create full Pipeline
##### Define maven installation in Jenkinsfile
##### Build Jar
##### Build Docker Image
##### Push to private Repository DockerHub
##### Use groovy script to extract commands
##### Execute build now:
<img width="452" alt="image" src="https://github.com/fomar123/Jenkins/assets/90075757/d6a450b0-081a-4c80-afc3-9b617963b9fb">

##### Image pushed to Dockerhub:
<img width="365" alt="image" src="https://github.com/fomar123/Jenkins/assets/90075757/8155577a-07b3-4325-b629-164475474b5f">


#  Automatically Trigger Jenkins Jobs - Webhooks
##### Installed gitlab plugin 
##### Created an access token for you gitlab API
##### Intergrate Jenkins in Gitlab: adds the URL of Jenkins and project name 
##### Test the configuration to see if the connect between gitlab and Jenkins works:
<img width="452" alt="image" src="https://github.com/fomar123/Jenkins/assets/90075757/619b6c9c-5f6a-4b8f-bb50-124ed7803bc1">

<img width="452" alt="image" src="https://github.com/fomar123/Jenkins/assets/90075757/d627353f-f158-496b-9424-34a34a83b9cf">

##### Configure Automatic Triggering of Jenkins Jobs for Multi-Branch Pipeline:
• Install Multi-branch webhook trigger plugin
• Add Scan by webhook 
• Open Webhooks in gitab: It'll send Jenkins a notification specific URL using the token specified. When Jenkins receive the token It'll check the token and trigger the multi-branch pipeline which has scan by webhook configured

• Copy token in webhook URL

<img width="452" alt="image" src="https://github.com/fomar123/Jenkins/assets/90075757/65ad75bb-74b4-4bef-9333-11fce5c665f3">

• Webhook will automatically send events to your branch

<img width="452" alt="image" src="https://github.com/fomar123/Jenkins/assets/90075757/019f0f54-44bf-451b-a687-569b8cf807f1">



