# 🚀 Automation Framework

This is a robust end-to-end test automation framework built using:

- ✅ **Cucumber BDD** – for human-readable scenarios
- ✅ **Selenium WebDriver** – for UI automation
- ✅ **Java** – core programming language
- ✅ **TestNG** – for orchestration, parallel execution & retries
- ✅ **Maven** – build and dependency management
- ✅ **Extent Reports** – for rich HTML test reports
- ✅ **TestRail Integration** – for automated test management reporting

---

## 📂 Project Structure

├── src
│ ├── main
│ │ └── java # Page Objects, Utils, Config
│ └── test
│ ├── java
│ │ └── stepDefinitions
│ └── resources
│ └── features # Cucumber feature files
├── testng.xml # TestNG suite configuration
├── pom.xml # Maven dependencies & plugins
└── README.md


---

## 🔧 Prerequisites

- Amazon Corretto JDK 17 or higher
- Maven 3.6+
- Chrome / Firefox installed
- (Optional) Docker / Selenium Grid for scaling

---

## 🚀 How to Execute Tests

### ➡ Install dependencies
```bash
mvn clean compile
### -  Run all tests
mvn test

### -  Run specific feature file
mvn test -D"cucumber.filter.tags=@smoke"
### -  Run with TestNG suite
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

### - Run tests over the grid
```bash
update config file environment: Remote
mvn test -DgridUrl=https://selenium-grid-qa.ncl.com


### Parallel execution can be configured in `testng.xml` by setting the `parallel` attribute and `thread-count`.
### Example:
```xml  
<suite name="Parallel Suite" parallel="methods" thread-count="5">
    <test name="Test 1">
        <classes>
            <class name="com.nclh.runners.TestRunner"/>
        </classes>
    </test>
    <test name="Test 2">
        <classes>
            <class name="com.nclh.runners.TestRunner"/>
        </classes>
    </test>
</suite>
```

### Extent HTML Reports
``` 
/test-output/ExtentReports/index.html

### -  View reports in `target/extent-reports/index.html`
### -  Integrate with TestRail
### -  Configure TestRail credentials in `src/main/resources/config.properties`
```properties
testrail.url=https://your-testrail-instance.testrail.io
testrail.username=your_username
testrail.password=your_password
testrail.projectId=1
testrail.runId=1
```
