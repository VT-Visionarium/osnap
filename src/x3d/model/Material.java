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

import x3d.fields.SFColor;
import x3d.fields.SFFloat;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{}X3DMaterialNode">
 *       &lt;attribute name="ambientIntensity" default="0.2">
 *         &lt;simpleType>
 *           &lt;restriction base="{}SFFloat">
 *             &lt;minInclusive value="0"/>
 *             &lt;maxInclusive value="1"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="diffuseColor" type="{}SFColor" default="0.8 0.8 0.8" />
 *       &lt;attribute name="emissiveColor" type="{}SFColor" default="0 0 0" />
 *       &lt;attribute name="shininess" default="0.2">
 *         &lt;simpleType>
 *           &lt;restriction base="{}SFFloat">
 *             &lt;minInclusive value="0"/>
 *             &lt;maxInclusive value="1"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="specularColor" type="{}SFColor" default="0 0 0" />
 *       &lt;attribute name="transparency" default="0">
 *         &lt;simpleType>
 *           &lt;restriction base="{}SFFloat">
 *             &lt;minInclusive value="0"/>
 *             &lt;maxInclusive value="1"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Material")
public class Material
    extends X3DMaterialNode
{

    @XmlAttribute(name = "ambientIntensity")
    protected SFFloat ambientIntensity;

    @XmlAttribute(name = "diffuseColor")
    protected SFColor diffuseColor;

    @XmlAttribute(name = "emissiveColor")
    protected SFColor emissiveColor;

    @XmlAttribute(name = "shininess")
    protected SFFloat shininess;

    @XmlAttribute(name = "specularColor")
    protected SFColor specularColor;

    @XmlAttribute(name = "transparency")
    protected SFFloat transparency;

    /**
     * Gets the value of the ambientIntensity property.
     * 
     * @return
     *     possible object is
     *     {@link SFFloat }
     *     
     */
    public SFFloat getAmbientIntensity() {
        if (ambientIntensity == null) {
            ambientIntensity = new SFFloat (0.2f);
        }
        return ambientIntensity;
    }

    /**
     * Sets the value of the ambientIntensity property.
     * 
     * @param value
     *     allowed object is
     *     {@link SFFloat }
     *     
     */
    public void setAmbientIntensity ( SFFloat value ) {
        this.ambientIntensity = value;
    }

    /**
     * Gets the value of the diffuseColor property.
     * 
     * @return
     *     possible object is
     *     {@link SFColor }
     *     
     */
    public SFColor getDiffuseColor() {
        if (diffuseColor == null) {
            diffuseColor = new SFColor (0.8f, 0.8f, 0.8f);
        }
        return diffuseColor;
    }

    /**
     * Sets the value of the diffuseColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link SFColor }
     *     
     */
    public void setDiffuseColor ( SFColor value ) {
        this.diffuseColor = value;
    }

    /**
     * Gets the value of the emissiveColor property.
     * 
     * @return
     *     possible object is
     *     {@link SFColor }
     *     
     */
    public SFColor getEmissiveColor() {
        if (emissiveColor == null) {
            emissiveColor = new SFColor (0f, 0f, 0f);
        }
            return emissiveColor;
    }

    /**
     * Sets the value of the emissiveColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link SFColor }
     *     
     */
    public void setEmissiveColor(SFColor value) {
        this.emissiveColor = value;
    }

    /**
     * Gets the value of the shininess property.
     * 
     * @return
     *     possible object is
     *     {@link SFFloat }
     *     
     */
    public SFFloat getShininess() {
        if (shininess == null) {
            shininess = new SFFloat (0.2f);
        }
        return shininess;
    }

    /**
     * Sets the value of the shininess property.
     * 
     * @param value
     *     allowed object is
     *     {@link SFFloat }
     *     
     */
    public void setShininess ( SFFloat value ) {
        this.shininess = value;
    }

    /**
     * Gets the value of the specularColor property.
     * 
     * @return
     *     possible object is
     *     {@link SFColor }
     *     
     */
    public SFColor getSpecularColor() {
        if (specularColor == null) {
            specularColor = new SFColor (0f, 0f, 0f);
        }
        return specularColor;
    }

    /**
     * Sets the value of the specularColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link SFColor }
     *     
     */
    public void setSpecularColor ( SFColor value ) {
        this.specularColor = value;
    }

    /**
     * Gets the value of the transparency property.
     * 
     * @return
     *     possible object is
     *     {@link SFFloat }
     *     
     */
    public SFFloat getTransparency() {
        if (transparency == null) {
            transparency = new SFFloat(0f);
        }
        return transparency;
    }

    /**
     * Sets the value of the transparency property.
     * 
     * @param value
     *     allowed object is
     *     {@link SFFloat }
     *     
     */
    public void setTransparency ( SFFloat value ) {
        this.transparency = value;
    }

}
