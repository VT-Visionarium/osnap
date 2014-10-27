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
@XmlType(name = "MFMatrix4d")
public class MFMatrix4d
        extends X3DArrayField <SFMatrix4d> {

    @Override
    @XmlValue
    public String getStringValue (){
        return this.toString();
    }

    @Override
    public void setStringValue (String value)
            throws IllegalArgumentException {

        ArrayList<String> tokenList = this.getListFromString(value);

        for (String token : tokenList){
            StringTokenizer tokenizer = new StringTokenizer(token, " ", false);
            while (tokenizer.hasMoreTokens()){
                try{
                    String sfMatrix4dToken = tokenizer.nextToken();
                    for (int i = 0; i < 14; i++){
                        sfMatrix4dToken += " " + tokenizer.nextToken();
                    }
                    sfMatrix4dToken = tokenizer.nextToken();
  
                    SFMatrix4d sfMatrix4d = new SFMatrix4d();
                    sfMatrix4d.setStringValue(sfMatrix4dToken);

                    this.add(sfMatrix4d);
                }
                catch (Exception ex){
                    throw new IllegalArgumentException(ex.getMessage());
                }
            }
        }
    }

    public MFMatrix4d(){
        super();
    }

}
