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
     * Limit Saving Value for overbook
     */
    @Column(name="LIMIT_OB_SAVING")
    private BigDecimal limitObSaving;
    
    /**
     * Limit Giro value for overbook
     */
    @Column(name="LIMIT_OB_GIRO")
    private BigDecimal limitObGiro;
    
    /**
     * Limit Saving Value for fund transfer
     */
    @Column(name="LIMIT_FT_SAVING")
    private BigDecimal limitFtSaving;
    
    /**
     * Limit Giro value for fund transfer
     */
    @Column(name="LIMIT_FT_GIRO")
    private BigDecimal limitFtGiro;
    
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

    /**
     * Gets <code>limitObSaving</code>.
     * @return The <code>limitObSaving</code>.
     */
    public BigDecimal getLimitObSaving() {
        return limitObSaving;
    }

    /**
     * Sets <code>limitObSaving</code>.
     * @param limitObSaving The <code>limitObSaving</code> to set.
     */
    public void setLimitObSaving(BigDecimal limitObSaving) {
        this.limitObSaving = limitObSaving;
    }

    /**
     * Gets <code>limitObGiro</code>.
     * @return The <code>limitObGiro</code>.
     */
    public BigDecimal getLimitObGiro() {
        return limitObGiro;
    }

    /**
     * Sets <code>limitObGiro</code>.
     * @param limitObGiro The <code>limitObGiro</code> to set.
     */
    public void setLimitObGiro(BigDecimal limitObGiro) {
        this.limitObGiro = limitObGiro;
    }

    /**
     * Gets <code>limitFtSaving</code>.
     * @return The <code>limitFtSaving</code>.
     */
    public BigDecimal getLimitFtSaving() {
        return limitFtSaving;
    }

    /**
     * Sets <code>limitFtSaving</code>.
     * @param limitFtSaving The <code>limitFtSaving</code> to set.
     */
    public void setLimitFtSaving(BigDecimal limitFtSaving) {
        this.limitFtSaving = limitFtSaving;
    }

    /**
     * Gets <code>limitFtGiro</code>.
     * @return The <code>limitFtGiro</code>.
     */
    public BigDecimal getLimitFtGiro() {
        return limitFtGiro;
    }

    /**
     * Sets <code>limitFtGiro</code>.
     * @param limitFtGiro The <code>limitFtGiro</code> to set.
     */
    public void setLimitFtGiro(BigDecimal limitFtGiro) {
        this.limitFtGiro = limitFtGiro;
    }

    /**
     * Gets <code>serialversionuid</code>.
     * @return The <code>serialversionuid</code>.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "LimitPackage [profileName=" + profileName + ", limitObSaving=" + limitObSaving + ", limitObGiro="
		+ limitObGiro + ", limitFtSaving=" + limitFtSaving + ", limitFtGiro=" + limitFtGiro + ", isDefault="
		+ isDefault + "]";
    }

}
