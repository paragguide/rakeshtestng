package testcases;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import core.Page;

public class AddToKart extends Page
{

	public void test() throws Exception
	{
		openBrowser("chrome","https://www.amazon.in/");
		
		driver.findElement(By.xpath("//*[@id=\"twotabsearchtextbox\"]")).sendKeys("Sun Glasses");
		
		Actions a = new Actions(driver);
		a.sendKeys(Keys.ENTER).perform();
		
		driver.findElement(By.partialLinkText("Half Rim UV Protection Aviator Sunglasses For Men Women Latest Stylish")).click();
		
		Thread.sleep(6000);
		
		Set <String> s = driver.getWindowHandles();
		Iterator <String> it = s.iterator();
		
		String currentmain = it.next();
		
		String producttab = it.next();
		
		driver.switchTo().window(producttab);
		
		driver.findElement(By.xpath("//*[@id = 'add-to-cart-button']")).click();
	}
	public static void main(String[] args) throws Exception 
	{
		AddToKart s = new AddToKart();
		s.test();
	}

}
