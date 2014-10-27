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
@XmlType(name = "MFMatrix3f")
public class MFMatrix3f
        extends X3DArrayField <SFMatrix3f> {


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
                    String sfMatrix3fToken = tokenizer.nextToken();
                    for (int i = 0; i < 7; i++){
                        sfMatrix3fToken += " " + tokenizer.nextToken();
                    }
                    sfMatrix3fToken = tokenizer.nextToken();

  
                    SFMatrix3f sfMatrix3f = new SFMatrix3f();
                    sfMatrix3f.setStringValue(sfMatrix3fToken);

                    this.add(sfMatrix3f);
                }
                catch (Exception ex){
                    throw new IllegalArgumentException(ex.getMessage());
                }
            }
        }
    }

    public MFMatrix3f(){
        super();
    }

}
