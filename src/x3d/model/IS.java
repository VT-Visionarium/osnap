//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.06.16 at 09:21:15 AM EDT 
//


package x3d.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{}SceneGraphStructureNodeType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element ref="{}connect"/>
 *       &lt;/choice>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "connect"
})
@XmlRootElement(name = "IS")
public class IS
    extends SceneGraphStructureNode
{

    protected List<Connect> connect;

    /**
     * Gets the value of the connect property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the connect property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConnect().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(scale) are allowed in the list
     * {@link Connect }
     * 
     * 
     */
    public List<Connect> getConnect() {
        if (connect == null) {
            connect = new ArrayList<>();
        }
        return this.connect;
    }

}
