/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

package x3d.fields;


import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import java.util.StringTokenizer;
import java.util.ArrayList;


/**
 * 
 * @author peter
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "MFColor")
@XmlRootElement(name = "MFColor")
public class MFColor
        extends X3DArrayField<SFColor> {


    // <editor-fold defaultstate="expanded"
    // desc="Construction/Destruction/Initialization">

    public MFColor() {

        super();
    }

    public MFColor(SFColor... values) {

        super(values);
    }

    // </editor-fold>


    // <editor-fold defaultstate="expanded" desc="Public Methods">
    @Override
    public void add(SFColor color)
            throws IllegalArgumentException {

        if (color.getValue().size() % 3 != 0) {
            throw new IllegalArgumentException();
        }
        this.getValue().add(color);

    }

    @Override
    @XmlValue
    public String getStringValue() {

        return this.toString();
    }

    @Override
    public void setStringValue(String value) {

        ArrayList<String> tokenList = this.getListFromString(value);

        for (String token : tokenList) {
            StringTokenizer tokenizer = new StringTokenizer(token, " ", false);
            while (tokenizer.hasMoreTokens()) {
                try {
                    String firstToken = tokenizer.nextToken();
                    String secondToken = tokenizer.nextToken();
                    String thirdToken = tokenizer.nextToken();

                    Float firstValue = Float.parseFloat(firstToken);
                    Float secondValue = Float.parseFloat(secondToken);
                    Float thirdValue = Float.parseFloat(thirdToken);

                    SFColor sfcolor = new SFColor();
                    sfcolor.setRedValue(firstValue);
                    sfcolor.setGreenValue(secondValue);
                    sfcolor.setBlueValue(thirdValue);

                    this.add(sfcolor);
                }
                catch (Exception ex) {
                    throw new IllegalArgumentException(ex.getMessage());
                }
            }
        }
    }

}
