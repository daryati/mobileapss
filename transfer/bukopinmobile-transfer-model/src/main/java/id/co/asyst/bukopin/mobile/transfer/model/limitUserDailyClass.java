/**
 * 
 */
package id.co.asyst.bukopin.mobile.transfer.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author 216930237
 *
 */

public class limitUserDailyClass implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("amount")
	private BigDecimal amount;

	@JsonProperty("username")
	private String username;
	@JsonProperty("accNo")
	private String accNo;
	@JsonProperty("jenis")
	private String jenis;

	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the accNo
	 */
	public String getAccNo() {
		return accNo;
	}

	/**
	 * @param accNo
	 *            the accNo to set
	 */
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	/**
	 * @return the jenis
	 */
	public String getJenis() {
		return jenis;
	}

	/**
	 * @param jenis
	 *            the jenis to set
	 */
	public void setJenis(String jenis) {
		this.jenis = jenis;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "limitUserDailyClass [amount=" + amount + ", username="
				+ username + ", accNo=" + accNo + ", jenis=" + jenis + "]";
	}

}
