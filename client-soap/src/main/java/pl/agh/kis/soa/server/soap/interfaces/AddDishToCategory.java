package pl.agh.kis.soa.server.soap.interfaces;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addDishToCategory complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addDishToCategory">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dishName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dishPrice" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="categoryId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addDishToCategory", propOrder = {
    "dishName",
    "dishPrice",
    "categoryId"
})
public class AddDishToCategory {

    protected String dishName;
    protected Float dishPrice;
    protected Long categoryId;

    /**
     * Gets the value of the dishName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDishName() {
        return dishName;
    }

    /**
     * Sets the value of the dishName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDishName(String value) {
        this.dishName = value;
    }

    /**
     * Gets the value of the dishPrice property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getDishPrice() {
        return dishPrice;
    }

    /**
     * Sets the value of the dishPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setDishPrice(Float value) {
        this.dishPrice = value;
    }

    /**
     * Gets the value of the categoryId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * Sets the value of the categoryId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCategoryId(Long value) {
        this.categoryId = value;
    }

}
