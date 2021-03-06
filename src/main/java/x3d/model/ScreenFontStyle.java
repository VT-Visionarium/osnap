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

import x3d.fields.MFString;
import x3d.fields.SFBool;
import x3d.fields.SFFloat;
import x3d.fields.SFString;

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
 *     &lt;extension base="{}X3DFontStyleNode">
 *       &lt;attribute name="family" type="{}MFString" default=""SERIF"" />
 *       &lt;attribute name="horizontal" type="{}SFBool" default="true" />
 *       &lt;attribute name="justify" type="{}MFString" default=""BEGIN"" />
 *       &lt;attribute name="language" type="{}SFString" />
 *       &lt;attribute name="leftToRight" type="{}SFBool" default="true" />
 *       &lt;attribute name="pointSize" type="{}SFFloat" default="12.0" />
 *       &lt;attribute name="spacing" type="{}SFFloat" default="1.0" />
 *       &lt;attribute name="style" type="{}fontStyleValues" default="PLAIN" />
 *       &lt;attribute name="topToBottom" type="{}SFBool" default="true" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "ScreenFontStyle")
public class ScreenFontStyle extends X3DFontStyleNode {

	@XmlAttribute(name = "family")
	protected MFString family;
	@XmlAttribute(name = "horizontal")
	protected SFBool horizontal;
	@XmlAttribute(name = "justify")
	protected MFString justify;
	@XmlAttribute(name = "language")
	protected SFString language;
	@XmlAttribute(name = "leftToRight")
	protected SFBool leftToRight;
	@XmlAttribute(name = "pointSize")
	protected SFFloat pointSize;
	@XmlAttribute(name = "spacing")
	protected SFFloat spacing;
	@XmlAttribute(name = "style")
	protected FontStyleValues style;
	@XmlAttribute(name = "topToBottom")
	protected SFBool topToBottom;

	/**
	 * Gets the value of the family property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the family property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getFamily().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(scale) are allowed in the list {@link MFString }
	 * 
	 * 
	 */
	public MFString getFamily() {

		return this.family;
	}

	/**
	 * Gets the value of the horizontal property.
	 * 
	 * @return possible object is {@link SFBool }
	 * 
	 */
	public SFBool isHorizontal() {

		return horizontal;
	}

	/**
	 * Sets the value of the horizontal property.
	 * 
	 * @param value
	 *            allowed object is {@link SFBool }
	 * 
	 */
	public void setHorizontal(SFBool value) {
		this.horizontal = value;
	}

	/**
	 * Gets the value of the justify property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the justify property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getJustify().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(scale) are allowed in the list {@link MFString }
	 * 
	 * 
	 */
	public MFString getJustify() {

		return this.justify;
	}

	/**
	 * Gets the value of the language property.
	 * 
	 * @return possible object is {@link SFString }
	 * 
	 */
	public SFString getLanguage() {
		return language;
	}

	/**
	 * Sets the value of the language property.
	 * 
	 * @param value
	 *            allowed object is {@link SFString }
	 * 
	 */
	public void setLanguage(SFString value) {
		this.language = value;
	}

	/**
	 * Gets the value of the leftToRight property.
	 * 
	 * @return possible object is {@link SFBool }
	 * 
	 */
	public SFBool isLeftToRight() {
		return leftToRight;

	}

	/**
	 * Sets the value of the leftToRight property.
	 * 
	 * @param value
	 *            allowed object is {@link SFBool }
	 * 
	 */
	public void setLeftToRight(SFBool value) {
		this.leftToRight = value;
	}

	/**
	 * Gets the value of the pointSize property.
	 * 
	 * @return possible object is {@link SFFloat }
	 * 
	 */
	public SFFloat getPointSize() {
		return pointSize;
	}

	/**
	 * Sets the value of the pointSize property.
	 * 
	 * @param value
	 *            allowed object is {@link SFFloat }
	 * 
	 */
	public void setPointSize(SFFloat value) {
		this.pointSize = value;
	}

	/**
	 * Gets the value of the spacing property.
	 * 
	 * @return possible object is {@link SFFloat }
	 * 
	 */
	public SFFloat getSpacing() {
		return spacing;
	}

	/**
	 * Sets the value of the spacing property.
	 * 
	 * @param value
	 *            allowed object is {@link SFFloat }
	 * 
	 */
	public void setSpacing(SFFloat value) {
		this.spacing = value;
	}

	/**
	 * Gets the value of the style property.
	 * 
	 * @return possible object is {@link FontStyleValues }
	 * 
	 */
	public FontStyleValues getStyle() {
		return style;
	}

	/**
	 * Sets the value of the style property.
	 * 
	 * @param value
	 *            allowed object is {@link FontStyleValues }
	 * 
	 */
	public void setStyle(FontStyleValues value) {
		this.style = value;
	}

	/**
	 * Gets the value of the topToBottom property.
	 * 
	 * @return possible object is {@link SFBool }
	 * 
	 */
	public SFBool isTopToBottom() {
		return topToBottom;
	}

	/**
	 * Sets the value of the topToBottom property.
	 * 
	 * @param value
	 *            allowed object is {@link SFBool }
	 * 
	 */
	public void setTopToBottom(SFBool value) {
		this.topToBottom = value;
	}

	public ScreenFontStyle() {
		this.family = new MFString();
		this.family.getValue().add(new SFString("SERIF"));
		this.horizontal = new SFBool(true);
		this.justify = new MFString();
		this.justify.getValue().add(new SFString("BEGIN"));
		this.language = new SFString();
		this.leftToRight = new SFBool(true);
		this.pointSize = new SFFloat(12.0f);
		this.spacing = new SFFloat(1.0f);
		this.style = FontStyleValues.PLAIN;
		this.topToBottom = new SFBool(true);
	}

}
