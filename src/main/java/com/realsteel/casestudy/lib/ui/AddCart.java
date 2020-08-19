package com.realsteel.casestudy.lib.ui;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddCart {

	
	WebDriver driver;
	public AddCart(WebDriver driver)
	{
		this.driver=driver;
	}
	

    public WebElement getAddToCartQuantityTextbox()
    {
	return driver.findElement(By.cssSelector("div>div>input#input-quantity"));
    }
    
    public WebElement getAddToCartButton()
    {
    	return driver.findElement(By.cssSelector("div>div>button#button-cart"));
    }
    
    public WebElement getBasketTabButton()
    {
    	return driver.findElement(By.xpath("//ul//li//a[@title='Shopping Cart']"));
    }
    
    public WebElement getDescription()
    {
    	return driver.findElement(By.cssSelector("div>div#tab-description"));
    }
    
    public List<WebElement> getAdditionImageTab()
    {
    	return driver.findElements(By.xpath("//div[@id='product-product']//div[@class='row']//li//a/img"));
    }
}
