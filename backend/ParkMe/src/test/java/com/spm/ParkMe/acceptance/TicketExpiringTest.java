package com.spm.ParkMe.acceptance;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TicketExpiringTest {

	public TicketExpiringTest() {
		// TODO Auto-generated constructor stub
		
		
	}

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

}
