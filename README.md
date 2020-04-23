# AllureReportsTestNGSelenium
Demonstrating Implementation of AllureReports using TestNG+ Selenium

Below command should run from root directory of the app.
 
MVN commands for test:
mvn clean test -DsuiteXmlFile=TestNG.xml

MVN commands for generating Report:
mvn io.qameta.allure:allure-maven:serve
mvn io.qameta.allure:allure-maven:report
