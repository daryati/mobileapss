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

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.asyst.bukopin.mobile.user.model.entity.AccountCard;

/**
 * Spring Data repository for <code>AccountCard</code> entity.
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 6, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@Repository
public interface AccountCardRepository extends JpaRepository<AccountCard, Long> {
    
    /**
     * Find Account Card by user id
     * 
     * @param id user's db id
     * @return Account Card by user id
     */
    @Query("select ac from AccountCard ac where ac.user.id= ?1")
    public AccountCard findByUserId(Long id);
    
    /**
     * Find Account Card by CIF
     * 
     * @param cif user's CIF
     * @return Account Card by CIF
     */
    public AccountCard findByCif(String cif);
    
    /**
     * Find Account Cards By Username
     * 
     * @param username The User's username
     * @return List of User's Account Cards.
     */
    @Query("select ac from AccountCard ac where ac.user.username= ?1")
    public List<AccountCard> findByUsername(String username);
    
    /**
     * Find Account Card by Registered Card
     * 
     * @param registCard user's Registered Card
     * @return Account Card by Registered Card
     */
    public AccountCard findByRegisteredCard(String registCard);
}
