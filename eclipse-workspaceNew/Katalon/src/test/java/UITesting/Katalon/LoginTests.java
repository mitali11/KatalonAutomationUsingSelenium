package UITesting.Katalon;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


public class LoginTests {
	WebDriver driver;
	ChromeOptions options;
	
	@BeforeSuite(alwaysRun=true)
	public void SetUp() {
		options=new ChromeOptions();
		options.addArguments("--start-maximized");
		driver=new ChromeDriver(options);
	}
	
	@Test(groups={"positive"})
	public void ValidLogin() {
		
		driver.get("https://katalon-demo-cura.herokuapp.com/#appointment");
		driver.findElement(By.id("btn-make-appointment")).click();
		Assert.assertEquals("https://katalon-demo-cura.herokuapp.com/profile.php#login", driver.getCurrentUrl());
		driver.findElement(By.name("username")).sendKeys("John Doe");
		driver.findElement(By.name("password")).sendKeys("ThisIsNotAPassword");
		driver.findElement(By.id("btn-login")).click();
		Assert.assertEquals("https://katalon-demo-cura.herokuapp.com/#appointment", driver.getCurrentUrl());
		
	}
	
	@Test
	@Severity(SeverityLevel.BLOCKER)
	public void InvalidLogin() {
		
		driver.get("https://katalon-demo-cura.herokuapp.com/#appointment");
		driver.findElement(By.id("btn-make-appointment")).click();
		Assert.assertEquals("https://katalon-demo-cura.herokuapp.com/profile.php#login", driver.getCurrentUrl());
		driver.findElement(By.name("username")).sendKeys("John Doe1");
		driver.findElement(By.name("password")).sendKeys("ThisIsANotAPassword");
		driver.findElement(By.id("btn-login")).click();
		Assert.assertEquals("Login failed! Please ensure the username and password are valid.", driver.findElement(By.xpath("//p[contains(text(),\"Login failed!\")]")).getText());
		
	}
	
	@AfterSuite(alwaysRun=true)
	public void TearDown() {
		driver.quit();
	}
}
