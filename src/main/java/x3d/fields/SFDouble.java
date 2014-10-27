/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package x3d.fields;

import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.XmlType;
/**
 *
 * @author peter
 */
@XmlType(name="SFDouble", propOrder="value")
public class SFDouble 
        extends X3DField implements Comparable<SFDouble>{

    // <editor-fold defaultstate="collapsed" desc="Fields">

    private Double value;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Properties">

    @XmlValue
    public Double getValue() {
        if (value == null)
        {
            return 0.0;
        }
        return value;
    }

    public void setValue(Double value) {
        this.value = value;

    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Construction/Destruction/Initialization">

    public SFDouble (Double value){
        this.value = value;
    }

    public SFDouble (){
        this.value = new Double(0);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    public void add (Double value) {
        this.value += value;
    }

    public void add (SFDouble value) {
        this.add(value.getValue());
    }

    public static SFDouble add (SFDouble lhs, SFDouble rhs) {
        return SFDouble.add(lhs.getValue(), rhs.getValue());
    }

    public static SFDouble add (SFDouble lhs, Double rhs) {
        return SFDouble.add(lhs.getValue(), rhs);
    }

    public static SFDouble add (Double lhs, SFDouble rhs) {
        return SFDouble.add(lhs, rhs.getValue());
    }

    public static SFDouble add (Double lhs, Double rhs) {
        return new SFDouble (lhs + rhs);
    }

    public void subtract (Double value) {
        this.value -= value;
    }


    public void subtract (SFDouble value) {
        this.subtract(value.getValue());
    }

    public static SFDouble subtract (SFDouble lhs, SFDouble rhs) {
        return SFDouble.subtract(lhs.getValue(), rhs.getValue());
    }

    public static SFDouble subtract (SFDouble lhs, Double rhs) {
        return SFDouble.subtract(lhs.getValue(), rhs);
    }

    public static SFDouble subtract (Double lhs, SFDouble rhs) {
        return SFDouble.subtract(lhs, rhs.getValue());
    }

    public static SFDouble subtract (Double lhs, Double rhs) {
        return new SFDouble (lhs - rhs);
    }

    public void multiply (Double value) {
        this.value *= value;
    }

    public void multiply (SFDouble value) {
        this.multiply(value.getValue());
    }

    public static SFDouble multiply (SFDouble lhs, SFDouble rhs) {
        return SFDouble.multiply(lhs.getValue(), rhs.getValue());
    }

    public static SFDouble multiply (SFDouble lhs, Double rhs) {
        return SFDouble.multiply(lhs.getValue(), rhs);
    }

    public static SFDouble multiply (Double lhs, SFDouble rhs) {
        return SFDouble.multiply(lhs, rhs.getValue());
    }

    public static SFDouble multiply (Double lhs, Double rhs) {
        return new SFDouble (lhs * rhs);
    }

    public void divide (Double value) {
        this.value /= value;
    }

    public void divide (SFDouble value) {
        this.divide(value.getValue());
    }

    public static SFDouble divide (SFDouble lhs, SFDouble rhs) {
        return SFDouble.divide(lhs.getValue(), rhs.getValue());
    }

    public static SFDouble divide (SFDouble lhs, Double rhs) {
        return SFDouble.divide(lhs.getValue(), rhs);
    }

    public static SFDouble divide (Double lhs, SFDouble rhs) {
        return SFDouble.divide(lhs, rhs.getValue());
    }

    public static SFDouble divide (Double lhs, Double rhs) {
        return new SFDouble (lhs + rhs);
    }


    public static SFDouble convert (SFInt32 value) {
        return new SFDouble (value.getValue().doubleValue());
    }

    public static SFDouble convert (SFFloat value) {
        return new SFDouble (value.getValue().doubleValue());
    }


    public static Boolean equals (Double lhs, SFDouble rhs) {
        return lhs.equals(rhs.getValue());
    }

    public static Boolean equals (SFDouble lhs, Double rhs) {
        return lhs.getValue().equals(rhs);
    }

    public static Boolean equals (SFDouble lhs, SFDouble rhs) {
        return lhs.equals(rhs);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Comparable members">

    @Override
    public int compareTo(SFDouble t) {
        if (t == null){
            throw new NullPointerException("Cannot compare to null.");
        }
        else {
            return this.value.compareTo(t.getValue());
        }
    }

    public static int compareTo (SFDouble lhs, Double rhs) {
        return lhs.getValue().compareTo(rhs);
    }

    public static int compareTo (Double lhs, SFDouble rhs) {
        return lhs.compareTo(rhs.getValue());
    }

    public static int compareTo (SFDouble lhs, SFDouble rhs) {
        return lhs.compareTo(rhs);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Overrides">

    @Override
    public String toString() {
        return this.value.toString();
    }

    @Override
    public SFDouble clone () {
        return new SFDouble (this.value);
    }

    @Override
    public boolean equals(Object other) {
        if (other != null &&
            other.getClass().equals(SFDouble.class)) {
            SFDouble otherSFDouble = (SFDouble)other;
            double epsilon = 0.0000000001;

            return (Math.abs(this.value - otherSFDouble.getValue()) < epsilon);
        }
        else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }


    // </editor-fold>

}
