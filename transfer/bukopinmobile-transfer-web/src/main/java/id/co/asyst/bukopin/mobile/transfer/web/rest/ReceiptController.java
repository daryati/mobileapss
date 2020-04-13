/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.transfer.web.rest;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import id.co.asyst.bukopin.mobile.transfer.core.service.ReceiptService;
import id.co.asyst.bukopin.mobile.transfer.model.ReceiptTransfer;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * 
 * 
 * @author Abi Nanel
 * @version $Revision$, Nov 21, 2019
 * @since 2.0
 */
@RestController
@RequestMapping("/receipt")
public class ReceiptController {
    /* Constants: */
    private final Logger log = LoggerFactory.getLogger(ReceiptController.class);

    /* Attributes: */
    @Autowired
    ReceiptService receiptService;

    /* Transient Attributes: */

    /* Constructors: */
    public ReceiptController() {
	// TO DO : nothing
    }

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * download Receipt PDF
     * 
     * @param request information for fill Jasper Report
     * @param response to send pdf file back to front end
     * @return HTTP STATUS 200 (OK)
     * @throws Exception
     */
    @PostMapping("/downloadPdf")
    @ResponseStatus(HttpStatus.OK)
    public void downloadReceiptTransfer(@Valid @RequestBody ReceiptTransfer request, HttpServletResponse response) 
	    throws Exception {
	log.debug("Data Receipt : "+request);
	JasperPrint jasperPrint = null;
	response.setContentType("application/x-download");
	response.setHeader("Content-Disposition", String.format("attachment; filename=\"receipt.pdf\""));
	
	OutputStream out = response.getOutputStream();
	jasperPrint = receiptService.generatePdf(request);
	JasperExportManager.exportReportToPdfStream(jasperPrint, out);
    }

    /* Overrides: */
}
