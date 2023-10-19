package testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import core.Page;

public class AlertDemo extends Page
{
	public void test() throws Exception
	{
		openBrowser("chrome","https://mail.rediff.com/cgi-bin/login.cgi");
		
		driver.findElement(By.xpath("//*[@type = 'submit']")).click();
		
		Thread.sleep(7000);
		
		Alert a = driver.switchTo().alert();
		
		String txt = a.getText();
		System.out.println(txt);
		
		a.accept(); // close alert
	}

	public static void main(String[] args) throws Exception 
	{
		// TODO Auto-generated method stub
		AlertDemo s = new AlertDemo();
		s.test();
	}

}
