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

import id.co.asyst.bukopin.mobile.user.core.service.soap.GetInquiryCIFPortType;
import id.co.asyst.bukopin.mobile.user.core.service.soap.ServiceInquiryCIF;
import id.co.asyst.bukopin.mobile.user.model.soap.cif.Fault;
import id.co.asyst.bukopin.mobile.user.model.soap.cif.GetInquiryCIFReqType;
import id.co.asyst.bukopin.mobile.user.model.soap.cif.GetInquiryCIFResType;
import id.co.asyst.bukopin.mobile.user.model.soap.cif.HeaderRQ;
import id.co.asyst.bukopin.mobile.user.model.soap.cif.HeaderRS;

/**
 * Service Implementation for Managing CIF
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Oct 27, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@Service
public class CIFService implements GetInquiryCIFPortType {

	/* Constants: */

	/* Attributes: */
	@Autowired
	private ServiceInquiryCIF cifClient;

	/* Transient Attributes: */

	/* Constructors: */

	/* Getters & setters for attributes: */

	/* Getters & setters for transient attributes: */

	/* Functionalities: */

	/* Overrides: */
	/* (non-Javadoc)
	 * @see id.co.asyst.bukopin.mobile.user.core.service.soap.GetInquiryCIFPortType#getInquiryCIF(id.co.asyst.bukopin.mobile.user.model.soap.cif.HeaderRQ, id.co.asyst.bukopin.mobile.user.model.soap.cif.GetInquiryCIFReqType, javax.xml.ws.Holder, javax.xml.ws.Holder)
	 */
	@Override
	public void getInquiryCIF(HeaderRQ headerRQ, GetInquiryCIFReqType inquiryCIFRQ, Holder<HeaderRS> headerRS,
			Holder<GetInquiryCIFResType> inquiryCIFRS) throws Fault {
		cifClient.getGetInquiryCIFPortTypeEndpoint1().getInquiryCIF(
				headerRQ, inquiryCIFRQ, headerRS, inquiryCIFRS);
	}
}
