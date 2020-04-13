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

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.asyst.bukopin.mobile.master.model.entity.Currency;

/**
 * Repository for Currency
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 07, 2020
 * @since 1.1.Alpha.1
 */
@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long>{
	 @Query("SELECT a FROM Currency a WHERE a.code= :code")
	 Currency findCurrencyByCode(@Param("code") String code);
	 
	 @Query("SELECT a FROM Currency a WHERE a.currencyNo= :currencyNo")
	 Currency findCurrencyByCurrencyNo(@Param("currencyNo") String currencyNo);
}
