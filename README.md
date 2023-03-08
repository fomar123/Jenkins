# Jenkins Project 

# Project 1:
##### Install Jenkins on a digital ocean droplet
##### Create a Server (Droplet) on DigitalOcean
##### Configure Firewall Rules to open port 22 and port 8080 for our new server
##### Install Docker on DigitalOcean Droplet
##### Start Jenkins Docker container with named volume: 
docker run -p 8080:8080 -p 50000:50000 -d 
-v jenkins_home:/var/jenkins_home(where the volume is mounted) jenkins/jenkins:lts (name of image and tag)

