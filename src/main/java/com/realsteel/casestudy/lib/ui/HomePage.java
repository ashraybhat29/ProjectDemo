package com.realsteel.casestudy.lib.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;



public class HomePage {

//	public String SORTBYOPTION= By.cssSelector("select#input-sort");
	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
	}
	

	public WebElement getTabletCategory()
	{
	   return driver.findElement(By.xpath("//div[@class='collapse navbar-collapse navbar-ex1-collapse']//a[contains(text(),'Tablets')]"));
	}
	
	public WebElement getCameraCategory()
	{
	   return driver.findElement(By.xpath("//div[@class='collapse navbar-collapse navbar-ex1-collapse']//ul//li[7]/a"));
	}
	public WebElement getMp3Category()
	{
	   return driver.findElement(By.xpath("//div[@class='collapse navbar-collapse navbar-ex1-collapse']//ul//li[8]/a"));
	}
	public WebElement getSearchContentPane()
	{
	   return driver.findElement(By.xpath("//div[@id='content']"));
	}
	public WebElement getYourStoreLogo()
	{
		return driver.findElement(By.cssSelector("div#logo>h1>a"));
	}

	public WebElement getSearchTextBox()
	{
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
		return driver.findElement(By.cssSelector("div#search>input"));
	}

	public WebElement getSearchButton()
	{
		return driver.findElement(By.cssSelector("span.input-group-btn"));
	}

	public WebElement getSearchedProduct()
	{
		return driver.findElement(By.xpath("(//div[contains(@class,'product-grid')]//h4/a)"));
	}

	public WebElement getCategoryLink()
	{
		return driver.findElement(By.xpath("//nav[@id='menu']//div//ul[contains(@class,'nav')]//li/a"));
	}

	public WebElement getLaptopCatogery()
    {
	return driver.findElement(By.xpath("//a[text()='Laptops & Notebooks']"));
    }
	
	public WebElement getPhonesAndPDAs()
	{
		return driver.findElement(By.xpath("//a[text()='Phones & PDAs']/.."));
	}
	
	public WebElement geDesktops()
	{
		return driver.findElement(By.xpath("//a[text()='Desktops']/.."));
	}
	
	
	public WebElement getShowAllLaptop()
	{
		return driver.findElement(By.linkText("Show All Laptops & Notebooks"));
	}
	
	public WebElement getSortByDropdown()
	{
	    return driver.findElement(By.cssSelector("select#input-sort"));
	}
	
//	public WebElement getNameAtoZDropdown()
//	{
//         return driver.findElement(By.xpath("//option[contains(text(),'Name (A - Z')]"));
//	}
//	
//	public WebElement getNameZtoADropdown()
//	{
//         return driver.findElement(By.xpath("//option[contains(text(),'Name (Z - A')]"));
//	}
	  public WebElement getCheckoutTabButton()
	    {
	    	return driver.findElement(By.cssSelector("div>div#cart>button"));
	    }
	  
	  public String getCartEmptyText()
	    {
		  String errormessage=  driver.findElement(By.xpath("//li//p[contains(text(),'Your shopping cart is empty')]")).getText();
		  return  errormessage;
	    }
	  
	  //Select options in Sort By options.Name A-Z
	  public void selectSortDropdownAtoZ(String text)
	  {
		Select select= new Select(getSortByDropdown());
		select.selectByVisibleText(text);
		java.util.List<WebElement>  values = driver.findElements(By.xpath("(//div[contains(@class,'product-grid')]//h4/a)"));
	
		List actuallist = new ArrayList();

		for(WebElement ele: values)
		{
			String data =ele.getText();
			actuallist.add(data);
			System.out.println(actuallist);
		}
		
	    List temp= new ArrayList();
	
	    temp.addAll(actuallist);
	    System.out.println("Getting value from actuallist"+temp);
	
	    //Ascending
	
	    Collections.sort(temp);
        System.out.println("Temp after sorting"+temp);
	    Assert.assertTrue(actuallist.equals(temp));
	  }
	  
	    //Select options in Sort By options.Name Z-A
	  public void selectSortDropdownZtoA(String text) 
	  {
		Select select= new Select(getSortByDropdown());
		select.selectByVisibleText(text);
		java.util.List<WebElement>  values = driver.findElements(By.xpath("(//div[contains(@class,'product-grid')]//h4/a)"));
			
		List actuallist = new ArrayList();
			
		for(WebElement ele: values)
		{
			String data =ele.getText();
			actuallist.add(data);
			System.out.println(actuallist);
		}
		List temp= new ArrayList();
		
		temp.addAll(actuallist);
		System.out.println("Getting value from actuallist"+temp);
		
		//Ascending
		
		Collections.reverse(temp);
		
		System.out.println("Temp after Reversing"+temp);
		
		Assert.assertFalse(actuallist.equals(temp));
	  }
		    

}