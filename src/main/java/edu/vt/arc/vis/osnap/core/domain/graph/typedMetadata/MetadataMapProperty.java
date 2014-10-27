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


package edu.vt.arc.vis.osnap.core.domain.graph.typedMetadata;

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


import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * The <CODE>MetadataMapProperty</CODE> class provides a container for
 * <CODE>Metadata</CODE> objects paired with consistency-checking.
 *
 * @author Peter J. Radics
 * @version 0.1
 *
 * @see TypedMetadata
 * @see Schema
 *
 */
@XmlType(name = "MetadataMap", propOrder = { "schema", "metadata" })
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({ Schema.class })
public class MetadataMapProperty {

    @XmlIDREF
    @XmlAttribute
    @XmlSchemaType(name = "IDREF")
    private final Schema                              schema;

    private final Map<String, List<TypedMetadata<?>>> metadata;


    /**
     * Returns the <CODE>Schema</CODE> adhered to.
     *
     * @return the schema.
     */
    public Schema getSchema() {

        return this.schema;
    }


    @SuppressWarnings("unused")
    private MetadataMapProperty() {

        this(null, true);
    }

    /**
     * Creates a new instance of the <CODE>MetadataMapProperty</CODE> class
     * adhering to the provided <CODE>Schema</CODE>.
     *
     * @param schema
     *            the <CODE>Schema</CODE> to adhere to.
     */
    public MetadataMapProperty(Schema schema) {

        this(schema, false);
    }


    private MetadataMapProperty(Schema schema, boolean serialization) {

        // System.err.println("\tMetadata Map Property Constructor");
        if (schema == null && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a MetadataMap without a Schema!");
        }

        if (schema == null) {
            this.schema = new Schema("Schema");
        }
        else {
            this.schema = schema;
        }
        this.metadata = new LinkedHashMap<>();
        // System.err.println("\tDone.");
    }


    /**
     * Copy Constructor.
     *
     * @param toCopy
     *            the <CODE>MetadataMapProperty</CODE> to copy.
     */
    public MetadataMapProperty(MetadataMapProperty toCopy) {

        this(toCopy.getSchema());
        if (toCopy != null) {

            for (String key : toCopy.getKeys()) {

                List<TypedMetadata<?>> list = toCopy.getMetadata(key);

                for (TypedMetadata<?> metadata : list) {
                    this.addMetadata(metadata);
                }
            }
        }
    }

    /**
     * Returns the keys of the <CODE>Metadata</CODE> stored.
     *
     * @return the keys.
     */
    public Set<String> getKeys() {

        return Collections.unmodifiableSet(metadata.keySet());
    }


    /**
     * Returns all <CODE>Metadata</CODE> stored in this container.
     *
     * @return the metadata.
     */
    @XmlElement(type = TypedMetadata.class, nillable = true)
    public List<TypedMetadata<?>> getMetadata() {

        List<TypedMetadata<?>> elements = new LinkedList<>();

        for (List<TypedMetadata<?>> list : this.metadata.values()) {
            elements.addAll(list);
        }

        return elements;
    }

    @SuppressWarnings("unused")
    private void setMetadata(List<TypedMetadata<?>> metadata) {

        for (TypedMetadata<?> metadataInstance : metadata) {
            this.addMetadata(metadataInstance);
        }
    }


    /**
     * Returns the <CODE>Metadata</CODE> associated with a certain key.
     *
     * @param key
     *            the key.
     * @return a list of <CODE>Metadata</CODE> or <CODE>null</CODE>, if the key
     *         is not contained in this container.
     */
    public List<TypedMetadata<?>> getMetadata(String key) {

        return this.metadata.get(key);
    }


    /**
     * Adds the metadata provided to the container. If a uniqueness constraint
     * is specified in the Schema, present values of the given key are
     * overwritten.
     *
     * @param metadata
     *            the metadata to add.
     */
    public void addMetadata(TypedMetadata<?> metadata) {

        String key = metadata.getKey();
        SchemaEntry<?> entry = null;
        if (this.schema == null) {
            System.err.println("Hijo de puta");
        }
        if (this.schema.containsKey(key)) {
            entry = this.schema.getEntry(key);
            // System.out.println("Adding metadata with existing schema entry.");
        }
        else {
            entry = this.schema.createEntry(key, metadata.getValueType());
            // System.out.println("Adding metadata without existing schema entry.");
        }


        if (entry.isUnique()) {
            if (this.metadata.containsKey(key)
                    && !this.metadata.get(key).isEmpty()) {
                throw new IllegalArgumentException(
                        "Adding the metadata with key \"" + key
                                + "\" violates the uniqueness "
                                + "constraint of the Schema!");
            }
        }

        List<TypedMetadata<?>> list;

        // A list of Metadata for this key is already present.
        if (this.metadata.containsKey(key)) {

            list = this.metadata.get(key);

            // enforcing the uniqueness constraint.
            if (entry.isUnique()) {
                list.clear();
            }
        }
        // No list of Metadata is available for this key. Creating a new one.
        else {
            list = new LinkedList<>();
            this.metadata.put(key, list);
        }

        list.add(metadata.deepCopy());

        // TODO: notify observers
    }


