
package id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for productInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="productInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productcode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="productname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productdescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "productInformationType",namespace = "http://xml.asyst.co.id/cbs/product/productinformation", propOrder = {
    "productcode",
    "productname",
    "productdescription"
})
public class ProductInformationType {

    protected int productcode;
    protected String productname;
    protected String productdescription;

    /**
     * Gets the value of the productcode property.
     * 
     */
    public int getProductcode() {
        return productcode;
    }

    /**
     * Sets the value of the productcode property.
     * 
     */
    public void setProductcode(int value) {
        this.productcode = value;
    }

    /**
     * Gets the value of the productname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductname() {
        return productname;
    }

    /**
     * Sets the value of the productname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductname(String value) {
        this.productname = value;
    }

    /**
     * Gets the value of the productdescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductdescription() {
        return productdescription;
    }

    /**
     * Sets the value of the productdescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductdescription(String value) {
        this.productdescription = value;
    }

}
