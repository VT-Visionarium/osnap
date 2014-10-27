//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.06.16 at 09:21:15 AM EDT 
//


package x3d.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import x3d.fields.SFString;

/**
 * <p>Java class for X3DMetadataObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="X3DMetadataObject">
 *   &lt;complexContent>
 *     &lt;extension base="{}X3DNode">
 *       &lt;attribute name="name" type="{}SFString" />
 *       &lt;attribute name="reference" type="{}SFString" />
 *       &lt;attribute name="containerField" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" default="metadata" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="X3DMetadataObject")
@XmlType(name = "X3DMetadataObject", propOrder = {
    "name",
    "reference",
    "containerField"
})
@XmlSeeAlso({
    MetadataString.class,
    MetadataInteger.class,
    MetadataFloat.class,
    MetadataDouble.class,
    MetadataSet.class
})
public abstract class X3DMetadataObject
  //  extends X3DNode
{

    @XmlAttribute(name = "name")
    protected SFString name;
    @XmlAttribute(name = "reference")
    protected SFString reference;
    @XmlAttribute(name = "containerField")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String containerField;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SFString getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(SFString value) {
        this.name = value;
    }

    /**
     * Gets the value of the reference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public SFString getReference() {
        return reference;
    }

    /**
     * Sets the value of the reference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReference(SFString value) {
        this.reference = value;
    }

    /**
     * Gets the value of the containerField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContainerField() {
        if (containerField == null) {
            return "metadata";
        } else {
            return containerField;
        }
    }

    /**
     * Sets the value of the containerField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContainerField(String value) {
        this.containerField = value;
    }

    public X3DMetadataObject(SFString name, SFString reference, String containerField) {
        this.name = name;
        this.reference = reference;
        this.containerField = containerField;
    }

    public X3DMetadataObject(){
        this (new SFString(), new SFString(), null);
    }

}
