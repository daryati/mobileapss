/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.transfer.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.asyst.bukopin.mobile.transfer.model.entity.ReceiverInfo;
import id.co.asyst.bukopin.mobile.user.model.entity.User;

/**
 * Spring Data repository for <code>ReceiverInfo</code> entity.
 * 
 * @author Ihsan Firman
 * @version $Revision$, Nov 14, 2019
 * @since 1.0.Alpha1
 */
@Repository
public interface ReceiverInfoRepository extends JpaRepository<ReceiverInfo, Long> {

    /**
     * Finds Fav? or ReceiverInfo? byUserIdAndAccountNum<br/>
     * TODO: Document Me.
     * 
     * @param userId User ID to find.
     * @param accountNumber Account Number to find.
     * @return Selected <code>ReceiverInfo</code>.
     */
    @Query("SELECT a FROM ReceiverInfo a WHERE a.username= :username AND (a.accountNumber = :accountNumber)")
    ReceiverInfo findFavByUserIdAndAccountNum(@Param("username") User userId,
            @Param("accountNumber") String accountNumber);

    /**
     * TODO: Document Me. findBukopinReceiver
     * 
     * @param username Document Me.
     * @param bank Document Me.
     * @return Document Me.
     */
    @Query("SELECT a FROM ReceiverInfo a WHERE a.username.id = :username AND (a.bank.bankCode = :bank) AND (a.isSave = :isSave)")
    List<ReceiverInfo> findBukopinReceiver(@Param("username") long username, @Param("bank") String bank, @Param("isSave") boolean isSave);

    @Query("SELECT a FROM ReceiverInfo a WHERE a.username.id = :username AND (a.bank.bankCode <> :bank) AND (a.isSave = :isSave)")
    List<ReceiverInfo> findNotBukopinReceiver(@Param("username") long username, @Param("bank") String bank, @Param("isSave") boolean isSave);
    
    @Query("SELECT a FROM ReceiverInfo a WHERE a.username = :username AND (a.isSave = :isSave)")
    List<ReceiverInfo> findAllByusername(@Param("username") String username, @Param("isSave") boolean isSave);
    
    @Query("select ri from ReceiverInfo ri where ri.username.username=?1 AND ri.isSave = ?2 order by counter desc")
    List<ReceiverInfo> findMostFrequent(String username, boolean isSave);

}
