package id.co.asyst.bukopin.mobile.transfer.core.service;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import id.co.asyst.bukopin.mobile.common.core.util.BkpmUtil;
import id.co.asyst.bukopin.mobile.common.core.util.ConnectionUtils;
import id.co.asyst.bukopin.mobile.common.core.util.CryptoUtil;
import id.co.asyst.bukopin.mobile.common.core.util.JsonVariable;
import id.co.asyst.bukopin.mobile.common.core.util.MessageUtil;
import id.co.asyst.bukopin.mobile.common.model.BkpmConstants;
import id.co.asyst.bukopin.mobile.common.model.ResponseMessage;
import id.co.asyst.bukopin.mobile.common.model.payload.Identity;
import id.co.asyst.bukopin.mobile.transfer.core.config.GetConfiguration;
import id.co.asyst.bukopin.mobile.transfer.core.service.soap.PostingPT;
import id.co.asyst.bukopin.mobile.transfer.core.util.AuthUtil;
import id.co.asyst.bukopin.mobile.transfer.model.InquiryAccountReq;
import id.co.asyst.bukopin.mobile.transfer.model.InquiryAccountRes;
import id.co.asyst.bukopin.mobile.transfer.model.PostingElementRes;
import id.co.asyst.bukopin.mobile.transfer.model.PostingReq;
import id.co.asyst.bukopin.mobile.transfer.model.PostingRes;
import id.co.asyst.bukopin.mobile.transfer.model.PostingToReq;
import id.co.asyst.bukopin.mobile.transfer.model.entity.ReceiverInfo;
import id.co.asyst.bukopin.mobile.transfer.model.entity.Bank;
import id.co.asyst.bukopin.mobile.transfer.model.entity.FundTransfer;
import id.co.asyst.bukopin.mobile.transfer.model.soap.posting.Fault;
import id.co.asyst.bukopin.mobile.transfer.model.soap.posting.HeaderRQ;
import id.co.asyst.bukopin.mobile.transfer.model.soap.posting.HeaderRQ.Credentials;
import id.co.asyst.bukopin.mobile.transfer.model.soap.posting.HeaderRS;
import id.co.asyst.bukopin.mobile.transfer.model.soap.posting.Posting;
import id.co.asyst.bukopin.mobile.transfer.model.soap.posting.iso8583.Elements;
import id.co.asyst.bukopin.mobile.transfer.model.soap.posting.iso8583.ISOMessages;
import id.co.asyst.bukopin.mobile.user.model.entity.User;

/**
 * Service Implementation for managing overbook.<br/>
 * 
 * @author Kartika Dwi Handini
 * @version $Revision$, (Created on Nov 15, 2019)
 * @since 1.0.Alpha1
 */
@Service("overbookService")
@Transactional
public class OverbookService {

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(OverbookService.class);
    
    /**
     * Separator: Pipe
     */
    private final static String SEPARATOR_PIPE = "|";
    
    /**
     * Note to insert in fund transfer database
     */
    private final static String NOTE_ID = "TF ke ";
    private final static String NOTE_EN = "TF to ";

    /**
     * Port Type (SOAP Manager).
     */
    @Autowired
    private PostingPT portType;
    
    @Autowired
    private BankService bankService;
    
    @Autowired
    private ReceiverInfoService receiverInfoService;
    
    @Autowired
    private FundTransferService fundTransferService;
    
    @Autowired
    private GetConfiguration config;
    
    @Autowired
    private Environment env;
    
    @Autowired
    private HttpServletRequest servletRequest;
    
    @Autowired
    private MessageUtil messageUtil;
    
    @Autowired
    private TransferService transferService;
    
    
    private DateFormat dateFormat;
    
