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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import java.util.StringTokenizer;
import java.util.ArrayList;


/**
 * 
 * @author peter
 */

// @XmlType(name = "MFInt32", propOrder = {"value"})
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "MFInt32")
@XmlRootElement(name = "MInt32")
public class MFInt32
        extends X3DArrayField<SFInt32> {


    public MFInt32() {

        super();
    }


    public MFInt32(Integer... values) {

        super();
        for (Integer value : values) {
            this.add(value);
        }
    }

    public MFInt32(SFInt32... values) {

        super(values);
    }

    public void add(Integer value) {

        this.getValue().add(new SFInt32(value));
    }

    @Override
    public String toString() {

        return this.getStringFromList(this.getValue());
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

                    Integer firstValue = Integer.parseInt(firstToken);

                    SFInt32 sfInt32 = new SFInt32();
                    sfInt32.setValue(firstValue);

                    this.add(sfInt32);
                }
                catch (Exception ex) {
                    throw new IllegalArgumentException(ex.getMessage());
                }
            }
        }
    }

}
