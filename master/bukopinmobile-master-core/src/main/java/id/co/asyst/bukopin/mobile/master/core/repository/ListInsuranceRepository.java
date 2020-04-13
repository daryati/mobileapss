/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.master.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.asyst.bukopin.mobile.master.model.entity.ListInsurance;

/**
 * Repository for List Insurance
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Mar 16, 2020
 * @since 1.1.Alpha1
 */
@Repository
public interface ListInsuranceRepository extends JpaRepository<ListInsurance, Long>{
	 @Query("SELECT a FROM ListInsurance a WHERE a.codeIns= :code")
	 ListInsurance findInsuranceByCode(@Param("code") String code);
	 
	 @Query("SELECT a, b FROM ListInsurance a, PrefixTelco b WHERE UPPER(a.codeIns) = UPPER(b.provider)")
	    List<List<Object>> findAllInsurancewithPic();
}
