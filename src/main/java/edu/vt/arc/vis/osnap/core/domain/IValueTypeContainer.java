package edu.vt.arc.vis.osnap.core.domain;


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


/**
 * The {@code IGValueTypeContainer} interface provides the contract for
 * containers of key-value pairs that provide information on their value type.
 * 
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 0.1.0
 */
public interface IValueTypeContainer {


    /**
     * Returns the key (name) of this value container
     * 
     * @return the key (name).
     */
    public abstract String getKey();

    /**
     * Returns the type of the values of this value container.
     * 
     * @return the value type.
     */
    public abstract Class<?> getValueType();
}
