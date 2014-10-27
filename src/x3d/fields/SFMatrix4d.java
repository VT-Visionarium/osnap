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
@XmlType(name = "SFMatrix4d")
public class SFMatrix4d
        extends X3DField  {

    // <editor-fold defaultstate="collapsed" desc="Fields">

    private SFVec4d firstValue;
    private SFVec4d secondValue;
    private SFVec4d thirdValue;
    private SFVec4d fourthValue;

    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="Properties">

    public SFVec4d getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(SFVec4d firstValue) {
        this.firstValue = firstValue;
    }

    public SFVec4d getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(SFVec4d secondValue) {
        this.secondValue = secondValue;
    }

    public SFVec4d getThirdValue() {
        return thirdValue;
    }

    public void setThirdValue(SFVec4d thirdValue) {
        this.thirdValue = thirdValue;
    }

    public SFVec4d getFourthValue() {
        return fourthValue;
    }

    public void setFourthValue(SFVec4d fourthValue) {
        this.fourthValue = fourthValue;
    }

    @XmlValue
    public String getStringValue (){
        return this.toString();
    }

    public void setStringValue (String value){
        StringTokenizer tokenizer = new StringTokenizer(value, " ", false);

        for (int i = 0; i < 4; i++){
            String sfVec4dToken = tokenizer.nextToken();
            sfVec4dToken += " " + tokenizer.nextToken();
            sfVec4dToken += " " + tokenizer.nextToken();
            sfVec4dToken += " " + tokenizer.nextToken();

            SFVec4d rowVector = new SFVec4d();
            rowVector.setStringValue(sfVec4dToken);
            
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
                case 4:
                    this.setFourthValue(rowVector);
                    break;
            }
        }
    }

    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="Construction/Destruction/Initialization">

    public SFMatrix4d(){
        this.firstValue = new SFVec4d(1.0, 0.0, 0.0, 0.0);
        this.secondValue = new SFVec4d(0.0, 1.0, 0.0, 0.0);
        this.thirdValue = new SFVec4d(0.0, 0.0, 1.0, 0.0);
        this.fourthValue = new SFVec4d(0.0, 0.0, 0.0, 1.0);
    }

    public SFMatrix4d (SFVec4d firstValue, SFVec4d secondValue, SFVec4d thirdValue, SFVec4d fourthValue){
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.thirdValue = thirdValue;
        this.fourthValue = fourthValue;
    }

    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    public static SFMatrix4d convert (SFMatrix4f value) {
        return new SFMatrix4d (SFVec4d.convert(value.getFirstValue()),
                               SFVec4d.convert(value.getSecondValue()),
                               SFVec4d.convert(value.getThirdValue()),
                               SFVec4d.convert(value.getFourthValue()));
    }

    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="Overrides and Overridables">

    @Override
    public String toString() {
        String returnValue = new String();

        returnValue += this.firstValue;
        returnValue += " ";
        returnValue += this.secondValue;
        returnValue += " ";
        returnValue += this.thirdValue;
        returnValue += " ";
        returnValue += this.fourthValue;

        return returnValue;
    }

    // </editor-fold>
}
