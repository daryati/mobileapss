/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.transfer.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import id.co.asyst.bukopin.mobile.user.model.entity.User;

/**
 * 
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$, Nov 25, 2019
 * @since 2.0
 */
@Entity
public class UserTransfer extends User implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /* Constants: */

    /* Attributes: */
    /*@OneToMany(fetch=FetchType.EAGER, mappedBy="username", cascade=CascadeType.ALL)
    private List<FundTransfer> fundTransfer;
*/    
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany( mappedBy="username", cascade=CascadeType.ALL)
    private List<ReceiverInfo> receiverInfo;
    
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany( mappedBy="username", cascade=CascadeType.ALL)
    private List<FundTransfer> fundTransfer;

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */
    
    /**
     * Gets <code>id</code>.
     * 
     * @return The <code>id</code>.
     */
  /* 
    public List<FundTransfer> getFundTransfer() {
        return fundTransfer;
    }

    public void setFundTransfer(List<FundTransfer> fundTransfer) {
        this.fundTransfer = fundTransfer;
    }*/


    /* Getters & setters for transient attributes: */

    /* Functionalities: */

   

   


   

}
