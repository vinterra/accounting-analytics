/**
 * 
 */
package org.gcube.accounting.analytics.persistence;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.gcube.accounting.analytics.Filter;
import org.gcube.accounting.analytics.Info;
import org.gcube.accounting.analytics.TemporalConstraint;
import org.gcube.accounting.datamodel.AggregatedUsageRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Luca Frosini (ISTI - CNR) http://www.lucafrosini.com/
 *
 */
public abstract class AccountingPersistenceQuery {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountingPersistenceQuery.class);
	
	protected abstract void prepareConnection(AccountingPersistenceQueryConfiguration configuration) throws Exception;
	
	protected abstract Map<Calendar, Info> reallyQuery(@SuppressWarnings("rawtypes") Class<? extends AggregatedUsageRecord> usageRecordType, 
			TemporalConstraint temporalConstraint, List<Filter> filters) throws Exception;
	
	/**
	 * Query the persistence obtaining a Map where the date is the key and 
	 * the #Info is the value. The result is relative to an Usage Record Type,
	 * respect a TemporalConstraint and can be applied one or more filters.
	 * @param usageRecordType the Usage Record Type of interest
	 * @param temporalConstraint the TemporalConstraint (interval and aggregation)
	 * @param filters the filter for the query. If null or empty string get all
	 * data. The filters are evaluated in the order the are presented and are
	 * considered in AND 
	 * @return the Map containing for each date in the required interval the
	 * requested data
	 * @throws Exception if fails
	 */
	public Map<Calendar, Info> query(@SuppressWarnings("rawtypes") Class<? extends AggregatedUsageRecord> usageRecordType, 
			TemporalConstraint temporalConstraint, List<Filter> filters) throws Exception{
		logger.trace("Request query: UsageRecordType={}, {}={}, {}s={}", usageRecordType.newInstance().getUsageRecordType(), 
				TemporalConstraint.class.getSimpleName(), temporalConstraint.toString(), 
				Filter.class.getSimpleName(), filters);
		return reallyQuery(usageRecordType, temporalConstraint, filters);
	}
	
	/**
	 * Return the list of key valid for queries a certain usage record type
	 * @param usageRecordType the usage record type 
	 * @return a set containing the list of key
	 * @throws Exception if fails
	 */
	public abstract Set<String> getKeys(@SuppressWarnings("rawtypes") Class<? extends AggregatedUsageRecord> usageRecordType) throws Exception;
	
	/**
	 * Close the connection to persistence
	 * @throws Exception if the close fails
	 */
	public abstract void close() throws Exception;
}
