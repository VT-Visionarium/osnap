package edu.vt.arc.vis.osnap.core.domain.graph.metadata;

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


import javax.xml.bind.annotation.XmlType;

import edu.vt.arc.vis.osnap.events.domain.metadata.FloatMetadataDetails;


/**
 * This class represents metadata with float values contained within a graph
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
@XmlType(name = "FloatMetadata")
public class FloatMetadata
        extends Metadata {

    Float value;


    @Override
    public Float getValue() {

        return this.value;
    }


    @Override
    public void setValue(Object value) {

        if (value == null || value instanceof Float) {
            this.value = (Float) value;
        }
        else {
            throw new IllegalArgumentException("Value has to be of type Float.");
        }
    }

    @SuppressWarnings("unused")
    private FloatMetadata() {

        this(null, null, true);
    }

    /**
     * Creates a new instance with the name provided. The value is set to
     * <CODE>null</CODE>.
     * 
     * @param name
     *            the name.
     */
    public FloatMetadata(String name) {

        this(name, null);
    }

    /**
     * Creates a new instance with the name and value provided.
     * 
     * @param name
     *            the name.
     * @param value
     *            the value.
     */
    public FloatMetadata(String name, Object value) {

        this(name, value, false);
    }

    private FloatMetadata(String name, Object value, boolean serialization) {

        super(name, serialization);
        this.setValue(value);
    }

    /**
     * Copy constructor.
     * 
     * @param toCopy
     *            the Metadata to copy.
     */
    public FloatMetadata(FloatMetadata toCopy) {

        super(toCopy);
        this.value = toCopy.getValue();
    }


    @Override
    public FloatMetadata deepCopy() {

        return new FloatMetadata(this);
    }


    @Override
    public final MetadataType getMetadataType() {

        return MetadataType.FLOAT;
    }



    /**
     * Creates a new instance of the {@link FloatMetadata} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    public FloatMetadata(final FloatMetadataDetails details) {

        super(details);

        this.setValue(details.getValue());
    }

    @Override
    public FloatMetadataDetails toDetails() {

        FloatMetadataDetails details = new FloatMetadataDetails(
                super.toDetails());

        details.setValue(this.getValue());

        return details;
    }

    /**
     * Returns a {@link FloatMetadata} corresponding to the provided details.
     * 
     * @param details
     *            the details.
     * @return a {@link FloatMetadata} corresponding to the provided details.
     */
    public static FloatMetadata fromDetails(final FloatMetadataDetails details) {

        return new FloatMetadata(details);
    }


}
