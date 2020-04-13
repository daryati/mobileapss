
package id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element ref="{http://xml.asyst.co.id/cbs/exchangerate}currency"/>
 *         &lt;element ref="{http://xml.asyst.co.id/cbs/exchangerate}exchangerate" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reqexchangerate", namespace = "http://xml.asyst.co.id/cbs/exchangerate", propOrder = {
    "currency",
    "exchangerate"
})
@XmlRootElement(name = "reqexchangerate")
public class Reqexchangerate {

    @XmlElement(required = true)
    protected CurrencyType currency;
    protected ExchangerateType exchangerate;

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link CurrencyType }
     *     
     */
    public CurrencyType getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link CurrencyType }
     *     
     */
    public void setCurrency(CurrencyType value) {
        this.currency = value;
    }

    /**
     * Gets the value of the exchangerate property.
     * 
     * @return
     *     possible object is
     *     {@link ExchangerateType }
     *     
     */
    public ExchangerateType getExchangerate() {
        return exchangerate;
    }

    /**
     * Sets the value of the exchangerate property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExchangerateType }
     *     
     */
    public void setExchangerate(ExchangerateType value) {
        this.exchangerate = value;
    }

}
