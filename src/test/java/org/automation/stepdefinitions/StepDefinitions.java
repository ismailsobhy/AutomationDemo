package org.automation.stepdefinitions;


import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.automation.pagefactory.CartPage;
import org.automation.pagefactory.MainPage;
import org.automation.pagefactory.ProductPage;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import com.cucumber.listener.Reporter;
import com.google.common.io.Files;


import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


/**
 * @author Ismail.Sobhy
 *
 */
public class StepDefinitions {

	private WebDriver driver;
	//The implementation depends on page factory for the three pages to be interacted with
	MainPage mainPage;
	ProductPage productPage;
	CartPage cartPage;
	// baseURL for the website
	public static String baseURL = "https://arielkiell.wixsite.com/interview";

	/**
	 * Setup the driver and load the driver file
	 * all cookies are removed and browser is expanded to max
	 * Chrome driver only is supported
	 * Initialize page factory classes
	 */
	@Before
	public void setup(){

		System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.navigate().to(baseURL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	
		mainPage=new MainPage(driver);
		productPage=new ProductPage(driver);
		cartPage=new CartPage(driver);
		
	}
	
	/*
	 * check if user in the page
	 * The first thing it hits on the homepage
	 * the function for the action in in MainPage
	 */
	@Given("^user is on homepage$")
	public void userOnHomepage() {
		
		this.takeScreenshot("homepage");
		
		mainPage.waitForContainer();
	}
	
	/*
	 * User clicks on shop from nav bar
	 * and selects fourth product
	 * the function for the action in in MainPage
	 */
	@When("^user goes to shop and selects product$")
	public void userClicksOnShopAndGetsProduct() {

		mainPage.clickOnShopIcon();
		this.takeScreenshot("shop");
		
		mainPage.clickOnProduct();
			
		this.takeScreenshot("product");
		
	}
	/*
	 * The user increase the quantity of item
	 * after select black color
	 * the function for the action in in ProductPage
	 */
	@When("^user adds a black item to cart$")
	public void userPicksBlackItem() {

		productPage.setColorToBlack();
		productPage.increaseQuantity();
		this.takeScreenshot("increaseQuantity");
		
		
	}

	/*
	 * User clicks on add to cart
	 * the function for the action in in ProductPage
	 */
	@When("^user clicks on add to cart$")
	public void userClicksOnAddCart() {

		productPage.clickOnAddToCartButton();
			
		this.takeScreenshot("AddToCart");
		
	}

	/*
	 * verify on total price
	 * the function for the action in in CartPage
	 */
	@Then("^total price will be 75 canadian dollars$")
	public void verifyItemsType() {

	
		Assert.assertEquals(cartPage.validateTotalPrice("C$75.00"), true);
			
		this.takeScreenshot("totalPrice");
		
	}
	
	/*
	 * The use clicks on checkout
	 * the function for the action in in CartPage
	 */
	@And("^user will click on checkout$")
	public void userWillCheckOut() {

	
		cartPage.clickOnCheckoutButton();
		this.takeScreenshot("checkout");
	}
	

	/**
	 *The screenshot name is sent and screenshots are exported in
	 * test-outout/screenshots
	 * @param screenshotName
	 */
	public void takeScreenshot(String screenshotName) {
		screenshotName = screenshotName + System.currentTimeMillis() + ".png";
		try {

			// This takes a screenshot from the driver at save it to the specified location
			File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			// Building up the destination path for the screenshot to save
		
			File folderPath = new File(System.getProperty("user.dir") + "\\test-output\\Screenshots\\");
			folderPath.mkdir();

			File destinationPath = new File(
					System.getProperty("user.dir") + "\\test-output\\Screenshots\\" + screenshotName);

			// Copy taken screenshot from source location to destination location
			Files.copy(sourcePath, destinationPath);

		} catch (IOException e) {
			System.out.println(e.toString());
		}
	 
	}
	  

}
