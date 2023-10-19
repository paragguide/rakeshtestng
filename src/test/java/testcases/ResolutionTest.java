package testcases;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.StringTokenizer;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.relevantcodes.extentreports.LogStatus;

import core.Page;
import mail.TestConfig;
import mail.monitoringMail;

public class ResolutionTest extends Page
{
	
@FindBy(xpath = xpath.AllXpath.img)
WebElement img;

	public void test() //throws Exception
	{
		      try {
		generateLog("responsive","dest1");
		generateReport("responsive");
	//	startVideo("responsive");
		openWBConnection("responsive1.xlsx"); // inherited
		
		log.debug("connectedc to excel..");
		test.log(LogStatus.PASS, "connected to excel");
		
		Statement stm = con.createStatement();
		
	ResultSet rs =	stm.executeQuery("select * from Sheet1");
	 while(rs.next())
	 {
		 // reading data from excel..
		 String browser = rs.getString(1);
		 String url = rs.getString(2);
		 String resolution = rs.getString(3);
		 
		 log.debug("browser "+browser+" url"+url+" resolution "+resolution);
		 test.log(LogStatus.INFO, "browser "+browser+" url"+url+" resolution "+resolution);
		   // break resolution
		 StringTokenizer str = new StringTokenizer(resolution,",");
		 int w = Integer.parseInt(str.nextToken().trim());
		 int h = Integer.parseInt(str.nextToken().trim());
		 
		 String expw = rs.getString(4);
		 String exph = rs.getString(5);
		 
		 log.debug("Exp w "+expw+" exph "+exph);
		 test.log(LogStatus.INFO, "Exp w "+expw+" exph "+exph);
		 openBrowser(browser,url); // inherited
		 
		 driver.manage().window().setSize(new Dimension(w,h));
		 
		 log.debug("browser opened.. pass");
		 test.log(LogStatus.PASS, "browser opened.. pass");
		 // screen shot
		 takeScreenShot("img-"+resolution);
		 
		 // get data from website
		String actualw = img.getCssValue("width");
		String actualh = img.getCssValue("height");
		
		log.debug("actyual w"+actualw+" actual h"+actualh);
		
		 if(expw.equals(actualw) && exph.equals(actualh))
		 {
			 // pass
			 stm.executeUpdate("insert into Sheet2(resolution,expected,actual,status) values('"+resolution+"','"+expw+" x "+exph+"','"+actualw+" x "+actualh+"','Pass')");
			 closeWBConnection(); // compulsory
			 
			   // reopen connection
			 openWBConnection("responsive1.xlsx"); // inherited
				
			 stm = con.createStatement();
			 
			 closeBrowser();
				
			 log.debug("Pass");
			 test.log(LogStatus.PASS, "pass");
		 }
		 else
		 {
			 // fail
			 stm.executeUpdate("insert into Sheet2(resolution,expected,actual,status) values('"+resolution+"','"+expw+" x "+exph+"','"+actualw+" x "+actualh+"','Fail')");
			 closeWBConnection(); // compulsory
			 
			   // reopen connection
			 openWBConnection("responsive1.xlsx"); // inherited
				
			 stm = con.createStatement();
			 
			 closeBrowser();
			 
			 log.debug("fail");
			 test.log(LogStatus.FAIL, "fail");
			
		 }
	 } // end of while loop
		      } // end of try
		      catch(Exception e)
		      {
		    	  log.debug(e.getMessage());
		    	  test.log(LogStatus.ERROR, e.getMessage());
		      }
		      
		      closeReport(); // compulsory
		      try {
			//	stopVideo(); // compulosory
				
				// send email..
				monitoringMail m = new monitoringMail();
		//		m.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, TestConfig.messageBody, TestConfig.attachmentPath, TestConfig.attachmentName);
			System.out.println("mail sent..");
		      
		      } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		      
	} // end of method
	public static void main(String[] args) throws Exception
	{
		ResolutionTest r = new ResolutionTest();
		r.test();
	}

}
