package pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class ShopProductPage extends BasePage {

	public WebDriver driver;
	
	//locate the web elements
	By sizeOption = By.cssSelector("[data-product-attribute='1']");
	By quantityIncrease = By.cssSelector(".touchspin-up");
	By quantityDecrease = By.cssSelector(".touchspin-down");
	By addToCartBtn = By.cssSelector(".add-to-cart.btn.btn-primary");
	By homepageLink = By.xpath("//span[.='Home']");
	
	//return the values of above locators
	public ShopProductPage() throws IOException {
		super();
	}
	
	public WebElement getSizeOption() throws IOException {
		this.driver = getDriver();
		return driver.findElement(sizeOption);
	}
	
	public WebElement getQuantIncrease() throws IOException {
		this.driver = getDriver();
		return driver.findElement(quantityIncrease);
	}

	public WebElement getQuantDecrease() throws IOException {
		this.driver = getDriver();
		return driver.findElement(quantityDecrease);
	}
	
	public WebElement getAddToCartBtn() throws IOException {
		this.driver = getDriver();
		return driver.findElement(addToCartBtn);
	}
	
	public WebElement getHomepageLink() throws IOException {
		this.driver = getDriver();
		return driver.findElement(homepageLink);
	}
}
