//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.06.16 at 09:21:15 AM EDT 
//


package x3d.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fontStyleValues.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="fontStyleValues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="PLAIN"/>
 *     &lt;enumeration value="BOLD"/>
 *     &lt;enumeration value="ITALIC"/>
 *     &lt;enumeration value="BOLDITALIC"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "fontStyleValues")
@XmlEnum
public enum FontStyleValues {

    PLAIN,
    BOLD,
    ITALIC,
    BOLDITALIC;

    public String value() {
        return name();
    }

    public static FontStyleValues fromValue(String v) {
        return valueOf(v);
    }

}
