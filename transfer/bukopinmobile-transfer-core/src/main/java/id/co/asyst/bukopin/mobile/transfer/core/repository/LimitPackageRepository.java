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
package id.co.asyst.bukopin.mobile.transfer.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.asyst.bukopin.mobile.transfer.model.entity.LimitPackage;

/**
 * Repository for Limit Package
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Jul 6, 2020
 * @since 1.4.Alpha1
 */
@Repository
public interface LimitPackageRepository extends JpaRepository<LimitPackage, Long>{

    /**
     * Find By IsDefault Y or N
     * @param isDefault default value to find
     * @return Persist Limit Package
     */
    public List<LimitPackage> findByIsDefault(boolean isDefault);
    
}
