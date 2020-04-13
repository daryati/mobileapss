
package id.co.asyst.bukopin.mobile.user.model.soap.statement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for GetMiniStatementResType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetMiniStatementResType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accInfo" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="accNo">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;minLength value="0"/>
 *                         &lt;maxLength value="20"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="name">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;minLength value="0"/>
 *                         &lt;maxLength value="60"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="lastBalance">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="transaction" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="trxID">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;minLength value="1"/>
 *                         &lt;maxLength value="4"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="trxAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *                   &lt;element name="posting">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;minLength value="0"/>
 *                         &lt;maxLength value="1"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="trxDateTime">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}dateTime">
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="trxDesc">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;minLength value="0"/>
 *                         &lt;maxLength value="40"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetMiniStatementResType", propOrder = {
    "accInfo",
    "transaction"
})
public class GetMiniStatementResType {

    protected GetMiniStatementResType.AccInfo accInfo;
    protected List<GetMiniStatementResType.Transaction> transaction;

    /**
     * Gets the value of the accInfo property.
     * 
     * @return
     *     possible object is
     *     {@link GetMiniStatementResType.AccInfo }
     *     
     */
    public GetMiniStatementResType.AccInfo getAccInfo() {
        return accInfo;
    }

    /**
     * Sets the value of the accInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetMiniStatementResType.AccInfo }
     *     
     */
    public void setAccInfo(GetMiniStatementResType.AccInfo value) {
        this.accInfo = value;
    }

    /**
     * Gets the value of the transaction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transaction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransaction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GetMiniStatementResType.Transaction }
     * 
     * 
     */
    public List<GetMiniStatementResType.Transaction> getTransaction() {
        if (transaction == null) {
            transaction = new ArrayList<GetMiniStatementResType.Transaction>();
        }
        return this.transaction;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="accNo">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;minLength value="0"/>
     *               &lt;maxLength value="20"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="name">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;minLength value="0"/>
     *               &lt;maxLength value="60"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="lastBalance">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "accNo",
        "name",
        "lastBalance"
    })
    public static class AccInfo {

        @XmlElement(required = true)
        protected String accNo;
        @XmlElement(required = true)
        protected String name;
        @XmlElement(required = true)
        protected BigDecimal lastBalance;

        /**
         * Gets the value of the accNo property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAccNo() {
            return accNo;
        }

        /**
         * Sets the value of the accNo property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAccNo(String value) {
            this.accNo = value;
        }

        /**
         * Gets the value of the name property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the value of the name property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setName(String value) {
            this.name = value;
        }

        /**
         * Gets the value of the lastBalance property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getLastBalance() {
            return lastBalance;
        }

        /**
         * Sets the value of the lastBalance property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setLastBalance(BigDecimal value) {
            this.lastBalance = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="trxID">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;minLength value="1"/>
     *               &lt;maxLength value="4"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="trxAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
     *         &lt;element name="posting">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;minLength value="0"/>
     *               &lt;maxLength value="1"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="trxDateTime">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}dateTime">
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="trxDesc">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;minLength value="0"/>
     *               &lt;maxLength value="40"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "trxID",
        "trxAmount",
        "posting",
        "trxDateTime",
        "trxDesc"
    })
    public static class Transaction {

        @XmlElement(required = true)
        protected String trxID;
        @XmlElement(required = true)
        protected BigDecimal trxAmount;
        @XmlElement(required = true)
        protected String posting;
        @XmlElement(required = true)
        protected XMLGregorianCalendar trxDateTime;
        @XmlElement(required = true)
        protected String trxDesc;

        /**
         * Gets the value of the trxID property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTrxID() {
            return trxID;
        }

        /**
         * Sets the value of the trxID property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTrxID(String value) {
            this.trxID = value;
        }

        /**
         * Gets the value of the trxAmount property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getTrxAmount() {
            return trxAmount;
        }

        /**
         * Sets the value of the trxAmount property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setTrxAmount(BigDecimal value) {
            this.trxAmount = value;
        }

        /**
         * Gets the value of the posting property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPosting() {
            return posting;
        }

        /**
         * Sets the value of the posting property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPosting(String value) {
            this.posting = value;
        }

        /**
         * Gets the value of the trxDateTime property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getTrxDateTime() {
            return trxDateTime;
        }

        /**
         * Sets the value of the trxDateTime property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setTrxDateTime(XMLGregorianCalendar value) {
            this.trxDateTime = value;
        }

        /**
         * Gets the value of the trxDesc property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTrxDesc() {
            return trxDesc;
        }

        /**
         * Sets the value of the trxDesc property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTrxDesc(String value) {
            this.trxDesc = value;
        }

    }

}
