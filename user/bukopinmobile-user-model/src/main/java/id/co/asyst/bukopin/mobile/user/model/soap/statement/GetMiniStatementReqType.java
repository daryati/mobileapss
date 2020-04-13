
package id.co.asyst.bukopin.mobile.user.model.soap.statement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetMiniStatementReqType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetMiniStatementReqType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="accType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetMiniStatementReqType", namespace = "http://xml.asyst.co.id/cbs/listing/statement/getministatement/v1_0", propOrder = {
    "accNo",
    "accType"
})
public class GetMiniStatementReqType {

    @XmlElement(required = true, namespace = "http://xml.asyst.co.id/cbs/listing/statement/getministatement/v1_0")
    protected String accNo;
    @XmlElement(required = true, namespace = "http://xml.asyst.co.id/cbs/listing/statement/getministatement/v1_0")
    protected String accType;

    /**
     * Gets the value of the accNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccNo() {
        return accNo;
    }

    /**
     * Sets the value of the accNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccNo(String value) {
        this.accNo = value;
    }

    /**
     * Gets the value of the accType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccType() {
        return accType;
    }

    /**
     * Sets the value of the accType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccType(String value) {
        this.accType = value;
    }

}