    /**
     * post transaction via API
     * @param postingReq, identity
     * @return Posting Res
     */
    @SuppressWarnings("unchecked")
	public PostingRes postTransactionViaAPI (PostingReq request, Identity identity) {
    	PostingRes result = new PostingRes();
    	String url =config.getConfigValue("URL_BUKOPIN_XLINK_POSTINGFT");
    	String method ="";
    			
    	JSONObject dataJson = new JSONObject();
		//dataJson.put(JsonVariable.LANG, "EN");
		dataJson.put(JsonVariable.CHANNEL_ID, BkpmConstants.TRANSFER_CHANNEL_ID);
		
		Date date = new Date();
		dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String transDateTime = dateFormat.format(date);
        //log.debug("transDateTime "+transDateTime);
		dataJson.put(JsonVariable.TRANSMISSION_DATE_TIME, transDateTime);
		
		dataJson.put(JsonVariable.STAN, request.getStan());		
		dataJson.put(JsonVariable.SENDER_NAME, request.getPostingFrom().getAccountName().toUpperCase());
		dataJson.put(JsonVariable.SENDER_ACCOUNT, request.getPostingFrom().getAccountNumber());
		dataJson.put(JsonVariable.BENEFICIARY_NAME, request.getPostingTo().getAccountName());
		dataJson.put(JsonVariable.BENEFICIARY_ACCOUNT, request.getPostingTo().getAccountNumber());
		dataJson.put(JsonVariable.BENEFICIARY_BANK_CODE, request.getPostingTo().getBankCode().toString());
		dataJson.put(JsonVariable.CURRENCY_CODE, BkpmConstants.CURRENCY_CODE_RUPIAH);
		dataJson.put(JsonVariable.AMOUNT, request.getAmount().toString());
		dataJson.put(JsonVariable.REFERENCE, "");
		dataJson.put(JsonVariable.CONFIRMATION_CODE, request.getConfirmationCode());
    	
		Map<String, String> header = new HashMap<String, String>();
		header.put(BkpmConstants.HTTP_HEADER_CONTENTTYPE, BkpmConstants.HTTP_HEADER_CONTENT_JSON);
		header.put(BkpmConstants.HTTP_HEADER_ACCEPT, BkpmConstants.HTTP_HEADER_CONTENT_JSON);
		// only for prod
		String apiKey = env.getProperty("config.header.overbook.api-key");
		if(StringUtils.hasLength(apiKey)) {
		    log.debug("overbook api key set");
		    header.put(BkpmConstants.BKP_API_KEY_HEADER,
			    env.getProperty("config.header.overbook.api-key"));
		}
    	
		//log.debug("Request "+dataJson.toString());
		try {
			String res = ConnectionUtils.urlAccess(url, dataJson.toString(),
						header).toString();
			JSONParser parser = new JSONParser();
			if(res != null){
				JSONObject responseJson = (JSONObject) parser.parse(res);
				//log.debug("response "+responseJson);
				
				JSONObject statusObj = (JSONObject) responseJson.get("Status");
				if(null == statusObj) {
					statusObj = (JSONObject) responseJson.get("status");
				}
				
				String statusCode = String.valueOf(statusObj.get("statusCode"));
				String statusDesc = (String) statusObj.get("statusDesc");
				
				result.setStatusCode(statusCode);
				result.setStatusDesc(statusDesc);
				
				if("200".equals(statusCode)) {
					JSONObject dataObj = (JSONObject) responseJson.get(JsonVariable.DATA);					
					if("00".equals((String)dataObj.get(JsonVariable.RESPONSE_CODE))) {
						result.setAmount(request.getAmount());
						result.setMessage(request.getMessage());
						result.setPostingFrom(request.getPostingFrom());
						result.setPostingTo(request.getPostingTo());
						result.setAuthID((String) dataObj.get(JsonVariable.AUTH_ID));
						result.setBankReference((String) dataObj.get(JsonVariable.BANK_REFFERENCE));
						result.setStan((String) dataObj.get(JsonVariable.STAN));
						result.setResponseCode((String)dataObj.get(JsonVariable.RESPONSE_CODE));
						result.setResponseDescription((String)dataObj.get(JsonVariable.RESPONCE_DESCRIPTION));
						result.setTransmissionDateTime((String)dataObj.get(JsonVariable.TRANSMISSION_DATE_TIME));
						
						
						//save data to DB
						FundTransfer tf = new FundTransfer();
						tf.setAccountNumber(request.getPostingFrom().getAccountNumber());
						
						String adminFee="";
						String transferType;
						if(BkpmConstants.BUKOPIN_BANK_CODE.equals(request.getPostingTo().getBankCode())) {
							//ntr d caching
							adminFee =config.getConfigValue("OVERBOOK_ADMIN_FEE");
							method = messageUtil.get("overbook", servletRequest.getLocale());
							transferType= BkpmConstants.OVERBOOK;
							
						    tf.setAdminFee(new BigDecimal(adminFee));
						    tf.setMethod(BkpmConstants.OVERBOOK);
						    
						    result.setAdminFee(new BigDecimal(adminFee));
						    result.setMethod(messageUtil.get("overbook", servletRequest.getLocale()));
						    result.setTransferType(BkpmConstants.OVERBOOK);
						} else {
							//ntr d caching
							adminFee =config.getConfigValue("FUND_TRANSFER_ADMIN_FEE");
							method = messageUtil.get("fund.transfer", servletRequest.getLocale());
							transferType = BkpmConstants.FUND_TRANSFER;
							
							tf.setAdminFee(new BigDecimal(adminFee));
						    tf.setMethod(BkpmConstants.FUND_TRANSFER);
						    
						    result.setAdminFee(new BigDecimal(adminFee));
						    result.setMethod(messageUtil.get("fund.transfer", servletRequest.getLocale()));
						    result.setTransferType(BkpmConstants.FUND_TRANSFER);
						    
						}
						
						tf.setAmount(request.getAmount());
						tf.setCreatedOn(LocalDateTime.now());
						
						
						// get user data 
					    String urlUser = config.getConfigValue("USER_BASE_URL")+config.getConfigValue("URL_FINDUSER_BY_USERNAME");
					    
					    //use this if run user in local PC
					    //String urlUser ="http://localhost:8080/bukopinmobile-user/account/findUserByUsername";
					    
					    
					    dataJson = new JSONObject();
					    
					    JSONObject identityObj = new JSONObject();
					    Date now = new Date();
					    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					    String nowSt = sdfDate.format(now);
					    
					    identityObj.put(JsonVariable.REQ_TIME, identity.getReqTime());
					    identityObj.put(JsonVariable.PLATFORM, identity.getPlatform());
					    identityObj.put(JsonVariable.USER_AGENT, identity.getUserAgent());
					    identityObj.put(JsonVariable.SECRET_CODE, identity.getSecretCode());
					    identityObj.put(JsonVariable.TOKEN, identity.getToken());
					    dataJson.put(JsonVariable.IDENTITY, identityObj);
					    
					    JSONObject parameter = new JSONObject();
					    parameter.put(JsonVariable.USERNAME, request.getPostingFrom().getUsername());
					    dataJson.put(JsonVariable.PARAMETER, parameter);
					    
					    
					    header = new HashMap<String, String>();
					    header.put(BkpmConstants.HTTP_HEADER_CONTENTTYPE, BkpmConstants.HTTP_HEADER_CONTENT_JSON);
					    header.put(BkpmConstants.HTTP_HEADER_ACCEPT, BkpmConstants.HTTP_HEADER_CONTENT_JSON);
					    
					    res = ConnectionUtils.urlAccess(urlUser, dataJson.toString(),
								header).toString();
					    parser = new JSONParser();
					    User user = new User();
						if(res != null){
							responseJson = (JSONObject) parser.parse(res);
							//log.debug("response ini yaaa "+responseJson);
							
							JSONObject userObj = new JSONObject();
							userObj = (JSONObject) responseJson.get(JsonVariable.RESULT);
							user.setId((Long) userObj.get(JsonVariable.ID));
							user.setCreatedBy((String) userObj.get(JsonVariable.CREATED_BY));
							
							dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSz");
							String dt = (String)userObj.get(JsonVariable.CREATED_DATE);
							
							user.setCreatedDate(dateFormat.parse(dt));
							user.setEmail((String) userObj.get(JsonVariable.EMAIL));
							user.setFirstName((String) userObj.get(JsonVariable.FIRSTNAME));
							user.setLastName((String) userObj.get(JsonVariable.LASTNAME));
							user.setMiddleName((String) userObj.get(JsonVariable.MIDDLENAME));
							user.setMobilePhone((String) userObj.get(JsonVariable.MOBILEPHONE));
							user.setUpdatedBy((String) userObj.get(JsonVariable.UPDATED_BY));
							user.setUpdatedDate((Date) userObj.get(JsonVariable.UPDATED_DATE));
							user.setUsername((String) userObj.get(JsonVariable.USERNAME));
							user.setCifNumber((String) userObj.get(JsonVariable.CIF_NUMBER));
							
						} else {
							// user not found
						}
					    
					    
						//User usr = userService.findUserByUsername(request.getPostingFrom().getUsername());
						tf.setUsername(user);
						
						tf.setMessage(request.getMessage());
						tf.setReferenceCode(result.getBankReference());
						tf.setStatus(BkpmConstants.STATUS_SUCCESS);
						
						// find existing receiver info
						ReceiverInfo receiver = receiverInfoService.findAccountByUsernameAndAccountNumber(
							user, request.getPostingTo().getAccountNumber());
						
						
						if(null!=receiver) {
							boolean oldSave = receiver.isSave();
							
							if(oldSave == true) {
								receiver.setSave(oldSave);
							} else {
								receiver.setSave(request.getPostingTo().isSave());
							}
							
						    //exist
						    receiver.setAlias(request.getPostingTo().getAlias());
						    receiver.setCounter(receiver.getCounter()+1);   
						    //receiver.setSave(request.getPostingTo().isSave());		
						    
						    // send Email receipt saved, isSave = true?
						    if(request.getPostingTo().isSave() == true && oldSave!=true) {
						    	log.debug("send email receipt to "+user.getEmail());
							    transferService.sendEmailReceiptSaved(receiver, user, servletRequest.getLocale(), transferType);
							    
						    } else {
						    	log.debug("Not send email receipt to "+user.getEmail()+" issave = false");
							   
						    }
						} else {
						    // // if receiver is save true
							log.debug("SAVE RECEIVER. . .");
						    receiver = new ReceiverInfo();
						    receiver.setAccountNumber(request.getPostingTo().getAccountNumber());
						    receiver.setAccountName(request.getPostingTo().getAccountName());
						    receiver.setAlias(request.getPostingTo().getAlias());
						    receiver.setCounter(1);
						    receiver.setSave(request.getPostingTo().isSave());
						    receiver.setUsername(user);						    
						    Bank bank = bankService.findBankByBankCode(request.getPostingTo().getBankCode());
						    receiver.setBank(bank);
						    
						    // send Email receipt saved, issave=true?
						    
						    // send Email receipt saved, isSave = true?
						    if(request.getPostingTo().isSave() == true) {
						    	log.debug("send email receipt to "+user.getEmail());
							    transferService.sendEmailReceiptSaved(receiver, user, servletRequest.getLocale(), transferType);
							    
						    } else {
						    	log.debug("Not send email receipt to "+user.getEmail()+" issave = false");
							   
						    }
						    
						    
						}
						// set receiver for note
						String noteReceiver = receiver.getAccountName().concat(" "+receiver.getAccountNumber());
						tf.setNoteId(NOTE_ID.concat(noteReceiver));
						tf.setNoteEn(NOTE_EN.concat(noteReceiver));
						
						tf.setReceiverInfo(receiver);
						fundTransferService.saveFundTransfer(tf);
					} else {
						// response code error 
						log.debug("error code posting "+(String)dataObj.get(JsonVariable.RESPONSE_CODE)+" - - "+(String)dataObj.get(JsonVariable.RESPONCE_DESCRIPTION));
						result.setResponseCode((String)dataObj.get(JsonVariable.RESPONSE_CODE));
						result.setResponseDescription((String)dataObj.get(JsonVariable.RESPONCE_DESCRIPTION));
						result.setStatusCode((String)dataObj.get(JsonVariable.RESPONSE_CODE));
						result.setStatusDesc((String)dataObj.get(JsonVariable.RESPONCE_DESCRIPTION));
					}
					
				} else {
					result.setStatusCode(statusCode);
					result.setStatusDesc(statusDesc);
					log.debug("error "+statusCode+" - - "+statusDesc);
				}
			}			
		} catch (Exception e) {
			log.error(e.toString(), e);
		}
    	
    	return result;
    }
    
