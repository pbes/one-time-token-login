# One Time Token login w/ Spring Security

This is a simple example of Spring Security 6.4's new feature: one time token login.4

## Pre-requisites
* Java 17
* Maven 3
* Docker 20+<sup>1</sup>

<sup>1</sup> To run Mailhog, started by the `compose.yaml` file during application run

## Run the application
```bash
mvn spring-boot:run
```

## How does it work?

1. User navigates to home page
2. If user is not logged in, they are redirected to the login page
3. User can log in by:
    * Entering their credentials (username & password)
    * Or: enterint their user name and requesting a one time token
4. If the user requests a one time token, they will receive an email with a link to log in
5. User can check the sent mail on http://localhost:8025 or in application console
6. User clicks on the link in the email to log in

## URLs

|URL|Description|
|---|---|
|http://localhost:8080|Home page|
|http://localhost:8080/login|Login page|
|http://localhost:8080/logout|Logout page (open manually to force logout)|
|http://localhost:8025|Mailhog to check emails|

## Credentials
|User|Password|
|---|---|
|user1|pw|
|user2|pw|