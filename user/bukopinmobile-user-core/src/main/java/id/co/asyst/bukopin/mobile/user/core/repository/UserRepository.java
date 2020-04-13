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
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.asyst.bukopin.mobile.user.model.entity.User;

/**
 * 
 * 
 * @author Gibran Haq
 * @version $Revision$, Oct 31, 2019
 * @since 1.0.Alpha1
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT a FROM User a WHERE a.email = :email")
	User getUserByEmail(@Param("email") String email);
	
    /**
     * Find By Username
     * 
     * @param username User's username.
     * @return Persisted User.
     */
    public User findByUsername(String username);
    
    public User findByMobilePhone(String mobilePhone);

}
