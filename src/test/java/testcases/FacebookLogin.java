package testcases;

import java.sql.ResultSet;
import java.sql.Statement;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.Page;

public class FacebookLogin extends Page
{
	@FindBy(xpath = xpath.AllXpath.uid)
	WebElement uid;
	
	@FindBy(xpath = xpath.AllXpath.pwd)
	WebElement pwd;
	
	@FindBy(xpath = xpath.AllXpath.btn)
	WebElement btn;
	
	@FindBy(xpath = xpath.AllXpath.err)
	WebElement err;
	
	public void test() throws Exception
	{
		
		generateLog("facebook","dest2");
		
		//openBrowser("chrome","https://www.facebook.com/");
		
		openWBConnection("Student.xlsx");
		Statement stm = con.createStatement();
		
		ResultSet rs = stm.executeQuery("select * from Sheet2");
		
		  while(rs.next())
		  {
			  openBrowser("chrome","https://www.facebook.com/");
			  
			  log.debug("facebook open..");
			  
			  String id = rs.getString(1); // from excel
			  String pd = rs.getString(2); // from excel
			  
			  log.debug("checking id "+id+" and pwd "+pd);
			  
			  uid.clear();
			  uid.sendKeys(id);
			  
			  pwd.clear();
			  pwd.sendKeys(pd);
			  
			  btn.click();
			      try {
			 String errmsg = err.getText();
			 System.out.println(errmsg);
			 
			 stm.executeUpdate("insert into Sheet3(uid,pwd,status) values('"+id+"','"+pd+"','not member')");
			 closeWBConnection(); 
			   // reopen
			 openWBConnection("Student.xlsx");
		      stm = con.createStatement();
		      
		   //   driver.navigate().back();
				
		      closeBrowser();
			      }
			      catch(Exception e)
			      {
			    	 // e.printStackTrace();
			    	  // no err message
			    	  stm.executeUpdate("insert into Sheet3(uid,pwd,status) values('"+id+"','"+pd+"','Member')");
						 closeWBConnection();
						 openWBConnection("Student.xlsx");
					      stm = con.createStatement();
		//	 driver.navigate().back();		
					      closeBrowser();
			      }
		  }
		
		
		
	}

	public static void main(String[] args) throws Exception
	{
		FacebookLogin f = new FacebookLogin();
		f.test();
	}

}
