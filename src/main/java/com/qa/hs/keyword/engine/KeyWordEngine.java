package com.qa.hs.keyword.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.qa.hs.keyword.base.Base;


/**
 * 
 * @author Mohamed Gamal Abou-Daif
 *
 */
public class KeyWordEngine {

	public WebDriver driver;
	public Properties prop;

	public static Workbook book;
	public static Sheet sheet;

	public WebElement element;

	public final String SCENARIO_SHEET_PATH = System.getProperty("user.dir") 
			+ "/src/main/java/com/qa/hs/keyword/scenarios/Senario.xlsx";

	public KeyWordEngine()
	{
		driver = Base.driver;
	}

	public void startExecution(String sheetName) {

		FileInputStream file = null;
		try {
			file = new FileInputStream(SCENARIO_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		sheet = book.getSheet(sheetName);
		int k = 0;
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			try {

				String locatorType = sheet.getRow(i + 1).getCell(k + 1).toString().trim();
				String locatorValue = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
				String action = sheet.getRow(i + 1).getCell(k + 3).toString().trim();
				String value = sheet.getRow(i + 1).getCell(k + 4).toString().trim();
				//String secondValue = sheet.getRow(i + 1).getCell(k + 5).toString().trim();

				switch (action) {
				case "open browser":
					try {

						prop = Base.init_properties();
						if (value.isEmpty() || value.equals("NA")) {
							driver = Base.init_driver(prop.getProperty("browser"));
						} else {
							driver = Base.init_driver(value);
						}
						Base.childTest.pass("Successfully Opened the browser :" +value);
					}
					catch (Exception e) {
						Base.childTest.fail("Unable to Opened the browser :");
						throw e;
					}
					break;

				case "enter url":
					try {
						if (value.isEmpty() || value.equals("NA")) {
							driver.get(prop.getProperty("url"));
						} else {
							driver.get(value);
						}
						Base.childTest.pass("Successfully navigate to App :" +value);
					}
					catch (Exception e) {
						Base.childTest.fail("Unable to navigate to App :");
						throw e;
					}
					break;
				case "upload file":
					try {
						Runtime .getRuntime().exec(System.getProperty("user.dir")+"\\Uploads\\"+value);

						Base.childTest.pass("Successfully Uploaded the file :" +value);
					}
					catch (Exception e) {
						Base.childTest.fail("Unable to Uploaded the file :");
						throw e;
					}
					break;

				case "quit":
					driver.quit();
					break;
				default:
					break;
				}

				switch (locatorType) {
				case "id":
					element = driver.findElement(By.id(locatorValue));
					if (action.equalsIgnoreCase("sendkeys")) {
						try {
							element.clear();
							element.sendKeys(value);
							Base.childTest.pass("Performed type in  :" +action+ " with data : "+value);

						} catch (Exception e) {
							System.out.println("Element is not present");
							Base.childTest.fail("Unable to Performed type in  :" +action+ " with data : "+value ,
									MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot()).build());
							Base.childTest.info(e);
							throw e;
						}
					}
					else if (action.equalsIgnoreCase("click")) {
						try {
							element.click();
							Base.childTest.pass("Successfully performed click on :" +value);

						} catch (Exception e) {
							Base.childTest.fail("Unable to performed click on :" +value,
									MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot()).build());
							Base.childTest.info(e);
							throw e;
						}

					}
					else if (action.equalsIgnoreCase("isDisplayed")) {
						element.isDisplayed();
					} else if (action.equalsIgnoreCase("getText")) {
						String elementText = element.getText();
						System.out.println("text from element : " + elementText);
					}
					locatorType = null;
					break;

				case "name":
					element = driver.findElement(By.name(locatorValue));

					if (action.equalsIgnoreCase("sendkeys")) {
						try {
							element.clear();
							element.sendKeys(value);
							Base.childTest.pass("Performed type in  :" +action+ " with data : "+value);

						} catch (Exception e) {
							System.out.println("Element is not present");
							Base.childTest.fail("Unable to Performed type in  :" +action+ " with data : "+value ,
									MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot()).build());
							Base.childTest.info(e);
							throw e;
						}
					}

					else if (action.equalsIgnoreCase("click")) {
						try {
							element.click();
							Base.childTest.pass("Successfully performed click on :" +value);

						} catch (Exception e) {
							Base.childTest.fail("Unable to performed click on :" +value,
									MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot()).build());
							Base.childTest.info(e);
							throw e;
						}
					}
					else if (action.equalsIgnoreCase("isDisplayed")) {
						element.isDisplayed();
					} else if (action.equalsIgnoreCase("getText")) {
						String elementText = element.getText();
						System.out.println("text from element : " + elementText);
					}
					locatorType = null;
					break;

				case "xpath":
					element = driver.findElement(By.xpath(locatorValue));
					//int fristValue = driver.findElements(By.xpath(locatorValue)).size();
					//int secValue = driver.findElements(By.xpath(secondValue)).size();
					if (action.equalsIgnoreCase("sendkeys")) {
						try {
							element.clear();
							element.sendKeys(value);
							Base.childTest.pass("Performed type in  :" +action+ " with data : "+value);

						} catch (Exception e) {
							System.out.println("Element is not present");
							Base.childTest.fail("Unable to Performed type in  :" +action+ " with data : "+value ,
									MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot()).build());
							Base.childTest.info(e);
							throw e;
						}
					}
					/*else if (action.equalsIgnoreCase("Validate")) {
						try {
							if(fristValue == secValue) 
							{
								Base.childTest.pass("Second Value "+secValue+" is equal to Frist Value : "+fristValue);
							}
						}
						catch (Exception e){
							Base.childTest.fail("Second Value "+secValue+" is not equal to Frist Value :" +fristValue,
									MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot()).build());
							Base.childTest.info(e);
							throw e;
						}
					}*/
					else if (action.equalsIgnoreCase("click")) {
						try {
							element.click();
							Base.childTest.pass("Successfully performed click on :" +value);

						} catch (Exception e) {
							Base.childTest.fail("Unable to performed click on :" +value,
									MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot()).build());
							Base.childTest.info(e);
							throw e;
						}
					} else if (action.equalsIgnoreCase("isDisplayed")) {
						element.isDisplayed();
					} else if (action.equalsIgnoreCase("getText")) {
						String elementText = element.getText();
						System.out.println("text from element : " + elementText);
					}
					locatorType = null;
					break;

				case "cssSelector":
					element = driver.findElement(By.cssSelector(locatorValue));
					if (action.equalsIgnoreCase("sendkeys")) {
						try {
							element.clear();
							element.sendKeys(value);
							Base.childTest.pass("Performed type in  :" +action+ " with data : "+value);

						} catch (Exception e) {
							System.out.println("Element is not present");
							Base.childTest.fail("Unable to Performed type in  :" +action+ " with data : "+value ,
									MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot()).build());
							Base.childTest.info(e);
							throw e;
						}
					}

					else if (action.equalsIgnoreCase("click")) {
						try {
							element.click();
							Base.childTest.pass("Successfully performed click on :" +value);

						} catch (Exception e) {
							Base.childTest.fail("Unable to performed click on :" +value,
									MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot()).build());
							Base.childTest.info(e);
							throw e;
						}
					} else if (action.equalsIgnoreCase("isDisplayed")) {
						element.isDisplayed();
					} else if (action.equalsIgnoreCase("getText")) {
						String elementText = element.getText();
						System.out.println("text from element : " + elementText);
					}
					locatorType = null;
					break;

				case "className":
					element = driver.findElement(By.className(locatorValue));
					if (action.equalsIgnoreCase("sendkeys")) {
						try {
							element.clear();
							element.sendKeys(value);
							Base.childTest.pass("Performed type in  :" +action+ " with data : "+value);

						} catch (Exception e) {
							System.out.println("Element is not present");
							Base.childTest.fail("Unable to Performed type in  :" +action+ " with data : "+value ,
									MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot()).build());
							Base.childTest.info(e);
							throw e;
						}
					}

					else if (action.equalsIgnoreCase("click")) {
						try {
							element.click();
							Base.childTest.pass("Successfully performed click on :" +value);

						} catch (Exception e) {
							Base.childTest.fail("Unable to performed click on :" +value,
									MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot()).build());
							Base.childTest.info(e);
							throw e;
						}
					} else if (action.equalsIgnoreCase("isDisplayed")) {
						element.isDisplayed();
					} else if (action.equalsIgnoreCase("getText")) {
						String elementText = element.getText();
						System.out.println("text from element : " + elementText);
					}
					locatorType = null;
					break;

				case "linkText":
					element = driver.findElement(By.linkText(locatorValue));
					if (action.equalsIgnoreCase("sendkeys")) {
						try {
							element.clear();
							element.sendKeys(value);
							Base.childTest.pass("Performed type in  :" +action+ " with data : "+value);

						} catch (Exception e) {
							System.out.println("Element is not present");
							Base.childTest.fail("Unable to Performed type in  :" +action+ " with data : "+value ,
									MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot()).build());
							Base.childTest.info(e);
							throw e;
						}
					}

					else if (action.equalsIgnoreCase("click")) {
						try {
							element.click();
							Base.childTest.pass("Successfully performed click on :" +value);

						} catch (Exception e) {
							Base.childTest.fail("Unable to performed click on :" +value,
									MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot()).build());
							Base.childTest.info(e);
							throw e;
						}
					} else if (action.equalsIgnoreCase("isDisplayed")) {
						element.isDisplayed();
					} else if (action.equalsIgnoreCase("getText")) {
						String elementText = element.getText();
						System.out.println("text from element : " + elementText);
					}
					locatorType = null;
					break;

				case "partialLinkText":
					element = driver.findElement(By.partialLinkText(locatorValue));
					if (action.equalsIgnoreCase("sendkeys")) {
						try {
							element.clear();
							element.sendKeys(value);
							Base.childTest.pass("Performed type in  :" +action+ " with data : "+value);

						} catch (Exception e) {
							System.out.println("Element is not present");
							Base.childTest.fail("Unable to Performed type in  :" +action+ " with data : "+value ,
									MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot()).build());
							Base.childTest.info(e);
							throw e;
						}
					}

					else if (action.equalsIgnoreCase("click")) {
						try {
							element.click();
							Base.childTest.pass("Successfully performed click on :" +value);

						} catch (Exception e) {
							Base.childTest.fail("Unable to performed click on :" +value,
									MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot()).build());
							Base.childTest.info(e);
							throw e;
						}
					} else if (action.equalsIgnoreCase("isDisplayed")) {
						element.isDisplayed();
					} else if (action.equalsIgnoreCase("getText")) {
						String elementText = element.getText();
						System.out.println("text from element : " + elementText);
					}
					locatorType = null;
					break;

				default:
					break;
				}

			} catch (Exception e) {

			}

		}

	}
	public String screenShot() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	}
}
