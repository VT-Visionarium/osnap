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
import javax.xml.bind.annotation.XmlType;

import x3d.fields.MFFloat;

/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{}X3DGroupingNode">
 *       &lt;attribute name="clipBoundary" type="{}MFFloat" default="0 1 0 1" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Viewport")
public class Viewport
    extends X3DGroupingNode
{

    @XmlAttribute(name = "clipBoundary")
    protected MFFloat clipBoundary;

    /**
     * Gets the value of the clipBoundary property.
     * 
     * @return
     *     possible object is
     *     {@link MFFloat }
     *     
     */
    public MFFloat getClipBoundary() {
            return clipBoundary;
    }

    /**
     * Sets the value of the clipBoundary property.
     * 
     * @param value
     *     allowed object is
     *     {@link MFFloat }
     *     
     */
    public void setClipBoundary(MFFloat value) {
        this.clipBoundary = value;
    }

    public Viewport() {
        this.clipBoundary = new MFFloat();
        this.clipBoundary.add(0f);
        this.clipBoundary.add(1f);
        this.clipBoundary.add(0f);
        this.clipBoundary.add(1f);
    }



}