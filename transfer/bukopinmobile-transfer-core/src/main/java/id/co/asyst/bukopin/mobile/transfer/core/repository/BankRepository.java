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

import id.co.asyst.bukopin.mobile.transfer.model.entity.Bank;

/**
 * Spring Data repository for <code>Bank</code> entity.
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Nov 25, 2019
 * @since 1.0.Alpha1
 */
@Repository
public interface BankRepository extends JpaRepository<Bank, Long>{

    /**
     * Find Bank by Bank Code.
     * 
     * @param bankCode Bank Code to find.
     * @return Selected Bank Code.
     */
    @Query("SELECT a FROM Bank a WHERE a.bankCode = :bankCode")
    Bank findBankByBankCode(@Param("bankCode") String bankCode);
  
    
 /*   @Query("SELECT a FROM FavoriteDestination a WHERE a.username = :username AND (a.name = :name)")
    List<FavoriteDestination> findFavoriteByname(@Param("username") String username,
	    @Param("name") String name);
    
    @Query("SELECT a FROM FavoriteDestination a WHERE a.username = :username")
    List<FavoriteDestination> findAllByusername(@Param("username") String username);*/
    
    
}
