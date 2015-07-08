/**
 * 
 */
package org.gcube.accounting.analytics.persistence;

import java.util.List;

import org.gcube.accounting.analytics.Filter;
import org.gcube.accounting.analytics.Info;
import org.gcube.accounting.analytics.TemporalConstraint;
import org.gcube.accounting.datamodel.UsageRecord;

/**
 * @author Luca Frosini (ISTI - CNR) http://www.lucafrosini.com/
 *
 */
public abstract class AccountingPersistenceQuery {
	
	protected abstract void prepareConnection(AccountingPersistenceQueryConfiguration configuration) throws Exception;
	
	protected abstract List<Info> reallyQuery(Class<? extends UsageRecord> usageRecordType, 
			TemporalConstraint temporalConstraint, List<Filter> filters) throws Exception;
	
	public List<Info> query(Class<? extends UsageRecord> usageRecordType, 
			TemporalConstraint temporalConstraint, List<Filter> filters) throws Exception{
		return reallyQuery(usageRecordType, temporalConstraint, filters);
	}
	
	public abstract void close() throws Exception;
}
