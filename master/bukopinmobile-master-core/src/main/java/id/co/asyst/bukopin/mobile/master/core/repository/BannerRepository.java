/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
/*
 * Copyright (c) 2020 Lufthansa Systems Indonesia, PT. All rights reserved.
 */
package id.co.asyst.bukopin.mobile.master.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.asyst.bukopin.mobile.master.model.entity.cms.Banner;


/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Apr 29, 2020
 * @since 2.0
 */
@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {
	
	@Query("SELECT a FROM Banner a WHERE a.status = :status")
	List<Banner> getActiveBanner(@Param("status") boolean status);

}
