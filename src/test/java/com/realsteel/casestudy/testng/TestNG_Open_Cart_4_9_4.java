package com.realsteel.casestudy.testng;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.realsteel.casestudy.lib.util.DataHandlers;
import com.realsteel.casestudy.lib.util.Utility;
import com.realsteel.test.config.CreateDrivers;
import com.realsteel.casestudy.lib.ui.AddCart;
import com.realsteel.casestudy.lib.ui.Category;
import com.realsteel.casestudy.lib.ui.Checkout;
import com.realsteel.casestudy.lib.ui.HomePage;
import com.realsteel.casestudy.lib.ui.LoginPage;
import com.realsteel.casestudy.lib.ui.MyAccount;
import com.realsteel.casestudy.lib.ui.ShoppingCart;

//import java.util.concurrent.TimeUnit;
//
//import org.openqa.selenium.By;


public class TestNG_Open_Cart_4_9_4
{
	//private static final boolean WebElement = false;
	WebDriver driver;
	LoginPage login;
	MyAccount account;
	AddCart addcart;
	Category category;
	ShoppingCart shopping;
	HomePage homepage;
	Checkout checkout;
	
	// 1st wrong username and password
	String wrongun1=DataHandlers.getDataFromExcel("data","TC01", 1, 0);
	String wrongpwd1=DataHandlers.getDataFromExcel("data","TC01", 1, 1);
	//2nd wrong username and password
	String wrongun2=DataHandlers.getDataFromExcel("data","TC01", 2, 0);
	String wrongpwd2=DataHandlers.getDataFromExcel("data","TC01", 2, 1);
	//Valid username and password
	String validusername=DataHandlers.getDataFromExcel("data","TC01", 3, 0);
	String validpassword=DataHandlers.getDataFromExcel("data","TC01", 3, 1);        
				
	@BeforeMethod
	public void precondition()
	{
		driver= CreateDrivers.getDriverInstance();
		login= new LoginPage(driver);
		account=new MyAccount(driver);
		addcart=new AddCart(driver);
		category= new Category(driver);
		shopping = new ShoppingCart(driver);
		homepage= new HomePage(driver);
		checkout = new Checkout(driver);
		
		//wait for login page
		login.waitForLoginPageToLoad();
		//get title and verify
				
		Utility.captureScreenshot(driver, "Browser Started");
		//clicking on Login button
		login.getMyAccountButton().click();
		login.getMyAccountLoginButton().click();
		Utility.captureScreenshot(driver, "Login Page");
				
	}
	
	public void login()
	{
		// Entering Valid Username Password		  
		login.getUsernameTestbox().sendKeys(validusername);
		login.getPasswordTextbox().sendKeys(validpassword);
		login.getLoginButton().click();
	}
	
	public void logout() throws InterruptedException
	{
		Thread.sleep(3000);
		login.getMyAccountButton().click();
  	    login.getLogoutButton().click();
  		Utility.captureScreenshot(driver, "Logout");
  		System.out.println("Logged out successfull");
	}
	
