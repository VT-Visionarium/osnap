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
@XmlType(name = "MFBool")
@XmlRootElement(name = "MFBool")
public class MFBool
        extends X3DArrayField<SFBool> {


    public void add(Boolean value) {

        this.getValue().add(new SFBool(value));
    }


    public MFBool() {

        super();
    }

    public MFBool(Boolean... values) {

        super();
        for (Boolean value : values) {
            this.add(value);
        }
    }

    public MFBool(SFBool... values) {

        super(values);
    }

    @Override
    @XmlValue
    public String getStringValue() {

        return this.toString();
    }

    @Override
    public void setStringValue(String value) {

        if (value.contains(",")) {
            throw new IllegalArgumentException();
        }

        ArrayList<String> tokenList = this.getListFromString(value);

        for (String token : tokenList) {
            StringTokenizer tokenizer = new StringTokenizer(token, " ", false);
            while (tokenizer.hasMoreTokens()) {
                try {
                    String firstToken = tokenizer.nextToken();

                    Boolean firstValue = Boolean.parseBoolean(firstToken);

                    SFBool sfBool = new SFBool();
                    sfBool.setValue(firstValue);

                    this.add(sfBool);
                }
                catch (Exception ex) {
                    throw new IllegalArgumentException(ex.getMessage());
                }
            }
        }
    }

    @Override
    public String toString() {

        String returnValue = new String();
        int i = 0;
        for (SFBool field : this.getValue()) {
            if (field != null) {
                returnValue += field.toString();
                if (i != this.getValue().size() - 1) {
                    returnValue += " ";
                }
            }
            i++;
        }
        return returnValue;
    }

}
