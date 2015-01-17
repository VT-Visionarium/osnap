package edu.vt.arc.vis.osnap.core.domain.graph.metadata;


//@formatter:off
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
//@formatter:on


import javax.xml.bind.annotation.XmlType;

import edu.vt.arc.vis.osnap.events.domain.metadata.BooleanMetadataDetails;


/**
 * This class represents metadata with boolean values contained within a graph
 * universe.
 * 
 * This class implements the abstract Metadata class and can be part of
 * Schemata.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 * @see edu.vt.arc.vis.osnap.core.domain.graph.metadata.Metadata
 * @see edu.vt.arc.vis.osnap.core.domain.graph.metadata.Schema
 */
@XmlType(name = "BooleanMetadata")
public class BooleanMetadata
        extends Metadata {

    private Boolean value;

    @Override
    public Boolean getValue() {

        return this.value;
    }


    @Override
    public void setValue(Object value) {

        if (value == null || value instanceof Boolean) {

            this.value = (Boolean) value;
        }
        else {

            String stringValue = value.toString();

            if ("true".equals(stringValue)
                    || "false".equalsIgnoreCase(stringValue)) {

                this.value = Boolean.parseBoolean(stringValue);
            }
            else {
                throw new IllegalArgumentException(
                        "Value has to be of type Boolean.");
            }
        }
    }

    @Override
    public BooleanMetadata deepCopy() {

        return new BooleanMetadata(this);
    }


    @Override
    public final MetadataType getMetadataType() {

        return MetadataType.BOOLEAN;
    }

    @SuppressWarnings("unused")
    private BooleanMetadata() {

        this(null, null, true);
    }

    /**
     * Creates a new instance with the key provided. The value is set to
     * <CODE>null</CODE>.
     * 
     * @param key
     *            the name.
     */
    public BooleanMetadata(String key) {

        this(key, null);
    }


    /**
     * Creates a new instance with the key and value provided.
     * 
     * @param key
     *            the key.
     * @param value
     *            the value.
     */
    public BooleanMetadata(String key, Object value) {

        this(key, value, false);
    }

    private BooleanMetadata(String name, Object value, boolean serialization) {

        super(name, serialization);
        this.setValue(value);
    }

    /**
     * Copy constructor.
     * 
     * @param toCopy
     *            the Metadata to copy.
     */
    public BooleanMetadata(BooleanMetadata toCopy) {

        super(toCopy);
        this.value = toCopy.getValue();
    }

    /**
     * Creates a new instance of the {@link BooleanMetadata} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    public BooleanMetadata(final BooleanMetadataDetails details) {

        super(details);

        this.setValue(details.getValue());
    }

    @Override
    public BooleanMetadataDetails toDetails() {

        BooleanMetadataDetails details = new BooleanMetadataDetails(
                super.toDetails());

        details.setValue(this.getValue());

        return details;
    }

    /**
     * Returns a {@link BooleanMetadata} corresponding to the provided details.
     * 
     * @param details
     *            the details.
     * @return a {@link BooleanMetadata} corresponding to the provided details.
     */
    public static BooleanMetadata fromDetails(
            final BooleanMetadataDetails details) {

        return new BooleanMetadata(details);
    }
}
