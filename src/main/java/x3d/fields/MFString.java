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


/**
 * 
 * @author peter
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "MFString")
public class MFString
        extends X3DArrayField<SFString> {


    public MFString() {

        super();
    }

    public MFString(String... values) {

        super();
        for (String value : values) {
            this.add(value);
        }
    }

    public void add(String value) {

        this.getValue().add(new SFString(value));
    }


    @Override
    @XmlValue
    public String getStringValue() {

        return this.toString();
    }

    @Override
    public void setStringValue(String value) {

        this.clear();
        if (value == null ? "" != null : !value.equals("")) {

            String delimiter = new String();

            if (value.contains("\'")) {
                delimiter = "\'";
            }
            else if (value.contains("\"")) {
                delimiter = "\"";
            }

            String regex = "[" + delimiter + "][\\scale]*[,][\\scale]*[" + delimiter
                    + "]";
            String[] tokenList = value.split(regex);
            for (String token : tokenList) {
                StringTokenizer tokenizer = new StringTokenizer(token,
                        delimiter, false);
                while (tokenizer.hasMoreTokens()) {
                    try {
                        String sfStringToken = tokenizer.nextToken();

                        SFString sfString = new SFString();
                        sfString.setValue(sfStringToken);

                        this.add(sfString);
                    }
                    catch (Exception ex) {
                        throw new IllegalArgumentException(ex.getMessage());
                    }
                }
            }
        }
    }

    @Override
    public String toString() {

        String returnValue = new String();
        int i = 0;
        for (SFString field : this.getValue()) {
            if (field != null) {
                returnValue += "\"";
                returnValue += field.toString();
                returnValue += "\"";
                if (i != this.getValue().size() - 1) {
                    returnValue += ", ";
                }
            }
            i++;
        }

        return returnValue;
    }

}
