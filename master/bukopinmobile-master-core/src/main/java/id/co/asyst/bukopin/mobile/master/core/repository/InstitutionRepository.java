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

import id.co.asyst.bukopin.mobile.master.model.entity.Institution;


/**
 * Repository for Institution
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 13, 2020
 * @since 1.0.Alpha.1
 */
@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long>{
	 @Query("SELECT a FROM Institution a WHERE a.prefixTelcoId = :prefixId")
	 List<Institution> findInstitutionByPrefixId(@Param("prefixId") Long prefixId);	 
	 
	 @Query("SELECT a FROM Institution a WHERE a.prefixTelcoId = :prefixId AND a.institutionType = :institutionType")
	 Institution findPrefixTelcoByPrefixNoAndType(@Param("prefixId") Long prefixId, @Param("institutionType") String institutionType);	 
	
	 @Query("SELECT a FROM Institution a WHERE a.prefixTelcoId.provider = :provider")
		Institution findProvider(@Param("provider") String provider);
}
