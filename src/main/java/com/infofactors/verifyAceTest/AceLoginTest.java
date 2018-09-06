package com.infofactors.verifyAceTest;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;




public class AceLoginTest 
{
	
	WebDriver driver;
	ExtentReports reports;
	ExtentTest logger;
	
    @Test
    @Parameters("browser")
	public void aceloginTest(String browserName)
	{
    	
		if (browserName.equalsIgnoreCase("firefox"))
		{
			
			
			driver=new FirefoxDriver();
			
		}else if (browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
			driver=new ChromeDriver();
			
		}else if (browserName.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\\\executables\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();
			
		}
		
		reports=new ExtentReports(System.getProperty("user.dir")+"\\src\\test\\resources\\extentreports\\extentreport.html");
		logger=reports.startTest("Verify Acegrades Login Test");
		
		driver.manage().window().maximize();
		logger.log(LogStatus.INFO, "Browser Maximized");
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://acegrades.com/");
		logger.log(LogStatus.INFO, "Navigate to Browser URL");
		driver.findElement(By.linkText("LOG IN")).click();
		
		
		
		driver.findElement(By.id("email")).sendKeys("sample.teacher@gmail.com");
		driver.findElement(By.id("password")).sendKeys("123456789623asdg");
		driver.findElement(By.id("loginId")).click();
		String teacher_title=driver.getTitle();
		System.out.println(teacher_title);
		Assert.assertEquals(teacher_title, "Teacher Dashboard");
		logger.log(LogStatus.INFO, "Title Captured");
		
		reports.endTest(logger);
		reports.flush();
		
	}
    
    @AfterMethod
    public void teardown(ITestResult result)
    {
    	if (result.getStatus()==ITestResult.FAILURE) 
    	{
    		String temp=Utility.getscreenshot(driver);
    		logger.log(LogStatus.FAIL, temp);
    		reports.flush();
		}
    }
    

}
