/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import id.co.asyst.bukopin.mobile.user.model.CardStatusEnum;
import id.co.asyst.foundation.common.model.IdBasedObject;

/**
 * Account Card Entity Model
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 6, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@Entity
@Table(name="ACCOUNT_CARD")
public class AccountCard extends IdBasedObject implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 2671398255305575443L;
    
    /* Constants: */

    /* Attributes: */
    /**
     * Card Register status. 0=unregistered. 1=registered.
     */
    @Column(name="REGISTERED_CARD", length=50)
    private String registeredCard;
    
    /**
     * CIF Number
     */
    @Column(name="CIF", length=20)
    private String cif;
    
    /**
     * Birthdate
     */
    @Column(name="BIRTHDATE")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    
    /**
     * Month Card validity. 2 digit month. 01=January, 12=December.
     */
    //@Max(value=2, message="Valid Month should not be greater than 2 characters.")
    @Column(name="VALID_MONTH", length=2)
    private String validMonth;
    
    /**
     * Year Card validity. 4 digit year.
     */
    //@Max(value=4, message="Valid Year should not be greater than 4 characters.")
    @Column(name="VALID_YEAR", length=4)
    private String validYear;
    
    /**
     * Registration date and time, include timezone.
     */
    @Column(name="REGISTERED_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registeredOn;
    
    /**
     * Card Status. 0=Unactivated, 1=activated.
     */
    @Enumerated(EnumType.STRING)
    @Column(name="CARD_STATUS")
    private CardStatusEnum cardStatus;
    
    /**
     * User Foreign Key
     */
    // TODO change to @ManyToOne, 1 user may has more then 1 account card
    @OneToOne
    @JoinColumn(name="USER_ID")
    private User user;
    
    /**
     * List of Accounts
     */
    @OneToMany(fetch=FetchType.EAGER, mappedBy="accountCard", cascade=CascadeType.ALL)
    private List<AccountInfo> accounts;
    
    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>registeredCard</code>.
     * @return The <code>registeredCard</code>.
     */
    public String getRegisteredCard() {
        return registeredCard;
    }

    /**
     * Sets <code>registeredCard</code>.
     * @param registeredCard The <code>registeredCard</code> to set.
     */
    public void setRegisteredCard(String registeredCard) {
        this.registeredCard = registeredCard;
    }

    /**
     * Gets <code>cif</code>.
     * @return The <code>cif</code>.
     */
    public String getCif() {
        return cif;
    }

    /**
     * Sets <code>cif</code>.
     * @param cif The <code>cif</code> to set.
     */
    public void setCif(String cif) {
        this.cif = cif;
    }

    /**
     * Gets <code>birthDate</code>.
     * @return The <code>birthDate</code>.
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Sets <code>birthDate</code>.
     * @param birthDate The <code>birthDate</code> to set.
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Gets <code>validMonth</code>.
     * @return The <code>validMonth</code>.
     */
    public String getValidMonth() {
        return validMonth;
    }

    /**
     * Sets <code>validMonth</code>.
     * @param validMonth The <code>validMonth</code> to set.
     */
    public void setValidMonth(String validMonth) {
        this.validMonth = validMonth;
    }

    /**
     * Gets <code>validYear</code>.
     * @return The <code>validYear</code>.
     */
    public String getValidYear() {
        return validYear;
    }

    /**
     * Sets <code>validYear</code>.
     * @param validYear The <code>validYear</code> to set.
     */
    public void setValidYear(String validYear) {
        this.validYear = validYear;
    }

    /**
     * Gets <code>registeredOn</code>.
     * @return The <code>registeredOn</code>.
     */
    public Date getRegisteredOn() {
        return registeredOn;
    }

    /**
     * Sets <code>registeredOn</code>.
     * @param registeredOn The <code>registeredOn</code> to set.
     */
    public void setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
    }

    /**
     * Gets <code>cardStatus</code>.
     * @return The <code>cardStatus</code>.
     */
    public CardStatusEnum getCardStatus() {
        return cardStatus;
    }

    /**
     * Sets <code>cardStatus</code>.
     * @param cardStatus The <code>cardStatus</code> to set.
     */
    public void setCardStatus(CardStatusEnum cardStatus) {
        this.cardStatus = cardStatus;
    }
    
    /**
     * Gets <code>accounts</code>.
     * @return The <code>accounts</code>.
     */
    public List<AccountInfo> getAccounts() {
        return accounts;
    }

    /**
     * Sets <code>accounts</code>.
     * @param accounts The <code>accounts</code> to set.
     */
    public void setAccounts(List<AccountInfo> accounts) {
        this.accounts = accounts;
    }

    /**
     * Gets <code>user</code>.
     * @return The <code>user</code>.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets <code>user</code>.
     * @param user The <code>user</code> to set.
     */
    public void setUser(User user) {
        this.user = user;
    }
    
    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "AccountCard [registeredCard=" + registeredCard + ", cif=" + cif + ", birthDate=" + birthDate
		+ ", validMonth=" + validMonth + ", validYear=" + validYear + ", registeredOn=" + registeredOn
		+ ", cardStatus=" + cardStatus + ", accounts=" + accounts + "]";
    }
}
