
package id.co.asyst.bukopin.mobile.user.model.soap.account;

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
 * <p>Java class for GetInquiryTransactionResType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetInquiryTransactionResType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="response"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="resCode"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="desc"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="0"/&gt;
 *                         &lt;maxLength value="255"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="accInfo"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="accNo"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="0"/&gt;
 *                         &lt;maxLength value="20"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="accType" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                   &lt;element name="name" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="0"/&gt;
 *                         &lt;maxLength value="60"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="accStatus" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="0"/&gt;
 *                         &lt;maxLength value="10"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Currency" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="0"/&gt;
 *                         &lt;maxLength value="10"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Branch" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="0"/&gt;
 *                         &lt;maxLength value="50"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Location" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="0"/&gt;
 *                         &lt;maxLength value="50"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="productID" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="0"/&gt;
 *                         &lt;maxLength value="15"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="cifNumber" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="cifStatus" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="0"/&gt;
 *                         &lt;maxLength value="10"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="trxSummary" type="{http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0}trxSummaryType" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="transaction" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="postingDate"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}dateTime"&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="effectiveDate"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}dateTime"&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="trxDesc1"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="0"/&gt;
 *                         &lt;maxLength value="40"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="trxDesc2"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="1"/&gt;
 *                         &lt;maxLength value="40"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="trxDesc3"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="1"/&gt;
 *                         &lt;maxLength value="60"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="amtDebit"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="amtCredit"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="balanceAmt"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="reffNumber"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="0"/&gt;
 *                         &lt;maxLength value="40"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="trxSummary" type="{http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0}trxSummaryType" minOccurs="0"/&gt;
 *         &lt;element name="pagingInfo" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="startIndex" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                   &lt;element name="itemsPerPage" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                   &lt;element name="totalResult" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetInquiryTransactionResType", namespace = "http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0", propOrder = {
    "response",
    "accInfo",
    "transaction",
    "trxSummary",
    "pagingInfo"
})
public class GetInquiryTransactionResType {

    @XmlElement(required = true)
    protected GetInquiryTransactionResType.Response response;
    @XmlElement(required = true)
    protected GetInquiryTransactionResType.AccInfo accInfo;
    protected List<GetInquiryTransactionResType.Transaction> transaction;
    protected TrxSummaryType trxSummary;
    protected GetInquiryTransactionResType.PagingInfo pagingInfo;

    /**
     * Gets the value of the response property.
     * 
     * @return
     *     possible object is
     *     {@link GetInquiryTransactionResType.Response }
     *     
     */
    public GetInquiryTransactionResType.Response getResponse() {
        return response;
    }

    /**
     * Sets the value of the response property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetInquiryTransactionResType.Response }
     *     
     */
    public void setResponse(GetInquiryTransactionResType.Response value) {
        this.response = value;
    }

    /**
     * Gets the value of the accInfo property.
     * 
     * @return
     *     possible object is
     *     {@link GetInquiryTransactionResType.AccInfo }
     *     
     */
    public GetInquiryTransactionResType.AccInfo getAccInfo() {
        return accInfo;
    }

