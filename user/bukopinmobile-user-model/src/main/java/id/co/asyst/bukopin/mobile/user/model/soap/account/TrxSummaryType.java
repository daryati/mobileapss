
package id.co.asyst.bukopin.mobile.user.model.soap.account;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for trxSummaryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="trxSummaryType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="totalDebitAmt"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="totalCreditAmt"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="endingBalance"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="startingBalance"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "trxSummaryType", namespace = "http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0", propOrder = {
    "totalDebitAmt",
    "totalCreditAmt",
    "endingBalance",
    "startingBalance"
})
public class TrxSummaryType {

    @XmlElement(required = true)
    protected BigDecimal totalDebitAmt;
    @XmlElement(required = true)
    protected BigDecimal totalCreditAmt;
    @XmlElement(required = true)
    protected BigDecimal endingBalance;
    @XmlElement(required = true)
    protected BigDecimal startingBalance;

    /**
     * Gets the value of the totalDebitAmt property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalDebitAmt() {
        return totalDebitAmt;
    }

    /**
     * Sets the value of the totalDebitAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalDebitAmt(BigDecimal value) {
        this.totalDebitAmt = value;
    }

    /**
     * Gets the value of the totalCreditAmt property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalCreditAmt() {
        return totalCreditAmt;
    }

    /**
     * Sets the value of the totalCreditAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalCreditAmt(BigDecimal value) {
        this.totalCreditAmt = value;
    }

    /**
     * Gets the value of the endingBalance property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getEndingBalance() {
        return endingBalance;
    }

    /**
     * Sets the value of the endingBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setEndingBalance(BigDecimal value) {
        this.endingBalance = value;
    }

    /**
     * Gets the value of the startingBalance property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getStartingBalance() {
        return startingBalance;
    }

    /**
     * Sets the value of the startingBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setStartingBalance(BigDecimal value) {
        this.startingBalance = value;
    }

}
