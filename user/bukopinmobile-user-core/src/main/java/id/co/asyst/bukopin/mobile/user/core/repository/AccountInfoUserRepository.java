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
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import id.co.asyst.bukopin.mobile.user.model.AccountStatusEnum;
import id.co.asyst.bukopin.mobile.user.model.entity.AccountInfo;

/**
 * Spring Data repository for <code>AccountInfo</code> entity.
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 6, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@Repository
public interface AccountInfoUserRepository extends JpaRepository<AccountInfo, Long> {

    public List<AccountInfo> findByCif(String cif);

    @Query("SELECT a FROM AccountInfo a WHERE a.cif = :cif AND (a.accountStatus = :accountStatus)")
    public List<AccountInfo> findActiveByCif(@Param(value = "cif") String cif,
	    @Param(value = "accountStatus") AccountStatusEnum accountStatus);

    @Modifying
    @Transactional
    @Query(value = "delete from AccountInfo a where a.cif = ?1")
    public void deleteByCif(String cif);

    @Modifying
    @Transactional
    @Query(value = "delete from AccountInfo a where a.accountCard.id = ?1")
    public void deleteByAccountCardId(Long id);
    
    @Query("SELECT a FROM AccountInfo a WHERE a.accountNo = :accountNo")
    public AccountInfo findAccountInfoByAccountNo(@Param("accountNo") String accountNo);
    
    @Query("SELECT a FROM AccountInfo a WHERE a.accountCard.user.username = ?1")
    public List<AccountInfo> findByUsername(String username);
    
}
