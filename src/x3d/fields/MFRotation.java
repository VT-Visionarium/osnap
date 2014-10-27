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
@XmlType(name = "MFRotation")
public class MFRotation
        extends X3DArrayField <SFRotation> {


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
                    String sfRotationToken = tokenizer.nextToken() + " ";
                    sfRotationToken += tokenizer.nextToken() + " ";
                    sfRotationToken += tokenizer.nextToken() + " ";
                    sfRotationToken += tokenizer.nextToken();

                    SFRotation sfRotation = new SFRotation();

                    sfRotation.setStringValue(sfRotationToken);

                    this.add(sfRotation);
                }
                catch (Exception ex){
                    throw new IllegalArgumentException(ex.getMessage());
                }
            }
        }
    }

    public MFRotation(){
        super();
    }

}
