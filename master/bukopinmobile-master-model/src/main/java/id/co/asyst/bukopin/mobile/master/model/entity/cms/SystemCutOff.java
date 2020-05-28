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
package id.co.asyst.bukopin.mobile.master.model.entity.cms;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import id.co.asyst.foundation.common.model.IdBasedObject;

/**
 * System Cut Off Entity Model
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, May 27, 2020
 * @since 1.4.Alpha1
 */
@Entity
@Table(name="SYSTEM_CUT_OFF")
public class SystemCutOff extends IdBasedObject implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 7424953839915336390L;
    
    /* Constants: */

    /* Attributes: */
    /**
     * System Name 
     */
    @Column(name="SYSTEM_NAME", length=20, nullable=false)
    private String systemName;
    
    /**
     * Start Time of Cut Off
     */
    @Column(name="START_TIME", nullable=false)
    @Temporal(TemporalType.TIME)
    private Date startTime;
    
    /**
     * End Time of Cut Off
     */
    @Column(name="END_TIME", nullable=false)
    @Temporal(TemporalType.TIME)
    private Date endTime;
    
    /**
     * Last Update Date
     */
    @Column(name="UPDATED_DATE")
    private Date updatedDate;
    
    /**
     * Name of User who did last update.
     */
    @Column(name="USER_UPDATED", length=50)
    private String userUpdated;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>systemName</code>.
     * @return The <code>systemName</code>.
     */
    public String getSystemName() {
        return systemName;
    }

    /**
     * Sets <code>systemName</code>.
     * @param systemName The <code>systemName</code> to set.
     */
    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    /**
     * Gets <code>startTime</code>.
     * @return The <code>startTime</code>.
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Sets <code>startTime</code>.
     * @param startTime The <code>startTime</code> to set.
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets <code>endTime</code>.
     * @return The <code>endTime</code>.
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * Sets <code>endTime</code>.
     * @param endTime The <code>endTime</code> to set.
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * Gets <code>updatedDate</code>.
     * @return The <code>updatedDate</code>.
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * Sets <code>updatedDate</code>.
     * @param updatedDate The <code>updatedDate</code> to set.
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * Gets <code>userUpdated</code>.
     * @return The <code>userUpdated</code>.
     */
    public String getUserUpdated() {
        return userUpdated;
    }

    /**
     * Sets <code>userUpdated</code>.
     * @param userUpdated The <code>userUpdated</code> to set.
     */
    public void setUserUpdated(String userUpdated) {
        this.userUpdated = userUpdated;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "SystemCutOff [systemName=" + systemName + ", startTime=" + startTime + ", endTime=" + endTime
		+ ", updatedDate=" + updatedDate + ", userUpdated=" + userUpdated + "]";
    }
}
