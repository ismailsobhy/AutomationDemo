package org.automation.pagefactory;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
    WebDriver driver;
    private WebDriverWait wait;
    //JavascriptExecutor will be use in some clicks
	private JavascriptExecutor js;
	//actions is used for key clicks
	Actions actions;
	
	//list of elements to be used
	@FindBy(xpath="//iframe[contains(@name,'tpapopup')")
    WebElement frame;
	
	By pageTitle=By.xpath("//div[@data-hook='breadcrumbs']/a[contains(text(),'product')]");
    @FindBy(xpath="//dd[@data-hook='estimated-total']")
    WebElement totalPrice;
    
    @FindBy(xpath="//input[@type='radio' and @aria-label='Black']")
    WebElement blackColorRadio;
    
    @FindBy(xpath="//input[@type='radio' and @aria-label='Brown']")
    WebElement brownColorRadio;
    
    @FindBy(xpath="//span[@data-hook='number-input-spinner-up-arrow']")
    WebElement increaseQuantityArrowButton;
    
    @FindBy(xpath="//span[@data-hook='number-input-spinner-down-arrow']")
    WebElement decreaseQuantityArrowButton;
    
    
    @FindBy(xpath="//input[@data-hook='number-input-spinner-input']")
    WebElement quantityField;
    
    @FindBy(xpath="//button[@data-hook='add-to-cart']")
    WebElement addToCartButton;
    
    
    By viewCartLocator=By.cssSelector("a#widget-view-cart-button");
    
    @FindBy(xpath="//input[@data-hook='product-quantity-input']")
    WebElement cartQuantityField;
    
    
    /**
     * Load the driver in page factory
     * and said the webdriver wait
     * @param driver
     */
    public ProductPage(WebDriver driver){

        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.actions=new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);

    }   

    /**
     * set color to brown
     * a wait has to be included until the locator is loaded
     */
    public void setColorToBrown(){
    	
    	wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
    	this.js.executeScript("arguments[0].click()",brownColorRadio);
    	
    	
    }
    /**
     * set color to black
     * a wait has to be included until the locator is loaded
     */
    public void setColorToBlack(){
    	
    	wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
    	this.js.executeScript("arguments[0].click()",blackColorRadio);
    	
    	
    }
    
    
    /**
     * Increase quantity by pressing up 
     */
    public void increaseQuantity(){
    		
    	
    	actions.sendKeys(quantityField, Keys.ARROW_UP).perform();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }
    
  
    

    
    
    /**
     * Click on add to carton button,
     * in the event the view cart button appears
     * also click it
     */
    public void clickOnAddToCartButton(){

    	//addToCartButton.click();
    	this.js.executeScript("arguments[0].click()",addToCartButton);
    	
    	try {
    		
    		clickOnViewCartButton();
        		
    		
    	}catch(Exception e) {
    		
    	
    	}
    }	
    
    /**
     * in some instances, the view cart button appears
     *
     * 
     */
    public void clickOnViewCartButton(){
    	//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    	//driver.switchTo().frame(frame);
    	wait.until(ExpectedConditions.elementToBeClickable(viewCartLocator));
	    WebElement element=driver.findElement(viewCartLocator);
	    element.click();
	   
	  
	    //driver.switchTo().defaultContent();
    }	

}