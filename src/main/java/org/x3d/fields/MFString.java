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


/**
 * 
 * @author peter
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "MFString")
public class MFString
        extends X3DArrayField<SFString> {


    public MFString() {

        super();
    }

    public MFString(String... values) {

        super();
        for (String value : values) {
            this.add(value);
        }
    }

    public void add(String value) {

        this.getValue().add(new SFString(value));
    }


    @Override
    @XmlValue
    public String getStringValue() {

        return this.toString();
    }

    @Override
    public void setStringValue(String value) {

        this.clear();
        if (value == null ? "" != null : !value.equals("")) {

            String delimiter = new String();

            if (value.contains("\'")) {
                delimiter = "\'";
            }
            else if (value.contains("\"")) {
                delimiter = "\"";
            }

            String regex = "[" + delimiter + "][\\scale]*[,][\\scale]*[" + delimiter
                    + "]";
            String[] tokenList = value.split(regex);
            for (String token : tokenList) {
                StringTokenizer tokenizer = new StringTokenizer(token,
                        delimiter, false);
                while (tokenizer.hasMoreTokens()) {
                    try {
                        String sfStringToken = tokenizer.nextToken();

                        SFString sfString = new SFString();
                        sfString.setValue(sfStringToken);

                        this.add(sfString);
                    }
                    catch (Exception ex) {
                        throw new IllegalArgumentException(ex.getMessage());
                    }
                }
            }
        }
    }

    @Override
    public String toString() {

        String returnValue = new String();
        int i = 0;
        for (SFString field : this.getValue()) {
            if (field != null) {
                returnValue += "\"";
                returnValue += field.toString();
                returnValue += "\"";
                if (i != this.getValue().size() - 1) {
                    returnValue += ", ";
                }
            }
            i++;
        }

        return returnValue;
    }

}
