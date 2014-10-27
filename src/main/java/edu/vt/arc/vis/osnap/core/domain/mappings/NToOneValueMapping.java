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


import java.util.Collections;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The <code>NToOneValueMapping</code> class implements a mapping between a set
 * of domainValues values to a single co-domainValues value.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * @param <DOMAIN_VALUE_TYPE>
 *            the Type of values within the domainValues.
 * @param <CODOMAIN_VALUE_TYPE>
 *            the Type of values within the co-domainValues.
 */
@XmlRootElement(name = "NToOneValueMapping")
@XmlType(name = "NToOneValueMapping")
public final class NToOneValueMapping<DOMAIN_VALUE_TYPE, CODOMAIN_VALUE_TYPE>
        implements IValueMapping<DOMAIN_VALUE_TYPE, CODOMAIN_VALUE_TYPE> {

    @XmlElementWrapper(name = "DomainValues")
    @XmlElement(name = "DomainValue")
    private final Set<DOMAIN_VALUE_TYPE> domainValues;
    @XmlElement(name = "CoDomainValue")
    private final CODOMAIN_VALUE_TYPE    coDomainValue;


    /**
     * Returns the domainValues values.
     * 
     * @return a set of domainValues values.
     */
    public Set<DOMAIN_VALUE_TYPE> getDomainValues() {

        return Collections.unmodifiableSet(domainValues);
    }

    @Override
    public Class<?> getDomainValueType() {

        if (domainValues != null && !domainValues.isEmpty()) {
            return this.domainValues.iterator().next().getClass();
        }
        return null;
    }

    /**
     * Returns the co-domainValues value.
     * 
     * @return the co-domainValues value.
     */
    public CODOMAIN_VALUE_TYPE getCoDomainValue() {

        return coDomainValue;
    }

    @Override
    public Class<?> getCoDomainValueType() {

        if (this.coDomainValue != null) {
            return this.coDomainValue.getClass();
        }

        return null;
    }

    /**
     * Creates a new {@link NToOneValueMapping}. (Serialization Constructor)
     */
    @SuppressWarnings("unused")
    private NToOneValueMapping() {

        this(null, null, true);
    }

    /**
     * Creates a new {@link NToOneValueMapping} from the provided set of
     * domainValues values to the provided co-domainValues value.
     * 
     * @param domainValues
     *            the domainValues values.
     * @param coDomainValue
     *            the co-domainValues value.
     */
    public NToOneValueMapping(Set<DOMAIN_VALUE_TYPE> domainValues,
            CODOMAIN_VALUE_TYPE coDomainValue) {

        this(domainValues, coDomainValue, false);
    }

    /**
     * Creates a new {@link NToOneValueMapping} from the provided set of
     * domainValues values to the provided co-domainValues value.
     * 
     * @param domainValues
     *            the domainValues values.
     * @param coDomainValue
     *            the co-domainValues value.
     * @param serialization
     *            whether or not the constructor is called during serialization.
     */
    private NToOneValueMapping(Set<DOMAIN_VALUE_TYPE> domainValues,
            CODOMAIN_VALUE_TYPE coDomainValue, boolean serialization) {

        if ((domainValues == null || domainValues.isEmpty()) && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a mapping without domain values!");
        }

        if (coDomainValue == null && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a mapping without a co-domain value!");
        }

        this.coDomainValue = coDomainValue;
        this.domainValues = domainValues;

    }


    @Override
    public CODOMAIN_VALUE_TYPE getImageForValue(DOMAIN_VALUE_TYPE value) {

        if (domainValues.contains(value)) {
            return this.coDomainValue;
        }
        else {
            return null;
        }
    }

    @Override
    public String toString() {

        return this.domainValues.toString() + " -> "
                + this.coDomainValue.toString();
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NToOneValueMapping<?, ?> other = (NToOneValueMapping<?, ?>) obj;
        if (this.domainValues.equals(other.getDomainValues())
                && this.coDomainValue.equals(other.getCoDomainValue())) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public final int hashCode() {

        int hash = 3;
        hash = 97
                * hash
                + (this.domainValues != null ? this.domainValues.hashCode() : 0);
        hash = 37
                * hash
                + (this.coDomainValue != null ? this.coDomainValue.hashCode()
                        : 0);
        return hash;
    }

}
