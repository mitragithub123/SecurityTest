# Security Test Framework

![Security Test Framework](https://img.shields.io/badge/Security--Testing-Framework-blue.svg) ![OWASP ZAP](https://img.shields.io/badge/OWASP-ZAP-orange.svg) ![Selenium](https://img.shields.io/badge/Selenium-WebDriver-green.svg) ![TestNG](https://img.shields.io/badge/TestNG-Testing-red.svg) 

This repository contains an automated security testing framework using **OWASP ZAP**, **Selenium WebDriver**, and **TestNG** to perform traditional, AJAX spidering, passive, and active security scans on web applications. The framework integrates ZAP's proxy capabilities to intercept and analyze web traffic for security vulnerabilities.

---

## Features 🚀
✅ **Traditional Spider**: Crawls through the web application’s static pages to discover all accessible resources.

✅ **AJAX Spider**: Crawls web applications built with modern frameworks (e.g., Angular, React) to discover dynamic pages loaded via JavaScript.

✅ **Passive Scan**: Passively monitors the web traffic between the browser and server to detect potential security issues without 
    actively interacting with the application.

✅ **Active Scan**: Actively probes the web application with attack payloads to identify vulnerabilities like **SQL Injection, XSS, and others**.

✅ **ZAP Reports**: Automatically generates security reports (**HTML format**) after scans are completed.

✅ **Clean Scan Tree**: Clears the ZAP scan tree after each test execution to ensure a fresh state for subsequent tests.

---

## Prerequisites ⚙️
- **Java JDK 8+**
- **Maven**
- **ChromeDriver** (for browser interaction)
- **OWASP ZAP installed and running locally**
- **ZAP API key** (required for API interaction)
- **TestNG framework**
- **WebDriverManager** (to manage browser drivers)

---

## Setup Instructions 📌

### 🔹 Install OWASP ZAP
1. Go to [OWASP ZAP Download](https://www.zaproxy.org/download/)
2. Click **Windows (64) Installer**
3. File will be downloaded
4. Install it
5. Open **Zaproxy**
6. Start a session
7. Go to **header** → **Click Tools > Options > Network > Server Certificates > Root CA Certificates > Click Save**
8. A `zap_root_ca.cert` will be downloaded

### 🔹 Import Certificate in Chrome 🔒
1. Open **Chrome browser**
2. Go to **Settings**
3. Search **cert**
4. Click **Security**
5. Click **Manage Certificates**
6. Go to **Local Certificates**
7. Click **Manage imported certificates from Windows**
8. Click **Import**
9. Select `zap_root_ca.cert`
10. Apply and close
11. Restart **Chrome**

### 🔹 Clone the Repository 🛠
```sh
git clone https://github.com/mitragithub123/SecurityTest.git
cd SecurityTest
```

### 🔹 Dependencies 📦
```xml
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

	<!-- ZAP Client API -->
	<dependency>
		<groupId>org.zaproxy</groupId>
		<artifactId>zap-clientapi</artifactId>
		<version>1.14.0</version>
	</dependency>
</dependencies>
```

### 🔹 Configure ZAP API Key and Proxy Settings 🔑
```java
private static final String zapAddress = "127.0.0.1";
private static final int zapPort = 8080;
private static final String apiKey = "your-zap-api-key";
```

---

## Key Classes and Methods 🏗

### 🔹 `ZapUtility.java`
- **`waitTillPassiveScanCompleted()`**: Waits for passive scanning to complete.
- **`performActiveScan()`**: Starts an active scan on a specified URL.
- **`generateZapReport()`**: Generates **ZAP security reports** in **HTML format**.
- **`cleanTheScanTree()`**: Cleans up the **ZAP scan tree** after tests.

### 🔹 `PassiveAndActiveScan.java`
- **`testPassiveScan()`**: Runs passive security tests on the target URL.
- **`testActiveScan()`**: Initiates **active security scans** on the target URL.

---

## Project Structure 📁
```plaintext
SecurityTest
│── src
│   ├── main/java
│   ├── main/resources
│   ├── test/java
│   │   ├── testCases
│   │   │   ├── ActiveScan.java
│   │   │   ├── PassiveAndActiveScan.java
│   │   │   ├── PassiveScan.java
│   │   │   ├── TraditionalAndAjaxSpiderScan.java
│   │   ├── utility
│   │   │   ├── ZapUtility.java
│   │   │   ├── ZapUtility1.java
│   │   │   ├── ZapUtilitySpider.java
│   ├── test/resources
│   │   ├── config.properties
│── reports
│── target
│── test-output
│── pom.xml
```

---

## Running Tests 🏃‍♂️
To execute the security tests, run:
`ZapUtility.java`
`ZapUtility1.java`

---

## Report Generation 📊
After execution, the security scan **report** will be available in the `reports/` folder.

---

## Contributing 🤝
Feel free to fork, improve, and submit **pull requests**!

---

## License 📜
This project is licensed under the **MIT License**.

