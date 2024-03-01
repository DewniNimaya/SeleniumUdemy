package uk.co.automationtesting;

import java.io.IOException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BasePage;
import base.ExtentManager;
import base.Hooks;
import pageObjects.Homepage;
import pageObjects.OderConfirm;
import pageObjects.OrderAddress;
import pageObjects.OrderPayment;
import pageObjects.OrderPersonalInfo;
import pageObjects.OrderShipping;
import pageObjects.ShopContentPanel;
import pageObjects.ShopHomePage;
import pageObjects.ShopProductPage;
import pageObjects.ShoppingCart;

//using the Listeners class
@Listeners(resources.Listeners.class)

public class OrderPlacedTest extends Hooks {

	public OrderPlacedTest() throws IOException {
		super();
		
	}

		@Test
		//implement methods in each individual page object classes to interact with the elements
		public void EndToEndTest() throws IOException, InterruptedException {
			
		ExtentManager.log("Starting OrderCompleteTest...");
			 
		//creating an object of home page and clicking test store link
		Homepage home = new Homepage();
		home.getCookie().click();
		JavascriptExecutor jse = (JavascriptExecutor)getDriver();
		jse.executeScript("arguments[0].scrollIntoView()", home.getTestStoreLink()); 
		home.getTestStoreLink().click();
		ExtentManager.pass("Have successfully reached store homepage");
		//creating an object of shop page and cliking a product
		ShopHomePage shop = new ShopHomePage();
		shop.getProdOne().click();
		ExtentManager.pass("Have successfully clicked on product");
	
		//creating an object from selected product, changing size and quantity and clicking add to cart button
		ShopProductPage product = new ShopProductPage();
		ExtentManager.pass("Have successfully reached shop product page");
		Select option = new Select(product.getSizeOption());
		option.selectByVisibleText("M");
		ExtentManager.pass("Have successfully selected product size");
		product.getQuantIncrease().click();
		ExtentManager.pass("Have successfully increased quantity");
		product.getAddToCartBtn().click();
		ExtentManager.pass("Have successfully added item to cart");
	
		//creating an object from shop content panel and clicking proceed to  checkout
		ShopContentPanel panel = new ShopContentPanel();
		panel.getCheckoutBtn().click();
		
		//creating an object from shopping cart panel, adding a promo code and clicking proceed to checkout 
		ShoppingCart cart = new ShoppingCart();
		ExtentManager.pass("Have successfully reached the shopping cart page");
		cart.getHavePromo().click();
		ExtentManager.pass("Have successfully selected the promo button");
		cart.getPromoTextbox().sendKeys("20OFF");
		cart.getPromoAddBtn().click();
		cart.getProceedCheckoutBtn().click();
		ExtentManager.pass("Have successfully selected the check out button");
		
		//creating an object from Order Personal Info, filling the personal info
		OrderPersonalInfo personal = new OrderPersonalInfo();
		personal.getGenderMr();
		personal.getFirstNameField().sendKeys("ann");
		personal.getLastnameField().sendKeys("perera");
		personal.getEmailField().sendKeys("ann@gmail.com");
		personal.getTermsConditionsCheckbox().click();
		Thread.sleep(2000);
		personal.getContinueBtn().click();
		ExtentManager.pass("Have successfully entered customer details");
		
		//creating an object from Order Address Info, filling the address info
		OrderAddress address = new OrderAddress();
		address.getAddressField().sendKeys("133 flower road");
		address.getCityField().sendKeys("pannipitya");
		Select state = new Select(address.getStateDropdown());
		state.selectByVisibleText("Alaska");
		address.getPostcodeField().sendKeys("11111");
		Thread.sleep(4000);
		address.getContinueBtn().click();
		ExtentManager.pass("Have successfully entered delivery info");
		
		//creating an object from Order Shipping Info, filling the delivery info
		OrderShipping ship = new OrderShipping();
		ship.getDeliveryMsgTextbox().sendKeys("If im not in home, please give my order to father");		
		ship.getContinueBtn().click();
		ExtentManager.pass("Have successfully selected the shipping method");
		
		//creating an object from Order Payment Info, filling the payment info
		OrderPayment pay = new OrderPayment();
		pay.getPayByCheckRadioBtn().click();
		pay.getTermsConditionsCheckbox().click();
		pay.getOrderBtn().click();
		ExtentManager.pass("Have successfully placed order");
		
		//checking if the order has placed successfully
		OderConfirm con = new OderConfirm();
		String actualText = con.getExpec().getText().replaceAll("[^a-zA-Z0-9]", " ").trim();
		String expecText = "YOUR ORDER IS CONFIRMED";
		
        try {
            Assert.assertEquals(expecText, actualText);
            System.out.println("Test case passed");
        } catch (AssertionError e) {
            System.out.println("Test case failed");
            throw e;
		
        }
		
	}
		
		
}
