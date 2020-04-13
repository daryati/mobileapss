package id.co.asyst.bukopin.mobile.user.model.centagate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Field 'object' of Centagate User Registration Result
 * 
 * @author Ihsan Firman
 * @version $Revision$, Oct 29, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRegistrationResultData {

	private String message;
	private String object;
	private String code;
	
	/**
	 * Gets <code>message</code>.
	 * @return The <code>message</code>.
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * Sets <code>message</code>.
	 * @param message The <code>message</code> to set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * Gets <code>object</code>.
	 * @return The <code>object</code>.
	 */
	public String getObject() {
		return object;
	}
	/**
	 * Sets <code>object</code>.
	 * @param object The <code>object</code> to set.
	 */
	public void setObject(String object) {
		this.object = object;
	}
	/**
	 * Gets <code>code</code>.
	 * @return The <code>code</code>.
	 */
	public String getCode() {
		return code;
	}
	/**
	 * Sets <code>code</code>.
	 * @param code The <code>code</code> to set.
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
