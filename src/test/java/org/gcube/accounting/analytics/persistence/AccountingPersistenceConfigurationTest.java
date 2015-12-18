/**
 * 
 */
package org.gcube.accounting.analytics.persistence;

import org.gcube.accounting.persistence.AccountingPersistenceConfiguration;
import org.gcube.common.scope.api.ScopeProvider;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Luca Frosini (ISTI - CNR) http://www.lucafrosini.com/
 *
 */
public class AccountingPersistenceConfigurationTest {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountingPersistenceConfigurationTest.class);

	public static final String COUCHDB_CLASS_NAME = "AccountingPersistenceQueryCouchDB";

	public static final String[] SCOPES = new String[]{"/gcube", "/gcube/devNext", "/gcube/devsec"};
	
	public abstract class AccountingPersistenceQueryCouchDB extends AccountingPersistenceBackendQuery {}
	
	@Test
	public void getUsernamePasswordForScopes() throws Exception{
		for(String scope : SCOPES){
			ScopeProvider.instance.set(scope);
			try {
				AccountingPersistenceBackendQueryConfiguration persitenceConfiguration = new AccountingPersistenceBackendQueryConfiguration(AccountingPersistenceQueryCouchDB.class);
				String uri = persitenceConfiguration.getProperty(AccountingPersistenceConfiguration.URL_PROPERTY_KEY);
				String username = persitenceConfiguration.getProperty(AccountingPersistenceConfiguration.USERNAME_PROPERTY_KEY);
				String password = persitenceConfiguration.getProperty(AccountingPersistenceConfiguration.PASSWORD_PROPERTY_KEY);
				logger.debug("{} {} - {} : {}", scope, uri, username, password);
			}catch(IndexOutOfBoundsException e){
				logger.debug("No AccountingPersistenceConfiguration : \n {} {} \n\n", e.getClass().getName(), e.getMessage());
			} catch(Exception e){
				logger.error("Error getting AccountingPersistenceConfiguration", e);
				throw e;
			}
		}
	}
		
}
