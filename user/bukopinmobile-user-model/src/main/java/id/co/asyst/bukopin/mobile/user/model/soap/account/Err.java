
package id.co.asyst.bukopin.mobile.user.model.soap.account;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="ErrCat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ErrCatDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ErrCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ErrDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ErrSev" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ErrSrc" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ErrTgt" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ErrDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="ErrDetail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element ref="{http://xml.asyst.co.id/commons/ws/fault}BWErrDetails" minOccurs="0"/&gt;
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
    "errCat",
    "errCatDesc",
    "errCode",
    "errDesc",
    "errSev",
    "errSrc",
    "errTgt",
    "errDateTime",
    "errDetail",
    "bwErrDetails"
})
@XmlRootElement(name = "Err")
public class Err {

    @XmlElement(name = "ErrCat")
    protected String errCat;
    @XmlElement(name = "ErrCatDesc")
    protected String errCatDesc;
    @XmlElement(name = "ErrCode")
    protected String errCode;
    @XmlElement(name = "ErrDesc")
    protected String errDesc;
    @XmlElement(name = "ErrSev")
    protected String errSev;
    @XmlElement(name = "ErrSrc", required = true)
    protected String errSrc;
    @XmlElement(name = "ErrTgt", required = true)
    protected String errTgt;
    @XmlElement(name = "ErrDateTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar errDateTime;
    @XmlElement(name = "ErrDetail")
    protected String errDetail;
    @XmlElement(name = "BWErrDetails")
    protected BWErrDetails bwErrDetails;

    /**
     * Gets the value of the errCat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrCat() {
        return errCat;
    }

    /**
     * Sets the value of the errCat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrCat(String value) {
        this.errCat = value;
    }

    /**
     * Gets the value of the errCatDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrCatDesc() {
        return errCatDesc;
    }

    /**
     * Sets the value of the errCatDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrCatDesc(String value) {
        this.errCatDesc = value;
    }

    /**
     * Gets the value of the errCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrCode() {
        return errCode;
    }

    /**
     * Sets the value of the errCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrCode(String value) {
        this.errCode = value;
    }

    /**
     * Gets the value of the errDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrDesc() {
        return errDesc;
    }

    /**
     * Sets the value of the errDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrDesc(String value) {
        this.errDesc = value;
    }

    /**
     * Gets the value of the errSev property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrSev() {
        return errSev;
    }

    /**
     * Sets the value of the errSev property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrSev(String value) {
        this.errSev = value;
    }

    /**
     * Gets the value of the errSrc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrSrc() {
        return errSrc;
    }

    /**
     * Sets the value of the errSrc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrSrc(String value) {
        this.errSrc = value;
    }

    /**
     * Gets the value of the errTgt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrTgt() {
        return errTgt;
    }

    /**
     * Sets the value of the errTgt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrTgt(String value) {
        this.errTgt = value;
    }

    /**
     * Gets the value of the errDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getErrDateTime() {
        return errDateTime;
    }

    /**
     * Sets the value of the errDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setErrDateTime(XMLGregorianCalendar value) {
        this.errDateTime = value;
    }

    /**
     * Gets the value of the errDetail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrDetail() {
        return errDetail;
    }

    /**
     * Sets the value of the errDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrDetail(String value) {
        this.errDetail = value;
    }

    /**
     * Gets the value of the bwErrDetails property.
     * 
     * @return
     *     possible object is
     *     {@link BWErrDetails }
     *     
     */
    public BWErrDetails getBWErrDetails() {
        return bwErrDetails;
    }

    /**
     * Sets the value of the bwErrDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link BWErrDetails }
     *     
     */
    public void setBWErrDetails(BWErrDetails value) {
        this.bwErrDetails = value;
    }

}
