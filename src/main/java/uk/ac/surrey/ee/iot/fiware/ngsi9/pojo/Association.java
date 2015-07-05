//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.04.29 at 11:32:08 AM BST 
//


package uk.ac.surrey.ee.iot.fiware.ngsi9.pojo;

import com.google.gson.annotations.*;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Value complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Value">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entityAssociation" type="{}EntityAssociation" minOccurs="0"/>
 *         &lt;element name="attributeAssociationList" type="{}AttributeAssociationList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
//@XmlRootElement(name = "value")
@XmlAccessorType(XmlAccessType.FIELD)
public class Association {

    @SerializedName("source")
    protected EntityId sourceEntityId;
    @SerializedName("target")
    protected EntityId targetEntityId;
    @SerializedName("attributeAssociations")
    @XmlElementWrapper(name="attributeAssociationList")
    protected List<AttributeAssociation> attributeAssociation;
   
    public Association(){};

    public EntityId getSourceEntityId() {
        return sourceEntityId;
    }

    /**
     * Sets the value of the sourceEntityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityId }
     *     
     */
    public void setSourceEntityId(EntityId value) {
        this.sourceEntityId = value;
    }

    /**
     * Gets the value of the targetEntityId property.
     * 
     * @return
     *     possible object is
     *     {@link EntityId }
     *     
     */
    public EntityId getTargetEntityId() {
        return targetEntityId;
    }

    /**
     * Sets the value of the targetEntityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityId }
     *     
     */
    public void setTargetEntityId(EntityId value) {
        this.targetEntityId = value;
    }
    
    public List<AttributeAssociation> getAttributeAssociation() {
        if (attributeAssociation == null) {
            attributeAssociation = new ArrayList<>();
        }
        return this.attributeAssociation;
    } 

}