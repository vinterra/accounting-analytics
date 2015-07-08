/**
 * 
 */
package org.gcube.accounting.analytics.persistence;

import java.net.URI;

import org.gcube.accounting.persistence.AccountingPersistenceConfiguration;

/**
 * @author Luca Frosini (ISTI - CNR) http://www.lucafrosini.com/
 *
 */
public class AccountingPersistenceQueryConfiguration extends AccountingPersistenceConfiguration {
	
	protected final String SERVICE_ENDPOINT_NAME = "PersistenceQuery";
	
	public AccountingPersistenceQueryConfiguration(){
		super();
	}
	
	public AccountingPersistenceQueryConfiguration(URI uri, String username, String password){
		super(uri, username, password);
	}
	
	public AccountingPersistenceQueryConfiguration(String persistenceClassName) throws Exception{
		super(persistenceClassName);
	}
}
