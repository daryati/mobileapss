
package id.co.asyst.bukopin.mobile.user.model.soap.account;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the id.co.asyst.bukopin.mobile.user.model.soap.account package. 
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

    private final static QName _HeaderRQ_QNAME = new QName("http://xml.asyst.co.id/commons/ws/header", "HeaderRQ");
    private final static QName _HeaderRS_QNAME = new QName("http://xml.asyst.co.id/commons/ws/header", "HeaderRS");
    private final static QName _GetInquiryTransactionReq_QNAME = new QName("http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0", "GetInquiryTransactionReq");
    private final static QName _GetInquiryTransactionRes_QNAME = new QName("http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0", "GetInquiryTransactionRes");
    private final static QName _GetAccountBalanceReq_QNAME = new QName("http://xml.asyst.co.id/cbs/listing/statement/getaccountbalance/v1_0", "GetAccountBalanceReq");
    private final static QName _GetAccountBalanceRes_QNAME = new QName("http://xml.asyst.co.id/cbs/listing/statement/getaccountbalance/v1_0", "GetAccountBalanceRes");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: id.co.asyst.bukopin.mobile.user.model.soap.account
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAccountBalanceResType }
     * 
     */
    public GetAccountBalanceResType createGetAccountBalanceResType() {
        return new GetAccountBalanceResType();
    }

    /**
     * Create an instance of {@link GetAccountBalanceReqType }
     * 
     */
    public GetAccountBalanceReqType createGetAccountBalanceReqType() {
        return new GetAccountBalanceReqType();
    }

    /**
     * Create an instance of {@link GetInquiryTransactionResType }
     * 
     */
    public GetInquiryTransactionResType createGetInquiryTransactionResType() {
        return new GetInquiryTransactionResType();
    }

    /**
     * Create an instance of {@link GetInquiryTransactionReqType }
     * 
     */
    public GetInquiryTransactionReqType createGetInquiryTransactionReqType() {
        return new GetInquiryTransactionReqType();
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
     * Create an instance of {@link TrxSummaryType }
     * 
     */
    public TrxSummaryType createTrxSummaryType() {
        return new TrxSummaryType();
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
     * Create an instance of {@link GetAccountBalanceResType.Response }
     * 
     */
    public GetAccountBalanceResType.Response createGetAccountBalanceResTypeResponse() {
        return new GetAccountBalanceResType.Response();
    }

    /**
     * Create an instance of {@link GetAccountBalanceResType.Transaction }
     * 
     */
    public GetAccountBalanceResType.Transaction createGetAccountBalanceResTypeTransaction() {
        return new GetAccountBalanceResType.Transaction();
    }

    /**
     * Create an instance of {@link GetAccountBalanceResType.TotalBalance }
     * 
     */
    public GetAccountBalanceResType.TotalBalance createGetAccountBalanceResTypeTotalBalance() {
        return new GetAccountBalanceResType.TotalBalance();
    }

    /**
     * Create an instance of {@link GetAccountBalanceReqType.Account }
     * 
     */
    public GetAccountBalanceReqType.Account createGetAccountBalanceReqTypeAccount() {
        return new GetAccountBalanceReqType.Account();
    }

    /**
     * Create an instance of {@link GetInquiryTransactionResType.Response }
     * 
     */
    public GetInquiryTransactionResType.Response createGetInquiryTransactionResTypeResponse() {
        return new GetInquiryTransactionResType.Response();
    }

    /**
     * Create an instance of {@link GetInquiryTransactionResType.AccInfo }
     * 
     */
    public GetInquiryTransactionResType.AccInfo createGetInquiryTransactionResTypeAccInfo() {
        return new GetInquiryTransactionResType.AccInfo();
    }

    /**
     * Create an instance of {@link GetInquiryTransactionResType.Transaction }
     * 
     */
    public GetInquiryTransactionResType.Transaction createGetInquiryTransactionResTypeTransaction() {
        return new GetInquiryTransactionResType.Transaction();
    }

    /**
     * Create an instance of {@link GetInquiryTransactionResType.PagingInfo }
     * 
     */
    public GetInquiryTransactionResType.PagingInfo createGetInquiryTransactionResTypePagingInfo() {
        return new GetInquiryTransactionResType.PagingInfo();
    }

    /**
     * Create an instance of {@link GetInquiryTransactionReqType.Date }
     * 
     */
    public GetInquiryTransactionReqType.Date createGetInquiryTransactionReqTypeDate() {
        return new GetInquiryTransactionReqType.Date();
    }

    /**
     * Create an instance of {@link GetInquiryTransactionReqType.PagingInfo }
     * 
     */
    public GetInquiryTransactionReqType.PagingInfo createGetInquiryTransactionReqTypePagingInfo() {
        return new GetInquiryTransactionReqType.PagingInfo();
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

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetInquiryTransactionReqType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetInquiryTransactionReqType }{@code >}
     */
    @XmlElementDecl(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0", name = "GetInquiryTransactionReq")
    public JAXBElement<GetInquiryTransactionReqType> createGetInquiryTransactionReq(GetInquiryTransactionReqType value) {
        return new JAXBElement<GetInquiryTransactionReqType>(_GetInquiryTransactionReq_QNAME, GetInquiryTransactionReqType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetInquiryTransactionResType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetInquiryTransactionResType }{@code >}
     */
    @XmlElementDecl(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getinquirytransaction/v1_0", name = "GetInquiryTransactionRes")
    public JAXBElement<GetInquiryTransactionResType> createGetInquiryTransactionRes(GetInquiryTransactionResType value) {
        return new JAXBElement<GetInquiryTransactionResType>(_GetInquiryTransactionRes_QNAME, GetInquiryTransactionResType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAccountBalanceReqType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAccountBalanceReqType }{@code >}
     */
    @XmlElementDecl(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getaccountbalance/v1_0", name = "GetAccountBalanceReq")
    public JAXBElement<GetAccountBalanceReqType> createGetAccountBalanceReq(GetAccountBalanceReqType value) {
        return new JAXBElement<GetAccountBalanceReqType>(_GetAccountBalanceReq_QNAME, GetAccountBalanceReqType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAccountBalanceResType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAccountBalanceResType }{@code >}
     */
    @XmlElementDecl(namespace = "http://xml.asyst.co.id/cbs/listing/statement/getaccountbalance/v1_0", name = "GetAccountBalanceRes")
    public JAXBElement<GetAccountBalanceResType> createGetAccountBalanceRes(GetAccountBalanceResType value) {
        return new JAXBElement<GetAccountBalanceResType>(_GetAccountBalanceRes_QNAME, GetAccountBalanceResType.class, null, value);
    }

}
