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
 * Java class for an X3D Cone.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{}X3DGeometryNode">
 *       &lt;attribute name="bottomRadius" default="1">
 *         &lt;simpleType>
 *           &lt;restriction base="{}SFFloat">
 *             &lt;minExclusive value="0"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="height" default="2">
 *         &lt;simpleType>
 *           &lt;restriction base="{}SFFloat">
 *             &lt;minExclusive value="0"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="sideVisible" type="{}SFBool" default="true" />
 *       &lt;attribute name="bottomVisible" type="{}SFBool" default="true" />
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
@XmlRootElement(name = "Cone")
public class Cone
        extends X3DGeometryNode {

    /**
     * The bottom radius of the Cone.
     */
    @XmlAttribute(name = "bottomRadius")
    protected SFFloat bottomRadius;

    /**
     * The height of the Cone.
     */
    @XmlAttribute(name = "height")
    protected SFFloat height;

    /**
     * Determines whether the side of the Cone is visible.
     */
    @XmlAttribute(name = "side")
    protected SFBool  sideVisible;

    /**
     * Determines whether the bottom of the Cone is visible.
     */
    @XmlAttribute(name = "bottom")
    protected SFBool  bottomVisible;


    /**
     * Determines whether the Cone is solid or a wireframe.
     */
    @XmlAttribute(name = "solid")
    protected SFBool  solid;

    /**
     * Gets the value of the bottomRadius property.
     * 
     * @return possible object is {@link SFFloat }
     * 
     */
    public SFFloat getBottomRadius() {

        return bottomRadius;
    }

    /**
     * Sets the value of the bottomRadius property.
     * 
     * @param value
     *            allowed object is {@link SFFloat }
     * 
     */
    public void setBottomRadius(SFFloat value) {

        if (value == null) {
            throw new IllegalArgumentException(
                    "Bottom radius of a cone cannot be null!");
        }
        if (value.getValue() <= 0.0f) {
            throw new IllegalArgumentException(
                    "Bottom radius of a cone has to be greater than zero!");
        }
        this.bottomRadius = value;
    }

    public void setBottomRadius(float value) {

        this.setBottomRadius(new SFFloat(value));
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

        if (value == null) {
            throw new IllegalArgumentException(
                    "Height of a cone cannot be null!");
        }
        if (value.getValue() <= 0.0f) {
            throw new IllegalArgumentException(
                    "Height of a cone has to be greater than zero!");
        }
        this.height = value;
    }

    public void setHeight(float value) {

        this.setHeight(new SFFloat(value));
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

    public void setSideVisible(boolean value) {

        this.setSideVisible(new SFBool(value));
    }

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
    protected void setBottom(SFBool value) {

        this.bottomVisible = value;
    }

    protected void setBottom(boolean value) {

        this.setBottom(new SFBool(value));
    }

    /**
     * Gets the value of the solid property.
     * 
     * @return possible object is {@link Boolean }
     * 
     */
    public SFBool isSolid() {

        return solid;
    }

    /**
     * Sets the value of the solid property.
     * 
     * @param value
     *            allowed object is {@link Boolean }
     * 
     */
    public void setSolid(SFBool value) {

        this.solid = value;
    }


    /**
     * Creates a new Cone with bottom radius=1, height=2 that is solid and
     * completely visible.
     */
    public Cone() {

        this(1.0f, 2.0f);
    }


    /**
     * Creates a new Cone with the provided bottom radius and height that is
     * solid and completely visible.
     * 
     * @param bottomRadius
     *            the bottom radius.
     * @param height
     *            the height.
     */
    public Cone(float bottomRadius, float height) {

        this(bottomRadius, height, true);
    }


    /**
     * Creates a new Cone with the provided bottom radius and height that is
     * solid and completely visible.
     * 
     * @param bottomRadius
     *            the bottom radius.
     * @param height
     *            the height.
     */
    public Cone(SFFloat bottomRadius, SFFloat height) {

        this(bottomRadius, height, SFBool.TRUE);
    }


    /**
     * Creates a new Cone with the provided bottom radius, height, and solidity
     * that is completely visible.
     * 
     * @param bottomRadius
     *            the bottom radius.
     * @param height
     *            the height.
     * @param solid
     *            whether or not the cone is solid or wireframe.
     */
    public Cone(float bottomRadius, float height, boolean solid) {

        this(bottomRadius, height, solid, true, true);
    }


    /**
     * Creates a new Cone with the provided bottom radius, height, and solidity
     * that is completely visible.
     * 
     * @param bottomRadius
     *            the bottom radius.
     * @param height
     *            the height.
     * @param solid
     *            whether or not the cone is solid or wireframe.
     */
    public Cone(SFFloat bottomRadius, SFFloat height, SFBool solid) {

        this(bottomRadius, height, solid, SFBool.TRUE, SFBool.TRUE);
    }


    /**
     * Creates a new Cone with the provided bottom radius, height, and solidity
     * that is completely visible.
     * 
     * @param bottomRadius
     *            the bottom radius.
     * @param height
     *            the height.
     * @param solid
     *            whether or not the cone is solid or wireframe.
     * @param bottomVisible
     *            whether or not the bottom of the cone is visible.
     * @param sideVisible
     *            whether or not the side of the cone is visible.
     */
    public Cone(float bottomRadius, float height, boolean solid,
            boolean bottomVisible, boolean sideVisible) {

        this(new SFFloat(bottomRadius), new SFFloat(height), new SFBool(solid),
                new SFBool(bottomVisible), new SFBool(sideVisible));
    }


    /**
     * Creates a new Cone with the provided bottom radius, height, and solidity
     * that is completely visible.
     * 
     * @param bottomRadius
     *            the bottom radius.
     * @param height
     *            the height.
     * @param solid
     *            whether or not the cone is solid or wireframe.
     * @param bottomVisible
     *            whether or not the bottom of the cone is visible.
     * @param sideVisible
     *            whether or not the side of the cone is visible.
     */
    public Cone(SFFloat bottomRadius, SFFloat height, SFBool solid,
            SFBool bottomVisible, SFBool sideVisible) {

        this.bottomRadius = bottomRadius;
        this.height = height;
        this.solid = solid;
        this.bottomVisible = bottomVisible;
        this.sideVisible = sideVisible;
    }

}