	@Test
	public void tc001_LoginLogout() throws InterruptedException
	{
		//Step 3 Entering 1st wrong username and password
		try
		{
			login.getUsernameTestbox().sendKeys(wrongun1);
			login.getPasswordTextbox().sendKeys(wrongpwd1);
			Thread.sleep(2000);
			login.getLoginButton().click();
			String loginErrorMsg = login.getLoginErrorMsg();
			String ExpectedLoginErrorMsg = "Warning: No match for E-Mail Address and/or Password.";
			
			if (loginErrorMsg.contains(ExpectedLoginErrorMsg))
			{
				System.out.println("Login Failed due to invalid username password for the 1st time");
				Utility.captureScreenshot(driver, "3 Wrong Un and Pwd 1");
				login.getUsernameTestbox().clear();
				login.getPasswordTextbox().clear();
				//Step 4 Entering 2nd wrong username and password
				login.getUsernameTestbox().sendKeys(wrongun2);
				login.getPasswordTextbox().sendKeys(wrongpwd2);
				Thread.sleep(2000);
				String loginErrorMsg2 = login.getLoginErrorMsg();
				if (loginErrorMsg2.contains(ExpectedLoginErrorMsg))
				   {
					System.out.println("Login Failed due to invalid username password for the 2nd time");
					Utility.captureScreenshot(driver, "4 Wrong Un and Pwd 2");
					login.getLoginButton().click();
					login.getUsernameTestbox().clear();
					login.getPasswordTextbox().clear();

					//Step 5 Entering Valid Username Password		  
					login();
					Utility.captureScreenshot(driver, "5 Login");

					String actualTitle = driver.getTitle();
					String expectedTitle = "My Account";
					Assert.assertEquals(actualTitle, expectedTitle);
//					homepage.getYourStoreLogo().click();
//					homepage.getSearchTextBox().sendKeys(producttosearch);
					System.out.println("1st test cases completed ");
				   } 
				else 
				    {
					System.out.println("Logged in successfully  without entering wrong username "
							+ "and password for the 2nd time");
				    }
			} 
			else
			     {
				System.out.println(
						"Logged in successfully  without entering wrong username and password for the 1st time");
			     } 
		} 
		catch (Exception e)
		   {
			System.out.println("Exception while Identifying error message" +e.getMessage());
		   }
		
	}
		
	@Test(dataProvider="TC02")
	public void tc002_E2E(String search,String quantity,String shoppingquantity ) throws InterruptedException
	{
	    //2st step
		login();
		Utility.captureScreenshot(driver, "2 Login");
		//keyboardSearch();
		
		//3rd step search product and press enter
		homepage.getYourStoreLogo().click();
		homepage.getSearchTextBox().sendKeys(search);
		homepage.getSearchTextBox().sendKeys(Keys.ENTER);
		Utility.captureScreenshot(driver, "Searched "+search);
        System.out.println("Searched "+search);   
        String searchedobject = "HP";
	        if(search != searchedobject)
	        {

        //4th step Click on the product 
        
        homepage.getSearchedProduct().click();
        Utility.captureScreenshot(driver, "4 ProductClicked "+search);
        
        //5th step Enter Quantity and click on AddtoCart
        
        addcart.getAddToCartQuantityTextbox().clear();
        addcart.getAddToCartQuantityTextbox().sendKeys(quantity);
        Thread.sleep(2000);
        Utility.captureScreenshot(driver, "5 AddToCart " +search);
        addcart.getAddToCartButton().click();

        //6th step Click on Basket Tab
        
        addcart.getBasketTabButton().click();
        String actualTitle = driver.getTitle();
	    String expectedTitle = "Shopping Cart";
	    Assert.assertEquals(actualTitle, expectedTitle);
	    Utility.captureScreenshot(driver, "6 ShoppingCart "+search);
        	    //step 7 click on continue shopping
	    
	    shopping.getContinueShoppingbutton().click();
	    //8th step search and select product
	    
	    homepage.getYourStoreLogo().click();
		homepage.getSearchTextBox().sendKeys(search);
		homepage.getSearchTextBox().sendKeys(Keys.ENTER);
		Utility.captureScreenshot(driver, "8 Searched "+search);
        System.out.println("Searched "+search);   
        //step 9 select the product
        homepage.getSearchedProduct().click();
        Utility.captureScreenshot(driver, "9 ProductClicked "+search);
        
        //step 10 select quantity and click on add to cart
        addcart.getAddToCartQuantityTextbox().clear();
        addcart.getAddToCartQuantityTextbox().sendKeys(shoppingquantity);
        Thread.sleep(2000);
        Utility.captureScreenshot(driver, "10 AddToCart " +search);
        addcart.getAddToCartButton().click();
        //step11 click on BasketTab     
        addcart.getBasketTabButton().click();
        String actualTitle1 = driver.getTitle();
	    String expectedTitle1 = "Shopping Cart";
	    Assert.assertEquals(actualTitle1, expectedTitle1);
	    Utility.captureScreenshot(driver, "11 ShoppingCart "+search);
	    
	    // step 12 to 16
	    
	    shopping.getCheckoutButton().click();
	    Utility.captureScreenshot(driver, " 12 checkout " +search);
	    checkout.checkoutProcess();
	        }
    	//16 step Clicking on Logout
   	     logout();
	}
	
