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

import edu.vt.arc.vis.osnap.events.domain.metadata.LongMetadataDetails;


/**
 * This class represents metadata with long values contained within a graph
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
@XmlType(name = "LongMetadata")
public class LongMetadata
        extends Metadata {

    private Long value;

    @Override
    public Long getValue() {

        return this.value;
    }


    @Override
    public void setValue(Object value) {

        if (value == null || value instanceof Long) {
            this.value = (Long) value;
        }
        else {

            try {

                this.value = Long.parseLong(value.toString());
            }
            catch (NumberFormatException e) {

                throw new IllegalArgumentException(
                        "Value has to be of type Long.", e);
            }
        }
    }

    @SuppressWarnings("unused")
    private LongMetadata() {

        this(null, null, true);
    }

    /**
     * Creates a new instance with the name provided. The value is set to
     * <CODE>null</CODE>.
     * 
     * @param name
     *            the name.
     */
    public LongMetadata(String name) {

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
    public LongMetadata(String name, Object value) {

        this(name, value, false);
    }

    private LongMetadata(String name, Object value, boolean serialization) {

        super(name, serialization);
        this.setValue(value);
    }

    /**
     * Copy constructor.
     * 
     * @param toCopy
     *            the Metadata to copy.
     */
    public LongMetadata(LongMetadata toCopy) {

        super(toCopy);
        this.value = toCopy.getValue();
    }


    @Override
    public LongMetadata deepCopy() {

        return new LongMetadata(this);
    }


    @Override
    public final MetadataType getMetadataType() {

        return MetadataType.LONG;
    }


    /**
     * Creates a new instance of the {@link LongMetadata} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    public LongMetadata(final LongMetadataDetails details) {

        super(details);

        this.setValue(details.getValue());
    }

    @Override
    public LongMetadataDetails toDetails() {

        LongMetadataDetails details = new LongMetadataDetails(super.toDetails());

        details.setValue(this.getValue());

        return details;
    }

    /**
     * Returns a {@link LongMetadata} corresponding to the provided details.
     * 
     * @param details
     *            the details.
     * @return a {@link LongMetadata} corresponding to the provided details.
     */
    public static LongMetadata fromDetails(final LongMetadataDetails details) {

        return new LongMetadata(details);
    }

}