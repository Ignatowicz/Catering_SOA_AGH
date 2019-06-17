package pl.agh.kis.soa.server.soap.interfaces;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pl.agh.kis.soa.server.soap.interfaces package. 
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

    private final static QName _AddDishToCategoryResponse_QNAME = new QName("http://interfaces.soap.server.soa.kis.agh.pl/", "addDishToCategoryResponse");
    private final static QName _AddDishToCategory_QNAME = new QName("http://interfaces.soap.server.soa.kis.agh.pl/", "addDishToCategory");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pl.agh.kis.soa.server.soap.interfaces
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddDishToCategory }
     * 
     */
    public AddDishToCategory createAddDishToCategory() {
        return new AddDishToCategory();
    }

    /**
     * Create an instance of {@link AddDishToCategoryResponse }
     * 
     */
    public AddDishToCategoryResponse createAddDishToCategoryResponse() {
        return new AddDishToCategoryResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddDishToCategoryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.soap.server.soa.kis.agh.pl/", name = "addDishToCategoryResponse")
    public JAXBElement<AddDishToCategoryResponse> createAddDishToCategoryResponse(AddDishToCategoryResponse value) {
        return new JAXBElement<AddDishToCategoryResponse>(_AddDishToCategoryResponse_QNAME, AddDishToCategoryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddDishToCategory }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.soap.server.soa.kis.agh.pl/", name = "addDishToCategory")
    public JAXBElement<AddDishToCategory> createAddDishToCategory(AddDishToCategory value) {
        return new JAXBElement<AddDishToCategory>(_AddDishToCategory_QNAME, AddDishToCategory.class, null, value);
    }

}
