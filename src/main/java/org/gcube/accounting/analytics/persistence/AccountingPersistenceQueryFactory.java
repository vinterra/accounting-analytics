/**
 * 
 */
package org.gcube.accounting.analytics.persistence;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import org.gcube.accounting.analytics.exception.NoAvailableScopeException;
import org.gcube.accounting.analytics.exception.NoUsableAccountingPersistenceQueryFound;
import org.gcube.common.scope.api.ScopeProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Luca Frosini (ISTI - CNR) http://www.lucafrosini.com/
 */
public abstract class AccountingPersistenceQueryFactory {

	private static Logger logger = LoggerFactory.getLogger(AccountingPersistenceQueryFactory.class);
	
	private static Map<String, AccountingPersistenceQuery> accountingPersistenceQueries;
	
	static {
		accountingPersistenceQueries = new HashMap<String, AccountingPersistenceQuery>();
	}
	
	/**
	 * @return AccountingPersistenceQuery instance
	 * @throws NoAvailableScopeException if no configuration is found on IS for
	 * the current scope
	 * @throws NoUsableAccountingPersistenceQueryFound if fails to instantiate
	 * the #AccountingPersistenceQuery
	 */
	public synchronized static AccountingPersistenceQuery getInstance() throws NoAvailableScopeException, NoUsableAccountingPersistenceQueryFound {
		String scope = ScopeProvider.instance.get();
		if(scope==null){
			throw new NoAvailableScopeException();
		}
		
		AccountingPersistenceQuery accountingPersistenceQuery = accountingPersistenceQueries.get(scope);
		if(accountingPersistenceQuery==null){
		
			try {
				ServiceLoader<AccountingPersistenceQuery> serviceLoader = ServiceLoader.load(AccountingPersistenceQuery.class);
				for (AccountingPersistenceQuery found : serviceLoader) {
					try {
						String foundClassName = found.getClass().getSimpleName();
						logger.debug("Testing {}", foundClassName);
						
						AccountingPersistenceQueryConfiguration configuration = new AccountingPersistenceQueryConfiguration(foundClassName);
						found.prepareConnection(configuration);
						accountingPersistenceQuery = found;
						break;
					} catch (Exception e) {
						logger.debug(String.format("%s not initialized correctly. It will not be used", found.getClass().getSimpleName()));
					}
				}
			} catch(Exception e){
				throw new NoUsableAccountingPersistenceQueryFound();
			}
			
			if(accountingPersistenceQuery==null){
				throw new NoUsableAccountingPersistenceQueryFound();
			}
			
			accountingPersistenceQueries.put(scope, accountingPersistenceQuery);
		}
		
		return accountingPersistenceQuery;
	}

	
	protected AccountingPersistenceQueryFactory(){
		
	}

	
	
	
}
