package com.realsteel.casestudy.lib.ui;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Category {

	
	WebDriver driver;
	public Category(WebDriver driver)
	{
		this.driver=driver;
	}
	

    public WebElement getSortByDropdown()
    {
	return driver.findElement(By.cssSelector("select#input-sort>option"));
    }
    
}