    @SuppressWarnings("unchecked")
	public InquiryAccountRes inquiryAmountViaAPI (InquiryAccountReq request) {
    	InquiryAccountRes result = new InquiryAccountRes();
    	
    	//get url from masterdata config 	
    	String url =config.getConfigValue("URL_BUKOPIN_XLINK_INQUIRYFT");

		JSONObject dataJson = new JSONObject();
		
		dataJson.put(JsonVariable.CHANNEL_ID, BkpmConstants.TRANSFER_CHANNEL_ID);
		
		
		Date dt = new Date();
		dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		dateFormat.format(dt.getTime());		
		//System.out.println("tanggal nya "+dateFormat.format(cal.getTime()));
       
        String transDateTime = dateFormat.format(dt.getTime());	
		dataJson.put(JsonVariable.TRANSMISSION_DATE_TIME, transDateTime);
		
		// generate
		String stan = "";
		try {
		    stan = AuthUtil.generateOTP(6);
		} catch (NoSuchAlgorithmException e1) {
		    e1.printStackTrace();
		}
		dataJson.put(JsonVariable.STAN, stan); // generate
		dataJson.put(JsonVariable.SENDER_ACCOUNT, request.getPostingFrom().getAccountNumber());
		dataJson.put(JsonVariable.BENEFICIARY_ACCOUNT, request.getPostingTo().getAccountNumber());
		dataJson.put(JsonVariable.BENEFICIARY_BANK_CODE, request.getPostingTo().getBankCode().toString());
		dataJson.put(JsonVariable.CURRENCY_CODE, BkpmConstants.CURRENCY_CODE_RUPIAH);
		dataJson.put(JsonVariable.AMOUNT, request.getAmount().toString());
		dataJson.put(JsonVariable.REFERENCE, "");
		
		Map<String, String> header = new HashMap<String, String>();
		header.put(BkpmConstants.HTTP_HEADER_CONTENTTYPE, BkpmConstants.HTTP_HEADER_CONTENT_JSON);
		header.put(BkpmConstants.HTTP_HEADER_ACCEPT, BkpmConstants.HTTP_HEADER_CONTENT_JSON);
		// only for prod
		String apiKey = env.getProperty("config.header.overbook.api-key");
		if(StringUtils.hasLength(apiKey)) {
		    log.debug("overbook api key set");
		    header.put(BkpmConstants.BKP_API_KEY_HEADER, 
				env.getProperty("config.header.overbook.api-key"));
		}
		
		//log.debug("Request "+dataJson.toString());
		try {
			String res = ConnectionUtils.urlAccess(url, dataJson.toString(),
						header).toString();
			JSONParser parser = new JSONParser();
			if(res != null){
				// to upper case sender account name
				JSONObject responseJson = (JSONObject) parser.parse(res);
				//log.debug("response "+responseJson);
				
				JSONObject statusObj = (JSONObject) responseJson.get("Status"); // kenapa status ny beda gede kecil				
				if(null == statusObj) {
					statusObj = (JSONObject) responseJson.get("status");
				}
				
				String statusCode = String.valueOf(statusObj.get("statusCode")); // handle statusCode in Long
				String statusDesc = (String) statusObj.get("statusDesc");
				
				result.setStatusCode(statusCode);
				result.setStatusDesc(statusDesc);
				
				if("200".equals(statusCode)) {
					JSONObject dataObj = (JSONObject) responseJson.get(JsonVariable.DATA);					
					if("00".equals(dataObj.get("responseCode"))) {
						result.setAmount(request.getAmount());
						result.setMessage(request.getMessage());
						request.getPostingFrom().setAccountName(request.getPostingFrom().getAccountName().toUpperCase());
						result.setPostingFrom(request.getPostingFrom());
						
						PostingToReq postingTo = request.getPostingTo();
						postingTo.setAccountName((String)dataObj.get(JsonVariable.BENEFICIARY_NAME));
						result.setPostingTo(postingTo);						
						result.setConfirmationCode((String) dataObj.get(JsonVariable.CONFIRMATION_CODE));
						result.setStan(stan);						
						//result.setAdminFee(new BigDecimal((String)dataObj.get(JsonVariable.AMOUNT_FEE))); ambil dr db
						String adminFee ="";
						if(BkpmConstants.BUKOPIN_BANK_CODE.equals(request.getPostingTo().getBankCode())) {
							//ntr d caching
							adminFee =config.getConfigValue("OVERBOOK_ADMIN_FEE");
							result.setAdminFee(new BigDecimal(adminFee));
						    result.setMethod(messageUtil.get("overbook", servletRequest.getLocale()));
						} else {
							//ntr d caching
							adminFee =config.getConfigValue("FUND_TRANSFER_ADMIN_FEE");
							result.setAdminFee(new BigDecimal(adminFee));
						    result.setMethod(messageUtil.get("fund.transfer", servletRequest.getLocale()));
						}
						
						// Reference Code
						// from|to|confirmationcode|merchant
						String ref = request.getPostingFrom().getAccountNumber() + SEPARATOR_PIPE
							    + request.getPostingTo().getAccountNumber() + SEPARATOR_PIPE
							    + result.getConfirmationCode() 
							    + SEPARATOR_PIPE + BkpmConstants.MERCHANT_ID;
						String referenceCode = CryptoUtil.encryptAES(ref);
						result.setReferenceCode(referenceCode);
					} else {
						result.setStatusCode((String) dataObj.get(JsonVariable.RESPONSE_CODE));
						result.setStatusDesc((String) dataObj.get(JsonVariable.RESPONCE_DESCRIPTION));
					}
					
					
				} else {
					log.debug("error inquiry transfer "+statusCode+" - - "+statusDesc);
				}
			}			
		} catch (Exception e) {
			log.error(e.toString(), e);
		}
    	return result;
    }

