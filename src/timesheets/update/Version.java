package timesheets.update;

public class Version {
	private int major;
	private int minor;
	private int patch;

	public Version(int major, int minor, int patch) {
		this.major = major;
		this.minor = minor;
		this.patch = patch;
	}

	@Override
	public String toString() {
		return "Version [" + major + "." + minor + "." + patch + "]";
	}

	public static Version parse(String tag) throws NumberFormatException {
		String[] tag_versions = tag.split("\\.");
		Version version = new Version(0, 0, 0);

		if (tag_versions.length == 1) {
			int major = Integer.parseInt(tag_versions[0]);
			version = new Version(major, 0, 0);
		} else if (tag_versions.length == 2) {
			int major = Integer.parseInt(tag_versions[0]);
			int minor = Integer.parseInt(tag_versions[1]);
			version = new Version(major, minor, 0);
		} else if (tag_versions.length == 3) {
			int major = Integer.parseInt(tag_versions[0]);
			int minor = Integer.parseInt(tag_versions[1]);
			int patch = Integer.parseInt(tag_versions[2]);
			version = new Version(major, minor, patch);
		} else {
			throw new NumberFormatException("Tag string is incompatible with Version object format. Tag=" + tag);
		}

		return version;
	}

	public boolean isNewerThan(Version current) {
		boolean isNewer = false;

		if (this.getMajorVersion() > current.getMajorVersion()) {
			isNewer = true;
		} else if (this.getMinorVersion() > current.getMinorVersion()
				&& this.getMajorVersion() == current.getMajorVersion()) {
			isNewer = true;
		} else if (this.getPatchVersion() > current.getPatchVersion()
				&& this.getMajorVersion() == current.getMajorVersion()
				&& this.getMinorVersion() == current.getMinorVersion()) {
			isNewer = true;
		}

		return isNewer;
	}

	public Version(String version) {
		Version v = Version.parse(version);
		this.major = v.getMajorVersion();
		this.minor = v.getMinorVersion();
		this.patch = v.getPatchVersion();
	}

	public int getMajorVersion() {
		return this.major;
	}

	public int getMinorVersion() {
		return this.minor;
	}

	public int getPatchVersion() {
		return this.patch;
	}

}
