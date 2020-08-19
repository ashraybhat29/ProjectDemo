package com.realsteel.casestudy.testng;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.csvreader.CsvWriter;
import com.realsteel.casestudy.lib.ui.AddCart;
import com.realsteel.casestudy.lib.ui.Category;
import com.realsteel.casestudy.lib.ui.Checkout;
import com.realsteel.casestudy.lib.ui.ContactUs;
import com.realsteel.casestudy.lib.ui.CurrenncyChange;
import com.realsteel.casestudy.lib.ui.HomePage;
import com.realsteel.casestudy.lib.ui.LoginPage;
import com.realsteel.casestudy.lib.ui.MyAccount;
import com.realsteel.casestudy.lib.ui.OrderHistory;
import com.realsteel.casestudy.lib.ui.ProductCompare;
import com.realsteel.casestudy.lib.ui.RegisterAccount;
import com.realsteel.casestudy.lib.ui.SamsungTab;
import com.realsteel.casestudy.lib.ui.ShoppingCart;
import com.realsteel.casestudy.lib.ui.WishList;
import com.realsteel.casestudy.lib.util.DataHandlers;
import com.realsteel.casestudy.lib.util.Utility;
import com.realsteel.test.config.CreateDrivers;

public class TestNG_Open_Cart_5_1_2 {
	String outputFile = DataHandlers.outputPath + "\\TC10_TotalTimeTakenByEachBrowser.csv";
	CsvWriter csvOutput = null;
	WebDriver driver;
	LoginPage login;
	MyAccount account;
	AddCart addcart;
	Category category;
	ShoppingCart shopping;
	HomePage homepage;
	Checkout checkout;
	ContactUs contact;
	RegisterAccount register;
	SamsungTab samsung;
	WishList wish;
	CurrenncyChange cc;
	ProductCompare pc;
	OrderHistory order;

	// Valid username and password
	String validusername = DataHandlers.getDataFromExcel("data", "TC01", 3, 0);
	String validpassword = DataHandlers.getDataFromExcel("data", "TC01", 3, 1);

	@BeforeMethod
	public void precondition() {
		driver = CreateDrivers.getDriverInstance();
		login = new LoginPage(driver);
		account = new MyAccount(driver);
		addcart = new AddCart(driver);
		category = new Category(driver);
		shopping = new ShoppingCart(driver);
		homepage = new HomePage(driver);
		checkout = new Checkout(driver);
		contact = new ContactUs(driver);
		register = new RegisterAccount(driver);
		samsung = new SamsungTab(driver);
		wish = new WishList(driver);
		cc = new CurrenncyChange(driver);
		pc = new ProductCompare(driver);
		order = new OrderHistory(driver);
		// wait for login page
		login.waitForLoginPageToLoad();
		// get title and verify
		Utility.captureScreenshot(driver, "Browser Started");
	}

	public void login() {
		// clicking on Login button
		login.getMyAccountButton().click();
		login.getMyAccountLoginButton().click();
		Utility.captureScreenshot(driver, "Login Page");
		// Entering Valid Username Password

		login.getUsernameTestbox().sendKeys(validusername);
		login.getPasswordTextbox().sendKeys(validpassword);
		login.getLoginButton().click();
	}

	public void logout() throws InterruptedException {
		Thread.sleep(3000);
		login.getMyAccountButton().click();
		login.getLogoutButton().click();
		Utility.captureScreenshot(driver, "Logout");
		System.out.println("Logged out successfull");
	}

	@Test(dataProvider = "TC01")
	public void tc_01(String firstname, String lastname, String email, String telephone, String createpassword,
			String contactname, String contactemail, String contactenquiry, String rating, String review)
			throws InterruptedException, IOException {
		shopping.clearCart();

		register.createAccount(firstname, lastname, email, telephone, createpassword);

		contact.contactUs(contactname, contactemail, contactenquiry);

		homepage.getTabletCategory().click();

		samsung.reviewProduct(review, rating);

		wish.wishlish();

		cc.currencyPound();

		cc.currencyEuro();

		cc.currencyDollar();

		wish.getAddToCartWishButton().click();

		Thread.sleep(5000);

		wish.getCloseSuccessMsg().click();

		Thread.sleep(5000);

		wish.getRemoveWish().click();

		Thread.sleep(5000);

		String SuccessMessage = wish.getUpdateWishMsg().getText();

		String successMessageExpected = "Success: You have modified your wish list!";

		if (SuccessMessage.contains(successMessageExpected))

		{

			wish.getContinueWish().click();

		}

		logout();

	}

