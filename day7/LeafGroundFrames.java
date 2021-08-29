package assignment7.day7;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LeafGroundFrames {
	
	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leafground.com/pages/frame.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//navigate to frame
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='wrapframe'][1]/iframe")));		
		
		//access the click me button element
		WebElement clickMeFirst = driver.findElement(By.xpath("//button[@id='Click']"));
		File src1 = clickMeFirst.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/seat2.png");
		FileUtils.copyFile(src1, dst);
		
		//switches to main page
		driver.switchTo().defaultContent();
		
		List<WebElement> f = driver.findElements(By.tagName("iframe"));
	    System.out.println("Total number of iframes in this page: " + f.size());
	}

}
