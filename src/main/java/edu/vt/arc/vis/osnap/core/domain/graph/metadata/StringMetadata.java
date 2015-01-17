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

import edu.vt.arc.vis.osnap.events.domain.metadata.StringMetadataDetails;


/**
 * This class represents metadata with string values contained within a graph
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
@XmlType(name = "StringMetadata")
public class StringMetadata
        extends Metadata {

    private String value;

    @Override
    public String getValue() {

        return this.value;
    }


    @Override
    public void setValue(Object value) {

        if (value == null || value instanceof String) {
            
            this.value = ((String) value);
        }
        else {
            
            this.value = value.toString();
        }
    }

    @SuppressWarnings("unused")
    private StringMetadata() {

        this(null, null, true);
    }

    /**
     * Creates a new instance with the name provided. The value is set to
     * <CODE>null</CODE>.
     * 
     * @param name
     *            the name.
     */
    public StringMetadata(String name) {

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
    public StringMetadata(String name, Object value) {

        this(name, value, false);
    }

    private StringMetadata(String name, Object value, boolean serialization) {

        super(name, serialization);
        this.setValue(value);
    }

    /**
     * Copy constructor.
     * 
     * @param toCopy
     *            the Metadata to copy.
     */
    public StringMetadata(StringMetadata toCopy) {

        super(toCopy);
        this.value = toCopy.getValue();
    }


    @Override
    public StringMetadata deepCopy() {

        return new StringMetadata(this);
    }


    @Override
    public final MetadataType getMetadataType() {

        return MetadataType.STRING;
    }


    /**
     * Creates a new instance of the {@link StringMetadata} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    public StringMetadata(final StringMetadataDetails details) {

        super(details);

        this.setValue(details.getValue());
    }

    @Override
    public StringMetadataDetails toDetails() {

        StringMetadataDetails details = new StringMetadataDetails(
                super.toDetails());

        details.setValue(this.getValue());

        return details;
    }

    /**
     * Returns a {@link StringMetadata} corresponding to the provided details.
     * 
     * @param details
     *            the details.
     * @return a {@link StringMetadata} corresponding to the provided details.
     */
    public static StringMetadata fromDetails(final StringMetadataDetails details) {

        return new StringMetadata(details);
    }
}
