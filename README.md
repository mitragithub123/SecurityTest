# Security Test Framework

This repository contains an automated security testing framework using OWASP ZAP, Selenium WebDriver, and TestNG to perform traditional, AJAX spidering, passive, and active security scans on web applications. The framework integrates ZAP's proxy capabilities to intercept and analyze web traffic for security vulnerabilities.

## Features

- **Traditional Spider**: Crawls through the web application’s static pages to discover all accessible resources.
- **AJAX Spider**: Crawls web applications built with modern frameworks (e.g., Angular, React) to discover dynamic pages loaded via JavaScript.
- **Passive Scan**: Passively monitors the web traffic between the browser and server to detect potential security issues without actively interacting with the application.
- **Active Scan**: Actively probes the web application with attack payloads to identify vulnerabilities like SQL Injection, XSS, and others.
- **ZAP Reports**: Automatically generates security reports (HTML format) after scans are completed.
- **Clean Scan Tree**: Clears the ZAP scan tree after each test execution to ensure a fresh state for subsequent tests.

## Prerequisites

- Java JDK 8+
- Maven
- ChromeDriver (for browser interaction)
- OWASP ZAP installed and running locally
- ZAP API key (required for API interaction)
- TestNG framework
- WebDriverManager (to manage browser drivers)

## Setup Instructions

1. Clone the repository:
    ```bash
    git clone https://github.com/your-repo/security-test-framework.git
    cd security-test-framework
    ```

2. Install dependencies:
    ```bash
    <dependencies>
		<!-- TestNG -->
		<dependency>
			<groupId>org.testng</groupId>
			<artifactId>testng</artifactId>
			<version>7.8.0</version>
			<scope>test</scope>
		</dependency>

		<!-- Selenium Java -->
		<dependency>
			<groupId>org.seleniumhq.selenium</groupId>
			<artifactId>selenium-java</artifactId>
			<version>4.19.1</version>
		</dependency>

		<!-- WebDriverManager -->
		<dependency>
			<groupId>io.github.bonigarcia</groupId>
			<artifactId>webdrivermanager</artifactId>
			<version>5.7.0</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/org.zaproxy/zap-clientapi -->
		<dependency>
			<groupId>org.zaproxy</groupId>
			<artifactId>zap-clientapi</artifactId>
			<version>1.14.0</version>
		</dependency>

	</dependencies>
    ```

3. Make sure OWASP ZAP is running and accessible on `localhost:8080`.

4. Configure ZAP API key and proxy settings in the `ZapUtility` class:
    ```java
    private static final String zapAddress = "127.0.0.1";
    private static final int zapPort = 8080;
    private static final String apiKey = "your-zap-api-key";

## Key Classes and Methods

- **ZapUtility.java**:
    - `waitTillPassiveScanCompleted()`: Waits for passive scanning to complete.
    - `performActiveScan()`: Starts an active scan on a specified URL.
    - `generateZapReport()`: Generates ZAP security reports in traditional HTML or HTML-plus formats.
    - `cleanTheScanTree()`: Cleans up the ZAP scan tree after tests.

- **PassiveAndActiveScan.java**:
    - `testPassiveScan()`: Runs passive security tests on the target URL.
    - `testActiveScan()`: Initiates active security scans on the target URL.