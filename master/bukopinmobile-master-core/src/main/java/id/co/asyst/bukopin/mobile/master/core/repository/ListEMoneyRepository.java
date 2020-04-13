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

import id.co.asyst.bukopin.mobile.master.model.entity.Configuration;
import id.co.asyst.bukopin.mobile.master.model.entity.FAQ;
import id.co.asyst.bukopin.mobile.master.model.entity.ListEMoney;

/**
 * Repository for List E-Money
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Jan 30, 2020
 * @since 1.1.Alpha1
 */
@Repository
public interface ListEMoneyRepository extends JpaRepository<ListEMoney, Long>{
	 @Query("SELECT a FROM ListEMoney a WHERE a.name= :name")
	    ListEMoney findEMoneyByName(@Param("name") String name);
	 
	 @Query("SELECT a, b FROM ListEMoney a, PrefixTelco b WHERE a.name= b.provider")
	    List<List<Object>> findAllEMoneywithPic();
}
