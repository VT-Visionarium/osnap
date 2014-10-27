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

import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.XmlType;
/**
 *
 * @author peter
 */
@XmlType(name = "SFFloat", propOrder="value")
public class SFFloat 
        extends X3DField implements Comparable<SFFloat> {

    // <editor-fold defaultstate="collapsed" desc="Fields">
    
    private  Float value;

    // </editor-fold>

    
    // <editor-fold defaultstate="collapsed" desc="Properties">

    @XmlValue
    public Float getValue() {
        if (value == null)
        {
            return 0.0f;
        }
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    // </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="Construction/Destruction/Initialization">

    public SFFloat(){
        this.value = new Float(0);
    }

    public SFFloat(Float value){
        this.value = value;
    }

    // </editor-fold>


    // <editor-fold defaultstate="expanded" desc="Public Methods">
    
    public void add (Float value) {
        this.value += value;
    }

    public void add (SFFloat value) {
        this.add(value.getValue());
    }

    public static SFFloat add (SFFloat lhs, SFFloat rhs) {
        return SFFloat.add(lhs.getValue(), rhs.getValue());
    }

    public static SFFloat add (SFFloat lhs, Float rhs) {
        return SFFloat.add(lhs.getValue(), rhs);
    }

    public static SFFloat add (Float lhs, SFFloat rhs) {
        return SFFloat.add(lhs, rhs.getValue());
    }

    public static SFFloat add (Float lhs, Float rhs) {
        return new SFFloat (lhs + rhs);
    }

    public void subtract (Float value) {
        this.value -= value;
    }


    public void subtract (SFFloat value) {
        this.subtract(value.getValue());
    }

    public static SFFloat subtract (SFFloat lhs, SFFloat rhs) {
        return SFFloat.subtract(lhs.getValue(), rhs.getValue());
    }

    public static SFFloat subtract (SFFloat lhs, Float rhs) {
        return SFFloat.subtract(lhs.getValue(), rhs);
    }

    public static SFFloat subtract (Float lhs, SFFloat rhs) {
        return SFFloat.subtract(lhs, rhs.getValue());
    }

    public static SFFloat subtract (Float lhs, Float rhs) {
        return new SFFloat (lhs - rhs);
    }

    public void multiply (Float value) {
        this.value *= value;
    }

    public void multiply (SFFloat value) {
        this.multiply(value.getValue());
    }

    public static SFFloat multiply (SFFloat lhs, SFFloat rhs) {
        return SFFloat.multiply(lhs.getValue(), rhs.getValue());
    }

    public static SFFloat multiply (SFFloat lhs, Float rhs) {
        return SFFloat.multiply(lhs.getValue(), rhs);
    }

    public static SFFloat multiply (Float lhs, SFFloat rhs) {
        return SFFloat.multiply(lhs, rhs.getValue());
    }

    public static SFFloat multiply (Float lhs, Float rhs) {
        return new SFFloat (lhs * rhs);
    }

    public void divide (Float value) {
        this.value /= value;
    }

    public void divide (SFFloat value) {
        this.divide(value.getValue());
    }

    public static SFFloat divide (SFFloat lhs, SFFloat rhs) {
        return SFFloat.divide(lhs.getValue(), rhs.getValue());
    }

    public static SFFloat divide (SFFloat lhs, Float rhs) {
        return SFFloat.divide(lhs.getValue(), rhs);
    }

    public static SFFloat divide (Float lhs, SFFloat rhs) {
        return SFFloat.divide(lhs, rhs.getValue());
    }

    public static SFFloat divide (Float lhs, Float rhs) {
        return new SFFloat (lhs + rhs);
    }

    public static SFFloat convert (SFFloat value) {
        return new SFFloat (value.getValue().floatValue());
    }

    public static SFFloat convert (SFInt32 value) {
        return new SFFloat (value.getValue().floatValue());
    }


    public static Boolean equals (Float lhs, SFFloat rhs) {
        return lhs.equals(rhs.getValue());
    }

    public static Boolean equals (SFFloat lhs, Float rhs) {
        return lhs.getValue().equals(rhs);
    }

    public static Boolean equals (SFFloat lhs, SFFloat rhs) {
        return lhs.equals(rhs);
    }
    
    // </editor-fold>


    // <editor-fold defaultstate="expanded" desc="Comparable members">

    @Override
    public int compareTo(SFFloat t) {
        if (t == null){
            throw new NullPointerException("Cannot compare to null.");
        }
        else {
            return this.value.compareTo(t.getValue());
        }
    }

    public static int compareTo (SFFloat lhs, Float rhs) {
        return lhs.getValue().compareTo(rhs);
    }

    public static int compareTo (Float lhs, SFFloat rhs) {
        return lhs.compareTo(rhs.getValue());
    }

    public static int compareTo (SFFloat lhs, SFFloat rhs) {
        return lhs.compareTo(rhs);
    }
    
// </editor-fold>


    // <editor-fold defaultstate="expanded" desc="Overrides">

    @Override
    public String toString(){
        return this.value.toString();
    }

    @Override
    public SFFloat clone () {
        return new SFFloat (this.value);
    }

    @Override
    public boolean equals(Object other) {
        if (other != null &&
            other.getClass().equals(SFFloat.class)) {
            SFFloat otherSFFloat = (SFFloat)other;
            float epsilon = 0.0000000001f;

            return (Math.abs(this.value - otherSFFloat.getValue()) < epsilon);
        }
        else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.value != null ? this.value.hashCode() : 0);
        return hash;
    }


    // </editor-fold>

}
