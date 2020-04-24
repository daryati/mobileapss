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

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import id.co.asyst.bukopin.mobile.payment.model.entity.CreditCard;
import id.co.asyst.bukopin.mobile.payment.model.entity.ListCredit;
import io.lettuce.core.dynamic.annotation.Param;

/**
 * Repository for Credit Card
 * 
 * @author Ihsan Firman
 * @version $Revision$, Apr 6, 2020
 * @since 2.0
 */
public interface CreditCardRepository extends JpaRepository<CreditCard, Long>{


}
