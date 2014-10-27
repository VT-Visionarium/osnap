/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
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
@XmlType(name = "SFVec3f")
public class SFVec3f
        extends X3DField
        implements Cloneable {

    // <editor-fold defaultstate="collapsed" desc="Fields">

    private SFFloat firstValue;
    private SFFloat secondValue;
    private SFFloat thirdValue;

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

    @XmlValue
    public String getStringValue() {

        return this.toString();
    }

    public void setStringValue(String value) {

        StringTokenizer tokenizer = new StringTokenizer(value, " ", false);

        String firstToken = tokenizer.nextToken();
        String secondToken = tokenizer.nextToken();
        String thirdToken = tokenizer.nextToken();

        this.setFirstValue(new SFFloat(Float.parseFloat(firstToken)));
        this.setSecondValue(new SFFloat(Float.parseFloat(secondToken)));
        this.setThirdValue(new SFFloat(Float.parseFloat(thirdToken)));
    }

    // </editor-fold>


    // <editor-fold defaultstate="collapsed"
    // desc="Construction/Destruction/Initialization">

    public SFVec3f() {

        this.firstValue = new SFFloat();
        this.secondValue = new SFFloat();
        this.thirdValue = new SFFloat();
    }

    public SFVec3f(SFFloat firstValue, SFFloat secondValue, SFFloat thirdValue) {

        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.thirdValue = thirdValue;
    }

    public SFVec3f(Float firstValue, Float secondValue, Float thirdValue) {

        this.firstValue = new SFFloat(firstValue);
        this.secondValue = new SFFloat(secondValue);
        this.thirdValue = new SFFloat(thirdValue);
    }

    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    public static SFVec3f convert(SFVec3d value) {

        return new SFVec3f(value.getFirstValue().getValue().floatValue(), value
                .getSecondValue().getValue().floatValue(), value
                .getThirdValue().getValue().floatValue());
    }


    public void add(SFVec3f value) {

        this.firstValue.add(value.firstValue);
        this.secondValue.add(value.secondValue);
        this.thirdValue.add(value.thirdValue);
    }

    public static SFVec3f add(SFVec3f lhs, SFVec3f rhs) {

        return new SFVec3f(SFFloat.add(lhs.firstValue, rhs.firstValue),
                SFFloat.add(lhs.secondValue, rhs.secondValue), SFFloat.add(
                        lhs.thirdValue, rhs.thirdValue));
    }

    public void subtract(SFVec3f value) {

        this.firstValue.subtract(value.firstValue);
        this.secondValue.subtract(value.secondValue);
        this.thirdValue.subtract(value.thirdValue);
    }

    public static SFVec3f subtract(SFVec3f lhs, SFVec3f rhs) {

        return new SFVec3f(SFFloat.subtract(lhs.firstValue, rhs.firstValue),
                SFFloat.subtract(lhs.secondValue, rhs.secondValue),
                SFFloat.subtract(lhs.thirdValue, rhs.thirdValue));
    }

    public void componentwiseMultiply(SFVec3f value) {

        this.firstValue.multiply(value.firstValue);
        this.secondValue.multiply(value.secondValue);
        this.thirdValue.multiply(value.thirdValue);
    }

    public static SFVec3f componentwiseMultiply(SFVec3f lhs, SFVec3f rhs) {

        return new SFVec3f(SFFloat.multiply(lhs.firstValue, rhs.firstValue),
                SFFloat.multiply(lhs.secondValue, rhs.secondValue),
                SFFloat.multiply(lhs.thirdValue, rhs.thirdValue));
    }


    public void scalarMultiply(SFFloat value) {

        this.scalarMultiply(value.getValue());
    }

    public void scalarMultiply(Float value) {

        this.componentwiseMultiply(new SFVec3f(value, value, value));
    }

    public static SFVec3f scalarMultiply(SFFloat lhs, SFVec3f rhs) {

        return SFVec3f.scalarMultiply(rhs, lhs);
    }

    public static SFVec3f scalarMultiply(Float lhs, SFVec3f rhs) {

        return SFVec3f.scalarMultiply(rhs, lhs);
    }

    public static SFVec3f scalarMultiply(SFVec3f lhs, SFFloat rhs) {

        return SFVec3f.scalarMultiply(lhs, rhs.getValue());
    }

    public static SFVec3f scalarMultiply(SFVec3f lhs, Float rhs) {

        SFVec3f clone = lhs.clone();

        clone.scalarMultiply(rhs);

        return clone;
    }

    public void componentwiseDivide(SFVec3f value) {

        this.firstValue.divide(value.firstValue);
        this.secondValue.divide(value.secondValue);
        this.thirdValue.divide(value.thirdValue);
    }

    public static SFVec3f componentwiseDivide(SFVec3f lhs, SFVec3f rhs) {

        return new SFVec3f(SFFloat.divide(lhs.firstValue, rhs.firstValue),
                SFFloat.divide(lhs.secondValue, rhs.secondValue),
                SFFloat.divide(lhs.thirdValue, rhs.thirdValue));
    }

    public void scalarDivide(SFFloat value) {

        this.scalarDivide(value.getValue());
    }

    public void scalarDivide(float value) {

        this.componentwiseDivide(new SFVec3f(value, value, value));
    }


    public static SFVec3f scalarDivide(SFVec3f lhs, SFFloat rhs) {

        return SFVec3f.scalarDivide(lhs, rhs.getValue());
    }

    public static SFVec3f scalarDivide(SFVec3f lhs, float rhs) {

        SFVec3f clone = lhs.clone();

        clone.componentwiseDivide(new SFVec3f(rhs, rhs, rhs));

        return clone;
    }

    public float length() {

        Float sumOfSquares = SFFloat.multiply(this.firstValue, this.firstValue)
                .getValue()
                + SFFloat.multiply(this.secondValue, this.secondValue)
                        .getValue()
                + SFFloat.multiply(this.thirdValue, this.thirdValue).getValue();
        Double sqrt = Math.sqrt(sumOfSquares.doubleValue());

        return sqrt.floatValue();
    }

    public float dotProduct(SFVec3f value) {

        return (SFFloat.multiply(this.firstValue, value.getFirstValue())
                .getValue()
                + SFFloat.multiply(this.secondValue, value.getSecondValue())
                        .getValue() + SFFloat.multiply(this.thirdValue,
                value.getThirdValue()).getValue());
    }

    public SFVec3f crossProduct(SFVec3f value) {

        SFVec3f result = new SFVec3f(0f, 0f, 0f);

        result.setFirstValue(SFFloat.multiply(this.secondValue,
                value.thirdValue));
        result.firstValue.subtract(SFFloat.multiply(this.thirdValue,
                value.secondValue));

        result.setSecondValue(SFFloat.multiply(this.thirdValue,
                value.firstValue));
        result.secondValue.subtract(SFFloat.multiply(this.firstValue,
                value.thirdValue));

        result.setThirdValue(SFFloat.multiply(this.firstValue,
                value.secondValue));
        result.thirdValue.subtract(SFFloat.multiply(this.secondValue,
                value.firstValue));


        return result;
    }

    public void normalize() {

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
    public SFVec3f clone() {

        return new SFVec3f(this.firstValue.getValue(),
                this.secondValue.getValue(), this.thirdValue.getValue());
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
    public boolean equals(Object other) {

        if (other != null && other.getClass() == SFVec3f.class) {

            SFVec3f otherVector = (SFVec3f) other;

            return otherVector.getFirstValue().equals(this.firstValue)
                    && otherVector.getSecondValue().equals(this.secondValue)
                    && otherVector.getThirdValue().equals(this.thirdValue);
        }
        return false;
    }

    @Override
    public int hashCode() {

        int hash = 5;
        hash = 29 * hash
                + (this.firstValue != null ? this.firstValue.hashCode() : 0);
        hash = 29 * hash
                + (this.secondValue != null ? this.secondValue.hashCode() : 0);
        hash = 29 * hash
                + (this.thirdValue != null ? this.thirdValue.hashCode() : 0);
        return hash;
    }

    // </editor-fold>

}
