package assignment7.day7;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowHandlingExercise {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/login");
		String title=driver.getTitle();
		System.out.println(title);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.id("username")).sendKeys("demosalesmanager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();
		driver.findElement(By.xpath("(//a/img[@src='/images/fieldlookup.gif'])[1]")).click();
		//String windowHandle = driver.getWindowHandle();
		//driver.switchTo().window(windowHandle);
		
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowHandleList=new ArrayList<String>(windowHandles);
		windowHandleList.get(1);
		driver.switchTo().window(windowHandleList.get(1));
		
		driver.findElement(By.xpath("//a[@class='linktext']")).click();
		driver.switchTo().window(windowHandleList.get(0));
		
		driver.findElement(By.xpath("(//a/img[@src='/images/fieldlookup.gif'])[2]")).click();
		Set<String> windowHandles1 = driver.getWindowHandles();
		List<String> windowHandleList1=new ArrayList<String>(windowHandles1);
		windowHandleList1.get(1);
		driver.switchTo().window(windowHandleList1.get(1));
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[2]/a")).click();
		driver.switchTo().window(windowHandleList1.get(0));
		driver.findElement(By.xpath("//a[text()='Merge']")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		String title2 = driver.getTitle();
		System.out.println(driver.findElement(By.id("sectionHeaderTitle_contacts")).getText() +title2);
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
