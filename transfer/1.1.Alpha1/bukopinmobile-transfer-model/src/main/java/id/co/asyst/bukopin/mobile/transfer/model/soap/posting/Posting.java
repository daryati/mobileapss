
package id.co.asyst.bukopin.mobile.transfer.model.soap.posting;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import id.co.asyst.bukopin.mobile.transfer.model.soap.posting.iso8583.ISOMessages;


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
 *         &lt;element ref="{http://xml.asyst.co.id/cbs/posting/proxy}Type" minOccurs="0"/>
 *         &lt;element ref="{http://xml.asyst.co.id/commons/iso8583}ISOMessages" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", namespace="http://xml.asyst.co.id/cbs/posting/proxy", propOrder = {
    "type",
    "isoMessages"
})
@XmlRootElement(name = "Posting")
public class Posting {

    @XmlElement(name = "Type", namespace="http://xml.asyst.co.id/cbs/posting/proxy")
    protected String type;
    @XmlElement(name = "ISOMessages", namespace = "http://xml.asyst.co.id/commons/iso8583")
    protected ISOMessages isoMessages;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the isoMessages property.
     * 
     * @return
     *     possible object is
     *     {@link ISOMessages }
     *     
     */
    public ISOMessages getISOMessages() {
        return isoMessages;
    }

    /**
     * Sets the value of the isoMessages property.
     * 
     * @param value
     *     allowed object is
     *     {@link ISOMessages }
     *     
     */
    public void setISOMessages(ISOMessages value) {
        this.isoMessages = value;
    }

}
