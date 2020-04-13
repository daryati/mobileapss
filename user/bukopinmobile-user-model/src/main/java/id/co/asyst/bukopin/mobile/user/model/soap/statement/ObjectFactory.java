
package id.co.asyst.bukopin.mobile.user.model.soap.statement;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the id.co.asyst.xml.cbs.listing.statement.getstatementbydaterange.v1_0 package. 
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

    private final static QName _GetStatementByDateRangeReq_QNAME = new QName("http://xml.asyst.co.id/cbs/listing/statement/getstatementbydaterange/v1_0", "GetStatementByDateRangeReq");
    private final static QName _GetStatementByDateRangeRes_QNAME = new QName("http://xml.asyst.co.id/cbs/listing/statement/getstatementbydaterange/v1_0", "GetStatementByDateRangeRes");
	private final static QName _GetMiniStatementRes_QNAME = new QName("http://xml.asyst.co.id/cbs/listing/statement/getministatement/v1_0", "GetMiniStatementRes");
    private final static QName _GetMiniStatementReq_QNAME = new QName("http://xml.asyst.co.id/cbs/listing/statement/getministatement/v1_0", "GetMiniStatementReq");
	private final static QName _HeaderRQ_QNAME = new QName("http://xml.asyst.co.id/commons/ws/header", "HeaderRQ");
    private final static QName _HeaderRS_QNAME = new QName("http://xml.asyst.co.id/commons/ws/header", "HeaderRS");
	
    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: id.co.asyst.xml.cbs.listing.statement.getstatementbydaterange.v1_0
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetStatementByDateRangeResType }
     * 
     */
    public GetStatementByDateRangeResType createGetStatementByDateRangeResType() {
        return new GetStatementByDateRangeResType();
    }

    /**
     * Create an instance of {@link GetStatementByDateRangeReqType }
     * 
     */
    public GetStatementByDateRangeReqType createGetStatementByDateRangeReqType() {
        return new GetStatementByDateRangeReqType();
    }

    /**
     * Create an instance of {@link GetStatementByDateRangeResType.AccInfo }
     * 
     */
    public GetStatementByDateRangeResType.AccInfo createGetStatementByDateRangeResTypeAccInfo() {
        return new GetStatementByDateRangeResType.AccInfo();
    }

    /**
     * Create an instance of {@link GetStatementByDateRangeResType.Transaction }
     * 
     */
    public GetStatementByDateRangeResType.Transaction createGetStatementByDateRangeResTypeTransaction() {
        return new GetStatementByDateRangeResType.Transaction();
    }

    /**
     * Create an instance of {@link GetStatementByDateRangeResType.PagingInfo }
     * 
     */
    public GetStatementByDateRangeResType.PagingInfo createGetStatementByDateRangeResTypePagingInfo() {
        return new GetStatementByDateRangeResType.PagingInfo();
    }

    /**
     * Create an instance of {@link GetStatementByDateRangeReqType.PagingInfo }
     * 
     */
    public GetStatementByDateRangeReqType.PagingInfo createGetStatementByDateRangeReqTypePagingInfo() {
        return new GetStatementByDateRangeReqType.PagingInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStatementByDateRangeReqType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getstatementbydaterange/v1_0", name = "GetStatementByDateRangeReq")
    public JAXBElement<GetStatementByDateRangeReqType> createGetStatementByDateRangeReq(GetStatementByDateRangeReqType value) {
        return new JAXBElement<GetStatementByDateRangeReqType>(_GetStatementByDateRangeReq_QNAME, GetStatementByDateRangeReqType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetStatementByDateRangeResType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getstatementbydaterange/v1_0", name = "GetStatementByDateRangeRes")
    public JAXBElement<GetStatementByDateRangeResType> createGetStatementByDateRangeRes(GetStatementByDateRangeResType value) {
        return new JAXBElement<GetStatementByDateRangeResType>(_GetStatementByDateRangeRes_QNAME, GetStatementByDateRangeResType.class, null, value);
    }

	/**
     * Create an instance of {@link GetMiniStatementResType }
     * 
     */
    public GetMiniStatementResType createGetMiniStatementResType() {
        return new GetMiniStatementResType();
    }

    /**
     * Create an instance of {@link GetMiniStatementReqType }
     * 
     */
    public GetMiniStatementReqType createGetMiniStatementReqType() {
        return new GetMiniStatementReqType();
    }

    /**
     * Create an instance of {@link GetMiniStatementResType.AccInfo }
     * 
     */
    public GetMiniStatementResType.AccInfo createGetMiniStatementResTypeAccInfo() {
        return new GetMiniStatementResType.AccInfo();
    }

    /**
     * Create an instance of {@link GetMiniStatementResType.Transaction }
     * 
     */
    public GetMiniStatementResType.Transaction createGetMiniStatementResTypeTransaction() {
        return new GetMiniStatementResType.Transaction();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMiniStatementResType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getministatement/v1_0", name = "GetMiniStatementRes")
    public JAXBElement<GetMiniStatementResType> createGetMiniStatementRes(GetMiniStatementResType value) {
        return new JAXBElement<GetMiniStatementResType>(_GetMiniStatementRes_QNAME, GetMiniStatementResType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMiniStatementReqType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getministatement/v1_0", name = "GetMiniStatementReq")
    public JAXBElement<GetMiniStatementReqType> createGetMiniStatementReq(GetMiniStatementReqType value) {
        return new JAXBElement<GetMiniStatementReqType>(_GetMiniStatementReq_QNAME, GetMiniStatementReqType.class, null, value);
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

}
