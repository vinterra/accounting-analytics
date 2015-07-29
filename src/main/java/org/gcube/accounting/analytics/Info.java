/**
 * 
 */
package org.gcube.accounting.analytics;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;


/**
 * @author Luca Frosini (ISTI - CNR) http://www.lucafrosini.com/
 *
 */
public class Info {

	protected Date date;
	protected JSONObject value;
	
	/**
	 * @param calendar
	 * @param value
	 */
	public Info(Date date, JSONObject value) {
		super();
		this.date = date;
		this.value = value;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
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
		simpleDateFormat.setTimeZone(TemporalConstraint.DEFAULT_TIME_ZONE);
		return String.format("Date : %s, Value : %s", simpleDateFormat.format(date), value.toString());
	}
}
