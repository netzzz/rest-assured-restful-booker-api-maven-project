## Restful-Booker API Testing Project written using REST Assured and POM Design Pattern
  
* [Test Cases](#test-cases)  
* [Test Suites](#test-suites)  
* [Instructions to Execute the Test Suite](#instructions-to-execute-the-test-suite)  
* [Test Log and Test Report](#test-log-and-test-report)  
* [API Documentation](#api-documentation)  
  
## Test Cases  
  
Each Test Case verifies different API functionalities, ensuring the expected responses and statuses are returned.  
  
### 1. End to End Validation
  
Method: endToEndValidation()  
   
Test verifies the complete CRUD (Create, Read, Update, Delete) lifecycle of a booking entity using the Restful Booker API.  
It ensures each operation returns the expected HTTP status code and correctly reflects changes in data.  

<h3>Test Flow</h3>  
  
1. Authenticate
  
* Sends a login request using admin credentials.  
* Validates that the response status is HTTP 200 OK.  
* Extracts and stores the authentication token for authorized operations.  
  
2. Create Booking  
  
* Constructs a booking with specific user data (Johnsy Johnson).  
* Sends a POST request to create the booking.  
* Verifies that the creation response is HTTP 200 OK.  
* Extracts and stores the newly created booking ID.  
  
3. Validate Booking Creation  

* Retrieves the booking using the stored booking ID.  
* Validates response status is HTTP 200 OK.  
* Asserts that the first name and last name match the original values.  
  
4. Update Booking (PUT)  
  
* Updates the full booking record with a new name (Nick Milsen) while keeping other fields unchanged.  
* Sends a PUT request with the updated data and authentication token.
* Confirms the response status is HTTP 200 OK.  
* Retrieves the booking again and validates the name update.  
  
5. Partial Update (PATCH)  
  
* Partially updates the booking by only modifying the name to Mike Perez.  
* Sends a PATCH request with the new data and authentication token.  
* Confirms the response status is HTTP 200 OK.  
* Retrieves the booking and verifies the new name has been applied.  
  
6. Delete Booking  
  
* Sends a DELETE request with the booking ID and token.  
* Verifies that deletion is acknowledged with HTTP 201 Created (as per API behavior).  
* Validate Deletion  
* Attempts to fetch the booking again.  
* Confirms that the booking no longer exists with HTTP 404 Not Found.  

### Test Suites   
  
1. RestfulBookerRegressionSuite
  
The TestNG suite RestfulBookerRegressionSuite is designed to execute all Test Cases for the Restful Booker API.
  
## Instructions to Execute the Test Suite:  
  
1) Clone the Repository  
2) Open the Project in an IDE  
3) Update the Maven Project  
4) Install TestNG (if not already installed)
5) Add User Environment Variable 'SECRET_KEY' with value '1223315678594234'
6) Execute the Test Suite via the Command Line Terminal:   
```
mvn -Dlog4j.configurationFile=src\test\resources\loggerconfig\log4j2.xml -Dsurefire.suiteXmlFiles=src\test\resources\suites\RestfulBookerRegressionSuite.xml test
```  
  
## Test Log and Test Report  
  
* After each run, Test Log is stored in a timestamped .log file within the 'test_logs' directory,  
  while the Test Report is stored in a timestamped directory within the 'test_reports' directory.  
* Each report includes an 'html' directory where the simple, colour-coded view of the test results is stored.  
  
## API Documentation
[Restful Booker API Documentation](https://restful-booker.herokuapp.com/apidoc/index.html)  