    public PostingElementRes postTransaction (PostingReq request) {
    	// get transaction ID 
    	String txId = BkpmUtil.generateTrxId();
    	
        log.debug("posting transaction with trxId "+txId);
        
        
        //set request
        HeaderRQ headerRQ = new HeaderRQ();
        headerRQ.setClientTxnID(txId);
        
        Credentials credential= new Credentials();
        credential.setClientID(BkpmConstants.CLIENT_ID);        
        headerRQ.setCredentials(credential);
        
		try {
			GregorianCalendar gregCal = new GregorianCalendar();
			gregCal.setTime(new Date());
			XMLGregorianCalendar xmlGregCal;
			xmlGregCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregCal);
			xmlGregCal.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
	        headerRQ.setReqDateTime(xmlGregCal);
		} catch (DatatypeConfigurationException e1) {
			e1.printStackTrace();
		}
		
        Posting posting = new Posting();        
        posting.setType("TRX"); // ini nanti mau di bikin enum ap constant?
        ISOMessages msg = new ISOMessages();
        msg.setMTI("0200");
        
	        /*Elements elm = new Elements();        
	        elm.setElement03("491010");
	        elm.setElement04("255000");
	        elm.setElement06("55555555");
	        elm.setElement07("1122334455");
	        elm.setElement10("");
	        elm.setElement11("777777");
	        elm.setElement12("113507");
	        elm.setElement13("1014");
	        elm.setElement18("6014");
	        elm.setElement28("");
	        elm.setElement33("441");
	        elm.setElement37("RR5SAV44L201");
	        elm.setElement41("TLR420202");
	        elm.setElement48("Andri Risya Kharisma - transfer debit kredit   - saving acc tutup - checking acc ");
	        elm.setElement49("360");
	        elm.setElement51("360");
	        elm.setElement62("000000000599999999991234567890123456789011223344556677889900TLR420202      000000FTdariT24           2223355555");
	        elm.setElement100("441");
	        elm.setElement102("4217900020");
	        elm.setElement103("4202900099");*/
        
        
        Elements elm = new Elements();        
        elm.setElement03("49"+request.getPostingFrom().getAccountType()+request.getPostingFrom().getAccountType());
        elm.setElement04(request.getAmount().toString()); // amount
        elm.setElement06("00000000"); //card holder billing amount
        
