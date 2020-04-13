
package id.co.asyst.bukopin.mobile.transfer.model.soap.posting;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for HeaderRQ complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HeaderRQ">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ReqDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="ClientTxnID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IsLastTxn" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="Credentials">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ClientID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Identity" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="UserID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Session" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="SessionID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="SequenceNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HeaderRQ", namespace="http://xml.asyst.co.id/commons/ws/header", propOrder = {
    "reqDateTime",
    "clientTxnID",
    "isLastTxn",
    "credentials"
})
public class HeaderRQ {

    @XmlElement(name = "ReqDateTime", required = true, namespace="http://xml.asyst.co.id/commons/ws/header")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar reqDateTime;
    @XmlElement(name = "ClientTxnID", required = true, namespace="http://xml.asyst.co.id/commons/ws/header")
    protected String clientTxnID;
    @XmlElement(name = "IsLastTxn", namespace="http://xml.asyst.co.id/commons/ws/header")
    protected Boolean isLastTxn;
    @XmlElement(name = "Credentials", required = true, namespace="http://xml.asyst.co.id/commons/ws/header")
    protected HeaderRQ.Credentials credentials;

    /**
     * Gets the value of the reqDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getReqDateTime() {
        return reqDateTime;
    }

    /**
     * Sets the value of the reqDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setReqDateTime(XMLGregorianCalendar value) {
        this.reqDateTime = value;
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
     * Gets the value of the credentials property.
     * 
     * @return
     *     possible object is
     *     {@link HeaderRQ.Credentials }
     *     
     */
    public HeaderRQ.Credentials getCredentials() {
        return credentials;
    }

    /**
     * Sets the value of the credentials property.
     * 
     * @param value
     *     allowed object is
     *     {@link HeaderRQ.Credentials }
     *     
     */
    public void setCredentials(HeaderRQ.Credentials value) {
        this.credentials = value;
    }


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
     *         &lt;element name="ClientID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Identity" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="UserID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="Session" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="SessionID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="SequenceNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "clientID",
        "identity",
        "session"
    })
    public static class Credentials {

        @XmlElement(name = "ClientID", required = true, namespace="http://xml.asyst.co.id/commons/ws/header")
        protected String clientID;
        @XmlElement(name = "Identity", namespace="http://xml.asyst.co.id/commons/ws/header")
        protected HeaderRQ.Credentials.Identity identity;
        @XmlElement(name = "Session", namespace="http://xml.asyst.co.id/commons/ws/header")
        protected HeaderRQ.Credentials.Session session;

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
         * Gets the value of the identity property.
         * 
         * @return
         *     possible object is
         *     {@link HeaderRQ.Credentials.Identity }
         *     
         */
        public HeaderRQ.Credentials.Identity getIdentity() {
            return identity;
        }

        /**
         * Sets the value of the identity property.
         * 
         * @param value
         *     allowed object is
         *     {@link HeaderRQ.Credentials.Identity }
         *     
         */
        public void setIdentity(HeaderRQ.Credentials.Identity value) {
            this.identity = value;
        }

        /**
         * Gets the value of the session property.
         * 
         * @return
         *     possible object is
         *     {@link HeaderRQ.Credentials.Session }
         *     
         */
        public HeaderRQ.Credentials.Session getSession() {
            return session;
        }

        /**
         * Sets the value of the session property.
         * 
         * @param value
         *     allowed object is
         *     {@link HeaderRQ.Credentials.Session }
         *     
         */
        public void setSession(HeaderRQ.Credentials.Session value) {
            this.session = value;
        }


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
         *         &lt;element name="UserID" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
            "userID",
            "password"
        })
        public static class Identity {

            @XmlElement(name = "UserID", required = true)
            protected String userID;
            @XmlElement(name = "Password", required = true)
            protected String password;

            /**
             * Gets the value of the userID property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUserID() {
                return userID;
            }

            /**
             * Sets the value of the userID property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUserID(String value) {
                this.userID = value;
            }

            /**
             * Gets the value of the password property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPassword() {
                return password;
            }

            /**
             * Sets the value of the password property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPassword(String value) {
                this.password = value;
            }

        }


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
         *         &lt;element name="SessionID" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="SequenceNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
            "sessionID",
            "sequenceNumber"
        })
        public static class Session {

            @XmlElement(name = "SessionID", required = true)
            protected String sessionID;
            @XmlElement(name = "SequenceNumber")
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

}
