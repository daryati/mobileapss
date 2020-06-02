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
package id.co.asyst.bukopin.mobile.master.core.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import id.co.asyst.bukopin.mobile.master.core.repository.BannerRepository;
import id.co.asyst.bukopin.mobile.master.model.entity.cms.Banner;

/**
 * 
 * 
 * @author Ihsan Firman
 * @version $Revision$, Apr 29, 2020
 * @since 2.0
 */
@Service
@Transactional
public class BannerService {
	/* Constants: */

	/* Attributes: */
	@Autowired
	private BannerRepository bannerRepository;
	/* Transient Attributes: */

	/* Constructors: */

	/* Getters & setters for attributes: */

	/* Getters & setters for transient attributes: */

	/* Functionalities: */
	@Transactional
	public List<Banner> getAllBanner(){
		return bannerRepository.findAll();
	}
	
	@Transactional
	public Banner saveBanner(Banner banner) {
		return bannerRepository.save(banner);
	}
	
	@Transactional
	public Banner getBannerById(long id) {
		Optional<Banner> result = bannerRepository.findById(id);
		return result.get();
	}
	
	@Transactional
	public void deleteBanner(long id) {
		bannerRepository.deleteById(id);
	}
	
	@Transactional
	public List<Banner> getActiveBanner(boolean status){
		return bannerRepository.getActiveBanner(status);
	}

	/* Overrides: */}
