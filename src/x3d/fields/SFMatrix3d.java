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

/**
 *
 * @author peter
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "SFMatrix3d")
public class SFMatrix3d
        extends X3DField  {
    private SFVec3d firstValue;
    private SFVec3d secondValue;
    private SFVec3d thirdValue;

    public SFVec3d getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(SFVec3d firstValue) {
        this.firstValue = firstValue;
    }

    public SFVec3d getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(SFVec3d secondValue) {
        this.secondValue = secondValue;
    }

    public SFVec3d getThirdValue() {
        return thirdValue;
    }

    public void setThirdValue(SFVec3d thirdValue) {
        this.thirdValue = thirdValue;
    }

    @XmlValue
    public String getStringValue (){
        return this.toString();
    }

    public void setStringValue (String value){
        StringTokenizer tokenizer = new StringTokenizer(value, " ", false);

        for (int i = 0; i < 3; i++){
            String firstToken = tokenizer.nextToken();
            String secondToken = tokenizer.nextToken();
            String thirdToken = tokenizer.nextToken();

            SFVec3d rowVector = new SFVec3d();
            rowVector.setFirstValue(new SFDouble (Double.parseDouble(firstToken)));
            rowVector.setSecondValue(new SFDouble (Double.parseDouble(secondToken)));
            rowVector.setThirdValue(new SFDouble (Double.parseDouble(thirdToken)));
            
            switch (i){
                case 0:
                    this.setFirstValue(rowVector);
                    break;
                case 1:
                    this.setSecondValue(rowVector);
                    break;
                case 3:
                    this.setThirdValue(rowVector);
                    break;
            }
        }
    }

    public SFMatrix3d(){
        this.firstValue = new SFVec3d(1.0, 0.0, 0.0);
        this.secondValue = new SFVec3d(0.0, 1.0, 0.0);
        this.thirdValue = new SFVec3d(0.0, 0.0, 1.0);
    }

    public SFMatrix3d (SFVec3d firstValue, SFVec3d secondValue, SFVec3d thirdValue){
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.thirdValue = thirdValue;
    }

    public static SFMatrix3d convert (SFMatrix3f value) {
        return new SFMatrix3d (SFVec3d.convert(value.getFirstValue()),
                               SFVec3d.convert(value.getSecondValue()),
                               SFVec3d.convert(value.getThirdValue()));
    }

    @Override
    public String toString() {
        String returnValue = new String();

        returnValue += this.firstValue;
        returnValue += " ";
        returnValue += this.secondValue;
        returnValue += " ";
        returnValue += this.thirdValue;

        return returnValue;
    }


}
