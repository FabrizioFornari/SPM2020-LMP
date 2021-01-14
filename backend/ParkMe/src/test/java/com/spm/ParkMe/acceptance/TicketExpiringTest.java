package com.spm.ParkMe.acceptance;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TicketExpiringTest {


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
	    driver.manage().window().maximize();
	    //250ms frequency of pulling
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
	 public void testUntitledTestCase() throws Exception {
		    driver.get("http://localhost:4200/login");
		    Thread.sleep(waiting);
		    driver.findElement(By.id("inputEmail")).sendKeys("rocche@park.it");
		    Thread.sleep(waiting);
		    driver.findElement(By.id("inputPassword")).sendKeys("Rocche");
		    Thread.sleep(waiting);
		    driver.findElement(By.id("inputPassword")).sendKeys(Keys.ENTER);
		    WebDriverWait wait = new WebDriverWait(driver, 3);
		    wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return window.localStorage.getItem(\"token\")") != null); 
		    assertEquals("ParkMe | Profile", driver.getTitle());
		    driver.findElement(By.linkText("Buy-Ticket")).click();
		    Thread.sleep(waiting);
		    assertEquals("ParkMe | Buy Ticket", driver.getTitle());
		    driver.findElement(By.xpath("//div[@id='cardFunction']/h5")).click();
		    Thread.sleep(waiting);
		    driver.findElement(By.id("manual")).click();
		    Thread.sleep(waiting);
		    driver.findElement(By.xpath("//div[4]/img")).click();
		    Thread.sleep(waiting);
		    driver.findElement(By.xpath("//div[4]/i")).click();
		    Thread.sleep(waiting);
		    driver.findElement(By.xpath("//button[@type='button']")).click();
		    Thread.sleep(waiting);
		    driver.findElement(By.xpath("//div[@id='buttonConfirm']/p")).click();
		    Thread.sleep(waiting);
		    driver.findElement(By.id("inputHour")).click();
		    Thread.sleep(waiting);
		    new Select(driver.findElement(By.id("inputHour"))).selectByVisibleText("0.005");
		    Thread.sleep(waiting);
		    driver.findElement(By.id("updateButton")).click();
		    Thread.sleep(11000);
		    driver.findElement(By.id("notificationIcon")).click();
		    Thread.sleep(waiting);
		    driver.findElement(By.xpath("//div/div/p[2]")).click();
		    Thread.sleep(waiting);
		    driver.findElement(By.xpath("//app-check-park/div/p[2]")).click();
		    Thread.sleep(waiting);
		    driver.findElement(By.linkText("exit_to_app Logout")).click();
		    assertEquals("ParkMe | Login", driver.getTitle());
		    Thread.sleep(waiting);
		    driver.findElement(By.id("inputEmail")).sendKeys("cret@park.it");
		    Thread.sleep(waiting);
		    driver.findElement(By.id("inputPassword")).sendKeys("Cret");
		    Thread.sleep(waiting);
		    driver.findElement(By.id("inputPassword")).sendKeys(Keys.ENTER);
		    wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return window.localStorage.getItem(\"token\")") != null); 
		    assertEquals("ParkMe | Profile", driver.getTitle());
		    Thread.sleep(notificationLongWait);
		    driver.findElement(By.id("notificationIcon")).click();
		    Thread.sleep(waiting);
		    driver.findElement(By.xpath("//div[2]/div/div/div/p[2]")).click();
		    Thread.sleep(waiting);
		    driver.findElement(By.xpath("//button[@type='button']")).click();
		    Thread.sleep(waiting);
		    driver.findElement(By.xpath("//app-park-info/div/p[2]")).click();
		  }
	
}
