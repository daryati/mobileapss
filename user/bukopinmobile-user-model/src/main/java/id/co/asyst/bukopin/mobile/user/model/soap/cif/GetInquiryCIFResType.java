
package id.co.asyst.bukopin.mobile.user.model.soap.cif;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for GetInquiryCIFResType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetInquiryCIFResType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="response"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="rescode"&gt;
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
 *                   &lt;element name="cifnumber"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="0"/&gt;
 *                         &lt;maxLength value="20"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="firstname" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="0"/&gt;
 *                         &lt;maxLength value="60"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="middlename" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="0"/&gt;
 *                         &lt;maxLength value="10"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="lastname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="mothername" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="alias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="birthdate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *                   &lt;element name="mobilephone" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="0"/&gt;
 *                         &lt;maxLength value="50"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="homeaddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="homepostcode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *                   &lt;element name="homecity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="homephone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="totalaccount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *                   &lt;element name="customerid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="accounts" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="accnumber" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                   &lt;element name="acctype" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                   &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                   &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                   &lt;element name="accname"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;minLength value="1"/&gt;
 *                         &lt;maxLength value="40"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="accbranch" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                   &lt;element name="acclocation" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                   &lt;element name="productid" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="paginginfo" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="startindex" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                   &lt;element name="itemsperpage" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                   &lt;element name="totalresult" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
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
@XmlType(name = "GetInquiryCIFResType", namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0", propOrder = {
    "response",
    "accInfo",
    "accounts",
    "paginginfo"
})
public class GetInquiryCIFResType {

    @XmlElement(required = true)
    protected GetInquiryCIFResType.Response response;
    @XmlElement(required = true)
    protected GetInquiryCIFResType.AccInfo accInfo;
    protected List<GetInquiryCIFResType.Accounts> accounts;
    protected GetInquiryCIFResType.Paginginfo paginginfo;

    /**
     * Gets the value of the response property.
     * 
     * @return
     *     possible object is
     *     {@link GetInquiryCIFResType.Response }
     *     
     */
    public GetInquiryCIFResType.Response getResponse() {
        return response;
    }

    /**
     * Sets the value of the response property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetInquiryCIFResType.Response }
     *     
     */
    public void setResponse(GetInquiryCIFResType.Response value) {
        this.response = value;
    }

    /**
     * Gets the value of the accInfo property.
     * 
     * @return
     *     possible object is
     *     {@link GetInquiryCIFResType.AccInfo }
     *     
     */
    public GetInquiryCIFResType.AccInfo getAccInfo() {
        return accInfo;
    }

    /**
     * Sets the value of the accInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetInquiryCIFResType.AccInfo }
     *     
     */
    public void setAccInfo(GetInquiryCIFResType.AccInfo value) {
        this.accInfo = value;
    }

    /**
     * Gets the value of the accounts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accounts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccounts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GetInquiryCIFResType.Accounts }
     * 
     * 
     */
    public List<GetInquiryCIFResType.Accounts> getAccounts() {
        if (accounts == null) {
            accounts = new ArrayList<GetInquiryCIFResType.Accounts>();
        }
        return this.accounts;
    }

    /**
     * Gets the value of the paginginfo property.
     * 
     * @return
     *     possible object is
     *     {@link GetInquiryCIFResType.Paginginfo }
     *     
     */
    public GetInquiryCIFResType.Paginginfo getPaginginfo() {
        return paginginfo;
    }

