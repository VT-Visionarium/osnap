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
@XmlType(name = "MFDouble")
public class MFDouble
        extends X3DArrayField<SFDouble> {

    public MFDouble() {

        super();
    }

    public MFDouble(Double... values) {

        super();
        for (Double value : values) {
            this.add(value);
        }
    }

    public MFDouble(SFDouble... values) {

        super();
    }

    public void add(Double value) {

        this.getValue().add(new SFDouble(value));
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

                    Double firstValue = Double.parseDouble(firstToken);

                    SFDouble sfDouble = new SFDouble();
                    sfDouble.setValue(firstValue);

                    this.add(sfDouble);
                }
                catch (Exception ex) {
                    throw new IllegalArgumentException(ex.getMessage());
                }
            }
        }
    }

}
