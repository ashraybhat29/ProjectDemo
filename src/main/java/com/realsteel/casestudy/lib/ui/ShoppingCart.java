package com.realsteel.casestudy.lib.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShoppingCart {

	
	WebDriver driver;
	public ShoppingCart(WebDriver driver)
	{
		this.driver=driver;
	}
	

    public WebElement getContinueShoppingbutton()
    {
	return driver.findElement(By.linkText("Continue Shopping"));
    }
    
    public WebElement getCheckoutButton()
    {
    	return driver.findElement(By.linkText("Checkout"));
    }
    
    public WebElement getShoppingQuantityTextBox()
    {
    	return driver.findElement(By.xpath("(//tr//td[contains(@class,'text-left')]//div/input)[1]"));
    }
    
    
   public WebElement getUpdateButton()
    {
    	return driver.findElement(By.cssSelector("button[data-original-title='Update'][type='submit']"));
    }		
    
   public WebElement getRemoveButton()
   {
	   return driver.findElement(By.cssSelector("button[data-original-title='Remove'][type='button']"));
   }
  


}
