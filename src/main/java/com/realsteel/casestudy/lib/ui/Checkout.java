package com.realsteel.casestudy.lib.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.realsteel.casestudy.lib.util.Utility;

public class Checkout {
	
	
	WebDriver driver;
	public Checkout(WebDriver driver)
	{
		this.driver=driver;
	}

	public WebElement getNewAddressRadioButton()
	{
		return driver.findElement(By.cssSelector("input[type='radio'][value='new'][name='payment_address']"));
	}
	

public WebElement getFirstNameTextBox()
{
	
return driver.findElement(By.cssSelector("div>input#input-payment-firstname"));
}

public WebElement getLastNameTextBox()
{
	return driver.findElement(By.cssSelector("div>input#input-payment-lastname"));
}

public WebElement getCompanyTextBox()
{
	return driver.findElement(By.cssSelector("div>input#input-payment-company"));
}

public WebElement getAddress1TextBox()
{
	return driver.findElement(By.cssSelector("div>input#input-payment-address-1"));
}

public WebElement getAddress2TextBox()
{
	return driver.findElement(By.cssSelector("div>input#input-payment-address-2"));
}

public WebElement getCityTextBox()
{
	return driver.findElement(By.cssSelector("div>input#input-payment-city"));
}

public WebElement getPostcodeTextBox()
{
	return driver.findElement(By.cssSelector("div>input#input-payment-postcode"));
}

public WebElement getCountryDropDown()
{
	return driver.findElement(By.cssSelector("div>select#input-payment-country"));
}

public WebElement getRegionStateDropDown()
{
	return driver.findElement(By.cssSelector("div>select#input-payment-zone"));
}

public WebElement getContinueAddressButton()
{
	return driver.findElement(By.cssSelector("input[type='button'][value='Continue'][id='button-payment-address']"));
}
public WebElement getContinueShippingButton()
{
	return driver.findElement(By.cssSelector("input[type='button'][value='Continue'][id='button-shipping-address']"));
}
public WebElement getContinueDeliveryButton()
{
	return driver.findElement(By.cssSelector("input[type='button'][value='Continue'][id='button-shipping-method']"));
}

public WebElement getTermsAndConditionCheckbox()
{
	return driver.findElement(By.cssSelector("input[type='checkbox'][name='agree']"));
}

public WebElement getContinuePaymentButton()
{
	return driver.findElement(By.cssSelector("input[type='button'][value='Continue'][id='button-payment-method']"));
}

public WebElement getConfirmOrderButton()
{
	return driver.findElement(By.cssSelector("input[type='button'][value='Confirm Order'][id='button-confirm']"));
}

public String getOrderPlacedTextMsg()
{
	String orderplaced= driver.findElement(By.xpath("//h1[text()='Your order has been placed!']")).getText();
	return orderplaced;
}

public void checkoutProcess() throws InterruptedException
{
	   
		getContinueAddressButton().click();
		Thread.sleep(5000);
		getContinueShippingButton().click();
		Thread.sleep(5000);
		getContinueDeliveryButton().click();
		Thread.sleep(5000);
		getTermsAndConditionCheckbox().click();
		getContinuePaymentButton().click();
		Thread.sleep(5000);
		getConfirmOrderButton().click();
		Thread.sleep(5000);
		String orderPlacedMsg =getOrderPlacedTextMsg();
	    String expectedMsg = "Your order has been placed!";
	    Assert.assertEquals(orderPlacedMsg, expectedMsg);
}
public void fillAddress(String firstname,String lastname,String company,String address1, String address2, String city, String postcode,
		String country, String regionstate) throws InterruptedException
{
	getFirstNameTextBox().sendKeys(firstname);
    getLastNameTextBox().sendKeys(lastname);
    getCompanyTextBox().sendKeys(company);
    getAddress1TextBox().sendKeys(address1);
    getAddress2TextBox().sendKeys(address2);
    getCityTextBox().sendKeys(city);
    getPostcodeTextBox().sendKeys(postcode);
    Thread.sleep(1000);
	Select selectcountry=new Select(driver.findElement(By.cssSelector("div>select#input-payment-country")));	    
	selectcountry.selectByVisibleText(country);
	Select selectregion=new Select(driver.findElement(By.cssSelector("div>select#input-payment-zone")));
	selectregion.selectByVisibleText(regionstate);
	Utility.captureScreenshot(driver, " 10 address entered ");
}

}
