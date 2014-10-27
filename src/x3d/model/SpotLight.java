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
import x3d.fields.SFFloat;
import x3d.fields.SFBool;

/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{}X3DLightNode">
 *       &lt;attribute name="attenuation" type="{}SFVec3f" default="1 0 0" />
 *       &lt;attribute name="beamWidth" default="0.7854">
 *         &lt;simpleType>
 *           &lt;restriction base="{}SFFloat">
 *             &lt;minExclusive value="0"/>
 *             &lt;maxInclusive value="1.5708"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="cutOffAngle" default="1.5708">
 *         &lt;simpleType>
 *           &lt;restriction base="{}SFFloat">
 *             &lt;minExclusive value="0"/>
 *             &lt;maxInclusive value="1.5708"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="direction" type="{}SFVec3f" default="0 0 -1" />
 *       &lt;attribute name="location" type="{}SFVec3f" default="0 0 0" />
 *       &lt;attribute name="radius" default="100">
 *         &lt;simpleType>
 *           &lt;restriction base="{}SFFloat">
 *             &lt;minInclusive value="0"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="global" type="{}SFBool" default="true" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "SpotLight")
public class SpotLight
    extends X3DLightNode
{

    @XmlAttribute(name = "attenuation")
    protected SFVec3f attenuation;
    @XmlAttribute(name = "beamWidth")
    protected SFFloat beamWidth;
    @XmlAttribute(name = "cutOffAngle")
    protected SFFloat cutOffAngle;
    @XmlAttribute(name = "direction")
    protected SFVec3f direction;
    @XmlAttribute(name = "location")
    protected SFVec3f location;
    @XmlAttribute(name = "radius")
    protected SFFloat radius;
    @XmlAttribute(name = "global")
    protected SFBool global;

    /**
     * Gets the value of the attenuation property.
     * 
     * @return
     *     possible object is
     *     {@link SFVec3f }
     *     
     */
    public SFVec3f getAttenuation() {
        return attenuation;
    }

    /**
     * Sets the value of the attenuation property.
     * 
     * @param value
     *     allowed object is
     *     {@link SFVec3f }
     *     
     */
    public void setAttenuation(SFVec3f value) {
        this.attenuation = value;
    }

    /**
     * Gets the value of the beamWidth property.
     * 
     * @return
     *     possible object is
     *     {@link SFFloat }
     *     
     */
    public SFFloat getBeamWidth() {
        return beamWidth;
    }

    /**
     * Sets the value of the beamWidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link SFFloat }
     *     
     */
    public void setBeamWidth(SFFloat value) {
        if (value.getValue() < 0 || value.getValue() > 1.5708f){
            throw new IllegalArgumentException("Beam widht has to be between 0 and 1.5708!");
        }
        this.beamWidth = value;
    }

    /**
     * Gets the value of the cutOffAngle property.
     * 
     * @return
     *     possible object is
     *     {@link SFFloat }
     *     
     */
    public SFFloat getCutOffAngle() {
        return cutOffAngle;
    }

    /**
     * Sets the value of the cutOffAngle property.
     * 
     * @param value
     *     allowed object is
     *     {@link SFFloat }
     *     
     */
    public void setCutOffAngle(SFFloat value) {
        if (value.getValue() < 0 || value.getValue() > 1.5708f){
            throw new IllegalArgumentException("Beam widht has to be between 0 and 1.5708!");
        }
        this.cutOffAngle = value;
    }

    /**
     * Gets the value of the direction property.
     * 
     * @return
     *     possible object is
     *     {@link SFVec3f }
     *     
     */
    public SFVec3f getDirection() {
        return direction;
    }

    /**
     * Sets the value of the direction property.
     * 
     * @param value
     *     allowed object is
     *     {@link SFVec3f }
     *     
     */
    public void setDirection(SFVec3f value) {
        this.direction = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link SFVec3f }
     *     
     */
    public SFVec3f getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link SFVec3f }
     *     
     */
    public void setLocation(SFVec3f value) {
        this.location = value;
    }

    /**
     * Gets the value of the radius property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public SFFloat getRadius() {
        return radius;
    }

    /**
     * Sets the value of the radius property.
     * 
     * @param value
     *     allowed object is
     *     {@link SFFloat }
     *     
     */
    public void setRadius(SFFloat value) {
        if (value.getValue() < 0) {
            throw new IllegalArgumentException("Radius has to be greater than or equal to zero!");
        }
        this.radius = value;
    }

    /**
     * Gets the value of the global property.
     * 
     * @return
     *     possible object is
     *     {@link SFBool }
     *     
     */
    public SFBool isGlobal() {
        return global;
    }

    /**
     * Sets the value of the global property.
     * 
     * @param value
     *     allowed object is
     *     {@link SFBool }
     *     
     */
    public void setGlobal(SFBool value) {
        this.global = value;
    }

    public SpotLight() {
        this.attenuation = new SFVec3f(1f, 0f, 0f);
        this.beamWidth = new SFFloat(0.7854f);
        this.cutOffAngle = new SFFloat(1.5708f);
        this.direction = new SFVec3f (0f, 0f, -1f);
        this.location = new SFVec3f(0f, 0f, 0f);
        this.radius = new SFFloat(100f);
        this.global = new SFBool (true);
    }

}
