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
@XmlType(name = "MFFloat")
@XmlRootElement(name = "MFFloat")
public class MFFloat
        extends X3DArrayField<SFFloat> {

    public MFFloat() {

        super();
    }

    public MFFloat(Float... values) {

        super();
        for (Float value : values) {
            this.add(value);
        }
    }

    public MFFloat(SFFloat... values) {

        super();
        for (SFFloat value : values) {
            this.add(value);
        }
    }

    public void add(Float value) {

        this.getValue().add(new SFFloat(value));
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

                    Float firstValue = Float.parseFloat(firstToken);

                    SFFloat sfFloat = new SFFloat();
                    sfFloat.setValue(firstValue);

                    this.add(sfFloat);
                }
                catch (Exception ex) {
                    throw new IllegalArgumentException(ex.getMessage());
                }
            }
        }
    }

}
