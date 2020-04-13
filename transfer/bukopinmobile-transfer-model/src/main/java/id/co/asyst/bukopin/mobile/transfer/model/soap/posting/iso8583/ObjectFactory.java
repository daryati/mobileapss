
package id.co.asyst.bukopin.mobile.transfer.model.soap.posting.iso8583;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the id.co.asyst.xml.commons.iso8583 package. 
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

    private final static QName _BinaryData_QNAME = new QName("http://xml.asyst.co.id/commons/iso8583", "BinaryData");
    private final static QName _StringData_QNAME = new QName("http://xml.asyst.co.id/commons/iso8583", "StringData");
    private final static QName _MTI_QNAME = new QName("http://xml.asyst.co.id/commons/iso8583", "MTI");
    private final static QName _DataElement_QNAME = new QName("http://xml.asyst.co.id/commons/iso8583", "DataElement");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: id.co.asyst.xml.commons.iso8583
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Elements }
     * 
     */
    public Elements createElements() {
        return new Elements();
    }

    /**
     * Create an instance of {@link ISOMessages }
     * 
     */
    public ISOMessages createISOMessages() {
        return new ISOMessages();
    }

    /**
     * Create an instance of {@link Data }
     * 
     */
    public Data createData() {
        return new Data();
    }

    /**
     * Create an instance of {@link ISOTemplate }
     * 
     */
    public ISOTemplate createISOTemplate() {
        return new ISOTemplate();
    }

    /**
     * Create an instance of {@link ISOHeader }
     * 
     */
    public ISOHeader createISOHeader() {
        return new ISOHeader();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.asyst.co.id/commons/iso8583", name = "BinaryData")
    public JAXBElement<String> createBinaryData(String value) {
        return new JAXBElement<String>(_BinaryData_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.asyst.co.id/commons/iso8583", name = "StringData")
    public JAXBElement<String> createStringData(String value) {
        return new JAXBElement<String>(_StringData_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.asyst.co.id/commons/iso8583", name = "MTI")
    public JAXBElement<String> createMTI(String value) {
        return new JAXBElement<String>(_MTI_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Elements }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.asyst.co.id/commons/iso8583", name = "DataElement")
    public JAXBElement<Elements> createDataElement(Elements value) {
        return new JAXBElement<Elements>(_DataElement_QNAME, Elements.class, null, value);
    }

}
