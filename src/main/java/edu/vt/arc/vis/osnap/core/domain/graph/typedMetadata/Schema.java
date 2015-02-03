package edu.vt.arc.vis.osnap.core.domain.graph.typedMetadata;


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
// @formatter:on

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * This class represents schemata that can be associated with any object in a
 * graph universe.
 *
 * Schemata are collections of {@code Metadata} restrictions.
 *
 * @author Peter J. Radics
 * @version 0.2
 *
 * @see edu.vt.arc.vis.osnap.core.domain.graph.Graph
 * @see edu.vt.arc.vis.osnap.core.domain.graph.Node
 * @see edu.vt.arc.vis.osnap.core.domain.graph.Edge
 * @see edu.vt.arc.vis.osnap.core.domain.graph.HyperEdge
 * @see edu.vt.arc.vis.osnap.core.domain.graph.metadata.Metadata
 */
@XmlType(name = "Schema")
@XmlAccessorType(XmlAccessType.NONE)
public class Schema {

    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    @XmlAttribute(name = "id")
    private final String                      id;
    private final Map<String, SchemaEntry<?>> schema;


    /**
     * Returns the ID of this Schema.
     *
     * @return the ID.
     */

    public String getId() {

        return this.id;
    }


    /**
     * Returns the {@code SchemaEntries} of this {@code Schema}.
     *
     * @return a Collection of {@code SchemaEntries}.
     */
    @XmlElement(name = "Entry")
    public Collection<SchemaEntry<?>> getEntries() {

        return new ArrayList<>(this.schema.values());
    }

    /**
     * Sets the {@code SchemaEntries} of this {@code Schema}.
     *
     * @param entries
     *            a Collection of {@code SchemaEntries}.
     */
    public void setEntries(final Collection<SchemaEntry<?>> entries) {

        for (final SchemaEntry<?> entry : entries) {

            this.addEntry(entry);
        }
    }

    /**
     * Returns the keys of the {@code SchemaEntries}.
     *
     * @return the keys.
     */
    public Set<String> getKeys() {

        return Collections.unmodifiableSet(this.schema.keySet());
    }


    /**
     * Creates a new {@code Schema} instance.
     */
    @SuppressWarnings("unused")
    private Schema() {

        this(null, true);
    }

    /**
     * Creates a new {@code Schema} instance with the provided id.
     *
     * @param id
     *            the id of the new schema.
     */
    public Schema(final String id) {

        this(id, false);
    }

    private Schema(final String id, final boolean serialization) {

        if (((id == null) || "".equals(id)) && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a Schema without an ID.");
        }
        this.id = id;
        this.schema = new LinkedHashMap<>();
    }

    /**
     * Copy Constructor.
     *
     * @param toCopy
     *            the {@code Schema} to copy.
     */
    public Schema(final Schema toCopy) {

        this(toCopy.getId());

        for (final SchemaEntry<?> entry : toCopy.schema.values()) {
            this.schema.put(entry.getKey(), entry.deepCopy());
        }
    }


    /**
     * Returns the {@code SchemaEntry} with the provided key.
     *
     * @param key
     *            the key.
     * @return the {@code SchemaEntry} or {@code null}, if the {@code Schema}
     *         does not contain the key.
     */
    public SchemaEntry<?> getEntry(final String key) {

        return this.schema.get(key);
    }


    /**
     * Creates a new {@code SchemaEntry} with the provided key and type.
     *
     * @param key
     *            the key.
     * @param type
     *            the type.
     * @return returns the newly created entry.
     */
    public <T> SchemaEntry<T> createEntry(final String key, final Class<T> type) {

        return this.createEntry(key, type, null, false, false);
    }

    /**
     * Creates a new {@code SchemaEntry} with the provided key, type, default
     * value, requirement and uniqueness restrictions.
     *
     * @param key
     *            the key.
     * @param type
     *            the type.
     * @param defaultValue
     *            the default value.
     * @param required
     *            the requirement restriction.
     * @param unique
     *            the uniqueness restriction.
     * @return returns the newly created entry.
     */
    public <T> SchemaEntry<T> createEntry(final String key,
            final Class<T> type, final T defaultValue, final boolean required,
            final boolean unique) {

        if (this.containsKey(key)) {
            throw new IllegalArgumentException("A SchemaEntry for key \"" + key
                    + "\" already exists!");
        }

        final SchemaEntry<T> entry = new SchemaEntry<>(key, type, defaultValue,
                required, unique);

        this.addEntry(entry);

        return entry;
    }

    /**
     * Adds a deep copy of the {@code SchemaEntry} to the schema.
     *
     * @param schemaEntry
     *            the {@code SchemaEntry} to add.
     */
    public void addEntry(final SchemaEntry<?> schemaEntry) {

        this.schema.put(schemaEntry.getKey(), new SchemaEntry<>(schemaEntry));
    }

    /**
     * Removes the {@code SchemaEntry} provided from the {@code Schema}.
     *
     * @param schemaEntry
     *            the entry to be removed.
     */
    public void removeEntry(final SchemaEntry<?> schemaEntry) {

        this.schema.remove(schemaEntry.getKey());
    }

