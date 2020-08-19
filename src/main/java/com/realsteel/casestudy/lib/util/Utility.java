package com.realsteel.casestudy.lib.util;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utility {

	public static String SCREENSHOTPATH="C:\\Users\\abhat68\\git\\Casestudy\\target\\";
	public static void captureScreenshot(WebDriver driver, String screenshotname)
	{
		
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source,
					new File(SCREENSHOTPATH
							+ screenshotname + ".png")); 
//			System.out.println("Screenshot Taken");
		} catch (Exception e) {

			System.out.println("Exception while taking Screenshot" +e.getMessage());
		}
	}
	
	
}
