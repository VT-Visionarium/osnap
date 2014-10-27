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
@XmlType(name = "MFVec4f")
public class MFVec4f
        extends X3DArrayField <SFVec4f> {

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

                    Float firstValue = Float.parseFloat(firstToken);
                    Float secondValue = Float.parseFloat(secondToken);
                    Float thirdValue = Float.parseFloat(thirdToken);
                    Float fourthValue = Float.parseFloat(fourthToken);

                    SFVec4f sfVec4f = new SFVec4f(firstValue, secondValue, thirdValue, fourthValue);

                    this.add(sfVec4f);
                }
                catch (Exception ex){
                    throw new IllegalArgumentException(ex.getMessage());
                }
            }
        }
    }

    public MFVec4f(){
        super();
    }

}
