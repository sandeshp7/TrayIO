# TrayIO

## Run the test
- Download TrayIOTests-0.0.1-SNAPSHOT.one-jar.jar located in TrayIO/TrayIOTests/target/
- Open the terminal and navigate to the folder the Jar is located in
- Run this command: java -jar TrayIOTests-0.0.1-SNAPSHOT.one-jar.jar

## Testing approach
- Each connector message has a test method specifed in the TrayIOTest.java
- Input to the messages are specified in testdata.properties file
  * This will enable us to change the input to the connector without touching the code.
- Base url and the connector URL is maintained in config.properties
  * This will let us add or remove connectors

