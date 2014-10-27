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
@XmlType(name = "MFMatrix4f")
public class MFMatrix4f
        extends X3DArrayField <SFMatrix4f> {

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
                    String sfMatrix4fToken = tokenizer.nextToken();
                    for (int i = 0; i < 14; i++){
                        sfMatrix4fToken += " " + tokenizer.nextToken();
                    }
                    sfMatrix4fToken = tokenizer.nextToken();
  
                    SFMatrix4f sfMatrix4f = new SFMatrix4f();
                    sfMatrix4f.setStringValue(sfMatrix4fToken);

                    this.add(sfMatrix4f);
                }
                catch (Exception ex){
                    throw new IllegalArgumentException(ex.getMessage());
                }
            }
        }
    }

    public MFMatrix4f(){
        super();
    }

}
