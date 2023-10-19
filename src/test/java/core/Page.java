package core;



import java.io.File;
import java.io.FileInputStream;
//import java.sql.*; // jdbc library
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

//import atu.testrecorder.ATUTestRecorder;
//import atu.testrecorder.exceptions.ATUTestRecorderException;


public class Page 
{
	 public WebDriver driver = null; // global variable
	 public Connection con = null; // Connection interface from java.sql 
	 public Logger log = null; 
	 public ExtentReports report = null;
	 public ExtentTest test = null;
	 //public ATUTestRecorder recorder = null;
	 
	public void openBrowser(String browser,String url)   // define
	{
		if(browser.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("edge"))
		{
			driver = new EdgeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		
	//	driver.get(url); // not prefer cannot go back / forward / refresh
	
		     // or.....
		driver.navigate().to(url); // prefer can go back / forward / refrerh
		
		PageFactory.initElements(driver, this); // for @FindBy
				
		// implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		
		driver.manage().window().maximize(); // full screen of laptop
	}
	
	public void closeBrowser()
	{
		driver.quit();
	}
	
	public void openWBConnection(String wbname) throws Exception
	{
		Class.forName("com.googlecode.sqlsheet.Driver");  // Register Driver
		String wbpath = System.getProperty("user.dir")+"//src//test//java//excel//"+wbname;
		con = DriverManager.getConnection("jdbc:xls:file:"+wbpath);
	}
	
	public void closeWBConnection() throws Exception
	{
		con.commit();
		con.close();
	}
	
	public void takeScreenShot(String scrname) throws Exception
	{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); // store file to temprary location
		//Now you can do whatever you need to do with it, for example copy somewhere download org.apache.commons.io.FileUtils class API set classpath and use this class to copy.
		String screenshotpath = System.getProperty("user.dir")+"\\src\\test\\java\\srcshot\\"+scrname+".jpeg";
		
		FileUtils.copyFile(scrFile, new File(screenshotpath));

	}
	
	public void generateLog(String logname,String obj) throws Exception
	{
		Properties p = new Properties();
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//"+logname+".properties");
		
		p.load(fis);
		p.put("log4j.appender."+obj+".File", System.getProperty("user.dir")+"//src//test//java//logs//"+logname+".log");
		
		PropertyConfigurator.configure(p);
		log = Logger.getLogger(logname);
	}
	
	public void generateReport(String reportname) throws Exception
	{
		report = new ExtentReports(System.getProperty("user.dir")+"//src//test//java//reports//"+reportname+".html");
	
	test =	report.startTest(reportname);
	
	}
	
	public void closeReport()
	{
		report.endTest(test);
		report.flush();
	}
	   /*
	public void startVideo(String vname) throws Exception
	{
		recorder = new ATUTestRecorder(System.getProperty("user.dir")+"//src//test//java//video",vname,false); // "RecordedVideo-"+dateFormat.format(date)
		recorder.start();
	}
	
	public void stopVideo() throws Exception
	{
		recorder.stop();
	}  */
	
	}
