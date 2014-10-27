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
import java.util.Date;

/**
 *
 * @author Peter J. Radics
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "SFTime")
public class SFTime
        extends X3DField implements Comparable<SFTime> {

    // <editor-fold defaultstate="expanded" desc="Fields">

    private Date value;

    // </editor-fold>


    // <editor-fold defaultstate="expanded" desc="Properties">

    public Date getValue() {
        return value;
    }

    public void setValue(Date value) {
        this.value = value;
    }

    @XmlValue
    public String getStringValue (){
        return this.toString();
    }

    public void setStringValue (String value){
        StringTokenizer tokenizer = new StringTokenizer(value, " ", false);

        String timeToken = tokenizer.nextToken();

        Double timeDouble = Double.parseDouble(timeToken);
        Long time = timeDouble.longValue() * 1000;
        this.setValue(new Date (time));
    }

    // </editor-fold>

    
    // <editor-fold defaultstate="expanded" desc="Construction/Destruction/Initialization">

    public SFTime(){
        this(new Date(-1));
    }

    public SFTime (Date value){
        this.value = value;
    }

    // </editor-fold>

    // <editor-fold defaultstate="expanded" desc="Comparable Implementation">

    @Override
    public int compareTo(SFTime t) {
        if (t == null){
            throw new NullPointerException("Cannot compare to null.");
        }
        else {
            return this.value.compareTo(t.getValue());
        }
    }

    public static int compareTo (SFTime lhs, Date rhs) {
        return lhs.getValue().compareTo(rhs);
    }

    public static int compareTo (Date lhs, SFTime rhs) {
        return lhs.compareTo(rhs.getValue());
    }

    public static int compareTo (SFTime lhs, SFTime rhs) {
        return lhs.compareTo(rhs);
    }

    // </editor-fold>
    

    // <editor-fold defaultstate="expanded" desc="Overrides and Overridables">

    @Override
    public String toString() {
        String returnValue = new String();

        Long date = this.value.getTime();
        Double dateDouble = date / 1000.0;
        returnValue += dateDouble.toString();

        return returnValue;
    }

    
    @Override
    public boolean equals(Object o) {
        if (o != null &&
            o.getClass().equals(SFTime.class)){
            return this.value.equals(((SFTime)o).getValue());
        }
        else{
            return false;
        }
    }

    public static Boolean equals (Date lhs, SFTime rhs) {
        return lhs.equals(rhs.getValue());
    }

    public static Boolean equals (SFTime lhs, Date rhs) {
        return lhs.getValue().equals(rhs);
    }

    public static Boolean equals (SFTime lhs, SFTime rhs) {
        return lhs.equals(rhs);
    }

    @Override
    public int hashCode() {
        return this.getValue().hashCode();
    }

}
