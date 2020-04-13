
package id.co.asyst.bukopin.mobile.user.model.soap.statement;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for GetStatementByDateRangeResType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetStatementByDateRangeResType">
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
 *                   &lt;element name="batchNo">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;minLength value="0"/>
 *                         &lt;maxLength value="4"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="monthDay">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;minLength value="0"/>
 *                         &lt;maxLength value="4"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="trxNo">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;minLength value="0"/>
 *                         &lt;maxLength value="7"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="acc1">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;minLength value="1"/>
 *                         &lt;maxLength value="19"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="acc2">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;minLength value="1"/>
 *                         &lt;maxLength value="19"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="trxAmount">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;minLength value="0"/>
 *                         &lt;maxLength value="16"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="rateExchange">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;minLength value="0"/>
 *                         &lt;maxLength value="16"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="valasTrxAmount">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;minLength value="0"/>
 *                         &lt;maxLength value="16"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="trxDesc1">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;minLength value="0"/>
 *                         &lt;maxLength value="40"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="trxDesc2">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;minLength value="0"/>
 *                         &lt;maxLength value="40"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="trxDateTime">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}dateTime">
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="posting">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;minLength value="0"/>
 *                         &lt;maxLength value="1"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="pagingInfo" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="startIndex" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *                   &lt;element name="itemsPerPage" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *                   &lt;element name="totalResult" type="{http://www.w3.org/2001/XMLSchema}integer"/>
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
@XmlType(name = "GetStatementByDateRangeResType", propOrder = {
    "accInfo",
    "transaction",
    "pagingInfo"
})
public class GetStatementByDateRangeResType {

    protected GetStatementByDateRangeResType.AccInfo accInfo;
    protected List<GetStatementByDateRangeResType.Transaction> transaction;
    protected GetStatementByDateRangeResType.PagingInfo pagingInfo;

    /**
     * Gets the value of the accInfo property.
     * 
     * @return
     *     possible object is
     *     {@link GetStatementByDateRangeResType.AccInfo }
     *     
     */
    public GetStatementByDateRangeResType.AccInfo getAccInfo() {
        return accInfo;
    }

    /**
     * Sets the value of the accInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetStatementByDateRangeResType.AccInfo }
     *     
     */
    public void setAccInfo(GetStatementByDateRangeResType.AccInfo value) {
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
     * {@link GetStatementByDateRangeResType.Transaction }
     * 
     * 
     */
    public List<GetStatementByDateRangeResType.Transaction> getTransaction() {
        if (transaction == null) {
            transaction = new ArrayList<GetStatementByDateRangeResType.Transaction>();
        }
        return this.transaction;
    }

    /**
     * Gets the value of the pagingInfo property.
     * 
     * @return
     *     possible object is
     *     {@link GetStatementByDateRangeResType.PagingInfo }
     *     
     */
    public GetStatementByDateRangeResType.PagingInfo getPagingInfo() {
        return pagingInfo;
    }

    /**
     * Sets the value of the pagingInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetStatementByDateRangeResType.PagingInfo }
     *     
     */
    public void setPagingInfo(GetStatementByDateRangeResType.PagingInfo value) {
        this.pagingInfo = value;
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
     *         &lt;element name="startIndex" type="{http://www.w3.org/2001/XMLSchema}integer"/>
     *         &lt;element name="itemsPerPage" type="{http://www.w3.org/2001/XMLSchema}integer"/>
     *         &lt;element name="totalResult" type="{http://www.w3.org/2001/XMLSchema}integer"/>
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
        "startIndex",
        "itemsPerPage",
        "totalResult"
    })
    public static class PagingInfo {

        @XmlElement(required = true)
        protected BigInteger startIndex;
        @XmlElement(required = true)
        protected BigInteger itemsPerPage;
        @XmlElement(required = true)
        protected BigInteger totalResult;

