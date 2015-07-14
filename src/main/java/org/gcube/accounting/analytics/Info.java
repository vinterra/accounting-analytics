/**
 * 
 */
package org.gcube.accounting.analytics;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONObject;


/**
 * @author Luca Frosini (ISTI - CNR) http://www.lucafrosini.com/
 *
 */
public class Info {

	protected Calendar date;
	protected JSONObject value;
	
	/**
	 * @param calendar
	 * @param value
	 */
	public Info(Calendar date, JSONObject value) {
		super();
		this.date = date;
		this.value = value;
	}

	/**
	 * @return the calendar
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * @param calendar the calendar to set
	 */
	public void setDate(Calendar calendar) {
		this.date = calendar;
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
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS z");
		return String.format("Date : %s, Value : %s", dateFormatter.format(date.getTime()), value.toString());
	}
}
