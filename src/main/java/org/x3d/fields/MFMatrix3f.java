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
@XmlType(name = "MFMatrix3f")
public class MFMatrix3f
        extends X3DArrayField <SFMatrix3f> {


    @Override
    @XmlValue
    public String getStringValue (){
        return this.toString();
    }

    @Override
    public void setStringValue (String value)
            throws IllegalArgumentException {

        ArrayList<String> tokenList = this.getListFromString(value);

        for (String token : tokenList){
            StringTokenizer tokenizer = new StringTokenizer(token, " ", false);
            while (tokenizer.hasMoreTokens()){
                try{
                    String sfMatrix3fToken = tokenizer.nextToken();
                    for (int i = 0; i < 7; i++){
                        sfMatrix3fToken += " " + tokenizer.nextToken();
                    }
                    sfMatrix3fToken = tokenizer.nextToken();

  
                    SFMatrix3f sfMatrix3f = new SFMatrix3f();
                    sfMatrix3f.setStringValue(sfMatrix3fToken);

                    this.add(sfMatrix3f);
                }
                catch (Exception ex){
                    throw new IllegalArgumentException(ex.getMessage());
                }
            }
        }
    }

    public MFMatrix3f(){
        super();
    }

}