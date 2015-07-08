/**
 * 
 */
package org.gcube.accounting.analytics;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import org.gcube.accounting.analytics.exception.NoAvailableScopeException;
import org.gcube.accounting.analytics.exception.NoUsableAccountingPersistenceQueryFound;
import org.gcube.accounting.analytics.persistence.AccountingPersistenceQuery;
import org.gcube.common.scope.api.ScopeProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Luca Frosini (ISTI - CNR) http://www.lucafrosini.com/
 */
public abstract class ResourceRecordQueryFactory {

	private static Logger logger = LoggerFactory.getLogger(ResourceRecordQueryFactory.class);
	
	private static Map<String, ResourceRecordQuery> resourceRecordQueries;
	
	static {
		resourceRecordQueries = new HashMap<String, ResourceRecordQuery>();
	}
	
	public synchronized static ResourceRecordQuery getInstance() throws NoAvailableScopeException, NoUsableAccountingPersistenceQueryFound {
		String scope = ScopeProvider.instance.get();
		if(scope==null){
			new NoAvailableScopeException();
		}
		
		ResourceRecordQuery resourceRecordQuery = resourceRecordQueries.get(scope);
		if(resourceRecordQuery==null){
		
			try {
				ServiceLoader<AccountingPersistenceQuery> serviceLoader = ServiceLoader.load(AccountingPersistenceQuery.class);
				for (AccountingPersistenceQuery found : serviceLoader) {
					try {
						String foundClassName = found.getClass().getSimpleName();
						logger.debug("Testing {}", foundClassName);
						
						//AccountingPersistenceQueryConfiguration configuration = AccountingPersistenceQueryConfiguration.getConfiguration(foundClassName);
						//found.prepareConnection(configuration);

						resourceRecordQuery = new ResourceRecordQuery(found);
						
						break;
					} catch (Exception e) {
						logger.debug(String.format("%s not initialized correctly. It will not be used", found.getClass().getSimpleName()));
					}
				}
			} catch(Exception e){
				throw new NoUsableAccountingPersistenceQueryFound();
			}
			
			if(resourceRecordQuery==null){
				throw new NoUsableAccountingPersistenceQueryFound();
			}
			
			resourceRecordQueries.put(scope, resourceRecordQuery);
		}
		
		return resourceRecordQuery;
	}

	
	protected ResourceRecordQueryFactory(){
		
	}

	
	
	
}
