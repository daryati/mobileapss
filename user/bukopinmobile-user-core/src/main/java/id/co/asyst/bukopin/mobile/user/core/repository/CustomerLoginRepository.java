/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.asyst.bukopin.mobile.user.model.entity.CustomerLogin;

/**
 * Spring Data repository for <code>CustomerLogin</code> entity.
 * 
 * @author Abi Nanel
 * @version $Revision$, Mar 4, 2020
 * @since 1.1.Alpha1
 */
@Repository
public interface CustomerLoginRepository extends JpaRepository<CustomerLogin, Long> {

    public CustomerLogin findTopByUsernameOrderByLoginAtDesc(String username);
}
