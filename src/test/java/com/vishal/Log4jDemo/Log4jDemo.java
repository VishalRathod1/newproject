package com.vishal.Log4jDemo;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Log4jDemo {
	public WebDriver driver;
	//static Logger logger=Logger.getLogger(Log4jDemo.class);
	static Logger logger=Logger.getLogger(Log4jDemo.class);
  @Test(priority=1)
  public void loginWithValidDetails() {
	  PropertyConfigurator.configure("H:\\Basic Java\\Java projects\\Log4jDemo\\src\\test\\resources\\log4j.properties");
	  driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("Suvidyap1");
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("P@ssword1");
	  driver.findElement(By.xpath("//input[@name='login']")).click();
	  driver.findElement(By.linkText("SIGN-OFF")).click();
  }
  
  @Test(priority=2)
  public void loginWithInvalidDetails() {
	  PropertyConfigurator.configure("H:\\Basic Java\\Java projects\\Log4jDemo\\src\\test\\resources\\log4j.properties");
	  driver.findElement(By.xpath("//input[@name='userName']")).sendKeys("Suvidyap2");
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("P@ssword21");
	  driver.findElement(By.xpath("//input[@name='login']")).click();
	  
  }
  
  @BeforeMethod
  public void beforeMethod() {
	  logger.info("In getAllCookies method under BeforeMethod");
	  Set<Cookie> c=driver.manage().getCookies();
	  for(Cookie cookie:c){
		  logger.info(cookie.getName());
	  }
  }

  @AfterMethod
  public void captureScreenshot() throws IOException {
	  logger.info("In captureScreenshot method under AfterMethod");
	  File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  FileUtils.copyFile(src , new File("H:\\Basic Java\\Java projects\\Log4jDemo\\src\\test\\resources\\Screenshot\\MercuryTSLOog$j.jpeg"));
	  logger.info("ScreenShot captured successfully");
  }

  @BeforeClass
  public void windowMaximize() {
	  System.out.println("In windowMaximize method under beforeClass");
	  driver.manage().window().maximize();
	  System.out.println("Window is maximized successfully");
  }

  @AfterClass
  public void deleteCookies() {
	  logger.info("In deleteCookies method under AfterClass");
      driver.manage().deleteAllCookies();
      logger.info("delete cookies succesfull");
  }

  @BeforeTest
  public void enterApplicationUrl() {
	  logger.info("In enterApplicationUrl method under BeforeTest");
	  driver.get("http://newtours.demoaut.com/");
	  logger.info("url is entered successfully");
  }

  @AfterTest
  public void dbConnection() {
	  logger.info("Db connection close");
  }

  @BeforeSuite
  public void openBrowser() {
	  logger.info("In openBrowser method under BeforeSuite");
	  System.setProperty("webdriver.chrome.driver", "E:\\16122018\\chromedriver_win32\\chromedriver.exe");
	  driver=new ChromeDriver();
	  logger.info("Chrome browser is opened successfully");
  }

  @AfterSuite
  public void closeBrowser() {
	  logger.info("In closeBrowser method under AfterSuite");
	  driver.close();
	  logger.info("Chrome browser is closed successfully");
	  
	  
	  System.out.println("added from eclips");
  }

}
