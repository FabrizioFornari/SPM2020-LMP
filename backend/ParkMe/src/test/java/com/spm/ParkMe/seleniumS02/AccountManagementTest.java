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
import org.openqa.selenium.support.ui.Select;



@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountManagementTest {
	

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
	

	
	@Test
	@Order(1)
	@Disabled
	  public void changeEmail() throws Exception {
	    driver.get("http://localhost:4200/login");
	    driver.findElement(By.xpath("//label")).click();
	    driver.findElement(By.id("inputEmail")).clear();
	    driver.findElement(By.id("inputEmail")).sendKeys("rocche@park.it");
	    driver.findElement(By.id("inputPassword")).clear();
	    driver.findElement(By.id("inputPassword")).sendKeys("Rocche");
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    driver.findElement(By.xpath("//button[@type='button']")).click();
	    driver.findElement(By.name("newEmail")).click();
	    driver.findElement(By.name("newEmail")).clear();
	    driver.findElement(By.name("newEmail")).sendKeys("rocche2@park.it");
	    driver.findElement(By.id("updateButton")).click();
	  }
	
	@Test
	@Order(2)
	@Disabled
	public void changePassword() throws Exception{
		driver.get("http://localhost:4200/login");
	    driver.findElement(By.xpath("//label")).click();
	    driver.findElement(By.id("inputEmail")).clear();
	    driver.findElement(By.id("inputEmail")).sendKeys("rocche2@park.it");
	    driver.findElement(By.id("inputPassword")).clear();
	    driver.findElement(By.id("inputPassword")).sendKeys("Rocche");
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
	    driver.findElement(By.name("oldPassword")).click();
	    driver.findElement(By.name("oldPassword")).clear();
	    driver.findElement(By.name("oldPassword")).sendKeys("Rocche");
	    driver.findElement(By.name("newPassword")).click();
	    driver.findElement(By.name("newPassword")).clear();
	    driver.findElement(By.name("newPassword")).sendKeys("rocche");
	    driver.findElement(By.name("newPasswordRepeated")).click();
	    driver.findElement(By.name("newPasswordRepeated")).clear();
	    driver.findElement(By.name("newPasswordRepeated")).sendKeys("rocche");
	    driver.findElement(By.id("updateButton")).click();
	}
	
	@Test
	@Order(3)
	@Disabled
	  public void changePhone() throws Exception {
		driver.get("http://localhost:4200/login");
	    driver.findElement(By.xpath("//label")).click();
	    driver.findElement(By.id("inputEmail")).clear();
	    driver.findElement(By.id("inputEmail")).sendKeys("rocche2@park.it");
	    driver.findElement(By.id("inputPassword")).clear();
	    driver.findElement(By.id("inputPassword")).sendKeys("rocche");
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
	    driver.findElement(By.name("newPhone")).click();
	    driver.findElement(By.name("newPhone")).clear();
	    driver.findElement(By.name("newPhone")).sendKeys("+39 333 3434343");
	    driver.findElement(By.id("updateButton")).click();
	  }
	
	@Test
	@Order(4)
	@Disabled
	  public void changePlateAndVehicle() throws Exception {
	    driver.get("http://localhost:4200/login");
	    driver.findElement(By.xpath("//label")).click();
	    driver.findElement(By.id("inputEmail")).clear();
	    driver.findElement(By.id("inputEmail")).sendKeys("rocche2@park.it");
	    driver.findElement(By.id("inputPassword")).clear();
	    driver.findElement(By.id("inputPassword")).sendKeys("rocche");
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
	    driver.findElement(By.name("newPlate")).click();
	    driver.findElement(By.name("newPlate")).clear();
	    driver.findElement(By.name("newPlate")).sendKeys("AB010BA");
	    driver.findElement(By.id("inputNewVehicle")).click();
	    new Select(driver.findElement(By.id("inputNewVehicle"))).selectByVisibleText("4 Wheels Standard Vehicle");
	    driver.findElement(By.id("updateButton")).click();
	  }

	
	

}
