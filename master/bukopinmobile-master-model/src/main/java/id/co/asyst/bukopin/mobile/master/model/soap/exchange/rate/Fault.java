
package id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "Err", targetNamespace = "http://xml.asyst.co.id/commons/ws/fault")
public class Fault
    extends Exception
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -2830904796481334046L;
	/**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private Err faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public Fault(String message, Err faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public Fault(String message, Err faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: id.co.asyst.xml.commons.ws.fault.Err
     */
    public Err getFaultInfo() {
        return faultInfo;
    }

}
