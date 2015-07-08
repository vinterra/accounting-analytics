/**
 * 
 */
package org.gcube.accounting.analytics.exception;

/**
 * @author Luca Frosini (ISTI - CNR) http://www.lucafrosini.com/
 *
 */
public class NoUsableAccountingPersistenceQueryFound extends Exception {

	/**
	 * Generated serial Version UID
	 */
	private static final long serialVersionUID = -327144230654860518L;

	public NoUsableAccountingPersistenceQueryFound() {
		super();
	}

	public NoUsableAccountingPersistenceQueryFound(String message) {
		super(message);
	}
	
	public NoUsableAccountingPersistenceQueryFound(Throwable cause) {
		super(cause);
	}
	
	public NoUsableAccountingPersistenceQueryFound(String message, Throwable cause) {
		super(message, cause);
	}
}
