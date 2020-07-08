/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
/*
 * Copyright (c) 2020 Lufthansa Systems Indonesia, PT. All rights reserved.
 */
package id.co.asyst.bukopin.mobile.transfer.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import id.co.asyst.foundation.common.model.IdBasedObject;

/**
 * Limit Package Entity Model
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Jul 6, 2020
 * @since 1.4.Alpha1
 */
@Entity
@Table(name="LIMIT_PACKAGE")
public class LimitPackage  extends IdBasedObject implements Serializable {

    /**
     * Serial Versin UID
     */
    private static final long serialVersionUID = -6946903481951206091L;
    
    /* Constants: */

    /* Attributes: */
    /**
     * Limit Package's Profile Name
     */
    @Column(name="PROFILE_NAME")
    private String profileName;
    
    /**
     * Limit Saving Value
     */
    @Column(name="LIMIT_SAVING")
    private BigDecimal limitSaving;
    
    /**
     * Limit Giro value
     */
    @Column(name="LIMIT_GIRO")
    private BigDecimal limitGiro;
    
    /**
     * is default limit package
     */
    @Type(type = "yes_no")
    @Column(name="IS_DEFAULT", columnDefinition="CHAR(1) DEFAULT 'N'")
    private boolean isDefault;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>profileName</code>.
     * @return The <code>profileName</code>.
     */
    public String getProfileName() {
        return profileName;
    }

    /**
     * Sets <code>profileName</code>.
     * @param profileName The <code>profileName</code> to set.
     */
    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    /**
     * Gets <code>limitSaving</code>.
     * @return The <code>limitSaving</code>.
     */
    public BigDecimal getLimitSaving() {
        return limitSaving;
    }

    /**
     * Sets <code>limitSaving</code>.
     * @param limitSaving The <code>limitSaving</code> to set.
     */
    public void setLimitSaving(BigDecimal limitSaving) {
        this.limitSaving = limitSaving;
    }

    /**
     * Gets <code>limitGiro</code>.
     * @return The <code>limitGiro</code>.
     */
    public BigDecimal getLimitGiro() {
        return limitGiro;
    }

    /**
     * Sets <code>limitGiro</code>.
     * @param limitGiro The <code>limitGiro</code> to set.
     */
    public void setLimitGiro(BigDecimal limitGiro) {
        this.limitGiro = limitGiro;
    }

    /**
     * Gets <code>isDefault</code>.
     * @return The <code>isDefault</code>.
     */
    public boolean isDefault() {
        return isDefault;
    }

    /**
     * Sets <code>isDefault</code>.
     * @param isDefault The <code>isDefault</code> to set.
     */
    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "LimitPackage [profileName=" + profileName + ", limitSaving=" + limitSaving + ", limitGiro=" + limitGiro
		+ ", isDefault=" + isDefault + "]";
    }

}
