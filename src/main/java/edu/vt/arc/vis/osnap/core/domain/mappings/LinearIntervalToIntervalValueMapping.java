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


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.jutility.common.datatype.util.NumberUtils;
import org.jutility.math.arithmetics.ArithmeticOperations;
import org.jutility.common.datatype.range.Interval;


/**
 * The abstract {@link LinearIntervalToIntervalValueMapping} class provides the
 * base functionality of a linear value map from a numerical domainInterval into
 * another numerical domainInterval.
 *
 * @author Peter J. Radics
 * @version 1.0
 * @param <DOMAIN_VALUE_TYPE>
 *            the Type of the values in the domainInterval.
 * @param <CODOMAIN_VALUE_TYPE>
 *            the Type of the values in the co-domainInterval.
 */
@XmlRootElement(name = "LinearIntervalToIntervalMapping")
@XmlType(name = "LinearIntervalToIntervalMapping")
public class LinearIntervalToIntervalValueMapping<DOMAIN_VALUE_TYPE extends Number, CODOMAIN_VALUE_TYPE extends Number>
        implements IValueMapping<DOMAIN_VALUE_TYPE, CODOMAIN_VALUE_TYPE> {

    @XmlAttribute
    private final Class<? extends DOMAIN_VALUE_TYPE>   domainValueType;
    @XmlAttribute
    private final Class<? extends CODOMAIN_VALUE_TYPE> coDomainValueType;

    @XmlElement(name = "DomainInterval")
    private final Interval<DOMAIN_VALUE_TYPE>          domainInterval;
    @XmlElement(name = "CoDomainInterval")
    private final Interval<CODOMAIN_VALUE_TYPE>        coDomainInterval;


    /**
     * Returns the domainInterval interval.
     *
     * @return the domainInterval interval.
     */
    public Interval<DOMAIN_VALUE_TYPE> getDomainInterval() {

        return domainInterval;
    }

    @Override
    public Class<? extends DOMAIN_VALUE_TYPE> getDomainValueType() {

        return this.domainValueType;
    }

    /**
     * Returns the co-domainInterval interval.
     *
     * @return the co-domainInterval interval.
     */
    public Interval<CODOMAIN_VALUE_TYPE> getCoDomainInterval() {

        return coDomainInterval;
    }


    @Override
    public Class<? extends CODOMAIN_VALUE_TYPE> getCoDomainValueType() {

        return this.coDomainValueType;
    }

    /**
     * Creates a new linear interval to interval value mapping. (Serialization
     * Constructor)
     */
    @SuppressWarnings("unused")
    private LinearIntervalToIntervalValueMapping() {

        this(null, null, null, null, true);
    }

    /**
     * Creates a new linear interval to interval value mapping based on the
     * provided interval boundaries.
     *
     * @param min
     *            the lower boundary of the domainInterval.
     * @param max
     *            the upper boundary of the domainInterval.
     * @param fMin
     *            the lower boundary of the co-domainInterval.
     * @param fMax
     *            the upper boundary of the co-domainInterval.
     * @param domainValueType
     *            the domain value type.
     * @param coDomainValueType
     *            the co-domain value type.
     */
    public LinearIntervalToIntervalValueMapping(Number min, Number max,
            Number fMin, Number fMax,
            Class<? extends DOMAIN_VALUE_TYPE> domainValueType,
            Class<? extends CODOMAIN_VALUE_TYPE> coDomainValueType) {

        this(
                new Interval<>(
                        NumberUtils.cast(min, domainValueType), NumberUtils.cast(max,
                                domainValueType)),
                new Interval<>(NumberUtils.cast(fMin,
                        coDomainValueType), NumberUtils.cast(fMax, coDomainValueType)),
                domainValueType, coDomainValueType);
    }

    /**
     * Creates a new linear interval to interval value mapping based on the
     * provided intervals.
     *
     * @param domainInterval
     *            the domainInterval interval.
     * @param coDomainInterval
     *            the co-domainInterval interval.
     * @param domainValueType
     *            the domain value type.
     * @param coDomainValueType
     *            the co-domain value type.
     */
    public LinearIntervalToIntervalValueMapping(
            Interval<DOMAIN_VALUE_TYPE> domainInterval,
            Interval<CODOMAIN_VALUE_TYPE> coDomainInterval,
            Class<? extends DOMAIN_VALUE_TYPE> domainValueType,
            Class<? extends CODOMAIN_VALUE_TYPE> coDomainValueType) {

        this(domainInterval, coDomainInterval, domainValueType,
                coDomainValueType, false);
    }

    /**
     * Creates a new linear interval to interval value mapping based on the
     * provided intervals.
     *
     * @param domainInterval
     *            the domainInterval interval.
     * @param coDomainInterval
     *            the co-domainInterval interval.
     * @param domainValueType
     *            the domain value type.
     * @param coDomainValueType
     *            the co-domain value type.
     * @param serialization
     *            whether or not the constructor is used during serialization.
     */
    protected LinearIntervalToIntervalValueMapping(
            Interval<DOMAIN_VALUE_TYPE> domainInterval,
            Interval<CODOMAIN_VALUE_TYPE> coDomainInterval,
            Class<? extends DOMAIN_VALUE_TYPE> domainValueType,
            Class<? extends CODOMAIN_VALUE_TYPE> coDomainValueType,
            boolean serialization) {

        if (domainInterval == null && !serialization) {
            throw new IllegalArgumentException("Cannot create mapping without "
                    + "domain interval!");
        }

        if (coDomainInterval == null && !serialization) {
            throw new IllegalArgumentException("Cannot create mapping without "
                    + "co-domain interval!");
        }
        if (domainValueType == null && !serialization) {
            throw new IllegalArgumentException("Cannot create mapping without "
                    + "domain value type!");
        }
        if (coDomainValueType == null && !serialization) {
            throw new IllegalArgumentException("Cannot create mapping without "
                    + "co-domain value type!");
        }

        this.domainInterval = domainInterval;
        this.coDomainInterval = coDomainInterval;

        this.domainValueType = domainValueType;
        this.coDomainValueType = coDomainValueType;
    }


    /**
     * Returns the image of the value if it is contained in the domainInterval.
     *
     * Let T be an Interval with T : [tMin, tMax]. Let S be an Interval with S :
     * [sMin, sMax].
     *
     * * Let f be the linear mapping: f : T -> S
     *
     * Then it holds that f : x |-> (x - tMin) * (sMax - sMin) / (tMax - tMin) +
     * sMin for x element T
     *
     * @param domainValue
     *            The value.
     * @return The image of the value if contained. Otherwise <CODE>null</CODE>.
     */
    @Override
    public final CODOMAIN_VALUE_TYPE getImageForValue(
            DOMAIN_VALUE_TYPE domainValue) {

        if (this.domainInterval.contains(domainValue)) {

            Number scalingFactor = this.calculateScalingFactor();

            // normalizedDomainValue = x - tMin
            Number normalizedDomainValue = ArithmeticOperations.subtract(
                    domainValue, this.domainInterval.getLowerBound());

            // normalizedCoDomainValue = (x - tMin) * (sMax - sMin) / (tMax -
            // tMin)
            Number normalizedCoDomainValue = ArithmeticOperations.multiply(
                    normalizedDomainValue, scalingFactor);

            // coDomainValue = (x - tMin) * (sMax - sMin) / (tMax - tMin) + sMin
            Number coDomainValue = ArithmeticOperations.add(
                    normalizedCoDomainValue,
                    this.coDomainInterval.getLowerBound());

            return NumberUtils.cast(coDomainValue, this.getCoDomainValueType());
        }

        return null;
    }


    @Override
    public boolean equals(Object obj) {

        if (obj != null) {
            if (obj instanceof LinearIntervalToIntervalValueMapping<?, ?>) {
                final LinearIntervalToIntervalValueMapping<?, ?> other = (LinearIntervalToIntervalValueMapping<?, ?>) obj;
                if (this.domainInterval.equals(other.getDomainInterval())
                        && this.coDomainInterval.equals(other
                                .getCoDomainInterval())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public final int hashCode() {

        int hash = 3;
        hash = 97
                * hash
                + (this.domainInterval != null ? this.domainInterval.hashCode()
                        : 0);
        hash = 37
                * hash
                + (this.coDomainInterval != null ? this.coDomainInterval
                        .hashCode() : 0);
        return hash;
    }

    @Override
    public final String toString() {

        Number scalingFactor = this.calculateScalingFactor();
        return "f: " + domainInterval + " -> " + coDomainInterval
                + ", x |-> (x - " + domainInterval.getLowerBound() + ") * "
                + scalingFactor + " + " + coDomainInterval.getLowerBound();
    }

    /**
     * Calculates the scaling factor.
     *
     * Let T be an Interval with T : [tMin, tMax]. Let S be an Interval with S :
     * [sMin, sMax].
     *
     * Let f be the linear mapping: f : T -> S
     *
     * The scaling factor sf is defined as sf = (sMax - sMin) / (tMax - tMin)
     *
     * @return The scaling factor.
     */
    private Number calculateScalingFactor() {

        Number domainSize = ArithmeticOperations.subtract(
                this.domainInterval.getUpperBound(),
                this.domainInterval.getLowerBound());
        // System.out.println("domainSize = tMax - tMin: "
        // + this.domainInterval.getUpperBound() + " - "
        // + this.domainInterval.getLowerBound() + " = " + domainSize);
        // System.out.println("\tdomainSize type: " + domainSize.getClass());

        Number coDomainSize = ArithmeticOperations.subtract(
                this.coDomainInterval.getUpperBound(),
                this.coDomainInterval.getLowerBound());
        // System.out.println("coDomainSize = sMax - sMin: "
        // + this.coDomainInterval.getUpperBound() + " - "
        // + this.coDomainInterval.getLowerBound() + " = " + coDomainSize);
        // System.out.println("\tcoDomainSize type: " +
        // coDomainSize.getClass());

        Number scalingFactor = ArithmeticOperations.divide(coDomainSize,
                domainSize);
        // System.out.println("scalingFactor = coDomainSize / domainSize: "
        // + coDomainSize + " / " + domainSize + " = " + scalingFactor);
        // System.out.println("\tscalingFactor type: " +
        // scalingFactor.getClass());

        return scalingFactor;
    }

    /**
     *
     * @param domainMin
     * @param domainMax
     * @param coDomainMin
     * @param coDomainMax
     * @param domainType
     * @param coDomainType
     * @return something something something...dark side
     */
    public static <S extends Number, T extends Number> LinearIntervalToIntervalValueMapping<S, T> create(
            S domainMin, S domainMax, T coDomainMin, T coDomainMax,
            Class<? extends S> domainType, Class<? extends T> coDomainType) {

        return new LinearIntervalToIntervalValueMapping<>(domainMin, domainMax,
                coDomainMin, coDomainMax, domainType, coDomainType);
    }

    /**
     *
     * @param domainInterval
     * @param coDomainInterval
     * @param domainType
     * @param coDomainType
     * @return something something something...death star
     */
    public static <S extends Number, T extends Number> LinearIntervalToIntervalValueMapping<S, T> create(
            Interval<S> domainInterval, Interval<T> coDomainInterval,
            Class<? extends S> domainType, Class<? extends T> coDomainType) {

        return new LinearIntervalToIntervalValueMapping<>(domainInterval,
                coDomainInterval, domainType, coDomainType);
    }

}
