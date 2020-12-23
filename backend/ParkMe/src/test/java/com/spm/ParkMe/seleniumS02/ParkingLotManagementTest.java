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
class ParkingLotManagementTest {
	

	private static WebDriver driver;

	private static StringBuffer verificationErrors = new StringBuffer();

	private static String projectPath;

    
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		projectPath = System.getProperty("user.dir");
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

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		//driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	

	
	@Test
	@Order(1)
	  public void insertParkingLot() throws Exception {
			driver.get("http://localhost:4200/login");
		    driver.findElement(By.xpath("//label")).click();
		    driver.findElement(By.id("inputEmail")).clear();
		    driver.findElement(By.id("inputEmail")).sendKeys("flash@park.it");
		    driver.findElement(By.id("inputPassword")).clear();
		    driver.findElement(By.id("inputPassword")).sendKeys("Flash");
		    driver.findElement(By.xpath("//button[@type='submit']")).click();

		    WebDriverWait wait = new WebDriverWait(driver, 3);
		    wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return window.localStorage.getItem(\"token\")") != null); 
		    
		    driver.get("http://localhost:4200/parking-lot-list");
		    driver.findElement(By.xpath("//button[@type='button']")).click();
		    driver.findElement(By.name("street")).click();
		    driver.findElement(By.name("street")).clear();
		    driver.findElement(By.name("street")).sendKeys("Via Milano");
		    driver.findElement(By.name("numberOfParkingLot")).click();
		    driver.findElement(By.name("numberOfParkingLot")).clear();
		    driver.findElement(By.name("numberOfParkingLot")).sendKeys("1234");
		    driver.findElement(By.id("isHandicapParkingLot")).click();
		    new Select(driver.findElement(By.id("isHandicapParkingLot"))).selectByVisibleText("False");
		    driver.findElement(By.name("pricePerHours")).click();
		    driver.findElement(By.name("pricePerHours")).clear();
		    driver.findElement(By.name("pricePerHours")).sendKeys("1.5");
		    driver.findElement(By.id("typeOfVehicle")).click();
		    new Select(driver.findElement(By.id("typeOfVehicle"))).selectByVisibleText("4 Wheels Big Vehicle");
		    driver.findElement(By.name("latitude")).click();
		    driver.findElement(By.name("latitude")).clear();
		    driver.findElement(By.name("latitude")).sendKeys("106.1060");
		    driver.findElement(By.name("longitude")).click();
		    driver.findElement(By.name("longitude")).clear();
		    driver.findElement(By.name("longitude")).sendKeys("107.1070");
		    driver.findElement(By.id("updateButton")).click();
	  }
	
	
	@Test
	@Order(2)
	public void editParkingLot() throws Exception {
		driver.get("http://localhost:4200/login");
	    driver.findElement(By.xpath("//label")).click();
	    driver.findElement(By.id("inputEmail")).clear();
	    driver.findElement(By.id("inputEmail")).sendKeys("flash@park.it");
	    driver.findElement(By.id("inputPassword")).clear();
	    driver.findElement(By.id("inputPassword")).sendKeys("Flash");
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    
	    WebDriverWait wait = new WebDriverWait(driver, 3);
	    wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return window.localStorage.getItem(\"token\")") != null);
	    
	    driver.get("http://localhost:4200/parking-lot-list");
	    driver.findElement(By.xpath("//tr[1]/td")).click();
	    driver.findElement(By.name("newStreet")).click();
	    driver.findElement(By.name("newStreet")).clear();
	    driver.findElement(By.name("newStreet")).sendKeys("Via Milano");
	    driver.findElement(By.name("newNumberOfParkingLot")).click();
	    driver.findElement(By.name("newNumberOfParkingLot")).clear();
	    driver.findElement(By.name("newNumberOfParkingLot")).sendKeys("4321");
	    driver.findElement(By.id("isHandicapParkingLot")).click();
	    new Select(driver.findElement(By.id("isHandicapParkingLot"))).selectByVisibleText("True");
	    driver.findElement(By.name("newPricePerHours")).click();
	    driver.findElement(By.name("newPricePerHours")).clear();
	    driver.findElement(By.name("newPricePerHours")).sendKeys("5.1");
	    driver.findElement(By.id("vehicleType")).click();
	    new Select(driver.findElement(By.id("vehicleType"))).selectByVisibleText("2 Wheels Vehicle");
	    driver.findElement(By.name("newLatitude")).click();
	    driver.findElement(By.name("newLatitude")).clear();
	    driver.findElement(By.name("newLatitude")).sendKeys("60.123");
	    driver.findElement(By.name("newLongitude")).click();
	    driver.findElement(By.name("newLongitude")).clear();
	    driver.findElement(By.name("newLongitude")).sendKeys("12.543");
	    driver.findElement(By.id("updateButton")).click();
	  }
	
	@Test
	@Order(3)
	public void deleteParkingLot() throws Exception {
		driver.get("http://localhost:4200/login");
	    driver.findElement(By.xpath("//label")).click();
	    driver.findElement(By.id("inputEmail")).clear();
	    driver.findElement(By.id("inputEmail")).sendKeys("flash@park.it");
	    driver.findElement(By.id("inputPassword")).clear();
	    driver.findElement(By.id("inputPassword")).sendKeys("Flash");
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    
	    WebDriverWait wait = new WebDriverWait(driver, 3);
	    wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return window.localStorage.getItem(\"token\")") != null);
	    
	    driver.get("http://localhost:4200/parking-lot-list");
	    driver.findElement(By.xpath("//tr[1]/td")).click();
	    driver.findElement(By.id("deleteButton")).click();
	  }
}


