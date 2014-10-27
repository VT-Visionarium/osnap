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


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import edu.vt.arc.vis.osnap.core.domain.DomainObject;
import edu.vt.arc.vis.osnap.events.domain.metadata.BooleanMetadataDetails;
import edu.vt.arc.vis.osnap.events.domain.metadata.DoubleMetadataDetails;
import edu.vt.arc.vis.osnap.events.domain.metadata.FloatMetadataDetails;
import edu.vt.arc.vis.osnap.events.domain.metadata.IntegerMetadataDetails;
import edu.vt.arc.vis.osnap.events.domain.metadata.LongMetadataDetails;
import edu.vt.arc.vis.osnap.events.domain.metadata.MetadataDetails;
import edu.vt.arc.vis.osnap.events.domain.metadata.StringMetadataDetails;


/**
 * This abstract class represents metadata contained within a graph universe.
 *
 * Metadata are key value pairs, where the keys are strings and the values are
 * defined in the concrete implementations of the class.
 *
 * @author Peter J. Radics
 * @version 0.1
 *
 * @see edu.vt.arc.vis.osnap.core.domain.graph.Graph
 * @see edu.vt.arc.vis.osnap.core.domain.graph.Node
 * @see edu.vt.arc.vis.osnap.core.domain.graph.Edge
 * @see edu.vt.arc.vis.osnap.core.domain.graph.HyperEdge
 * @see edu.vt.arc.vis.osnap.core.domain.graph.metadata.Schema
 */
@XmlType(name = "Metadata")
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({ BooleanMetadata.class, DoubleMetadata.class, FloatMetadata.class,
        IntegerMetadata.class, LongMetadata.class, StringMetadata.class })
