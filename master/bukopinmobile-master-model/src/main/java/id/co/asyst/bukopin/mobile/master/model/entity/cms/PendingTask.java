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
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Pending Task Model
 * 
 * @author Abi Nanel
 * @version $Revision$, Mar 13, 2020
 * @since 1.1.Alpha1
 */
@Entity
@Table(name = "PENDING_TASK")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PendingTask implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /* Constants: */

    /* Attributes: */
    @Id
    @Column(name = "ID_PENDING_TASK", nullable = false)
    private long idPendingTask;

    /**
     * Join ApprovalMatriks Table
     */
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "ID_MENU")
    private ApprovalMatriks approvalMatriks;

    @Column(name = "STATUS", length = 10)
    private String status;

    @Column(name = "DESCRIPTION", length = 255)
    private String description;

    @Column(name = "CURRENT_APPROVE_LEVEL")
    private Long currentApproveLevel;

    @Column(name = "TODO", length = 255)
    private String todo;

    @Column(name = "REJECT_REASON", length = 255)
    private String rejectReason;

    @Column(name = "REQUESTED_BY", length = 50)
    private String requestedBy;

    @Column(name = "UPDATED_BY", length = 50)
    private String updatedBy;

    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    /**
     * Gets <code>idPendingTask</code>.
     * 
     * @return The <code>idPendingTask</code>.
     */
    public long getIdPendingTask() {
	return idPendingTask;
    }

    /**
     * Sets <code>idPendingTask</code>.
     * 
     * @param idPendingTask
     *            The <code>idPendingTask</code> to set.
     */
    public void setIdPendingTask(long idPendingTask) {
	this.idPendingTask = idPendingTask;
    }

    /**
     * Gets <code>approvalMatriks</code>.
     * 
     * @return The <code>approvalMatriks</code>.
     */
    public ApprovalMatriks getApprovalMatriks() {
	return approvalMatriks;
    }

    /**
     * Sets <code>approvalMatriks</code>.
     * 
     * @param approvalMatriks
     *            The <code>approvalMatriks</code> to set.
     */
    public void setApprovalMatriks(ApprovalMatriks approvalMatriks) {
	this.approvalMatriks = approvalMatriks;
    }

    /**
     * Gets <code>status</code>.
     * 
     * @return The <code>status</code>.
     */
    public String getStatus() {
	return status;
    }

    /**
     * Sets <code>status</code>.
     * 
     * @param status
     *            The <code>status</code> to set.
     */
    public void setStatus(String status) {
	this.status = status;
    }

    /**
     * Gets <code>description</code>.
     * 
     * @return The <code>description</code>.
     */
    public String getDescription() {
	return description;
    }

    /**
     * Sets <code>description</code>.
     * 
     * @param description
     *            The <code>description</code> to set.
     */
    public void setDescription(String description) {
	this.description = description;
    }

    /**
     * Gets <code>currentApproveLevel</code>.
     * 
     * @return The <code>currentApproveLevel</code>.
     */
    public Long getCurrentApproveLevel() {
	return currentApproveLevel;
    }

    /**
     * Sets <code>currentApproveLevel</code>.
     * 
     * @param currentApproveLevel
     *            The <code>currentApproveLevel</code> to set.
     */
    public void setCurrentApproveLevel(Long currentApproveLevel) {
	this.currentApproveLevel = currentApproveLevel;
    }

    /**
     * Gets <code>todo</code>.
     * 
     * @return The <code>todo</code>.
     */
    public String getTodo() {
	return todo;
    }

    /**
     * Sets <code>todo</code>.
     * 
     * @param todo
     *            The <code>todo</code> to set.
     */
    public void setTodo(String todo) {
	this.todo = todo;
    }

    /**
     * Gets <code>rejectReason</code>.
     * 
     * @return The <code>rejectReason</code>.
     */
    public String getRejectReason() {
	return rejectReason;
    }

    /**
     * Sets <code>rejectReason</code>.
     * 
     * @param rejectReason
     *            The <code>rejectReason</code> to set.
     */
    public void setRejectReason(String rejectReason) {
	this.rejectReason = rejectReason;
    }

    /**
     * Gets <code>requestedBy</code>.
     * 
     * @return The <code>requestedBy</code>.
     */
    public String getRequestedBy() {
	return requestedBy;
    }

    /**
     * Sets <code>requestedBy</code>.
     * 
     * @param requestedBy
     *            The <code>requestedBy</code> to set.
     */
    public void setRequestedBy(String requestedBy) {
	this.requestedBy = requestedBy;
    }

    /**
     * Gets <code>updatedBy</code>.
     * 
     * @return The <code>updatedBy</code>.
     */
    public String getUpdatedBy() {
	return updatedBy;
    }

    /**
     * Sets <code>updatedBy</code>.
     * 
     * @param updatedBy
     *            The <code>updatedBy</code> to set.
     */
    public void setUpdatedBy(String updatedBy) {
	this.updatedBy = updatedBy;
    }

    /**
     * Gets <code>createDate</code>.
     * 
     * @return The <code>createDate</code>.
     */
    public Date getCreateDate() {
	return createDate;
    }

    /**
     * Sets <code>createDate</code>.
     * 
     * @param createDate
     *            The <code>createDate</code> to set.
     */
    public void setCreateDate(Date createDate) {
	this.createDate = createDate;
    }

    /**
     * Gets <code>updateDate</code>.
     * 
     * @return The <code>updateDate</code>.
     */
    public Date getUpdateDate() {
	return updateDate;
    }

    /**
     * Sets <code>updateDate</code>.
     * 
     * @param updateDate
     *            The <code>updateDate</code> to set.
     */
    public void setUpdateDate(Date updateDate) {
	this.updateDate = updateDate;
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
	return "PendingTask [idPendingTask=" + idPendingTask + ", approvalMatriks=" + approvalMatriks + ", status="
		+ status + ", description=" + description + ", currentApproveLevel=" + currentApproveLevel + ", todo="
		+ todo + ", rejectReason=" + rejectReason + ", requestedBy=" + requestedBy + ", updatedBy=" + updatedBy
		+ ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
    }

}
