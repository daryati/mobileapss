package id.co.asyst.bukopin.mobile.user.core.service.soap;

import java.net.MalformedURLException;
import java.net.URL;

import javax.jws.HandlerChain;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.3.4
 * 2019-10-27T21:13:21.912+07:00
 * Generated source version: 3.3.4
 *
 */
@WebServiceClient(name = "AccountStatementServices",
                  wsdlLocation = "classpath:wsdl/AccountStatement.wsdl",
                  targetNamespace = "http://xmlns.example.com/1535711330238")
@org.springframework.stereotype.Service
@HandlerChain(file="handler-chain.xml") // soap interceptor to log (in "common" module)
public class AccountStatementServices extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://xmlns.example.com/1535711330238", "AccountStatementServices");
    public final static QName AccountStatementPTEndpointBinding = new QName("http://xmlns.example.com/1535711330238", "AccountStatementPTEndpointBinding");
    static {
        URL url = null;
        try {
            url = new URL("classpath:wsdl/AccountStatement.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(AccountStatementServices.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "classpath:wsdl/AccountStatement.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public AccountStatementServices(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public AccountStatementServices(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public AccountStatementServices() {
        super(WSDL_LOCATION, SERVICE);
    }

    public AccountStatementServices(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public AccountStatementServices(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public AccountStatementServices(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns PortType
     */
    @WebEndpoint(name = "AccountStatementPTEndpointBinding")
    public PortType getAccountStatementPTEndpointBinding() {
        return super.getPort(AccountStatementPTEndpointBinding, PortType.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PortType
     */
    @WebEndpoint(name = "AccountStatementPTEndpointBinding")
    public PortType getAccountStatementPTEndpointBinding(WebServiceFeature... features) {
        return super.getPort(AccountStatementPTEndpointBinding, PortType.class, features);
    }

}
