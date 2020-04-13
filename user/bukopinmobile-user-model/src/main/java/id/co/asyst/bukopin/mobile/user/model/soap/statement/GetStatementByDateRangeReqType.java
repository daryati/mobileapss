
package id.co.asyst.bukopin.mobile.user.model.soap.statement;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for GetStatementByDateRangeReqType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetStatementByDateRangeReqType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="accType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dateFrom" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="dateTo" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="pagingInfo" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="startIndex" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *                   &lt;element name="itemsPerPage" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *                   &lt;element name="totalResult" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
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
@XmlType(name = "GetStatementByDateRangeReqType", propOrder = {
    "accNo",
    "accType",
    "dateFrom",
    "dateTo",
    "pagingInfo"
})
public class GetStatementByDateRangeReqType {

    @XmlElement(required = true)
    protected String accNo;
    @XmlElement(required = true)
    protected String accType;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateFrom;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateTo;
    protected GetStatementByDateRangeReqType.PagingInfo pagingInfo;

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
     *     {@link String }
     *     
     */
    public String getAccType() {
        return accType;
    }

    /**
     * Sets the value of the accType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccType(String value) {
        this.accType = value;
    }

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

    /**
     * Gets the value of the pagingInfo property.
     * 
     * @return
     *     possible object is
     *     {@link GetStatementByDateRangeReqType.PagingInfo }
     *     
     */
    public GetStatementByDateRangeReqType.PagingInfo getPagingInfo() {
        return pagingInfo;
    }

    /**
     * Sets the value of the pagingInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetStatementByDateRangeReqType.PagingInfo }
     *     
     */
    public void setPagingInfo(GetStatementByDateRangeReqType.PagingInfo value) {
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
     *         &lt;element name="startIndex" type="{http://www.w3.org/2001/XMLSchema}integer"/>
     *         &lt;element name="itemsPerPage" type="{http://www.w3.org/2001/XMLSchema}integer"/>
     *         &lt;element name="totalResult" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
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
