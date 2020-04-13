/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.master.core.service;

import javax.xml.ws.Holder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.asyst.bukopin.mobile.master.core.service.soap.CBSExchangeRate;
import id.co.asyst.bukopin.mobile.master.core.service.soap.ExchangeRatePT;
import id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate.Fault;
import id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate.HeaderRQ;
import id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate.HeaderRS;
import id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate.Reqexchangerate;
import id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate.Resexchangerate;

/**
 * Service Connector to Account Info SOAP service.
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 10, 2020
 * @since 1.1.Alpha1
 */
@Service
public class ExchangeRatePTService implements ExchangeRatePT {
	
	/* Constants: */

	/* Attributes: */
	
	@Autowired
	private CBSExchangeRate exchangeRate;

	/* Transient Attributes: */

	/* Constructors: */

	/* Getters & setters for attributes: */

	/* Getters & setters for transient attributes: */

	/* Functionalities: */

	/* Overrides: */

	@Override
	public void getExchangeRate(HeaderRQ headerRQ, Reqexchangerate exchangeRateRQ,
			Holder<HeaderRS> headerRS, Holder<Resexchangerate> exchangeRateRS) throws Fault {
		exchangeRate.getExchangeRatePTEndpoint1().getExchangeRate(headerRQ, exchangeRateRQ, headerRS, exchangeRateRS);
		
	}
}
