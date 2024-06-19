package practicecode;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GetWindowHndle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RemoteWebDriver driver = new ChromeDriver();
//		RemoteWebDriver driver;
//		int browserType = 1;
//		switch (browserType) {
//		case 1:
//			 driver = new ChromeDriver();
//			break;
//		case 2:
//			 driver = new FirefoxDriver();
//
//		default:
//			break;
//		}
		
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		driver.get("https://www.leafground.com/window.xhtml");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy("//*[text()='Click and Confirm new Window Opens']"));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(null)
		driver.findElement(By.xpath("//span[text()='Open']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String myWindow = driver.getWindowHandle();
		Set<String> openedWindows = driver.getWindowHandles();
		for (String value : openedWindows) {
			if(!value.equals(myWindow)) {
				driver.switchTo().window(value);
				System.out.println(value.equals(myWindow));
				System.out.println("New Window: " + value);
				System.out.println("Old Window: " + myWindow);
				
			}	}
		
		List<String> tabs = new ArrayList<String>(openedWindows);
		driver.switchTo().window(tabs.get(0));
//						for (int i = 0; i < tabs.length-1; i++) {
//			if(!tabs[i].equals(myWindow)) {
//				driver.switchTo().window(tabs[i]);
//				System.out.println(tabs[i].equals(myWindow));
//				System.out.println("New Window: " + tabs[i]);
				
				
		System.out.println("No. of Windows opened: "+ openedWindows.size());
		System.out.println("Title: " + driver.getTitle());
		if(!(openedWindows.size()>1)) {
			System.out.println("No New window is opened");
		}
		
		driver.close();
		driver.switchTo().window(myWindow);
//		String myWindow = driver.getWindowHandle();
		driver.findElement(By.xpath("//*[text()='Open Multiple']")).click();
		Set<String> newWindows = driver.getWindowHandles();
		System.out.println("Count of window opened: " + newWindows.size());
		for (String value : newWindows) {
			if(!value.equals(myWindow)) {
				driver.switchTo().window(value);
				System.out.println(value.equals(myWindow));
				System.out.println("New Window: " + value);
				System.out.println("Old Window: " + myWindow);
				Actions actObject = new Actions(driver);
				WebElement actElement = driver.findElement(By.xpath("//*[text()='Close Windows']"));
				actObject.moveToElement(actElement).click().build().perform();
			}	
		
		
	}

	}
	}
