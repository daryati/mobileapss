
package id.co.asyst.bukopin.mobile.user.model.soap.cif;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for HeaderRS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HeaderRS"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ResDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="ClientTxnID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="IsLastTxn" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="Session" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="SessionID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="SequenceNumber" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ResCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HeaderRS", namespace = "http://xml.asyst.co.id/commons/ws/header", propOrder = {
    "resDateTime",
    "clientTxnID",
    "isLastTxn",
    "session",
    "resCode"
})
public class HeaderRS {

    @XmlElement(name = "ResDateTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar resDateTime;
    @XmlElement(name = "ClientTxnID", required = true)
    protected String clientTxnID;
    @XmlElement(name = "IsLastTxn")
    protected Boolean isLastTxn;
    @XmlElement(name = "Session")
    protected HeaderRS.Session session;
    @XmlElement(name = "ResCode", required = true)
    protected String resCode;

    /**
     * Gets the value of the resDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getResDateTime() {
        return resDateTime;
    }

    /**
     * Sets the value of the resDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setResDateTime(XMLGregorianCalendar value) {
        this.resDateTime = value;
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
     * Gets the value of the isLastTxn property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsLastTxn() {
        return isLastTxn;
    }

    /**
     * Sets the value of the isLastTxn property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsLastTxn(Boolean value) {
        this.isLastTxn = value;
    }

    /**
     * Gets the value of the session property.
     * 
     * @return
     *     possible object is
     *     {@link HeaderRS.Session }
     *     
     */
    public HeaderRS.Session getSession() {
        return session;
    }

    /**
     * Sets the value of the session property.
     * 
     * @param value
     *     allowed object is
     *     {@link HeaderRS.Session }
     *     
     */
    public void setSession(HeaderRS.Session value) {
        this.session = value;
    }

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
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="SessionID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="SequenceNumber" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
        "sessionID",
        "sequenceNumber"
    })
    public static class Session {

        @XmlElement(name = "SessionID", namespace = "http://xml.asyst.co.id/commons/ws/header", required = true)
        protected String sessionID;
        @XmlElement(name = "SequenceNumber", namespace = "http://xml.asyst.co.id/commons/ws/header")
        protected int sequenceNumber;

        /**
         * Gets the value of the sessionID property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSessionID() {
            return sessionID;
        }

        /**
         * Sets the value of the sessionID property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSessionID(String value) {
            this.sessionID = value;
        }

        /**
         * Gets the value of the sequenceNumber property.
         * 
         */
        public int getSequenceNumber() {
            return sequenceNumber;
        }

        /**
         * Sets the value of the sequenceNumber property.
         * 
         */
        public void setSequenceNumber(int value) {
            this.sequenceNumber = value;
        }

    }

}
