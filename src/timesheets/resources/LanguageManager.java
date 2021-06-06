package timesheets.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

import timesheets.logging.Logger;

public class LanguageManager {
	
	private static final Logger logger = new Logger(LanguageManager.class);
	private static InputStreamReader lang_isr = new InputStreamReader(ResourceHandler.lang_en);
	private static BufferedReader lang_reader = new BufferedReader(lang_isr);
	
	public static Map<String, String> language = new LinkedHashMap<String, String>();
	
	public static void initialise() {	
		logger.info("Loading Language File.");
		
		try {
			String line;
			
			while ((line = lang_reader.readLine()) != null) {
				if(!line.startsWith("#") && !line.isEmpty()) {
					String[] segments = line.split("=");
					
					if(segments.length == 1) {
						return;
					}
					
					String object = segments[0];
					String value = segments[1].replaceAll("\"", "");

					language.put(object, value);
					System.out.println("Object: " + object + ", Value: " + value);
				}
			}
		} catch(IOException e) {
			logger.error("COULD NOT READ FROM LANGUAGE FILE!", e);
		}
	}

}
