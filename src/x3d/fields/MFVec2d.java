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
@XmlType(name = "MFVec2d")
public class MFVec2d
        extends X3DArrayField <SFVec2d> {


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

                    Double firstValue = Double.parseDouble(firstToken);
                    Double secondValue = Double.parseDouble(secondToken);

                    SFVec2d sfVec2d = new SFVec2d(firstValue, secondValue);

                    this.add(sfVec2d);
                }
                catch (Exception ex){
                    throw new IllegalArgumentException(ex.getMessage());
                }
            }
        }
    }

    public MFVec2d(){
        super();
    }

}
