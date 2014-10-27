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


package edu.vt.arc.vis.osnap.graph.metadata;


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
 * Schemata are collections of <CODE>Metadata</CODE> restrictions.
 * 
 * @author Peter J. Radics
 * @version 0.2
 * 
 * @see edu.vt.arc.vis.osnap.graph.Graph
 * @see edu.vt.arc.vis.osnap.graph.Node
 * @see edu.vt.arc.vis.osnap.graph.Edge
 * @see edu.vt.arc.vis.osnap.graph.HyperEdge
 * @see edu.vt.arc.vis.osnap.graph.metadata.Metadata
 */
@XmlType(name = "Schema")
@XmlAccessorType(XmlAccessType.NONE)
public class Schema {

    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    @XmlAttribute(name = "id")
    private final String                   id;
    private final Map<String, SchemaEntry> schema;


    /**
     * Returns the ID of this Schema.
     * 
     * @return the ID.
     */

    public String getId() {

        return this.id;
    }


    /**
     * Returns the <CODE>SchemaEntries</CODE> of this <CODE>Schema</CODE>.
     * 
     * @return a Collection of <CODE>SchemaEntries</CODE>.
     */
    @XmlElement(name = "Entry")
    public Collection<SchemaEntry> getEntries() {

        return new ArrayList<>(schema.values());
    }

    /**
     * Sets the <CODE>SchemaEntries</CODE> of this <CODE>Schema</CODE>.
     * 
     * @param entries
     *            a Collection of <CODE>SchemaEntries</CODE>.
     */
    public void setEntries(Collection<SchemaEntry> entries) {

        for (SchemaEntry entry : entries) {

            this.addEntry(entry);
        }
    }

    /**
     * Returns the keys of the <CODE>SchemaEntries</CODE>.
     * 
     * @return the keys.
     */
    public Set<String> getKeys() {

        return Collections.unmodifiableSet(schema.keySet());
    }


    /**
     * Creates a new <CODE>Schema</CODE> instance.
     */
    @SuppressWarnings("unused")
    private Schema() {

        this(null, true);
    }

    /**
     * Creates a new <CODE>Schema</CODE> instance with the provided id.
     * 
     * @param id
     *            the id of the new schema.
     */
    public Schema(String id) {

        this(id, false);
    }

    private Schema(String id, boolean serialization) {

        if ((id == null || "".equals(id)) && !serialization) {
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
     *            the <CODE>Schema</CODE> to copy.
     */
    public Schema(Schema toCopy) {

        this(toCopy.getId());

        for (SchemaEntry entry : toCopy.schema.values()) {
            this.schema.put(entry.getKey(), entry.deepCopy());
        }
    }


    /**
     * Returns the <CODE>SchemaEntry</CODE> with the provided key.
     * 
     * @param key
     *            the key.
     * @return the <CODE>SchemaEntry</CODE> or <CODE>null</CODE>, if the
     *         <CODE>Schema</CODE> does not contain the key.
     */
    public SchemaEntry getEntry(String key) {

        return this.schema.get(key);
    }


    /**
     * Creates a new <CODE>SchemaEntry</CODE> with the provided key and type.
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
     * Creates a new <CODE>SchemaEntry</CODE> with the provided key, type,
     * default value, requirement and uniqueness restrictions.
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
     * Adds a deep copy of the <CODE>SchemaEntry</CODE> to the schema.
     * 
     * @param schemaEntry
     *            the <CODE>SchemaEntry</CODE> to add.
     */
    public void addEntry(SchemaEntry schemaEntry) {

        this.schema.put(schemaEntry.getKey(), new SchemaEntry(schemaEntry));

        // TODO: notify observers.
    }

    /**
     * Removes the <CODE>SchemaEntry</CODE> provided from the
     * <CODE>Schema</CODE>.
     * 
     * @param schemaEntry
     *            the entry to be removed.
     */
    public void removeEntry(SchemaEntry schemaEntry) {

        this.schema.remove(schemaEntry.getKey());
    }

    /**
     * Removes the <CODE>SchemaEntry</CODE> identified by the key provided from
     * the <CODE>Schema</CODE>.
     * 
     * @param key
     *            the key of the entry to be removed.
     */
    public void removeEntry(String key) {

        this.schema.remove(key);
    }

    /**
     * Checks whether the provided key is contained in the <CODE>Schema</CODE>.
     * 
     * @param key
     *            the key.
     * @return <CODE>true</CODE>, if the key is contained; <CODE>false</CODE>
     *         otherwise.
     */
    public boolean containsKey(String key) {

        return this.schema.containsKey(key);
    }

    /**
     * Checks whether the provided entry is contained in the <CODE>Schema</CODE>
     * .
     * 
     * @param entry
     *            the entry to check.
     * @return <CODE>true</CODE>, if the entry is contained; <CODE>false</CODE>
     *         otherwise.
     */
    public boolean contains(SchemaEntry entry) {

        return this.schema.containsKey(entry.getKey());
    }


    /**
     * Checks whether the provided entry conflicts with an entry contained in
     * the <CODE>Schema</CODE> .
     * 
     * @param entry
     *            the entry to check.
     * @return <CODE>true</CODE>, if the entry is contained; <CODE>false</CODE>
     *         otherwise.
     */
    public boolean containsConflictingEntry(SchemaEntry entry) {

        if (this.containsKey(entry.getKey())) {
            return !this.schema.get(entry.getKey()).isIdentical(entry);
        }

        return false;
    }

    /**
     * Clears the <CODE>Schema</CODE>.
     */
    public void clear() {

        this.schema.clear();
    }

    /**
     * Creates a deep copy of the <CODE>Schema</CODE> object.
     * 
     * @return A deep copy of the <CODE>Schema</CODE> object.
     */
    public Schema deepCopy() {

        return new Schema(this);
    }


    /**
     * Creates a new <CODE>Metadata</CODE> object consistent with this
     * <CODE>Schema</CODE>. If the key is not present, the <CODE>Schema</CODE>
     * is updated with the given key.
     * 
     * @param key
     *            the key of the <CODE>Metadata</CODE>.
     * @param value
     *            the value of the <CODE>Metadata</CODE>.
     * @return a new <CODE>Metadata</CODE> object.
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
            this.schema.put(key, entry);
        }

        return metadata;
    }


    /**
     * Creates a new <CODE>Metadata</CODE> object with a default value
     * consistent with this <CODE>Schema</CODE>.
     * 
     * @param key
     *            the key of the <CODE>Metadata</CODE>.
     * @return a new <CODE>Metadata</CODE> object o <CODE>null</CODE>, if the
     *         key is not present in the <CODE>Schema</CODE>.
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
     * Provided since equals() only checks the keys of the
     * <CODE>SchemaEntries</CODE> contained but not their values.
     * 
     * @param other
     *            The <CODE>Schema</CODE> to compare this object with.
     * 
     * @return <CODE>true</CODE> if contents are equal. Otherwise
     *         <CODE>false</CODE>.
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


}