   @Test(dataProvider="TC03")
	public void tc003_RemoveProduct(String search,String quantity,String searchagain,String shoppingquantity )throws InterruptedException
	{
		//2st step
				login();
				Utility.captureScreenshot(driver, "2 Login");
				//keyboardSearch();
				
				//3rd step search product and press enter
				homepage.getYourStoreLogo().click();
				homepage.getSearchTextBox().sendKeys(search);
				homepage.getSearchTextBox().sendKeys(Keys.ENTER);
				Utility.captureScreenshot(driver, "Searched "+search);
		        System.out.println("Searched "+search);   

		        //4th step Click on the product 
		        
		        homepage.getSearchedProduct().click();
		        Utility.captureScreenshot(driver, "4 ProductClicked "+search);
		        
		        //5th step Enter Quantity and click on AddtoCart
		        
		        addcart.getAddToCartQuantityTextbox().clear();
		        addcart.getAddToCartQuantityTextbox().sendKeys(quantity);
		        Thread.sleep(2000);
		        Utility.captureScreenshot(driver, "5 AddToCart " +search);
		        addcart.getAddToCartButton().click();

		        //6th step Click on Basket Tab
		        
		        addcart.getBasketTabButton().click();
		        String actualTitle = driver.getTitle();
			    String expectedTitle = "Shopping Cart";
			    Assert.assertEquals(actualTitle, expectedTitle);
			    Utility.captureScreenshot(driver, "6 ShoppingCart "+search);
		        	    //step 7 click on continue shopping
			    
			    shopping.getContinueShoppingbutton().click();
			    //8th step search and select product
			    
			    homepage.getYourStoreLogo().click();
				homepage.getSearchTextBox().sendKeys(searchagain);
				homepage.getSearchTextBox().sendKeys(Keys.ENTER);
				Utility.captureScreenshot(driver, "8 Searched "+search);
		        System.out.println("Searched "+search);   
		        //step 9 select the product
		        homepage.getSearchedProduct().click();
		        Utility.captureScreenshot(driver, "9 ProductClicked "+search);
		        
		        //step 10 select quantity and click on add to cart
		        addcart.getAddToCartQuantityTextbox().clear();
		        addcart.getAddToCartQuantityTextbox().sendKeys(shoppingquantity);
		        Thread.sleep(2000);
		        Utility.captureScreenshot(driver, "10 AddToCart " +search);
		        addcart.getAddToCartButton().click();
		       //step11 click on BasketTab     
		        addcart.getBasketTabButton().click();
		        String actualTitle1 = driver.getTitle();
			    String expectedTitle1 = "Shopping Cart";
			    Assert.assertEquals(actualTitle1, expectedTitle1);
			    Utility.captureScreenshot(driver, "11 ShoppingCart "+search);
                shopping.getRemoveButton().click();
	}
    
