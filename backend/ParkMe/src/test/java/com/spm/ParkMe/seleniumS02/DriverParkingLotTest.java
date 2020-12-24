package com.spm.ParkMe.seleniumS02;

import static org.junit.Assert.fail;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DriverParkingLotTest {
	
	private static WebDriver driver;

	private static StringBuffer verificationErrors = new StringBuffer();

	private static String projectPath;

    
	
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
	@Disabled
	public void bookAndCancelParkingLotProcess() throws Exception {
		driver.get("http://localhost:4200/login");
	    driver.findElement(By.xpath("//label")).click();
	    driver.findElement(By.id("inputEmail")).clear();
	    driver.findElement(By.id("inputEmail")).sendKeys("rocche@park.it");
	    Thread.sleep(2000);
	    driver.findElement(By.id("inputPassword")).clear();
	    driver.findElement(By.id("inputPassword")).sendKeys("Rocche");
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//button[@type='submit']")).click();

	    WebDriverWait wait = new WebDriverWait(driver, 3);
	    wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return window.localStorage.getItem(\"token\")") != null);
	    driver.get("http://localhost:4200/ticket-list");
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//div/div")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//div[4]/img")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//div[5]/i")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//div/div")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//app-map/div")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
	    Thread.sleep(3000);
  }
	
	@Test
	@Order(2)
	@Disabled
	public void bookAndBuyTicket() throws Exception {
		driver.get("http://localhost:4200/login");
	    driver.findElement(By.xpath("//label")).click();
	    driver.findElement(By.id("inputEmail")).clear();
	    driver.findElement(By.id("inputEmail")).sendKeys("rocche@park.it");
	    Thread.sleep(2000);
	    driver.findElement(By.id("inputPassword")).clear();
	    driver.findElement(By.id("inputPassword")).sendKeys("Rocche");
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//button[@type='submit']")).click();

	    WebDriverWait wait = new WebDriverWait(driver, 3);
	    wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return window.localStorage.getItem(\"token\")") != null);
	    driver.get("http://localhost:4200/ticket-list");
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//div/div")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//app-map/div")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.id("inputHour")).click();
	    Thread.sleep(2000);
	    new Select(driver.findElement(By.id("inputHour"))).selectByVisibleText("4");
	    Thread.sleep(2000);
	    driver.findElement(By.id("inputHour")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.id("updateButton")).click();
	    Thread.sleep(3000);
  }

}
