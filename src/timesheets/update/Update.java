package timesheets.update;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;

import timesheets.gui.lists.DisplayList;
import timesheets.gui.lists.PanelList;
import timesheets.logging.Logger;

public class Update {

	private static final Logger logger = new Logger(Update.class);
	private static Version currentVersion = new Version("2.0.0");
	
	private final static String url = "https://api.github.com/repos/CaptainKills/Timesheets/releases";
	private static HttpURLConnection conn;

	public static void checkForUpdates(boolean displayOnFail) {
		logger.info("Checking for program updates...");
		Version latestVersion = downloadLatestRelease();
		
		if(latestVersion.isNewerThan(currentVersion)) {
			logger.info("New version available! Current=" + currentVersion + ", Latest=" + latestVersion);
			JOptionPane.showMessageDialog(PanelList.mainPanel, DisplayList.updateMessagePane,
					latestVersion + " Available!", JOptionPane.PLAIN_MESSAGE);
		} else {
			logger.info("No new version available.");
			if(displayOnFail) {
				JOptionPane.showMessageDialog(PanelList.mainPanel,
						"There are no new updates. The latest version is currently installed.",
						"No Update Available.", JOptionPane.PLAIN_MESSAGE);
			}
		}
		
	}
	
	private static Version downloadLatestRelease() {
		BufferedReader reader;
		String line;
		StringBuffer responseContent = new StringBuffer();
		Version latestVersion = currentVersion;

		try {
			logger.info("API Opening Connection...");
			URL apiURL = new URL(url);
			conn = (HttpURLConnection) apiURL.openConnection();

			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);

			int status = conn.getResponseCode();
			logger.info("API Connection Status: " + status);

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

	public static String getUpdateText() {
		StringBuilder builder = new StringBuilder();

		builder.append("<html><body style=\"font-family:Arial;font-size:18; text-align:center\">");
		builder.append("A new update is available!<br>");
		builder.append("You can download the new release via:<br>");
		builder.append("<a href=\"https:/github.com/CaptainKills/Timesheets/releases/\">www.github.com</a>");
		builder.append("</body></html>");

		return builder.toString();
	}

	public static Version getCurrentVersion() {
		return currentVersion;
	}

}
