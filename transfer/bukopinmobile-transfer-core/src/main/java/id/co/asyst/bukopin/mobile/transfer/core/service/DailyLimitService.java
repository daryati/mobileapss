/**
 * 
 */
package id.co.asyst.bukopin.mobile.transfer.core.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import id.co.asyst.bukopin.mobile.common.model.payload.CommonResponse;
import id.co.asyst.bukopin.mobile.transfer.core.repository.LimitUserRepository;
import id.co.asyst.bukopin.mobile.transfer.model.entity.TrxLimit;
import id.co.asyst.bukopin.mobile.user.model.entity.User;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 216930237
 *
 */
@Service
@Transactional
public class DailyLimitService {
	private final Logger log = LoggerFactory.getLogger(DailyLimitService.class);

	@Autowired
	private LimitUserRepository lmtUserRepo;

	public DailyLimitService(LimitUserRepository lmtUserRepo) {
		this.lmtUserRepo = lmtUserRepo;
	}

	@Transactional
	public JSONObject findtrxlimitByAccountNumber(String account)
			throws ParseException {
		Calendar calStartDate = Calendar.getInstance();
		calStartDate.set(Calendar.HOUR_OF_DAY, 0);
		calStartDate.set(Calendar.MINUTE, 0);
		calStartDate.set(Calendar.SECOND, 0);

		Calendar calEndDate = Calendar.getInstance();
		calEndDate.set(Calendar.HOUR_OF_DAY, 23);
		calEndDate.set(Calendar.MINUTE, 59);
		calEndDate.set(Calendar.SECOND, 59);

		Date startDate = calStartDate.getTime();
		Date endDate = calEndDate.getTime();
		return lmtUserRepo.findtrxlimitByAccountNumber(account, startDate,
				endDate);
	}

	private Date yesterday() {
		Calendar calStartDate = Calendar.getInstance();
		calStartDate.set(Calendar.HOUR_OF_DAY, 0);
		calStartDate.set(Calendar.MINUTE, 0);
		calStartDate.set(Calendar.SECOND, 0);
		return calStartDate.getTime();
	}

