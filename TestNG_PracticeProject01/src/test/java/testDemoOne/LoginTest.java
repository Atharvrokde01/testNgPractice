package testDemoOne;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {
WebDriver driver;

@BeforeClass
public void steUp() {
	driver = new ChromeDriver();
	driver.navigate().to("https://www.saucedemo.com/");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	driver.manage().deleteAllCookies();
}
@Test
public void logintest() throws InterruptedException {
	driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
	driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
	driver.findElement(By.xpath("//input[@id='login-button']")).click();
	Thread.sleep(2000);

}

@Test(dependsOnMethods = {"logintest"})
public void verifyLogin() {
	String actual_title= driver.getTitle();
	String expected_title= "Swag Labs";
	Assert.assertEquals(actual_title , expected_title);
	
}

@Test(dependsOnMethods = {"logintest"})
public void verifyCartFunctinality() throws InterruptedException {
	driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).click();
	Thread.sleep(2000);
	String actual_text=driver.findElement(By.xpath("//span[@class='title']")).getText();
	String expected_text= "Your Cart";
	Assert.assertEquals(actual_text, expected_text);
	
}

@AfterClass
public void tearDown() {
	driver.navigate().back();
	driver.navigate().refresh();
	driver.quit();
	
}
}
