/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.master.model.entity.cms;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Approval Matriks Model
 * 
 * @author Abi Nanel
 * @version $Revision$, Mar 13, 2020
 * @since 1.1.Alpha1
 */
@Entity
@Table(name = "APPROVAL_MATRIKS")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApprovalMatriks implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /* Constants: */

    /* Attributes: */
    @Id
    @Column(name = "ID_MENU", nullable = false)
    private long idMenu;

    @Column(name = "MENU_NAME", length = 30)
    private String menuName;

    @Column(name = "APPROVAL_LEVEL")
    private Long approvalLevel;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>idMenu</code>.
     * 
     * @return The <code>idMenu</code>.
     */
    public long getIdMenu() {
	return idMenu;
    }

    /**
     * Sets <code>idMenu</code>.
     * 
     * @param idMenu
     *            The <code>idMenu</code> to set.
     */
    public void setIdMenu(long idMenu) {
	this.idMenu = idMenu;
    }

    /**
     * Gets <code>menuName</code>.
     * 
     * @return The <code>menuName</code>.
     */
    public String getMenuName() {
	return menuName;
    }

    /**
     * Sets <code>menuName</code>.
     * 
     * @param menuName
     *            The <code>menuName</code> to set.
     */
    public void setMenuName(String menuName) {
	this.menuName = menuName;
    }

    /**
     * Gets <code>approvalLevel</code>.
     * 
     * @return The <code>approvalLevel</code>.
     */
    public Long getApprovalLevel() {
	return approvalLevel;
    }

    /**
     * Sets <code>approvalLevel</code>.
     * 
     * @param approvalLevel
     *            The <code>approvalLevel</code> to set.
     */
    public void setApprovalLevel(Long approvalLevel) {
	this.approvalLevel = approvalLevel;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "ApprovalMatriks [idMenu=" + idMenu + ", menuName=" + menuName + ", approvalLevel=" + approvalLevel
		+ "]";
    }
}