	@Transactional
	public CommonResponse checkdailyLimit(String username, String accNo,
			BigDecimal amount, String jenis) throws ParseException {
		CommonResponse response = new CommonResponse();

		int deldata = lmtUserRepo.deleteDataLimit(yesterday());
		log.debug("hasil delete " + deldata);

		log.debug("masuk ke service validate user limit daily");
		JSONObject dtauser = lmtUserRepo.findLimitUserbyUsername(username);
		log.debug("eror else " + dtauser);
		String dailylimitft = dtauser.get("dailylimitft") + "";
		String dailylimitob = dtauser.get("dailylimitob") + "";
		String dailylimitpurchase = dtauser.get("dailylimitpurchase") + "";
		String dailylimitpayment = dtauser.get("dailylimitpayment") + "";
		BigDecimal dailylimitftbig = new BigDecimal(dailylimitft);
		BigDecimal dailylimitobbig = new BigDecimal(dailylimitob);
		BigDecimal dailylimitpurchasebig = new BigDecimal(dailylimitpurchase);
		BigDecimal dailylimitpaymentbig = new BigDecimal(dailylimitpayment);
		JSONObject dataLimit = new JSONObject();
		if (dtauser.size() == 0) {
			response.setCode("99");
			response.setMessage("Limit user not set");
		} else {
			JSONObject trxlimit = findtrxlimitByAccountNumber(accNo);
			if (trxlimit == null) {
				if (!dtauser.get("limitId").equals(null)
						&& !dtauser.get("limitId").equals("null")) {
					Calendar calStartDate = Calendar.getInstance();
					Date createDate = calStartDate.getTime();
					Long limitId = (Long) dtauser.get("limitId");
					int nilai = 0;
					if (jenis.equals("overbook")) {
						if (dailylimitobbig.compareTo(amount) < 0) {
							nilai++;
						}
						dataLimit.put("totalAmountob", amount);
					} else if (jenis.equals("fundtransfer")) {
						if (dailylimitftbig.compareTo(amount) < 0) {
							nilai++;
						}
						dataLimit.put("totalAmountft", amount);
					} else if (jenis.equals("purchase")) {
						if (dailylimitpurchasebig.compareTo(amount) < 0) {
							nilai++;
						}
						dataLimit.put("totalAmountpurchase", amount);
					} else if (jenis.equals("payment")) {
						if (dailylimitpaymentbig.compareTo(amount) < 0) {
							nilai++;
						}
						dataLimit.put("totalAmountpayment", amount);
					} else {
						response.setCode("99");
						response.setMessage("transaction limit type not found");
					}
					if (nilai > 0) {
						response.setCode("99");
						response.setMessage("amount more than daily limit user");
					} else {
						User usertrans = new User();
						usertrans.setUsername(username);
						usertrans.setId((Long) dtauser.get("userid"));
						dataLimit.put("createDate", createDate);
						dataLimit.put("limitId", limitId);
						dataLimit.put("username", usertrans);
						dataLimit.put("status", "Success");
						dataLimit.put("reason", "");
						dataLimit.put("menu", "daily limit user");
						dataLimit.put("action", "save");
						dataLimit.put("jenis", jenis);
						dataLimit.put("accountNumber", accNo);
						response.setData(dataLimit);
						response.setCode("000");
						response.setMessage("Success");

					}

				} else {
					response.setCode("99");
					response.setMessage("limit user unset");
				}
			} else {
				log.debug("masuk else");
				Long idnya = (Long) trxlimit.get("id");
				Long limitId = (Long) dtauser.get("limitId");
				String totalAmountft = trxlimit.get("totalAmountft") + "";
				String totalAmountob = trxlimit.get("totalAmountob") + "";
				String totalAmountpurchase = trxlimit
						.get("totalAmountpurchase") + "";
				String totalAmountpayment = trxlimit.get("totalAmountpayment")
						+ "";
				BigDecimal lmtob = new BigDecimal(0);
				BigDecimal lmtft = new BigDecimal(0);
				BigDecimal lmtpurchase = new BigDecimal(0);
				BigDecimal lmtpayment = new BigDecimal(0);
				dataLimit.put("id", idnya);
				dataLimit.put("jenis", jenis);
				dataLimit.put("action", "update");
				dataLimit.put("limitId", limitId);
				User usertrans = new User();
				usertrans.setUsername(username);
				usertrans.setId((Long) dtauser.get("userid"));
				dataLimit.put("username", usertrans);
				if (!totalAmountft.equals("null") && !totalAmountft.equals("")
						&& !totalAmountft.equals(null)) {
					lmtft = new BigDecimal(totalAmountft);
				}
				if (!totalAmountob.equals(null)
						&& !totalAmountob.equals("null")
						&& !totalAmountob.equals("")) {
					lmtob = new BigDecimal(totalAmountob);
				}

				if (!totalAmountpurchase.equals(null)
						&& !totalAmountpurchase.equals("null")
						&& !totalAmountpurchase.equals("")) {
					lmtpurchase = new BigDecimal(totalAmountpurchase);
				}
				if (!totalAmountpayment.equals(null)
						&& !totalAmountpayment.equals("null")
						&& !totalAmountpayment.equals("")) {
					lmtpayment = new BigDecimal(totalAmountpayment);
				}
				log.debug("total amount ft " + totalAmountft + "totalAmountob "
						+ totalAmountob + "totalAmountpurchase "
						+ totalAmountpurchase + "totalAmountpayment "
						+ totalAmountpayment);
				int temp = 0;
				if (jenis.equals("overbook")) {
					BigDecimal total = amount.add(lmtob);
					if (dailylimitobbig.compareTo(total) >= 0) {
						dataLimit.put("totalAmountob", total);
					} else {
						temp++;
					}
				} else if (jenis.equals("fundtransfer")) {
					BigDecimal total = amount.add(lmtft);
					if (dailylimitftbig.compareTo(total) >= 0) {
						dataLimit.put("totalAmountft", total);
					} else {
						temp++;
					}
				} else if (jenis.equals("purchase")) {
					BigDecimal total = amount.add(lmtpurchase);
					if (dailylimitpurchasebig.compareTo(total) >= 0) {
						dataLimit.put("totalAmountpurchase", total);
					} else {
						temp++;
					}
				} else if (jenis.equals("payment")) {
					BigDecimal total = amount.add(lmtpayment);
					if (dailylimitpaymentbig.compareTo(total) >= 0) {
						dataLimit.put("totalAmountpayment", total);
					} else {
						temp++;
					}
				}

				if (temp == 0) {
					response.setCode("000");
					response.setMessage("Success");
					response.setData(dataLimit);
				} else {
					response.setCode("99");
					response.setMessage("transactions exceed daily limit");
				}

			}

		}

		return response;

	}

