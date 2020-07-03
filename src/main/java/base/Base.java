package base;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import utilites.Helper;
/**
 * 
 * @author Mohamed Gamal Abou-Daif
 *
 */
public class Base {
	
	public static WebDriver driver;
	public static Properties prop;
	ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest parentTest;
	public static ExtentTest childTest;
	String method;
	
	public static WebDriver init_driver( String broswerName) {

		switch (Constants.broswerName) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+Constants.chromePath);
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+Constants.firefoxPath);
			driver = new FirefoxDriver();
			break;
		case "internet explorer":
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+Constants.iePath);
			driver = new InternetExplorerDriver();
			break;
		default:
			throw new RuntimeException("Invalid browser name: " + Constants.broswerName);
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		return driver;
		
	}
	
	@BeforeTest
	public void report() {
		htmlReporter = new ExtentHtmlReporter(new File(System.getProperty("user.dir") + Constants.reportPath));
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}
	@BeforeMethod
	public void method(Method method) {
		parentTest = extent.createTest(method.getName());
	}
	@AfterClass
	public void stopDriver() {
		//driver.quit();
		extent.flush();
	}
	@AfterMethod
	public void screenshotOnFailure(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			System.out.println("Failed");
			System.out.println("Taking Screenshot...");
			Helper.captureScreenshot(driver, result.getName());

		}
		
	}

}
