
package id.co.asyst.bukopin.mobile.user.model.soap.cif;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetInquiryCIFReqType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetInquiryCIFReqType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="cifnumber"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="0"/&gt;
 *               &lt;maxLength value="20"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="paginginfo" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="startindex" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                   &lt;element name="itemsperpage" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                   &lt;element name="totalresult" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
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
@XmlType(name = "GetInquiryCIFReqType", namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0", propOrder = {
    "cifnumber",
    "paginginfo"
})
public class GetInquiryCIFReqType {

    @XmlElement(required = true)
    protected String cifnumber;
    protected GetInquiryCIFReqType.Paginginfo paginginfo;

    /**
     * Gets the value of the cifnumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCifnumber() {
        return cifnumber;
    }

    /**
     * Sets the value of the cifnumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCifnumber(String value) {
        this.cifnumber = value;
    }

    /**
     * Gets the value of the paginginfo property.
     * 
     * @return
     *     possible object is
     *     {@link GetInquiryCIFReqType.Paginginfo }
     *     
     */
    public GetInquiryCIFReqType.Paginginfo getPaginginfo() {
        return paginginfo;
    }

    /**
     * Sets the value of the paginginfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetInquiryCIFReqType.Paginginfo }
     *     
     */
    public void setPaginginfo(GetInquiryCIFReqType.Paginginfo value) {
        this.paginginfo = value;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "GetInquiryCIFReqType [cifnumber=" + cifnumber + ", paginginfo=" + paginginfo + "]";
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
     *         &lt;element name="startindex" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
     *         &lt;element name="itemsperpage" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
     *         &lt;element name="totalresult" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
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
        "startindex",
        "itemsperpage",
        "totalresult"
    })
    public static class Paginginfo {

        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0", required = true)
        protected BigInteger startindex;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0", required = true)
        protected BigInteger itemsperpage;
        @XmlElement(namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0")
        protected BigInteger totalresult;

        /**
         * Gets the value of the startindex property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getStartindex() {
            return startindex;
        }

        /**
         * Sets the value of the startindex property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setStartindex(BigInteger value) {
            this.startindex = value;
        }

        /**
         * Gets the value of the itemsperpage property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getItemsperpage() {
            return itemsperpage;
        }

        /**
         * Sets the value of the itemsperpage property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setItemsperpage(BigInteger value) {
            this.itemsperpage = value;
        }

        /**
         * Gets the value of the totalresult property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getTotalresult() {
            return totalresult;
        }

        /**
         * Sets the value of the totalresult property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setTotalresult(BigInteger value) {
            this.totalresult = value;
        }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	    return "Paginginfo [startindex=" + startindex + ", itemsperpage=" + itemsperpage + ", totalresult="
		    + totalresult + "]";
	}

    }

}
