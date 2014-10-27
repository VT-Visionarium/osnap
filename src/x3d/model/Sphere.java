//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB)
// Reference Implementation, v2.2.4
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source
// schema.
// Generated on: 2011.06.16 at 09:21:15 AM EDT
//


package x3d.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import x3d.fields.SFFloat;
import x3d.fields.SFBool;


/**
 * <p>
 * Java class for an X3D Sphere.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{}X3DGeometryNode">
 *       &lt;attribute name="radius" default="1">
 *         &lt;simpleType>
 *           &lt;restriction base="{}SFFloat">
 *             &lt;minExclusive value="0"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="solid" type="{}SFBool" default="true" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Sphere")
public class Sphere
        extends X3DGeometryNode {

    /**
     * The radius of the Sphere.
     */
    @XmlAttribute(name = "radius")
    protected SFFloat radius;


    /**
     * Determines whether the Sphere is solid or a wireframe.
     */
    @XmlAttribute(name = "solid")
    protected SFBool  solid;

    /**
     * Gets the value of the radius property.
     * 
     * @return possible object is {@link SFFloat }
     * 
     */
    public SFFloat getRadius() {

        return radius;
    }

    /**
     * Sets the value of the radius property.
     * 
     * @param value
     *            allowed object is {@link SFFloat }
     * 
     */
    public void setRadius(SFFloat value) {

        this.radius = value;
    }

    /**
     * Sets the value of the radius property.
     * 
     * @param value
     *            allowed object is {@link Float }
     * 
     */
    public void setRadius(float value) {

        this.setRadius(new SFFloat(value));
    }

    /**
     * Gets the value of the solid property.
     * 
     * @return possible object is {@link SFBool }
     * 
     */
    public SFBool isSolid() {

        return solid;
    }

    /**
     * Sets the value of the solid property.
     * 
     * @param value
     *            allowed object is {@link SFBool }
     * 
     */
    public void setSolid(SFBool value) {

        this.solid = value;
    }

    /**
     * Creates a new, solid Sphere with radius=1.
     */
    public Sphere() {

        this.radius = new SFFloat(1.0f);
        this.solid = new SFBool(true);
    }

    /**
     * Creates a new, solid Sphere with the provided radius.
     * 
     * @param radius
     *            the radius.
     */
    public Sphere(float radius) {

        this(radius, true);
    }


    /**
     * Creates a new, solid Sphere with the provided radius.
     * 
     * @param radius
     *            the radius.
     */
    public Sphere(SFFloat radius) {

        this(radius, SFBool.TRUE);
    }


    /**
     * Creates a new Sphere with the provided radius and solidity.
     * 
     * @param radius
     *            the radius.
     * @param solid
     *            determines whether or not the Sphere is solid.
     */
    public Sphere(float radius, boolean solid) {

        this(new SFFloat(radius), new SFBool(solid));
    }


    /**
     * Creates a new Sphere with the provided radius and solidity.
     * 
     * @param radius
     *            the radius.
     * @param solid
     *            determines whether or not the Sphere is solid.
     */
    public Sphere(SFFloat radius, SFBool solid) {

        this.radius = radius;
        this.solid = solid;
    }


}
