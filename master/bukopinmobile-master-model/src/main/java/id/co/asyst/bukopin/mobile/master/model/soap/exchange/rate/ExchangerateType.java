
package id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for exchangerateType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="exchangerateType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="kurstax" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="kursrevaluasi" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="kursusdbaserate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="kursbanknotebuy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="kursbanknotesell" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="kursttbuy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="kursttsell" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="kursmailtransferbuy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="kursmailtransfersell" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="kurschequesbuy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="kurschequessell" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "exchangerateType", namespace = "http://xml.asyst.co.id/cbs/exchangerate", propOrder = {
    "kurstax",
    "kursrevaluasi",
    "kursusdbaserate",
    "kursbanknotebuy",
    "kursbanknotesell",
    "kursttbuy",
    "kursttsell",
    "kursmailtransferbuy",
    "kursmailtransfersell",
    "kurschequesbuy",
    "kurschequessell"
})
public class ExchangerateType {

    @XmlElement(required = true)
    protected String kurstax;
    @XmlElement(required = true)
    protected String kursrevaluasi;
    @XmlElement(required = true)
    protected String kursusdbaserate;
    @XmlElement(required = true)
    protected String kursbanknotebuy;
    @XmlElement(required = true)
    protected String kursbanknotesell;
    @XmlElement(required = true)
    protected String kursttbuy;
    @XmlElement(required = true)
    protected String kursttsell;
    @XmlElement(required = true)
    protected String kursmailtransferbuy;
    @XmlElement(required = true)
    protected String kursmailtransfersell;
    @XmlElement(required = true)
    protected String kurschequesbuy;
    @XmlElement(required = true)
    protected String kurschequessell;

    /**
     * Gets the value of the kurstax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKurstax() {
        return kurstax;
    }

    /**
     * Sets the value of the kurstax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKurstax(String value) {
        this.kurstax = value;
    }

    /**
     * Gets the value of the kursrevaluasi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKursrevaluasi() {
        return kursrevaluasi;
    }

    /**
     * Sets the value of the kursrevaluasi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKursrevaluasi(String value) {
        this.kursrevaluasi = value;
    }

    /**
     * Gets the value of the kursusdbaserate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKursusdbaserate() {
        return kursusdbaserate;
    }

    /**
     * Sets the value of the kursusdbaserate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKursusdbaserate(String value) {
        this.kursusdbaserate = value;
    }

    /**
     * Gets the value of the kursbanknotebuy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKursbanknotebuy() {
        return kursbanknotebuy;
    }

    /**
     * Sets the value of the kursbanknotebuy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKursbanknotebuy(String value) {
        this.kursbanknotebuy = value;
    }

    /**
     * Gets the value of the kursbanknotesell property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKursbanknotesell() {
        return kursbanknotesell;
    }

    /**
     * Sets the value of the kursbanknotesell property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKursbanknotesell(String value) {
        this.kursbanknotesell = value;
    }

    /**
     * Gets the value of the kursttbuy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKursttbuy() {
        return kursttbuy;
    }

    /**
     * Sets the value of the kursttbuy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKursttbuy(String value) {
        this.kursttbuy = value;
    }

    /**
     * Gets the value of the kursttsell property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKursttsell() {
        return kursttsell;
    }

    /**
     * Sets the value of the kursttsell property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKursttsell(String value) {
        this.kursttsell = value;
    }

    /**
     * Gets the value of the kursmailtransferbuy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKursmailtransferbuy() {
        return kursmailtransferbuy;
    }

    /**
     * Sets the value of the kursmailtransferbuy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKursmailtransferbuy(String value) {
        this.kursmailtransferbuy = value;
    }

    /**
     * Gets the value of the kursmailtransfersell property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKursmailtransfersell() {
        return kursmailtransfersell;
    }

    /**
     * Sets the value of the kursmailtransfersell property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKursmailtransfersell(String value) {
        this.kursmailtransfersell = value;
    }

    /**
     * Gets the value of the kurschequesbuy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKurschequesbuy() {
        return kurschequesbuy;
    }

    /**
     * Sets the value of the kurschequesbuy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKurschequesbuy(String value) {
        this.kurschequesbuy = value;
    }

    /**
     * Gets the value of the kurschequessell property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKurschequessell() {
        return kurschequessell;
    }

    /**
     * Sets the value of the kurschequessell property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKurschequessell(String value) {
        this.kurschequessell = value;
    }

}
