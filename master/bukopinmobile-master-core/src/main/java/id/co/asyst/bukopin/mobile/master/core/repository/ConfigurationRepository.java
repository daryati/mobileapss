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

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.asyst.bukopin.mobile.master.model.entity.Configuration;
import id.co.asyst.bukopin.mobile.master.model.entity.FAQ;

/**
 * Repository for configuration
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Nov 27, 2019
 * @since 2.0
 */
@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Long>{
	 @Query("SELECT a FROM Configuration a WHERE a.name= :name")
	    Configuration findConfigByName(@Param("name") String name);
}
