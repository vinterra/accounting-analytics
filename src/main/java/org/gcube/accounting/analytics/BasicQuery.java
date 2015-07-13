/**
 * 
 */
package org.gcube.accounting.analytics;

import java.util.List;

import org.gcube.accounting.analytics.exception.NoAvailableScopeException;
import org.gcube.accounting.analytics.exception.NoUsableAccountingPersistenceQueryFound;
import org.gcube.accounting.analytics.persistence.AccountingPersistenceQuery;
import org.gcube.accounting.analytics.persistence.AccountingPersistenceQueryFactory;
import org.gcube.common.scope.api.ScopeProvider;

/**
 * @author Luca Frosini (ISTI - CNR) http://www.lucafrosini.com/
 */
public class BasicQuery {

	protected AccountingPersistenceQuery accountingPersistenceQuery;
	
	public BasicQuery() throws NoAvailableScopeException, NoUsableAccountingPersistenceQueryFound {
		this.accountingPersistenceQuery = AccountingPersistenceQueryFactory.getInstance();
	}
	
	public BasicQuery(String scope) throws NoAvailableScopeException, NoUsableAccountingPersistenceQueryFound {
		ScopeProvider.instance.set(scope);
		this.accountingPersistenceQuery = AccountingPersistenceQueryFactory.getInstance();
	}
	
	public List<Info> getServiceInfo(TemporalConstraint temporalConstraint) {
		
		
		return null;
	}
	
	public List<Info> getStorageInfo(TemporalConstraint temporalConstraint) {
		
		
		return null;
	}
	
}
