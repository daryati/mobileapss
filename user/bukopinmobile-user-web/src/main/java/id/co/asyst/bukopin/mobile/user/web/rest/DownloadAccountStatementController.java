/*
 * $Id$
 * 
 * Copyright (c) 2020 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.web.rest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JTextArea;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.co.asyst.bukopin.mobile.common.core.exception.ForbiddenAccessException;
import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonRequest;
import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.service.core.UserModuleService;
import id.co.asyst.bukopin.mobile.user.core.service.DownloadAccountStatementService;
import id.co.asyst.bukopin.mobile.user.core.service.UserService;
import id.co.asyst.bukopin.mobile.user.core.service.UserTokenService;
import id.co.asyst.bukopin.mobile.user.model.AccountStatementFieldTable;
import id.co.asyst.bukopin.mobile.user.model.AccountStatementReq;
import id.co.asyst.bukopin.mobile.user.model.DownloadAccountStatement;
import id.co.asyst.bukopin.mobile.user.model.InquiryTransactionReq;
import id.co.asyst.bukopin.mobile.user.model.InquiryTransactionRes;
import id.co.asyst.bukopin.mobile.user.model.entity.User;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyAccountOwnerRequest;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyAccountOwnerResponse;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyPhoneOwnerRequest;
import id.co.asyst.bukopin.mobile.user.model.payload.VerifyTokenOwnerResponse;
import id.co.asyst.foundation.service.connector.Services;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * REST Controller for download account statement
 * 
 * @author Ihsan Firman
 * @version $Revision$, Jan 15, 2020
 * @since 2.0
 */
@RestController
@RequestMapping("/accountMutation")
public class DownloadAccountStatementController {
    /* Constants: */
    private final Logger log = LoggerFactory.getLogger(DownloadAccountStatementController.class);

    /* Attributes: */
    @Autowired
    DownloadAccountStatementService accountMutationService;

    @Autowired
    AccountInfoController accountInfoController;

    @Autowired
    AccountController accountController;

    /**
     * User Token Service
     */
    @Autowired
    private UserTokenService userTokenService;

    @Autowired
    UserService userService;

    @Autowired
    MessageUtil messageUtil;

    @Autowired
    HttpServletRequest servletRequest;

    /* Transient Attributes: */

