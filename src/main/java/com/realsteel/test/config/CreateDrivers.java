package com.realsteel.test.config;


import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.realsteel.casestudy.lib.util.DataHandlers;


public class CreateDrivers {
	public static WebDriver getDriverInstance()
	{
		WebDriver driver=null;
		String browser=DataHandlers.getDataFromProperties("configuration","browser");
		String url= DataHandlers.getDataFromProperties("configuration","env");
		if(browser.equalsIgnoreCase("chrome"))
		{
					
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\Drivers\\chromedriver_84.exe");
			
			driver= new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver","F:\\Ashray\\Selenium\\Projects\\Selenium_Jqueryui\\Drivers\\geckodriver.exe");
			driver= new FirefoxDriver();
		}
		else
		{
			System.err.println("Invalid browser, please"+ " check configuartion.properties file");
			return driver;
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
	
		if(url.equalsIgnoreCase("prod"))
		{
			driver.get("https://demo.opencart.com/");
		}
		else 
		{
			driver.get("https://jqueryui.com/");
		}
		return driver;
		
	}

}