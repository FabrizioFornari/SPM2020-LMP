package com.spm.ParkMe.acceptance;

import static org.junit.Assert.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;





public class DisablingParkingLotTest {

	private static WebDriver driver;

	private static StringBuffer verificationErrors = new StringBuffer();
	
	static String projectPath;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		projectPath = System.getProperty("user.dir");
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		String OS = System.getProperty("os.name");
		if(OS.equals("Mac OS X")) {
			System.setProperty("webdriver.chrome.driver", projectPath+"/drivers/mac/chromedriver");
		}
		if(OS.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath+"\\drivers\\windows\\chromedriver.exe");
		}
		if(OS.contains("nix") || OS.contains("nux") || OS.contains("aix")) {
			System.setProperty("webdriver.chrome.driver", projectPath+"/drivers/linux/chromedriver");
		}
		
		 
		
	    driver = new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    //250ms frequency of pulling
	    driver.manage().window().maximize();
	}

	@AfterEach
	void tearDown() throws Exception {
		driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	}
	
	@Test
	@Order(1)
	  public void disablingParkingLot() throws Exception {
	    driver.get("http://localhost:4200/login");
	    driver.findElement(By.xpath("//label")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.id("inputEmail")).clear();
	    Thread.sleep(1000);
	    driver.findElement(By.id("inputEmail")).sendKeys("cret@park.it");
	    Thread.sleep(1000);
	    driver.findElement(By.id("inputPassword")).clear();
	    Thread.sleep(1000);
	    driver.findElement(By.id("inputPassword")).sendKeys("Cret");
	    Thread.sleep(1000);
	    driver.findElement(By.id("inputPassword")).sendKeys(Keys.ENTER);
	    Thread.sleep(1000);
	    driver.findElement(By.linkText("Vigilant-Panel")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("//div[@id='cardFunction']/h5")).click(); 
	    Thread.sleep(1000);
	    driver.findElement(By.id("streetCardInfo")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("(//div[@id='streetCardInfo']/h5)[2]")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("//button[@type='button']")).click();
	    Thread.sleep(2000);
	  }
	  
	

}
