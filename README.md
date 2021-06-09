# Read Me

## Build and Run RESTAir Project

Download the RESTAir project as a ZIP archive or clone the repository.
Open a comand line tool and navigate to the Dockerfile.

***NOTE: RESTAir folder needs to be in the same location as the Dockerfile.***

Build the Docker Image:
```
docker build -t restairimg .
```

Once Docker Image is built, run the container:
```
docker run -p 8080:8080 -it restairimg
```

Once container starts you should be able to access the REST service:
```
GET http://localhost:8080/api/gates
```

## API Documentation

Project comes with predefined data:
1. Users
    * admin/admin
    * user/user

2. Flights
    * AB-1234
    * BC-5678
    * DE-9012
    * FG-3456
    * AA-1221
    * BB-5656
    * DD-9010
    * FF-5687

3. Gates
    * A100
    * A200
    * B100
    * B200
    * C100
    * C200

REST API does not provide methods to add more users/flights/gates but additional data 
can be added using the `RESTAir/src/main/resources/import.sql` file.
New data must be added before you build the Docker Image.

### Listing all of the flights/gates

To list all of the flights use:
```
Headers:
Authorization: Basic Base64(username:password)

GET http://localhost:8080/api/flights

```

To list all of the gates use:
```
Headers:
Authorization: Basic Base64(username:password)

GET http://localhost:8080/api/gates

```

### Setting availability period for gates

To set a period when a gate is available use:
```
Headers:
Authorization: Basic Base64(admin:admin)
Content-Type: application/json

POST http://localhost:8080/api/gates/[gateNumber]/times

Request Body Example:
{
    "availableFrom": "11:00",
    "availableTo": "17:00"
}
```
***NOTE: Values for times must be in HH:mm format***

List of errors that can occur when you use this method:
1. 403 - Forbidden - Access Denied, Insufficient Rights!
    * This error occurs when user/user tries this method.
2. 400 - Bad Request - Both Time values are required (availableFrom and availableTo)!
    * This error occurs when you omit one or both values
3. 400 - Bad Request - Time values need to be in HH:mm format!
    * This error occurs when you provide values that are not in HH:mm format
4. 404 - Not Found - Gate [gateNumber] was not found!
    * This error occurs when Gate with a given number could not be found
5. 400 - Bad Request - Times must be between 00:00 and 23:59!
    * This error occurs when you provide values that are higher than 23:59
6. 400 - Bad Request - From Time can't be after To Time!
    * This error occurs when From value is higher than To value
7. 400 - Bad Request - Times must be at least 15 minutes apart!
    * This error occurs when values are not at least 15 minutes apart
8. 409 - Conflict - Record was updated by another user, reload and try again.
    * This error occurs if multiple request are hitting the same gate at the almost same time.
9. 500 - Internal Server Error - Oops, something went wrong. Contact System Administrator to resolve the issue.
    * This error occurs in case of any other error

To remove previously specified period use:
```
Headers:
Authorization: Basic Base64(admin:admin)
Content-Type: application/json

POST http://localhost:8080/api/gates/[gateNumber]/times

With Empty Request Body - Nothing in the body of the request not even empty JSON {}
```
List of errors that can occur when you use this method:
1. 403 - Forbidden - Access Denied, Insufficient Rights!
    * This error occurs when user/user tries this method.
2. 404 - Not Found - Gate [gateNumber] was not found!
    * This error occurs when Gate with a given number could not be found
3. 409 - Conflict - Record was updated by another user, reload and try again.
    * This error occurs if multiple request are hitting the same gate at the almost same time
4. 500 - Internal Server Error - Oops, something went wrong. Contact System Administrator to resolve the issue.
    * This error occurs in case of any other error

### Assigning flights to gates

To assign a flight to a first available gate use:
```
Headers:
Authorization: Basic Base64(username:password)
Content-Type: application/json

POST http://localhost:8080/api/gates/assign

Request Body Example:
{
    "flightNumber": "BC-5678"
}
```

List of errors that can occur when you use this method:
1. 400 - Bad Request - Flight Number (flightNumber) is required!
    * This error occurs when you send an empty flightNumber
2. 404 - Not Found - Flight [flightNumber] was not found!
    * This error occurs when Flight with a given number could not be found
3. 409 - Conflict - Flight [flightNumber] already assigned!
    * This error occurs when Flight with a given number is already assigned to a gate
4. 409 - Conflict - All gates are currently unavailable!
    * This error occurs when there are no gates to assign Flight to
5. 409 - Conflict - Record was updated by another user, reload and try again.
    * This error occurs if multiple request are hitting the same gate at the almost same time
6. 500 - Internal Server Error - Oops, something went wrong. Contact System Administrator to resolve the issue.
    * This error occurs in case of any other error

To assign a flight to a specific gate use:
```
Headers:
Authorization: Basic Base64(username:password)
Content-Type: application/json

POST http://localhost:8080/api/gates/[gateNumber]/assign

Request Body Example:
{
    "flightNumber": "BC-5678"
}
```
List of errors that can occur when you use this method:
1. 400 - Bad Request - Flight Number (flightNumber) is required!
    * This error occurs when you send an empty flightNumber
2. 404 - Not Found - Flight [flightNumber] was not found!
    * This error occurs when Flight with a given number could not be found
3. 404 - Not Found - Gate [gateNumber] was not found!
    * This error occurs when Gate with a given number could not be found
4. 409 - Conflict - Flight [flightNumber] already assigned!
    * This error occurs when Flight with a given number is already assigned to a gate
5. 409 - Conflict - Gate [gateNumber] already assigned!
    * This error occurs when Gate with a given number is already assigned a flight
6. 409 - Conflict - Gate [gateNumber] not available at this time!
    * This error occurs when Gate with a given number is not available at this time
7. 409 - Conflict - Record was updated by another user, reload and try again.
    * This error occurs if multiple request are hitting the same gate at the almost same time
8. 500 - Internal Server Error - Oops, something went wrong. Contact System Administrator to resolve the issue.
    * This error occurs in case of any other error

To make a gate available again use:
```
Headers:
Authorization: Basic Base64(username:password)
Content-Type: application/json

POST http://localhost:8080/api/gates/[gateNumber]/assign

Request Body Example:
With Empty Request Body - Nothing in the body of the request not even empty JSON {}
```
List of errors that can occur when you use this method:
1. 404 - Not Found - Gate [gateNumber] was not found!
    * This error occurs when Gate with a given number could not be found
2. 409 - Conflict - Record was updated by another user, reload and try again.
    * This error occurs if multiple request are hitting the same gate at the almost same time
3. 500 - Internal Server Error - Oops, something went wrong. Contact System Administrator to resolve the issue.
    * This error occurs in case of any other error
