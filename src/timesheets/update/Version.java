package timesheets.update;

public class Version {
	private int major;
	private int minor;
	private int patch;

	public Version(int major, int minor, int patch) {
		this.setMajorVersion(major);
		this.setMinorVersion(minor);
		this.setPatchVersion(patch);
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
			version = new Version(major);
		} else if (tag_versions.length == 2) {
			int major = Integer.parseInt(tag_versions[0]);
			int minor = Integer.parseInt(tag_versions[1]);
			version = new Version(major, minor);
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

	public boolean isNewer(Version current) {
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

	public Version(int major, int minor) {
		this.setMajorVersion(major);
		this.setMinorVersion(minor);
		this.setPatchVersion(0);
	}

	public Version(int major) {
		this.setMajorVersion(major);
		this.setMinorVersion(0);
		this.setPatchVersion(0);
	}

	public Version(String version) {
		Version v = Version.parse(version);
		this.setMajorVersion(v.getMajorVersion());
		this.setMinorVersion(v.getMinorVersion());
		this.setPatchVersion(v.getPatchVersion());
	}

	public int getMajorVersion() {
		return major;
	}

	public void setMajorVersion(int major) {
		this.major = major;
	}

	public int getMinorVersion() {
		return minor;
	}

	public void setMinorVersion(int minor) {
		this.minor = minor;
	}

	public int getPatchVersion() {
		return patch;
	}

	public void setPatchVersion(int patch) {
		this.patch = patch;
	}

}
