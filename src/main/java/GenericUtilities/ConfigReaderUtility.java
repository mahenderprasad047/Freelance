package GenericUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReaderUtility 
{
	static Properties pro;
	public synchronized static String GetConfigProperty(String Key)
	{
		CheckForInstance();
		return pro.getProperty(Key);
	}
	
	public static Properties CheckForInstance()
	{
		if(pro == null)
		{
			//System.out.println("LOG:INFO - PropertyFile instance is NOT available");
			pro = new Properties();
			try 
			{
				pro.load(new FileInputStream(new File(System.getProperty("user.dir")+"/Config/Config.properties")));
			} 
			catch (FileNotFoundException e) {
				System.out.println("File does not exist "+ e.getMessage());
			} 
			catch (IOException e) {
				System.out.println("Could not load the file "+ e.getMessage());
			}
			return pro;
		}
		else
		{
			//System.out.println("LOG:INFO - PropertyFile instance is already available");
			return pro;
		}
		
	}
}
