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
import org.gcube.accounting.datamodel.SingleUsageRecord;

/**
 * @author Luca Frosini (ISTI - CNR) http://www.lucafrosini.com/
 *
 */
public abstract class AccountingPersistenceQuery {
	
	protected abstract void prepareConnection(AccountingPersistenceQueryConfiguration configuration) throws Exception;
	
	protected abstract Map<Calendar, Info> reallyQuery(Class<? extends SingleUsageRecord> usageRecordType, 
			TemporalConstraint temporalConstraint, List<Filter> filters) throws Exception;
	
	public Map<Calendar, Info> query(Class<? extends SingleUsageRecord> usageRecordType, 
			TemporalConstraint temporalConstraint, List<Filter> filters) throws Exception{
		return reallyQuery(usageRecordType, temporalConstraint, filters);
	}
	
	public abstract void close() throws Exception;
}
