package id.co.asyst.bukopin.mobile.service.model.payload;

import java.io.Serializable;

public class CtgDeleteUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6323636951340223040L;
	/* Constants: */

	/* Attributes: */
	private String username;

	private String cenToken;

	/**
	 * Gets <code>username</code>.
	 * 
	 * @return The <code>username</code>.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets <code>username</code>.
	 * 
	 * @param username The <code>username</code> to set.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets <code>cenToken</code>.
	 * 
	 * @return The <code>cenToken</code>.
	 */
	public String getCenToken() {
		return cenToken;
	}

	/**
	 * Sets <code>cenToken</code>.
	 * 
	 * @param cenToken The <code>cenToken</code> to set.
	 */
	public void setCenToken(String cenToken) {
		this.cenToken = cenToken;
	}

	/* Transient Attributes: */

	/* Constructors: */

	/* Getters & setters for attributes: */

	/* Getters & setters for transient attributes: */

	/* Functionalities: */

	/* Overrides: */
	@Override
	public String toString() {
		return "CtgDeleteUser [username = " + username + ", cenToken = " + cenToken + "]";
	}
}
