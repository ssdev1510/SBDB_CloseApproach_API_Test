#Docker File> create maven container and copy the files from host to container.

FROM maven:3.8-jdk-8
WORKDIR /SBDB_CloseApproach_API_Test
COPY src /SBDB_CloseApproach_API_Test/src
COPY pom.xml /SBDB_CloseApproach_API_Test