    /**
     * Sets the value of the accInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetInquiryTransactionResType.AccInfo }
     *     
     */
    public void setAccInfo(GetInquiryTransactionResType.AccInfo value) {
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
     * {@link GetInquiryTransactionResType.Transaction }
     * 
     * 
     */
    public List<GetInquiryTransactionResType.Transaction> getTransaction() {
        if (transaction == null) {
            transaction = new ArrayList<GetInquiryTransactionResType.Transaction>();
        }
        return this.transaction;
    }

    /**
     * Gets the value of the trxSummary property.
     * 
     * @return
     *     possible object is
     *     {@link TrxSummaryType }
     *     
     */
    public TrxSummaryType getTrxSummary() {
        return trxSummary;
    }

    /**
     * Sets the value of the trxSummary property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrxSummaryType }
     *     
     */
    public void setTrxSummary(TrxSummaryType value) {
        this.trxSummary = value;
    }

    /**
     * Gets the value of the pagingInfo property.
     * 
     * @return
     *     possible object is
     *     {@link GetInquiryTransactionResType.PagingInfo }
     *     
     */
    public GetInquiryTransactionResType.PagingInfo getPagingInfo() {
        return pagingInfo;
    }

    /**
     * Sets the value of the pagingInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetInquiryTransactionResType.PagingInfo }
     *     
     */
    public void setPagingInfo(GetInquiryTransactionResType.PagingInfo value) {
        this.pagingInfo = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="accNo"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="0"/&gt;
     *               &lt;maxLength value="20"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="accType" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
     *         &lt;element name="name" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="0"/&gt;
     *               &lt;maxLength value="60"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="accStatus" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="0"/&gt;
     *               &lt;maxLength value="10"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Currency" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="0"/&gt;
     *               &lt;maxLength value="10"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Branch" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="0"/&gt;
     *               &lt;maxLength value="50"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Location" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="0"/&gt;
     *               &lt;maxLength value="50"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="productID" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="0"/&gt;
     *               &lt;maxLength value="15"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="cifNumber" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="cifStatus" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="0"/&gt;
     *               &lt;maxLength value="10"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="trxSummary" type="{http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0}trxSummaryType" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "accNo",
        "accType",
        "name",
        "accStatus",
        "currency",
        "branch",
        "location",
        "productID",
        "cifNumber",
        "cifStatus",
        "trxSummary"
    })
    public static class AccInfo {

        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0", required = true)
        protected String accNo;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0", required = true)
        protected BigInteger accType;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0")
        protected String name;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0")
        protected String accStatus;
        @XmlElement(name = "Currency", namespace = "http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0")
        protected String currency;
        @XmlElement(name = "Branch", namespace = "http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0")
        protected String branch;
        @XmlElement(name = "Location", namespace = "http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0")
        protected String location;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0")
        protected String productID;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0")
        protected BigInteger cifNumber;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0")
        protected String cifStatus;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0")
        protected TrxSummaryType trxSummary;

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
         * Gets the value of the accType property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getAccType() {
            return accType;
        }

        /**
         * Sets the value of the accType property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setAccType(BigInteger value) {
            this.accType = value;
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
         * Gets the value of the accStatus property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAccStatus() {
            return accStatus;
        }

        /**
         * Sets the value of the accStatus property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAccStatus(String value) {
            this.accStatus = value;
        }

        /**
         * Gets the value of the currency property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCurrency() {
            return currency;
        }

        /**
         * Sets the value of the currency property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCurrency(String value) {
            this.currency = value;
        }

        /**
         * Gets the value of the branch property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBranch() {
            return branch;
        }

        /**
         * Sets the value of the branch property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBranch(String value) {
            this.branch = value;
        }

        /**
         * Gets the value of the location property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLocation() {
            return location;
        }

        /**
         * Sets the value of the location property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLocation(String value) {
            this.location = value;
        }

        /**
         * Gets the value of the productID property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProductID() {
            return productID;
        }

        /**
         * Sets the value of the productID property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProductID(String value) {
            this.productID = value;
        }

        /**
         * Gets the value of the cifNumber property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getCifNumber() {
            return cifNumber;
        }

        /**
         * Sets the value of the cifNumber property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setCifNumber(BigInteger value) {
            this.cifNumber = value;
        }

        /**
         * Gets the value of the cifStatus property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCifStatus() {
            return cifStatus;
        }

        /**
         * Sets the value of the cifStatus property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCifStatus(String value) {
            this.cifStatus = value;
        }

        /**
         * Gets the value of the trxSummary property.
         * 
         * @return
         *     possible object is
         *     {@link TrxSummaryType }
         *     
         */
        public TrxSummaryType getTrxSummary() {
            return trxSummary;
        }

