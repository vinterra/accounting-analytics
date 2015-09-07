/**
 * 
 */
package org.gcube.accounting.analytics.exception;

/**
 * @author Luca Frosini (ISTI - CNR) http://www.lucafrosini.com/
 *
 */
public class NoAvailableScopeException extends Exception {

	/**
	 * Generated serial Version UID
	 */
	private static final long serialVersionUID = -327144230654860518L;

	
	public NoAvailableScopeException() {
		super();
	}

	/**
	 * Constructs a new exception with the specified detail message. The cause 
	 * is not initialized, and may subsequently be initialized by a call to 
	 * initCause.
	 * @param message the detail message. The detail message is saved for later 
	 * retrieval by the getMessage() method.
	 */
	public NoAvailableScopeException(String message) {
		super(message);
	}

	public NoAvailableScopeException(Throwable cause) {
		super(cause);
	}
	
	public NoAvailableScopeException(String message, Throwable cause) {
		super(message, cause);
	}
}
