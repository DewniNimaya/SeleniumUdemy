package pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class ShopLogin extends BasePage {

	public WebDriver driver;
	
	//find and locate the web elements
	By email = By.name("email");
	By password = By.name("password");
	By submitBtn = By.id("submit-login");
	
	public ShopLogin() throws IOException {
		super();
	}
	
	public WebElement getEmail() throws IOException {
		this.driver = getDriver();
		return driver.findElement(email);
	}
	
	public WebElement getPassword() throws IOException {
		this.driver = getDriver();
		return driver.findElement(password);
	}
	
	public WebElement getSubmitBtn() throws IOException {
		this.driver = getDriver();
		return driver.findElement(submitBtn);
	}
	


	
}
