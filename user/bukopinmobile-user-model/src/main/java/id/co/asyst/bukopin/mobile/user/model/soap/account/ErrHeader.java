
package id.co.asyst.bukopin.mobile.user.model.soap.account;

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
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="BWTxnID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ClientTxnID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ClientID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="BWProcess" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="BWActivity" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "bwTxnID",
    "clientTxnID",
    "clientID",
    "bwProcess",
    "bwActivity"
})
@XmlRootElement(name = "ErrHeader")
public class ErrHeader {

    @XmlElement(name = "BWTxnID", required = true)
    protected String bwTxnID;
    @XmlElement(name = "ClientTxnID", required = true)
    protected String clientTxnID;
    @XmlElement(name = "ClientID", required = true)
    protected String clientID;
    @XmlElement(name = "BWProcess", required = true)
    protected String bwProcess;
    @XmlElement(name = "BWActivity", required = true)
    protected String bwActivity;

    /**
     * Gets the value of the bwTxnID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBWTxnID() {
        return bwTxnID;
    }

    /**
     * Sets the value of the bwTxnID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBWTxnID(String value) {
        this.bwTxnID = value;
    }

    /**
     * Gets the value of the clientTxnID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientTxnID() {
        return clientTxnID;
    }

    /**
     * Sets the value of the clientTxnID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientTxnID(String value) {
        this.clientTxnID = value;
    }

    /**
     * Gets the value of the clientID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientID() {
        return clientID;
    }

    /**
     * Sets the value of the clientID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientID(String value) {
        this.clientID = value;
    }

    /**
     * Gets the value of the bwProcess property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBWProcess() {
        return bwProcess;
    }

    /**
     * Sets the value of the bwProcess property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBWProcess(String value) {
        this.bwProcess = value;
    }

    /**
     * Gets the value of the bwActivity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBWActivity() {
        return bwActivity;
    }

    /**
     * Sets the value of the bwActivity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBWActivity(String value) {
        this.bwActivity = value;
    }

}
