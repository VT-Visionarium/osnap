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
 * Java class for an X3D Cylinder.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{}X3DGeometryNode">
 *       &lt;attribute name="bottomVisible" type="{}SFBool" default="true" />
 *       &lt;attribute name="height" default="2">
 *         &lt;simpleType>
 *           &lt;restriction base="{}SFFloat">
 *             &lt;minExclusive value="0"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="radius" default="1">
 *         &lt;simpleType>
 *           &lt;restriction base="{}SFFloat">
 *             &lt;minExclusive value="0"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="sideVisible" type="{}SFBool" default="true" />
 *       &lt;attribute name="topVisible" type="{}SFBool" default="true" />
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
@XmlRootElement(name = "Cylinder")
public class Cylinder
        extends X3DGeometryNode {

    /**
     * Determines whether the bottom of the Cylinder is visible.
     */
    @XmlAttribute(name = "bottom")
    protected SFBool  bottomVisible;

    /**
     * The height of the Cylinder.
     */
    @XmlAttribute(name = "height")
    protected SFFloat height;

    /**
     * The radius of the Cylinder.
     */
    @XmlAttribute(name = "radius")
    protected SFFloat radius;

    /**
     * Determines whether the side of the Cylinder is visible.
     */
    @XmlAttribute(name = "side")
    protected SFBool  sideVisible;

    /**
     * Determines whether the top of the Cylinder is visible.
     */
    @XmlAttribute(name = "top")
    protected SFBool  topVisible;

    /**
     * Determines whether the Cylinder is solid or a wireframe.
     */
    @XmlAttribute(name = "solid")
    protected SFBool  solid;

    /**
     * Gets the value of the bottomVisible property.
     * 
     * @return possible object is {@link SFBool }
     * 
     */
    public SFBool isBottomVisible() {

        return bottomVisible;
    }

    /**
     * Sets the value of the bottomVisible property.
     * 
     * @param value
     *            allowed object is {@link SFBool }
     * 
     */
    public void setBottomVisible(SFBool value) {

        this.bottomVisible = value;
    }

    /**
     * Gets the value of the height property.
     * 
     * @return possible object is {@link SFFloat }
     * 
     */
    public SFFloat getHeight() {

        return height;
    }

    /**
     * Sets the value of the height property.
     * 
     * @param value
     *            allowed object is {@link SFFloat }
     * 
     */
    public void setHeight(SFFloat value) {

        this.height = value;
    }

    /**
     * Sets the value of the height property.
     * 
     * @param value
     *            allowed object is {@link SFFloat }
     * 
     */
    public void setHeight(float value) {

        this.setHeight(new SFFloat(value));
    }

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
     *            allowed object is {@link SFFloat }
     * 
     */
    public void setRadius(float value) {

        this.setRadius(new SFFloat(value));
    }

    /**
     * Gets the value of the sideVisible property.
     * 
     * @return possible object is {@link SFBool }
     * 
     */
    public SFBool isSideVisible() {

        return sideVisible;
    }

    /**
     * Sets the value of the sideVisible property.
     * 
     * @param value
     *            allowed object is {@link SFBool }
     * 
     */
    public void setSideVisible(SFBool value) {

        this.sideVisible = value;
    }

    /**
     * Sets the value of the sideVisible property.
     * 
     * @param value
     *            allowed object is {@link SFBool }
     * 
     */
    public void setSideVisible(boolean value) {

        this.setSideVisible(new SFBool(value));
    }

    /**
     * Gets the value of the topVisible property.
     * 
     * @return possible object is {@link SFBool }
     * 
     */
    public SFBool isTopVisible() {

        return topVisible;
    }

    /**
     * Sets the value of the topVisible property.
     * 
     * @param value
     *            allowed object is {@link SFBool }
     * 
     */
    public void setTopVisible(SFBool value) {

        this.topVisible = value;
    }

    /**
     * Sets the value of the topVisible property.
     * 
     * @param value
     *            allowed object is {@link SFBool }
     * 
     */
    public void setTopVisible(boolean value) {

        this.setTopVisible(new SFBool(value));
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
     * Sets the value of the solid property.
     * 
     * @param value
     *            allowed object is {@link SFBool }
     * 
     */
    public void setSolid(boolean value) {

        this.setSolid(new SFBool(value));
    }


    /**
     * Creates a new Cylinder with radius=1, height=2 that is solid with all
     * sides visible.
     */
    public Cylinder() {

        this(1.0f, 2.0f, true, true, true, true);
    }


    /**
     * Creates a new Cylinder with the provided radius and height that is solid
     * with all sides visible.
     * 
     * @param radius
     *            the radius of the cylinder.
     * @param height
     *            the height of the cylinder.
     */
    public Cylinder(float radius, float height) {

        this(radius, height, true);
    }


    /**
     * Creates a new Cylinder with the provided radius and height that is solid
     * with all sides visible.
     * 
     * @param radius
     *            the radius of the cylinder.
     * @param height
     *            the height of the cylinder.
     */
    public Cylinder(SFFloat radius, SFFloat height) {

        this(radius, height, SFBool.TRUE);
    }


    /**
     * Creates a new Cylinder with the provided radius and height that is solid
     * with all sides visible.
     * 
     * @param radius
     *            the radius of the cylinder.
     * @param height
     *            the height of the cylinder.
     * @param solid
     *            determines whether or not the cylinder is solid.
     */
    public Cylinder(float radius, float height, boolean solid) {

        this(radius, height, solid, true, true, true);
    }


    /**
     * Creates a new Cylinder with the provided radius and height that is solid
     * with all sides visible.
     * 
     * @param radius
     *            the radius of the cylinder.
     * @param height
     *            the height of the cylinder.
     * @param solid
     *            determines whether or not the cylinder is solid.
     */
    public Cylinder(SFFloat radius, SFFloat height, SFBool solid) {

        this(radius, height, solid, SFBool.TRUE, SFBool.TRUE, SFBool.TRUE);
    }


    /**
     * Creates a new Cylinder with the provided radius and height that is solid
     * with all sides visible.
     * 
     * @param radius
     *            the radius of the cylinder.
     * @param height
     *            the height of the cylinder.
     * @param solid
     *            determines whether or not the cylinder is solid.
     * @param bottomVisible
     *            determines whether or not the bottom of the cylinder is
     *            visible.
     * @param sideVisible
     *            determines whether or not the side of the cylinder is visible.
     * @param topVisible
     *            determines whether or not the top of the cylinder is visible.
     */
    public Cylinder(float radius, float height, boolean solid,
            boolean bottomVisible, boolean sideVisible, boolean topVisible) {

        this(new SFFloat(radius), new SFFloat(height), new SFBool(solid),
                new SFBool(bottomVisible), new SFBool(sideVisible), new SFBool(
                        topVisible));
    }


    /**
     * Creates a new Cylinder with the provided radius and height that is solid
     * with all sides visible.
     * 
     * @param radius
     *            the radius of the cylinder.
     * @param height
     *            the height of the cylinder.
     * @param solid
     *            determines whether or not the cylinder is solid.
     * @param bottomVisible
     *            determines whether or not the bottom of the cylinder is
     *            visible.
     * @param sideVisible
     *            determines whether or not the side of the cylinder is visible.
     * @param topVisible
     *            determines whether or not the top of the cylinder is visible.
     */
    public Cylinder(SFFloat radius, SFFloat height, SFBool solid,
            SFBool bottomVisible, SFBool sideVisible, SFBool topVisible) {

        this.bottomVisible = bottomVisible;
        this.height = height;
        this.radius = radius;
        this.sideVisible = sideVisible;
        this.topVisible = topVisible;
        this.solid = solid;
    }

}
