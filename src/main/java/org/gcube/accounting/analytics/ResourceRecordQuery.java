/**
 * 
 */
package org.gcube.accounting.analytics;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.gcube.accounting.analytics.exception.NoAvailableScopeException;
import org.gcube.accounting.analytics.exception.NoUsableAccountingPersistenceQueryFound;
import org.gcube.accounting.analytics.persistence.AccountingPersistenceQuery;
import org.gcube.accounting.analytics.persistence.AccountingPersistenceQueryFactory;
import org.gcube.accounting.datamodel.AggregatedUsageRecord;
import org.gcube.accounting.datamodel.SingleUsageRecord;
import org.gcube.accounting.datamodel.usagerecords.ServiceUsageRecord;
import org.gcube.common.scope.api.ScopeProvider;
import org.json.JSONException;
import org.json.JSONObject;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Luca Frosini (ISTI - CNR) http://www.lucafrosini.com/
 */
public class ResourceRecordQuery {
	
	private static Logger logger = LoggerFactory.getLogger(ResourceRecordQuery.class);
	
	protected static Map<Class<? extends SingleUsageRecord>, Set<String>> resourceRecords = null;
	
	/**
	 * Return a Map containing a set of required fields for each Resource 
	 * Records Types
	 * @return the Map
	 */
	public static Map<Class<? extends SingleUsageRecord>, Set<String>> getResourceRecordsTypes() {
		if(resourceRecords==null){
			resourceRecords = new HashMap<Class<? extends SingleUsageRecord>, Set<String>>();
			Package usageRecordPackage = ServiceUsageRecord.class.getPackage();
			Reflections reflections = new Reflections(usageRecordPackage.getName());
			Set<Class<? extends SingleUsageRecord>> resourceRecordsTypes = reflections.getSubTypesOf(SingleUsageRecord.class);
			for(Class<? extends SingleUsageRecord> resourceRecordsType : resourceRecordsTypes){
				try {
					SingleUsageRecord singleUsageRecord = resourceRecordsType.newInstance();
					resourceRecords.put(resourceRecordsType, singleUsageRecord.getRequiredFields());
				} catch (InstantiationException | IllegalAccessException e) {
					logger.error(String.format("Unable to correctly istantiate %s", resourceRecordsType.getSimpleName()), e);
				}
			}
			
		}
		return resourceRecords;
	}
	
	
	protected AccountingPersistenceQuery accountingPersistenceQuery;
	
	/**
	 * Instantiate the ResourceRecord for the current scope
	 * @throws NoAvailableScopeException if there is not possible to query in 
	 * the current scope
	 * @throws NoUsableAccountingPersistenceQueryFound if there is no available 
	 * instance which can query in that scope
	 */
	public ResourceRecordQuery() throws NoAvailableScopeException, NoUsableAccountingPersistenceQueryFound {
		this.accountingPersistenceQuery = AccountingPersistenceQueryFactory.getInstance();
	}
	
	/**
	 * Instantiate the ResourceRecord for the provided scope
	 * @param scope the scope
	 * @throws NoAvailableScopeException if there is not possible to query in 
	 * that scope
	 * @throws NoUsableAccountingPersistenceQueryFound if there is no available 
	 * instance which can query in that scope
	 */
	public ResourceRecordQuery(String scope) throws NoAvailableScopeException, NoUsableAccountingPersistenceQueryFound {
		ScopeProvider.instance.set(scope);
		this.accountingPersistenceQuery = AccountingPersistenceQueryFactory.getInstance();
	}
	
	protected static JSONObject getPaddingJSONObject(Map<Calendar, Info> unpaddedResults) throws JSONException{
		Info auxInfo = new ArrayList<Info>(unpaddedResults.values()).get(0);
		JSONObject auxJsonObject =  auxInfo.getValue();
		@SuppressWarnings("unchecked")
		Iterator<String> keys = auxJsonObject.keys();
		
		JSONObject jsonObject = new JSONObject();
		while(keys.hasNext()){
			String key = keys.next();
			jsonObject.put(key, 0);
		}
		
		return jsonObject;
	}
	
	/**
	 * Pad the data
	 * @param unpaddedData the data to be pad
	 * @param temporalConstraint temporalConstraint the temporal interval and 
	 * the granularity of the data to pad 
	 * @return the data padded taking in account the TemporalConstraint
	 * @throws Exception if fails
	 */
	public static List<Info> getPaddedResults(Map<Calendar, Info> unpaddedData, 
			TemporalConstraint temporalConstraint) throws Exception {
		JSONObject jsonObject = getPaddingJSONObject(unpaddedData);
		
		List<Info> paddedResults = new ArrayList<Info>();
		List<Calendar> sequence = temporalConstraint.getCalendarSequence();

		for(Calendar progressTime : sequence){
			if(unpaddedData.get(progressTime)!=null){
				paddedResults.add(unpaddedData.get(progressTime));
			}else{
				Date date = new Date(progressTime.getTimeInMillis());
				Info info = new Info(date, jsonObject);
				paddedResults.add(info);
			}
		}
		
		return paddedResults;
	}
	
	/**
	 * Return results with padding if pad is set to true.
	 * @param usageRecordType the UsageRecord type to query
	 * @param temporalConstraint the temporal interval and the granularity 
	 * @param filters the list keys to filter (in AND)
	 * @param pad indicate is the results have to be padded with zeros when 
	 * there is no data available at certain data points of sequence
	 * @return the requested list of Info 
	 * @throws Exception if fails
	 */
	public List<Info> getInfo(@SuppressWarnings("rawtypes") Class<? extends AggregatedUsageRecord> usageRecordType, 
			TemporalConstraint temporalConstraint, List<Filter> filters, boolean pad) throws Exception {
		Map<Calendar, Info> unpaddedResults = accountingPersistenceQuery.query(usageRecordType, temporalConstraint, filters);
		if(!pad){
			return new ArrayList<Info>(unpaddedResults.values());
		}
		return getPaddedResults(unpaddedResults, temporalConstraint);
	}
	
	/**
	 * Return unpadded results
	 * @param usageRecordType the UsageRecord type to query
	 * @param temporalConstraint the temporal interval and the granularity 
	 * @param filters the list keys to filter (in AND)
	 * @return the requested list of Info 
	 * @throws Exception if fails
	 */
	public List<Info> getInfo(@SuppressWarnings("rawtypes") Class<? extends AggregatedUsageRecord> usageRecordType, 
			TemporalConstraint temporalConstraint, List<Filter> filters) throws Exception{
		return getInfo(usageRecordType, temporalConstraint, filters, false);
	}
	
}
