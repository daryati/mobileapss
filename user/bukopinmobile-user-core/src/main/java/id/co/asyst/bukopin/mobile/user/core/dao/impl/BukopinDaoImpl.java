/*
 * $Id$
 * 
 * Copyright (c) 2019 Aero Systems Indonesia, PT.
 * All rights reserved.
 * 
 * AERO SYSTEMS INDONESIA PROPRIETARY/CONFIDENTIAL. Use is subject to
 * license terms.
 */
package id.co.asyst.bukopin.mobile.user.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import id.co.asyst.bukopin.mobile.user.core.dao.BukopinDao;
import id.co.asyst.bukopin.mobile.user.model.entity.internal.DebitCardInfo;

/**
 * DAO implementation
 * 
 * @author Eka Ariyansyah
 * @version $Revision$, Nov 5, 2019
 * @since 1.0.Alpha1-SNAPSHOT
 */
@Repository
@Transactional("bukopinTransactionManager")
public class BukopinDaoImpl implements BukopinDao {
    
    @PersistenceContext(unitName="bukopinEntityManagerFactory")
    private EntityManager entityManager;
    
    /* Constants: */

    /* Attributes: */

    /* Transient Attributes: */

    /* Constructors: */

    /* Getters & setters for attributes: */

    /* Getters & setters for transient attributes: */

    /* Functionalities: */

    /* Overrides: */
    /* (non-Javadoc)
     * @see id.co.asyst.bukopin.mobile.user.core.dao.BukopinDao#getCardByCardNumber(java.lang.String)
     */
    @Override
    public List<DebitCardInfo> getCardByCardNumber(String cardNumber) {
	System.out.println("Bukopin Dao - Get Card: "+cardNumber);
//	String sql = "select c.card_no as NOMOR_KARTU, c.status AS STATUS_KARTU," + 
//		" c.expiry AS EXPIRED_DATE, n.id AS CIF," + 
//		" c.acc_no AS ACCOUNT_NUMBER" + 
//		" from nasabah n, card c" + 
//		" where n.id = c.member_no;";
	String sql = "SELECT" + 
		" A.PAN AS NOMOR_KARTU, A.CARDSTATUSXID AS STATUS_KARTU," + 
		" A.CARDEXPIRYDATE AS EXPIRED_DATE, B.MEMBERID AS CIF," + 
		" C.ACCOUNTNO AS ACCOUNT_NUMBER" + 
		" FROM xlink.XLCARD A," + 
		" xlink.XLMEMBER B, xlink.XLACCOUNT C" + 
		" WHERE" + 
		" A.MEMBERXID = B.MEMBERXID" + 
		" AND A.MEMBERXID = C.MEMBERXID" + 
		" AND A.PAN = '"+cardNumber+"';";
	
	// run query
	Session session = entityManager.unwrap(Session.class);
	List<Object> lo = session.createSQLQuery(sql)
//		.setResultSetMapping("debitResult")
//		.setResultTransformer(Transformers.aliasToBean(DebitCardResult.class))
//		.addEntity(DebitCardResult.class)
		.list();
	
	// Mapping result
	List<DebitCardInfo> result = new ArrayList<DebitCardInfo>();
	lo.forEach(row -> {
	    Object[] cols = (Object[]) row;
	    DebitCardInfo dcr = new DebitCardInfo();
	    dcr.setCardNumber(String.valueOf(cols[0]));
	    dcr.setCardStatus(String.valueOf(cols[1]));
	    dcr.setExpiredDate(String.valueOf(cols[2]));
	    dcr.setCif(String.valueOf(cols[3]));
	    dcr.setAccountNumber(String.valueOf(cols[4]));
	    result.add(dcr);
	});
	entityManager.close();
	
	return result;
    }
}
