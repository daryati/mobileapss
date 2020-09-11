/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
/*
 * Copyright (c) 2020 Lufthansa Systems Indonesia, PT. All rights reserved.
 */
package id.co.asyst.bukopin.mobile.master.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.asyst.bukopin.mobile.master.model.entity.cms.SystemCutOff;

/**
 * System Cut Off Repository
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, May 27, 2020
 * @since 1.4.Alpha1
 */
@Repository
public interface SystemCutOffRepository extends JpaRepository<SystemCutOff, Long>{

}
