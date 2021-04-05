# 360T Automation Task  


Few points related to the expectations are as follows:  

## 1. TestNG is used to develop an automation framework for this task.  

Using utils.ReadDataFromExcel , Test Data (\data\excel\dto\TelephoneDialPad.xlsx) is provided to TestNG Data Provider.  

In Test (\src\test\java\dto\TelephoneDialPadTest.java), @BeforeMethod method is used to change the test name as per each test dataset. 
Currently, the updated test case name is only available on console view.  

For Reporting purpose, TestNG generates emailable-report (\test-output\emailable-report.html) and index (\test-output\index.html) HTML files.
The in-built reporting was upgraded with the org.testng.Reporter class to print the 'Test Case Name', 'Test Description' and 'Letter Combinations generated' in the Messages section.  


## 2. Test Data is available in Excel (.xlsx) format under (\data\excel\)  

Further performance enhancement of data retriveing can be done as follows:
1) Create another util to convert the Excel data to .csv or .json file.
2) Use this util at the Set Up Stage of Test Execution.
3) Instead of Excel Data Retrieve, Switch test retrival from .csv or .json by changing the properties in the config file. (\variables\config.properties)  


In Excel file (\data\excel\dto\TelephoneDialPad.xlsx), TC_Dummy test dataset is kept to provide the information about the test data structure.
For Prioitize the test case, TC_PRIORITY maps to 0, 1, 2.
High - 0
Medium - 1
Low - 2  

 
For TC_EXECUTION,
Y/Yes - For execution
SKIP - For skipping the dataset read (Helps to focus on one test during the test creation process)  

By creating another util, TC_TAGS can be used to control the execution of test suites.  


## 3. Result is available at (\test-output\emailable-report.html).  


## 4. Steps to execute the test cases for the code (\src\main\java\dto\TelephoneDialPad.java) are as follows:  

git clone https://github.com/pramodbhalerao/TestNGAutomationFramework.git  

Open Eclipse > File > Open Projects from File System.. > Import Source (Directory...) > Select the Project Directory  
(On Windwos) Left click on Project Name > Maven > Update Project..  
Left click on testng > Run as > TestNG Suite  



(Other option)  
git clone https://github.com/pramodbhalerao/TestNGAutomationFramework.git  
cd ~\TestNGAutomationFramework
mvn clean install -DskipTests  
mvn clean test  
#TODO Currently, this option is not generating the test-output.  

