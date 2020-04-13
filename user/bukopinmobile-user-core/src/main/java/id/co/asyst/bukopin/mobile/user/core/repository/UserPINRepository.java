/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.asyst.bukopin.mobile.user.model.entity.UserPIN;

/**
 * Spring Data repository for <code>UserPIN</code> entity.
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 3, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@Repository
public interface UserPINRepository extends JpaRepository<UserPIN, Long> {

}
