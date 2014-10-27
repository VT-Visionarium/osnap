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

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author peter
 */
@XmlType (name="SFColorRGBA")
public class SFColorRGBA
        extends X3DField{

    protected ArrayList<Float> value = new ArrayList<>(4);

    public SFColorRGBA ()
    {
        value.add(0, 0.0f);
        value.add(1, 0.0f);
        value.add(2, 0.0f);
        value.add(3, 0.0f);
    }

    @XmlValue
    public ArrayList<Float> getValue(){
        return value;
    }

    public void setValue ( ArrayList<Float> value)
            throws IllegalArgumentException {
        if (value.size() != 4)
        {
            throw new IllegalArgumentException();
        }
        for (Float floatValue : value){
            if (floatValue < 0.0 || floatValue > 1.0){
                throw new IllegalArgumentException();
            }
        
        }

        this.value = value;
    }

    public void setRedValue ( Float value )
            throws IllegalArgumentException{
        if (value < 0.0 || value > 1.0)
        {
            throw new IllegalArgumentException();
        }
        this.value.set(0, value);
    }
    
    public void setGreenValue ( Float value )
            throws IllegalArgumentException{
        if (value < 0.0 || value > 1.0)
        {
            throw new IllegalArgumentException();
        }
        this.value.set(1, value);
    }

    public void setBlueValue ( Float value )
                throws IllegalArgumentException{
        if (value < 0.0 || value > 1.0)
        {
            throw new IllegalArgumentException();
        }
        this.value.set(2, value);
    }

    public void setAlphaValue ( Float value )
                throws IllegalArgumentException{
        if (value < 0.0 || value > 1.0)
        {
            throw new IllegalArgumentException();
        }
        this.value.set(3, value);
    }

    @Override
    public String toString(){
        String returnValue = new String();

        int i = 0;
        for (Float floatValue : this.value){
            returnValue += floatValue.toString();
            if (i != 3)
            {
                returnValue += " ";
            }
            i++;
        }


        return returnValue;
    }
}
