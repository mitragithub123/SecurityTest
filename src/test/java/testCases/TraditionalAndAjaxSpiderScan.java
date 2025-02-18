package testCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.zaproxy.clientapi.core.ClientApiException;

import io.github.bonigarcia.wdm.WebDriverManager;
import utility.ZapUtility1;
import utility.ZapUtilitySpider;

public class TraditionalAndAjaxSpiderScan {
	public WebDriver driver;
	public Properties property;
	private final String contextName = "Demo_Context";

	private String currentTestName;
	public String urlToTest;

	@BeforeMethod
	public void setUp() throws IOException {
		FileInputStream file = new FileInputStream(".\\src\\test\\resources\\config.properties");
		property = new Properties();
		property.load(file);
		file.close();

		urlToTest = property.getProperty("url");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setProxy(ZapUtility1.proxy);
		chromeOptions.setAcceptInsecureCerts(true);

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(chromeOptions);
	}

	@Test
	public void testSpider() throws ClientApiException {
		ZapUtilitySpider.performSpidering(urlToTest, contextName);
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws ClientApiException {
		if (result.getStatus() == ITestResult.SUCCESS) {
			System.out.println("Test " + result.getName() + " passed.");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			System.out.println("Test " + result.getName() + " failed.");
		}

		currentTestName = result.getName();
		ZapUtility1.generateZapReport(urlToTest, currentTestName);
		ZapUtility1.cleanTheScanTree();
		driver.quit();
	}
}
