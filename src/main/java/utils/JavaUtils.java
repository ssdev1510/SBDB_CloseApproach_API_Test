package utils;
/* Sahil Singla / sahil.9singla@gmail.com
 * RestAssured Utility Class to control
 * different methods of APIs
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class JavaUtils {
	
/*Getting the value from config.property file 
 * 
 * @Return: Value of passed key from config.properties file
 * 	
 */

	public static String getConfigProperty(String key) throws IOException {

		Properties prop = new Properties();
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		FileInputStream fstream = new FileInputStream("src//test//resources//configs//config.properties");
		prop.load(fstream);
		
		return prop.getProperty(key);
	}

	
//Getting the values from excel files.	
//Screenshots	
}

