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

import id.co.asyst.bukopin.mobile.payment.model.entity.Samolnas;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Apr 29, 2020
 * @since 1.2.Alpha1
 */
@Repository
public interface SamolnasRepository extends JpaRepository<Samolnas, Long> {

}
