/**
 * 
 */
package org.gcube.accounting.analytics;

import java.util.List;

import org.gcube.accounting.analytics.persistence.AccountingPersistenceQuery;
import org.gcube.accounting.datamodel.UsageRecord;

/**
 * @author Luca Frosini (ISTI - CNR) http://www.lucafrosini.com/
 */
public class ResourceRecordQuery {

	protected AccountingPersistenceQuery accountingPersistenceQuery;
	
	protected ResourceRecordQuery(AccountingPersistenceQuery accountingPersistenceQuery){
		this.accountingPersistenceQuery = accountingPersistenceQuery;
	}
	
	public List<Info> getInfo(Class<? extends UsageRecord> usageRecordType, 
			TemporalConstraint temporalConstraint, List<Filter> filters) throws Exception{
		return accountingPersistenceQuery.query(usageRecordType, temporalConstraint, filters);
	}
	
}
