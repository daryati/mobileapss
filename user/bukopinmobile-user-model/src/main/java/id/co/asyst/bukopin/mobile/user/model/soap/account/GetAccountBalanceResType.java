
package id.co.asyst.bukopin.mobile.user.model.soap.account;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetAccountBalanceResType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetAccountBalanceResType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="response"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="resCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
 *         &lt;element name="transaction" maxOccurs="unbounded"&gt;
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
 *                   &lt;element name="currency" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="0"/&gt;
 *                         &lt;maxLength value="10"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="branch" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="0"/&gt;
 *                         &lt;maxLength value="50"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="lokasi" minOccurs="0"&gt;
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
 *                         &lt;maxLength value="25"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="cifNumber" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *                   &lt;element name="cifStatus" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="0"/&gt;
 *                         &lt;maxLength value="10"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="effectiveBalance" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *                   &lt;element name="holdAmt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *                   &lt;element name="availableBalance" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *                   &lt;element name="plafon" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="totalBalance" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="totalEffectiveBalance" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *                   &lt;element name="totalAvailableBalance" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
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
@XmlType(name = "GetAccountBalanceResType", namespace = "http://xml.asyst.co.id/cbs/listing/statement/getaccountbalance/v1_0", propOrder = {
    "response",
    "transaction",
    "totalBalance"
})
public class GetAccountBalanceResType {

    @XmlElement(required = true)
    protected GetAccountBalanceResType.Response response;
    @XmlElement(required = true)
    protected List<GetAccountBalanceResType.Transaction> transaction;
    protected GetAccountBalanceResType.TotalBalance totalBalance;

    /**
     * Gets the value of the response property.
     * 
     * @return
     *     possible object is
     *     {@link GetAccountBalanceResType.Response }
     *     
     */
    public GetAccountBalanceResType.Response getResponse() {
        return response;
    }

    /**
     * Sets the value of the response property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetAccountBalanceResType.Response }
     *     
     */
    public void setResponse(GetAccountBalanceResType.Response value) {
        this.response = value;
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
     * {@link GetAccountBalanceResType.Transaction }
     * 
     * 
     */
    public List<GetAccountBalanceResType.Transaction> getTransaction() {
        if (transaction == null) {
            transaction = new ArrayList<GetAccountBalanceResType.Transaction>();
        }
        return this.transaction;
    }

    /**
     * Gets the value of the totalBalance property.
     * 
     * @return
     *     possible object is
     *     {@link GetAccountBalanceResType.TotalBalance }
     *     
     */
    public GetAccountBalanceResType.TotalBalance getTotalBalance() {
        return totalBalance;
    }

    /**
     * Sets the value of the totalBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetAccountBalanceResType.TotalBalance }
     *     
     */
    public void setTotalBalance(GetAccountBalanceResType.TotalBalance value) {
        this.totalBalance = value;
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
     *         &lt;element name="resCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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

        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getaccountbalance/v1_0", required = true)
        protected String resCode;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getaccountbalance/v1_0", required = true)
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
     *         &lt;element name="totalEffectiveBalance" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
     *         &lt;element name="totalAvailableBalance" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
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
        "totalEffectiveBalance",
        "totalAvailableBalance"
    })
    public static class TotalBalance {

        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getaccountbalance/v1_0", required = true)
        protected BigDecimal totalEffectiveBalance;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getaccountbalance/v1_0", required = true)
        protected BigDecimal totalAvailableBalance;

        /**
         * Gets the value of the totalEffectiveBalance property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getTotalEffectiveBalance() {
            return totalEffectiveBalance;
        }

        /**
         * Sets the value of the totalEffectiveBalance property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setTotalEffectiveBalance(BigDecimal value) {
            this.totalEffectiveBalance = value;
        }

        /**
         * Gets the value of the totalAvailableBalance property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getTotalAvailableBalance() {
            return totalAvailableBalance;
        }

        /**
         * Sets the value of the totalAvailableBalance property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setTotalAvailableBalance(BigDecimal value) {
            this.totalAvailableBalance = value;
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
     *         &lt;element name="currency" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="0"/&gt;
     *               &lt;maxLength value="10"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="branch" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="0"/&gt;
     *               &lt;maxLength value="50"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="lokasi" minOccurs="0"&gt;
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
     *               &lt;maxLength value="25"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="cifNumber" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
     *         &lt;element name="cifStatus" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="0"/&gt;
     *               &lt;maxLength value="10"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="effectiveBalance" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
     *         &lt;element name="holdAmt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
     *         &lt;element name="availableBalance" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
     *         &lt;element name="plafon" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
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
        "lokasi",
        "productID",
        "cifNumber",
        "cifStatus",
        "effectiveBalance",
        "holdAmt",
        "availableBalance",
        "plafon"
    })
    public static class Transaction {

        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getaccountbalance/v1_0", required = true)
        protected String accNo;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getaccountbalance/v1_0", required = true)
        protected BigInteger accType;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getaccountbalance/v1_0")
        protected String name;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getaccountbalance/v1_0")
        protected String accStatus;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getaccountbalance/v1_0")
        protected String currency;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getaccountbalance/v1_0")
        protected String branch;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getaccountbalance/v1_0")
        protected String lokasi;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getaccountbalance/v1_0")
        protected String productID;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getaccountbalance/v1_0")
        protected BigInteger cifNumber;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getaccountbalance/v1_0")
        protected String cifStatus;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getaccountbalance/v1_0")
        protected BigDecimal effectiveBalance;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getaccountbalance/v1_0")
        protected BigDecimal holdAmt;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getaccountbalance/v1_0")
        protected BigDecimal availableBalance;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getaccountbalance/v1_0")
        protected BigDecimal plafon;

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
         * Gets the value of the lokasi property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLokasi() {
            return lokasi;
        }

        /**
         * Sets the value of the lokasi property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLokasi(String value) {
            this.lokasi = value;
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
         * Gets the value of the effectiveBalance property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getEffectiveBalance() {
            return effectiveBalance;
        }

        /**
         * Sets the value of the effectiveBalance property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setEffectiveBalance(BigDecimal value) {
            this.effectiveBalance = value;
        }

        /**
         * Gets the value of the holdAmt property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getHoldAmt() {
            return holdAmt;
        }

        /**
         * Sets the value of the holdAmt property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setHoldAmt(BigDecimal value) {
            this.holdAmt = value;
        }

        /**
         * Gets the value of the availableBalance property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getAvailableBalance() {
            return availableBalance;
        }

        /**
         * Sets the value of the availableBalance property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setAvailableBalance(BigDecimal value) {
            this.availableBalance = value;
        }

        /**
         * Gets the value of the plafon property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getPlafon() {
            return plafon;
        }

        /**
         * Sets the value of the plafon property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setPlafon(BigDecimal value) {
            this.plafon = value;
        }

    }

}
