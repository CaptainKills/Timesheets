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
	private static Version current_version = new Version("2.0.0");
	private static Version latest_version = new Version("0.0.0");

	private final static String url = "https://api.github.com/repos/CaptainKills/Timesheets/releases";
	private static HttpURLConnection conn;

	public static void checkForUpdates() {
		BufferedReader reader;
		String line;
		StringBuffer responseContent = new StringBuffer();

		try {
			logger.info("Checking for program updates...");
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
			latest_version = getNewestVersion(responseContent.toString());
			checkVersion();
		} catch(UnknownHostException e) {
			logger.warn("Could not resolve hostname! Probably not internet connection.");
		}catch (IOException e) {
			logger.error("COULD NOT CHECK FOR UPDATES!", e);
		} finally {
			conn.disconnect();
		}
	}

	private static Version getNewestVersion(String responseBody) {
		JSONArray releases = new JSONArray(responseBody);
		JSONObject latest_release = releases.getJSONObject(0); // get latest release
		String tag = latest_release.getString("tag_name");

		return new Version(tag);
	}

	private static void checkVersion() {
		if (latest_version.isNewer(getCurrentVersion())) {
			logger.info("New version available! Current=" + getCurrentVersion() + ", Latest=" + latest_version);
			JOptionPane.showMessageDialog(PanelList.mainPanel, DisplayList.updateMessagePane,
					latest_version + " Available!", JOptionPane.PLAIN_MESSAGE);
		} else {
			logger.info("No new version available. Current=" + getCurrentVersion() + ", Latest=" + latest_version);
		}
	}

	public static String getUpdateText() {
		StringBuilder builder = new StringBuilder();

		builder.append("<htm><body style=\"font-family:Arial;font-size:18; text-align:center\">");
		builder.append("A new update is available!<br>");
		builder.append("You can download the new release via:<br>");
		builder.append("<a href=\"https:/github.com/CaptainKills/Timesheets/releases/\">www.github.com</a>");
		builder.append("</body></html>");

		return builder.toString();
	}

	public static boolean hasNewVersion() {
		if (latest_version.isNewer(current_version)) {
			return true;
		} else {
			return false;
		}
	}

	public static Version getCurrentVersion() {
		return current_version;
	}

	public static void setCurrentVersion(Version current_version) {
		Update.current_version = current_version;
	}

}
