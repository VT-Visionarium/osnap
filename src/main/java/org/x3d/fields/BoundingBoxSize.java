package org.x3d.fields;


// @formatter:off
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
// @formatter:on


import java.util.StringTokenizer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 *
 * @author Peter J. Radics
 * @version 0.8.0
 * @since 0.1.0
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "BoundingBoxSize")
public class BoundingBoxSize
        extends X3DField {

    private SFFloat firstValue;
    private SFFloat secondValue;
    private SFFloat thirdValue;

    public SFFloat getFirstValue() {

        return this.firstValue;
    }

    public void setFirstValue(final SFFloat firstValue) {

        this.firstValue = firstValue;
    }

    public SFFloat getSecondValue() {

        return this.secondValue;
    }

    public void setSecondValue(final SFFloat secondValue) {

        this.secondValue = secondValue;
    }

    public SFFloat getThirdValue() {

        return this.thirdValue;
    }

    public void setThirdValue(final SFFloat thirdValue) {

        this.thirdValue = thirdValue;
    }

    @XmlValue
    public String getStringValue() {

        return this.toString();
    }

    public void setStringValue(final String value) {

        final StringTokenizer tokenizer = new StringTokenizer(value, " ", false);

        final String firstToken = tokenizer.nextToken();
        final String secondToken = tokenizer.nextToken();
        final String thirdToken = tokenizer.nextToken();

        this.setFirstValue(new SFFloat(Float.parseFloat(firstToken)));
        this.setSecondValue(new SFFloat(Float.parseFloat(secondToken)));
        this.setThirdValue(new SFFloat(Float.parseFloat(thirdToken)));
    }

    public BoundingBoxSize() {

        this.firstValue = new SFFloat();
        this.secondValue = new SFFloat();
        this.thirdValue = new SFFloat();
    }

    public BoundingBoxSize(final SFFloat firstValue, final SFFloat secondValue,
            final SFFloat thirdValue) {

        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.thirdValue = thirdValue;
    }

    public BoundingBoxSize(final Float firstValue, final Float secondValue,
            final Float thirdValue) {

        this.firstValue = new SFFloat(firstValue);
        this.secondValue = new SFFloat(secondValue);
        this.thirdValue = new SFFloat(thirdValue);
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
