# **Assignment- API Test automation for SBDB Close-Approach Data API.**

**Technical Stack:**

- API Automation Tool: REST-Assured (Java Library)
- Test automation framework: Cucumber
- Reporting: Spark & Cucumber reporting.
- Build tool: Maven
- Java 1.8+
- Docker
## Cucumber-API-Automation-Framework using Rest Assured Library.
  **This framework contains:**
- Runner class > Defines which feature/step Definitions/Reports Plugins to include in test.
- Rest-Assured Utils class > Defines different methods of Rest-Assured library.
- Step Definitions class > Defines implementation of test scenarios defined in feature file.
- Feature file > Defines the Test scenarios in gherkin language.
- 7 Scenarios: “SBDB\_GET\_API\_Validations.feature”
  - Verify the status code and Response time of DATA API response.
  - Verify the Signature & Source of DATA API response.
  - Verify the number of asteroids approached to earth between Minimum date & Maximum date.
  - Verify when query is too restrictive > Count should be come as 0.
  - Verify the orbit Id of asteroid primary designation 433 in year 1900.
  - Negative scenario: Verify the output when user pass wrong value in data field.

**Note:**

- Assignment is having only GET method using query parameters> But in the framework I tried to handle Get, Post, Put, Delete method as well with both query and path parameters. So, in future if there is need of extension– this framework can easily handle different types of requests.
- In addition to it, I also included one method for Authentication: which get access token and can be used further in sending requests. 
- Reports: Spark reporting which generate both html and pdf reports.
- If you want to see failed scenario in result report: Just uncomment and run below mentioned scenario in feature file.
  - Intentionally Failing the scenario to view failed test result in report.
- Both Failed/Passed scenario Test result report screenshot you can find below.

## **Steps to run at your system/container:**
**Prerequisite:** Maven, Java, Git installation.

- Run "git clone https://github.com/ssdev1510/SBDB_CloseApproach_API_Test.git" to Clone the repository.
- Run "cd SBDB_CloseApproach_API_Test" to Go inside directory.
- Run "mvn clean"
- Run "mvn test"
- Check the test result report as mentioned in below section.

## **Steps to run at Docker Container:**
**Prerequisite:** Docker, Maven, Java, Git installation.
- Run "git clone https://github.com/ssdev1510/SBDB_CloseApproach_API_Test.git" to Clone the repository.
- Run "cd SBDB_CloseApproach_API_Test" to Go inside directory.
- Run "sudo docker build -t sbdb_api_image ." to Build docker image from Dockerfile.
- Run "docker run --name sbdb_image_name -it -v `` `pwd` ``:/SBDB_CloseApproach_API_Test sbdb_api_image mvn clean test" to Run docker container using the image built in the above step and to execute maven commands. -v flag: sync files form the local system to the container.
- Run "docker rm sbdb_image_name" to Remove docker container.
- Run "docker rmi sbdb_api_image" to  Remove docker image.
- Check the test result report as mentioned in below section.

**Note:** Above mentioned steps can also be execute in one go through shell script.

## **Reports:** 
  **This will generate two visual reports:**
- HTML Reporting (Project folder - "target\test-output\SparkReport"), file - "index.html"
- PDF Reporting (Project folder - "target\test-output-pdf\PDFReport"), file - "ExtentPdf.pdf"

**Logs:** Logs can be found under (folder - "\logs" )folder, file - ".log".
## **Directory Structure:**
<kbd>![Alt text](/readmeImages/directory.png?raw=true)</kbd>

## **Test output from Terminal:**

<kbd>![Alt text](/readmeImages/buildSuccess.png?raw=true)</kbd>

## **Reports**:

**1) HTML Report**

-Graph view

![Alt text](/readmeImages/HtmlView.png?raw=true)

-View having both Passed/Failed Scenarios.

![Alt text](/readmeImages/testScenario.png?raw=true)

**2) PDF Report:**

<kbd>![Alt text](/readmeImages/pdfReport1.png?raw=true)</kbd>

<kbd>![Alt text](/readmeImages/pdfReport2.png?raw=true)</kbd>
