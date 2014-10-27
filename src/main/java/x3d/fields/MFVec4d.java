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
@XmlType(name = "MFVec4d")
public class MFVec4d
        extends X3DArrayField <SFVec4d>{

    @Override
    @XmlValue
    public String getStringValue (){
        return this.toString();
    }

    @Override
    public void setStringValue (String value){

        ArrayList<String> tokenList = this.getListFromString(value);

        for (String token : tokenList){
            StringTokenizer tokenizer = new StringTokenizer(token, " ", false);
            while (tokenizer.hasMoreTokens()){
                try{
                    String firstToken = tokenizer.nextToken();
                    String secondToken = tokenizer.nextToken();
                    String thirdToken = tokenizer.nextToken();
                    String fourthToken = tokenizer.nextToken();

                    Double firstValue = Double.parseDouble(firstToken);
                    Double secondValue = Double.parseDouble(secondToken);
                    Double thirdValue = Double.parseDouble(thirdToken);
                    Double fourthValue = Double.parseDouble(fourthToken);

                    SFVec4d sfVec4d = new SFVec4d(firstValue, secondValue, thirdValue, fourthValue);

                    this.add(sfVec4d);
                }
                catch (Exception ex){
                    throw new IllegalArgumentException(ex.getMessage());
                }
            }
        }
    }

    public MFVec4d(){
        super();
    }

}