    /**
     * Creates a new Metadata object and adds it to the container.
     *
     * @param key
     *            the key of the new metadata.
     * @param value
     *            the value of the new metadata.
     */
    public void addMetadata(String key, Object value) {

        // System.out.println("Adding metadata key-value pair \"" + key + "\":,"
        // + value );
        TypedMetadata<?> metadata = this.schema.createMetadata(key, value);

        this.addMetadata(metadata);
    }


    /**
     * Removes the provided metadata element from the container if content equal
     * to an existing element in the container.
     *
     * @param element
     *            the element to remove.
     */
    public void removeMetadata(TypedMetadata<?> element) {

        String key = element.getKey();

        if (this.containsKey(key)) {
            List<TypedMetadata<?>> list = this.getMetadata(key);
            if (!list.isEmpty()) {
                Iterator<TypedMetadata<?>> it = list.iterator();
                while (it.hasNext()) {
                    if (element.equals(it.next())) {
                        it.remove();
                        break;
                    }
                }
            }
        }
    }


    /**
     * Removes all metadata for the provided key.
     *
     * @param key
     *            the key.
     */
    public void removeMetadata(String key) {

        this.metadata.remove(key);
    }

    /**
     * Returns whether or not an entry exists for the given key.
     *
     * @param key
     *            the key.
     * @return <CODE>true</CODE>, if an entry exists for the key;
     *         <CODE>false</CODE> otherwise.
     */
    public boolean containsKey(String key) {

        return this.metadata.containsKey(key);
    }

    /**
     * Returns whether or not the provided entry exists in the container.
     *
     * @param metadata
     *            the metadata.
     * @return <CODE>true</CODE>, if the entry exists in the container;
     *         <CODE>false</CODE> otherwise.
     */
    public boolean contains(TypedMetadata<?> metadata) {

        if (this.containsKey(metadata.getKey())) {
            for (TypedMetadata<?> metadataToCompare : this.getMetadata(metadata
                    .getKey())) {
                if (metadata.equals(metadataToCompare)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Clears the contents of this container.
     */
    public void clear() {

        this.metadata.clear();
    }


    /**
     * Removes empty entries from this container.
     */
    public void prune() {

        Iterator<Entry<String, List<TypedMetadata<?>>>> it = metadata
                .entrySet().iterator();

        while (it.hasNext()) {
            Entry<String, List<TypedMetadata<?>>> entry = it.next();
            if (entry.getValue() == null || entry.getValue().isEmpty()
                    || entry.getKey() == null) {
                it.remove();
            }
        }
    }

    /**
     * Returns a deep copy of this <CODE>MetadataMapProperty</CODE>.
     *
     * @return the deep copy.
     */
    public MetadataMapProperty deepCopy() {

        return new MetadataMapProperty(this);
    }


    /**
     * Returns whether or not this MetadataMapProperty is identical to the
     * provided one.
     *
     * @param other
     *            the provided MetadataMapProperty.
     * @return <code>true</code>, if they are identical; <code>false</code>
     *         otherwise.
     */
    public boolean isIdentical(MetadataMapProperty other) {

        boolean schemaIdentical = this.getSchema().isIdentical(
                other.getSchema());
        boolean metadataEqual = this.getMetadata().equals(other.getMetadata());

        return schemaIdentical && metadataEqual;
    }


    @Override
    public boolean equals(Object obj) {

        System.err.println("Metadata Map Property equals");
        if (obj != null && obj instanceof MetadataMapProperty) {

            final MetadataMapProperty other = (MetadataMapProperty) obj;

            return this.getSchema().equals(other.getSchema())
                    && this.getMetadata().equals(other.getMetadata());

        }
        return false;
    }


    @Override
    public int hashCode() {

        int hash = 3;
        hash = 37 + hash * this.schema.hashCode() + 7
                * this.getMetadata().hashCode();
        return hash;
    }


    @Override
    public String toString() {


        StringBuilder returnValue = new StringBuilder();
        returnValue.append("MetadataContainer contains "
                + this.getKeys().size() + " keys:\n");

        for (String key : this.getKeys()) {

            returnValue.append("\t");
            returnValue.append(key);
            returnValue.append(":\n");

            for (TypedMetadata<?> metadata : this.getMetadata(key)) {
                returnValue.append("\t\t");
                returnValue.append(metadata);
                returnValue.append("\n");
            }
        }

        return returnValue.toString();
    }

}
