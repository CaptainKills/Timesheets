package timesheets.report;

import java.util.Map;

import timesheets.resources.LanguageManager;

public enum ReportOutputType {
	TODAY,
	WEEK,
	MONTH,
	SPECIFIC;
	
	@Override
	public String toString() {
		Map<String, String> lang = LanguageManager.language;
		
		switch(this) {
		case TODAY: return lang.get("today_const");
		case WEEK: return lang.get("week_const");
		case MONTH: return lang.get("month_const");
		case SPECIFIC: return lang.get("specific_const");
		default: return this.toString();
		}
	}
}