	@Test(dataProvider="TC04")
	public void tc004_OrderQuantity(String search,String quantity,String shoppingquantity )throws InterruptedException
	{
		try {
		    //2st step
				login();
				Utility.captureScreenshot(driver, "2 Login");
		
	        //3rd step search product and press enter
			
			    homepage.getYourStoreLogo().click();
			    homepage.getSearchTextBox().sendKeys(search);
			    homepage.getSearchTextBox().sendKeys(Keys.ENTER);
		        Utility.captureScreenshot(driver, "3 Searched "+search);
		        System.out.println("Searched "+search);
		        if(search != "HP")
		        {
		    //4th step Click on the product 
		        
		        homepage.getSearchedProduct().click();
		        Utility.captureScreenshot(driver, "4 ProductClicked "+search);
		        
		    //5th step Enter Quantity and click on AddtoCart
		        
		        addcart.getAddToCartQuantityTextbox().clear();
		        addcart.getAddToCartQuantityTextbox().sendKeys(quantity);
		        Utility.captureScreenshot(driver, "5 AddToCart " +search);
		        addcart.getAddToCartButton().click();
		        
		        
		    //6th step Click on Basket Tab
		        
		        addcart.getBasketTabButton().click();
		        String actualTitle = driver.getTitle();
			    String expectedTitle = "Shopping Cart";
			    Assert.assertEquals(actualTitle, expectedTitle);
			    Utility.captureScreenshot(driver, "6 ShoppingCart "+search);
		        	    
		    //7th step Enter 2 in quantity field
			    shopping.getShoppingQuantityTextBox().clear();
			    shopping.getShoppingQuantityTextBox().sendKeys(shoppingquantity);
			    Utility.captureScreenshot(driver, "7 Quantity " +search);
		        
			//8th to 11th step    
			    shopping.getCheckoutButton().click();
			    Utility.captureScreenshot(driver, " 12 checkout " +search);
				Thread.sleep(2000);
				checkout.checkoutProcess();
			  }
			    		    
	        //12th step Logoff
			    logout();
		}
			    catch (Exception e) {
					e.printStackTrace();
				}
	}
	
	@Test
	public void tc05_BrowseCategory() throws InterruptedException
	{
		login();
		Utility.captureScreenshot(driver, "Login");
		Thread.sleep(2000);
		homepage.getTabletCategory().click();
		Thread.sleep(3000);
		Utility.captureScreenshot(driver, "tableclick");
		Assert.assertTrue(homepage.getSearchContentPane().isDisplayed(), "Search content not displayed!");		
		homepage.getCameraCategory().click();
		Utility.captureScreenshot(driver, "cameraclick");
		Thread.sleep(3000);
		Assert.assertTrue(homepage.getSearchContentPane().isDisplayed(), "Search content not displayed!");
		homepage.getMp3Category().click();
		Utility.captureScreenshot(driver, "mp3click");
		Thread.sleep(3000);
		Assert.assertTrue(homepage.getSearchContentPane().isDisplayed(), "Search content not displayed!");
		//6th step logout
		logout();
		
	}
	
	@Test(dataProvider="TC06")
	public void tc006_AdditionalImage(String producttosearch) throws InterruptedException
	
	{
		try {
			//2st step
			login();
			Utility.captureScreenshot(driver, "2 Login");
	
           //3rd step search product and press enter
		
			homepage.getYourStoreLogo().click();
			homepage.getSearchTextBox().sendKeys(producttosearch);
			homepage.getSearchTextBox().sendKeys(Keys.ENTER);
	        Utility.captureScreenshot(driver, "3 Searched "+producttosearch);
	        System.out.println("Searched "+producttosearch);
	        
	       //4th step Click on the product 
	        
	        homepage.getSearchedProduct().click();
	        Utility.captureScreenshot(driver, "4 ProductClicked "+producttosearch);
	        
	        //5th Step Verify Description
	        addcart.getDescription().isDisplayed();
	   
	        java.util.List<WebElement>  numberOfProducts= addcart.getAdditionImageTab();
	        
	        //numberOfProducts.remove(0);
	        
	        for(WebElement we : numberOfProducts)
	        {
	        	String prod = we.getAttribute("src");
	        	int i = (prod).lastIndexOf("/");
	        	String FileName = prod.substring(i+1);
	        	System.out.println(FileName);
	        	//we.getAttribute("innerHTML");
	        }
		}
		    catch (Exception e) {
				e.printStackTrace();
			}
		//7th step logout
		logout();

	}
	
	
	@Test(dataProvider="TC07")
	public void tc007_EditAccountInfo(String telephonenumber) throws InterruptedException
	{
		try {
			
			//2st step
			login();
			
			//3rd step click edit your account
			
			account.getEditYouAccountInfoLink().click();
			Utility.captureScreenshot(driver, "3 edityouraccount");
			
			//4th step Change telephone number
			account.getTelephoneTextBox().clear();
			account.getTelephoneTextBox().sendKeys(telephonenumber);
			Utility.captureScreenshot(driver, "4 telephonenumber");
			
			//5th step click on continue
			
			account.getMyAccountContinueButton().click();
			Utility.captureScreenshot(driver, "5 Saved");
			
			//6th step logout
			logout();
			
		}
			catch (Exception e) {
				e.printStackTrace();
			}
			
	}
		