public abstract class Metadata
        extends DomainObject {


    @XmlAttribute(name = "key")
    private final String key;


    /**
     * The getter for the metadata'scale key.
     *
     * @return The key of the metadata.
     */
    public String getKey() {

        return this.key;
    }

    /**
     * Getter for the value of the metadata.
     *
     * @return The value of the metadata.
     */
    @XmlElement(name = "value", nillable = true)
    public abstract Object getValue();

    /**
     * Setter for the value of the metdata.
     *
     * @param value
     *            The value to be set.
     */
    public abstract void setValue(Object value);

    @SuppressWarnings("unused")
    private Metadata() {

        this(null, false);
    }

    /**
     * Creates a new instance of the Metadata class.
     *
     *
     * @param key
     *            The key of the new metadata instance.
     */
    public Metadata(String key) {

        this(key, false);
    }

    /**
     * @param key
     *            the key of the metadata
     * @param serialization
     *            flag determining whether or not constructor is used in
     *            serialization.
     */
    protected Metadata(String key, boolean serialization) {

        super(UUID.randomUUID().toString(), serialization);
        // System.err.println("\t\t\tMetadata Constructor");
        if (key == null && !serialization) {

            throw new IllegalArgumentException("Metadata must have a key.");
        }

        this.key = key;
        // System.err.println("\t\t\tDone.");
    }

    /**
     * Copy Constructor of the Metadata class.
     *
     * Provides a copy of only the name of the object into a new object. Value
     * copy is left to the implementing classes.
     *
     * @param toCopy
     *            The metadata to copy.
     */
    public Metadata(Metadata toCopy) {

        this(toCopy.getKey());
    }

    /**
     * Creates a new instance of the {@link Metadata} class from the provided
     * details.
     * 
     * @param details
     *            the details.
     */
    public Metadata(final MetadataDetails details) {

        super(details);

        this.key = details.getKey();

    }

    /**
     * Creates a deep copy of the metadata object.
     *
     * @return A deep copy of the metadata object.
     */
    public abstract Metadata deepCopy();



    /**
     * Returns the <CODE>MetadataType</CODE> of this instance.
     *
     * @return the <CODE>MetadataType</CODE>.
     */
    public abstract MetadataType getMetadataType();



    @Override
    public MetadataDetails toDetails() {

        MetadataDetails details = new MetadataDetails(super.toDetails());

        details.setKey(this.getKey());

        return details;
    }

    /**
     * Compares two objects and determines equality.
     *
     * Equality of metadata objects is determined based of equality of names.
     *
     * @param obj
     *            The object to compare this object to.
     * @return <CODE>true</CODE>, if the other object is not null, an instance
     *         of the Metadata class, and has the same name as this object.
     *         Otherwise <CODE>false</CODE>.
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {

            return true;
        }

        if (obj != null && obj instanceof Metadata) {

            final Metadata other = (Metadata) obj;

            boolean keysEqual = this.getKey().equals(other.getKey());

            boolean sameValue = this.getValue() == other.getValue();

            if (!sameValue && this.getValue() != null) {

                sameValue = this.getValue().equals(other.getValue());
            }

            return keysEqual && sameValue;
        }
        return false;
    }

    /**
     * Provides a hash code for the metadata instance.
     *
     * The hash code is based upon the name only.
     *
     * @return The hash code for this object.
     */
    @Override
    public int hashCode() {

        int hash = 3;
        hash = 31 * hash + this.getKey().hashCode();
        if (this.getValue() != null) {
            hash += 7 * this.getValue().hashCode();
        }
        return hash;
    }

    /**
     * Provides a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {

        if (this.getValue() == null) {
            return "<" + this.key + "/>";
        }
        else {
            return "<" + this.key + ">" + this.getValue().toString() + "</"
                    + this.key + ">";
        }
    }



    /**
     * Creates a new Metadata object of the provided type, key, and value.
     *
     * @param key
     *            the key.
     * @param type
     *            the type.
     * @param value
     *            the value.
     * @return the new Metadata object.
     */
    public static Metadata createMetadata(String key, MetadataType type,
            String value) {

        Metadata metadata = null;

        switch (type) {
            case BOOLEAN:
                metadata = new BooleanMetadata(key, Boolean.parseBoolean(value));
                break;
            case DOUBLE:
                metadata = new DoubleMetadata(key, Double.parseDouble(value));
                break;
            case FLOAT:
                metadata = new FloatMetadata(key, Float.parseFloat(value));
                break;
            case INTEGER:
                metadata = new IntegerMetadata(key, Integer.parseInt(value));
                break;
            case LONG:
                metadata = new LongMetadata(key, Long.parseLong(value));
                break;
            case STRING:
                metadata = new StringMetadata(key, value);
                break;
            default:
                throw new IllegalArgumentException(
                        "Cannot create a Metadata instance with the provided type \""
                                + type + "\"");
        }

        return metadata;
    }

    /**
     * Creates a new Metadata object of the provided type, key, and value.
     *
     * @param key
     *            the key.
     * @param type
     *            the type.
     * @param value
     *            the value.
     * @return the new Metadata object.
     */
    public static Metadata createMetadata(String key, MetadataType type,
            Object value) {

        Metadata metadata = null;

        switch (type) {
            case BOOLEAN:
                metadata = new BooleanMetadata(key, value);
                break;
            case DOUBLE:
                metadata = new DoubleMetadata(key, value);
                break;
            case FLOAT:
                metadata = new FloatMetadata(key, value);
                break;
            case INTEGER:
                metadata = new IntegerMetadata(key, value);
                break;
            case LONG:
                metadata = new LongMetadata(key, value);
                break;
            case STRING:
                metadata = new StringMetadata(key, value);
                break;
            default:
                throw new IllegalArgumentException(
                        "Cannot create a Metadata instance with the provided type \""
                                + type + "\"");
        }

        return metadata;
    }

    /**
     * Creates a new Metadata object of the appropriate concrete type with the
     * provided key and value.
     *
     * @param key
     *            the key.
     * @param value
     *            the value.
     * @return the new Metadata object.
     */
    public static Metadata createMetadata(String key, Object value) {

        Metadata metadata = null;

        if (value instanceof Boolean) {
            metadata = new BooleanMetadata(key, value);
        }
        else if (value instanceof Double) {
            metadata = new DoubleMetadata(key, value);
        }
        else if (value instanceof Float) {
            metadata = new FloatMetadata(key, value);
        }
        else if (value instanceof Integer) {
            metadata = new IntegerMetadata(key, value);
        }
        else if (value instanceof Long) {
            metadata = new LongMetadata(key, value);
        }
        else if (value instanceof String) {
            metadata = new StringMetadata(key, value);
        }
        else {
            throw new UnsupportedOperationException(
                    "Cannot create an Metadata for an object with the "
                            + "provided type \"" + value.getClass() + "\"");
        }

        return metadata;
    }

    /**
     * Returns {@link Metadata} corresponding to the provided details.
     * 
     * @param details
     *            the details.
     * @return {@link Metadata} corresponding to the provided details.
     */
    public static Metadata fromDetails(final MetadataDetails details) {

        if (details instanceof BooleanMetadataDetails) {

            return BooleanMetadata
                    .fromDetails((BooleanMetadataDetails) details);
        }
        else if (details instanceof DoubleMetadataDetails) {

            return DoubleMetadata.fromDetails((DoubleMetadataDetails) details);
        }
        else if (details instanceof FloatMetadataDetails) {

            return FloatMetadata.fromDetails((FloatMetadataDetails) details);
        }
        else if (details instanceof IntegerMetadataDetails) {

            return IntegerMetadata
                    .fromDetails((IntegerMetadataDetails) details);
        }
        else if (details instanceof LongMetadataDetails) {

            return LongMetadata.fromDetails((LongMetadataDetails) details);
        }
        else if (details instanceof StringMetadataDetails) {

            return StringMetadata.fromDetails((StringMetadataDetails) details);
        }

        throw new IllegalArgumentException("Did not recognize type "
                + details.getClass());
    }

    /**
     * Returns a {@link List} of {@link Metadata} corresponding to the provided
     * details.
     * 
     * @param details
     *            the details.
     * @return a {@link List} of {@link Metadata} corresponding to the provided
     *         details.
     */
    public static List<Metadata> fromDetails(final List<MetadataDetails> details) {

        List<Metadata> metadata = new ArrayList<>(details.size());

        for (MetadataDetails data : details) {

            metadata.add(Metadata.fromDetails(data));
        }

        return metadata;
    }
}