	@Test

	public void tc_02() throws InterruptedException, IOException {

		login();

		shopping.clearCart();

		homepage.getPhonesAndPDAs().click();

		pc.sortingProducts();

		pc.addToCompare();

		addcart.getAddToCartButton().click();

		addcart.getAddToCartSuccessClose().click();

		Thread.sleep(2000);

		homepage.getSpecialFooterLink().click();

		order.Subscribe();

		logout();

	}

	@Test

	public void tc_03()

	{

		System.out.println("Not present in excel");

	}

	@Test(dataProvider = "TC04")

	public void tc_04(String search, String quantity) throws InterruptedException, IOException

	{

		login();

		shopping.clearCart();

		homepage.getSearchTextBox().sendKeys(search);

		homepage.getSearchTextBox().sendKeys(Keys.ENTER);

		Utility.captureScreenshot(driver, "Searched " + search);

		System.out.println("Searched " + search);

		String searchedobject = "HP";

		if (search == searchedobject)

		{

			System.out.println("Search with HP Products, as other products are out of stock");

		}

		else

		{

			Thread.sleep(2000);

			// Image search and entering quantity

			Assert.assertTrue(homepage.getProductImage().isDisplayed(), "Displayed");

			homepage.getProductImage().click();

			Thread.sleep(2000);

			addcart.getAddToCartQuantityTextbox().clear();

			addcart.getAddToCartQuantityTextbox().sendKeys(quantity);

			// adding to cart

			addcart.getAddToCartButton().click();

			Thread.sleep(2000);

			addcart.getBasketTabButton().click();

			Thread.sleep(2000);

			// writting in file

			File file = new File("C:\\Users\\abhat68\\git\\Casestudy\\FLATFILEPATH");

			/*
			 * This logic will make sure that the file
			 * 
			 * gets created if it is not present at the
			 * 
			 * specified location
			 */

			if (!file.exists())

			{

				file.createNewFile();

			}

			FileWriter fw = new FileWriter(file);

			BufferedWriter bw = new BufferedWriter(fw);

			String totValue = shopping.getTotal().getText();

			bw.write(totValue);

			System.out.println("File written Successfully");

			bw.close();

			try

			{

				String totalValue = shopping.getTotal().getText();

				String tot = totalValue.substring(1, 4);

				System.out.println(tot);

				// checking for the total

				if (Integer.parseInt(tot) < 200)

				{

					shopping.getContinueShoppingbutton().click();

					Thread.sleep(3000);

				}

				else

				{

					logout();

				}

			}

			catch (Exception e) {

				e.printStackTrace();

			}

		}

	}

	@Test

	public void tc_05() throws InterruptedException

	{

		login();

		shopping.clearCart();

		homepage.getPhonesAndPDAs().click();

		homepage.getAddtoCart1().click();

		System.out.println("First product Added");

		Thread.sleep(3000);

		String PhoneName = homepage.getProductNameInSuccess().getText();

		String SuccessMessage = homepage.getShoppingCartSuccessMsg().getText();

		String successMessageExpected = "Success: You have added " + PhoneName + " to your shopping cart!";

		if (SuccessMessage.contains(successMessageExpected)) {

			System.out.println(PhoneName + " is added to the cart successfully.Click on "

					+ "Checkout button to proceed!");

			homepage.getAddtoCart2().click();

			System.out.println("Second product Added");

			Thread.sleep(3000);

			String PhoneName2 = homepage.getProductNameInSuccess().getText();

			String SuccessMessage2 = homepage.getShoppingCartSuccessMsg().getText();

			String successMessageExpected2 = "Success: You have added " + PhoneName2 + " to your shopping cart!";

			if (SuccessMessage2.contains(successMessageExpected2))

			{

				System.out.println(PhoneName2 + " is added to the cart successfully.Click on "

						+ "Checkout button to proceed!");

			}

			homepage.getAddtoCart3().click();

			System.out.println("Third product Added");

			Thread.sleep(3000);

			String PhoneName3 = homepage.getProductNameInSuccess().getText();

			String SuccessMessage3 = homepage.getShoppingCartSuccessMsg().getText();

			String successMessageExpected3 = "Success: You have added " + PhoneName2 + " to your shopping cart!";

			if (SuccessMessage3.contains(successMessageExpected3))

			{

				System.out.println(PhoneName3 + " is added to the cart successfully.Click on "

						+ "Checkout button to proceed!");

			}

		} else

			System.out.println(PhoneName + " is not added to the cart.Please select check");

		// Cannot automate other steps as we are getting error in checkout page due to
		// less stock of products.

		logout();

	}

