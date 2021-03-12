package org.automation.pagefactory;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    WebDriver driver;
    private WebDriverWait wait;
    //elements to be used
    @FindBy(xpath="//h2[@data-hook='cart-title']")
    WebElement header;
    
    
    
    @FindBy(xpath="//iframe[@title='Cart Page']")
    WebElement frame;
    
    @FindBy(css="dd#total-sum")
    WebElement totalPrice;
    

    @FindBy(xpath="//button[@data-hook='checkout-button-button']")
    WebElement checkoutButton;
    
    /**
     * Load the driver in page factory
     * and said the webdriver wait
     * @param driver
     */
    public CartPage(WebDriver driver){

        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);

    }   

    /**
     *Validate on the total price if equal to the totalAmount
     * switching frame is need to interact with the field
     * @param totalAmount
     * @return
     */
    public boolean validateTotalPrice(String totalAmount){
    
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    	
    	driver.switchTo().frame(frame);
    	
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("dd#total-sum")));
    	
    	String value=totalPrice.getText();
    	
    	driver.switchTo().defaultContent();
    	
    	return value.equals(totalAmount);

    }	
    

    
    /**
     * click on checkout button, switching frame is needed here
     */
    public void clickOnCheckoutButton(){
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    	driver.switchTo().frame(frame);
    	checkoutButton.click();
    	driver.switchTo().defaultContent();

    }	

}