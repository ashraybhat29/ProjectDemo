package com.realsteel.casestudy.lib.ui;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
	}

	
	
	public WebElement getMyAccountButton()
	{
		return driver.findElement(By.xpath("(//a[@title='My Account']|//span[text()='My Account'])[1]"));
	}
	
	public WebElement getMYAccountDropdown()
	{
		return driver.findElement(By.xpath("(//ul//li//a[contains(text(),'My Account')])[1]"));
	}
	
	public WebElement getMyAccountLoginButton()
	{
		return driver.findElement(By.linkText("Login"));
	}
	
	
	public WebElement getUsernameTestbox()
	{
		return driver.findElement(By.xpath("//input[@placeholder='E-Mail Address']|//input[@name='email']|//input[@id='input-email']"));
	}

	public WebElement getPasswordTextbox()
	{
		return driver.findElement(By.id("input-password"));
	}
	
	public WebElement getLoginButton()
	
	{
		return driver.findElement(By.xpath("//input[@type='submit']"));
	}
	
public String getLoginErrorMsg()
	
	{
		String loginErrorMsg= driver.findElement(By.xpath("//div[contains(text(),'Warning: No match')]")).getText();
		return loginErrorMsg;
	}
	

public WebElement getLogoutButton()
{
	return driver.findElement(By.linkText("Logout"));
}


	public void waitForLoginPageToLoad()
	{
		WebDriverWait wait= new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div#logo"))));
	}


	

	
}