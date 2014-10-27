/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

package x3d.fields;


import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

import java.util.StringTokenizer;
import java.util.ArrayList;


/**
 * 
 * @author peter
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "MFColorRGBA")
public class MFColorRGBA
        extends X3DArrayField<SFColorRGBA> {

    // <editor-fold defaultstate="expanded"
    // desc="Construction/Destruction/Initialization">

    public MFColorRGBA() {

        super();
    }

    public MFColorRGBA(SFColorRGBA... values) {

        super(values);
    }

    // </editor-fold>


    // <editor-fold defaultstate="expanded" desc="Public Methods">

    @Override
    public void add(SFColorRGBA color)
            throws IllegalArgumentException {

        if (color.getValue().size() % 4 != 0) {
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
                    String fourthToken = tokenizer.nextToken();

                    Float firstValue = Float.parseFloat(firstToken);
                    Float secondValue = Float.parseFloat(secondToken);
                    Float thirdValue = Float.parseFloat(thirdToken);
                    Float fourthValue = Float.parseFloat(fourthToken);

                    SFColorRGBA sfcolor = new SFColorRGBA();
                    sfcolor.setRedValue(firstValue);
                    sfcolor.setGreenValue(secondValue);
                    sfcolor.setBlueValue(thirdValue);
                    sfcolor.setAlphaValue(fourthValue);

                    this.add(sfcolor);
                }
                catch (Exception ex) {
                    throw new IllegalArgumentException(ex.getMessage());
                }
            }
        }
    }


    // </editor-fold>
}
