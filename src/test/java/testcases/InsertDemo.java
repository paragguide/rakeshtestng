package testcases;

import core.Page;
import java.sql.*;

public class InsertDemo extends Page
{

	public void insertData() throws Exception
	{
		openWBConnection("Student.xlsx");
		
		Statement stm = con.createStatement();
		
		stm.executeUpdate("insert into Sheet1 (name,age) values ('ironman',43)");
		
		closeWBConnection(); // compulsory 
		
		System.out.println("inserted..");
	}
	public static void main(String[] args) throws Exception 
	{
		InsertDemo i = new InsertDemo();
		i.insertData();   // keep excel closed while inserting data
	}

}
