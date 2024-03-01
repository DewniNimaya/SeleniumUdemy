package pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class OderConfirm extends BasePage{
	
	public WebDriver driver;
	
	//locator of oder confirmation message
	By expectext =  By.xpath("//section[@id='content-hook_order_confirmation']//h3[contains(@class, 'card-title')]");
	

	public OderConfirm() throws IOException {
		super();
	}
	
	public WebElement getExpec() throws IOException {
		this.driver = getDriver();
		return driver.findElement(expectext);
	}
	


}

