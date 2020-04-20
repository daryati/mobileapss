/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.payment.core.service.repository;

import org.aspectj.weaver.ast.Call;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.asyst.bukopin.mobile.payment.model.entity.ListCredit;
import io.lettuce.core.dynamic.annotation.Param;

/**
 * List Credit Repository
 * 
 * @author Ihsan Firman
 * @version $Revision$, Apr 6, 2020
 * @since 2.0
 */
@Repository
public interface ListCreditRepository extends JpaRepository<ListCredit, Long>{

    /**
     * Find List Credit By Code CC
     * @param codeCc
     * @return Single data of List Credit
     */
    @Query("SELECT a FROM ListCredit a WHERE a.codeCc = :codeCc")
    ListCredit findByCodeCc(@Param("codeCc") String codeCc);
    
    /**
     * Find List Credit By Name
     * @param name
     * @return Single data of List Credit
     */
    @Query("SELECT a FROM ListCredit a WHERE a.name = :name")
    ListCredit findByName(@Param("name") String name);
}
