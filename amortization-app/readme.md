

# Project

amortization-app is a simple java project (gradle project)


## Project Structure:

### Java source: 
	source files are located in src/main/java


### Tests: 
	located in src/test/java

### Java doc:
	amortization-app/javadoc

### Package structure:

1) com.amortization.client: contain client program which is "AmortizationClient.java"
		--> this accepts input data from user  and invokes "AmortizationSchedule.generateAmortizationSchedule"

2) com.amortization.vo : 
	contains value object to hold input and output details. used builder pattern to construct value objects.

3) com.amortization.helper: 
	contains utility classes to format output, print output to console, validate and convert data.

4) com.amortization:
		 
	AmortizationCalculator: contain implementation details of how to calculate amortization schedule.
			process input data and generates amortization schedule.
	method details:
	--> calculateAmortizationScheduleDetail : entry method which takes input and call		
				generateNextScheduleReport to get next schedue details
	--> generateNextScheduleReport: generates each month schedule report and calls print method to output data.

	AmortizationScheduler: provides interface to generate amortization schedule, client interacts with this class.


5) test/java: contain test classes for AmortizationCalculator, 	AmortizationOutputFormatter and Value object tests.
	
	AmortizationTestSuite : is the test suite for tests.



## How to build:

	1) gradle clean jar -- generates jar into ${projectDir}/build/lib/mortization-app-1.0.jar


## How to run the app:

	1) from command line: provide the path to generated jar and execute client program
		--> java -cp amortization-app-1.0.jar com.amortization.client.AmortizationClient

	2) from ide (eclipse or intellij..):
		--> right click on AmortizationClient and run as Java program.

### Run tests:

1) from commandline: gradle clean test
2) ide: right clieck on test package and run tests. AmortizationTestSuite : is the test suite for tests.