    /* Constructors: */
    public DownloadAccountStatementController(DownloadAccountStatementService accountMutationService) {
	this.accountMutationService = accountMutationService;
    }

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */
    /**
     * download account statement
     * 
     * @param request  include accountNumber, accountType, txLine as mandatory
     *                 dateFrom and dateTo as Optional
     * @param response file Statement.pdf to be saved
     * @throws Exception
     */
    @PostMapping("/downloadPdf")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse downloadPdf(@Valid @RequestBody CommonRequest<AccountStatementReq> request,
	    HttpServletResponse response) throws Exception {
	// used to testing out of Bukopin Intra Connection
	// public void downloadPdf(@Valid @RequestBody
	// CommonRequest<InquiryTransactionRes> request, HttpServletResponse response)
	// throws Exception {

	CommonResponse responseDownload = new CommonResponse(ResponseMessage.SUCCESS.getCode(),
		messageUtil.get("success", servletRequest.getLocale()));

	// Validate Token and Phone Owner
	CommonRequest<VerifyPhoneOwnerRequest> phoneReq = new CommonRequest<>();
	VerifyPhoneOwnerRequest phoneReqData = new VerifyPhoneOwnerRequest();
	phoneReqData.setUsername(request.getData().getUsername());
	phoneReqData.setToken(servletRequest.getHeader(HttpHeaders.AUTHORIZATION));
	phoneReqData.setPhoneIdentity(servletRequest.getHeader(BkpmConstants.HTTP_HEADER_DEVICE_ID));
	phoneReq.setData(phoneReqData);
	CommonResponse resPhone = Services.create(UserModuleService.class).verifyPhoneOwner(phoneReq).execute().body();
	if (!ResponseMessage.SUCCESS.getCode().equals(resPhone.getCode())) {
	    log.error("Validate Token and Phone owner error..");
	    return resPhone;
	}

	VerifyTokenOwnerResponse verifyToken = userTokenService.verifyTokenAndPhoneOwner(
		request.getData().getUsername(), servletRequest.getHeader(HttpHeaders.AUTHORIZATION),
		servletRequest.getHeader(BkpmConstants.HTTP_HEADER_DEVICE_ID));
	if (!verifyToken.isValid()) {
	    log.error("Token or device owner not valid");
	    throw new ForbiddenAccessException();
	}

	VerifyAccountOwnerRequest verifyAccountOwnerReqData = new VerifyAccountOwnerRequest();
	verifyAccountOwnerReqData.setAccountNo(request.getData().getAccountNumber());
	verifyAccountOwnerReqData.setUsername(request.getData().getUsername());

	CommonRequest<VerifyAccountOwnerRequest> verifyAccOwnerRequest = new CommonRequest<>();
	verifyAccOwnerRequest.setIdentity(request.getIdentity());
	verifyAccOwnerRequest.setData(verifyAccountOwnerReqData);

	CommonResponse verifyAccOwnerResponse = accountController.verifyAccountOwner(verifyAccOwnerRequest);

	if (!ResponseMessage.SUCCESS.getCode().equals(verifyAccOwnerResponse.getCode())) {
	    log.error("Error while verify account owner");
	    return verifyAccOwnerResponse;
	}
	ObjectMapper mapper = new ObjectMapper();
	VerifyAccountOwnerResponse verifyAccOwnRespObj = mapper.convertValue(verifyAccOwnerResponse.getData(),
		VerifyAccountOwnerResponse.class);

	if (!verifyAccOwnRespObj.isValid()) {
	    log.error("User and Account Info didn't match: " + request.getData().getAccountNumber());
	    responseDownload.setCode(ResponseMessage.DATA_NOT_MATCH.getCode());
	    responseDownload.setMessage(messageUtil.get("error.invalid.user.accountno", servletRequest.getLocale()));
	    return responseDownload;
	}

	log.debug("Create Pdf: " + request);
	JasperPrint jasperPrint = null;
	response.setContentType("application/x-download");
	response.setHeader("Content-Disposition", String.format("attachment; filename=\"Statement.pdf\""));

	SimpleDateFormat formatDate = new SimpleDateFormat("dd MMM yyyy");
	SimpleDateFormat yearToDate = new SimpleDateFormat("yyyy MMM dd");
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	SimpleDateFormat trueFormat = new SimpleDateFormat("yyyy MMM dd'\n'HH:mm");

	InquiryTransactionReq req = new InquiryTransactionReq();
	req.setAccountNumber(request.getData().getAccountNumber());
	req.setAccountType(request.getData().getAccountType());
	req.setDateFrom(request.getData().getDateFrom());
	req.setDateTo(request.getData().getDateTo());
	req.setTxLine(request.getData().getTxLine());

	CommonRequest<InquiryTransactionReq> rq = new CommonRequest<>();
	rq.setData(req);

	ArrayList<AccountStatementFieldTable> accountMutationFieldTables = new ArrayList<>();
	CommonResponse dataMutation = accountInfoController.getInquiryTransaction(rq);
	InquiryTransactionRes resp = mapper.convertValue(dataMutation.getData(), InquiryTransactionRes.class);
	log.debug("Response Inquiry : {} " + BkpmUtil.convertToJson(resp));
	if (resp == null) {
	    responseDownload.setCode(ResponseMessage.DATA_NOT_FOUND.getCode());
	    responseDownload.setMessage(messageUtil.get("error.data.not.found", servletRequest.getLocale()));
	    return responseDownload;
	}
	DownloadAccountStatement mutationModel = new DownloadAccountStatement();
	NumberFormat id = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

	for (int index = 0; index < resp.getTransactiondetails().size(); index++) {
	    AccountStatementFieldTable accountMutationFieldTable = new AccountStatementFieldTable();
	    Date date = formatter.parse(resp.getTransactiondetails().get(index).getPostingDate());
	    String newDate = trueFormat.format(date);
	    accountMutationFieldTable.setPostingDate(newDate);
	    String desc = resp.getTransactiondetails().get(index).getDescription();
	    accountMutationFieldTable.setDescription(desc);
	    String reff = resp.getTransactiondetails().get(index).getReffNumber();
	    accountMutationFieldTable.setReference(reff);

	    if (String.valueOf(resp.getTransactiondetails().get(index).getAmountCredit()).equalsIgnoreCase("0.0")) {
		String amount = id.format(resp.getTransactiondetails().get(index).getAmountDebit()).replace("", "");
		accountMutationFieldTable.setAmount("- " + amount);
	    } else {
		String amount = id.format(resp.getTransactiondetails().get(index).getAmountCredit()).replace("", "");
		accountMutationFieldTable.setAmount("+ " + amount);
	    }
	    accountMutationFieldTables.add(accountMutationFieldTable);
	}

	mutationModel.setvAccountNo(resp.getAccountNumber());
	mutationModel.setvOwner(resp.getName());
	mutationModel.setvCurrency("IDR (Rupiah)");
	if (request.getData().getDateFrom() == null && request.getData().getDateTo() == null) {
	    Date date = yearToDate
		    .parse(accountMutationFieldTables.get(accountMutationFieldTables.size() - 1).getPostingDate());
	    Date dateTo = yearToDate.parse(accountMutationFieldTables.get(0).getPostingDate());
	    mutationModel.setvFrom(formatDate.format(date));
	    mutationModel.setvTo(formatDate.format(dateTo));
	} else {
	    mutationModel.setvFrom(formatDate.format(request.getData().getDateFrom()));
	    mutationModel.setvTo(formatDate.format(request.getData().getDateTo()));
	}
	/*
	 * mutationModel.setvFrom(new Date().toString()); mutationModel.setvTo(new
	 * Date().toString());
	 */
	mutationModel.setAccountMutationFieldTable(accountMutationFieldTables);

	OutputStream out = response.getOutputStream();
	jasperPrint = accountMutationService.generatePdf(mutationModel);
	JasperExportManager.exportReportToPdfStream(jasperPrint, out);

	return responseDownload;
    }

    /* Overrides: */}
