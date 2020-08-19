package com.realsteel.casestudy.lib.ui;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyAccount {

	
	WebDriver driver;
	public MyAccount(WebDriver driver)
	{
		this.driver=driver;
	}
	


public WebElement getEditYouAccountInfoLink()

{
	return driver.findElement(By.xpath("//a[text()='Edit your account information']"));
}

public WebElement getAddressBookLink()
{
	return driver.findElement(By.xpath("//a[text()='Address Book']"));
}

public WebElement getEditAddressBookButton()
{
	return driver.findElement(By.xpath("//a[text()='Edit']"));
}

public WebElement getNewAddressButton()
{
	return driver.findElement(By.xpath("//a[text()='New Address']"));
}
public WebElement getTelephoneTextBox()
{
	return driver.findElement(By.cssSelector("div>input#input-telephone"));
}

public WebElement getMyAccountContinueButton()
{
	return driver.findElement(By.cssSelector("input[type='submit'][value='Continue']"));
}

public WebElement getFirstNameTextBox()
{
	
return driver.findElement(By.cssSelector("div>input#input-firstname"));
}

public WebElement getLastNameTextBox()
{
	return driver.findElement(By.cssSelector("div>input#input-lastname"));
}

public WebElement getCompanyTextBox()
{
	return driver.findElement(By.cssSelector("div>input#input-company"));
}

public WebElement getAddress1TextBox()
{
	return driver.findElement(By.cssSelector("div>input#input-address-1"));
}

public WebElement getAddress2TextBox()
{
	return driver.findElement(By.cssSelector("div>input#input-address-2"));
}

public WebElement getCityTextBox()
{
	return driver.findElement(By.cssSelector("div>input#input-city"));
}

public WebElement getPostcodeTextBox()
{
	return driver.findElement(By.cssSelector("div>input#input-postcode"));
}

public WebElement getCountryDropDown()
{
	return driver.findElement(By.cssSelector("div>input#input-country"));
}

public WebElement getRegionStateDropDown()
{
	return driver.findElement(By.cssSelector("div>input#input-zone"));
}



}