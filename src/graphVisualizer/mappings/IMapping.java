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


package graphVisualizer.mappings;


import java.util.Set;


/**
 * The <CODE>IMapping</CODE> interface provides the contract for a mapping
 * between a domain and a co-domain.
 * 
 * The <CODE>IMapping</CODE> maps domain values of a provided domain key to
 * co-domain values of a provided co-domain key.
 * 
 * @param <DOMAIN_KEY_TYPE>
 *            The domain key type. This specifies the key of the domain (e.g.,
 *            whether we have to retrieve domain values from a graph property or
 *            metadata).
 * @param <CODOMAIN_KEY_TYPE>
 *            The co-domain key type. This specifies what property we are
 *            mapping into (e.g., what visual property we are using).
 * @param <DOMAIN_VALUE_TYPE>
 *            The domain value type. This specifies the data type of the values
 *            of the property specified by the domain key.
 * @param <CODOMAIN_VALUE_TYPE>
 *            The co-domain value type. This specifies the data type of the
 *            values of the property specified by the co-domain key.
 * @author Peter J. Radics
 * @version 0.l
 */
public interface IMapping<DOMAIN_KEY_TYPE, DOMAIN_VALUE_TYPE, CODOMAIN_VALUE_TYPE, CODOMAIN_KEY_TYPE> {


    /**
     * Returns the domain key.
     * 
     * In other words, this is the identifier of the property or metadata that
     * we want to map from.
     * 
     * @return the domain key.
     */
    public abstract DOMAIN_KEY_TYPE getDomainKey();


    /**
     * Returns the runtime Type of the domain key.
     * 
     * @return the runtime Type of the domain key.
     */
    public abstract Class<?> getDomainKeyType();


    /**
     * Returns the co-domain key.
     * 
     * In other words, this is the identifier of the property or metadata that
     * we want to map to.
     * 
     * @return the co-domain key.
     */
    public abstract CODOMAIN_KEY_TYPE getCoDomainKey();


    /**
     * Returns the runtime Type of the co-domain key.
     * 
     * @return the runtime Type of the co-domain key.
     */
    public abstract Class<?> getCoDomainKeyType();

    /**
     * Returns a set of value mappings from the domain into the co-domain.
     * 
     * @return a set of value mappings.
     */
    public abstract Set<IValueMapping<DOMAIN_VALUE_TYPE, CODOMAIN_VALUE_TYPE>> getValueMappings();

    /**
     * Adds a value mapping from the domain into the co-domain.
     * 
     * @param valueMapping
     *            a value mapping.
     */
    public abstract void addValueMapping(
            IValueMapping<DOMAIN_VALUE_TYPE, CODOMAIN_VALUE_TYPE> valueMapping);

    /**
     * Removes a value mapping from the domain into the co-domain.
     * 
     * @param valueMapping
     *            a value mapping
     */
    public abstract void removeValueMapping(
            IValueMapping<DOMAIN_VALUE_TYPE, CODOMAIN_VALUE_TYPE> valueMapping);

    /**
     * Removes all value mappings from the domain into the co-domain.
     */
    public abstract void clearValueMappings();

    /**
     * Retrieves the image (the mapped co-domain value) for a value (in the
     * domain).
     * 
     * @param value
     *            the value that needs to be mapped.
     * @return the image of the value.
     */
    public abstract CODOMAIN_VALUE_TYPE getImageForValue(DOMAIN_VALUE_TYPE value);

    /**
     * Returns the runtime type of the domain values.
     * 
     * @return the runtime type of the domain values.
     */
    public abstract Class<? extends DOMAIN_VALUE_TYPE> getDomainValueType();

    /**
     * Returns the runtime type of the co-domain values.
     * 
     * @return the runtime type of the co-domain values.
     */
    public abstract Class<? extends CODOMAIN_VALUE_TYPE> getCoDomainValueType();
}