    /**
     * Sets the value of the paginginfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetInquiryCIFResType.Paginginfo }
     *     
     */
    public void setPaginginfo(GetInquiryCIFResType.Paginginfo value) {
        this.paginginfo = value;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "GetInquiryCIFResType [response=" + response + ", accInfo=" + accInfo + ", accounts=" + accounts
		+ ", paginginfo=" + paginginfo + "]";
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
     *         &lt;element name="cifnumber"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="0"/&gt;
     *               &lt;maxLength value="20"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="firstname" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="0"/&gt;
     *               &lt;maxLength value="60"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="middlename" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="0"/&gt;
     *               &lt;maxLength value="10"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="lastname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="mothername" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="alias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="birthdate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
     *         &lt;element name="mobilephone" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="0"/&gt;
     *               &lt;maxLength value="50"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="homeaddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="homepostcode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
     *         &lt;element name="homecity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="homephone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="totalaccount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
     *         &lt;element name="customerid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
        "cifnumber",
        "firstname",
        "middlename",
        "lastname",
        "mothername",
        "alias",
        "birthdate",
        "mobilephone",
        "email",
        "homeaddress",
        "homepostcode",
        "homecity",
        "homephone",
        "totalaccount",
        "customerid"
    })
    public static class AccInfo {

        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0", required = true)
        protected String cifnumber;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0")
        protected String firstname;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0")
        protected String middlename;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0")
        protected String lastname;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0")
        protected String mothername;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0")
        protected String alias;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0")
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar birthdate;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0")
        protected String mobilephone;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0")
        protected String email;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0")
        protected String homeaddress;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0")
        protected Integer homepostcode;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0")
        protected String homecity;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0")
        protected String homephone;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0")
        protected Integer totalaccount;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0")
        protected String customerid;

        /**
         * Gets the value of the cifnumber property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCifnumber() {
            return cifnumber;
        }

        /**
         * Sets the value of the cifnumber property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCifnumber(String value) {
            this.cifnumber = value;
        }

        /**
         * Gets the value of the firstname property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFirstname() {
            return firstname;
        }

        /**
         * Sets the value of the firstname property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFirstname(String value) {
            this.firstname = value;
        }

        /**
         * Gets the value of the middlename property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMiddlename() {
            return middlename;
        }

        /**
         * Sets the value of the middlename property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMiddlename(String value) {
            this.middlename = value;
        }

        /**
         * Gets the value of the lastname property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLastname() {
            return lastname;
        }

        /**
         * Sets the value of the lastname property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLastname(String value) {
            this.lastname = value;
        }

        /**
         * Gets the value of the mothername property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMothername() {
            return mothername;
        }

        /**
         * Sets the value of the mothername property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMothername(String value) {
            this.mothername = value;
        }

        /**
         * Gets the value of the alias property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAlias() {
            return alias;
        }

        /**
         * Sets the value of the alias property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAlias(String value) {
            this.alias = value;
        }

        /**
         * Gets the value of the birthdate property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getBirthdate() {
            return birthdate;
        }

        /**
         * Sets the value of the birthdate property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setBirthdate(XMLGregorianCalendar value) {
            this.birthdate = value;
        }

        /**
         * Gets the value of the mobilephone property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMobilephone() {
            return mobilephone;
        }

        /**
         * Sets the value of the mobilephone property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMobilephone(String value) {
            this.mobilephone = value;
        }

        /**
         * Gets the value of the email property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEmail() {
            return email;
        }

        /**
         * Sets the value of the email property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEmail(String value) {
            this.email = value;
        }

        /**
         * Gets the value of the homeaddress property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHomeaddress() {
            return homeaddress;
        }

        /**
         * Sets the value of the homeaddress property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHomeaddress(String value) {
            this.homeaddress = value;
        }

        /**
         * Gets the value of the homepostcode property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getHomepostcode() {
            return homepostcode;
        }

        /**
         * Sets the value of the homepostcode property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setHomepostcode(Integer value) {
            this.homepostcode = value;
        }

        /**
         * Gets the value of the homecity property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHomecity() {
            return homecity;
        }

        /**
         * Sets the value of the homecity property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHomecity(String value) {
            this.homecity = value;
        }

        /**
         * Gets the value of the homephone property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHomephone() {
            return homephone;
        }

        /**
         * Sets the value of the homephone property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHomephone(String value) {
            this.homephone = value;
        }

        /**
         * Gets the value of the totalaccount property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getTotalaccount() {
            return totalaccount;
        }

        /**
         * Sets the value of the totalaccount property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setTotalaccount(Integer value) {
            this.totalaccount = value;
        }

        /**
         * Gets the value of the customerid property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCustomerid() {
            return customerid;
        }

        /**
         * Sets the value of the customerid property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCustomerid(String value) {
            this.customerid = value;
        }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	    return "AccInfo [cifnumber=" + cifnumber + ", firstname=" + firstname + ", middlename=" + middlename
		    + ", lastname=" + lastname + ", mothername=" + mothername + ", alias=" + alias + ", birthdate="
		    + birthdate + ", mobilephone=" + mobilephone + ", email=" + email + ", homeaddress=" + homeaddress
		    + ", homepostcode=" + homepostcode + ", homecity=" + homecity + ", homephone=" + homephone
		    + ", totalaccount=" + totalaccount + ", customerid=" + customerid + "]";
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
     *         &lt;element name="accnumber" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
     *         &lt;element name="acctype" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *         &lt;element name="accname"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;minLength value="1"/&gt;
     *               &lt;maxLength value="40"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="accbranch" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *         &lt;element name="acclocation" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *         &lt;element name="productid" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
        "accnumber",
        "acctype",
        "currency",
        "status",
        "accname",
        "accbranch",
        "acclocation",
        "productid"
    })
    public static class Accounts {

        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0", required = true)
        protected BigInteger accnumber;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0")
        protected int acctype;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0")
        protected int currency;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0")
        protected int status;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0", required = true)
        protected String accname;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0")
        protected int accbranch;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0")
        protected int acclocation;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0")
        protected int productid;

        /**
         * Gets the value of the accnumber property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getAccnumber() {
            return accnumber;
        }

        /**
         * Sets the value of the accnumber property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setAccnumber(BigInteger value) {
            this.accnumber = value;
        }

        /**
         * Gets the value of the acctype property.
         * 
         */
        public int getAcctype() {
            return acctype;
        }

        /**
         * Sets the value of the acctype property.
         * 
         */
        public void setAcctype(int value) {
            this.acctype = value;
        }

        /**
         * Gets the value of the currency property.
         * 
         */
        public int getCurrency() {
            return currency;
        }

        /**
         * Sets the value of the currency property.
         * 
         */
        public void setCurrency(int value) {
            this.currency = value;
        }

        /**
         * Gets the value of the status property.
         * 
         */
        public int getStatus() {
            return status;
        }

        /**
         * Sets the value of the status property.
         * 
         */
        public void setStatus(int value) {
            this.status = value;
        }

        /**
         * Gets the value of the accname property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAccname() {
            return accname;
        }

        /**
         * Sets the value of the accname property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAccname(String value) {
            this.accname = value;
        }

        /**
         * Gets the value of the accbranch property.
         * 
         */
        public int getAccbranch() {
            return accbranch;
        }

        /**
         * Sets the value of the accbranch property.
         * 
         */
        public void setAccbranch(int value) {
            this.accbranch = value;
        }

        /**
         * Gets the value of the acclocation property.
         * 
         */
        public int getAcclocation() {
            return acclocation;
        }

        /**
         * Sets the value of the acclocation property.
         * 
         */
        public void setAcclocation(int value) {
            this.acclocation = value;
        }

        /**
         * Gets the value of the productid property.
         * 
         */
        public int getProductid() {
            return productid;
        }

        /**
         * Sets the value of the productid property.
         * 
         */
        public void setProductid(int value) {
            this.productid = value;
        }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	    return "Accounts [accnumber=" + accnumber + ", acctype=" + acctype + ", currency=" + currency + ", status="
		    + status + ", accname=" + accname + ", accbranch=" + accbranch + ", acclocation=" + acclocation
		    + ", productid=" + productid + "]";
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
     *         &lt;element name="startindex" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
     *         &lt;element name="itemsperpage" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
     *         &lt;element name="totalresult" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
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
        "startindex",
        "itemsperpage",
        "totalresult"
    })
    public static class Paginginfo {

        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0", required = true)
        protected BigInteger startindex;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0", required = true)
        protected BigInteger itemsperpage;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0", required = true)
        protected BigInteger totalresult;

        /**
         * Gets the value of the startindex property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getStartindex() {
            return startindex;
        }

        /**
         * Sets the value of the startindex property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setStartindex(BigInteger value) {
            this.startindex = value;
        }

        /**
         * Gets the value of the itemsperpage property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getItemsperpage() {
            return itemsperpage;
        }

        /**
         * Sets the value of the itemsperpage property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setItemsperpage(BigInteger value) {
            this.itemsperpage = value;
        }

        /**
         * Gets the value of the totalresult property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getTotalresult() {
            return totalresult;
        }

        /**
         * Sets the value of the totalresult property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setTotalresult(BigInteger value) {
            this.totalresult = value;
        }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	    return "Paginginfo [startindex=" + startindex + ", itemsperpage=" + itemsperpage + ", totalresult="
		    + totalresult + "]";
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
     *         &lt;element name="rescode"&gt;
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
        "rescode",
        "desc"
    })
    public static class Response {

        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0", required = true)
        protected String rescode;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0", required = true)
        protected String desc;

        /**
         * Gets the value of the rescode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRescode() {
            return rescode;
        }

        /**
         * Sets the value of the rescode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRescode(String value) {
            this.rescode = value;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	    return "Response [rescode=" + rescode + ", desc=" + desc + "]";
	}

    }

}
