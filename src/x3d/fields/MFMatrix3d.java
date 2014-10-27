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
@XmlType(name = "MFMatrix3d")
public class MFMatrix3d
        extends X3DArrayField <SFMatrix3d> {
    
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
                    String sfMatrix3dToken = tokenizer.nextToken();
                    for (int i = 0; i < 7; i++){
                        sfMatrix3dToken += " " + tokenizer.nextToken();
                    }
                    sfMatrix3dToken = tokenizer.nextToken();

  
                    SFMatrix3d sfMatrix3d = new SFMatrix3d();
                    sfMatrix3d.setStringValue(sfMatrix3dToken);

                    this.add(sfMatrix3d);
                }
                catch (Exception ex){
                    throw new IllegalArgumentException(ex.getMessage());
                }
            }
        }
    }

    public MFMatrix3d(){
        super();
    }

}
