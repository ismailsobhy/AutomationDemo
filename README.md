Project with BDD with Cucumber/Selenium/TestNG/Extent reports
=================

The project is a behavior driven development(BDD) a the sample website for simple item purchase

The project consists of the following:
--------------------------------------
* Cucumber Feature file wixsite.feature  has the .feature extension and is stored src/test/java/
* StepDefinations.java under the package org.automation.stepdefinations contains the mapping between the code and each step of the scenario.
* TestRunner.java under org.automation.testrunner. You can define the feature you are using, the StepDefinations.java as the glue used to link to feature and also the path for generating the extent report and path of extent report config.
* Also package pagefactory which has all the components and actions for the automation and called from StepDefinations.java

Installation:
This project is a maven project, and can be both acquired from github and imported as a maven project in eclipse/intellij. The chrome driver should to be stored in src\chromedriver.exe, the version should be the same as the chrome browser on your machine and can be downloaded from.

Screenshots can be found in Screenshots directory in test-output\Screenshots

The extent report will be generated in target\cucumber-reports\report.html
