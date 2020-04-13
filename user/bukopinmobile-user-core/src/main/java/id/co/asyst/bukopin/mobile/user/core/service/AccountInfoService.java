/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.core.service;

import javax.xml.ws.Holder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.asyst.bukopin.mobile.user.core.service.soap.AccountStatementServices;
import id.co.asyst.bukopin.mobile.user.core.service.soap.PortType;
import id.co.asyst.bukopin.mobile.user.model.soap.account.Fault;
import id.co.asyst.bukopin.mobile.user.model.soap.account.GetAccountBalanceReqType;
import id.co.asyst.bukopin.mobile.user.model.soap.account.GetAccountBalanceResType;
import id.co.asyst.bukopin.mobile.user.model.soap.account.GetInquiryTransactionReqType;
import id.co.asyst.bukopin.mobile.user.model.soap.account.GetInquiryTransactionResType;
import id.co.asyst.bukopin.mobile.user.model.soap.account.HeaderRQ;
import id.co.asyst.bukopin.mobile.user.model.soap.account.HeaderRS;

/**
 * Service Connector to Account Info SOAP service.
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Oct 27, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@Service
public class AccountInfoService implements PortType {
	
	/* Constants: */

	/* Attributes: */
	@Autowired
	private AccountStatementServices accountInfoClient;

	/* Transient Attributes: */

	/* Constructors: */

	/* Getters & setters for attributes: */

	/* Getters & setters for transient attributes: */

	/* Functionalities: */

	/* Overrides: */
	@Override
	public void getInquiryTransaction(HeaderRQ headerRQ, GetInquiryTransactionReqType getInquiryTransactionRQ,
			Holder<HeaderRS> headerRS, Holder<GetInquiryTransactionResType> getInquiryTransactionRS) throws Fault {
		accountInfoClient.getAccountStatementPTEndpointBinding().getInquiryTransaction(
				headerRQ, getInquiryTransactionRQ, headerRS, getInquiryTransactionRS);
	}

	@Override
	public void getAccountBalance(HeaderRQ headerRQ, GetAccountBalanceReqType getAccountBalanceRQ,
			Holder<HeaderRS> headerRS, Holder<GetAccountBalanceResType> getAccountBalanceRS) throws Fault {
		accountInfoClient.getAccountStatementPTEndpointBinding().getAccountBalance(
				headerRQ, getAccountBalanceRQ, headerRS, getAccountBalanceRS);
	}
}
