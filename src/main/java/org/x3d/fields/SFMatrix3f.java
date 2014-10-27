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

/**
 *
 * @author peter
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "SFMatrix3f")
public class SFMatrix3f
        extends X3DField  {
    private SFVec3f firstValue;
    private SFVec3f secondValue;
    private SFVec3f thirdValue;

    public SFVec3f getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(SFVec3f firstValue) {
        this.firstValue = firstValue;
    }

    public SFVec3f getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(SFVec3f secondValue) {
        this.secondValue = secondValue;
    }

    public SFVec3f getThirdValue() {
        return thirdValue;
    }

    public void setThirdValue(SFVec3f thirdValue) {
        this.thirdValue = thirdValue;
    }

    @XmlValue
    public String getStringValue (){
        return this.toString();
    }

    public void setStringValue (String value){
        StringTokenizer tokenizer = new StringTokenizer(value, " ", false);

        for (int i = 0; i < 3; i++){
            String firstToken = tokenizer.nextToken();
            String secondToken = tokenizer.nextToken();
            String thirdToken = tokenizer.nextToken();

            SFVec3f rowVector = new SFVec3f();
            rowVector.setFirstValue(new SFFloat (Float.parseFloat(firstToken)));
            rowVector.setSecondValue(new SFFloat (Float.parseFloat(secondToken)));
            rowVector.setThirdValue(new SFFloat (Float.parseFloat(thirdToken)));
            
            switch (i){
                case 0:
                    this.setFirstValue(rowVector);
                    break;
                case 1:
                    this.setSecondValue(rowVector);
                    break;
                case 3:
                    this.setThirdValue(rowVector);
                    break;
            }
        }
    }

    public SFMatrix3f(){
        this.firstValue = new SFVec3f(1.0f, 0.0f, 0.0f);
        this.secondValue = new SFVec3f(0.0f, 1.0f, 0.0f);
        this.thirdValue = new SFVec3f(0.0f, 0.0f, 1.0f);
    }

    public SFMatrix3f (SFVec3f firstValue, SFVec3f secondValue, SFVec3f thirdValue){
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.thirdValue = thirdValue;
    }

    public static SFMatrix3f convert (SFMatrix3d value) {
        return new SFMatrix3f (SFVec3f.convert(value.getFirstValue()),
                               SFVec3f.convert(value.getSecondValue()),
                               SFVec3f.convert(value.getThirdValue()));
    }

    @Override
    public String toString() {
        String returnValue = new String();

        returnValue += this.firstValue;
        returnValue += " ";
        returnValue += this.secondValue;
        returnValue += " ";
        returnValue += this.thirdValue;

        return returnValue;
    }


}
