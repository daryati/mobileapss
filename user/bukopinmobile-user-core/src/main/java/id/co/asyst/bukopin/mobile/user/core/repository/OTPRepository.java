/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.core.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.asyst.bukopin.mobile.user.model.OTPTypeEnum;
import id.co.asyst.bukopin.mobile.user.model.entity.OneTimePassword;

/**
 * Spring Data repository for <code>OneTimePassword</code> entity.
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 1, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@Repository
public interface OTPRepository extends JpaRepository<OneTimePassword, Long> {

    /**
     * Validate OTP
     * 
     * @param receiver
     *            Receiver to find
     * @param otp
     *            OTP to find
     * @param currentDate
     *            Current date
     * 
     * @return Persist valid otp.
     */
    public OneTimePassword findByReceiverAndOtpAndValidUntilAfter(String receiver, String otp, Date currentDate);

    /**
     * Find active OTP
     * 
     * @param receiver
     *            Receiver to search
     * @param type
     *            Type to search
     * @param currentDate
     *            Current date
     * @return Persist valid otp.
     */
    public List<OneTimePassword> findByReceiverAndTypeAndValidUntilAfter(String receiver, OTPTypeEnum type,
	    Date currentDate);
    
    /**
     * Find By Receiver And Type
     * 
     * @param receiver Receiver to search
     * @param type Type to search
     * @return Persist OTP.
     */
    public List<OneTimePassword> findByReceiverAndType(String receiver, OTPTypeEnum type);

}
