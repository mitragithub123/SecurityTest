package testCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.ZapUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PassiveScan {
	public WebDriver driver;
	public Properties property;
	public String url;

	@BeforeMethod
	public void setUp() throws IOException {
		FileInputStream file = new FileInputStream(".\\src\\test\\resources\\config.properties");
		property = new Properties();
		property.load(file);
		file.close();

		url = property.getProperty("url");

		ChromeOptions chromeOptions = new ChromeOptions();

		chromeOptions.setProxy(ZapUtility.proxy);
		chromeOptions.setAcceptInsecureCerts(true);
		chromeOptions.addArguments("--headless");

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(chromeOptions);
	}

	@Test
	public void testPassiveScan() {
		driver.get(url);
		ZapUtility.waitTillPassiveScanCompleted();
	}

	@AfterMethod
	public void tearDown() {
		ZapUtility.generateZapReport(url);
		driver.quit();
	}
}
