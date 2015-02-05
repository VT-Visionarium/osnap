package edu.vt.arc.vis.osnap.core.domain.layout.common;


//@formatter:off
/*
* _
* The Open Semantic Network Analysis Platform (OSNAP)
* _
* Copyright (C) 2012 - 2015 Visionarium at Virginia Tech
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
//@formatter:on


import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;


/**
 * The {@code CoordinateComponent} enum specifies coordinate components.
 * <p>
 * A coordinate layout component can have up to three components. A flag is
 * provided for routings with where no component is used.
 * 
 * @see ICoordinateLayout
 * 
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 0.1.0
 */
@XmlEnum
public enum CoordinateComponent {
    /**
     * Specifies that the {@link ICoordinateLayout} can provide a single
     * component or that the first component is to be used in a routing.
     */
    @XmlEnumValue(value = "FirstComponent")
    FIRST_COMPONENT("First Component"),
    /**
     * Specifies that the {@link ICoordinateLayout} can provide two components
     * or that the second component is to be used in a routing.
     */
    @XmlEnumValue(value = "SecondComponent")
    SECOND_COMPONENT("Second Component"),
    /**
     * Specifies that the {@link ICoordinateLayout} can provide three components
     * or that the third component is to be used in a routing.
     */
    @XmlEnumValue(value = "ThirdComponent")
    THIRD_COMPONENT("Third Component"),
    /**
     * Specifies that no component is to be used in a routing.
     */
    @XmlEnumValue(value = "NoComponent")
    NO_COMPONENT("No Component");

    private final String name;


    /**
     * Creates a new instance of the {@code CoordinateComponent} class.
     */
    private CoordinateComponent(final String name) {

        this.name = name;
    }


    @Override
    public String toString() {
    
        return this.name;
    }
}