	@Test(dataProvider="TC08")
	public void tc008_SortByCombo( String firstdropdown,String seconddropdown) throws InterruptedException
	{
		try {
			//2st step
			login();
			//3rd step Select a category from categorically link
			WebElement link=homepage.getLaptopCatogery();
			Actions act= new Actions(driver);
			act.moveToElement(link).perform();
			homepage.getShowAllLaptop().click();
			Utility.captureScreenshot(driver, "3 SelectCatogery");
			
			//4th Step Select Sort By Combo Box (Name A-Z)
			homepage.selectSortDropdownAtoZ(firstdropdown);
			Utility.captureScreenshot(driver, "4 A-Z");
			Thread.sleep(5000);
			//5th Step Select Sort By Combo Box (Name Z-A)
			homepage.selectSortDropdownZtoA(seconddropdown);
			Utility.captureScreenshot(driver, "5 Z-A");
			Thread.sleep(5000);

		    //6th step logout
		    logout();
			
		}
			catch (Exception e) {
				e.printStackTrace();
			}	
	}
		
	
	@Test
	public void tc009_ShoppingEmptyCart() throws InterruptedException  
	{
		   //2st step
			login();
			
			//3rd step Click on "Check Out"Tab
			homepage.getCheckoutTabButton().click();
			String cartemptytxt=homepage.getCartEmptyText();
			String cartemptytxtexpected="Your shopping cart is empty!";
				 if (cartemptytxt.contains(cartemptytxtexpected))
				  {
					 Utility.captureScreenshot(driver, "3 EmptyCart");
					//4th step logout
						logout();
				  }
				  else
				  {
					  System.out.println("Didnt get Your shopping cart is empty text");
				  }					
	}	
	
	
	@Test(dataProvider="TC10")
	public void tc010_ChangeAddress(String searchproduct,String addcartquantity, String shoppingquantity, 
			String firstname,String lastname, String company, String address1,String address2,
			String city, String postcode, String country,String regionstate   )  
	{
		
		try {
			
			//2st step
			login();
			
			
			//3rd step search product and press enter
			homepage.getYourStoreLogo().click();
			homepage.getSearchTextBox().sendKeys(searchproduct);
			homepage.getSearchTextBox().sendKeys(Keys.ENTER);
		    Utility.captureScreenshot(driver, "3 Searched "+searchproduct);
		    System.out.println("Searched "+searchproduct);
		        
            //4th step Click on the product 
		        
		    homepage.getSearchedProduct().click();
		    Utility.captureScreenshot(driver, "4 ProductClicked "+searchproduct);
		        
		    //5th step Enter Quantity and click on AddtoCart
		      
		    addcart.getAddToCartQuantityTextbox().clear();
		    addcart.getAddToCartQuantityTextbox().sendKeys(addcartquantity);
		    Utility.captureScreenshot(driver, "5 AddToCart " +searchproduct);
		    addcart.getAddToCartButton().click();
		        
		   //6th step Click on Basket Tab
		        
		   addcart.getBasketTabButton().click();
		   String actualTitle = driver.getTitle();
		   String expectedTitle = "Shopping Cart";
		   Assert.assertEquals(actualTitle, expectedTitle);
		   Utility.captureScreenshot(driver, "6 ShoppingCart "+searchproduct);
		        	    
		   //7th step Enter 2 in quantity field
		   shopping.getShoppingQuantityTextBox().clear();
		   shopping.getShoppingQuantityTextBox().sendKeys(shoppingquantity);
		   Utility.captureScreenshot(driver, "7 Quantity " +searchproduct);
			    
	       //8th step click on checkout button 
			    
		    shopping.getCheckoutButton().click();
			Utility.captureScreenshot(driver, " 8 checkout " +searchproduct);
			    
	        //9th step click on change address radio button
			Thread.sleep(2000);
			checkout.getNewAddressRadioButton().click();
			Utility.captureScreenshot(driver, " 9 clicknewaddress ");
			  
	        //10th step enter address
			checkout.fillAddress( firstname, lastname, company, address1,  address2,  city, 
					postcode, country,  regionstate);
         	checkout.getContinueAddressButton().click();
			Utility.captureScreenshot(driver, "  11 address button ");
			Thread.sleep(2000);
			checkout.getContinueShippingButton().isDisplayed();
				 
			checkout.getContinueShippingButton().isEnabled();
			System.out.println("button is visible");
			Thread.sleep(2000);
			checkout.getContinueShippingButton().click();
			Thread.sleep(2000);
			Utility.captureScreenshot(driver, "  11 shipping button ");
			checkout.getContinueDeliveryButton().click();
			Utility.captureScreenshot(driver, "  11 delivery button ");
			Thread.sleep(2000);
				 
				 if ( checkout.getTermsAndConditionCheckbox().isSelected() )
				 {
				
					 System.out.println("plz select the terms and conditions");
				 }
				 else
				 {
					 checkout.getTermsAndConditionCheckbox().click();
					 Utility.captureScreenshot(driver, "  12terms and condition button ");
					 checkout.getContinuePaymentButton().click();
					 Utility.captureScreenshot(driver, " 12 payment button ");
					 
				 }
				 Thread.sleep(1000);
				 checkout.getConfirmOrderButton().click();
				 Thread.sleep(2000);
				 String orderplaced = driver.getTitle();
			      String orderplacedexpected = "Your order has been placed!";
			      if (orderplaced.contains(orderplacedexpected))
					  {
						 Utility.captureScreenshot(driver, "13 orderplaced");
						//4th step logout
							logout();
					  }
					  else
						  System.out.println("Order didnt get placed");

		       }
		     	catch (Exception e) {
				 System.out.println("Exception  " +e);
		            e.printStackTrace();
				
			}
			
	}	

