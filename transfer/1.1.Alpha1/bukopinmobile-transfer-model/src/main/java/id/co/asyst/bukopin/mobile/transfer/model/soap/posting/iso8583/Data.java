
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
 *         &lt;element ref="{http://xml.asyst.co.id/commons/iso8583}BinaryData" minOccurs="0"/>
 *         &lt;element ref="{http://xml.asyst.co.id/commons/iso8583}StringData" minOccurs="0"/>
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
    "binaryData",
    "stringData"
})
@XmlRootElement(name = "Data")
public class Data {

    @XmlElement(name = "BinaryData")
    protected String binaryData;
    @XmlElement(name = "StringData")
    protected String stringData;

    /**
     * Gets the value of the binaryData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBinaryData() {
        return binaryData;
    }

    /**
     * Sets the value of the binaryData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBinaryData(String value) {
        this.binaryData = value;
    }

    /**
     * Gets the value of the stringData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStringData() {
        return stringData;
    }

    /**
     * Sets the value of the stringData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStringData(String value) {
        this.stringData = value;
    }

}
