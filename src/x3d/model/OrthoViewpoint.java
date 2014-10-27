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

import x3d.fields.MFFloat;


/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{}X3DViewpointNode">
 *       &lt;attribute name="fieldOfView" type="{}MFFloat" default="-1 -1 1 1" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "OrthoViewpoint")
public class OrthoViewpoint
        extends X3DViewpointNode {

    @XmlAttribute(name = "fieldOfView")
    private MFFloat fieldOfView;

    /**
     * Gets the value of the fieldOfView property.
     * 
     * @return possible object is {@link MFFloat }
     * 
     */
    public MFFloat getFieldOfView() {

        return fieldOfView;
    }

    /**
     * Sets the value of the fieldOfView property.
     * 
     * @param value
     *            allowed object is {@link MFFloat }
     * 
     */
    public void setFieldOfView(MFFloat value) {

        this.fieldOfView = value;
    }


    /**
     * Creates a new instance of the {@link OrthoViewpoint} class.
     */
    public OrthoViewpoint() {

        this.fieldOfView = new MFFloat(-1f, -1f, 1f, 1f);
    }
}