	@DataProvider(name="TC02")
	public Object [][]getTC02Data()
	{
		Object data[][]= DataHandlers.getTestData("TC02");
		return data;
		
	}
	
	@DataProvider(name="TC03")
	public Object [][]getTC03Data()
	{
		Object data[][]= DataHandlers.getTestData("TC03");
		return data;
		
	}
	@DataProvider(name="TC04")
	public Object [][]getTC04Data()
	{
		Object data[][]= DataHandlers.getTestData("TC04");
		return data;
		
	}
	@DataProvider(name="TC06")
	public Object [][]getTC06Data()
	{
		Object data[][]= DataHandlers.getTestData("TC06");
		return data;
		
	}
	@DataProvider(name="TC07")
	public Object [][]getTC07Data()
	{
		Object data[][]= DataHandlers.getTestData("TC07");
		return data;
		
	}
	@DataProvider(name="TC08")
	public Object [][]getTC08Data()
	{
		Object data[][]= DataHandlers.getTestData("TC08");
		return data;
		
	}
	@DataProvider(name="TC10")
	public Object [][]getTC10Data()
	{
		Object data[][]= DataHandlers.getTestData("TC10");
		return data;
		
	}
	
	@AfterMethod
	public void postCondition()
	{
	
	driver.close();
	}
}