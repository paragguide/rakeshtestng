
package mail;

public class TestConfig{


	
	public static String server="smtp.gmail.com";
	public static String from = "roliguide@gmail.com";
	public static String password = "lsiedbtktyhituf"; // password generated by gmail security settings
	public static String[] to ={"rakeshmahajan671@gmail.com","paragguide@yahoo.co.in"};
	public static String subject = "Report 29-9-23 ";
	
	public static String messageBody ="title of report any TestMessage";
	public static String attachmentPath= System.getProperty("user.dir")+"\\src\\test\\java\\reports\\responsive.html";
	
	public static String attachmentName="TestResults.html";
	
	
	
		
}