	public CommonResponse prosesValidateLimit(Object request) {
		JSONObject param = (JSONObject) request;
		log.debug("isi dari request " + param + " request asli " + request);
		CommonResponse response = new CommonResponse();
		log.debug((String) param.get("action"));
		if (param != null) {
			if (param.get("action").equals("save")) {
				log.debug("masuk save ");
				TrxLimit trxlimit = new TrxLimit();
				trxlimit.setAccountNumber((String) param.get("accountNumber"));
				trxlimit.setLimitId((Long) param.get("limitId"));
				if (param.get("jenis").equals("overbook")) {
					trxlimit.setTotalAmountob((BigDecimal) param
							.get("totalAmountob"));
				} else if (param.get("jenis").equals("fundtransfer")) {
					trxlimit.setTotalAmountft((BigDecimal) param
							.get("totalAmountft"));
				} else if (param.get("jenis").equals("purchase")) {
					trxlimit.setTotalAmountpurchase((BigDecimal) param
							.get("totalAmountpurchase"));

				} else if (param.get("jenis").equals("payment")) {
					trxlimit.setTotalAmountpayment((BigDecimal) param
							.get("totalAmountpayment"));
				}
				Calendar calStartDate = Calendar.getInstance();
				Date createDate = calStartDate.getTime();
				trxlimit.setCreateDate(createDate);
				trxlimit.setUsername((User) param.get("username"));
				trxlimit.setMenu((String) param.get("menu"));
				trxlimit.setReason("");
				trxlimit.setStatus((String) param.get("status"));
				log.debug("trxlimit " + trxlimit);
				lmtUserRepo.save(trxlimit);
			} else if (param.get("action").equals("update")) {
				Long idnya = (Long) param.get("id");
				if (param.get("jenis").equals("overbook")) {
					lmtUserRepo.updatedailylimitob(idnya,
							(BigDecimal) param.get("totalAmountob"));
				} else if (param.get("jenis").equals("fundtransfer")) {
					lmtUserRepo.updatedailylimitft(idnya,
							(BigDecimal) param.get("totalAmountft"));
				} else if (param.get("jenis").equals("purchase")) {
					lmtUserRepo.updatedailylimitpurchase(idnya,
							(BigDecimal) param.get("totalAmountpurchase"));
				} else if (param.get("jenis").equals("payment")) {
					log.debug("update payment "
							+ (BigDecimal) param.get("totalAmountpayment"));
					lmtUserRepo.updatedailylimitpayment(idnya,
							(BigDecimal) param.get("totalAmountpayment"));
				}
				response.setCode("000");
				response.setMessage("Success");
			} else {
				response.setCode("99");
				response.setMessage("action null");
			}
		} else {
			response.setCode("99");
			response.setMessage("failed to proccess data");
		}
		return response;

	}

}
