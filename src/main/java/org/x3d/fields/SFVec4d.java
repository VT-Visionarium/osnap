/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.x3d.fields;

/*
 * _
 * The Open Semantic Network Analysis Platform (OSNAP)
 * _
 * Copyright (C) 2012 - 2014 Visionarium at Virginia Tech
 * _
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * _
 */

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
@XmlType(name = "SFVec4d")
public class SFVec4d
        extends X3DField
        implements Cloneable {

    // <editor-fold defaultstate="collapsed" desc="Fields">

    private SFDouble firstValue;
    private SFDouble secondValue;
    private SFDouble thirdValue;
    private SFDouble fourthValue;

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

    public SFDouble getThirdValue() {
        return thirdValue;
    }

    public void setThirdValue(SFDouble thirdValue) {
        this.thirdValue = thirdValue;
    }

    public SFDouble getFourthValue() {
        return fourthValue;
    }

    public void setFourthValue(SFDouble fourthValue) 
        throws IllegalArgumentException {
        if (SFDouble.equals(fourthValue, 0.0) ||
            SFDouble.equals(fourthValue, 1.0)) {
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

        this.setFirstValue(new SFDouble(Double.parseDouble(firstToken)));
        this.setSecondValue(new SFDouble (Double.parseDouble(secondToken)));
        this.setThirdValue(new SFDouble (Double.parseDouble(thirdToken)));
        this.setFourthValue(new SFDouble (Double.parseDouble(fourthToken)));
    }

    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="Construction/Destruction/Initialization">

    public SFVec4d(){
        this (0.0, 0.0, 0.0, 1.0);
    }

    public SFVec4d (Double firstValue, Double secondValue, Double thirdValue, Double fourthValue) {
        this (new SFDouble(firstValue),
              new SFDouble(secondValue),
              new SFDouble(thirdValue),
              new SFDouble(fourthValue));
    }


    public SFVec4d (SFDouble firstValue, SFDouble secondValue, SFDouble thirdValue, SFDouble fourthValue){
        if (!(SFDouble.equals(fourthValue, 0.0) ||
            SFDouble.equals(fourthValue, 1.0))) {
            throw new IllegalArgumentException("Fourth component of homogenous coordinate needs to be either 1 or 0");
        }
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.thirdValue = thirdValue;
        this.fourthValue = fourthValue;
    }



    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    public static SFVec4d convert (SFVec4f value) {
        return new SFVec4d (value.getFirstValue().getValue().doubleValue(),
                            value.getSecondValue().getValue().doubleValue(),
                            value.getThirdValue().getValue().doubleValue(),
                            value.getFourthValue().getValue().doubleValue());
    }

    public void add (SFVec4d value) 
        throws ArithmeticException {

        if (SFDouble.equals(this.getFourthValue(), 1.0) &&
            SFDouble.equals(value.getFourthValue(), 1.0)) {
            throw new ArithmeticException("Cannot add two points in homogenous coordinates!");
        }
        
        this.firstValue.add(value.firstValue);
        this.secondValue.add(value.secondValue);
        this.thirdValue.add(value.thirdValue);
    }

    
    public static SFVec4d add (SFVec4d lhs, SFVec4d rhs)
        throws ArithmeticException {

        if (SFDouble.equals(lhs.getFourthValue(), 1.0) &&
            SFDouble.equals(rhs.getFourthValue(), 1.0)) {
            throw new ArithmeticException("Cannot add two points in homogenous coordinates!");
        }
        
        return new SFVec4d (SFDouble.add(lhs.firstValue, rhs.firstValue),
                            SFDouble.add(lhs.secondValue, rhs.secondValue),
                            SFDouble.add(lhs.thirdValue, rhs.thirdValue),
                            SFDouble.add(lhs.fourthValue, rhs.fourthValue));
    }

    
    public void subtract (SFVec4d value)
        throws ArithmeticException {

        if (SFDouble.equals(this.fourthValue, 0.0) &&
            SFDouble.equals(value.fourthValue, 1.0)) {
            throw new ArithmeticException ("Cannot subtract a point from a vector!");
        }
        
        this.firstValue.subtract(value.firstValue);
        this.secondValue.subtract(value.secondValue);
        this.thirdValue.subtract(value.thirdValue);
        this.fourthValue.subtract(value.fourthValue);
    }


    public static SFVec4d subtract (SFVec4d lhs, SFVec4d rhs)
        throws ArithmeticException {

        if (SFDouble.equals(lhs.fourthValue, 0.0) &&
            SFDouble.equals(rhs.fourthValue, 1.0)) {
            throw new ArithmeticException ("Cannot subtract a point from a vector!");
        }

        return new SFVec4d (SFDouble.subtract(lhs.firstValue, rhs.firstValue),
                            SFDouble.subtract(lhs.secondValue, rhs.secondValue),
                            SFDouble.subtract(lhs.thirdValue, rhs.thirdValue),
                            SFDouble.subtract(lhs.fourthValue, rhs.fourthValue));
    }


    public void componentwiseMultiply (SFVec4d value) {
        this.firstValue.multiply(value.firstValue);
        this.secondValue.multiply(value.secondValue);
        this.thirdValue.multiply(value.thirdValue);
        this.fourthValue.multiply(value.fourthValue);
    }


    public static SFVec4d componentwiseMultiply (SFVec4d lhs, SFVec4d rhs) {
        return new SFVec4d (SFDouble.multiply(lhs.firstValue, rhs.firstValue),
                            SFDouble.multiply(lhs.secondValue, rhs.secondValue),
                            SFDouble.multiply(lhs.thirdValue, rhs.thirdValue),
                            SFDouble.multiply(lhs.fourthValue, rhs.fourthValue));
    }


    public void scalarMultiply (SFDouble value) {
        this.scalarMultiply(value.getValue());
    }

    public void scalarMultiply (Double value) {
        this.componentwiseMultiply(new SFVec4d(value, value, value, 1.0));
    }

    public static SFVec4d scalarMultiply (SFDouble lhs, SFVec4d rhs) {
        return SFVec4d.scalarMultiply(rhs, lhs);
    }

    public static SFVec4d scalarMultiply (Double lhs, SFVec4d rhs) {
        return SFVec4d.scalarMultiply(rhs, lhs);
    }

    public static SFVec4d scalarMultiply (SFVec4d lhs, SFDouble rhs) {
        return SFVec4d.scalarMultiply(lhs, rhs.getValue());
    }

    public static SFVec4d scalarMultiply (SFVec4d lhs, Double rhs) {
        SFVec4d clone = lhs.clone();

        clone.scalarMultiply(rhs);

        return clone;
    }


    public void componentwiseDivide (SFVec4d value) {
        this.firstValue.divide(value.firstValue);
        this.secondValue.divide(value.secondValue);
        this.thirdValue.divide(value.thirdValue);
        this.fourthValue.divide(value.fourthValue);
    }

    
    public static SFVec4d componentwiseDivide (SFVec4d lhs, SFVec4d rhs) {
        return new SFVec4d (SFDouble.divide(lhs.firstValue, rhs.firstValue),
                            SFDouble.divide(lhs.secondValue, rhs.secondValue),
                            SFDouble.divide(lhs.thirdValue, rhs.thirdValue),
                            SFDouble.divide(lhs.fourthValue, rhs.fourthValue));
    }

    public void scalarDivide (SFDouble value) {
        this.scalarDivide(value.getValue());
    }

    public void scalarDivide (Double value) {
        this.componentwiseDivide(new SFVec4d(value, value, value, 1.0));
    }



    public static SFVec4d scalarDivide (SFVec4d lhs, SFDouble rhs) {
        return SFVec4d.scalarDivide(lhs, rhs.getValue());
    }

    public static SFVec4d scalarDivide (SFVec4d lhs, Double rhs) {
        SFVec4d clone = lhs.clone();

        clone.componentwiseDivide(new SFVec4d(rhs, rhs, rhs, 1.0));

        return clone;
    }


    public Double length () {
        return Math.sqrt (
                SFDouble.multiply(this.firstValue, this.firstValue).getValue() +
                SFDouble.multiply(this.secondValue, this.secondValue).getValue() +
                SFDouble.multiply(this.thirdValue, this.thirdValue).getValue()
                );
    }

    public Double dotProduct (SFVec4d value) 
        throws ArithmeticException {

        if (SFDouble.equals(this.fourthValue, 1.0) ||
            SFDouble.equals(this.fourthValue, 1.0)) {
            throw new ArithmeticException ("Dot product not defined for points!");
        }
        
        return (SFDouble.multiply(this.firstValue, value.getFirstValue()).getValue() +
                SFDouble.multiply(this.secondValue, value.getSecondValue()).getValue() +
                SFDouble.multiply(this.thirdValue, value.getThirdValue()).getValue());
    }

    public SFVec4d crossProduct (SFVec4d value)
        throws ArithmeticException{

        if (SFDouble.equals(this.fourthValue, 1.0) ||
            SFDouble.equals(this.fourthValue, 1.0)) {
            throw new ArithmeticException ("Cross product not defined for points!");
        }

        SFVec4d result = new SFVec4d(0.0, 0.0, 0.0, 0.0);

        result.setFirstValue(SFDouble.multiply(this.secondValue, value.thirdValue));
        result.firstValue.subtract(SFDouble.multiply(this.thirdValue, value.secondValue));

        result.setSecondValue(SFDouble.multiply(this.thirdValue, value.firstValue));
        result.secondValue.subtract(SFDouble.multiply(this.firstValue, value.thirdValue));

        result.setThirdValue(SFDouble.multiply(this.firstValue, value.secondValue));
        result.thirdValue.subtract(SFDouble.multiply(this.secondValue, value.firstValue));


        return result;
    }

    public void normalize () {
        if (this.length() != 0) {
            Double length = length();
            this.firstValue.divide(length);
            this.secondValue.divide(length);
            this.thirdValue.divide(length);
        }
    }



    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="Cloneable Members">

    @Override
    public SFVec4d clone () {
        return new SFVec4d(this.firstValue.getValue(), this.secondValue.getValue(),
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
