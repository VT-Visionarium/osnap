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

import x3d.fields.SFVec3f;
import x3d.fields.SFBool;


/**
 * <p>
 * Java class for an X3D Box.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{}X3DGeometryNode">
 *       &lt;attribute name="size" type="{}SFVec3f" default="2 2 2" />
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
@XmlRootElement(name = "Box")
public class Box
        extends X3DGeometryNode {

    /**
     * The size of the box.
     */
    @XmlAttribute(name = "size")
    protected SFVec3f size;


    /**
     * Determines whether the Box is solid or a wireframe.
     */
    @XmlAttribute(name = "solid")
    protected SFBool  solid;

    /**
     * Gets the value of the size property.
     * 
     * @return possible object is {@link SFVec3f }
     * 
     */
    public SFVec3f getSize() {

        return size;
    }

    /**
     * Sets the value of the size property.
     * 
     * @param value
     *            allowed object is {@link SFVec3f }
     * 
     */
    public void setSize(SFVec3f value) {

        this.size = value;
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
     * Creates a new, solid 2x2x2 Box.
     */
    public Box() {

        this(2.0f, 2.0f, 2.0f, true);
    }

    /**
     * Creates a new, solid Box with the provided dimensions.
     * 
     * @param xDimension
     *            the x dimension.
     * @param yDimension
     *            the y dimension.
     * @param zDimension
     *            the z dimension.
     */
    public Box(float xDimension, float yDimension, float zDimension) {

        this(xDimension, yDimension, zDimension, true);
    }

    /**
     * Creates a new, solid Box with the provided size.
     * 
     * @param size
     *            the size of the box.
     */
    public Box(SFVec3f size) {

        this(size, SFBool.TRUE);
    }

    /**
     * Creates a new Box with the provided dimensions and solidity.
     * 
     * @param xDimension
     *            the x dimension.
     * @param yDimension
     *            the y dimension.
     * @param zDimension
     *            the z dimension.
     * @param solid
     *            determines whether or not the box is solid.
     */
    public Box(float xDimension, float yDimension, float zDimension,
            boolean solid) {

        this(new SFVec3f(xDimension, yDimension, zDimension), new SFBool(solid));
    }

    /**
     * Creates a new Box with the provided size and solidity.
     * 
     * @param size
     *            the size of the box.
     * @param solid
     *            determines whether or not the box is solid.
     */
    public Box(SFVec3f size, SFBool solid) {

        this.solid = solid;
        this.size = size;
    }


}
