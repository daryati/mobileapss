
package id.co.asyst.bukopin.mobile.user.model.soap.cif;

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
 *         &lt;element name="StackTrace" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Msg" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="FullClass" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Class" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ProcessStack" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MsgCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Data" type="{http://xml.asyst.co.id/commons/ws/fault}anydata" minOccurs="0"/&gt;
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
    "stackTrace",
    "msg",
    "fullClass",
    "clazz",
    "processStack",
    "msgCode",
    "data"
})
@XmlRootElement(name = "ErrReport")
public class ErrReport {

    @XmlElement(name = "StackTrace")
    protected String stackTrace;
    @XmlElement(name = "Msg", required = true)
    protected String msg;
    @XmlElement(name = "FullClass")
    protected String fullClass;
    @XmlElement(name = "Class")
    protected String clazz;
    @XmlElement(name = "ProcessStack")
    protected String processStack;
    @XmlElement(name = "MsgCode")
    protected String msgCode;
    @XmlElement(name = "Data")
    protected Anydata data;

    /**
     * Gets the value of the stackTrace property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStackTrace() {
        return stackTrace;
    }

    /**
     * Sets the value of the stackTrace property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStackTrace(String value) {
        this.stackTrace = value;
    }

    /**
     * Gets the value of the msg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Sets the value of the msg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsg(String value) {
        this.msg = value;
    }

    /**
     * Gets the value of the fullClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFullClass() {
        return fullClass;
    }

    /**
     * Sets the value of the fullClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFullClass(String value) {
        this.fullClass = value;
    }

    /**
     * Gets the value of the clazz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClazz() {
        return clazz;
    }

    /**
     * Sets the value of the clazz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClazz(String value) {
        this.clazz = value;
    }

    /**
     * Gets the value of the processStack property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessStack() {
        return processStack;
    }

    /**
     * Sets the value of the processStack property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessStack(String value) {
        this.processStack = value;
    }

    /**
     * Gets the value of the msgCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgCode() {
        return msgCode;
    }

    /**
     * Sets the value of the msgCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsgCode(String value) {
        this.msgCode = value;
    }

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     {@link Anydata }
     *     
     */
    public Anydata getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     {@link Anydata }
     *     
     */
    public void setData(Anydata value) {
        this.data = value;
    }

}
