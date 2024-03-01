package uk.co.automationtesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.ExtentManager;
import base.Hooks;
import pageObjects.Homepage;
import pageObjects.ShopHomePage;
import pageObjects.ShopLogin;
import pageObjects.ShopYourAccount;


//using the Listeners class
@Listeners(resources.Listeners.class)

public class ShopLoginTest extends Hooks{

	public ShopLoginTest() throws IOException {
		super();
		
	}

	@Test
	//implement methods in each individual page object classes to interact with the elements
	public void shopLogin() throws IOException, InterruptedException {
	
	ExtentManager.log("Starting ShopLoginTest...");
	//creating an object of home page and clicking test store link
	Homepage home = new Homepage();
	home.getCookie().click();
	home.getTestStoreLink().click();
	
	//creating an object of shop page and cliking sign out link
	ShopHomePage shop = new ShopHomePage();
	shop.getLoginBtn().click();
	
	//accessing credentials workbook
	FileInputStream workbookLoc = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\credentials.xlsx");
	
	//using Apche POI class to access data in the workbook
	XSSFWorkbook workbookdata = new XSSFWorkbook(workbookLoc);
			
	//accessing the sheet within the workbook
	XSSFSheet sheet = workbookdata.getSheetAt(0);
	
	//accesing actual data within the sheet
	/****************************************************************************
	 * Excel Spreadsheet Layout Reminder (teaching purposes only)
	 * 
	 * |Row=0 -->| Email Address (Cell 0) Password (Cell 1) *
	 * -------------------------------------------------------------------- 
	 * |Row=1 -->| ktest@gmail.com (Cell 0) ktest (Cell 1) 
	 * |Row=2 -->| john.smith@test.com (Cell 0) test123 (Cell 1)
	 * |Row=3 -->| lucy.jones@test.com (Cell 0) catlover1 (Cell 1) 
	 * |Row=4 -->| martin.brian@test.com (Cell 0) ilovepasta5 (Cell 1) 
	 ****************************************************************************/
	
	Row row1 = sheet.getRow(1);
	Cell cellR1C0 = row1.getCell(0);
	Cell cellR1C1 = row1.getCell(1);
	
	String emailRow1 = cellR1C0.toString();
	String passwordRow1 = cellR1C1.toString();
	
	System.out.println(emailRow1);
	System.out.println(passwordRow1);
	
	//passing data to the email and password fields and login to the application
	ShopLogin shopL = new ShopLogin();
	shopL.getEmail().sendKeys(emailRow1);
	shopL.getPassword().sendKeys(passwordRow1);
	shopL.getSubmitBtn().click();
	
	//clicking signout
	ShopYourAccount shopA = new ShopYourAccount();
	
	//logging a testcase pass message
	try {
		
		shopA.getSignOutBtn().click();
		ExtentManager.pass("user has signed in successfully");
	}catch(Exception e) {
		
		ExtentManager.pass("user could not signed in");
		Assert.fail();
	}
	
	
	//accessing and passing second dataset
	Row row2 = sheet.getRow(2);
	Cell cellR2C0 = row2.getCell(0);
	Cell cellR2C1 = row2.getCell(1);
	
	String emailRow2 = cellR2C0.toString();
	String passwordRow2 = cellR2C1.toString();
	
	shopL.getEmail().sendKeys(emailRow2);
	shopL.getPassword().sendKeys(passwordRow2);
	shopL.getSubmitBtn().click();
	
	try {
		
		shopA.getSignOutBtn().click();
		ExtentManager.pass("user has signed in successfully");
	}catch(Exception e) {
		
		ExtentManager.pass("user could not signed in");
		Assert.fail();
	}
	}
	

}