	@Test

	public void tc_06() throws InterruptedException

	{

		login();

		List<WebElement> links = driver.findElements(By.tagName("a"));

		int totalLinks = links.size();

		System.out.println(" Total Links " + totalLinks);

		logout();

	}

	@Test(dataProvider = "TC07")

	public void tc_07(String name, String email, String enquiry) throws InterruptedException

	{

		login();

		homepage.getContactUs().click();

		contact.contactUs(name, email, enquiry);

		logout();

	}

	@Test

	public void tc_08()

	{

		System.out.println("Covered in Jqueryui project");

	}

	@Test

	public void tc_09() throws InterruptedException

	{

		login();

		shopping.clearCart();

		homepage.getPhonesAndPDAs().click();

		homepage.getAddtoCart1().click();

		System.out.println("First product Added");

		Thread.sleep(3000);

		String PhoneName = homepage.getProductNameInSuccess().getText();

		String SuccessMessage = homepage.getShoppingCartSuccessMsg().getText();

		String successMessageExpected = "Success: You have added " + PhoneName + " to your shopping cart!";

		if (SuccessMessage.contains(successMessageExpected)) {

			System.out.println(PhoneName + " is added to the cart successfully.Click on Checkout button to proceed!");

		} else

			System.out.println(PhoneName + " is not added to the cart.Please select check");

		// Cannot automate other steps as we are getting error in checkout page due to
		// less stock of products.

		logout();

	}

	@Test

	public void tc_10()

	{

		System.out.println("TestCase 10 is covered in another class. TC10_CrossBrowser");

	}

	@Test

	public void tc_11() throws IOException, InterruptedException

	{

		// 2st step

		login();

		homepage.getPhonesAndPDAs().click();

		Select select = new Select(homepage.getSortByDropdown());

		List<WebElement> dd = select.getOptions();

		System.out.println(dd.size());

		String outputFile = DataHandlers.outputPath + "\\TC11_test.csv";

		CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');

		for (int j = 0; j < dd.size(); j++) {

			System.out.println(dd.get(j).getText());

			csvOutput.write(dd.get(j).getText());

		}

		csvOutput.endRecord();

		csvOutput.close();

		System.out.println("CSV file has been created Successfully \n File path " + outputFile);

		// step 7

		logout();

	}

	@Test

	public void tc_12()

	{

		System.out.println("http://seleniumhq.org is changed to http://seleniumhq.dev and "

				+ "this website is not openeing in topgearvdi");

	}

	@Test

	public void tc_13() throws InterruptedException {

		// 2st step

		login();

		homepage.getPhonesAndPDAs().click();

		homepage.geDesktops().click();

		try {

			driver.navigate().back();

			driver.navigate().forward();

		} catch (Exception e) {

			e.printStackTrace();

		}

		// Step 8

		logout();

	}

	@DataProvider(name = "TC01")

	public Object[][] getTC01Data() throws InvalidFormatException, IOException {

		Object data[][] = DataHandlers.getTestData2("TC001");

		return data;

	}

	@DataProvider(name = "TC04")

	public Object[][] getTC04Data() throws InvalidFormatException, IOException {

		Object data[][] = DataHandlers.getTestData2("TC04");

		return data;

	}

	@DataProvider(name = "TC07")

	public Object[][] getTC07Data() throws InvalidFormatException, IOException {

		Object data[][] = DataHandlers.getTestData2("TC07");

		return data;

	}

	@AfterMethod

	public void postCondition()

	{

		driver.close();

	}

}