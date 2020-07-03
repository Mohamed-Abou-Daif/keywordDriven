package utilites;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {

	// Load Properties File From Folder
	
		public static Properties config = 
				loadProperties(System.getProperty("user.dir")+"\\src\\main\\java\\config\\config.properties");
		
		public static Properties loadProperties(String path) {
			
			Properties prop = new Properties();
			// Stream for reading file
			try {
				FileInputStream stream = new FileInputStream(path);
				prop.load(stream);
			} catch (FileNotFoundException e) {
				System.out.println("Error Occured" + e.getMessage());
			} catch (IOException e) {
				System.out.println("Error Occured" + e.getMessage());
			}catch (NullPointerException e) {
				System.out.println("Error Occured" + e.getMessage());
			}
			
			
			return prop;
		}
}
