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
@XmlType(name = "SFVec2f")
public class SFVec2f
        extends X3DField
        implements Cloneable{

    // <editor-fold defaultstate="collapsed" desc="Fields">

    private SFFloat firstValue;
    private SFFloat secondValue;

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

    @XmlValue
    public String getStringValue (){
        return this.toString();
    }

    public void setStringValue (String value){
        StringTokenizer tokenizer = new StringTokenizer(value, " ", false);

        String firstToken = tokenizer.nextToken();
        String secondToken = tokenizer.nextToken();

        this.setFirstValue(new SFFloat(Float.parseFloat(firstToken)));
        this.setSecondValue(new SFFloat (Float.parseFloat(secondToken)));
    }

    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="Construction/Destruction/Initialization">

    public SFVec2f(){
        this.firstValue = new SFFloat();
        this.secondValue = new SFFloat();
    }

    public SFVec2f (SFFloat firstValue, SFFloat secondValue){
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public SFVec2f (Float firstValue, Float secondValue) {
        this.firstValue = new SFFloat (firstValue);
        this.secondValue = new SFFloat (secondValue);
    }

    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    public static SFVec2f convert (SFVec2d value) {
        return new SFVec2f (value.getFirstValue().getValue().floatValue(),
                            value.getSecondValue().getValue().floatValue());
    }

    public void add (SFVec2f value) {
        this.firstValue.add(value.firstValue);
        this.secondValue.add(value.secondValue);
    }

    public static SFVec2f add (SFVec2f lhs, SFVec2f rhs) {
        return new SFVec2f (SFFloat.add(lhs.firstValue, rhs.firstValue),
                            SFFloat.add(lhs.secondValue, rhs.secondValue));
    }

    public void subtract (SFVec2f value) {
        this.firstValue.subtract(value.firstValue);
        this.secondValue.subtract(value.secondValue);
    }

    public static SFVec2f subtract (SFVec2f lhs, SFVec2f rhs) {
        return new SFVec2f (SFFloat.subtract(lhs.firstValue, rhs.firstValue),
                            SFFloat.subtract(lhs.secondValue, rhs.secondValue));
    }

    public void componentwiseMultiply (SFVec2f value) {
        this.firstValue.multiply(value.firstValue);
        this.secondValue.multiply(value.secondValue);
    }

    public static SFVec2f componentwiseMultiply (SFVec2f lhs, SFVec2f rhs) {
        return new SFVec2f (SFFloat.multiply(lhs.firstValue, rhs.firstValue),
                            SFFloat.multiply(lhs.secondValue, rhs.secondValue));
    }


    public void scalarMultiply (SFFloat value) {
        this.componentwiseMultiply(new SFVec2f(value, value));
    }

    public void scalarMultiply (Float value) {
        this.componentwiseMultiply(new SFVec2f(value, value));
    }

    public static SFVec2f scalarMultiply (SFFloat lhs, SFVec2f rhs) {
        return SFVec2f.scalarMultiply(rhs, lhs);
    }

    public static SFVec2f scalarMultiply (Float lhs, SFVec2f rhs) {
        return SFVec2f.scalarMultiply(rhs, lhs);
    }

    public static SFVec2f scalarMultiply (SFVec2f lhs, SFFloat rhs) {
        return SFVec2f.scalarMultiply(lhs, rhs.getValue());
    }

    public static SFVec2f scalarMultiply (SFVec2f lhs, Float rhs) {
        SFVec2f clone = lhs.clone();

        clone.scalarMultiply(rhs);

        return clone;
    }


    public void componentwiseDivide (SFVec2f value) {
        this.firstValue.divide(value.firstValue);
        this.secondValue.divide(value.secondValue);
    }

    public static SFVec2f componentwiseDivide (SFVec2f lhs, SFVec2f rhs) {
        return new SFVec2f (SFFloat.divide(lhs.firstValue, rhs.firstValue),
                            SFFloat.divide(lhs.secondValue, rhs.secondValue));
    }

    public void scalarDivide (SFFloat value) {
        this.componentwiseDivide(new SFVec2f(value, value));
    }

    public void scalarDivide (Float value) {
        this.componentwiseDivide(new SFVec2f(value, value));
    }



    public static SFVec2f scalarDivide (SFVec2f lhs, SFFloat rhs) {
        return SFVec2f.scalarDivide(lhs, rhs.getValue());
    }

    public static SFVec2f scalarDivide (SFVec2f lhs, Float rhs) {
        SFVec2f clone = lhs.clone();

        clone.componentwiseDivide(new SFVec2f(rhs, rhs));

        return clone;
    }


    public Float length () {
        Float sum = SFFloat.multiply(this.firstValue, this.firstValue).getValue() +
                SFFloat.multiply(this.secondValue, this.secondValue).getValue();
        Double sqrt = Math.sqrt (sum.doubleValue());
        return sqrt.floatValue();
    }

    public Float dotProduct (SFVec2f value) {
        return SFFloat.add(SFFloat.multiply(this.firstValue, value.getFirstValue()),
               SFFloat.multiply(this.secondValue, value.getSecondValue())).getValue();
    }

    public void normalize () {
        if (this.length() != 0) {
            Float length = length();
            this.firstValue.divide(length);
            this.secondValue.divide(length);
        }
    }

    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="Cloneable Members">

    @Override
    public SFVec2f clone () {
        return new SFVec2f(this.firstValue.clone(), this.secondValue.clone());
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
