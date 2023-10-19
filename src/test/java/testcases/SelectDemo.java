package testcases;

import java.sql.ResultSet;
import java.sql.Statement;

import core.Page;

public class SelectDemo extends Page
{
	
	public void selectdata() throws Exception
	{
        openWBConnection("Student.xlsx");
		
		Statement stm = con.createStatement();
		
	ResultSet rs =	stm.executeQuery("select * from Sheet1");
	  while(rs.next())
	  {
		  String n = rs.getString(1);
		  int a = rs.getInt(2);
		  
		  System.out.println(n+" - "+a);
	  }
	  
	  closeWBConnection();
	}

	public static void main(String[] args) throws Exception 
	{
		SelectDemo s = new SelectDemo();
		s.selectdata();
	}

}