        /**
         * Sets the value of the trxSummary property.
         * 
         * @param value
         *     allowed object is
         *     {@link TrxSummaryType }
         *     
         */
        public void setTrxSummary(TrxSummaryType value) {
            this.trxSummary = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="startIndex" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
     *         &lt;element name="itemsPerPage" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
     *         &lt;element name="totalResult" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
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

        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0", required = true)
        protected BigInteger startIndex;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0", required = true)
        protected BigInteger itemsPerPage;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0", required = true)
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
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="resCode"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="desc"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="0"/&gt;
     *               &lt;maxLength value="255"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "resCode",
        "desc"
    })
    public static class Response {

        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0", required = true)
        protected String resCode;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0", required = true)
        protected String desc;

        /**
         * Gets the value of the resCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getResCode() {
            return resCode;
        }

        /**
         * Sets the value of the resCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setResCode(String value) {
            this.resCode = value;
        }

        /**
         * Gets the value of the desc property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDesc() {
            return desc;
        }

        /**
         * Sets the value of the desc property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDesc(String value) {
            this.desc = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="postingDate"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}dateTime"&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="effectiveDate"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}dateTime"&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="trxDesc1"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="0"/&gt;
     *               &lt;maxLength value="40"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="trxDesc2"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="1"/&gt;
     *               &lt;maxLength value="40"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="trxDesc3"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="1"/&gt;
     *               &lt;maxLength value="60"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="amtDebit"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="amtCredit"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="balanceAmt"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="reffNumber"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="0"/&gt;
     *               &lt;maxLength value="40"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "postingDate",
        "effectiveDate",
        "trxDesc1",
        "trxDesc2",
        "trxDesc3",
        "amtDebit",
        "amtCredit",
        "balanceAmt",
        "reffNumber"
    })
    public static class Transaction {

        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0", required = true)
        protected XMLGregorianCalendar postingDate;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0", required = true)
        protected XMLGregorianCalendar effectiveDate;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0", required = true)
        protected String trxDesc1;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0", required = true)
        protected String trxDesc2;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0", required = true)
        protected String trxDesc3;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0", required = true)
        protected BigDecimal amtDebit;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0", required = true)
        protected BigDecimal amtCredit;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0", required = true)
        protected BigDecimal balanceAmt;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0", required = true)
        protected String reffNumber;

        /**
         * Gets the value of the postingDate property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getPostingDate() {
            return postingDate;
        }

        /**
         * Sets the value of the postingDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setPostingDate(XMLGregorianCalendar value) {
            this.postingDate = value;
        }

        /**
         * Gets the value of the effectiveDate property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getEffectiveDate() {
            return effectiveDate;
        }

        /**
         * Sets the value of the effectiveDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setEffectiveDate(XMLGregorianCalendar value) {
            this.effectiveDate = value;
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
         * Gets the value of the trxDesc3 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTrxDesc3() {
            return trxDesc3;
        }

        /**
         * Sets the value of the trxDesc3 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTrxDesc3(String value) {
            this.trxDesc3 = value;
        }

        /**
         * Gets the value of the amtDebit property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getAmtDebit() {
            return amtDebit;
        }

        /**
         * Sets the value of the amtDebit property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setAmtDebit(BigDecimal value) {
            this.amtDebit = value;
        }

        /**
         * Gets the value of the amtCredit property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getAmtCredit() {
            return amtCredit;
        }

        /**
         * Sets the value of the amtCredit property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setAmtCredit(BigDecimal value) {
            this.amtCredit = value;
        }

        /**
         * Gets the value of the balanceAmt property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getBalanceAmt() {
            return balanceAmt;
        }

        /**
         * Sets the value of the balanceAmt property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setBalanceAmt(BigDecimal value) {
            this.balanceAmt = value;
        }

        /**
         * Gets the value of the reffNumber property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getReffNumber() {
            return reffNumber;
        }

        /**
         * Sets the value of the reffNumber property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setReffNumber(String value) {
            this.reffNumber = value;
        }

    }

}
