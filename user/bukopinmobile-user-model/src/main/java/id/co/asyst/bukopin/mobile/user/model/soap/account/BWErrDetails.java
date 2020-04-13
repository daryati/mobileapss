
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
 *         &lt;element ref="{http://xml.asyst.co.id/commons/ws/fault}ErrHeader" minOccurs="0"/&gt;
 *         &lt;element ref="{http://xml.asyst.co.id/commons/ws/fault}ErrReport" minOccurs="0"/&gt;
 *         &lt;element ref="{http://xml.asyst.co.id/commons/ws/fault}ProviderErrs" minOccurs="0"/&gt;
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
    "errHeader",
    "errReport",
    "providerErrs"
})
@XmlRootElement(name = "BWErrDetails")
public class BWErrDetails {

    @XmlElement(name = "ErrHeader")
    protected ErrHeader errHeader;
    @XmlElement(name = "ErrReport")
    protected ErrReport errReport;
    @XmlElement(name = "ProviderErrs")
    protected ProviderErrs providerErrs;

    /**
     * Gets the value of the errHeader property.
     * 
     * @return
     *     possible object is
     *     {@link ErrHeader }
     *     
     */
    public ErrHeader getErrHeader() {
        return errHeader;
    }

    /**
     * Sets the value of the errHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link ErrHeader }
     *     
     */
    public void setErrHeader(ErrHeader value) {
        this.errHeader = value;
    }

    /**
     * Gets the value of the errReport property.
     * 
     * @return
     *     possible object is
     *     {@link ErrReport }
     *     
     */
    public ErrReport getErrReport() {
        return errReport;
    }

    /**
     * Sets the value of the errReport property.
     * 
     * @param value
     *     allowed object is
     *     {@link ErrReport }
     *     
     */
    public void setErrReport(ErrReport value) {
        this.errReport = value;
    }

    /**
     * Gets the value of the providerErrs property.
     * 
     * @return
     *     possible object is
     *     {@link ProviderErrs }
     *     
     */
    public ProviderErrs getProviderErrs() {
        return providerErrs;
    }

    /**
     * Sets the value of the providerErrs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProviderErrs }
     *     
     */
    public void setProviderErrs(ProviderErrs value) {
        this.providerErrs = value;
    }

}
