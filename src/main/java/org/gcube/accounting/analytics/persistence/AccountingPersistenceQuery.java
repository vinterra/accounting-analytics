/**
 * 
 */
package org.gcube.accounting.analytics.persistence;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

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
	
	public Map<Calendar, Info> query(@SuppressWarnings("rawtypes") Class<? extends AggregatedUsageRecord> usageRecordType, 
			TemporalConstraint temporalConstraint, List<Filter> filters) throws Exception{
		logger.trace("Request query: UsageRecordType={}, {}={}, {}s={}", usageRecordType.newInstance().getUsageRecordType(), 
				TemporalConstraint.class.getSimpleName(), temporalConstraint.toString(), 
				Filter.class.getSimpleName(), filters);
		return reallyQuery(usageRecordType, temporalConstraint, filters);
	}
	
	public abstract void close() throws Exception;
}
