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
