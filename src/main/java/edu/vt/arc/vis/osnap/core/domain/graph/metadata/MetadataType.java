package edu.vt.arc.vis.osnap.core.domain.graph.metadata;


// @formatter:off
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

import java.util.Optional;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * The {@code MetadataType} enum enumerates over the different supported types
 * of metadata.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
@XmlEnum
@XmlType(name = "MetadataType")
public enum MetadataType {
    /**
     * Boolean Metadata
     */
    @XmlEnumValue(value = "Boolean")
    BOOLEAN,
    /**
     * Double Metadata
     */
    @XmlEnumValue(value = "Double")
    DOUBLE,
    /**
     * Float Metadata
     */
    @XmlEnumValue(value = "Float")
    FLOAT,
    /**
     * Integer Metadata
     */
    @XmlEnumValue(value = "Integer")
    INTEGER,
    /**
     * Long Metadata
     */
    @XmlEnumValue(value = "Long")
    LONG,
    /**
     * String Metadata
     */
    @XmlEnumValue(value = "String")
    STRING;


    /**
     * Checks the input value and makes sure it is returned as the
     * {@link MetadataType}.
     * 
     * @param inputValue
     *            input value.
     * @param type
     *            the {@link MetadataType}.
     * 
     * @return {@code true}, if the value matches the {@link MetadataType}
     *         provided; {@code false} otherwise.
     */
    public static boolean compareValueType(String inputValue, MetadataType type) {

        return MetadataType.extractValue(inputValue, type).isPresent();
    }

    /**
     * Extracts a value of the provided {@link MetadataType} from the input
     * value.
     * 
     * @param inputValue
     *            input value.
     * @param type
     *            the {@link MetadataType}.
     * 
     * @return the resulting {@link Optional}{@code <} {@link Object} {@code >}.
     */
    public static Optional<Object> extractValue(String inputValue,
            MetadataType type) {

        Object result = null;

        try {

            switch (type) {

                case BOOLEAN:

                    if ("true".equalsIgnoreCase(inputValue)) {

                        result = Boolean.TRUE;
                    }
                    else if ("false".equalsIgnoreCase(inputValue)) {

                        result = Boolean.FALSE;
                    }
                    break;

                case DOUBLE:
                    result = Double.parseDouble(inputValue);
                    break;

                case FLOAT:
                    result = Float.valueOf(inputValue);
                    break;

                case INTEGER:
                    result = Integer.valueOf(inputValue);
                    break;

                case LONG:
                    result = Long.valueOf(inputValue);
                    break;

                case STRING:
                    if (inputValue != null && !inputValue.isEmpty()) {

                        result = inputValue;
                    }
                    break;

                default:
                    break;
            }
        }
        catch (Exception ex) {

            result = null;
        }

        return Optional.ofNullable(result);
    }
}
