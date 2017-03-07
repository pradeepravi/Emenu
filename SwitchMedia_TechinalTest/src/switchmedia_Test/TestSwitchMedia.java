package switchmedia_Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestSwitchMedia {

	public static void main(String[] args) throws InterruptedException {

		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
		WebDriver driver;
		System.setProperty("webdriver.gecko.driver", "D:\\geckodriver-v0.11.1-win64\\geckodriver.exe");
		driver = new FirefoxDriver(capabilities);
		// Test 1 : Validate the existence of Gmail login page UI element- Email

		driver.get("http://gmail.com");

		WebElement eaddress = driver.findElement(By.xpath("//*[@id='Email']"));
		if (eaddress.isDisplayed()) {
			System.out.println("Test 1 Pass: Email field is displayed");
			eaddress.sendKeys("devikatavant@gmail.com");

			driver.findElement(By.xpath("//*[@id='next']")).click();
		} else {
			System.out.println("Test 1 Fail : Email field is NOT displayed");
		}

		// Test 2 : Validate the existence of Gmail login page UI element-
		// Password

		Thread.sleep(5000);
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement pwd = driver.findElement(By.xpath("//*[@id='Passwd']"));
		if (pwd.isDisplayed())

		{
			System.out.println("Test 1 Pass: Password field is displayed");
			pwd.sendKeys("testing123");
		}

		else

		{
			System.out.println("Test 1 Fail : Password field is NOT displayed, Cannot proceed with test ");
		}

		// Test 3: Logging in

		driver.findElement(By.xpath("//*[@id='signIn']")).click();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		/*
		 * while (!driver.findElement(By.xpath("//div[contains(@gh,'cm')]")).
		 * isDisplayed()); { System.out.println("logging in....."); }
		 * System.out.println("Logged in Successfully");
		 */

		if (driver.findElement(By.xpath("//div[contains(@gh,'cm')]")).isDisplayed()) {
			System.out.println("Logged in Successfully");
		} else {
			System.out.println("Test 3 Fail : Unable to Login, Cannot proceed with the test");
		}

		// Test 4 : Composing and sending Email
		driver.findElement(By.xpath("//div[contains(@gh,'cm')]")).click();

		if (driver.findElement(By.className("vO")).isDisplayed()) {
			System.out.println("Compose pop up displayed");
		}
		// driver.findElement(By.xpath("//td//img[2]")).click();

		driver.findElement(By.xpath("//textarea[@name='to']")).click();
		driver.findElement(By.xpath("//textarea[@name='to']")).sendKeys("devurk@gmail.com");
		driver.findElement(By.xpath("//input[@class='aoT']")).click();
		driver.findElement(By.xpath("//input[@class='aoT']")).sendKeys("test subject");
		driver.findElement(By.xpath("//div[contains(@aria-label,'Message Body')]")).click();
		driver.findElement(By.xpath("//div[contains(@aria-label,'Message Body')]")).sendKeys("send from test script");

		driver.findElement(By.xpath("//div[text()='Send']")).click();

		//driver.findElement(By.xpath("//a[@title='Sent Mail']")).click();
		try {
			driver.findElement(By.xpath("//a[@title='Sent Mail']")).click();
			Alert alert=driver.switchTo().alert();
			if(alert!=null) {
				alert.dismiss();
			}

		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("No Alert 1");
		} // catch
		System.out.println("sent items displayed");
		WebElement sentmail = driver.findElement(By.xpath("//div [@class='y6']/span[contains(.,'test subject')]"));
		if (sentmail.isDisplayed()) {
			System.out.println("Test 4 : Pass -  Email sent sucessfully!!!");
		} else {
			System.out.println("Test 4 :Fail - Failed to send email !!!");
		}
		Thread.sleep(5000);
		// Log out
		driver.findElement(By.xpath("//span[contains(@class,'9a gbii')]")).click();
		//driver.findElement(By.xpath("//a[contains(.,'Sign out')]")).click();

		// driver.switchTo().alert().dismiss();
		driver.findElement(By.xpath("//a[contains(.,'Sign out')]")).click();

		try {
			Alert alert=driver.switchTo().alert();
			if(alert!=null) {
				alert.dismiss();
			}
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("No Alert 2");
		} // catch

		Thread.sleep(5000);
		System.out.println("***************SLEEP 1");
		if (driver.findElement(By.xpath("//h2[contains(.,'Sign in to continue to Gmail')]")).isDisplayed()) {
			System.out.println("Test 5 : Pass -  logged out!!!");
		} else {
			System.out.println("Test 5 : Fail -  log out Failed!!!");
		}
		try {
			Alert alert=driver.switchTo().alert();
			if(alert!=null) {
				alert.accept();
			}
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("No Alert 3");
		} // catch
		// driver.quit();

	}

}
