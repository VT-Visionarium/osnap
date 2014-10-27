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
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import edu.vt.arc.vis.osnap.core.domain.DomainObject;
import edu.vt.arc.vis.osnap.events.domain.metadata.SchemaDetails;
import edu.vt.arc.vis.osnap.events.domain.metadata.SchemaEntryDetails;


/**
 * This class represents schemata that can be associated with any object in a
 * graph universe.
 * 
 * Schemata are collections of {@link Metadata} restrictions.
 * 
 * @author Peter J. Radics
 * @version 1.1.0
 * @since 0.2.0
 * 
 * @see edu.vt.arc.vis.osnap.core.domain.graph.Graph
 * @see edu.vt.arc.vis.osnap.core.domain.graph.Node
 * @see edu.vt.arc.vis.osnap.core.domain.graph.Edge
 * @see edu.vt.arc.vis.osnap.core.domain.graph.HyperEdge
 * @see edu.vt.arc.vis.osnap.core.domain.graph.metadata.Metadata
 */
@XmlType(name = "Schema")
@XmlAccessorType(XmlAccessType.NONE)
public class Schema
        extends DomainObject {

    private final Map<String, SchemaEntry> schemaEntries;



    /**
     * Returns the {@link SchemaEntry SchemaEntries} of this {@link Schema}.
     * 
     * @return the {@link SchemaEntry SchemaEntries} of this {@link Schema}.
     */
    @XmlElement(name = "Entry")
    public Collection<SchemaEntry> getEntries() {

        return new ArrayList<>(schemaEntries.values());
    }

    /**
     * Sets the {@link SchemaEntry SchemaEntries} of this {@link Schema}.
     * 
     * @param entries
     *            the {@link SchemaEntry SchemaEntries} of this {@link Schema}.
     */
    public void setEntries(Collection<SchemaEntry> entries) {

        for (SchemaEntry entry : entries) {

            this.addEntry(entry);
        }
    }

    /**
     * Returns the keys of the {@link SchemaEntry SchemaEntries}.
     * 
     * @return the keys.
     */
    public Set<String> getKeys() {

        return Collections.unmodifiableSet(schemaEntries.keySet());
    }


    /**
     * Creates a new instance of the {@link Schema} class.
     */
    @SuppressWarnings("unused")
    private Schema() {

        this(null, true);
    }

    /**
     * Creates a new instance of the {@link Schema} class with the provided id.
     * 
     * @param id
     *            the id of the new schemaEntries.
     */
    public Schema(String id) {

        this(id, false);
    }

    private Schema(String id, boolean serialization) {

        super(id, serialization);
        if ((id == null || "".equals(id)) && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a Schema without an ID.");
        }
        this.schemaEntries = new LinkedHashMap<>();
    }

    /**
     * Copy Constructor.
     * 
     * @param toCopy
     *            the {@link Schema} to copy.
     */
    public Schema(Schema toCopy) {

        this(toCopy.getId());

        for (SchemaEntry entry : toCopy.schemaEntries.values()) {

            this.schemaEntries.put(entry.getKey(), entry.deepCopy());
        }
    }

    /**
     * Creates a new instance of the {@link Schema} class from the provided
     * details.
     * 
     * @param details
     *            the details.
     */
    public Schema(final SchemaDetails details) {

        super(details);

        this.schemaEntries = new LinkedHashMap<>();

        if (details.getSchemaEntries() != null
                && !details.getSchemaEntries().isEmpty()) {

            for (SchemaEntryDetails entry : details.getSchemaEntries()) {

                this.addEntry(SchemaEntry.fromDetails(entry));
            }
        }
    }

    /**
     * Returns the {@link SchemaEntry} with the provided key.
     * 
     * @param key
     *            the key.
     * @return the {@link SchemaEntry} or {@code null}, if the {@link Schema}
     *         does not contain the key.
     */
    public SchemaEntry getEntry(String key) {

        return this.schemaEntries.get(key);
    }


    /**
     * Creates a new {@link SchemaEntry} with the provided key and type.
     * 
     * @param key
     *            the key.
     * @param type
     *            the type.
     * @return returns the newly created entry.
     */
    public SchemaEntry createEntry(String key, MetadataType type) {

        return this.createEntry(key, type, null, false, false);
    }

    /**
     * Creates a new {@link SchemaEntry} with the provided key, type, default
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
    public SchemaEntry createEntry(String key, MetadataType type,
            Metadata defaultValue, boolean required, boolean unique) {

        if (this.containsKey(key)) {
            throw new IllegalArgumentException("A SchemaEntry for key \"" + key
                    + "\" already exists!");
        }

        SchemaEntry entry = new SchemaEntry(key, type, defaultValue, required,
                unique);

        this.addEntry(entry);

        return entry;
    }

    /**
     * Adds a deep copy of the {@link SchemaEntry} to the schemaEntries.
     * 
     * @param schemaEntry
     *            the {@link SchemaEntry} to add.
     */
    public void addEntry(SchemaEntry schemaEntry) {

        this.schemaEntries.put(schemaEntry.getKey(), new SchemaEntry(
                schemaEntry));

        // TODO: notify observers.
    }

    /**
     * Removes the {@link SchemaEntry} provided from the {@link Schema}.
     * 
     * @param schemaEntry
     *            the entry to be removed.
     */
    public void removeEntry(SchemaEntry schemaEntry) {

        this.schemaEntries.remove(schemaEntry.getKey());
    }

    /**
     * Removes the {@link SchemaEntry} identified by the key provided from the
     * {@link Schema}.
     * 
     * @param key
     *            the key of the entry to be removed.
     */
    public void removeEntry(String key) {

        this.schemaEntries.remove(key);
    }

    /**
     * Checks whether the provided key is contained in the {@link Schema}.
     * 
     * @param key
     *            the key.
     * @return {@code true}, if the key is contained; {@code false} otherwise.
     */
    public boolean containsKey(String key) {

        return this.schemaEntries.containsKey(key);
    }

    /**
     * Checks whether the provided entry is contained in the {@link Schema} .
     * 
     * @param entry
     *            the entry to check.
     * @return {@code true}, if the entry is contained; {@code false} otherwise.
     */
    public boolean contains(SchemaEntry entry) {

        return this.schemaEntries.containsKey(entry.getKey());
    }


    /**
     * Checks whether the provided entry conflicts with an entry contained in
     * the {@link Schema} .
     * 
     * @param entry
     *            the entry to check.
     * @return {@code true}, if the entry is contained; {@code false} otherwise.
     */
    public boolean containsConflictingEntry(SchemaEntry entry) {

        if (this.containsKey(entry.getKey())) {
            return !this.schemaEntries.get(entry.getKey()).isIdentical(entry);
        }

        return false;
    }

    /**
     * Clears the {@link Schema}.
     */
    public void clear() {

        this.schemaEntries.clear();
    }

    /**
     * Creates a deep copy of the {@link Schema} object.
     * 
     * @return A deep copy of the {@link Schema} object.
     */
    public Schema deepCopy() {

        return new Schema(this);
    }


    /**
     * Creates a new {@link Metadata} object consistent with this {@link Schema}
     * . If the key is not present, the {@link Schema} is updated with the given
     * key.
     * 
     * @param key
     *            the key of the {@link Metadata}.
     * @param value
     *            the value of the {@link Metadata}.
     * @return a new {@link Metadata} object.
     */
    protected Metadata createMetadata(String key, Object value) {

        SchemaEntry entry;
        Metadata metadata;

        if (this.containsKey(key)) {
            entry = this.getEntry(key);
            metadata = Metadata.createMetadata(key, entry.getType(), value);
        }
        else {
            metadata = Metadata.createMetadata(key, value);
            entry = this.createEntry(key, metadata.getMetadataType());
            this.schemaEntries.put(key, entry);
        }

        return metadata;
    }


    /**
     * Creates a new {@link Metadata} object with a default value consistent
     * with this {@link Schema}.
     * 
     * @param key
     *            the key of the {@link Metadata}.
     * @return a new {@link Metadata} object o {@code null}, if the key is not
     *         present in the {@link Schema}.
     */
    protected Metadata createMetadata(String key) {

        if (this.containsKey(key)) {

            SchemaEntry entry = this.getEntry(key);
            return entry.getDefaultValue().deepCopy();
        }
        else {

            throw new IllegalArgumentException("Schema does not contain key \""
                    + key + "\". Cannot create default value for this key.");
        }
    }

    /**
     * Provides a test to check equality based on content.
     * 
     * Provided since equals() only checks the keys of the {@link SchemaEntry
     * SchemaEntries} contained but not their values.
     * 
     * @param other
     *            The {@link Schema} to compare this object with.
     * 
     * @return {@code true} if contents are equal. Otherwise {@code false}.
     */
    public boolean isIdentical(Schema other) {

        if (this.equals(other)) {

            boolean sameID = this.getId().equals(other.getId());

            if (sameID) {
                for (SchemaEntry entry : this.getEntries()) {

                    boolean identical = false;
                    for (SchemaEntry otherEntry : other.getEntries()) {

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
    public boolean equals(Object obj) {

        if (this == obj) {

            return true;
        }

        if (obj != null && obj instanceof Schema) {

            final Schema other = (Schema) obj;

            boolean sameEntries = this.getEntries().equals(other.getEntries());

            return sameEntries;
        }
        return false;
    }

    @Override
    public int hashCode() {

        int hash = 3;
        hash = 37 * hash + this.getEntries().hashCode();
        return hash;
    }

    @Override
    public String toString() {

        StringBuilder returnValue = new StringBuilder();
        returnValue.append("Schema contains " + this.getEntries().size()
                + " entries:\n");

        for (SchemaEntry entry : this.getEntries()) {

            returnValue.append("\t");
            returnValue.append(entry);
            returnValue.append("\n");
        }

        return returnValue.toString();
    }

    @Override
    public SchemaDetails toDetails() {

        SchemaDetails details = new SchemaDetails(super.toDetails());

        if (!this.schemaEntries.isEmpty()) {

            details.setSchemaEntries(new ArrayList<>(this.getEntries().size()));
            for (SchemaEntry entry : this.getEntries()) {

                details.getSchemaEntries().add(entry.toDetails());
            }
        }

        return details;
    }

    /**
     * Returns a {@link Schema} corresponding to the provided details.
     * 
     * @param details
     *            the details.
     * @return a {@link Schema} corresponding to the provided details.
     */
    public static Schema fromDetails(final SchemaDetails details) {

        return new Schema(details);
    }

    /**
     * Returns a {@link List} of {@link Schema Schemas} corresponding to the
     * provided details.
     * 
     * @param details
     *            the details.
     * @return a {@link List} of {@link Schema Schemas} corresponding to the
     *         provided details.
     */
    public static List<Schema> fromDetails(final List<SchemaDetails> details) {

        List<Schema> schemas = new ArrayList<>(details.size());

        for (SchemaDetails schema : details) {

            schemas.add(new Schema(schema));
        }

        return schemas;
    }

}
