/*******************************************************************************
 * Copyright 2014 Virginia Tech Visionarium
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/


package edu.vt.arc.vis.osnap.core.domain.mappings;


/**
 * The <CODE>IValueMapping</CODE> interface provides the contract for a mapping
 * of values from a domain into a co-domain.
 * 
 * The mapping can map elements of the domain value type onto elements of the
 * co-domain value type.
 * 
 * @param <DOMAIN_VALUE_TYPE>
 *            The domain value type.
 * @param <CODOMAIN_VALUE_TYPE>
 *            The co-domain value type.
 * @author Peter Radics
 * @version 0.2
 */
public interface IValueMapping<DOMAIN_VALUE_TYPE, CODOMAIN_VALUE_TYPE> {

    /**
     * Retrieves the image of the domain value (i.e., the representation of the
     * domain value in the co-domain).
     * 
     * @param value
     *            the domain value.
     * @return the image of the domain value.
     */
    public CODOMAIN_VALUE_TYPE getImageForValue(DOMAIN_VALUE_TYPE value);

    /**
     * Returns the runtime Type of the domain value.
     * 
     * @return the runtime Type of the domain value.
     */
    public Class<?> getDomainValueType();

    /**
     * Returns the runtime Type of the co-domain value.
     * 
     * @return the runtime Type of the co-domain value.
     */
    public Class<?> getCoDomainValueType();
}
