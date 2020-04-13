/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.transfer.core.service;

import javax.xml.ws.Holder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.asyst.bukopin.mobile.transfer.core.service.soap.CBSPostingHTTP;
import id.co.asyst.bukopin.mobile.transfer.core.service.soap.PostingPT;
import id.co.asyst.bukopin.mobile.transfer.model.soap.posting.Fault;
import id.co.asyst.bukopin.mobile.transfer.model.soap.posting.HeaderRQ;
import id.co.asyst.bukopin.mobile.transfer.model.soap.posting.HeaderRS;
import id.co.asyst.bukopin.mobile.transfer.model.soap.posting.Posting;

/**
 * Service Implementation for Managing Account Info
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$, Nov 15, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@Service
public class PostingService implements PostingPT {
	
	/* Constants: */

	/* Attributes: */
	@Autowired
	private CBSPostingHTTP cbsPosting;

	/* Transient Attributes: */

	/* Constructors: */

	/* Getters & setters for attributes: */

	/* Getters & setters for transient attributes: */

	/* Functionalities: */

	/* Overrides: */
	
	@Override
	public void postTransaction(HeaderRQ headerReq, Posting postingReq, Holder<HeaderRS> headerRes,
			Holder<Posting> postingRes) throws Fault {
		cbsPosting.getPostingPTEndpoint0().postTransaction(headerReq, postingReq, headerRes, postingRes);
		
	}
	
	
	
}
