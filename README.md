# Requirements

Gradle and a JRE that supports Java 11

# How to Build
Navigate to the root of the project and run `gradlew build`

# How to Run

At the root of the folder run `Java -jar build/libs/menu-0.0.1-SNAPSHOT.jar`

Alternatively, if you have an IDE you can run the application from the Menu Application class.

## API

To see examples of the api calls in use, navigate to `menu/src/main/resources/Store Contoller.postman_collection.json`

This collection can be imported to Postman and you'll be able to test the application there.

Alternatively, you can execute curl commands in order to access the data.

Sample curl command to access a menu via user `curl -I -v --user user:password localhost:8080/api/store/1`

## Notes

User Credentials: 'user:password' 

Admin Credentials: 'admin:password'

The data is hosted via h2 and will be reset on every restart. If you wish to view the h2 data, 
navigate to `localhost:8080/h2-console`

There are only 4 separate stores provided associated with keys 1-4.