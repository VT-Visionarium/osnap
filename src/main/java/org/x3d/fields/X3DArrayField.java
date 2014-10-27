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


import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.xml.bind.annotation.XmlTransient;


/**
 * 
 * @author peter
 */
@XmlTransient
public abstract class X3DArrayField<T>
        extends X3DField {

    private ArrayList<T> value;

    public ArrayList<T> getValue() {

        return this.value;
    }

    public void setValue(ArrayList<T> value) {

        this.value = value;
    }

    public X3DArrayField() {

        this.value = new ArrayList<>();
    }

    @SafeVarargs
	public X3DArrayField(T... values) {

        this.value = new ArrayList<>();

        for (T value : values) {
            this.value.add(value);
        }

    }


    public void add(T value) {

        this.getValue().add(value);
    }

    public void clear() {

        this.getValue().clear();
    }

    protected ArrayList<String> getListFromString(String value) {

        ArrayList<String> tokenList = new ArrayList<>();

        String tokenString = value;

        if (tokenString.contains(",")) {
            StringTokenizer tokenizer = new StringTokenizer(tokenString, ",",
                    false);
            while (tokenizer.hasMoreTokens()) {
                tokenList.add(tokenizer.nextToken());
            }
        }
        else {
            tokenList.add(tokenString);
        }
        return tokenList;
    }

    protected String getStringFromList(ArrayList<T> value) {

        String returnValue = new String();
        int i = 0;
        for (T field : value) {
            if (field != null) {
                returnValue += field.toString();
                if (i != value.size() - 1) {
                    returnValue += ", ";
                }
            }
            i++;
        }

        return returnValue;
    }

    @Override
    public String toString() {

        return this.getStringFromList(this.getValue());
    }

    public abstract String getStringValue();

    public abstract void setStringValue(String value);
}
