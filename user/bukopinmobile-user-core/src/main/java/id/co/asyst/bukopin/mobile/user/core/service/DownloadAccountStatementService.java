/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.core.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.user.model.AccountStatementFieldTable;
import id.co.asyst.bukopin.mobile.user.model.DownloadAccountStatement;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Service to generate pdf for Statement.pdf
 * 
 * @author Ihsan Firman
 * @version $Revision$, Jan 15, 2020
 * @since 2.0
 */
@Service
public class DownloadAccountStatementService {
    /* Constants: */
    
    /* Attributes: */
    @Autowired
    MessageUtil messageUtil;
    @Autowired
    HttpServletRequest servletRequest;
    
    private final Logger log = LoggerFactory.getLogger(DownloadAccountStatementService.class);
    
    private static final String LINK_BUKOPIN = "www.bukopin.co.id";
    private static final String HALO_BUKOPIN = "Halo KB Bukopin 14005";
    
    //DEV repository
    //private static final String TEMPLATE_PATH = "/home/dev/apps/spring-boot/bkpm-user/jasper-reports/Account_Statement_Template.jrxml";
    //private static final String IMAGE_PATH = "/home/dev/apps/spring-boot/bkpm-user/";
    
    //LOCAL repository
    /*private final String TEMPLATE_PATH = "D:/Kantor/Asyst/Project/8 Bukopin/bukopin/Asset/bkpm-user/jasper-reports/Account_Statement_Template.jrxml";*/
    
    Resource IMAGE_PATH = new ClassPathResource("/report/MuRek_Bukopin.png");   
    Resource TEMPLATE_LOC = new ClassPathResource("/report/Account_Statement_Template.jrxml");

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * Generate file pdf
     * 
     * @param model
     * @return the created pdf file
     * @throws JRException
     * @throws IOException 
     */
    public JasperPrint generatePdf(DownloadAccountStatement model) throws JRException, IOException {
	
	InputStream path = TEMPLATE_LOC.getInputStream();
	
	JasperReport report = JasperCompileManager.compileReport(path);
	Map<String, Object> parameters = mutationParameters(model);
	JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
	log.debug("Statement.pdf successfully created...");
	
	return print;
    }
    
    /**
     * put parameters that already defined from Jasper
     * @param request
     * @return Parameters that filled
     * @throws IOException 
     */
    private Map<String, Object> mutationParameters(DownloadAccountStatement request) throws IOException{
	final Map<String, Object> parameters = new HashMap<>();
	log.debug("Put parameters for " + request);
	
	BufferedImage image = ImageIO.read(getClass().getResource("/report/MuRek_Bukopin.png"));
	
	SimpleDateFormat formatDate = new SimpleDateFormat("dd MMM yyyy");
	//Convert List to JRBeanCollectionDataSource
	JRBeanCollectionDataSource itemTable = new JRBeanCollectionDataSource(request.getAccountMutationFieldTable());
	//Data Label
	parameters.put("pemilik_rekening", messageUtil.get("account.owner", servletRequest.getLocale()));
	parameters.put("nomor_rekening", messageUtil.get("account.number", servletRequest.getLocale()));
	parameters.put("mata_uang", messageUtil.get("currency", servletRequest.getLocale()));
	parameters.put("rentang_waktu", messageUtil.get("range", servletRequest.getLocale()));
	parameters.put("tanggal_cetak", messageUtil.get("issued.date", servletRequest.getLocale()));
	parameters.put("history_transaksi", messageUtil.get("transaction.history", servletRequest.getLocale()));
	parameters.put("footer_description", messageUtil.get("footer.pdf", servletRequest.getLocale()));
	parameters.put("page", messageUtil.get("page", servletRequest.getLocale()));
	parameters.put("from", messageUtil.get("from", servletRequest.getLocale()));
	//Table Head
	parameters.put("head_date", messageUtil.get("head.date", servletRequest.getLocale()));
	parameters.put("head_rincianTransaksi", messageUtil.get("headDesc", servletRequest.getLocale()));
	parameters.put("head_reference", messageUtil.get("headReff", servletRequest.getLocale()));
	parameters.put("head_debKre", messageUtil.get("headAmount", servletRequest.getLocale()));
	//Data Value
	parameters.put("value_pemilik", request.getvOwner());
	parameters.put("value_nomorRekening", request.getvAccountNo());
	parameters.put("value_mataUang", request.getvCurrency());
	parameters.put("value_from", request.getvFrom());
	parameters.put("value_to", request.getvTo());
	parameters.put("value_tanggalCetak", formatDate.format(new Date()));
	parameters.put("linkBukopin", LINK_BUKOPIN);
	parameters.put("haloBukopin", HALO_BUKOPIN);
	parameters.put("imgpath", image);
	//Tabel Value
	parameters.put("value_table", itemTable);
	
	log.debug("Parameters already mapped....");

	return parameters;
    }

    /* Overrides: */}
