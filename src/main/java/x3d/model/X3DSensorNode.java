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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import x3d.fields.SFBool;

/**
 * <p>Java class for X3DSensorNode complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="X3DSensorNode">
 *   &lt;complexContent>
 *     &lt;extension base="{}X3DChildNode">
 *       &lt;attribute name="enabled" type="{}SFBool" default="true" />
 *       &lt;attribute name="isActive" type="{}SFBool" default="true" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "X3DSensorNode")
@XmlSeeAlso({
//    X3DPickingNode.class,
//    X3DNetworkSensorNode.class,
//    X3DEnvironmentalSensorNode.class,
    X3DKeyDeviceSensorNode.class,
    x3d.avalon.IOSensor.class,
    X3DPointingDeviceSensorNode.class
})
public abstract class X3DSensorNode
    extends X3DChildNode
{

    @XmlAttribute(name = "enabled")
    private SFBool enabled;
    
    @XmlAttribute(name = "isActive")
    private SFBool isActive;

    /**
     * Gets the value of the enabled property.
     * 
     * @return
     *     possible object is
     *     {@link SFBool }
     *     
     */
    public SFBool isEnabled() {
        if (enabled == null) {
            enabled = SFBool.TRUE;
        }
        return enabled;
        
    }

    /**
     * Sets the value of the enabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link SFBool }
     *     
     */
    public void setEnabled(SFBool value) {
        this.enabled = value;
    }

        /**
     * Gets the value of the isActive property.
     * 
     * @return
     *     possible object is
     *     {@link SFBool }
     *     
     */
    public SFBool isActive() {
        if (isActive == null) {
            isActive = SFBool.TRUE;
        }
        return isActive;
        
    }

    /**
     * Sets the value of the isActive property.
     * 
     * @param value
     *     allowed object is
     *     {@link SFBool }
     *     
     */
    public void setActive(SFBool value) {
        this.isActive = value;
    }
    
}