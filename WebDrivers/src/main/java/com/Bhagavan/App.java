package com.Bhagavan;

import java.util.concurrent.TimeUnit;

import javax.security.auth.login.LoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.Exception.AlreadyRegistered;
import com.interfce.InfoApp;

	
public class App implements InfoApp {
	
	WebDriver driver;
	App app;
	String RegisterPassword;

		@BeforeSuite
		public void declarations()
		{
			String Location = "G:\\chromedriver.exe";
			String url = "http://automationpractice.com/index.php";
			System.setProperty("webdriver.chrome.driver", Location);
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		    driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(url);
		}
		
		
		
		@Test()
		public void verifyTitle()
		{
			System.out.println("firstStep");
			System.out.println("Launching Chrome driver");
			String expectedTitle = "My Store";
			String actualTitle = driver.getTitle();
			Assert.assertEquals(actualTitle, expectedTitle);
			System.out.println("verified titles");
			driver.findElement(By.className("login")).click();
		}
		
		
		
		@Override
		@Test(dependsOnMethods = {"verifyTitle"})
		public void alreadyRegistered() throws LoginException, InterruptedException, AlreadyRegistered
		{
//			String expectedTitle = driver.getTitle();
//			String actualTitle = "Login - My Store";
			driver.findElement(By.name("email_create")).sendKeys("duplicate67162877@cuoly.com");
			driver.findElement(By.id("SubmitCreate")).click();
			Thread.sleep(5000);
//			if(expectedTitle.equals(actualTitle))
//			{
//				driver.close();
//				throw new AlreadyRegistered("This email Id has an account");
//			}
		}
		
		
		
		@Override
		@Test(dependsOnMethods = {"alreadyRegistered"})
		public void signinaccountPage() throws InterruptedException
		{
			System.out.println("secondstep");
			driver.findElement(By.id("uniform-id_gender1")).click();
			driver.findElement(By.id("customer_firstname")).sendKeys("Mamidipaka");
			driver.findElement(By.id("customer_lastname")).sendKeys("Bhagavan");
			driver.findElement(By.name("passwd")).sendKeys("Bhagavan@1999");
			driver.findElement(By.id("days")).sendKeys("31");
			driver.findElement(By.id("months")).sendKeys("December");
			driver.findElement(By.id("years")).sendKeys("1998");
			driver.findElement(By.id("newsletter")).click();
			driver.findElement(By.id("optin")).click();
			driver.findElement(By.id("address1")).sendKeys("D.No: 15-123, Sanivarapeta");
			driver.findElement(By.name("city")).sendKeys("Eluru");
			Select dropdown = new Select(driver.findElement(By.id("id_state")));
			dropdown.selectByIndex(15);
			//Thread.sleep(2000);
			driver.findElement(By.name("postcode")).sendKeys("46202");
			driver.findElement(By.id("phone_mobile")).sendKeys("9493503388");
			driver.findElement(By.id("alias")).sendKeys("alias address");
			//Thread.sleep(2000);
			driver.findElement(By.name("submitAccount")).click();
			//Thread.sleep(2000);
			driver.findElement(By.cssSelector("a[title = \"Log me out\"]")).click();
			Thread.sleep(2000);
		}
		
		
		@Override
		@Test(dependsOnMethods = {"signinaccountPage"})
		public void Login()
		{
			try {
			String actualTitle = "My account - My Store";
			driver.findElement(By.id("email")).clear();
			driver.findElement(By.id("email")).sendKeys("duplicate67162877@cuoly.com");
			driver.findElement(By.id("passwd")).clear();
			driver.findElement(By.id("passwd")).sendKeys("Bhagavan@1999");
			driver.findElement(By.id("SubmitLogin")).click();
				Thread.sleep(2000);
			
				String expectedTitle = driver.getTitle();
				if(expectedTitle.equals(actualTitle))
				{
					System.out.println("******************");
					System.out.println("Login Sussessfully");
				}
				else
				{
					driver.close();
					throw new LoginException("Invalid credentials or User should register");
				}
				driver.findElement(By.id("search_query_top")).sendKeys("short",Keys.ENTER);
				System.out.println("Enter to T-shirts cart");
				driver.findElement(By.cssSelector("img[title=\"Blouse\"]")).click();
				Thread.sleep(2000);
				driver.findElement(By.name("Submit")).click();
				driver.findElement(By.cssSelector("a[title = \"Proceed to checkout\"]")).click();
				Thread.sleep(2000);
				driver.close();
			}
			catch (InterruptedException | LoginException e) {
				e.printStackTrace();
			}
		}	
		
	
}
