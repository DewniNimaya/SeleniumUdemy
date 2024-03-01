package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	private String webUrl;
	private Properties prop;
	public static String screenShotDestinationPath;
	
	public BasePage() throws IOException {
		
		//using the properties file by initializing properties class and assigning to prop variable
	    prop = new Properties();
		
		//read the content of the file as a stream of bytes in Java
		FileInputStream data = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\config.properties");
		prop.load(data);
	}
	
	public static WebDriver getDriver() throws IOException {

		return WebDriverInstance.getDriver();
	}
	
	public String getUrl() throws IOException {
		
		//open the website
		webUrl = prop.getProperty("url");
		return webUrl;
	
	}
	
	//method to screenshot of the current webpage
	public  static String takeSnapshot(String name) throws IOException {
		
		File srcfile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		//defines the destination file path where the screenshot will be saved using the timestamp
		String destFile = System.getProperty("user.dir") + "\\target\\screenshots\\" + timeStamp() + ".png";
		
		//updates the screenShotDestinationPath variable with the path of the screenshot.
		screenShotDestinationPath = destFile;
		
		//copy the screenshot file to the destination. 
		try {
			FileUtils.copyFile(srcfile, new File(destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return name;
	}
	
	//method to generate a unique file name(ss) by appending current timestamp
	public static String timeStamp() {
		
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}
	
	//defines a method that returns the path of the screenshot destination.
	public static String getScreenshotDestinationPath() {
		return screenShotDestinationPath;
	}
	
	// waits until the element becomes invisible on the web page
	public static void waitForElementInvisible(WebElement element, Duration timer) throws IOException {
		WebDriverWait wait = new WebDriverWait(getDriver(), timer);
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

}
