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
import org.openqa.selenium.chrome.ChromeOptions;
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

	private static ChromeOptions options;
	
	private static boolean headless = true;
    
	
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
		

		ChromeOptions options = new ChromeOptions();
		if(headless) {
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--no-sandbox");
			options.addArguments("--window-size=1920,1080");
			options.addArguments("--disable-gpu");
			options.addArguments("--disable-extensions");
			options.addArguments("--start-maximized");		
			options.addArguments("--headless");
			waiting = mediumWaiting = notificationLongWait = notificationShortWait = 50;
		}
		
		
	    driver = new ChromeDriver(options);
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
		    new Select(driver.findElement(By.id("inputNewVehicle"))).selectByVisibleText("4 Wheels Standard Vehicle");
		    Thread.sleep(waiting);
		    driver.findElement(By.id("updateButton")).click();
		    Thread.sleep(mediumWaiting);
	}

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
	    driver.findElement(By.id("handicapIcon")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.id("updateButton")).click();
	    Thread.sleep(mediumWaiting);
	}
	
	
	@Order(4)
	@Tag("AcceptanceTest")
	@Test
	 public void driverBuysPersonalParkingLot() throws Exception {
	    driver.get("http://apromore.unicam.it/SPM2020-LMP/#/login");
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
	    driver.findElement(By.xpath("(//div[@id='cardFunction'])[2]")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("//div[4]/img[2]")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("//div[3]/i")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.name("months")).click();
	    Thread.sleep(waiting);
	    new Select(driver.findElement(By.name("months"))).selectByVisibleText("4");
	    Thread.sleep(waiting);
	    driver.findElement(By.name("months")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("//div[@id='buttonConfirm']/p")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("//button[@type='button']")).click();
	    Thread.sleep(waiting);
	  }

	
	@Order(5)
	@Test
	@Tag("AcceptanceTest")
	public void vigilantDisableParkingLot() throws Exception{
		Thread.sleep(waiting);
		driver.get("http://apromore.unicam.it/SPM2020-LMP/#/login");
		Thread.sleep(waiting);
	    driver.findElement(By.id("inputEmail")).clear();
	    Thread.sleep(waiting);
	    driver.findElement(By.id("inputEmail")).sendKeys("cret@park.it");
	    Thread.sleep(waiting);
	    driver.findElement(By.id("inputPassword")).clear();
	    Thread.sleep(waiting);
	    driver.findElement(By.id("inputPassword")).sendKeys("Cret");
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.linkText("Vigilant-Panel")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.id("cardFunction")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.id("streetCardInfo")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.id("public")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("//div[@id='streetCardInfo']/h5")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("//button[@type='button']")).click();
	    Thread.sleep(mediumWaiting);
	}	


	
	
	@Order(6)
	@Test
	@Tag("AcceptanceTest")
	public void vigilantDisablePersonalParkingLot() throws Exception{
		Thread.sleep(waiting);
		driver.get("http://apromore.unicam.it/SPM2020-LMP/#/login");
		Thread.sleep(waiting);
	    driver.findElement(By.id("inputEmail")).clear();
	    Thread.sleep(waiting);
	    driver.findElement(By.id("inputEmail")).sendKeys("cret@park.it");
	    Thread.sleep(waiting);
	    driver.findElement(By.id("inputPassword")).clear();
	    Thread.sleep(waiting);
	    driver.findElement(By.id("inputPassword")).sendKeys("Cret");
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.linkText("Vigilant-Panel")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.id("cardFunction")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("(//div[@id='streetCardInfo'])[3]")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.id("personal")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("//div[@id='streetCardInfo']/h5")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("//button[@type='button']")).click();
	    Thread.sleep(mediumWaiting);
	}
	
	@Disabled
	@Order(7)
	@Test
	@Tag("AcceptanceTest")
	public void vigilantCheckParkingLotStatusFromNotification() throws Exception {
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
	    Thread.sleep(mediumWaiting);
	    Thread.sleep(notificationShortWait);
	    driver.findElement(By.id("notificationIcon")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("//div/div/p[2]")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("//app-check-park/div/p[2]")).click();
	    
	    Thread.sleep(waiting);
	    driver.findElement(By.linkText("exit_to_app Logout")).click();
	    
	    driver.findElement(By.id("inputEmail")).clear();
	    Thread.sleep(waiting);
	    driver.findElement(By.id("inputEmail")).sendKeys("cret@park.it");
	    Thread.sleep(waiting);
	    driver.findElement(By.id("inputPassword")).clear();
	    Thread.sleep(waiting);
	    driver.findElement(By.id("inputPassword")).sendKeys("Cret");
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    Thread.sleep(waiting);
	    Thread.sleep(notificationLongWait);
	    driver.findElement(By.id("notificationIcon")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("//div[2]/div/div/div/p[2]")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("//button[@type='button']")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("//app-park-info/div/p[2]")).click();
	    Thread.sleep(mediumWaiting);
	}

	
	@Order(8)
	@Test
	@Tag("AcceptanceTest")
	public void adminAcceptsHandicapRequest() throws Exception{
		driver.get("http://apromore.unicam.it/SPM2020-LMP/#/login");
		Thread.sleep(waiting);
	    driver.findElement(By.id("inputEmail")).clear();
	    driver.findElement(By.id("inputEmail")).sendKeys("admin@park.it");
	    Thread.sleep(waiting);
	    driver.findElement(By.id("inputPassword")).clear();
	    driver.findElement(By.id("inputPassword")).sendKeys("Fusaro");
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.linkText("Admin-Panel")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.xpath("(//div[@id='cardFunction'])[3]")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.id("cardFunction")).click();
	    Thread.sleep(waiting);
	    driver.findElement(By.id("updateButton")).click();
	    Thread.sleep(waiting);
	}

	
	

	
}
