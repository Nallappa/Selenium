
package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utility_Component  extends Page_Object_Component  {
	
	String o = "nalli";
	
	public static String readValue(String key) throws IOException
	{
		
		FileInputStream fis = new FileInputStream(new File("D:\\Latest_Selenium\\Global_Properties.txt"));
		
		Properties prop = new Properties();
		
		prop.load(fis);
		return prop.getProperty(key);
			
	}

}


