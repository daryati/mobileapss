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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import id.co.asyst.foundation.common.model.IdBasedObject;

/**
 * User PIN Entity Model
 * Centagate doesn't have service to save PIN. So, we use Question and Answer service to save PIN.
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 3, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@Entity
@Table(name="PIN_AUTH")
public class UserPIN extends IdBasedObject implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 267713838066573675L;
    
    /* Constants: */

    /* Attributes: */
    /**
     * Username who has PIN
     */
    @OneToOne
    @JoinColumn(name="APP_USER", unique=true, referencedColumnName = "USERNAME")
    private User user;
    
    /**
     * Centagate Security Question ID
     */
    @Column(name="QUESTION_ID")
    private String questionId;
    
    /**
     * The Question Text
     */
    @Column(name="QUESTION_TEXT", length=200)
    private String question;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
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

    /**
     * Gets <code>questionId</code>.
     * @return The <code>questionId</code>.
     */
    public String getQuestionId() {
        return questionId;
    }

    /**
     * Sets <code>questionId</code>.
     * @param questionId The <code>questionId</code> to set.
     */
    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    /**
     * Gets <code>question</code>.
     * @return The <code>question</code>.
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Sets <code>question</code>.
     * @param question The <code>question</code> to set.
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "UserPIN [user=" + user + ", questionId=" + questionId + ", question=" + question
		+ "]";
    }
}
