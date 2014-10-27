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
@XmlType(name = "SFBool", propOrder="value")
public class SFBool 
        extends X3DField implements Comparable<SFBool>{

// <editor-fold defaultstate="expanded" desc="Fields">

    protected Boolean value;

    public static final SFBool TRUE = new SFBool(true);
    public static final SFBool FALSE = new SFBool(false);

// </editor-fold>

    
// <editor-fold defaultstate="expanded" desc="Properties">
    @XmlValue
    public Boolean getValue (){
        return this.value;
    }

    public void setValue(Boolean value){
        this.value = value;
    }
    
// </editor-fold>


// <editor-fold defaultstate="expanded" desc="Construction/Destruction/Initialization">

    public SFBool (){
        this.value = Boolean.FALSE;
    }

    public SFBool ( Boolean value ) {
        this.value = value;
    }

// </editor-fold>


// <editor-fold defaultstate="expanded" desc="Comparable Implementation">


    @Override
    public int compareTo(SFBool t) {
        if (t == null){
            throw new NullPointerException("Cannot compare to null.");
        }
        else {
            return this.value.compareTo(t.getValue());
        }
    }

    public static int compareTo (SFBool lhs, Boolean rhs) {
        return lhs.getValue().compareTo(rhs);
    }

    public static int compareTo (Boolean lhs, SFBool rhs) {
        return lhs.compareTo(rhs.getValue());
    }

    public static int compareTo (SFBool lhs, SFBool rhs) {
        return lhs.compareTo(rhs);
    }

// </editor-fold>

    
// <editor-fold defaultstate="expanded" desc="Overrides and Overridables">

    @Override
    public String toString(){
        return this.value.toString();
    }

    @Override
    public SFBool clone () {
        return new SFBool(this.value);

    }

    @Override
    public boolean equals(Object o) {
        if (o != null &&
            o.getClass().equals(SFBool.class)){
            return this.value.equals(((SFBool)o).getValue());
        }
        else{
            return false;
        }
    }

    public static Boolean equals (Boolean lhs, SFBool rhs) {
        return lhs.equals(rhs.getValue());
    }

    public static Boolean equals (SFBool lhs, Boolean rhs) {
        return lhs.getValue().equals(rhs);
    }

    public static Boolean equals (SFBool lhs, SFBool rhs) {
        return lhs.equals(rhs);
    }

    @Override
    public int hashCode() {
        return this.getValue().hashCode();
    }
    
// </editor-fold>

}