        /**
         * Gets the value of the startIndex property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getStartIndex() {
            return startIndex;
        }

        /**
         * Sets the value of the startIndex property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setStartIndex(BigInteger value) {
            this.startIndex = value;
        }

        /**
         * Gets the value of the itemsPerPage property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getItemsPerPage() {
            return itemsPerPage;
        }

        /**
         * Sets the value of the itemsPerPage property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setItemsPerPage(BigInteger value) {
            this.itemsPerPage = value;
        }

        /**
         * Gets the value of the totalResult property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getTotalResult() {
            return totalResult;
        }

        /**
         * Sets the value of the totalResult property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setTotalResult(BigInteger value) {
            this.totalResult = value;
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
     *         &lt;element name="batchNo">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;minLength value="0"/>
     *               &lt;maxLength value="4"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="monthDay">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;minLength value="0"/>
     *               &lt;maxLength value="4"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="trxNo">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;minLength value="0"/>
     *               &lt;maxLength value="7"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="acc1">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;minLength value="1"/>
     *               &lt;maxLength value="19"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="acc2">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;minLength value="1"/>
     *               &lt;maxLength value="19"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="trxAmount">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;minLength value="0"/>
     *               &lt;maxLength value="16"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="rateExchange">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;minLength value="0"/>
     *               &lt;maxLength value="16"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="valasTrxAmount">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;minLength value="0"/>
     *               &lt;maxLength value="16"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="trxDesc1">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;minLength value="0"/>
     *               &lt;maxLength value="40"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="trxDesc2">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;minLength value="0"/>
     *               &lt;maxLength value="40"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="trxDateTime">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}dateTime">
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="posting">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;minLength value="0"/>
     *               &lt;maxLength value="1"/>
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
        "batchNo",
        "monthDay",
        "trxNo",
        "acc1",
        "acc2",
        "trxAmount",
        "rateExchange",
        "valasTrxAmount",
        "trxDesc1",
        "trxDesc2",
        "trxDateTime",
        "posting"
    })
    public static class Transaction {

        @XmlElement(required = true)
        protected String batchNo;
        @XmlElement(required = true)
        protected String monthDay;
        @XmlElement(required = true)
        protected String trxNo;
        @XmlElement(required = true)
        protected String acc1;
        @XmlElement(required = true)
        protected String acc2;
        @XmlElement(required = true)
        protected String trxAmount;
        @XmlElement(required = true)
        protected String rateExchange;
        @XmlElement(required = true)
        protected String valasTrxAmount;
        @XmlElement(required = true)
        protected String trxDesc1;
        @XmlElement(required = true)
        protected String trxDesc2;
        @XmlElement(required = true)
        protected XMLGregorianCalendar trxDateTime;
        @XmlElement(required = true)
        protected String posting;

        /**
         * Gets the value of the batchNo property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBatchNo() {
            return batchNo;
        }

        /**
         * Sets the value of the batchNo property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBatchNo(String value) {
            this.batchNo = value;
        }

        /**
         * Gets the value of the monthDay property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMonthDay() {
            return monthDay;
        }

        /**
         * Sets the value of the monthDay property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMonthDay(String value) {
            this.monthDay = value;
        }

        /**
         * Gets the value of the trxNo property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTrxNo() {
            return trxNo;
        }

        /**
         * Sets the value of the trxNo property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTrxNo(String value) {
            this.trxNo = value;
        }

        /**
         * Gets the value of the acc1 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAcc1() {
            return acc1;
        }

        /**
         * Sets the value of the acc1 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAcc1(String value) {
            this.acc1 = value;
        }

        /**
         * Gets the value of the acc2 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAcc2() {
            return acc2;
        }

        /**
         * Sets the value of the acc2 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAcc2(String value) {
            this.acc2 = value;
        }

        /**
         * Gets the value of the trxAmount property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTrxAmount() {
            return trxAmount;
        }

        /**
         * Sets the value of the trxAmount property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTrxAmount(String value) {
            this.trxAmount = value;
        }

        /**
         * Gets the value of the rateExchange property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRateExchange() {
            return rateExchange;
        }

        /**
         * Sets the value of the rateExchange property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRateExchange(String value) {
            this.rateExchange = value;
        }

        /**
         * Gets the value of the valasTrxAmount property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValasTrxAmount() {
            return valasTrxAmount;
        }

        /**
         * Sets the value of the valasTrxAmount property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValasTrxAmount(String value) {
            this.valasTrxAmount = value;
        }

        /**
         * Gets the value of the trxDesc1 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTrxDesc1() {
            return trxDesc1;
        }

        /**
         * Sets the value of the trxDesc1 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTrxDesc1(String value) {
            this.trxDesc1 = value;
        }

        /**
         * Gets the value of the trxDesc2 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTrxDesc2() {
            return trxDesc2;
        }

        /**
         * Sets the value of the trxDesc2 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTrxDesc2(String value) {
            this.trxDesc2 = value;
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

    }

}
