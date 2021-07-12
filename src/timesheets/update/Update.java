package timesheets.update;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Map;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;

import timesheets.gui.other.CustomOptionPane;
import timesheets.logging.Logger;
import timesheets.resources.LanguageManager;

public class Update {

	private static final Logger logger = new Logger(Update.class);
	private static Version currentVersion = new Version("2.0.0");
	
	private final static String API_URL = "https://api.github.com/repos/CaptainKills/Timesheets/releases";
	private final static String DOWNLOAD_URL = "https://github.com/CaptainKills/Timesheets/releases";
	private static HttpURLConnection conn;
	
	private static Map<String, String> lang = LanguageManager.language;
	private static String dialogTitleSuccess = lang.get("jop_update_title_success");
	private static String dialogMsgSuccess = lang.get("jop_update_msg_success");
	private static String dialogTitleFail = lang.get("jop_update_title_fail");
	private static String dialogMsgFail = lang.get("jop_update_msg_fail");
	private static Object[] buttonOptions = {lang.get("jop_update_option_ok"), lang.get("jop_update_option_open")};

	public static void checkForUpdates(boolean displayOnFail) {
		logger.info("Checking for program updates...");
		Version latestVersion = downloadLatestRelease();
		
		if(latestVersion.isNewerThan(currentVersion)) {
			logger.info("New version available! Current=" + currentVersion + ", Latest=" + latestVersion);
			
			CustomOptionPane cop = new CustomOptionPane("Update Success");
			cop.setText(dialogTitleSuccess, dialogMsgSuccess);
			cop.setConfig(JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
			cop.setButtons(buttonOptions);
			
			int status = cop.showDialog();
			if(status == JOptionPane.NO_OPTION) {
				try {
					logger.debug("Opening URL with desktop.");
					Desktop.getDesktop().browse(URI.create(DOWNLOAD_URL));
				} catch(IOException e) {
					logger.error("COULD NOT OPEN URL WITH DESKTOP!", e);
				}
			}
		} else {
			logger.info("No new version available.");
			
			if(displayOnFail) {
				CustomOptionPane cop = new CustomOptionPane("Update Fail");
				cop.setText(dialogTitleFail, dialogMsgFail);
				cop.setConfig(JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
				cop.showDialog();
			}
		}
		
	}
	
	private static Version downloadLatestRelease() {
		BufferedReader reader;
		String line;
		StringBuffer responseContent = new StringBuffer();
		Version latestVersion = currentVersion;

		try {
			logger.debug("API Opening Connection...");
			URL apiURL = new URL(API_URL);
			conn = (HttpURLConnection) apiURL.openConnection();

			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);

			int status = conn.getResponseCode();
			logger.debug("API Connection Status: " + status);

			if (status > 299) {
				reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}

				logger.warn("API Connection Problem: " + responseContent.toString());
				reader.close();
			} else {
				reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			}
			
			latestVersion = getNewestVersion(responseContent.toString());
		} catch(UnknownHostException e) {
			logger.warn("Could not resolve hostname! Probably no internet connection.");			
		}catch (IOException e) {
			logger.error("COULD NOT CHECK FOR UPDATES!", e);
		} finally {
			conn.disconnect();
		}
		
		return latestVersion;
	}

	private static Version getNewestVersion(String responseBody) {
		JSONArray releases = new JSONArray(responseBody);
		JSONObject latest_release = releases.getJSONObject(0); // get latest release
		String tag = latest_release.getString("tag_name");

		return new Version(tag);
	}

	public static Version getCurrentVersion() {
		return currentVersion;
	}

}
