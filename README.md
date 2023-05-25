## Getting Started
1. Clone the repository:
```bash
git clone git@github.com:rianp/echo-server.git
```

2. Navigate to the project directory:
```bash
cd echo-server
```

<<<<<<< Updated upstream
3. Install IntelliJ and JDK (Java Development Kit) if you haven't already. You can download IntelliJ from the official website and JDK from the Oracle website. Make sure to follow the installation instructions for your specific operating system.

4. Open the project in IntelliJ:
- Launch IntelliJ.
- Click on "Open" or "Import Project."
- Browse to the cloned echo-server directory and select it.
- IntelliJ will detect the project configuration and set it up.

5. Build the project using Gradle:
```bash
gradle build
```

6. Run the server:
```bash
=======
3. Build the project using Gradle:
```
gradle build
```

1. Run the server:
```
>>>>>>> Stashed changes
gradle run
```

**The server should now be running on localhost at port (todo: port number).**

## Testing
**This project includes JUnit tests to ensure that the server is functioning correctly.**

### Running Tests in IntelliJ
1. Open the project in IntelliJ if you haven't already.
2. Navigate to the test directory:
3. In the Project Explorer pane, locate the test directory.
4. Right-click on the test directory and select "Run 'All Tests'."

### Running Tests in Bash
1. Make sure you're in the project directory.
   Run the tests using Gradle:
```bash
gradle test
```

