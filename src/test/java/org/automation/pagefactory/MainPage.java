package org.automation.pagefactory;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

	/*
	 * List of components used in the page
	 * PageFactory concept is followed
	 * 
	 * 
	 */
    WebDriver driver;
    private WebDriverWait wait;
    
    @FindBy(xpath="//div[@data-testid='colorUnderlay']")
    WebElement container;
    
    @FindBy(xpath="//p[text()='Home']")
    WebElement homeIcon;
    
    
    @FindBy(xpath="//p[text()='Shop']")
    WebElement shopIcon;
    
    @FindBy(xpath="//a[@data-hook='cart-icon-button']")
    WebElement cartIcon;
    
    @FindBy(xpath="(//div[@data-hook='product-item-root'])[4]")
    WebElement product;
 
 
    /**
     * Load the driver in page factory
     * and said the webdriver wait
     * @param driver
     */
    public MainPage(WebDriver driver){ 
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);

    }   

    
    /**
     * Wait for the main container for the welcome page
     */
    public void waitForContainer(){

    	wait.until(ExpectedConditions.visibilityOf(container));

    }
    
    /**
     * click on shop icon in navagition bar
     */
    public void clickOnShopIcon(){ 
    	shopIcon.click();

    }
    /**
     * click on cart icon in navagition bar
     */
    public void clickOnCartIcon(){

    	cartIcon.click();

    }
    /**
     * click on desired product in the table
     */
    public void clickOnProduct(){

    	product.click();

    }

}