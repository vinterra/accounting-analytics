/**
 * 
 */
package org.gcube.accounting.analytics;

/**
 * @author Luca Frosini (ISTI - CNR) http://www.lucafrosini.com/
 *
 */
public class TemporalConstraint {

	public enum AggregationMode {
		YEARLY, MONTHLY, DAILY, HOURLY, MINUTELY, SECONDLY, MILLISECONDLY  
	}
	
	protected long startTime;
	protected long endTime;
	protected AggregationMode aggregationMode;
	
	public TemporalConstraint(long startTime, long endTime, AggregationMode aggregationMode){
		this.startTime = startTime;
		this.endTime = endTime;
		this.aggregationMode = aggregationMode;
	}

	/**
	 * @return the startTime
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public long getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the aggregationMode
	 */
	public AggregationMode getAggregationMode() {
		return aggregationMode;
	}

	/**
	 * @param aggregationMode the aggregationMode to set
	 */
	public void setAggregationMode(AggregationMode aggregationMode) {
		this.aggregationMode = aggregationMode;
	}
	
	
}
