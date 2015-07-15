/**
 * 
 */
package org.gcube.accounting.analytics;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import org.json.JSONObject;


/**
 * @author Luca Frosini (ISTI - CNR) http://www.lucafrosini.com/
 *
 */
public class Info {

	protected Calendar calendar;
	protected JSONObject value;
	
	/**
	 * @param calendar
	 * @param value
	 */
	public Info(Calendar calendar, JSONObject value) {
		super();
		this.calendar = calendar;
		this.value = value;
	}

	/**
	 * @return the calendar
	 */
	public Calendar getCalendar() {
		return calendar;
	}

	/**
	 * @param calendar the calendar to set
	 */
	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	/**
	 * @return the value
	 */
	public JSONObject getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(JSONObject value) {
		this.value = value;
	}
	
	@Override
	public String toString(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS z");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		return String.format("Date : %s, Value : %s", simpleDateFormat.format(calendar.getTime()), value.toString());
	}
}
