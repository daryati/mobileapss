/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.telco.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.asyst.bukopin.mobile.telco.model.entity.TelcoPrepaid;

/**
 * Repository for TelcoPrepaid
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 19, 2020
 * @since 1.0.Alpha1
 */
@Repository
public interface TelcoPrepaidRepository extends JpaRepository<TelcoPrepaid, Long> {

}
