
package id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for currencyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="currencyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="currencycode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="currencyname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currencydescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "currencyType", namespace = "http://xml.asyst.co.id/cbs/exchangerate", propOrder = {
    "currencycode",
    "currencyname",
    "currencydescription"
})
public class CurrencyType {

    @XmlElement(required = true)
    protected String currencycode;
    protected String currencyname;
    protected String currencydescription;

    /**
     * Gets the value of the currencycode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencycode() {
        return currencycode;
    }

    /**
     * Sets the value of the currencycode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencycode(String value) {
        this.currencycode = value;
    }

    /**
     * Gets the value of the currencyname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyname() {
        return currencyname;
    }

    /**
     * Sets the value of the currencyname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyname(String value) {
        this.currencyname = value;
    }

    /**
     * Gets the value of the currencydescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencydescription() {
        return currencydescription;
    }

    /**
     * Sets the value of the currencydescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencydescription(String value) {
        this.currencydescription = value;
    }

}
