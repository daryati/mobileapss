/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.transfer.core.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.asyst.bukopin.mobile.transfer.model.entity.FundTransfer;

/**
 * Spring Data repository for <code>FundTransfer</code> entity.
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$, Nov 25, 2019
 * @since 2.0
 */
@Repository
public interface FundTransferRepository extends JpaRepository<FundTransfer, Long>{

    /**
     * Find Fund Transfer By Username
     * @param username username to find
     * @return selected FunTransfer.
     */
    @Query("SELECT a FROM FundTransfer a WHERE a.username = :username ")
    FundTransfer findFundTransferByUsername(@Param("username") String username);
    
    /**
     * Find Fund Transfer By accountNumber
     * @param accountNumber account number to find.
     * @return selected FundTransfer.
     */
    @Query("SELECT a FROM FundTransfer a WHERE a.accountNumber = :accountNumber ")
    FundTransfer findFundTransferByAccountNumber(@Param("accountNumber") Integer accountNumber);
    
    
}
