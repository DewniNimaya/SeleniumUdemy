package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

//This class  responsibles for initializing the driver
public class WebDriverInstance {
	
	public static ThreadLocal<WebDriver>  driver = new ThreadLocal<>();
	
	//checking if the thread is in use
	public static WebDriver getDriver() {
		
		if(driver.get() == null) {
			try {
				
				driver.set(createWebDriver());
				
			}catch(IOException e) {
				
				e.printStackTrace();
			}
		}
		return driver.get();
	}
	
	//initializing the driver object
	public static WebDriver createWebDriver() throws IOException {
		
		WebDriver driver = null;
		
		//using the properties file by initializing properties class
		Properties prop = new Properties();
		
		//read the content of the file as a stream of bytes in Java
		FileInputStream data = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\config.properties");
		prop.load(data);
		
		//handle driver initialization for each browser
		if(prop.getProperty("browser").equals("chrome")) {
			
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\java\\drivers\\chromedriver.exe");
			driver = new ChromeDriver(); 
			
		}else{
			
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\src\\main\\java\\drivers\\msedgedriver.exe");
			driver = new EdgeDriver(); 			
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		return driver;
	}
	
	//cleaning up the session and removing variables from the Thread
	public static void cleanUpDriver() {
		
		driver.get().quit();
		driver.remove();
	}
	

}
