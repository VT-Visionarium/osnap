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
@XmlType(name="SFString", propOrder="value")
public class SFString 
        extends X3DField
        implements Comparable <SFString>{

    private String value;
    
    @XmlValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public SFString (String value){
        this.value = value;
    }

    public SFString (){
        this.value = new String();
    }

// <editor-fold defaultstate="expanded" desc="Comparable Implementation">

    @Override
    public int compareTo(SFString t) {
        if (t == null){
            throw new NullPointerException("Cannot compare to null.");
        }
        else {
            return this.value.compareTo(t.getValue());
        }
    }

    public static int compareTo (SFString lhs, String rhs) {
        return lhs.getValue().compareTo(rhs);
    }

    public static int compareTo (String lhs, SFString rhs) {
        return lhs.compareTo(rhs.getValue());
    }

    public static int compareTo (SFString lhs, SFString rhs) {
        return lhs.compareTo(rhs);
    }
    
// </editor-fold>


// <editor-fold defaultstate="expanded" desc="Overrides and Overridables">

    @Override
    public String toString(){
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null &&
            o.getClass().equals(SFString.class)){
            return this.value.equals(((SFString)o).getValue());
        }
        else{
            return false;
        }
    }

    public static Boolean equals (String lhs, SFString rhs) {
        return lhs.equals(rhs.getValue());
    }

    public static Boolean equals (SFString lhs, String rhs) {
        return lhs.getValue().equals(rhs);
    }

    public static Boolean equals (SFString lhs, SFString rhs) {
        return lhs.equals(rhs);
    }

    @Override
    public int hashCode() {
        return this.getValue().hashCode();
    }

// </editor-fold>

}
