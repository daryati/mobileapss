/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.master.core.repository;

import java.util.List;

import org.aspectj.weaver.ast.Call;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.asyst.bukopin.mobile.master.model.entity.Destination;
import id.co.asyst.bukopin.mobile.user.model.entity.User;

/**
 * Repository for Destination
 * 
 * @author Ihsan Firman
 * @version $Revision$, Dec 7, 2019
 * @since 2.0
 */
@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {

    /**
     * Find active destination by User id and Category
     * @param userId
     * @param isFavorite
     * @param categoryId
     * @return List Data of Destination
     */
    @Query("SELECT a FROM Destination a WHERE a.user.id = :userId AND (a.isFavorite = :isFavorite) AND (a.category.id = :categoryId)")
    List<Destination> findAllDestinationByUserIdAndCategory(@Param("userId") long userId, @Param("isFavorite") boolean isFavorite, @Param("categoryId") long categoryId);

    /**
     * Find active Destination by user id  
     * @param userId
     * @param isFavorite = true
     * @return List Data of Destination
     */
    @Query("SELECT a FROM Destination a WHERE a.user.id = :userId AND (a.isFavorite = :isFavorite)")
    List<Destination> findAllDestinationByUserId(@Param("userId") long userId, @Param("isFavorite") boolean isFavorite);
    
    /**
     * Find active Destination by user id and subscriber number
     * @param userId
     * @param number
     * @return Single data of Destination
     */
    @Query("SELECT a FROM Destination a WHERE a.user.id = :userId AND (a.subscriberNumber = :number)")
    Destination findAllDestinationByUserIdAndSubNum(@Param("userId") long userId, @Param("number") String number);
    
    /**
     * Find User's Destination by Type
     * 
     * @param userId The User's Id
     * @param subscriberNumber The Subscriber Number
     * @param destinationType The Destination Type
     * @return Persisted Destination
     */
    @Query("select d from Destination d WHERE d.user.id = ?1 and d.subscriberNumber = ?2 and d.destinationType=?3")
    Destination findUserDestinationType(long userId, String subscriberNumber, String destinationType);
    
    /**
     * Find active Destination based on user id and category id
     * @param username
     * @param categoryId
     * @return List Data of Destination
     */
    @Query("select d from Destination d where d.user.username=?1 and d.category.idCategory=?2 and d.isFavorite='Y'")
    public List<Destination> getFavoriteByUserAndCategory(String username, Long categoryId);
    
/*    @Query("select d from Destination d where d.user.username=?1 and d.category.idCategory=?2 and d.isFavorite='Y' order by counter desc")
    public List<Destination> getMostFavoriteByUserAndCategory(String username, Long categoryId);*/
}
