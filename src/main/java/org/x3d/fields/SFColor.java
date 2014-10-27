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


import java.awt.Color;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * 
 * @author peter
 */
@XmlType(name = "SFColor", propOrder = "value")
public class SFColor
        extends X3DField {

    public static final SFColor RED   = new SFColor(1.0f, 0.0f, 0.0f);
    public static final SFColor GREEN = new SFColor(0.0f, 1.0f, 0.0f);
    public static final SFColor BLUE  = new SFColor(0.0f, 0.0f, 1.0f);
    public static final SFColor BLACK = new SFColor(0.0f, 0.0f, 0.0f);
    public static final SFColor WHITE = new SFColor(1.0f, 1.0f, 1.0f);

    protected ArrayList<Float>  value = new ArrayList<>(3);

    public SFColor() {

        this(0.0f, 0.0f, 0.0f);
    }

    public SFColor(javafx.scene.paint.Color color) {

        this((float) color.getRed(), (float) color.getGreen(), (float) color
                .getBlue());
    }

    public SFColor(Color color) {

        this(color.getRed(), color.getGreen(), color.getBlue());
    }

    public SFColor(int redValue, int greenValue, int blueValue) {

        this(redValue / 255f, greenValue / 255f, blueValue / 255f);
    }

    public SFColor(float redValue, float greenValue, float blueValue) {

        if (redValue < 0.0 || redValue > 1.0) {
            throw new IllegalArgumentException();
        }
        if (greenValue < 0.0 || greenValue > 1.0) {
            throw new IllegalArgumentException();
        }
        if (blueValue < 0.0 || blueValue > 1.0) {
            throw new IllegalArgumentException();
        }
        value.add(0, redValue);
        value.add(1, greenValue);
        value.add(2, blueValue);
    }

    @XmlValue
    public ArrayList<Float> getValue() {

        return value;
    }

    public void setValue(ArrayList<Float> value)
            throws IllegalArgumentException {

        if (value.size() != 3) {
            throw new IllegalArgumentException();
        }
        for (Float floatValue : value) {
            if (floatValue < 0.0 || floatValue > 1.0) {
                throw new IllegalArgumentException();
            }
        }
        this.value = value;
    }

    public void setRedValue(Float value)
            throws IllegalArgumentException {

        if (value < 0.0 || value > 1.0) {
            throw new IllegalArgumentException();
        }
        this.value.set(0, value);
    }

    public void setGreenValue(Float value)
            throws IllegalArgumentException {

        if (value < 0.0 || value > 1.0) {
            throw new IllegalArgumentException();
        }
        this.value.set(1, value);
    }

    public void setBlueValue(Float value)
            throws IllegalArgumentException {

        if (value < 0.0 || value > 1.0) {
            throw new IllegalArgumentException();
        }
        this.value.set(2, value);
    }


    @Override
    public String toString() {

        String returnValue = new String();

        int i = 0;
        for (Float floatValue : this.value) {
            returnValue += floatValue.toString();
            if (i != 2) {
                returnValue += " ";
            }
            i++;
        }


        return returnValue;
    }
}
