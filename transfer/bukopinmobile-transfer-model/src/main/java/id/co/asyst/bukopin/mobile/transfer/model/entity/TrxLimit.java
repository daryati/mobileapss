/**
 * 
 */
package id.co.asyst.bukopin.mobile.transfer.model.entity;

import id.co.asyst.bukopin.mobile.user.model.entity.User;
import id.co.asyst.foundation.base.model.IdBasedObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author 216930237
 *
 */
@Entity
@Table(name = "TRX_LIMIT")
public class TrxLimit extends IdBasedObject implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(name = "ACCOUNT_NUMBER")
	private String accountNumber;
	@JsonProperty("createDate")
	@Column(name = "CREATE_DATE")
	private Date createDate;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "USER_ID")
	private User username;
	@Column(name = "LIMIT_ID")
	private Long limitId;
	@Column(name = "TOTAL_AMOUNT_TF")
	private BigDecimal totalAmountft;
	@Column(name = "TOTAL_AMOUNT_OB")
	private BigDecimal totalAmountob;
	@Column(name = "TOTAL_AMOUNT_PURCHASE")
	private BigDecimal totalAmountpurchase;
	@Column(name = "TOTAL_AMOUNT_PAYMENT")
	private BigDecimal totalAmountpayment;
	@Column(name = "MENU")
	private String menu;
	@Column(name = "REASON")
	private String reason;
	@Column(name = "STATUS")
	private String status;

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber
	 *            the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the username
	 */
	public User getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(User username) {
		this.username = username;
	}

	/**
	 * @return the limitId
	 */
	public Long getLimitId() {
		return limitId;
	}

	/**
	 * @param limitId
	 *            the limitId to set
	 */
	public void setLimitId(Long limitId) {
		this.limitId = limitId;
	}

	/**
	 * @return the totalAmountft
	 */
	public BigDecimal getTotalAmountft() {
		return totalAmountft;
	}

	/**
	 * @param totalAmountft
	 *            the totalAmountft to set
	 */
	public void setTotalAmountft(BigDecimal totalAmountft) {
		this.totalAmountft = totalAmountft;
	}

	/**
	 * @return the totalAmountob
	 */
	public BigDecimal getTotalAmountob() {
		return totalAmountob;
	}

	/**
	 * @param totalAmountob
	 *            the totalAmountob to set
	 */
	public void setTotalAmountob(BigDecimal totalAmountob) {
		this.totalAmountob = totalAmountob;
	}

	/**
	 * @return the totalAmountpurchase
	 */
	public BigDecimal getTotalAmountpurchase() {
		return totalAmountpurchase;
	}

	/**
	 * @param totalAmountpurchase
	 *            the totalAmountpurchase to set
	 */
	public void setTotalAmountpurchase(BigDecimal totalAmountpurchase) {
		this.totalAmountpurchase = totalAmountpurchase;
	}

	/**
	 * @return the totalAmountpayment
	 */
	public BigDecimal getTotalAmountpayment() {
		return totalAmountpayment;
	}

	/**
	 * @param totalAmountpayment
	 *            the totalAmountpayment to set
	 */
	public void setTotalAmountpayment(BigDecimal totalAmountpayment) {
		this.totalAmountpayment = totalAmountpayment;
	}

	/**
	 * @return the menu
	 */
	public String getMenu() {
		return menu;
	}

	/**
	 * @param menu
	 *            the menu to set
	 */
	public void setMenu(String menu) {
		this.menu = menu;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason
	 *            the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the serialversionuid
	 */
	// public static long getSerialversionuid() {
	// return serialVersionUID;
	// }
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TrxLimit [accountNumber=" + accountNumber + ", createDate="
				+ createDate + ", username=" + username + ", limitId="
				+ limitId + ", totalAmountft=" + totalAmountft
				+ ", totalAmountob=" + totalAmountob + ", totalAmountpurchase="
				+ totalAmountpurchase + ", totalAmountpayment="
				+ totalAmountpayment + ", menu=" + menu + ", reason=" + reason
				+ ", status=" + status + "]";
	}

}
