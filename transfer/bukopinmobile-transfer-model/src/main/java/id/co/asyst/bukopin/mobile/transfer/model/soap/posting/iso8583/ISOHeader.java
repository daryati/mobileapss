
package id.co.asyst.bukopin.mobile.transfer.model.soap.posting.iso8583;

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
 *         &lt;element ref="{http://xml.asyst.co.id/commons/iso8583}MTI"/>
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
    "mti"
})
@XmlRootElement(name = "ISOHeader")
public class ISOHeader {

    @XmlElement(name = "MTI", required = true)
    protected String mti;

    /**
     * Gets the value of the mti property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMTI() {
        return mti;
    }

    /**
     * Sets the value of the mti property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMTI(String value) {
        this.mti = value;
    }

}
