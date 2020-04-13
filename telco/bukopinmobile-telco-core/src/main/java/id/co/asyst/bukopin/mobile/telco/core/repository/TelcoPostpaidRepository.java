/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.telco.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.asyst.bukopin.mobile.telco.model.entity.TelcoPostpaid;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Feb 21, 2020
 * @since 1.1.Alpha1-SNAPSHOT
 */
@Repository
public interface TelcoPostpaidRepository extends JpaRepository<TelcoPostpaid, Long> {

}
