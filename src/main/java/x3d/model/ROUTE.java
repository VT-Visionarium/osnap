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
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{}SceneGraphStructureNodeType">
 *       &lt;attribute name="fromNode" use="required" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *       &lt;attribute name="fromField" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="toNode" use="required" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *       &lt;attribute name="toField" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "ROUTE")
public class ROUTE
    extends SceneGraphStructureNode
{

    @XmlAttribute(name = "fromNode", required = true)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object fromNode;
    
    @XmlAttribute(name = "fromField", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String fromField;
    
    @XmlAttribute(name = "toNode", required = true)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object toNode;
    
    @XmlAttribute(name = "toField", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String toField;

    /**
     * Gets the value of the fromNode property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getFromNode() {
        return fromNode;
    }

    /**
     * Sets the value of the fromNode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setFromNode(Object value) {
        this.fromNode = value;
    }

    /**
     * Gets the value of the fromField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromField() {
        return fromField;
    }

    /**
     * Sets the value of the fromField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromField(String value) {
        this.fromField = value;
    }

    /**
     * Gets the value of the toNode property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getToNode() {
        return toNode;
    }

    /**
     * Sets the value of the toNode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setToNode(Object value) {
        this.toNode = value;
    }

    /**
     * Gets the value of the toField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToField() {
        return toField;
    }

    /**
     * Sets the value of the toField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToField(String value) {
        this.toField = value;
    }

}
