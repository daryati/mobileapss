/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.payment.core.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.asyst.bukopin.mobile.payment.model.entity.Insurance;


/**
 * 
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Mar 13, 2020
 * @since 1.1.Alpha1
 */
@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {

}
