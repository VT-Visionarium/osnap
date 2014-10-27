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
@XmlType(name = "SFVec3d")
public class SFVec3d
        extends X3DField
        implements Cloneable {

    // <editor-fold defaultstate="collapsed" desc="Fields">

    private SFDouble firstValue;
    private SFDouble secondValue;
    private SFDouble thirdValue;

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

    @XmlValue
    public String getStringValue (){
        return this.toString();
    }

    public void setStringValue (String value){
        StringTokenizer tokenizer = new StringTokenizer(value, " ", false);

        String firstToken = tokenizer.nextToken();
        String secondToken = tokenizer.nextToken();
        String thirdToken = tokenizer.nextToken();

        this.setFirstValue(new SFDouble(Double.parseDouble(firstToken)));
        this.setSecondValue(new SFDouble (Double.parseDouble(secondToken)));
        this.setThirdValue(new SFDouble (Double.parseDouble(thirdToken)));
    }

    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="Construction/Destruction/Initialization">

    public SFVec3d(){
        this.firstValue = new SFDouble();
        this.secondValue = new SFDouble();
        this.thirdValue = new SFDouble();
    }

    public SFVec3d (SFDouble firstValue, SFDouble secondValue, SFDouble thirdValue){
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.thirdValue = thirdValue;
    }

    public SFVec3d (Double firstValue, Double secondValue, Double thirdValue) {
        this.firstValue = new SFDouble (firstValue);
        this.secondValue = new SFDouble (secondValue);
        this.thirdValue = new SFDouble (thirdValue);
    }

    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    public static SFVec3d convert (SFVec3f value) {
        return new SFVec3d (value.getFirstValue().getValue().doubleValue(),
                            value.getSecondValue().getValue().doubleValue(),
                            value.getThirdValue().getValue().doubleValue());
    }


    public void add (SFVec3d value) {
        this.firstValue.add(value.firstValue);
        this.secondValue.add(value.secondValue);
        this.thirdValue.add(value.thirdValue);
    }

    public static SFVec3d add (SFVec3d lhs, SFVec3d rhs) {
        return new SFVec3d (SFDouble.add(lhs.firstValue, rhs.firstValue),
                            SFDouble.add(lhs.secondValue, rhs.secondValue),
                            SFDouble.add(lhs.thirdValue, rhs.thirdValue));
    }

    public void subtract (SFVec3d value) {
        this.firstValue.subtract(value.firstValue);
        this.secondValue.subtract(value.secondValue);
        this.thirdValue.subtract(value.thirdValue);
    }

    public static SFVec3d subtract (SFVec3d lhs, SFVec3d rhs) {
        return new SFVec3d (SFDouble.subtract(lhs.firstValue, rhs.firstValue),
                            SFDouble.subtract(lhs.secondValue, rhs.secondValue),
                            SFDouble.subtract(lhs.thirdValue, rhs.thirdValue));
    }

    public void componentwiseMultiply (SFVec3d value) {
        this.firstValue.multiply(value.firstValue);
        this.secondValue.multiply(value.secondValue);
        this.thirdValue.multiply(value.thirdValue);
    }

    public static SFVec3d componentwiseMultiply (SFVec3d lhs, SFVec3d rhs) {
        return new SFVec3d (SFDouble.multiply(lhs.firstValue, rhs.firstValue),
                            SFDouble.multiply(lhs.secondValue, rhs.secondValue),
                            SFDouble.multiply(lhs.thirdValue, rhs.thirdValue));
    }


    public void scalarMultiply (SFDouble value) {
        this.scalarMultiply(value.getValue());
    }

    public void scalarMultiply (Double value) {
        this.componentwiseMultiply(new SFVec3d(value, value, value));
    }

    public static SFVec3d scalarMultiply (SFDouble lhs, SFVec3d rhs) {
        return SFVec3d.scalarMultiply(rhs, lhs);
    }

    public static SFVec3d scalarMultiply (Double lhs, SFVec3d rhs) {
        return SFVec3d.scalarMultiply(rhs, lhs);
    }

    public static SFVec3d scalarMultiply (SFVec3d lhs, SFDouble rhs) {
        return SFVec3d.scalarMultiply(lhs, rhs.getValue());
    }

    public static SFVec3d scalarMultiply (SFVec3d lhs, Double rhs) {
        SFVec3d clone = lhs.clone();

        clone.scalarMultiply(rhs);

        return clone;
    }


    public void componentwiseDivide (SFVec3d value) {
        this.firstValue.divide(value.firstValue);
        this.secondValue.divide(value.secondValue);
        this.thirdValue.divide(value.thirdValue);
    }

    public static SFVec3d componentwiseDivide (SFVec3d lhs, SFVec3d rhs) {
        return new SFVec3d (SFDouble.divide(lhs.firstValue, rhs.firstValue),
                            SFDouble.divide(lhs.secondValue, rhs.secondValue),
                            SFDouble.divide(lhs.thirdValue, rhs.thirdValue));
    }

    public void scalarDivide (SFDouble value) {
        this.scalarDivide(value.getValue());
    }

    public void scalarDivide (Double value) {
        this.componentwiseDivide(new SFVec3d(value, value, value));
    }



    public static SFVec3d scalarDivide (SFVec3d lhs, SFDouble rhs) {
        return SFVec3d.scalarDivide(lhs, rhs.getValue());
    }

    public static SFVec3d scalarDivide (SFVec3d lhs, Double rhs) {
        SFVec3d clone = lhs.clone();

        clone.componentwiseDivide(new SFVec3d(rhs, rhs, rhs));

        return clone;
    }

    public Double length () {
        return Math.sqrt (
                SFDouble.multiply(this.firstValue, this.firstValue).getValue() +
                SFDouble.multiply(this.secondValue, this.secondValue).getValue() +
                SFDouble.multiply(this.thirdValue, this.thirdValue).getValue()
                );
    }

    public Double dotProduct (SFVec3d value) {

        return (SFDouble.multiply(this.firstValue, value.getFirstValue()).getValue() +
                SFDouble.multiply(this.secondValue, value.getSecondValue()).getValue() +
                SFDouble.multiply(this.thirdValue, value.getThirdValue()).getValue());
    }

    public SFVec3d crossProduct (SFVec3d value) {

        SFVec3d result = new SFVec3d(0.,0.,0.);

        result.setFirstValue(SFDouble.multiply(this.secondValue, value.thirdValue));
        result.firstValue.subtract(SFDouble.multiply(this.thirdValue, value.secondValue));

        result.setSecondValue(SFDouble.multiply(this.thirdValue, value.firstValue));
        result.secondValue.subtract(SFDouble.multiply(this.firstValue, value.thirdValue));

        result.setThirdValue(SFDouble.multiply(this.firstValue, value.secondValue));
        result.thirdValue.subtract(SFDouble.multiply(this.secondValue, value.firstValue));


        return result;
    }

    public void normalize () {
        double length = this.length();
        if (length != 0) {
            this.firstValue.divide(length);
            this.secondValue.divide(length);
            this.thirdValue.divide(length);
        }
    }




    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="Cloneable Members">

    @Override
    public SFVec3d clone () {
        return new SFVec3d(this.firstValue.clone(), this.secondValue.clone(),
                this.thirdValue.clone());
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

        return returnValue;
    }

    @Override
    public boolean equals (Object other) {
        if (other != null &&
            other.getClass() == SFVec3d.class) {

            SFVec3d otherVector = (SFVec3d)other;

            return otherVector.getFirstValue().equals(this.firstValue) &&
                   otherVector.getSecondValue().equals(this.secondValue) &&
                   otherVector.getThirdValue().equals(this.thirdValue);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.firstValue != null ? this.firstValue.hashCode() : 0);
        hash = 29 * hash + (this.secondValue != null ? this.secondValue.hashCode() : 0);
        hash = 29 * hash + (this.thirdValue != null ? this.thirdValue.hashCode() : 0);
        return hash;
    }

    // </editor-fold>

}
