package dashboard;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Assessment1 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
//		Assessment TestCase
//		==================
//		Credentials: saturn@testleaf.com ; Bootcamp$123
//		1. Login to https://login.salesforce.com
		boolean visibleFlag = false;
		Random randObject = new Random();
		ChromeOptions optionsBrowser = new ChromeOptions();
		optionsBrowser.addArguments("--disable-notifications");
		
		
		RemoteWebDriver driver = new ChromeDriver(optionsBrowser);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		
		Actions actObject = new Actions(driver);		
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		driver.findElement(By.id("username")).sendKeys("bootcamp_2024@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Bootcamp@123");
		driver.findElement(By.id("Login")).click();
		System.out.println(driver.getTitle());
		if (driver.getTitle().equalsIgnoreCase("Lightning Experience")) {
			System.out.println("Home page is launched");
		}
//		Thread.sleep(10000);
//		2. Click on toggle menu button from the left corner
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='App Launcher']")));
		driver.findElement(By.xpath("//button[@title='App Launcher']")).click();
		Thread.sleep(3000);
//		3. Click view All 
		driver.findElement(By.xpath("(//button[@class='slds-button'])[2]")).click();
		Thread.sleep(3000);
//		4. Click Sales from App Launcher
		driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("Sales");
		WebElement salesElement = driver.findElement(By.xpath("//p[text()='Manage your sales process with accounts, leads, opportunities, and more']"));
		wait.until(ExpectedConditions.visibilityOf(salesElement));
		salesElement.click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()='Home']"))));
		Thread.sleep(3000);
//		5. Select Home from the DropDown
		System.out.println(driver.getTitle());
		if (driver.getTitle().equalsIgnoreCase("Home | Salesforce")) {
			System.out.println("Sales Page is launched");
		}
		actObject.moveToElement(driver.findElement(By.xpath("//span[text()='Home']"))).click().perform();
//		driver.findElement(By.xpath("//span[text()='Home']")).click();
		Thread.sleep(3000);
		
