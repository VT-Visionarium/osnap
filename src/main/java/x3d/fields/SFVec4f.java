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
@XmlType(name = "SFVec4f")
public class SFVec4f
        extends X3DField
        implements Cloneable {

    // <editor-fold defaultstate="collapsed" desc="Fields">
    
    private SFFloat firstValue;
    private SFFloat secondValue;
    private SFFloat thirdValue;
    private SFFloat fourthValue;

    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="Properties">

    public SFFloat getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(SFFloat firstValue) {
        this.firstValue = firstValue;
    }

    public SFFloat getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(SFFloat secondValue) {
        this.secondValue = secondValue;
    }

    public SFFloat getThirdValue() {
        return thirdValue;
    }

    public void setThirdValue(SFFloat thirdValue) {
        this.thirdValue = thirdValue;
    }

    public SFFloat getFourthValue() {
        return fourthValue;
    }

    public void setFourthValue(SFFloat fourthValue) 
        throws IllegalArgumentException {
        if (SFFloat.equals(fourthValue, 0.0f) ||
            SFFloat.equals(fourthValue, 1.0f)) {

            this.fourthValue = fourthValue;
        }
        else {
            throw new IllegalArgumentException("Fourth component of homogenous coordinate needs to be either 1 or 0");
        }
    }

    @XmlValue
    public String getStringValue (){
        return this.toString();
    }

    public void setStringValue (String value){
        StringTokenizer tokenizer = new StringTokenizer(value, " ", false);

        String firstToken = tokenizer.nextToken();
        String secondToken = tokenizer.nextToken();
        String thirdToken = tokenizer.nextToken();
        String fourthToken = tokenizer.nextToken();

        this.setFirstValue(new SFFloat(Float.parseFloat(firstToken)));
        this.setSecondValue(new SFFloat (Float.parseFloat(secondToken)));
        this.setThirdValue(new SFFloat (Float.parseFloat(thirdToken)));
        this.setFourthValue(new SFFloat (Float.parseFloat(fourthToken)));
    }

    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="Construction/Destruction/Initialization">

    public SFVec4f(){
        this.firstValue = new SFFloat(0.0f);
        this.secondValue = new SFFloat(0.0f);
        this.thirdValue = new SFFloat(0.0f);
        this.fourthValue = new SFFloat(1.0f);
    }

    public SFVec4f (SFFloat firstValue, SFFloat secondValue, SFFloat thirdValue, SFFloat fourthValue){
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.thirdValue = thirdValue;
        this.fourthValue = fourthValue;
    }

    public SFVec4f (Float firstValue, Float secondValue, Float thirdValue, Float fourthValue) {
        this.firstValue = new SFFloat (firstValue);
        this.secondValue = new SFFloat (secondValue);
        this.thirdValue = new SFFloat (thirdValue);
        this.fourthValue = new SFFloat (fourthValue);
    }

    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    public static SFVec4f convert (SFVec4d value) {
        return new SFVec4f (value.getFirstValue().getValue().floatValue(),
                            value.getSecondValue().getValue().floatValue(),
                            value.getThirdValue().getValue().floatValue(),
                            value.getFourthValue().getValue().floatValue());
    }

    public void add (SFVec4f value)
        throws ArithmeticException {

        if (SFFloat.equals(this.getFourthValue(), 1.0f) &&
            SFFloat.equals(value.getFourthValue(), 1.0f)) {
            throw new ArithmeticException("Cannot add two points in homogenous coordinates!");
        }

        this.firstValue.add(value.firstValue);
        this.secondValue.add(value.secondValue);
        this.thirdValue.add(value.thirdValue);
    }


    public static SFVec4f add (SFVec4f lhs, SFVec4f rhs)
        throws ArithmeticException {

        if (SFFloat.equals(lhs.getFourthValue(), 1.0f) &&
            SFFloat.equals(rhs.getFourthValue(), 1.0f)) {
            throw new ArithmeticException("Cannot add two points in homogenous coordinates!");
        }
        
        return new SFVec4f (SFFloat.add(lhs.firstValue, rhs.firstValue),
                            SFFloat.add(lhs.secondValue, rhs.secondValue),
                            SFFloat.add(lhs.thirdValue, rhs.thirdValue),
                            SFFloat.add(lhs.fourthValue, rhs.fourthValue));
    }


    public void subtract (SFVec4f value)
        throws ArithmeticException {

        if (SFFloat.equals(this.fourthValue, 0.0f) &&
            SFFloat.equals(value.fourthValue, 1.0f)) {
            throw new ArithmeticException ("Cannot subtract a point from a vector!");
        }

        this.firstValue.subtract(value.firstValue);
        this.secondValue.subtract(value.secondValue);
        this.thirdValue.subtract(value.thirdValue);
        this.fourthValue.subtract(value.fourthValue);
    }


    public static SFVec4f subtract (SFVec4f lhs, SFVec4f rhs)
        throws ArithmeticException {

        if (SFFloat.equals(lhs.fourthValue, 0.0f) &&
            SFFloat.equals(rhs.fourthValue, 1.0f)) {
            throw new ArithmeticException ("Cannot subtract a point from a vector!");
        }

        return new SFVec4f (SFFloat.subtract(lhs.firstValue, rhs.firstValue),
                            SFFloat.subtract(lhs.secondValue, rhs.secondValue),
                            SFFloat.subtract(lhs.thirdValue, rhs.thirdValue),
                            SFFloat.subtract(lhs.fourthValue, rhs.fourthValue));
    }


    public void componentwiseMultiply (SFVec4f value) {
        this.firstValue.multiply(value.firstValue);
        this.secondValue.multiply(value.secondValue);
        this.thirdValue.multiply(value.thirdValue);
        this.fourthValue.multiply(value.fourthValue);
    }


    public static SFVec4f componentwiseMultiply (SFVec4f lhs, SFVec4f rhs) {
        return new SFVec4f (SFFloat.multiply(lhs.firstValue, rhs.firstValue),
                            SFFloat.multiply(lhs.secondValue, rhs.secondValue),
                            SFFloat.multiply(lhs.thirdValue, rhs.thirdValue),
                            SFFloat.multiply(lhs.fourthValue, rhs.fourthValue));
    }

    public void scalarMultiply (SFFloat value) {
        this.scalarMultiply(value.getValue());
    }

    public void scalarMultiply (Float value) {
        this.componentwiseMultiply(new SFVec4f(value, value, value, 1.0f));
    }

    public static SFVec4f scalarMultiply (SFFloat lhs, SFVec4f rhs) {
        return SFVec4f.scalarMultiply(rhs, lhs);
    }

    public static SFVec4f scalarMultiply (Float lhs, SFVec4f rhs) {
        return SFVec4f.scalarMultiply(rhs, lhs);
    }

    public static SFVec4f scalarMultiply (SFVec4f lhs, SFFloat rhs) {
        return SFVec4f.scalarMultiply(lhs, rhs.getValue());
    }

    public static SFVec4f scalarMultiply (SFVec4f lhs, Float rhs) {
        SFVec4f clone = lhs.clone();

        clone.scalarMultiply(rhs);

        return clone;
    }

    public void componentwiseDivide (SFVec4f value) {
        this.firstValue.divide(value.firstValue);
        this.secondValue.divide(value.secondValue);
        this.thirdValue.divide(value.thirdValue);
        this.fourthValue.divide(value.fourthValue);
    }


    public static SFVec4f componentwiseDivide (SFVec4f lhs, SFVec4f rhs) {
        return new SFVec4f (SFFloat.divide(lhs.firstValue, rhs.firstValue),
                            SFFloat.divide(lhs.secondValue, rhs.secondValue),
                            SFFloat.divide(lhs.thirdValue, rhs.thirdValue),
                            SFFloat.divide(lhs.fourthValue, rhs.fourthValue));
    }


    public void scalarDivide (SFFloat value) {
        this.scalarDivide(value.getValue());
    }

    public void scalarDivide (Float value) {
        this.componentwiseDivide(new SFVec4f(value, value, value, 1.0f));
    }



    public static SFVec4f scalarDivide (SFVec4f lhs, SFFloat rhs) {
        return SFVec4f.scalarDivide(lhs, rhs.getValue());
    }

    public static SFVec4f scalarDivide (SFVec4f lhs, Float rhs) {
        SFVec4f clone = lhs.clone();

        clone.componentwiseDivide(new SFVec4f(rhs, rhs, rhs, 1.0f));

        return clone;
    }
    
    public Float length () {
        Float sumOfSquares = SFFloat.multiply(this.firstValue, this.firstValue).getValue() +
                SFFloat.multiply(this.secondValue, this.secondValue).getValue() +
                SFFloat.multiply(this.thirdValue, this.thirdValue).getValue();
        Double sqrt = Math.sqrt (sumOfSquares.doubleValue());

        return sqrt.floatValue();
    }

    public Float dotProduct (SFVec4f value)
        throws ArithmeticException {

        if (SFFloat.equals(this.fourthValue, 1.0f) ||
            SFFloat.equals(this.fourthValue, 1.0f)) {
            throw new ArithmeticException ("Dot product not defined for points!");
        }

        return (SFFloat.multiply(this.firstValue, value.getFirstValue()).getValue() +
                SFFloat.multiply(this.secondValue, value.getSecondValue()).getValue() +
                SFFloat.multiply(this.thirdValue, value.getThirdValue()).getValue());
    }

    public SFVec4f crossProduct (SFVec4f value)
        throws ArithmeticException{

        if (SFFloat.equals(this.fourthValue, 1.0f) ||
            SFFloat.equals(this.fourthValue, 1.0f)) {
            throw new ArithmeticException ("Cross product not defined for points!");
        }

        SFVec4f result = new SFVec4f(0.0f, 0.0f, 0.0f, 0.0f);

        result.setFirstValue(SFFloat.multiply(this.secondValue, value.thirdValue));
        result.firstValue.subtract(SFFloat.multiply(this.thirdValue, value.secondValue));

        result.setSecondValue(SFFloat.multiply(this.thirdValue, value.firstValue));
        result.secondValue.subtract(SFFloat.multiply(this.firstValue, value.thirdValue));

        result.setThirdValue(SFFloat.multiply(this.firstValue, value.secondValue));
        result.thirdValue.subtract(SFFloat.multiply(this.secondValue, value.firstValue));


        return result;
    }

    public void normalize () {
        if (this.length() != 0) {
            Float length = length();
            this.firstValue.divide(length);
            this.secondValue.divide(length);
            this.thirdValue.divide(length);
        }
    }

    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="Cloneable Members">

    @Override
    public SFVec4f clone () {
        return new SFVec4f(this.firstValue.getValue(), this.secondValue.getValue(),
                this.thirdValue.getValue(), this.fourthValue.getValue());
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
