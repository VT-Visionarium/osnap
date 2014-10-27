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
import java.util.ArrayList;


/**
 * 
 * @author peter
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "MFColorRGBA")
public class MFColorRGBA
        extends X3DArrayField<SFColorRGBA> {

    // <editor-fold defaultstate="expanded"
    // desc="Construction/Destruction/Initialization">

    public MFColorRGBA() {

        super();
    }

    public MFColorRGBA(SFColorRGBA... values) {

        super(values);
    }

    // </editor-fold>


    // <editor-fold defaultstate="expanded" desc="Public Methods">

    @Override
    public void add(SFColorRGBA color)
            throws IllegalArgumentException {

        if (color.getValue().size() % 4 != 0) {
            throw new IllegalArgumentException();
        }
        this.getValue().add(color);

    }


    @Override
    @XmlValue
    public String getStringValue() {

        return this.toString();
    }

    @Override
    public void setStringValue(String value) {

        ArrayList<String> tokenList = this.getListFromString(value);

        for (String token : tokenList) {
            StringTokenizer tokenizer = new StringTokenizer(token, " ", false);
            while (tokenizer.hasMoreTokens()) {
                try {
                    String firstToken = tokenizer.nextToken();
                    String secondToken = tokenizer.nextToken();
                    String thirdToken = tokenizer.nextToken();
                    String fourthToken = tokenizer.nextToken();

                    Float firstValue = Float.parseFloat(firstToken);
                    Float secondValue = Float.parseFloat(secondToken);
                    Float thirdValue = Float.parseFloat(thirdToken);
                    Float fourthValue = Float.parseFloat(fourthToken);

                    SFColorRGBA sfcolor = new SFColorRGBA();
                    sfcolor.setRedValue(firstValue);
                    sfcolor.setGreenValue(secondValue);
                    sfcolor.setBlueValue(thirdValue);
                    sfcolor.setAlphaValue(fourthValue);

                    this.add(sfcolor);
                }
                catch (Exception ex) {
                    throw new IllegalArgumentException(ex.getMessage());
                }
            }
        }
    }


    // </editor-fold>
}