        LocalDateTime date = LocalDateTime.now();
        String transDateTime = String.valueOf(date.getMonthValue())+
        		String.valueOf(date.getDayOfMonth()) + String.valueOf(date.getHour())+
        		String.valueOf(date.getMinute())+String.valueOf(date.getSecond());
        elm.setElement07(transDateTime); //month,date,hours,minute,second mmddhhMMss
        
        elm.setElement10("");
        elm.setElement11("000000"); //STAN
        
       /* LocalTime tm = request.getTimeLocal();
        String time = String.valueOf(tm.getHour()) + String.valueOf(tm.getMinute()) + String.valueOf(tm.getSecond());
        elm.setElement12(time); // time local HHMMSS
*/        
       /* LocalDate dt = request.getDateLocal();
        String dateStr = String.valueOf(dt.getMonthValue())+String.valueOf(dt.getDayOfMonth());
        elm.setElement13(dateStr); // date local MMDD
*/        
        elm.setElement18(BkpmConstants.MERCHANT_ID); // merchant ID
        elm.setElement28("0"); //transaction amount fee
        elm.setElement33(BkpmConstants.FORWARDING_INSTITUTION_ID); // forwading institution code
        //elm.setElement37(request.getRetrievaReferencelNumber()); //retrieval reference number
        elm.setElement41(request.getPostingFrom().getUsername()); //card acceptor (username)
        elm.setElement48(request.getMessage()); //additional data (transaction message)
        elm.setElement49(BkpmConstants.CURRENCY_CODE_RUPIAH); //transaction currency
        elm.setElement51(BkpmConstants.CURRENCY_CODE_RUPIAH); //currency code
        
