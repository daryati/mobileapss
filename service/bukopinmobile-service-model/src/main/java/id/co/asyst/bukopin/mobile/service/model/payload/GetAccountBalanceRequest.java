package id.co.asyst.bukopin.mobile.service.model.payload;

import java.io.Serializable;
import java.math.BigInteger;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;



/**
 * GetAccountBalance Request
 * 
 * @author Ihsan FIrman
 * @version $Revision$, Dec 2,019
 * @since 1.0.Alpha1-SNAPSHOT
 */

public class GetAccountBalanceRequest implements Serializable {
    /* Constants: */

    /* Attributes: */
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -282503329867556084L;

    /* Constants: */

    /* Attributes: */
    /**
     * account type.
     */
    @NotNull
    @JsonProperty("accountType")
    private String accountType;

    @NotNull
    @JsonProperty("accountNumber")
    private String accountNumber;
    /**
     * list of accountNumber.
     */
    /*
     * @JsonProperty("accountNumbers") private List<String> accountNumbers;
     */

    @NotBlank
    @JsonProperty("username")
    private String username;
    

    /* Constructors: */

    /* Getters & setters for attributes: */

    /**
     * Gets <code>accountType</code>.
     * 
     * @return The <code>accountType</code>.
     */
    public String getAccountType() {
	return accountType;
    }

    /**
     * Sets <code>accountType</code>.
     * 
     * @param accountTypeEnum The <code>accountType</code> to set.
     */
    public void setAccountType(String accountTypeEnum) {
	this.accountType = accountTypeEnum;
    }

    /**
     * Gets <code>accountNumber</code>.
     * @return The <code>accountNumber</code>.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets <code>accountNumber</code>.
     * @param accountNumber The <code>accountNumber</code> to set.
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    /* Functionalities: x */

    /* Overrides: */

}
