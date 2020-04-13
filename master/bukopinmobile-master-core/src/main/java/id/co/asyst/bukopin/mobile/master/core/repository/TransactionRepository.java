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

import id.co.asyst.bukopin.mobile.master.model.entity.Transaction;

/**
 * Spring Data repository for <code>Transaction</code> entity.
 * 
 * @author Abi Nanel
 * @version $Revision$, Dec 11, 2019
 * @since 1.0.Alpha1
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
