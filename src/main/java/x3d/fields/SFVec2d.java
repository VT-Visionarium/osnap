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
@XmlType(name = "SFVec2d")
public class SFVec2d
        extends X3DField
        implements Cloneable{

    // <editor-fold defaultstate="collapsed" desc="Fields">

    private SFDouble firstValue;
    private SFDouble secondValue;

    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="Properties">

    public SFDouble getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(SFDouble firstValue) {
        this.firstValue = firstValue;
    }

    public SFDouble getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(SFDouble secondValue) {
        this.secondValue = secondValue;
    }

    @XmlValue
    public String getStringValue (){
        return this.toString();
    }

    public void setStringValue (String value){
        StringTokenizer tokenizer = new StringTokenizer(value, " ", false);

        String firstToken = tokenizer.nextToken();
        String secondToken = tokenizer.nextToken();

        this.setFirstValue(new SFDouble(Double.parseDouble(firstToken)));
        this.setSecondValue(new SFDouble (Double.parseDouble(secondToken)));
    }

    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="Construction/Destruction/Initialization">

    public SFVec2d(){
        this.firstValue = new SFDouble();
        this.secondValue = new SFDouble();
    }

    public SFVec2d (SFDouble firstValue, SFDouble secondValue){
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public SFVec2d (Double firstValue, Double secondValue) {
        this.firstValue = new SFDouble (firstValue);
        this.secondValue = new SFDouble (secondValue);
    }

    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    public static SFVec2d convert (SFVec2f value) {
        return new SFVec2d (value.getFirstValue().getValue().doubleValue(),
                            value.getSecondValue().getValue().doubleValue());
    }

    public void add (SFVec2d value) {
        this.firstValue.add(value.firstValue);
        this.secondValue.add(value.secondValue);
    }
    
    public static SFVec2d add (SFVec2d lhs, SFVec2d rhs) {
        SFVec2d clone = lhs.clone();
        
        clone.add(rhs);
        
        return clone;
    }

    public void subtract (SFVec2d value) {
        this.firstValue.subtract(value.firstValue);
        this.secondValue.subtract(value.secondValue);
    }

    public static SFVec2d subtract (SFVec2d lhs, SFVec2d rhs) {
        SFVec2d clone = lhs.clone();

        clone.subtract(rhs);

        return clone;
    }

    public void componentwiseMultiply (SFVec2d value) {
        this.firstValue.multiply(value.firstValue);
        this.secondValue.multiply(value.secondValue);
    }

    public static SFVec2d componentwiseMultiply (SFVec2d lhs, SFVec2d rhs) {
        SFVec2d clone = lhs.clone();

        clone.componentwiseMultiply(rhs);

        return clone;
    }


    public void scalarMultiply (SFDouble value) {
        this.scalarMultiply(value.getValue());
    }

    public void scalarMultiply (Double value) {
        this.componentwiseMultiply(new SFVec2d(value, value));
    }

    public static SFVec2d scalarMultiply (SFDouble lhs, SFVec2d rhs) {
        return SFVec2d.scalarMultiply(rhs, lhs);
    }

    public static SFVec2d scalarMultiply (Double lhs, SFVec2d rhs) {
        return SFVec2d.scalarMultiply(rhs, lhs);
    }
    
    public static SFVec2d scalarMultiply (SFVec2d lhs, SFDouble rhs) {
        return SFVec2d.scalarMultiply(lhs, rhs.getValue());
    }

    public static SFVec2d scalarMultiply (SFVec2d lhs, Double rhs) {
        SFVec2d clone = lhs.clone();

        clone.scalarMultiply(rhs);

        return clone;
    }

    

    public void componentwiseDivide (SFVec2d value) {
        this.firstValue.divide(value.firstValue);
        this.secondValue.divide(value.secondValue);
    }

    public static SFVec2d componentwiseDivide (SFVec2d lhs, SFVec2d rhs) {
        SFVec2d clone = lhs.clone();

        clone.componentwiseDivide(rhs);

        return clone;
    }

    public void scalarDivide (SFDouble value) {
        this.scalarDivide(value.getValue());
    }

    public void scalarDivide (Double value) {
        this.componentwiseDivide(new SFVec2d(value, value));
    }



    public static SFVec2d scalarDivide (SFVec2d lhs, SFDouble rhs) {
        return SFVec2d.scalarDivide(lhs, rhs.getValue());
    }

    public static SFVec2d scalarDivide (SFVec2d lhs, Double rhs) {
        SFVec2d clone = lhs.clone();

        clone.componentwiseDivide(new SFVec2d(rhs, rhs));

        return clone;
    }

    public Double length () {
        return Math.sqrt (
                SFDouble.multiply(this.firstValue, this.firstValue).getValue() +
                SFDouble.multiply(this.secondValue, this.secondValue).getValue()
                );
    }

    public Double dotProduct (SFVec2d value) {
        return SFDouble.add(SFDouble.multiply(this.firstValue, value.getFirstValue()),
               SFDouble.multiply(this.secondValue, value.getSecondValue())).getValue();
    }
    
    public void normalize () {
        if (this.length() != 0) {
            Double length = this.length();
            this.firstValue.divide(length);
            this.secondValue.divide(length);
        }
    }



    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="Cloneable Members">

    @Override
    public SFVec2d clone () {
        return new SFVec2d(this.firstValue.clone(), this.secondValue.clone());
    }

    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="Overrides and Overridables">

    @Override
    public String toString() {
        String returnValue = new String();

        returnValue += this.firstValue;
        returnValue += " ";
        returnValue += this.secondValue;

        return returnValue;
    }

    // </editor-fold>

}
