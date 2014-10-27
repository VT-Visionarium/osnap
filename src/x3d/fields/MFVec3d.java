/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
@XmlType(name = "MFVec3d")
public class MFVec3d
        extends X3DArrayField<SFVec3d> {


    @Override
    @XmlValue
    public String getStringValue (){
        return this.toString();
    }

    @Override
    public void setStringValue(String value) {

        ArrayList<String> tokenList = this.getListFromString(value);

        for (String token : tokenList){
            StringTokenizer tokenizer = new StringTokenizer(token, " ", false);
            while (tokenizer.hasMoreTokens()){
                try{
                    String firstToken = tokenizer.nextToken();
                    String secondToken = tokenizer.nextToken();
                    String thirdToken = tokenizer.nextToken();

                    Double firstValue = Double.parseDouble(firstToken);
                    Double secondValue = Double.parseDouble(secondToken);
                    Double thirdValue = Double.parseDouble(thirdToken);

                    SFVec3d sfVec3d = new SFVec3d(firstValue, secondValue, thirdValue);

                    this.add(sfVec3d);
                }
                catch (Exception ex){
                    throw new IllegalArgumentException(ex.getMessage());
                }
            }
        }
    }

    public MFVec3d(){
        super();
    }

}
