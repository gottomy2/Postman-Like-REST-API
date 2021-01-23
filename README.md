# Copy of Postman Project  - Testing Unit
### Goal of the Main Project 
Was to achieve cloud located app similar to [Postman](https://www.postman.com/)  

-------------
### Goal for this project
Was to create testing service for each user to create test scenarios and perform multiple requests on API service created by [ Mr Valentyn Kuts](). Notice, that this project consists only of backend in form of Java Spring Application to which frontend is prepared by [Mr Maciej Milewski](https://github.com/MaciejMilewski)  

-------------  
### Useful Informations
#### DataBase
Project is based on the [postgres](https://www.postgresql.org/) database on which all API calls are executed.
Database is an existing localhost database which is blank empty before start of the application.
There are  5 main tables in the database:
- users:

| Name  | Type |
| ------------- | ------------- |
| id | bigint |
| email | character varying(255)  |
| password | character varying(255) |
| username | character varying(255) |

Each user can have multiple request bound to his id 
- scenarios:

| Name  | Type | Description |
| ------------- | ------------- |------------- |
| id | bigint | id of scenario | 
| userId | bigint | id of user to which scenario belongs |
|requestsIds|  character varying(255) | string containing all existing requests ids in form of: "id1/id2/id3/id4/..." | 
| name |  character varying(255) | Name of scenario | 
| description | character varying(255) | Scenario description |

Each scenario can be bound to one **userId** and multiple **requestIds** provided via String

- requests:

| Name  | Type | Description |
| ------------- | ------------- |------------- |
| id | bigint | ID of the request |
| url | character varying(255) | url to call the request on |
| user_id | bigint | Id of the user to which request belongs |

Each request can have multiple parameters bound to his id
- params:

| Name | Type | Description |
| ------------- | ------------- |------------- |
| id | bigint | id of the parameter |
| name | character varying(255) | name of the parameter example: "username" |
| value | character varying(255) | value of the parameter example: "admin" |
| request_id | bigint | id of the request to which param belongs |

- responses:

| Name | Type | Description |
| ------------- | ------------- |------------- |
| id | bigint | id of the response
| request_id | bigint | id of the request to which response is bound
| response | character varying(255) | actuall server response

Response entity is created on existing request call within MainController class

------------- 
#### API calls example based on UserController class
can be found in apitest.http file located [here](https://github.com/gottomy2/PostmanCopy/blob/master/src/main/java/edu/pjatk/postman/apitest.http)


-------------  
### JavaDocs Documentation
Here is full [Documentation](https://gottomy2.github.io/postmanCopyDoc/) of this github project generated thorugh usage of javadocs.




