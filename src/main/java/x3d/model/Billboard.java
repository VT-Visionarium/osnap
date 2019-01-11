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

import x3d.fields.SFVec3f;

/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{}X3DGroupingNode">
 *       &lt;attribute name="axisOfRotation" type="{}SFVec3f" default="0 1 0" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Billboard")
public class Billboard
    extends X3DGroupingNode
{

    @XmlAttribute(name = "axisOfRotation")
    protected SFVec3f axisOfRotation;

    /**
     * Gets the value of the axisOfRotation property.
     * 
     * @return
     *     possible object is
     *     {@link SFVec3f }
     *     
     */
    public SFVec3f getAxisOfRotation() {
        if (this.axisOfRotation == null) {
            this.axisOfRotation = new SFVec3f(0.0f, 1.0f, 0.0f);
        }
        return axisOfRotation;
    }

    /**
     * Sets the value of the axisOfRotation property.
     * 
     * @param value
     *     allowed object is
     *     {@link SFVec3f }
     *     
     */
    public void setAxisOfRotation(SFVec3f value) {
        this.axisOfRotation = value;
    }
}