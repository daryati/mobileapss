
package id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for responsemessageType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="responsemessageType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="responsecode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="errormessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responsemessageType", propOrder = {
    "responsecode",
    "errormessage"
})
public class ResponsemessageType {

    @XmlElement(required = true)
    protected String responsecode;
    protected String errormessage;

    /**
     * Gets the value of the responsecode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponsecode() {
        return responsecode;
    }

    /**
     * Sets the value of the responsecode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponsecode(String value) {
        this.responsecode = value;
    }

    /**
     * Gets the value of the errormessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrormessage() {
        return errormessage;
    }

    /**
     * Sets the value of the errormessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrormessage(String value) {
        this.errormessage = value;
    }

}
