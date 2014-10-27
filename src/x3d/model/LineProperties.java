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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import x3d.fields.SFFloat;
import x3d.fields.SFBool;
import x3d.fields.SFInt32;

/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{}X3DAppearanceChildNode">
 *       &lt;attribute name="applied" type="{}SFBool" default="true" />
 *       &lt;attribute name="linetype" type="{}SFInt32" default="1" />
 *       &lt;attribute name="linewidthScaleFactor" type="{}SFFloat" default="0" />
 *       &lt;attribute name="containerField" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" default="lineProperties" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "LineProperties")
public class LineProperties
        extends X3DAppearanceChildNode {

    @XmlAttribute(name = "applied")
    protected SFBool applied;
    @XmlAttribute(name = "linetype")
    protected SFInt32 linetype;
    @XmlAttribute(name = "linewidthScaleFactor")
    protected SFFloat linewidthScaleFactor;
    @XmlAttribute(name = "containerField")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String containerField;

    /**
     * Gets the value of the applied property.
     * 
     * @return
     *     possible object is
     *     {@link SFBool }
     *     
     */
    public SFBool isApplied() {
        if (applied == null) {
            applied = SFBool.TRUE;
        }
        return applied;
    }

    /**
     * Sets the value of the applied property.
     * 
     * @param value
     *     allowed object is
     *     {@link SFBool }
     *     
     */
    public void setApplied(SFBool value) {
        this.applied = value;
    }

    /**
     * Gets the value of the linetype property.
     * 
     * @return
     *     possible object is
     *     {@link SFInt32 }
     *     
     */
    public SFInt32 getLinetype() {
        if (linetype == null) {
            linetype = new SFInt32(1);
        }
        return linetype;
    }

    /**
     * Sets the value of the linetype property.
     * 
     * @param value
     *     allowed object is
     *     {@link SFInt32 }
     *     
     */
    public void setLinetype(SFInt32 value) {
        this.linetype = value;
    }

    /**
     * Gets the value of the linewidthScaleFactor property.
     * 
     * @return
     *     possible object is
     *     {@link SFFloat }
     *     
     */
    public SFFloat getLinewidthScaleFactor() {
        if (linewidthScaleFactor == null) {
            linewidthScaleFactor = new SFFloat (0f);
        }
        return linewidthScaleFactor;
    }

    /**
     * Sets the value of the linewidthScaleFactor property.
     * 
     * @param value
     *     allowed object is
     *     {@link SFFloat }
     *     
     */
    public void setLinewidthScaleFactor(SFFloat value) {
        this.linewidthScaleFactor = value;
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
            return "lineProperties";
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
}
