
package id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate;

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
 *         &lt;element ref="{http://xml.asyst.co.id/cbs/product/productinformation}productInformation"/>
 *         &lt;element ref="{http://xml.asyst.co.id/cbs/product/productinformation}productdetail"/>
 *         &lt;element ref="{http://xml.asyst.co.id/cbs/product/productinformation}responsemessage"/>
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
    "productInformation",
    "productdetail",
    "responsemessage"
})
@XmlRootElement(name = "resproductinformation")
public class Resproductinformation {

    @XmlElement(required = true)
    protected ProductInformationType productInformation;
    @XmlElement(required = true)
    protected ProductdetailType productdetail;
    @XmlElement(required = true)
    protected ResponsemessageType responsemessage;

    /**
     * Gets the value of the productInformation property.
     * 
     * @return
     *     possible object is
     *     {@link ProductInformationType }
     *     
     */
    public ProductInformationType getProductInformation() {
        return productInformation;
    }

    /**
     * Sets the value of the productInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductInformationType }
     *     
     */
    public void setProductInformation(ProductInformationType value) {
        this.productInformation = value;
    }

    /**
     * Gets the value of the productdetail property.
     * 
     * @return
     *     possible object is
     *     {@link ProductdetailType }
     *     
     */
    public ProductdetailType getProductdetail() {
        return productdetail;
    }

    /**
     * Sets the value of the productdetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductdetailType }
     *     
     */
    public void setProductdetail(ProductdetailType value) {
        this.productdetail = value;
    }

    /**
     * Gets the value of the responsemessage property.
     * 
     * @return
     *     possible object is
     *     {@link ResponsemessageType }
     *     
     */
    public ResponsemessageType getResponsemessage() {
        return responsemessage;
    }

    /**
     * Sets the value of the responsemessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponsemessageType }
     *     
     */
    public void setResponsemessage(ResponsemessageType value) {
        this.responsemessage = value;
    }

}
