package com.spm.ParkMe.acceptance;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DriverFreesParkingLotTest {
	
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
	public void driverFreesParkingLot() throws Exception {
		driver.get("http://localhost:4200/login");
		Thread.sleep(1000);
	    driver.findElement(By.id("inputEmail")).clear();
	    driver.findElement(By.id("inputEmail")).sendKeys("rocche@park.it");
	    Thread.sleep(1000);
	    driver.findElement(By.id("inputPassword")).clear();
	    driver.findElement(By.id("inputPassword")).sendKeys("Rocche");
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.linkText("Buy-Ticket")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("//div[@id='cardFunction']/span")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.id("manual")).click();
	    Thread.sleep(1000);
	    //Aldo Moro # 8 
	    driver.findElement(By.xpath("//div[4]/img[2]")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("//i")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("//button[@type='button']")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("//div[@id='buttonConfirm']/p")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.id("inputHour")).click();
	    new Select(driver.findElement(By.id("inputHour"))).selectByVisibleText("4");
	    Thread.sleep(1000);
	    driver.findElement(By.id("updateButton")).click();
	    Thread.sleep(20000);
	    driver.findElement(By.id("notificationIcon")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("//div/div/p[2]")).click();
	    Thread.sleep(2000);
	}

}
