package com.infofactors.verifyAceTest;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utility
{
	public static String  getscreenshot(WebDriver driver)
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src_file=ts.getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"\\src\\test\\resources\\screenshots" +System.currentTimeMillis()+".png";
		File dstination=new File(path);
		
	try 
	{
		FileUtils.copyFile(src_file, dstination);

	} catch (Exception e) {
		
		System.out.println("Capture Failed"+e.getMessage());
	}
	return path;
	}
}
