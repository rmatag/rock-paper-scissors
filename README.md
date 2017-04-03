# rock-paper-scissors
The project is composed of 3 different Maven projects:
* **rps-common:** This contains the types as well as a utilities class to generate random plays
* **rps-remote-player:** Spring Boot Web application with a embedded Tomcat Server which serves a GET Rest Endpoint to get Random plays
* **rps-game**: Spring Boot command line application which simulates 10 iterations of the Zero-sum game Rock-Paper-Scissors.
This injects 3 service providers each of them with its own purpose:
  * _ConsoleInputScanner_: Service that provides the input from console
  * _PayoffMatrixService_: Service that provides the corresponding payoff matrix to obtain the result of a play
  * _PlayerPlayService_: Service that provides both players's play according to the following criteria:
    * FAIR Mode: Both players' play are generated randomly
    * UNFAIR Mode: The main player's play is generated randomly and the adversary player's play is always ROCK
    * REMOTE Mode: Idem FAIR mode but the adversary player's play is retrieved from a server (rps-remote-player web app)

  The outcome of the game is by logging the result of each of the 10 iterations played in the game as well as the total results.
  Logs are in both console and log file by using Log4j library.
  
# Run the Game
1. To run the game first, make sure you've got the common library installed in your local maven repository.
`cd PATH_TO_YOUR_WORKSPACE/rps-common` and then `mvn clean install`
1. Run the Remote Web Application:
`cd PATH_TO_YOUR_WORKSPACE/rps-remote-player` then `mvn clean package` and `mvn spring-boot:run`
1. Finally run the Game by the Spring boot command as the previous one: `mvn spring-boot:run`

# Possible improvements
The _ConsoleInputService_ could keep showing the menu while the introduced option is a not valid one and show a WARNING message to the user indicating that a not valid option was introduced, try a valid one.
Unit Test to verify the correct input from the users

