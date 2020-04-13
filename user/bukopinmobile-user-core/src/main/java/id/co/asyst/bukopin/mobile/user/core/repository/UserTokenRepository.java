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

import id.co.asyst.bukopin.mobile.user.model.security.UserToken;

/**
 * Spring Data repository for <code>UserToken</code> entity.
 * 
 * @author Stephen Dharma
 * @version $Revision$, (Created on Oct 24, 2019)
 * @since 1.0.Alpha1
 */
@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, String> {

     @Query("SELECT a FROM UserToken a where a.token = :token")
     UserToken getUserTOkenByToken(@Param("token") String token);
     
    /**
     * Find by Username and Token and Token Status
     * 
     * @param username
     *            The Username of User's to find
     * @param token
     *            The login token
     * @param active
     *            The token status
     * @return Persisted UserToken
     */
    public UserToken findByUsernameAndTokenAndActive(String username, String token, boolean active);
}
