#!/bin/bash
#Shell Script to run and execute the API Automation Test build for SBDB CloseApproach API.
#Prerequist - Docker, Java, Maven, Git setup on local machine.

#Git Clone
echo ">>>> Cloning the git repository from github <<<<"
git clone https://github.com/ssdev1510/SBDB_CloseApproach_API_Test.git

#Getting into the directory
echo ">>>> Getting into the directory <<<<"
cd SBDB_CloseApproach_API_Test

#Building image from Dockerfile
echo ">>>> Creating image from docker file with name sbdb_api_image <<<<"
sudo docker build -t sbdb_api_image .

#Running docker container using the image built in the above step and executing maven commands
#-v flag: sync files form the local system to the container.
echo ">>>> Running docker container and executing maven commands <<<<"
docker run --name sbdb_image_name -it -v `pwd`:/SBDB_CloseApproach_API_Test sbdb_api_image mvn clean test

#Removing docker container
echo ">>>> Removing Docker Container <<<<"
docker rm sbdb_image_name

#Removing docker image
echo ">>>> Removing Docker Image <<<<"
docker rmi sbdb_api_image

#Check Test result report.
echo ">>>> Please check the Test Result Report under /target/test-output/SpartReport/index.html <<<<"
