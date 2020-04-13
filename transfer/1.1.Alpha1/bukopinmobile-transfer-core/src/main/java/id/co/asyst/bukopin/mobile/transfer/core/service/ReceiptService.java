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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import id.co.asyst.bukopin.mobile.transfer.model.ReceiptTransfer;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;


/**
 * 
 * 
 * @author Abi Nanel
 * @version $Revision$, Nov 21, 2019
 * @since 2.0
 */
@Service
public class ReceiptService {
    /* Constants: */
    private final Logger log = LoggerFactory.getLogger(ReceiptService.class);
    private final String receipt_template_path = "/home/dev/apps/spring-boot/bkpm-transfer/jasper-reports/transfer_receipt.jrxml";

    /* Attributes: */

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * generate Receipt PDF
     * *Compile Jasper Report .jrxml
     * *Getting parameters for the report
     * *Fill report with parameters and data source
     * 
     * @param receipt information for fill Jasper Report
     * @return jasper print that has been filled
     * @throws Exception
     */
    public JasperPrint generatePdf(ReceiptTransfer receipt) throws Exception {
	JasperReport report = JasperCompileManager.compileReport(receipt_template_path);
	Map<String, Object> parameters = receiptParameters(receipt);
	JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
	
	return print;
    }
    
    /**
     * set parameters for report
     * 
     * @param receipt information for fill Jasper Report
     * @return parameters for report
     */
    private Map<String, Object> receiptParameters(ReceiptTransfer receipt) {
	final Map<String, Object> parameters = new HashMap<>();
	SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
	
	parameters.put("time",formatter.format(new Date()));
	parameters.put("referensi", receipt.getReferensi());
	parameters.put("from_account_name", receipt.getFromAccountName());
	parameters.put("from_saving_name", receipt.getFromSavingName());
	parameters.put("from_account_no", receipt.getFromAccountNo());
	parameters.put("to_account_name", receipt.getToAccountName());
	parameters.put("to_bank_name", receipt.getToBankName());
	parameters.put("to_account_no", receipt.getToAccountNo());
	parameters.put("amount", "Rp"+receipt.getAmount());
	parameters.put("charge", "Rp"+receipt.getCharge());
	parameters.put("total", "Rp"+receipt.getTotal());
	
	return parameters;
    }

    /* Overrides: */
}
