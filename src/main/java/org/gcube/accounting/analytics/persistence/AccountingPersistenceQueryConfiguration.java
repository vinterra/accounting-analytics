package org.gcube.accounting.analytics.persistence;

import java.net.URI;

import org.gcube.accounting.persistence.AccountingPersistenceConfiguration;
import org.gcube.common.resources.gcore.ServiceEndpoint;

/**
 * @author Luca Frosini (ISTI - CNR) http://www.lucafrosini.com/
 *
 */
public class AccountingPersistenceQueryConfiguration extends AccountingPersistenceConfiguration {
	
	/**
	 * Default Constructor
	 */
	public AccountingPersistenceQueryConfiguration(){
		super();
	}
	
	/**
	 * @param uri the URI of the persistence 
	 * @param username the username to connect to persistence 
	 * @param password the password to connect to persistence 
	 */
	public AccountingPersistenceQueryConfiguration(URI uri, String username, String password){
		super(uri, username, password);
	}
	
	/**
	 * @param persistenceClassName The classname of the persistence to instantiate
	 * @throws Exception if fails
	 */
	public AccountingPersistenceQueryConfiguration(String persistenceClassName) throws Exception{
		super.init();
		ServiceEndpoint serviceEndpoint = getServiceEndpoint(SERVICE_ENDPOINT_CATEGORY, SERVICE_ENDPOINT_NAME, persistenceClassName);
		setValues(serviceEndpoint, persistenceClassName);
	}
}
