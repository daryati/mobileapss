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
import org.springframework.stereotype.Repository;

import id.co.asyst.bukopin.mobile.master.model.entity.FAQ;

/**
 * Repository for Frequently Asked Question
 * 
 * @author Ihsan Firman
 * @version $Revision$, Nov 20, 2019
 * @since 2.0
 */
@Repository
public interface FAQRepository extends JpaRepository<FAQ, Long>{

}
