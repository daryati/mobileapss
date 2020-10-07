/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.asyst.bukopin.mobile.user.model.entity.NonFinancial;

/**
 * Spring Data repository for <code>NonFinancial</code> entity.
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Jun 16, 2020
 * @since 1.3.Alpha1-SNAPSHOT
 */
@Repository
public interface NonFinancialRepository extends JpaRepository<NonFinancial, Long> {
    
    /**
     * Find Non Financial by user id
     * 
     * @param id user's db id
     * @return List of non financial by user id
     */
    @Query("select a from NonFinancial a where a.user.id= ?1")
    public List<NonFinancial> findByUserId(Long id);
    
}
