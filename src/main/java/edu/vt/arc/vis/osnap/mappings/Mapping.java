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


package edu.vt.arc.vis.osnap.mappings;


import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Peter J. Radics
 * @version 0.1
 *
 * @param <DOMAIN_KEY_TYPE>
 *            The type of the identifier of the domain property.
 * @param <DOMAIN_VALUE_TYPE>
 *            The type of the domain values.
 * @param <CODOMAIN_VALUE_TYPE>
 *            The type of the co-domain values.
 * @param <CODOMAIN_KEY_TYPE>
 *            The type of the identifier of the co-domain property.
 */
@XmlRootElement(name = "Mapping")
@XmlType(name = "Mapping", propOrder = { "domainKey", "coDomainKey",
        "domainValueType", "coDomainValueType", "valueMappings" })
public class Mapping<DOMAIN_KEY_TYPE, DOMAIN_VALUE_TYPE, CODOMAIN_VALUE_TYPE, CODOMAIN_KEY_TYPE>
        implements
        IMapping<DOMAIN_KEY_TYPE, DOMAIN_VALUE_TYPE, CODOMAIN_VALUE_TYPE, CODOMAIN_KEY_TYPE> {


    @XmlElement(name = "DomainKey")
    private final DOMAIN_KEY_TYPE                                            domainKey;
    @XmlElement(name = "CoDomainKey")
    private final CODOMAIN_KEY_TYPE                                          coDomainKey;

    @XmlElement(name = "DomainValueType")
    private final Class<? extends DOMAIN_VALUE_TYPE>                         domainValueType;
    @XmlElement(name = "CoDomainValueType")
    private final Class<? extends CODOMAIN_VALUE_TYPE>                       coDomainValueType;

    @XmlElementWrapper(name = "ValueMappings")
    @XmlElements({
            @XmlElement(name = "NToOneValueMapping", type = NToOneValueMapping.class),
            @XmlElement(name = "OneToOneValueMapping", type = OneToOneValueMapping.class),
            @XmlElement(name = "LinearIntervalToIntervalValueMapping", type = LinearIntervalToIntervalValueMapping.class) })
    private final Set<IValueMapping<DOMAIN_VALUE_TYPE, CODOMAIN_VALUE_TYPE>> valueMappings;


    /**
     * Creates a new {@link Mapping}. (Serialization Constructor)
     */
    @SuppressWarnings("unused")
    private Mapping() {

        this(null, null, null, null, true);
    }

    /**
     * Creates a new {@link Mapping} between the domain and co-domain
     * represented by the respective keys.
     *
     * @param domainKey
     *            the domain key.
     * @param coDomainKey
     *            the co-domain key.
     * @param domainValueType
     *            the domain value type.
     * @param coDomainValueType
     *            the co-domain value type.
     */
    public Mapping(DOMAIN_KEY_TYPE domainKey, CODOMAIN_KEY_TYPE coDomainKey,
            Class<? extends DOMAIN_VALUE_TYPE> domainValueType,
            Class<? extends CODOMAIN_VALUE_TYPE> coDomainValueType) {

        this(domainKey, coDomainKey, domainValueType, coDomainValueType, false);
    }


    /**
     * Creates a new {@link Mapping} between the domain and co-domain
     * represented by the respective keys.
     *
     * @param domainKey
     *            the domain key.
     * @param coDomainKey
     *            the co-domain key.
     * @param domainValueType
     *            the domain value type.
     * @param coDomainValueType
     *            the co-domain value type.
     * @param serialization
     *            whether or not the constructor is used for serialization.
     */
    protected Mapping(DOMAIN_KEY_TYPE domainKey, CODOMAIN_KEY_TYPE coDomainKey,
            Class<? extends DOMAIN_VALUE_TYPE> domainValueType,
            Class<? extends CODOMAIN_VALUE_TYPE> coDomainValueType,
            boolean serialization) {

        if (domainKey == null && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a mapping without a domain key!");
        }
        if (coDomainKey == null && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a mapping without a co-domain key!");
        }
        this.domainKey = domainKey;
        this.coDomainKey = coDomainKey;

        this.domainValueType = domainValueType;
        this.coDomainValueType = coDomainValueType;

        this.valueMappings = new LinkedHashSet<>();
    }

    @Override
    public DOMAIN_KEY_TYPE getDomainKey() {

        return this.domainKey;
    }

    @Override
    public Class<?> getDomainKeyType() {

        if (this.domainKey != null) {

            return this.domainKey.getClass();
        }
        return null;
    }

    @Override
    public CODOMAIN_KEY_TYPE getCoDomainKey() {

        return this.coDomainKey;
    }

    @Override
    public Class<?> getCoDomainKeyType() {

        if (this.coDomainKey != null) {
            return this.coDomainKey.getClass();
        }
        return null;
    }

    @Override
    public Class<? extends DOMAIN_VALUE_TYPE> getDomainValueType() {

        return this.domainValueType;
    }

    @Override
    public Class<? extends CODOMAIN_VALUE_TYPE> getCoDomainValueType() {

        return this.coDomainValueType;
    }


    @Override
    public Set<IValueMapping<DOMAIN_VALUE_TYPE, CODOMAIN_VALUE_TYPE>> getValueMappings() {

        return Collections.unmodifiableSet(this.valueMappings);
    }

    @Override
    public void addValueMapping(
            IValueMapping<DOMAIN_VALUE_TYPE, CODOMAIN_VALUE_TYPE> valueMapping) {

        this.valueMappings.add(valueMapping);
    }

    @Override
    public void removeValueMapping(
            IValueMapping<DOMAIN_VALUE_TYPE, CODOMAIN_VALUE_TYPE> valueMapping) {

        this.valueMappings.remove(valueMapping);
    }
    
    @Override
    public void clearValueMappings() {
        
        this.valueMappings.clear();
    }

    @Override
    public CODOMAIN_VALUE_TYPE getImageForValue(DOMAIN_VALUE_TYPE value) {

        CODOMAIN_VALUE_TYPE image = null;

        for (IValueMapping<DOMAIN_VALUE_TYPE, CODOMAIN_VALUE_TYPE> valueMapping : this.valueMappings) {

            image = valueMapping.getImageForValue(value);
            if (image != null) {
                break;
            }
        }

        return image;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj != null && obj instanceof Mapping<?, ?, ?, ?>) {

            Mapping<?, ?, ?, ?> other = (Mapping<?, ?, ?, ?>) obj;

            boolean domainKeyEquals = this.getDomainKey().equals(
                    other.getDomainKey());
            boolean coDomainKeyEquals = this.getCoDomainKey().equals(
                    other.getCoDomainKey());

            boolean domainValueTypeEquals = this.getDomainValueType() == other
                    .getDomainValueType();
            boolean coDomainValueTypeEquals = this.getCoDomainValueType() == other
                    .getCoDomainValueType();

            boolean sameNumberOfValueMappings = this.getValueMappings().size() == other
                    .getValueMappings().size();

            if (domainKeyEquals && coDomainKeyEquals && domainValueTypeEquals
                    && coDomainValueTypeEquals && sameNumberOfValueMappings) {

                for (IValueMapping<?, ?> valueMapping : this.getValueMappings()) {

                    boolean match = false;

                    for (IValueMapping<?, ?> otherValueMapping : other
                            .getValueMappings()) {

                        if (valueMapping.equals(otherValueMapping)) {
                            match = true;
                            break;
                        }
                    }
                    if (match == false) {
                        return false;
                    }
                }

                return true;
            }
        }

        return false;
    }

    @Override
    public int hashCode() {

        int hash = 13;

        hash += 17 * this.getDomainKey().hashCode();
        hash += 23 * this.getCoDomainKey().hashCode();

        hash += 29 * this.getDomainValueType().hashCode();
        hash += 31 * this.getCoDomainValueType().hashCode();

        for (IValueMapping<?, ?> valueMapping : this.getValueMappings()) {

            hash += 37 * valueMapping.hashCode();
        }

        return hash;
    }

}
