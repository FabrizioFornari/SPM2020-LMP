package com.spm.ParkMe.acceptance;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;



@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TicketExpiringTest {


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
	}

	@AfterEach
	void tearDown() throws Exception {
		driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	}

	 public void testUntitledTestCase() throws Exception {
		    driver.get("http://localhost:4200/login");
		    driver.findElement(By.xpath("//label")).click();
		    driver.findElement(By.id("inputEmail")).clear();
		    driver.findElement(By.id("inputEmail")).sendKeys("rocche@park.it");
		    driver.findElement(By.id("inputPassword")).clear();
		    driver.findElement(By.id("inputPassword")).sendKeys("Rocche");
		    driver.findElement(By.id("inputPassword")).sendKeys(Keys.ENTER);
		    driver.findElement(By.linkText("Buy-Ticket")).click();
		    driver.findElement(By.xpath("//div[@id='cardFunction']/h5")).click();
		    driver.findElement(By.id("manual")).click();
		    driver.findElement(By.xpath("//div[4]/img[2]")).click();
		    driver.findElement(By.xpath("//div[4]/div[3]/div")).click();
		    driver.findElement(By.xpath("//button[@type='button']")).click();
		    driver.findElement(By.xpath("//div[@id='buttonConfirm']/p")).click();
		    driver.findElement(By.id("inputHour")).click();
		    new Select(driver.findElement(By.id("inputHour"))).selectByVisibleText("0.005");
		    driver.findElement(By.id("updateButton")).click();
		    driver.findElement(By.id("notificationIcon")).click();
		    driver.findElement(By.xpath("//div/div/p[2]")).click();
		    driver.findElement(By.xpath("//app-check-park/div/p[2]")).click();
		    driver.findElement(By.linkText("exit_to_app Logout")).click();
		    driver.findElement(By.xpath("//label")).click();
		    driver.findElement(By.id("inputEmail")).clear();
		    driver.findElement(By.id("inputEmail")).sendKeys("cret@park.it");
		    driver.findElement(By.id("inputPassword")).clear();
		    driver.findElement(By.id("inputPassword")).sendKeys("Cret");
		    driver.findElement(By.id("inputPassword")).sendKeys(Keys.ENTER);
		    driver.findElement(By.id("notificationIcon")).click();
		    driver.findElement(By.xpath("//div[2]/div/div/div/p[2]")).click();
		    driver.findElement(By.xpath("//button[@type='button']")).click();
		    driver.findElement(By.xpath("//app-park-info/div/p[2]")).click();
		  }
	
}
