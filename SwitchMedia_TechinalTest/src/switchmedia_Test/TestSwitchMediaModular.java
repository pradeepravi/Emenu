package switchmedia_Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestSwitchMediaModular {

	final static StringBuffer str = new StringBuffer();

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = initializeDriver();

		doBrowseToGmail(driver);

		boolean isLoggedIn = false;
		try {
			doEnterEmailID(driver);//

			doClickNextAfterEmail(driver);

			Thread.sleep(5000);

			doEnterPassword(driver);//

			doClickSign(driver);//

			Thread.sleep(20000);
			isLoggedIn = doVerifyLoggedin(driver);//

			doComposeMailAndSend(driver);

			doValidateEmailSent(driver);//
		} catch (Exception e) {
			e.getMessage();

		} finally {
			if (isLoggedIn) {
				Thread.sleep(5000);

				doLogOut(driver);

				Thread.sleep(5000);

				doConfirmLoggedOut(driver);//
			}else {
				str.append("\n").append("**************** Login Failed. Incorrect Credentials. Test cannot proceed **************** ");
			}
			generateReport();

			quitAndCloseDriver(driver);

		}

	}

	private static void generateReport() {
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			// String content = "This is the content to write into file\n";
			fw = new FileWriter(new File("d:\\report.txt"));
			bw = new BufferedWriter(fw);
			bw.write(str.toString());
			str.append("\n").append("Done");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	private static WebDriver initializeDriver() {
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
		System.setProperty("webdriver.gecko.driver", "D:\\geckodriver-v0.11.1-win64\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver(capabilities);
		return driver;
	}

	private static void quitAndCloseDriver(WebDriver driver) throws InterruptedException {
		if (driver != null) {
			try {
				driver.close();
				Thread.sleep(3000);
				driver.quit();
			} catch (Exception b) {
				b.getMessage();
			}
		}
	}

	private static void doConfirmLoggedOut(WebDriver driver) {
		if (driver.findElement(By.xpath("//h2[contains(.,'Sign in to continue to Gmail')]")).isDisplayed()) {
			str.append("\n").append("TestCase 6 - Pass: Logged out Successfully!!!");
		} else {
			str.append("\n").append("TestCase 6 - Fail: Log Out Failed!!!");
		}
	}

	private static void doLogOut(WebDriver driver) {
		driver.findElement(By.xpath("//span[contains(@class,'9a gbii')]")).click();
		driver.findElement(By.xpath("//a[contains(.,'Sign out')]")).click();
	}

	private static void doValidateEmailSent(WebDriver driver) {
		WebElement sentmail = driver.findElement(By.xpath("//div [@class='y6']/span[contains(.,'test subject')]"));
		if (sentmail.isDisplayed()) {
			str.append("\n").append("TestCase 5 - Pass: Email sent sucessfully!!!");
		} else {
			str.append("\n").append("TestCase 5 - Failed to send Email");

			// throw new Exception("TestCase 4 - Fail: Logged in Failed!, Cannot
			// proceed with the test");
		}
	}

	private static void doComposeMailAndSend(WebDriver driver) {

		// Test 4 : Composing and sending Email
		driver.findElement(By.xpath("//div[contains(@gh,'cm')]")).click();

		/*
		 * if (driver.findElement(By.className("vO")).isDisplayed()) {
		 * str.append("\n").append("Compose pop up displayed"); }
		 */

		driver.findElement(By.xpath("//textarea[@name='to']")).click();
		driver.findElement(By.xpath("//textarea[@name='to']")).sendKeys("devurk@gmail.com");
		driver.findElement(By.xpath("//input[@class='aoT']")).click();
		driver.findElement(By.xpath("//input[@class='aoT']")).sendKeys("test subject");
		driver.findElement(By.xpath("//div[contains(@aria-label,'Message Body')]")).click();
		driver.findElement(By.xpath("//div[contains(@aria-label,'Message Body')]")).sendKeys("send from test script");

		driver.findElement(By.xpath("//div[text()='Send']")).click();
		driver.findElement(By.xpath("//a[@title='Sent Mail']")).click();
	}

	private static boolean doVerifyLoggedin(WebDriver driver) throws Exception {
		if (driver.findElement(By.xpath("//div[contains(@gh,'cm')]")).isDisplayed()) {
			str.append("\n").append("TestCase 4 - Pass: Logged in Successfully!");

			return true;
		} else {
			str.append("\n").append("TestCase 4 - Fail: Logged in Failed!, Cannot proceed with the test");

			throw new Exception("TestCase 4 - Fail: Logged in Failed!, Cannot proceed with the test");
		}
	}

	private static boolean doClickSign(WebDriver driver) throws Exception {
		if (driver.findElement(By.xpath("//*[@id='signIn']")).isDisplayed()) {
			str.append("\n").append("TestCase 3 - Pass: Signin Button is displayed");
			driver.findElement(By.xpath("//*[@id='signIn']")).click();

			return true;
		} else {
			str.append("\n").append("TestCase 3 - Fail: Signin Button is NOT displayed,  Cannot proceed with test");

			throw new Exception("TestCase 3 - Fail: Signin Button is NOT displayed,  Cannot proceed with test");
		}
	}

	private static boolean doEnterPassword(WebDriver driver) throws Exception {
		WebElement pwd = driver.findElement(By.xpath("//*[@id='Passwd']"));
		if (pwd.isDisplayed()) {
			str.append("\n").append("TestCase 2 - Pass: Password field is displayed");
			pwd.sendKeys("testing123");

			return true;
		} else {
			str.append("\n").append("TestCase 2 - Fail : Password field is NOT displayed, Cannot proceed with test ");

			throw new Exception("TestCase 2 - Fail : Password field is NOT displayed, Cannot proceed with test");
		}
	}

	private static boolean doEnterEmailID(WebDriver driver) throws Exception {
		WebElement eaddress = driver.findElement(By.xpath("//*[@id='Email']"));
		if (eaddress.isDisplayed()) {
			str.append("\n").append("TestCase 1 - Pass: Email field is displayed");
			eaddress.sendKeys("devikatavant@gmail.com");

			return true;
		} else {
			str.append("\n").append("TestCase 1 - Fail : Email field is NOT displayed, Cannot proceed with test");
			throw new Exception("TestCase 1 - Fail : Email field is NOT displayed, Cannot proceed with test");
		}
	}

	private static void doClickNextAfterEmail(WebDriver driver) {
		driver.findElement(By.xpath("//*[@id='next']")).click();
	}

	private static void doBrowseToGmail(WebDriver driver) {
		driver.get("http://gmail.com");
	}

}
