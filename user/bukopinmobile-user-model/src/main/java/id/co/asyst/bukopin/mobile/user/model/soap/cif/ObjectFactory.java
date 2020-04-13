
package id.co.asyst.bukopin.mobile.user.model.soap.cif;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the id.co.asyst.bukopin.mobile.user.model.soap.cif package. 
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

    private final static QName _GetInquiryCIFReq_QNAME = new QName("http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0", "GetInquiryCIFReq");
    private final static QName _GetInquiryCIFRes_QNAME = new QName("http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0", "GetInquiryCIFRes");
    private final static QName _HeaderRQ_QNAME = new QName("http://xml.asyst.co.id/commons/ws/header", "HeaderRQ");
    private final static QName _HeaderRS_QNAME = new QName("http://xml.asyst.co.id/commons/ws/header", "HeaderRS");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: id.co.asyst.bukopin.mobile.user.model.soap.cif
     * 
     */
    public ObjectFactory() {
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
     * Create an instance of {@link GetInquiryCIFResType }
     * 
     */
    public GetInquiryCIFResType createGetInquiryCIFResType() {
        return new GetInquiryCIFResType();
    }

    /**
     * Create an instance of {@link GetInquiryCIFReqType }
     * 
     */
    public GetInquiryCIFReqType createGetInquiryCIFReqType() {
        return new GetInquiryCIFReqType();
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
     * Create an instance of {@link Err }
     * 
     */
    public Err createErr() {
        return new Err();
    }

    /**
     * Create an instance of {@link ErrList }
     * 
     */
    public ErrList createErrList() {
        return new ErrList();
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
     * Create an instance of {@link GetInquiryCIFResType.Response }
     * 
     */
    public GetInquiryCIFResType.Response createGetInquiryCIFResTypeResponse() {
        return new GetInquiryCIFResType.Response();
    }

    /**
     * Create an instance of {@link GetInquiryCIFResType.AccInfo }
     * 
     */
    public GetInquiryCIFResType.AccInfo createGetInquiryCIFResTypeAccInfo() {
        return new GetInquiryCIFResType.AccInfo();
    }

    /**
     * Create an instance of {@link GetInquiryCIFResType.Accounts }
     * 
     */
    public GetInquiryCIFResType.Accounts createGetInquiryCIFResTypeAccounts() {
        return new GetInquiryCIFResType.Accounts();
    }

    /**
     * Create an instance of {@link GetInquiryCIFResType.Paginginfo }
     * 
     */
    public GetInquiryCIFResType.Paginginfo createGetInquiryCIFResTypePaginginfo() {
        return new GetInquiryCIFResType.Paginginfo();
    }

    /**
     * Create an instance of {@link GetInquiryCIFReqType.Paginginfo }
     * 
     */
    public GetInquiryCIFReqType.Paginginfo createGetInquiryCIFReqTypePaginginfo() {
        return new GetInquiryCIFReqType.Paginginfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetInquiryCIFReqType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetInquiryCIFReqType }{@code >}
     */
    @XmlElementDecl(namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0", name = "GetInquiryCIFReq")
    public JAXBElement<GetInquiryCIFReqType> createGetInquiryCIFReq(GetInquiryCIFReqType value) {
        return new JAXBElement<GetInquiryCIFReqType>(_GetInquiryCIFReq_QNAME, GetInquiryCIFReqType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetInquiryCIFResType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetInquiryCIFResType }{@code >}
     */
    @XmlElementDecl(namespace = "http://xml.asyst.co.id/cbs/listing/cif/getinquirycif/v1_0", name = "GetInquiryCIFRes")
    public JAXBElement<GetInquiryCIFResType> createGetInquiryCIFRes(GetInquiryCIFResType value) {
        return new JAXBElement<GetInquiryCIFResType>(_GetInquiryCIFRes_QNAME, GetInquiryCIFResType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HeaderRQ }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link HeaderRQ }{@code >}
     */
    @XmlElementDecl(namespace = "http://xml.asyst.co.id/commons/ws/header", name = "HeaderRQ")
    public JAXBElement<HeaderRQ> createHeaderRQ(HeaderRQ value) {
        return new JAXBElement<HeaderRQ>(_HeaderRQ_QNAME, HeaderRQ.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HeaderRS }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link HeaderRS }{@code >}
     */
    @XmlElementDecl(namespace = "http://xml.asyst.co.id/commons/ws/header", name = "HeaderRS")
    public JAXBElement<HeaderRS> createHeaderRS(HeaderRS value) {
        return new JAXBElement<HeaderRS>(_HeaderRS_QNAME, HeaderRS.class, null, value);
    }

}