        //generate application information
        /**
         1-2 = fee code (dr??) 12= info saldo beda bank, 52=test in, 53=test out
		 3-8 = product code (dr??) SKN=109001, RTGS=109002
		 9-10 = operation code () 01= approval dg realisasi, 02= approval, 03=realisasi, 05=transaksi biasa, 06=transaksi reversal, 07=posting pending
		 11-20 = tanggal warkat (instrumen perbankan 000 10x)
		 21-40 = nomor warkat (nomor transaksi 000020x)
		 41-60 = device code
		 61-75 = user id (username / centage username) padding spasi di belakang(sisa diisi spasi)
		 76-78 = cabang (khusus teller 000 klo tidak ada)
		 79-81 = lokasi(khusus teller 000 klo tidak ada)
		 82-101 = FTdr T24 (diisi spasi)				
		 102-104 = dep code (di isi spasi)
		 105-106 = flag via va (di isi spasi)
		 107-111 = flag via cbs (di isi spasi)
         */
        elm.setElement62("000000000599999999991234567890123456789011223344556677889900TLR420202      000000FTdariT24           2223355555");
        elm.setElement100(BkpmConstants.FORWARDING_INSTITUTION_ID); // settlement id
        elm.setElement102(request.getPostingFrom().getAccountNumber().toString()); // account identification (acc number from)
        elm.setElement103(request.getPostingTo().getAccountNumber().toString()); // account identification (acc number to)
        msg.setDataElement(elm);
        
