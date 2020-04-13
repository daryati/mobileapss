
package id.co.asyst.bukopin.mobile.master.model.soap.exchange.rate;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;



/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the id.co.asyst.xml.cbs.product.productinformation package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Responsemessage_QNAME = new QName("http://xml.asyst.co.id/cbs/product/productinformation", "responsemessage");
    private final static QName _Productdetail_QNAME = new QName("http://xml.asyst.co.id/cbs/product/productinformation", "productdetail");
    private final static QName _ProductInformation_QNAME = new QName("http://xml.asyst.co.id/cbs/product/productinformation", "productInformation");
    private final static QName _HeaderRQ_QNAME = new QName("http://xml.asyst.co.id/commons/ws/header", "HeaderRQ");
    private final static QName _HeaderRS_QNAME = new QName("http://xml.asyst.co.id/commons/ws/header", "HeaderRS");
	private final static QName _Currency_QNAME = new QName("http://xml.asyst.co.id/cbs/exchangerate", "currency");
    private final static QName _Exchangerate_QNAME = new QName("http://xml.asyst.co.id/cbs/exchangerate", "exchangerate");
	
    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: id.co.asyst.xml.cbs.product.productinformation
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ProductdetailType }
     * 
     */
    public ProductdetailType createProductdetailType() {
        return new ProductdetailType();
    }

    /**
     * Create an instance of {@link Resproductinformation }
     * 
     */
    public Resproductinformation createResproductinformation() {
        return new Resproductinformation();
    }

    /**
     * Create an instance of {@link ProductInformationType }
     * 
     */
    public ProductInformationType createProductInformationType() {
        return new ProductInformationType();
    }

    /**
     * Create an instance of {@link ResponsemessageType }
     * 
     */
    public ResponsemessageType createResponsemessageType() {
        return new ResponsemessageType();
    }

    /**
     * Create an instance of {@link Reqproductinformation }
     * 
     */
    public Reqproductinformation createReqproductinformation() {
        return new Reqproductinformation();
    }

    /**
     * Create an instance of {@link ProductdetailType.Giro }
     * 
     */
    public ProductdetailType.Giro createProductdetailTypeGiro() {
        return new ProductdetailType.Giro();
    }

    /**
     * Create an instance of {@link ProductdetailType.Deposito }
     * 
     */
    public ProductdetailType.Deposito createProductdetailTypeDeposito() {
        return new ProductdetailType.Deposito();
    }

    /**
     * Create an instance of {@link ProductdetailType.Tabungan }
     * 
     */
    public ProductdetailType.Tabungan createProductdetailTypeTabungan() {
        return new ProductdetailType.Tabungan();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponsemessageType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.asyst.co.id/cbs/product/productinformation", name = "responsemessage")
    public JAXBElement<ResponsemessageType> createResponsemessage(ResponsemessageType value) {
        return new JAXBElement<ResponsemessageType>(_Responsemessage_QNAME, ResponsemessageType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProductdetailType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.asyst.co.id/cbs/product/productinformation", name = "productdetail")
    public JAXBElement<ProductdetailType> createProductdetail(ProductdetailType value) {
        return new JAXBElement<ProductdetailType>(_Productdetail_QNAME, ProductdetailType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProductInformationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.asyst.co.id/cbs/product/productinformation", name = "productInformation")
    public JAXBElement<ProductInformationType> createProductInformation(ProductInformationType value) {
        return new JAXBElement<ProductInformationType>(_ProductInformation_QNAME, ProductInformationType.class, null, value);
    }

	 /**
     * Create an instance of {@link ErrList }
     * 
     */
    public ErrList createErrList() {
        return new ErrList();
    }

    /**
     * Create an instance of {@link Err }
     * 
     */
    public Err createErr() {
        return new Err();
    }

    /**
     * Create an instance of {@link BWErrDetails }
     * 
     */
    public BWErrDetails createBWErrDetails() {
        return new BWErrDetails();
    }

    /**
     * Create an instance of {@link ErrHeader }
     * 
     */
    public ErrHeader createErrHeader() {
        return new ErrHeader();
    }

    /**
     * Create an instance of {@link ErrReport }
     * 
     */
    public ErrReport createErrReport() {
        return new ErrReport();
    }

    /**
     * Create an instance of {@link Anydata }
     * 
     */
    public Anydata createAnydata() {
        return new Anydata();
    }

    /**
     * Create an instance of {@link ProviderErrs }
     * 
     */
    public ProviderErrs createProviderErrs() {
        return new ProviderErrs();
    }
	
	/**
     * Create an instance of {@link HeaderRS }
     * 
     */
    public HeaderRS createHeaderRS() {
        return new HeaderRS();
    }

    /**
     * Create an instance of {@link HeaderRQ }
     * 
     */
    public HeaderRQ createHeaderRQ() {
        return new HeaderRQ();
    }

    /**
     * Create an instance of {@link HeaderRQ.Credentials }
     * 
     */
    public HeaderRQ.Credentials createHeaderRQCredentials() {
        return new HeaderRQ.Credentials();
    }

    /**
     * Create an instance of {@link HeaderRS.Session }
     * 
     */
    public HeaderRS.Session createHeaderRSSession() {
        return new HeaderRS.Session();
    }

    /**
     * Create an instance of {@link HeaderRQ.Credentials.Identity }
     * 
     */
    public HeaderRQ.Credentials.Identity createHeaderRQCredentialsIdentity() {
        return new HeaderRQ.Credentials.Identity();
    }

    /**
     * Create an instance of {@link HeaderRQ.Credentials.Session }
     * 
     */
    public HeaderRQ.Credentials.Session createHeaderRQCredentialsSession() {
        return new HeaderRQ.Credentials.Session();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HeaderRQ }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.asyst.co.id/commons/ws/header", name = "HeaderRQ")
    public JAXBElement<HeaderRQ> createHeaderRQ(HeaderRQ value) {
        return new JAXBElement<HeaderRQ>(_HeaderRQ_QNAME, HeaderRQ.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HeaderRS }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.asyst.co.id/commons/ws/header", name = "HeaderRS")
    public JAXBElement<HeaderRS> createHeaderRS(HeaderRS value) {
        return new JAXBElement<HeaderRS>(_HeaderRS_QNAME, HeaderRS.class, null, value);
    }

	/**
     * Create an instance of {@link Reqexchangerate }
     * 
     */
    public Reqexchangerate createReqexchangerate() {
        return new Reqexchangerate();
    }

    /**
     * Create an instance of {@link CurrencyType }
     * 
     */
    public CurrencyType createCurrencyType() {
        return new CurrencyType();
    }

    /**
     * Create an instance of {@link ExchangerateType }
     * 
     */
    public ExchangerateType createExchangerateType() {
        return new ExchangerateType();
    }

    /**
     * Create an instance of {@link Resexchangerate }
     * 
     */
    public Resexchangerate createResexchangerate() {
        return new Resexchangerate();
    }

    /**
     * Create an instance of {@link Responsemessage }
     * 
     */
    public Responsemessage createResponsemessage() {
        return new Responsemessage();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CurrencyType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.asyst.co.id/cbs/exchangerate", name = "currency")
    public JAXBElement<CurrencyType> createCurrency(CurrencyType value) {
        return new JAXBElement<CurrencyType>(_Currency_QNAME, CurrencyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExchangerateType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.asyst.co.id/cbs/exchangerate", name = "exchangerate")
    public JAXBElement<ExchangerateType> createExchangerate(ExchangerateType value) {
        return new JAXBElement<ExchangerateType>(_Exchangerate_QNAME, ExchangerateType.class, null, value);
    }
}
