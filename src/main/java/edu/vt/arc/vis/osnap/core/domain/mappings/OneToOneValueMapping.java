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


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The <code>OneToOneValueMapping</code> class implements a mapping between a
 * single domain value to a single co-domain value.
 *
 * @author Peter J. Radics
 * @version 0.1
 * @param <DOMAIN_VALUE_TYPE>
 *            the Type of values within the domain.
 * @param <CODOMAIN_VALUE_TYPE>
 *            the Type of values within the co-domain.
 */
@XmlRootElement(name = "OneToOneValueMapping")
@XmlType(name = "OneToOneValueMapping")
public final class OneToOneValueMapping<DOMAIN_VALUE_TYPE, CODOMAIN_VALUE_TYPE>
        implements IValueMapping<DOMAIN_VALUE_TYPE, CODOMAIN_VALUE_TYPE> {


    @XmlElement(name = "DomainValue")
    private final DOMAIN_VALUE_TYPE   domainValue;
    @XmlElement(name = "CoDomainValue")
    private final CODOMAIN_VALUE_TYPE coDomainValue;


    /**
     * Returns the co-domain value that is being mapped to.
     *
     * @return the co-domain value.
     */
    public CODOMAIN_VALUE_TYPE getCoDomainValue() {

        return this.coDomainValue;
    }

    @Override
    public Class<?> getCoDomainValueType() {

        if (this.coDomainValue != null) {
            return this.coDomainValue.getClass();
        }
        return null;
    }

    /**
     * Returns the domain value that is being mapped.
     *
     * @return the domain value.
     */
    public DOMAIN_VALUE_TYPE getDomainValue() {

        return domainValue;
    }

    @Override
    public Class<?> getDomainValueType() {

        if (domainValue != null) {
            return this.domainValue.getClass();
        }
        return null;
    }

    /**
     * Creates a new {@link OneToOneValueMapping}. (Serialization Constructor)
     */
    @SuppressWarnings("unused")
    private OneToOneValueMapping() {

        this(null, null, true);
    }

    /**
     * Creates a new {@link OneToOneValueMapping} from the provided domain value
     * to the co-domain value.
     *
     * @param domainValue
     *            the domain value to map.
     * @param coDomainValue
     *            the co-domain value to map to.
     */
    public OneToOneValueMapping(DOMAIN_VALUE_TYPE domainValue,
            CODOMAIN_VALUE_TYPE coDomainValue) {

        this(domainValue, coDomainValue, false);
    }

    /**
     * Creates a new {@link OneToOneValueMapping} from the provided domain value
     * to the co-domain value.
     *
     * @param domainValue
     *            the domain value to map.
     * @param coDomainValue
     *            the co-domain value to map to.
     * @param serialization
     *            whether or not the constructor is called during serialization.
     */
    public OneToOneValueMapping(DOMAIN_VALUE_TYPE domainValue,
            CODOMAIN_VALUE_TYPE coDomainValue, boolean serialization) {

        if (domainValue == null && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a mapping without" + "a domainValue!");
        }
        if (coDomainValue == null && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a mapping without" + "a co-domainValue!");
        }

        this.domainValue = domainValue;
        this.coDomainValue = coDomainValue;
    }


    @Override
    public CODOMAIN_VALUE_TYPE getImageForValue(DOMAIN_VALUE_TYPE value) {

        if (value != null && value.equals(domainValue)) {

            return coDomainValue;
        }
        else {

            return null;
        }
    }

    @Override
    public String toString() {

        return this.domainValue.toString() + " -> "
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
        final OneToOneValueMapping<?, ?> other = (OneToOneValueMapping<?, ?>) obj;
        if (this.domainValue.equals(other.getDomainValue())
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
        hash = 97 * hash
                + (this.domainValue != null ? this.domainValue.hashCode() : 0);
        hash = 37
                * hash
                + (this.coDomainValue != null ? this.coDomainValue.hashCode()
                        : 0);
        return hash;
    }

    /**
     * Factory method for the creation of arbitrary one-to-one value mappings
     *
     * @param domainValue
     *            the domain value.
     * @param coDomainValue
     *            the co-domain value.
     * @return the one-to-one mapping.
     */
    public static <T, S> OneToOneValueMapping<T, S> create(T domainValue,
            S coDomainValue) {

        return new OneToOneValueMapping<>(domainValue, coDomainValue);
    }
}
