/**
 * 
 */
package org.gcube.accounting.analytics;


/**
 * @author Luca Frosini (ISTI - CNR) http://www.lucafrosini.com/
 *
 */
public interface Info {

	public long getDate();
	
	public Number getInfo();
	
	/*
	public String getUnity(); // Number of Occurrences, Kb, 
	
	public String getUnityDescription(); // Total Kb accumulated, Single Operation Kb
	*/
	
}
