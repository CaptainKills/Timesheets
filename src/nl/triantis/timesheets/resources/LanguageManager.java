package nl.triantis.timesheets.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

import nl.triantis.timesheets.Settings;
import nl.triantis.timesheets.logging.Logger;

public class LanguageManager {
	
	private static final Logger logger = new Logger(LanguageManager.class);
	public static Map<String, String> language = new LinkedHashMap<String, String>();
	
	public static void initialise() {	
		logger.info("Loading Language File.");
		
		InputStreamReader lang_isr;
		String selectedLanguage = Settings.settings.get("language");
		
		switch(selectedLanguage) {
		case "en":
			lang_isr = new InputStreamReader(ResourceHandler.lang_en);
			break;
			
		case "nl":
			lang_isr = new InputStreamReader(ResourceHandler.lang_nl);
			break;
			
		default:
			logger.warn("Inidentified Language Setting. Default Language Selected.");
			lang_isr = new InputStreamReader(ResourceHandler.lang_en);
			break;				
		}
		
		loadLanguage(lang_isr);
	}
	
	private static void loadLanguage(InputStreamReader isr) {
		try {
			String line;
			BufferedReader lang_reader = new BufferedReader(isr);
			
			while ((line = lang_reader.readLine()) != null) {
				if(!line.startsWith("#") && !line.isEmpty()) {
					String[] segments = line.split("=");
					
					if(segments.length == 1) {
						return;
					}
					
					String object = segments[0];
					String value = segments[1];
					
					if(value.startsWith("\"")) {
						value = value.substring(1);
					}
					
					if(value.endsWith("\"")) {
						value = value.substring(0, value.length()-1);
					}

					language.put(object, value);
				}
			}
		} catch(IOException e) {
			logger.error("COULD NOT READ FROM LANGUAGE FILE!", e);
		}
	}

}
