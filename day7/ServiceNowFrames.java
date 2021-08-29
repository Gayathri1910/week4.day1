package assignment7.day7;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNowFrames {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://dev113545.service-now.com/");
		String title=driver.getTitle();
		System.out.println(title);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement userNameFrame = driver.findElement(By.xpath("//div[@class='navpage-main-left ng-isolate-scope']/iframe"));
		driver.switchTo().frame(userNameFrame);
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("w6hnF2FRhwLC");
		driver.findElement(By.xpath("//button[text()='Log in']")).click();
		WebElement filterNavigator = driver.findElement(By.id("filter"));
		filterNavigator.sendKeys("incident");
		filterNavigator.sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("(//div[text()='All'])[2]")).click();
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click();
		driver.findElement(By.id("lookup.incident.caller_id")).click();
		
		Set<String> usersWindow=driver.getWindowHandles();
		List<String> usersWindowList=new ArrayList<String>(usersWindow);
		usersWindowList.get(1);
		driver.switchTo().window(usersWindowList.get(1));
		driver.findElement(By.xpath("(//a[@class='glide_ref_item_link'])[1]")).click();
		driver.switchTo().window(usersWindowList.get(0));
		driver.switchTo().defaultContent();
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("incident.short_description")).sendKeys("Short description is given a value now");
		String incidentNumber = driver.findElement(By.id("incident.number")).getAttribute("value");
		System.out.println("Incident Number is : " +incidentNumber);
		driver.findElementByXPath("//button[text()='Submit']").click();
		driver.switchTo().window(usersWindowList.get(0));
		driver.switchTo().frame("gsft_main");
		WebElement searchBox = driver.findElement(By.xpath("(//input[@placeholder='Search'])[2]"));
		searchBox.sendKeys(incidentNumber);
		searchBox.sendKeys(Keys.ENTER);
		
		String createdId = driver.findElement(By.xpath("//a[@class='linked formlink']")).getText();
		if(createdId.equals(incidentNumber))
		{
		System.out.println("Incident number is created successfully " +createdId);
		}
		
		File src1 = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/servicenow1.png");
		FileUtils.copyFile(src1, dst);
		
		
		
		
		
		
	}
}
