/**
 * 
 */
package org.gcube.accounting.analytics;

import java.util.Calendar;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Luca Frosini (ISTI - CNR) http://www.lucafrosini.com/
 *
 */
public class NumberedFilter extends Filter {

	protected Double d;
	
	public NumberedFilter(String key, String value, Number n) {
		super(key, value);
		this.d = n.doubleValue();
	}
	
	public NumberedFilter(Filter filter, Number n) {
		this(filter.key, filter.value, n);
	}
	
	public NumberedFilter(Filter filter, Map<Calendar, Info> timeSeries, String orderingProperty) throws JSONException {
		super(filter.key, filter.value);
		
		for(Info info : timeSeries.values()){
			JSONObject value = info.getValue();
			
			if(d == null){
				d = value.getDouble(orderingProperty);
			}else{
				d = d + value.getDouble(orderingProperty);
			}
		}
		
	}
	
	/**
	 * @return the number
	 */
	public Number getNumber() {
		return d;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(Number n) {
		this.d = n.doubleValue();
	}
	
	/** {@inheritDoc} */
	public int compareTo(NumberedFilter numberedFilter) {
		int compareResult = this.d.compareTo(numberedFilter.d);
		if(compareResult==0){
			super.compareTo(numberedFilter);
		}
		return compareResult;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((d == null) ? 0 : d.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof NumberedFilter)) {
			return false;
		}
		NumberedFilter other = (NumberedFilter) obj;
		if (d == null) {
			if (other.d != null) {
				return false;
			}
		} else if (!d.equals(other.d)) {
			return false;
		}
		return true;
	}
	
}
