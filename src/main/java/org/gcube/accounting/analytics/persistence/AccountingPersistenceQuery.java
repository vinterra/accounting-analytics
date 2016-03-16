/**
 * 
 */
package org.gcube.accounting.analytics.persistence;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;

import org.gcube.accounting.analytics.Filter;
import org.gcube.accounting.analytics.Info;
import org.gcube.accounting.analytics.NumberedFilter;
import org.gcube.accounting.analytics.TemporalConstraint;
import org.gcube.accounting.datamodel.UsageRecord;
import org.gcube.accounting.datamodel.aggregation.AggregatedStorageUsageRecord;
import org.gcube.documentstore.records.AggregatedRecord;
import org.gcube.documentstore.records.Record;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Luca Frosini (ISTI - CNR) http://www.lucafrosini.com/
 * 
 */
public class AccountingPersistenceQuery {

	private static final AccountingPersistenceQuery accountingPersistenceQuery;

	public static final int DEFAULT_LIMIT_RESULT_NUMBER = 5;
	
	private AccountingPersistenceQuery() {
	}

	static {
		accountingPersistenceQuery = new AccountingPersistenceQuery();
	}

	protected static synchronized AccountingPersistenceQuery getInstance() {
		return accountingPersistenceQuery;
	}

	public static SortedSet<String> getQuerableKeys(
			@SuppressWarnings("rawtypes") AggregatedRecord instance)
			throws Exception {
		SortedSet<String> properties = new TreeSet<>(
				instance.getRequiredFields());

		properties.removeAll(instance.getAggregatedFields());
		properties.removeAll(instance.getComputedFields());
		properties.remove(Record.ID);
		properties.remove(Record.CREATION_TIME);
		properties.remove(Record.RECORD_TYPE);
		properties.remove(UsageRecord.SCOPE);

		return properties;
	}

	public static SortedSet<String> getQuerableKeys(
			Class<? extends AggregatedRecord<?,?>> aggregatedRecordClass)
			throws Exception {
		AggregatedRecord<?,?> instance = aggregatedRecordClass.newInstance();
		return getQuerableKeys(instance);
	}

	public SortedMap<Calendar, Info> getTimeSeries(
			Class<? extends AggregatedRecord<?,?>> aggregatedRecordClass,
			TemporalConstraint temporalConstraint, List<Filter> filters, boolean pad)
			throws Exception {
		SortedMap<Calendar, Info> ret = 
				AccountingPersistenceBackendQueryFactory.getInstance()
				.getTimeSeries(aggregatedRecordClass, temporalConstraint, 
						filters);
		
		if(pad){
			ret = padMap(ret, temporalConstraint);
		}
		
		return ret;
	}

	public static String getDefaultOrderingProperties(Class<? extends AggregatedRecord<?, ?>> recordClass){
		if(recordClass.isAssignableFrom(AggregatedStorageUsageRecord.class)){
			return AggregatedStorageUsageRecord.DATA_VOLUME;
		}
		return AggregatedRecord.OPERATION_COUNT;
	}
	
	protected static JSONObject getPaddingJSONObject(
			Map<Calendar, Info> unpaddedResults) throws JSONException {
		Info auxInfo = new ArrayList<Info>(unpaddedResults.values()).get(0);
		JSONObject auxJsonObject = auxInfo.getValue();
		@SuppressWarnings("unchecked")
		Iterator<String> keys = auxJsonObject.keys();

		JSONObject jsonObject = new JSONObject();
		while (keys.hasNext()) {
			String key = keys.next();
			jsonObject.put(key, 0);
		}

		return jsonObject;
	}
	
	/**
	 * Pad the data
	 * 
	 * @param unpaddedData
	 *            the data to be pad
	 * @param temporalConstraint
	 *            temporalConstraint the temporal interval and the granularity
	 *            of the data to pad
	 * @return the data padded taking in account the TemporalConstraint
	 * @throws Exception
	 *             if fails
	 */
	public static SortedMap<Calendar, Info> padMap(
			SortedMap<Calendar, Info> unpaddedData,
			TemporalConstraint temporalConstraint) throws Exception {
		
		JSONObject jsonObject = getPaddingJSONObject(unpaddedData);
		SortedSet<Calendar> sequence = temporalConstraint.getCalendarSequence();
		for (Calendar progressTime : sequence) {
			Info info = unpaddedData.get(progressTime);
			if (info == null) {
				info = new Info(progressTime, jsonObject);
				unpaddedData.put(progressTime, info);
			}
		}
		return unpaddedData;
	}
	
	public static SortedMap<NumberedFilter, SortedMap<Calendar, Info>> getTopValues(
			Class<? extends AggregatedRecord<?,?>> aggregatedRecordClass,
			TemporalConstraint temporalConstraint, List<Filter> filters, 
			String orderingProperty, boolean pad, int limit) throws Exception {
		
		SortedMap<NumberedFilter, SortedMap<Calendar, Info>> ret;
		
		if(orderingProperty==null){
			orderingProperty = getDefaultOrderingProperties(aggregatedRecordClass);
		}
		
		ret = AccountingPersistenceBackendQueryFactory.getInstance()
				.getTopValues(aggregatedRecordClass, temporalConstraint,
				filters, orderingProperty);
		
		
		if(pad){
			int count = ret.size() > limit ? limit : ret.size();
			while(--count >= 0){
				for(NumberedFilter nf : ret.keySet()){
					padMap(ret.get(nf), temporalConstraint);
				}
			}
		}
		
		return ret;
	}
	
	public static SortedMap<NumberedFilter, SortedMap<Calendar, Info>> getTopValues(
			Class<? extends AggregatedRecord<?,?>> aggregatedRecordClass,
			TemporalConstraint temporalConstraint, List<Filter> filters)
			throws Exception {
		return AccountingPersistenceBackendQueryFactory.getInstance()
				.getTopValues(aggregatedRecordClass, temporalConstraint,
						filters);
	}

	public static SortedSet<NumberedFilter> getNextPossibleValues(
			Class<? extends AggregatedRecord<?,?>> aggregatedRecordClass,
			TemporalConstraint temporalConstraint, List<Filter> filters)
			throws Exception {
		return AccountingPersistenceBackendQueryFactory.getInstance()
				.getNextPossibleValues(aggregatedRecordClass,
						temporalConstraint, filters);
	}
	
	
	/**
	 * Close the connection to persistence
	 * 
	 * @throws Exception
	 *             if the close fails
	 */
	public void close() throws Exception {
		AccountingPersistenceBackendQueryFactory.getInstance().close();
	}

}
