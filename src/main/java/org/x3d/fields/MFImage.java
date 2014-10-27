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
@XmlType(name = "MFImage")
public class MFImage 
        extends X3DArrayField <SFImage> {


    public MFImage() {
        super();
    }


   @Override
@XmlValue
    public String getStringValue() {
        return this.toString();
    }

    @Override
    public void setStringValue(String value) {
 
        ArrayList<String> tokenList = this.getListFromString(value);
        for (String token : tokenList){
            StringTokenizer tokenizer = new StringTokenizer(token, " ", false);

            while(tokenizer.hasMoreTokens()){

                String widthToken = tokenizer.nextToken();
                String heightToken = tokenizer.nextToken();
                String componentsToken = tokenizer.nextToken();


                SFImage newImage = new SFImage();

                newImage.setWidth(Integer.parseInt(widthToken));
                newImage.setHeight(Integer.parseInt(heightToken));
                newImage.setComponents(Integer.parseInt(componentsToken));


                ArrayList<Integer> somePixels = new ArrayList<>();

                int i = 0;
                int sfImageDimensions = newImage.getWidth() * newImage.getHeight();

                while (tokenizer.hasMoreTokens() && i < sfImageDimensions){
                    try{
                        
                        String theToken = tokenizer.nextToken();


                        Integer theValue = Integer.decode(theToken);

                        somePixels.add(theValue);
                        i++;
                    }
                    catch (Exception ex){
                        throw new IllegalArgumentException(ex.getMessage());
                    }
                }
                try{
                    newImage.setPixels(somePixels);
                }
                    
                catch (IllegalArgumentException ex){
                    throw ex;
                }
                    
                this.add(newImage);
            }
        }
    }

    @Override
    public String toString() {
        String returnValue = new String();

        int i = 0;
        for (SFImage image : this.getValue()){
            returnValue += image.toString();
            i++;
            if (i < this.getValue().size()){
                returnValue += " ";
            }

        }
        return returnValue;
    }
}