//		* old* Add CLOSED + OPEN values and result should set as the GOAL (If the result is less than 10000 then set the goal as 10000)
//		6.Set the goals by using random java and change the frequency
//		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//a[text()='View Accounts']")));
//		driver.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//span[text()='Monthly Goals']")));
//		Scroll to Goal setup
//		actObject.scrollToElement(driver.findElement(By.xpath("//span[text()='Monthly Goals']"))).click().perform();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[contains(text(),'Goals')]"))));
		if ( driver.findElement(By.xpath("//span[contains(text(),'Goals')]")).isDisplayed()) {
			System.out.println("Goals panel is displayed");
			visibleFlag= true;
		}else {
			System.out.println("Goals panel is not displayed");
		}
		if(visibleFlag) {
		driver.findElement(By.xpath("//button[@aria-label='Edit my goals']")).click();
		Thread.sleep(4000);
//		verify the set goals is displayed
		if(driver.findElement(By.xpath("//span[text()='Set Goals']")).isDisplayed()) {
			int nomeetings = randObject.nextInt(10);
			driver.findElement(By.xpath("//input[@placeholder='Number of meetings']")).clear();
			driver.findElement(By.xpath("//input[@placeholder='Number of meetings']")).sendKeys(String.valueOf(nomeetings));
			
			driver.findElement(By.xpath("//input[@placeholder='Number of calls']")).clear();;
			driver.findElement(By.xpath("//input[@placeholder='Number of calls']")).sendKeys(String.valueOf(randObject.nextInt(10)));
			
			driver.findElement(By.xpath("//input[@placeholder='Number of emails']")).clear();;
			driver.findElement(By.xpath("//input[@placeholder='Number of emails']")).sendKeys(String.valueOf(randObject.nextInt(10)));
			
			String defaultTimeFrame = driver.findElement(By.xpath("//button[@name='timeInterval']/span")).getText();
			System.out.println("Default TimeFrame Set: " + defaultTimeFrame );
			if(defaultTimeFrame.equalsIgnoreCase("Monthly")) {
				driver.findElement(By.xpath("//button[@name='timeInterval']")).click();
				driver.findElement(By.xpath("//span[text()='Weekly']")).click();
				//span[text()='Weekly']
				
				driver.findElement(By.xpath("//button[@title='Save']")).click();
				Thread.sleep(1000);
				String text = driver.findElement(By.xpath("//button[@aria-label='Edit my goals']/preceding::span[1]")).getText();
				if(text.startsWith("Weekly")) {
					System.out.println("Weekly Goal is set successfully");
				}
				else 
					System.out.println("Goal is not set successfully");
			}
			else {
				driver.findElement(By.xpath("//button[@name='timeInterval']")).click();
				driver.findElement(By.xpath("//span[text()='Monthly']")).click();
				driver.findElement(By.xpath("//button[@title='Save']")).click();
				Thread.sleep(1000);
				String text = driver.findElement(By.xpath("//button[@aria-label='Edit my goals']/preceding::span[1]")).getText();
				if(text.startsWith("Monthly")) {
					System.out.println("Monthly Goal is set successfully");
				}
				else 
					System.out.println("Goal is not set successfully");
			}
			
			
//		7. Navigate to Dashboard tab
			Thread.sleep(3000);
			WebElement findElement = driver.findElement(By.xpath("//a[@title='Dashboards']"));
			driver.executeScript("arguments[0].click();",findElement );
			Thread.sleep(1000);
//		8. Click on New Dashboard
			driver.executeScript("arguments[0].click();",driver.findElement(By.xpath("//div[@title='New Dashboard']")));
			Thread.sleep(1000);
			WebElement frameElement = driver.findElement(By.xpath("//iFrame[@title='dashboard']"));
			driver.switchTo().frame(frameElement);
//		9. Enter the Dashboard name as "YourName_Workout"
			driver.findElement(By.id("dashboardNameInput")).sendKeys("Anusuya_Workout");
//		10. Enter Description as Testing and Click on Create
			driver.findElement(By.id("dashboardDescriptionInput")).sendKeys("Testing");
//		11. Click on Create
			driver.findElement(By.id("submitBtn")).click();
			Thread.sleep(5000);
			driver.switchTo().defaultContent();
//		12. Click on Done
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//iFrame"))));
			Thread.sleep(1000);
			driver.switchTo().frame(driver.findElement(By.xpath("//iFrame")));
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[text()='Done']"))));
			driver.findElement(By.xpath("//button[text()='Done']")).click();
			driver.switchTo().defaultContent();
//		13. Click on Dashboard tab
			driver.executeScript("arguments[0].click();",findElement );
//		14. Verify the Dashboard is Created
//			driver.findElements(By.xpath("//th[@aria-label='Dashboard Name']//a"))
			boolean bFlag = false;
			 int pos=0;
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//th[@data-label='Dashboard Name']//a"))));
			List<WebElement> dataElements = driver.findElements(By.xpath("//th[@data-label='Dashboard Name']//a"));
			for (int i = 0; i < dataElements.size(); i++) {
				if(dataElements.get(i).getText().equalsIgnoreCase("Anusuya_Workout")) {
					System.out.println("Dashboard is created successfully");
					bFlag = true;
					pos = i;
					break;
				}else {
					System.out.println("Dashboard is not found");
				}
			}
			//Check with Karthick
			
			if(bFlag) {
//				15. Click on the newly created Dashboard
				driver.executeScript("arguments[0].click();", dataElements.get(pos));
				Thread.sleep(10000);
//				16. Click on Subscribe
				driver.switchTo().frame(driver.findElement(By.xpath("//iFrame")));
				Thread.sleep(5000);
				List<WebElement> subscribeElements = driver.findElements(By.xpath("(//button[text()='Subscribe'])[1]"));
				System.out.println("No. of subscribe elements found: " + subscribeElements.size());
				for (WebElement webElement : subscribeElements) {
					
				
				if(webElement.isDisplayed()) {
						Thread.sleep(1000);
//						driver.switchTo().frame(driver.findElement(By.xpath("//iFrame")));
						driver.executeScript("arguments[0].click();",webElement);
						break;
						}
				
				}
//				wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//iFrame"))));
			
				
				Thread.sleep(1000);
				driver.switchTo().defaultContent();
				wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[text()='Edit Subscription']"))));
//				14. Select Frequency as "Daily"
				driver.findElement(By.xpath("//span[text()='Daily']")).click();
//				15. Time as 10:00 AM
				WebElement selectionElement = driver.findElement(By.xpath("//span[text()='Time']/following::Select"));
				Select selection = new Select(selectionElement);
				selection.selectByVisibleText("10:00 am");
//				16. Click on Save
				driver.findElement(By.xpath("//span[text()='Save']")).click();
			}
			//tr[]//span[text()='Show actions']
//			int k=1;
//			do {
//			String text = driver.findElement(By.xpath("//table//tr["+ k +"]/th[1]//a")).getText();
//			if(text=="Anusuya_Workout") {
//				bFlag = true;
//				break;
//			}else {
//				continue;
//				k++;
//			}
//			
//			}while(bFlag == true)



//		17. Verify "You started Dashboard Subscription" message displayed or not
//		18. Click on Dashboards tab
			driver.executeScript("arguments[0].click();",findElement );
//		19. Verify the newly created Dashboard is available
//		20. Click on dropdown for the item
//		21. Select Delete
//		22. Confirm the Delete
//		23. Verify the item is not available under Private Dashboard folder

	}

}
		}
	}
