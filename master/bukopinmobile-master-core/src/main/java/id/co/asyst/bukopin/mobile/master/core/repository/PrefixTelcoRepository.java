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

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.asyst.bukopin.mobile.master.model.entity.PrefixTelco;

/**
 * Repository for Prefix Telco
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 13, 2020
 * @since 1.0.Alpha.1
 */
@Repository
public interface PrefixTelcoRepository extends JpaRepository<PrefixTelco, Long>{
	 @Query("SELECT a FROM PrefixTelco a WHERE a.prefixNo LIKE :prefix")
	 List<PrefixTelco> findPrefixTelcoByPrefixNo(@Param("prefix") String prefix);	 
	 
	 @Query("SELECT a FROM PrefixTelco a WHERE a.prefixNo LIKE :prefix AND a.type = :type")
	 PrefixTelco findPrefixTelcoByPrefixNoAndType(@Param("prefix") String prefix, @Param("type") String type);
	 
	 @Query("SELECT a FROM PrefixTelco a, Institution b WHERE a.id =:id AND b.prefixTelcoId =:id AND b.institutionType =:type")
	 PrefixTelco findPrefixTelcoBidNoAndIntitutionType(@Param("id") Long id, @Param("type") String institutionType);
	 
	 @Query("SELECT a.provider, a.pgroup, a.picture, a.id, b.codeArra, b.codeCbs FROM PrefixTelco a, Institution b WHERE a.id = b.prefixTelcoId and a.type = 'POST' and a.prefixNo LIKE :prefix")
	 Object findOnePrefixTelcoByPrefixNo(@Param("prefix") String prefix);
	
}