        posting.setISOMessages(msg);
        
        
        Holder<HeaderRS> headerRS = new Holder<HeaderRS>();        
        Holder<Posting> postingRS = new Holder<Posting>();
                
        PostingElementRes result = new PostingElementRes();
    	try {
        	portType.postTransaction(headerRQ, posting, headerRS, postingRS);
        	if(null != postingRS.value) {
        		        			
        			if("000".equalsIgnoreCase(postingRS.value.getISOMessages().getDataElement().getElement39())) {
        				result.setType(postingRS.value.getType());
        				
        				ISOMessages msgRes = postingRS.value.getISOMessages();
                		Elements elmRes = msgRes.getDataElement();
                		
                		result.setProcessingCode(elmRes.getElement03());
                		result.setTransactionAmount(elmRes.getElement04());
                		result.setCardHolderBillAmount(elmRes.getElement06());
                		result.setTransmissionDateTime(elmRes.getElement07());
                		result.setConversionRate(elmRes.getElement10());
                		result.setSTAN(elmRes.getElement11());
                		result.setTimeLocalTransaction(elmRes.getElement12());
                		result.setDateLocalTranasaction(elmRes.getElement13());
                		result.setMerchantId(elmRes.getElement18());
                		result.setTransactionAmountFee(elmRes.getElement28());
                		result.setForwardingInstitutionCode(elmRes.getElement33());
                		result.setRetrievalRefNumber(elmRes.getElement37());
                		result.setResponseCode(elmRes.getElement39());
                		result.setCardAcceptor(elmRes.getElement41());
                		result.setAdditionalResponseData(elmRes.getElement44());
                		result.setAdditionalData(elmRes.getElement48());
                		result.setTransactionCurrency(elmRes.getElement49());
                		result.setCurrencyCode(elmRes.getElement51());
                		result.setAdditionalAmount(elmRes.getElement54());
                		result.setApplicationInformation(elmRes.getElement62());
                		result.setSettlementId(elmRes.getElement100());
                		result.setSenderAccountNumber(elmRes.getElement102());
                		result.setReceiverAccountNumber(elmRes.getElement103());
                		
        			} else if("094".equalsIgnoreCase(postingRS.value.getISOMessages().getDataElement().getElement39())){
        				//duplicate transaction
        				//error ny mau di bikin gmn???
        				log.error("duplicate posting transaction");
        			} else {
        				log.error("error posting transaction 3");
        			}
        		
        	} else {
				log.error("error posting transaction 1");
			}
        	
        	
			
		} catch (Fault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return result;
    }
}
