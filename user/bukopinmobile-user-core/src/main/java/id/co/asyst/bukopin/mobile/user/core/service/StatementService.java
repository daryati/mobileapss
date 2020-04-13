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

import id.co.asyst.bukopin.mobile.user.core.service.soap.StatementPT;
import id.co.asyst.bukopin.mobile.user.core.service.soap.StatementServiceService;
import id.co.asyst.bukopin.mobile.user.model.soap.statement.Fault;
import id.co.asyst.bukopin.mobile.user.model.soap.statement.GetMiniStatementReqType;
import id.co.asyst.bukopin.mobile.user.model.soap.statement.GetMiniStatementResType;
import id.co.asyst.bukopin.mobile.user.model.soap.statement.GetStatementByDateRangeReqType;
import id.co.asyst.bukopin.mobile.user.model.soap.statement.GetStatementByDateRangeResType;
import id.co.asyst.bukopin.mobile.user.model.soap.statement.HeaderRQ;
import id.co.asyst.bukopin.mobile.user.model.soap.statement.HeaderRS;
import id.co.asyst.bukopin.mobile.user.model.soap.statement.ObjectFactory;

/**
 * Service Implementation for Managing Account Info
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$, Nov 04, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@Service
public class StatementService implements StatementPT {
	
	/* Constants: */

	/* Attributes: */
	@Autowired
	private StatementServiceService statementService;
	
	ObjectFactory objectFactory = new ObjectFactory();

	/* Transient Attributes: */

	/* Constructors: */

	/* Getters & setters for attributes: */

	/* Getters & setters for transient attributes: */

	/* Functionalities: */

	/* Overrides: */
	
	@Override
	public void getMiniStatement(HeaderRQ headerRQ,
			GetMiniStatementReqType getMiniStatementRQ,
			Holder<HeaderRS> headerRS,
			Holder<GetMiniStatementResType> getMiniStatementRS)
			throws Fault {
		statementService.getStatementPTEndpointBinding().getMiniStatement(
				headerRQ, getMiniStatementRQ, headerRS, getMiniStatementRS);
		
	}

	@Override
	public void getStatementByDateRange(HeaderRQ headerRQ,
			GetStatementByDateRangeReqType statementRQ,
			Holder<HeaderRS> headerRS,
			Holder<GetStatementByDateRangeResType> statementRS)
			throws Fault {
		statementService.getStatementPTEndpointBinding().getStatementByDateRange(
				headerRQ, statementRQ, headerRS, statementRS);
		
	}
	
	
	
}
