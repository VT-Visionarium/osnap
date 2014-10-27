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
@XmlType(name = "SFMatrix4f")
public class SFMatrix4f
        extends X3DField  {
    private SFVec4f firstValue;
    private SFVec4f secondValue;
    private SFVec4f thirdValue;
    private SFVec4f fourthValue;

    public SFVec4f getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(SFVec4f firstValue) {
        this.firstValue = firstValue;
    }

    public SFVec4f getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(SFVec4f secondValue) {
        this.secondValue = secondValue;
    }

    public SFVec4f getThirdValue() {
        return thirdValue;
    }

    public void setThirdValue(SFVec4f thirdValue) {
        this.thirdValue = thirdValue;
    }

    public SFVec4f getFourthValue() {
        return fourthValue;
    }

    public void setFourthValue(SFVec4f fourthValue) {
        this.fourthValue = fourthValue;
    }

    @XmlValue
    public String getStringValue (){
        return this.toString();
    }

    public void setStringValue (String value){
        StringTokenizer tokenizer = new StringTokenizer(value, " ", false);

        for (int i = 0; i < 4; i++){
            String sfVec4fToken = tokenizer.nextToken();
            sfVec4fToken += " " + tokenizer.nextToken();
            sfVec4fToken += " " + tokenizer.nextToken();
            sfVec4fToken += " " + tokenizer.nextToken();

            SFVec4f rowVector = new SFVec4f();
            rowVector.setStringValue(sfVec4fToken);
            
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
                case 4:
                    this.setFourthValue(rowVector);
                    break;
            }
        }
    }

    public SFMatrix4f(){
        this.firstValue = new SFVec4f(1.0f, 0.0f, 0.0f, 0.0f);
        this.secondValue = new SFVec4f(0.0f, 1.0f, 0.0f, 0.0f);
        this.thirdValue = new SFVec4f(0.0f, 0.0f, 1.0f, 0.0f);
        this.fourthValue = new SFVec4f(0.0f, 0.0f, 0.0f, 1.0f);
    }

    public SFMatrix4f (SFVec4f firstValue, SFVec4f secondValue, SFVec4f thirdValue, SFVec4f fourthValue){
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.thirdValue = thirdValue;
        this.fourthValue = fourthValue;
    }

    public static SFMatrix4f convert (SFMatrix4d value) {
        return new SFMatrix4f (SFVec4f.convert(value.getFirstValue()),
                               SFVec4f.convert(value.getSecondValue()),
                               SFVec4f.convert(value.getThirdValue()),
                               SFVec4f.convert(value.getFourthValue()));
    }

    @Override
    public String toString() {
        String returnValue = new String();

        returnValue += this.firstValue;
        returnValue += " ";
        returnValue += this.secondValue;
        returnValue += " ";
        returnValue += this.thirdValue;
        returnValue += " ";
        returnValue += this.fourthValue;

        return returnValue;
    }
}
