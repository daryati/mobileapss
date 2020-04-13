
package id.co.asyst.bukopin.mobile.user.model.soap.account;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for GetInquiryTransactionReqType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetInquiryTransactionReqType"&gt;
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
 *         &lt;choice&gt;
 *           &lt;element name="Date" minOccurs="0"&gt;
 *             &lt;complexType&gt;
 *               &lt;complexContent&gt;
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                   &lt;sequence&gt;
 *                     &lt;element name="dateFrom" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *                     &lt;element name="dateTo" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *                   &lt;/sequence&gt;
 *                 &lt;/restriction&gt;
 *               &lt;/complexContent&gt;
 *             &lt;/complexType&gt;
 *           &lt;/element&gt;
 *           &lt;element name="nTrx" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;/choice&gt;
 *         &lt;element name="pagingInfo" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="startIndex" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                   &lt;element name="itemsPerPage" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                   &lt;element name="totalResult" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
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
@XmlType(name = "GetInquiryTransactionReqType", namespace = "http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0", propOrder = {
    "accNo",
    "accType",
    "date",
    "nTrx",
    "pagingInfo"
})
public class GetInquiryTransactionReqType {

    @XmlElement(required = true)
    protected String accNo;
    @XmlElement(required = true)
    protected BigInteger accType;
    @XmlElement(name = "Date")
    protected GetInquiryTransactionReqType.Date date;
    protected BigInteger nTrx;
    protected GetInquiryTransactionReqType.PagingInfo pagingInfo;

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
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link GetInquiryTransactionReqType.Date }
     *     
     */
    public GetInquiryTransactionReqType.Date getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetInquiryTransactionReqType.Date }
     *     
     */
    public void setDate(GetInquiryTransactionReqType.Date value) {
        this.date = value;
    }

    /**
     * Gets the value of the nTrx property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNTrx() {
        return nTrx;
    }

    /**
     * Sets the value of the nTrx property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNTrx(BigInteger value) {
        this.nTrx = value;
    }

    /**
     * Gets the value of the pagingInfo property.
     * 
     * @return
     *     possible object is
     *     {@link GetInquiryTransactionReqType.PagingInfo }
     *     
     */
    public GetInquiryTransactionReqType.PagingInfo getPagingInfo() {
        return pagingInfo;
    }

    /**
     * Sets the value of the pagingInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetInquiryTransactionReqType.PagingInfo }
     *     
     */
    public void setPagingInfo(GetInquiryTransactionReqType.PagingInfo value) {
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
     *         &lt;element name="dateFrom" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
     *         &lt;element name="dateTo" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
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
        "dateFrom",
        "dateTo"
    })
    public static class Date {

        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar dateFrom;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar dateTo;

        /**
         * Gets the value of the dateFrom property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDateFrom() {
            return dateFrom;
        }

        /**
         * Sets the value of the dateFrom property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDateFrom(XMLGregorianCalendar value) {
            this.dateFrom = value;
        }

        /**
         * Gets the value of the dateTo property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDateTo() {
            return dateTo;
        }

        /**
         * Sets the value of the dateTo property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDateTo(XMLGregorianCalendar value) {
            this.dateTo = value;
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
     *         &lt;element name="totalResult" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
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
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0")
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

}
