package com.spm.ParkMe.acceptance;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FinalTest {
	
	private static WebDriver driver;

	private static StringBuffer verificationErrors = new StringBuffer();
	
	private static int waiting = 1000;
	private static int mediumWaiting = 2000;
	private static int notificationShortWait = 10000;
	private static int notificationLongWait = 20000;
	
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
	@Disabled
	@Order(1)
	@Test
	@Tag("AcceptanceTest")
	  public void parkingManagerCreatesParkingLot() throws Exception {
		driver.get("http://apromore.unicam.it/SPM2020-LMP/#/login");
		Thread.sleep(waiting);
	    driver.findElement(By.id("inputEmail")).clear();
		Thread.sleep(waiting);
	    driver.findElement(By.id("inputEmail")).sendKeys("flash@park.it");
	    Thread.sleep(waiting);
	    driver.findElement(By.id("inputPassword")).clear();
	    Thread.sleep(waiting);
	    driver.findElement(By.id("inputPassword")).sendKeys("Flash");
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.linkText("Manager-Panel")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("(//div[@id='cardFunction']/h5)[2]")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.name("street")).clear();
	    Thread.sleep(waiting);
	    driver.findElement(By.name("street")).sendKeys("via Enrico Mestica");
	    Thread.sleep(waiting);
	    driver.findElement(By.name("numberOfParkingLot")).clear();
	    Thread.sleep(waiting);
	    driver.findElement(By.name("numberOfParkingLot")).sendKeys("1");
	    Thread.sleep(waiting);
	    driver.findElement(By.id("isHandicapParkingLot")).click();
	    Thread.sleep(waiting);
	    new Select(driver.findElement(By.id("isHandicapParkingLot"))).selectByVisibleText("False");
	    Thread.sleep(waiting);
	    driver.findElement(By.id("typeOfVehicle")).click();
	    Thread.sleep(waiting);
	    new Select(driver.findElement(By.id("typeOfVehicle"))).selectByVisibleText("4 Wheels Standard Vehicle");
	    Thread.sleep(waiting);
	    driver.findElement(By.name("latitude")).clear();
	    Thread.sleep(waiting);
	    driver.findElement(By.name("latitude")).sendKeys("43.142125727585054");
	    Thread.sleep(waiting);
	    driver.findElement(By.name("longitude")).clear();
	    Thread.sleep(waiting);
	    driver.findElement(By.name("longitude")).sendKeys("13.066980429376263");
	    Thread.sleep(waiting);
	    driver.findElement(By.id("updateButton")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("(//div[@id='cardFunction'])[3]")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("(//div[@id='cardFunctionPersonal']/h5)[3]")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("//p[2]")).click();
	    Thread.sleep(mediumWaiting);
	  }
	
	@Disabled
	@Order(2)
	@Test
	@Tag("AcceptanceTest")
	  public void driverChangeUserInfo() throws Exception {
			driver.get("http://apromore.unicam.it/SPM2020-LMP/#/login");
		 	Thread.sleep(waiting);
		    driver.findElement(By.id("inputEmail")).clear();
		    Thread.sleep(waiting);
		    driver.findElement(By.id("inputEmail")).sendKeys("rocche@park.it");
		    Thread.sleep(waiting);
		    driver.findElement(By.id("inputPassword")).clear();
		    Thread.sleep(waiting);
		    driver.findElement(By.id("inputPassword")).sendKeys("Rocche");
		    Thread.sleep(waiting);
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		    Thread.sleep(waiting);
		    driver.findElement(By.xpath("//div[6]/p[2]")).click();
		    Thread.sleep(waiting);
		    driver.findElement(By.name("newPlate")).clear();
		    Thread.sleep(waiting);
		    driver.findElement(By.name("newPlate")).sendKeys("AA555AA");
		    Thread.sleep(waiting);
		    driver.findElement(By.id("inputNewVehicle")).click();
		    Thread.sleep(waiting);
		    new Select(driver.findElement(By.id("inputNewVehicle"))).selectByVisibleText("4 Wheels Big Vehicle");
		    Thread.sleep(waiting);
		    driver.findElement(By.id("updateButton")).click();
		    Thread.sleep(waiting);
		    driver.findElement(By.id("inputEmail")).clear();
		    Thread.sleep(waiting);
		    driver.findElement(By.id("inputEmail")).sendKeys("rocche@park.it");
		    Thread.sleep(waiting);
		    driver.findElement(By.id("inputPassword")).clear();
		    Thread.sleep(waiting);
		    driver.findElement(By.id("inputPassword")).sendKeys("Rocche");
		    Thread.sleep(waiting);
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		    Thread.sleep(waiting);
		    driver.findElement(By.xpath("//div[9]/p[2]")).click();
		    Thread.sleep(waiting);
		    driver.findElement(By.name("newPhone")).clear();
		    Thread.sleep(waiting);
		    driver.findElement(By.name("newPhone")).sendKeys("+39 334 5555555");
		    Thread.sleep(waiting);
		    driver.findElement(By.id("updateButton")).click();
		    Thread.sleep(mediumWaiting);
	}
	@Disabled
	@Order(3)
	@Test
	@Tag("AcceptanceTest")
	  public void driverAsksForHandicapPermissions() throws Exception {
		driver.get("http://apromore.unicam.it/SPM2020-LMP/#/login");
		Thread.sleep(waiting);
	    driver.findElement(By.id("inputEmail")).clear();
	    Thread.sleep(waiting);
	    driver.findElement(By.id("inputEmail")).sendKeys("rocche@park.it");
	    Thread.sleep(waiting);
	    driver.findElement(By.id("inputPassword")).clear();
	    Thread.sleep(waiting);
	    driver.findElement(By.id("inputPassword")).sendKeys("Rocche");
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("//p[2]")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.id("updateButton")).click();
	    Thread.sleep(mediumWaiting);
	}
	
	@Order(4)
	@Test
	@Tag("AcceptanceTest")
	public void driverBooksParkingLotAndWaitForNotificationExpiringTicket() throws Exception {
		 driver.get("http://apromore.unicam.it/SPM2020-LMP/#/login");
		    Thread.sleep(waiting);
		    driver.findElement(By.id("inputEmail")).clear();
		    Thread.sleep(waiting);
		    driver.findElement(By.id("inputEmail")).sendKeys("rocche@park.it");
		    Thread.sleep(waiting);
		    driver.findElement(By.id("inputPassword")).clear();
		    Thread.sleep(waiting);
		    driver.findElement(By.id("inputPassword")).sendKeys("Rocche");
		    Thread.sleep(waiting);
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		    Thread.sleep(waiting);
		    driver.findElement(By.linkText("Buy-Ticket")).click();
		    Thread.sleep(waiting);
		    driver.findElement(By.xpath("//div[@id='cardFunction']/h5")).click();
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
		    new Select(driver.findElement(By.id("inputHour"))).selectByVisibleText("0.005");
		    Thread.sleep(waiting);
		    driver.findElement(By.id("updateButton")).click();
		    Thread.sleep(waiting);
		    Thread.sleep(notificationShortWait);
		    driver.findElement(By.id("notificationIcon")).click();
		    Thread.sleep(waiting);
		    driver.findElement(By.xpath("//div/div/p[2]")).click();
		    Thread.sleep(waiting);
		    driver.findElement(By.xpath("//app-check-park/div/p[2]")).click();
		    Thread.sleep(mediumWaiting);
	}
	
}
