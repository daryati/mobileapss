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

import id.co.asyst.bukopin.mobile.master.core.service.soap.CBSProductInfo;
import id.co.asyst.bukopin.mobile.master.core.service.soap.ProductInfoPT;
import id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate.Fault;
import id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate.HeaderRQ;
import id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate.HeaderRS;
import id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate.Reqproductinformation;
import id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate.Resproductinformation;

/**
 * Service Connector to Account Info SOAP service.
 * 
 * @author Kartika Dwi H
 * @version $Revision$, Feb 10, 2020
 * @since 1.1.Alpha1
 */
@Service
public class ProductInfoPTService implements ProductInfoPT {
	
	/* Constants: */

	/* Attributes: */
	
	@Autowired
	private CBSProductInfo productInfo;

	/* Transient Attributes: */

	/* Constructors: */

	/* Getters & setters for attributes: */

	/* Getters & setters for transient attributes: */

	/* Functionalities: */

	/* Overrides: */


	@Override
	public void getProductInfo(HeaderRQ headerRQ,
			Reqproductinformation productInfoRQ, Holder<HeaderRS> headerRS,
			Holder<Resproductinformation> productInfoRS) throws Fault {
		productInfo.getProductInfoPTEndpoint1().getProductInfo(
				headerRQ, productInfoRQ, headerRS, productInfoRS);
		
	}
}
