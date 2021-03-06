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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import x3d.fields.SFBool;
import x3d.fields.SFRotation;
import x3d.fields.SFString;
import x3d.fields.SFVec3f;


/**
 * <p>
 * Java class for X3DViewpointNode complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="X3DViewpointNode">
 *   &lt;complexContent>
 *     &lt;extension base="{}X3DBindableNode">
 *       &lt;attribute name="centerOfRotation" type="{}SFVec3f" default="0 0 0" />
 *       &lt;attribute name="description" type="{}SFString" />
 *       &lt;attribute name="jump" type="{}SFBool" default="true" />
 *       &lt;attribute name="orientation" type="{}SFRotation" default="0 0 1 0" />
 *       &lt;attribute name="position" type="{}SFVec3f" default="0 0 10" />
 *       &lt;attribute name="retainUserOffsets" type="{}SFBool" default="false" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "X3DViewpointNode")
@XmlSeeAlso({ OrthoViewpoint.class, Viewpoint.class })
public abstract class X3DViewpointNode
        extends X3DBindableNode {

    @XmlAttribute(name = "centerOfRotation")
    private SFVec3f    centerOfRotation;
    @XmlAttribute(name = "description")
    private SFString   description;
    @XmlAttribute(name = "jump")
    private SFBool     jump;
    @XmlAttribute(name = "orientation")
    private SFRotation orientation;
    @XmlAttribute(name = "position")
    private SFVec3f    position;
    @XmlAttribute(name = "retainUserOffsets")
    private SFBool     retainUserOffsets;

    /**
     * Gets the value of the centerOfRotation property.
     * 
     * @return possible object is {@link SFVec3f }
     * 
     */
    public SFVec3f getCenterOfRotation() {

        return centerOfRotation;
    }

    /**
     * Sets the value of the centerOfRotation property.
     * 
     * @param value
     *            allowed object is {@link SFVec3f }
     * 
     */
    public void setCenterOfRotation(SFVec3f value) {

        this.centerOfRotation = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return possible object is {@link SFString }
     * 
     */
    public SFString getDescription() {

        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *            allowed object is {@link SFString }
     * 
     */
    public void setDescription(SFString value) {

        this.description = value;
    }

    /**
     * Gets the value of the jump property.
     * 
     * @return possible object is {@link SFBool }
     * 
     */
    public SFBool isJump() {

        return jump;
    }

    /**
     * Sets the value of the jump property.
     * 
     * @param value
     *            allowed object is {@link SFBool }
     * 
     */
    public void setJump(SFBool value) {

        this.jump = value;
    }

    /**
     * Gets the value of the orientation property.
     * 
     * @return possible object is {@link SFRotation }
     * 
     */
    public SFRotation getOrientation() {

        return orientation;
    }

    /**
     * Sets the value of the orientation property.
     * 
     * @param value
     *            allowed object is {@link SFRotation }
     * 
     */
    public void setOrientation(SFRotation value) {

        this.orientation = value;
    }

    /**
     * Gets the value of the position property.
     * 
     * @return possible object is {@link SFVec3f }
     * 
     */
    public SFVec3f getPosition() {

        return position;
    }

    /**
     * Sets the value of the position property.
     * 
     * @param value
     *            allowed object is {@link SFVec3f }
     * 
     */
    public void setPosition(SFVec3f value) {

        this.position = value;
    }

    /**
     * Gets the value of the retainUserOffsets property.
     * 
     * @return possible object is {@link SFBool }
     * 
     */
    public SFBool isRetainUserOffsets() {

        return retainUserOffsets;
    }

    /**
     * Sets the value of the retainUserOffsets property.
     * 
     * @param value
     *            allowed object is {@link SFBool }
     * 
     */
    public void setRetainUserOffsets(SFBool value) {

        this.retainUserOffsets = value;
    }

    /**
     * Creates a new instance of the {@link X3DViewpointNode} class.
     */
    public X3DViewpointNode() {

        this.centerOfRotation = new SFVec3f(0f, 0f, 0f);
        this.description = new SFString("");
        this.jump = SFBool.TRUE;
        this.orientation = new SFRotation();
        this.position = new SFVec3f(0f, 0f, 10f);
        this.retainUserOffsets = SFBool.FALSE;
    }

}