    /**
     * Removes the {@code SchemaEntry} identified by the key provided from the
     * {@code Schema}.
     *
     * @param key
     *            the key of the entry to be removed.
     */
    public void removeEntry(final String key) {

        this.schema.remove(key);
    }

    /**
     * Checks whether the provided key is contained in the {@code Schema}.
     *
     * @param key
     *            the key.
     * @return {@code true}, if the key is contained; {@code false} otherwise.
     */
    public boolean containsKey(final String key) {

        return this.schema.containsKey(key);
    }

    /**
     * Checks whether the provided entry is contained in the {@code Schema} .
     *
     * @param entry
     *            the entry to check.
     * @return {@code true}, if the entry is contained; {@code false} otherwise.
     */
    public boolean contains(final SchemaEntry<?> entry) {

        return this.schema.containsKey(entry.getKey());
    }


    /**
     * Checks whether the provided entry conflicts with an entry contained in
     * the {@code Schema} .
     *
     * @param entry
     *            the entry to check.
     * @return {@code true}, if the entry is contained; {@code false} otherwise.
     */
    public boolean containsConflictingEntry(final SchemaEntry<?> entry) {

        if (this.containsKey(entry.getKey())) {
            return !this.schema.get(entry.getKey()).isIdentical(entry);
        }

        return false;
    }

    /**
     * Clears the {@code Schema}.
     */
    public void clear() {

        this.schema.clear();
    }

    /**
     * Creates a deep copy of the {@code Schema} object.
     *
     * @return A deep copy of the {@code Schema} object.
     */
    public Schema deepCopy() {

        return new Schema(this);
    }


    /**
     * Creates a new {@code Metadata} object consistent with this {@code Schema}
     * . If the key is not present, the {@code Schema} is updated with the given
     * key.
     *
     * @param key
     *            the key of the {@code Metadata}.
     * @param value
     *            the value of the {@code Metadata}.
     * @return a new {@code Metadata} object.
     */
    protected <T> TypedMetadata<?> createMetadata(final String key,
            final T value) {

        SchemaEntry<?> entry;

        if (this.containsKey(key)) {
            entry = this.getEntry(key);
            if (!entry.getValueType().isAssignableFrom(value.getClass())) {
                throw new IllegalArgumentException(
                        "Cannot create Metadata with type " + value.getClass()
                                + "! Schema Entry has conflicting type "
                                + entry.getValueType());
            }
        }
        else {
            entry = this.createEntry(key, value.getClass());
            this.schema.put(key, entry);
        }

        final TypedMetadata<?> metadata = this.createMetadata(entry, value);

        return metadata;
    }

    /**
     * Creates a new {@code Metadata} object with a default value consistent
     * with this {@code Schema}.
     *
     * @param key
     *            the key of the {@code Metadata}.
     * @return a new {@code Metadata} object o {@code null}, if the key is not
     *         present in the {@code Schema}.
     */
    protected TypedMetadata<?> createMetadata(final String key) {

        if (this.containsKey(key)) {

            return this.createMetadata(this.getEntry(key), null);
        }
        else {

            throw new IllegalArgumentException("Schema does not contain key \""
                    + key + "\". Cannot create default value for this key.");
        }
    }

    private <S, T> TypedMetadata<S> createMetadata(final SchemaEntry<S> entry,
            final T value) {

        if ((value == null)
                || entry.getValueType().isAssignableFrom(value.getClass())) {

            return new TypedMetadata<>(entry.getKey(), entry.getValueType()
                    .cast(value));
        }

        return null;
    }

    /**
     * Provides a test to check equality based on content.
     *
     * Provided since equals() only checks the keys of the {@link SchemaEntry
     * SchemaEntries} contained but not their values.
     *
     * @param other
     *            The {@code Schema} to compare this object with.
     *
     * @return {@code true} if contents are equal. Otherwise {@code false}.
     */
    public boolean isIdentical(final Schema other) {

        if (this.equals(other)) {

            final boolean sameID = this.getId().equals(other.getId());

            if (sameID) {
                for (final SchemaEntry<?> entry : this.getEntries()) {

                    boolean identical = false;
                    for (final SchemaEntry<?> otherEntry : other.getEntries()) {

                        identical = entry.isIdentical(otherEntry);
                        if (identical) {
                            break;
                        }
                    }
                    if (!identical) {
                        return false;
                    }

                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {

            return true;
        }

        if ((obj != null) && (obj instanceof Schema)) {

            final Schema other = (Schema) obj;

            final boolean sameEntries = this.getEntries().equals(
                    other.getEntries());

            return sameEntries;
        }
        return false;
    }

    @Override
    public int hashCode() {

        int hash = 3;
        hash = (37 * hash) + this.getEntries().hashCode();
        return hash;
    }

    @Override
    public String toString() {

        final StringBuilder returnValue = new StringBuilder();
        returnValue.append("Schema contains " + this.getEntries().size()
                + " entries:\n");

        for (final SchemaEntry<?> entry : this.getEntries()) {

            returnValue.append("\t");
            returnValue.append(entry);
            returnValue.append("\n");
        }

        return returnValue.toString();
    }


}
