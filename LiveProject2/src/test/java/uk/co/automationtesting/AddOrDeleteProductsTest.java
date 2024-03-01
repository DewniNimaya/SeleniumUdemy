package uk.co.automationtesting;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BasePage;
import base.ExtentManager;
import base.Hooks;
import pageObjects.Homepage;
import pageObjects.ShopContentPanel;
import pageObjects.ShopHomePage;
import pageObjects.ShopProductPage;
import pageObjects.ShoppingCart;

//using the Listeners class
@Listeners(resources.Listeners.class)

public class AddOrDeleteProductsTest extends Hooks{

	public AddOrDeleteProductsTest() throws IOException {
		super();
		
	}

	@Test
	//implement methods in each individual page object classes to interact with the elements
	public void addOrDeleteProduct() throws IOException, InterruptedException {
	
	ExtentManager.log("Starting AddRemoveItemBasketTest...");
	//creating an object of home page and clicking test store link
	Homepage home = new Homepage();
	home.getCookie().click();
	home.getTestStoreLink().click();
	
	//creating an object of shop page and cliking a product
	ShopHomePage shop = new ShopHomePage();
	ExtentManager.pass("Reached the shop homepage");
	shop.getProdOne().click();

	//creating an object from selected product, changing size and quantity and clicking add to cart button
	ShopProductPage product = new ShopProductPage();
	ExtentManager.pass("Reached the shop product page");
	Select option = new Select(product.getSizeOption());
	option.selectByVisibleText("M");
	ExtentManager.pass("Have successfully selected product size");
	product.getAddToCartBtn().click();
	ExtentManager.pass("Have successfully added product to basket");

	//creating an object from shop content panel and clicking continue shopping button
	ShopContentPanel panel = new ShopContentPanel();
	panel.getContinueShopBtn().click();
	
	//clicking home page link
	product.getHomepageLink().click();
	
	//clinkg another product
	shop.getProdTwo().click();
	product.getAddToCartBtn().click();
	panel.getCheckoutBtn().click();
	
	//deleting an item
	ShoppingCart cart = new ShoppingCart();
	cart.getDeleteItemTwo().click();
	//get the actual total amount after deleteing the second item
	WebDriverWait wait = new WebDriverWait(cart.driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.invisibilityOf(cart.getDeleteItemTwo()));
	//String actualAmount = cart.getTotalAmount().getText();
	//String expecAmount = "$26.1";
	
	//checking if the expected total amount is same as the actual total amount after deleting the product two 
//    try {
//        Assert.assertEquals(expecAmount, actualAmount);
//        System.out.println("Test case passed");
//    } catch (AssertionError e) {
//        System.out.println("Test case failed");
//        throw e;
//	
//    }
	try {
		// using an assertion to make sure the total amount is what we are expecting
		Assert.assertEquals(cart.getTotalAmount().getText(), "$26.1");
		ExtentManager.pass("The total amount matches the expected amount.");
	} catch (AssertionError e) {
		Assert.fail("Cart amount did not match the expected amount, it found" + cart.getTotalAmount().getText() +
				" expected $26.1");
		ExtentManager.fail("The total amount did not match the expected amount.");
	}
	
	}
	

}
