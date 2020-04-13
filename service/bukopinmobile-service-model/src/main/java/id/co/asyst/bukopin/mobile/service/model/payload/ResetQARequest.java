package id.co.asyst.bukopin.mobile.service.model.payload;

import java.io.Serializable;

public class ResetQARequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/* Constants: */

	/* Attributes: */
	private String username;
	private String data;
	private String cenToken;
	/* Transient Attributes: */

	/* Constructors: */

	/* Getters & setters for attributes: */
	/**
	 * Gets <code>username</code>.
	 * @return The <code>username</code>.
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * Sets <code>username</code>.
	 * @param username The <code>username</code> to set.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * Gets <code>data</code>.
	 * @return The <code>data</code>.
	 */
	public String getData() {
		return data;
	}
	/**
	 * Sets <code>data</code>.
	 * @param data The <code>data</code> to set.
	 */
	public void setData(String data) {
		this.data = data;
	}
	/**
	 * Gets <code>cenToken</code>.
	 * @return The <code>cenToken</code>.
	 */
	public String getCenToken() {
		return cenToken;
	}
	/**
	 * Sets <code>cenToken</code>.
	 * @param cenToken The <code>cenToken</code> to set.
	 */
	public void setCenToken(String cenToken) {
		this.cenToken = cenToken;
	}
	/* Getters & setters for transient attributes: */

	/* Functionalities: */

	/* Overrides: */}
