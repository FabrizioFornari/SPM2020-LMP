package com.spm.ParkMe.acceptance;

import static org.junit.Assert.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class RefreshTicketTest {
	
	private static WebDriver driver;

	private static StringBuffer verificationErrors = new StringBuffer();
	
	static String projectPath;
	
	private static int waiting = 1500;
	private static int mediumWaiting = 2000;
	private static int notificationShortWait = 15000;
	private static int notificationLongWait = 25000;
	
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
	
	@Disabled
	@Test
	@Tag("AcceptanceTest")
	public void refreshTicket() throws Exception {
		driver.get("http://localhost:4200/login");
		Thread.sleep(waiting);
	    driver.findElement(By.id("inputEmail")).clear();
	    driver.findElement(By.id("inputEmail")).sendKeys("rocche@park.it");
	    Thread.sleep(waiting);
	    driver.findElement(By.id("inputPassword")).clear();
	    driver.findElement(By.id("inputPassword")).sendKeys("Rocche");
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.linkText("Buy-Ticket")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("//div[@id='cardFunction']/span")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.id("automatic")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("//app-map/div")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("//button[@type='button']")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("//button[@type='button']")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("//div[@id='buttonConfirm']/p")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.id("inputHour")).click();
	    Thread.sleep(waiting);
	    new Select(driver.findElement(By.id("inputHour"))).selectByVisibleText("1");
	    Thread.sleep(waiting);
	    driver.findElement(By.id("updateButton")).click();
	    Thread.sleep(12000);
	    driver.findElement(By.id("notificationIcon")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("//div/div/p[2]")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("//button[@type='button']")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.id("inputHour")).click();
	    Thread.sleep(waiting);
	    new Select(driver.findElement(By.id("inputHour"))).selectByVisibleText("1");
	    Thread.sleep(waiting);
	    driver.findElement(By.id("updateButton")).click();
	    Thread.sleep(mediumWaiting);
	}

}
