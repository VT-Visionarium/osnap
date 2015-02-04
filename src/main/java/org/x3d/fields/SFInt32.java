/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

package org.x3d.fields;


/*
 * _ The Open Semantic Network Analysis Platform (OSNAP) _ Copyright (C) 2012 -
 * 2014 Visionarium at Virginia Tech _ Licensed under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License. _
 */

import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Peter J. Radics
 * @version 0.8.0
 * @since 0.1.0
 */

@XmlType(name = "SFInt32", propOrder = "value")
public class SFInt32
        extends X3DField
        implements Comparable<SFInt32> {

    private Integer value;


    /**
     * Returns the value.
     * 
     * @return the value.
     */
    @XmlValue
    public Integer getValue() {

        return value;
    }

    /**
     * Sets the value.
     * 
     * @param value
     *            the value.
     */
    public void setValue(Integer value) {

        this.value = value;
    }



    /**
     * Creates a new instance of the {@code SFInt32} class.
     */
    public SFInt32() {

        this(0);
    }

    /**
     * Creates a new instance of the {@code SFInt32} class with the provided
     * value.
     * 
     * @param value
     *            the value
     */
    public SFInt32(final Integer value) {

        this.value = value;
    }


    /**
     * Adds the provided value to this {@code SFInt32}.
     * 
     * @param value
     *            the value to add.
     */
    public void add(final Integer value) {

        this.value += value;
    }


    /**
     * Adds the provided value to this {@code SFInt32}.
     * 
     * @param value
     *            the value to add.
     */
    public void add(SFInt32 value) {

        this.add(value.getValue());
    }

    /**
     * Adds the two values.
     * 
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @return the sum of the two values.
     */
    public static SFInt32 add(SFInt32 lhs, SFInt32 rhs) {

        return SFInt32.add(lhs.getValue(), rhs.getValue());
    }

    /**
     * Adds the two values.
     * 
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @return the sum of the two values.
     */
    public static SFInt32 add(SFInt32 lhs, Integer rhs) {

        return SFInt32.add(lhs.getValue(), rhs);
    }

    /**
     * Adds the two values.
     * 
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @return the sum of the two values.
     */
    public static SFInt32 add(Integer lhs, SFInt32 rhs) {

        return SFInt32.add(lhs, rhs.getValue());
    }

    /**
     * Adds the two values.
     * 
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @return the sum of the two values.
     */
    public static SFInt32 add(Integer lhs, Integer rhs) {

        return new SFInt32(lhs + rhs);
    }


    /**
     * Subtracts the provided value from this {@code SFInt32}.
     * 
     * @param value
     *            the value to subtract.
     */
    public void subtract(Integer value) {

        this.value -= value;
    }


    /**
     * Subtracts the provided value from this {@code SFInt32}.
     * 
     * @param value
     *            the value to subtract.
     */
    public void subtract(SFInt32 value) {

        this.subtract(value.getValue());
    }

    /**
     * Subtracts the two values.
     * 
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @return the difference of the two values.
     */
    public static SFInt32 subtract(SFInt32 lhs, SFInt32 rhs) {

        return SFInt32.subtract(lhs.getValue(), rhs.getValue());
    }

    /**
     * Subtracts the two values.
     * 
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @return the difference of the two values.
     */
    public static SFInt32 subtract(SFInt32 lhs, Integer rhs) {

        return SFInt32.subtract(lhs.getValue(), rhs);
    }

    /**
     * Subtracts the two values.
     * 
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @return the difference of the two values.
     */
    public static SFInt32 subtract(Integer lhs, SFInt32 rhs) {

        return SFInt32.subtract(lhs, rhs.getValue());
    }

    /**
     * Subtracts the two values.
     * 
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @return the difference of the two values.
     */
    public static SFInt32 subtract(Integer lhs, Integer rhs) {

        return new SFInt32(lhs - rhs);
    }


    /**
     * Multiplies the provided value with this {@code SFInt32}.
     * 
     * @param value
     *            the value to multiply.
     */
    public void multiply(Integer value) {

        this.value *= value;
    }

    /**
     * Multiplies the provided value with this {@code SFInt32}.
     * 
     * @param value
     *            the value to multiply.
     */
    public void multiply(SFInt32 value) {

        this.multiply(value.getValue());
    }

    /**
     * Multiplies the two values.
     * 
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @return the product of the two values.
     */
    public static SFInt32 multiply(SFInt32 lhs, SFInt32 rhs) {

        return SFInt32.multiply(lhs.getValue(), rhs.getValue());
    }

    /**
     * Multiplies the two values.
     * 
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @return the product of the two values.
     */
    public static SFInt32 multiply(SFInt32 lhs, Integer rhs) {

        return SFInt32.multiply(lhs.getValue(), rhs);
    }

    /**
     * Multiplies the two values.
     * 
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @return the product of the two values.
     */
    public static SFInt32 multiply(Integer lhs, SFInt32 rhs) {

        return SFInt32.multiply(lhs, rhs.getValue());
    }

    /**
     * Multiplies the two values.
     * 
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @return the product of the two values.
     */
    public static SFInt32 multiply(Integer lhs, Integer rhs) {

        return new SFInt32(lhs * rhs);
    }

    /**
     * Divides this {@code SFInt32} by the provided value.
     * 
     * @param value
     *            the value to divide by.
     */
    public void divide(Integer value) {

        this.value /= value;
    }

    /**
     * Divides this {@code SFInt32} by the provided value.
     * 
     * @param value
     *            the value to divide by.
     */
    public void divide(SFInt32 value) {

        this.divide(value.getValue());
    }

    /**
     * Divides the two values.
     * 
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @return the quotient of the two values.
     */
    public static SFInt32 divide(SFInt32 lhs, SFInt32 rhs) {

        return SFInt32.divide(lhs.getValue(), rhs.getValue());
    }

    /**
     * Divides the two values.
     * 
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @return the quotient of the two values.
     */
    public static SFInt32 divide(SFInt32 lhs, Integer rhs) {

        return SFInt32.divide(lhs.getValue(), rhs);
    }

    /**
     * Divides the two values.
     * 
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @return the quotient of the two values.
     */
    public static SFInt32 divide(Integer lhs, SFInt32 rhs) {

        return SFInt32.divide(lhs, rhs.getValue());
    }

    /**
     * Divides the two values.
     * 
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @return the quotient of the two values.
     */
    public static SFInt32 divide(Integer lhs, Integer rhs) {

        return new SFInt32(lhs + rhs);
    }

    public static SFInt32 convert(SFInt32 value) {

        return new SFInt32(value.getValue().intValue());
    }

    public static SFInt32 convert(SFFloat value) {

        return new SFInt32(value.getValue().intValue());
    }


    public static Boolean equals(Integer lhs, SFInt32 rhs) {

        return lhs.equals(rhs.getValue());
    }

    public static Boolean equals(SFInt32 lhs, Integer rhs) {

        return lhs.getValue().equals(rhs);
    }

    public static Boolean equals(SFInt32 lhs, SFInt32 rhs) {

        return lhs.equals(rhs);
    }



    @Override
    public int compareTo(SFInt32 t) {

        if (t == null) {
            throw new NullPointerException("Cannot compare to null.");
        }
        else {
            return this.value.compareTo(t.getValue());
        }
    }

    public static int compareTo(SFInt32 lhs, Integer rhs) {

        return lhs.getValue().compareTo(rhs);
    }

    public static int compareTo(Integer lhs, SFInt32 rhs) {

        return lhs.compareTo(rhs.getValue());
    }

    public static int compareTo(SFInt32 lhs, SFInt32 rhs) {

        return lhs.compareTo(rhs);
    }



    @Override
    public String toString() {

        return this.value.toString();
    }

    @Override
    public SFInt32 clone() {

        return new SFInt32(this.value);
    }

    @Override
    public boolean equals(Object other) {

        if (other != null && other.getClass().equals(SFInt32.class)) {

            return this.value.equals(((SFInt32) other).getValue());
        }

        return false;
    }

    @Override
    public int hashCode() {

        return this.value.hashCode();
    }
}
