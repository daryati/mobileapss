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
import org.springframework.stereotype.Repository;

import id.co.asyst.bukopin.mobile.payment.model.entity.PLNPostpaid;

/**
 * PLN Postpaid Repository
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Jan 24, 2020
 * @since 1.0.Alpha1-SNAPSHOT
 */
@Repository
public interface PLNPostpaidRepository extends JpaRepository<PLNPostpaid, Long>{

}
