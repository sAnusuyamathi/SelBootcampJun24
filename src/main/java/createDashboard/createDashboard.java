package createDashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class createDashboard {

public static void main(String[] args) throws InterruptedException {
	ChromeOptions optionsBrowser = new ChromeOptions();
	optionsBrowser.addArguments("--disable-notifications");
	ChromeDriver driver = new ChromeDriver(optionsBrowser);
	driver.get("https://login.salesforce.com/");
	driver.manage().window().maximize();
	driver.findElement(By.id("username")).sendKeys("bootcamp_2024@testleaf.com");
	driver.findElement(By.id("password")).sendKeys("Bootcamp@123");
	driver.findElement(By.id("Login")).click();
	System.out.println(driver.getTitle());
	if (driver.getTitle()=="Lightning Experience") {
		System.out.println("Home page is launched");
	}
	Thread.sleep(3000);
	driver.findElement(By.className("slds-icon-waffle")).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath("(//button[@class='slds-button'])[2]")).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath("(//p[text()='Sales'])")).click();
	Thread.sleep(6000);
	driver.findElement(By.xpath("(//Span[text()='Opportunities'])")).click();
	driver.findElement(By.xpath("(//Span[text()='New Opportunity'])")).click();
	Thread.sleep(3000);
	
	
}
}