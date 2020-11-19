package com.spm.ParkMe.seleniumS02;

import static org.junit.Assert.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.context.SpringBootTest;




class AccountManagementTest {

	private static WebDriver driver;

	private static StringBuffer verificationErrors = new StringBuffer();

    
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		 
		System.setProperty("webdriver.chrome.driver","src/test/java/com/spm/ParkMe/seleniumS02/chromedriver.exe");
		
	    driver = new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    //250ms frequency of pulling
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		driver.quit();
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

	

}
