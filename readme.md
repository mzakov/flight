# Flight
Flight booking web application written as a final assessment for the FastTrack'D program.
Randomly generates flights at a given interval, allows registered users to search for
direct and connecting flights between cities and book them. Flights include information about
layovers, flight time and total duration of the trip and can be shown on a map.
The registration and login system utilizes bcrypt on the front-end.
Information about previously booked flights is presented on the users profile page.
This project has been created to assess understanding of the included technologies
and not to be deployed.

## Getting Started

* First clone the repository into a location you can easily access.
* Import the existing Maven project into Eclipse (with Spring Tool Suite) or a similar Java IDE.
* Set your postgreSQL username and password in "src\main\resources\application.properties".
* Create the schema "ftd_flight" using pgAdmin.
* In Eclipse run the Maven project as a Spring Boot Application.
* You should now be able to visit the site on localhost at port 8000.

### Prerequisities

* Eclipse or a similar IDE
* PostgreSQL and pgAdmin

## Built With

* Eclipse Neon
* Maven
* Spring Boot (Web, JPA and PostgreSQL)
* JGraphT Library
* AngularJS
* Angular Material
* Atom
* Webpack

## Author

* **Martin Zakov**

## Acknowledgments

* Michael Boren
* Peter Zastoupil
