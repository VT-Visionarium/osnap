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
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import edu.vt.arc.vis.osnap.core.domain.DomainObject;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObjectBasedValueTypeContainer;
import edu.vt.arc.vis.osnap.events.domain.metadata.SchemaEntryDetails;


/**
 * The {@link SchemaEntry} class defines an entry of a {@link Schema}. It
 * specifies the characteristics of {@link Metadata} associated with a certain
 * key and of a certain type. Provides default values, requirement and
 * uniqueness restrictions for {@link Metadata} entries in a
 * {@link MetadataMapProperty}.
 * 
 * @author Peter J. Radics
 * @version 1.1.0
 * @since 0.1.0
 * 
 * @see Schema
 * @see Metadata
 * @see MetadataMapProperty
 * 
 */
@XmlType(name = "SchemaEntry")
@XmlAccessorType(XmlAccessType.NONE)
public class SchemaEntry
        extends DomainObject
        implements IGraphObjectBasedValueTypeContainer {


    @XmlAttribute(name = "key", required = true)
    private final String       key;
    @XmlAttribute(name = "type", required = true)
    private final MetadataType type;

    @XmlElement(name = "DefaultValue")
    private Metadata           defaultValue;

    @XmlAttribute(name = "required", required = true)
    private boolean            required;

    @XmlAttribute(name = "unique", required = true)
    private boolean            unique;


    /**
     * Returns the key of the SchemaEntry.
     * 
     * @return the key.
     */
    @Override
    public String getKey() {

        return this.key;
    }

    /**
     * Returns the type of the SchemaEntry.
     * 
     * @return the type.
     */
    public MetadataType getType() {

        return this.type;
    }

    @Override
    public Class<?> getValueType() {

        switch (this.getType()) {
            case BOOLEAN:
                return Boolean.class;
            case DOUBLE:
                return Double.class;
            case FLOAT:
                return Float.class;
            case INTEGER:
                return Integer.class;
            case LONG:
                return Long.class;
            case STRING:
                return String.class;
            default:
                return Object.class;
        }
    }

    /**
     * Returns the default value of the SchemaEntry.
     * 
     * @return the defaultValue.
     */
    public Metadata getDefaultValue() {

        return this.defaultValue;
    }

    /**
     * Sets the default value of the SchemaEntry.
     * 
     * @param defaultValue
     *            the defaultValue to set.
     */
    public void setDefaultValue(Metadata defaultValue) {

        this.defaultValue = defaultValue;
    }


    /**
     * Returns whether or not Metadata for this SchemaEntry is required.
     * 
     * @return {@code true}, if the Metadata for this SchemaEntry needs to be
     *         present in a MetadataMapProperty; {@code false} otherwise.
     */
    public boolean isRequired() {

        return this.required;
    }

    /**
     * Sets whether or not Metadata of this SchemaEntry are required.
     * 
     * @param required
     *            whether or not Metadata are required
     */
    public void setRequired(boolean required) {

        this.required = required;
    }

    /**
     * Returns whether or not Metadata of this SchemaEntry are unique.
     * 
     * @return {@code true} if Metadata of this SchemaEntry are unique;
     *         {@code false} otherwise.
     */
    public boolean isUnique() {

        return this.unique;
    }

    /**
     * Sets whether or not the Metadata of this SchemaEntry are unique.
     * 
     * @param unique
     *            whether or not they are unique.
     */
    public void setUnique(boolean unique) {

        this.unique = unique;
    }

    /**
     * Creates a new instance of the {@link SchemaEntry} class (Serialization
     * Constructor).
     */
    @SuppressWarnings("unused")
    private SchemaEntry() {

        this(null, null, null, false, false, true);
    }

    /**
     * Creates a new {@link SchemaEntry} instance with the provided key and
     * type. The default value is set to {@code null}, the entry is neither
     * required nor unique.
     * 
     * @param key
     *            the key of the Metadata to restrict.
     * @param type
     *            the type of the Metadata to restrict.
     */
    protected SchemaEntry(String key, MetadataType type) {

        this(key, type, null, false, false);

    }

    /**
     * Creates a new {@link SchemaEntry} instance with the provided values.
     * 
     * @param key
     *            the key of the Metadata to restrict.
     * @param type
     *            the type of the Metadata to restrict.
     * @param defaultValue
     *            the default value of the Metadata to restrict.
     * @param required
     *            whether or not the Metadata is required.
     * @param unique
     *            whether or not the Metadata is unique within a container.
     */
    public SchemaEntry(String key, MetadataType type, Metadata defaultValue,
            boolean required, boolean unique) {

        this(key, type, defaultValue, required, unique, false);
    }

    /**
     * Creates a new instance of the {@link SchemaEntry} class.
     * 
     * @param key
     *            the key of the Metadata to restrict.
     * @param type
     *            the type of the Metadata to restrict.
     * @param defaultValue
     *            the default value of the Metadata to restrict.
     * @param required
     *            whether or not the Metadata is required.
     * @param unique
     *            whether or not the Metadata is unique within a container.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    private SchemaEntry(String key, MetadataType type, Metadata defaultValue,
            boolean required, boolean unique, boolean serialization) {

        super(UUID.randomUUID().toString());

        if (key == null && !serialization) {
            throw new IllegalArgumentException(
                    "Cannot create a Schema Entry without a key!");
        }
        if (type == null && !serialization) {

            throw new IllegalArgumentException(
                    "Cannot create a Schema Entry without a metadata type!");
        }

        this.key = key;
        this.type = type;
        this.defaultValue = defaultValue;
        this.required = required;
        this.unique = unique;
    }

    /**
     * Copy Constructor.
     * 
     * @param toCopy
     *            the {@link SchemaEntry} to copy.
     */
    protected SchemaEntry(SchemaEntry toCopy) {

        this(toCopy.getKey(), toCopy.getType(), null, toCopy.isRequired(),
                toCopy.isUnique());

        if (toCopy.getDefaultValue() != null) {

            this.setDefaultValue(toCopy.getDefaultValue().deepCopy());
        }
    }

    /**
     * Creates a new instance of the {@link SchemaEntry} class from the provided
     * details.
     * 
     * @param details
     *            the details.
     */
    public SchemaEntry(final SchemaEntryDetails details) {

        this.key = details.getKey();
        this.type = MetadataType.valueOf(details.getType());
        if (details.getDefaultValue() != null) {

            this.defaultValue = Metadata.fromDetails(details.getDefaultValue());
        }
        this.setRequired(details.isRequired());
        this.setUnique(details.isUnique());

    }

    /**
     * Returns a deep copy of this {@link SchemaEntry}.
     * 
     * @return the deep copy.
     */
    public SchemaEntry deepCopy() {

        return new SchemaEntry(this);
    }


    /**
     * Compares two objects and determines equality.
     * 
     * Equality of {@link SchemaEntry} objects is determined based of equality
     * of names.
     * 
     * @param obj
     *            The object to compare this object to.
     * @return {@code true}, if the other object is not null, an instance of the
     *         {@link SchemaEntry} class, and has the same name as this object.
     *         Otherwise {@code false}.
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {

            return true;
        }

        if (obj != null && obj instanceof SchemaEntry) {

            final SchemaEntry other = (SchemaEntry) obj;

            return this.getKey().equals(other.getKey());
        }
        return false;
    }

    /**
     * Provides a test to check equality based on key and content.
     * 
     * Provided since equals() only checks the key of the {@link SchemaEntry} to
     * maintain position within hashed collections.
     * 
     * @param other
     *            The {@link SchemaEntry} to compare this object with.
     * 
     * @return {@code true} if both keys and contents are equal. Otherwise
     *         {@code false}.
     */
    public boolean isIdentical(SchemaEntry other) {

        if (this.equals(other)) {

            boolean sameType = this.getType().equals(other.getType());
            boolean sameRequirement = this.isRequired() == other.isRequired();
            boolean sameUniqueness = this.isUnique() == other.isUnique();

            boolean sameDefaultValue = (this.getDefaultValue() == other
                    .getDefaultValue());

            if (!sameDefaultValue && this.getDefaultValue() != null) {
                sameDefaultValue = this.getDefaultValue().equals(
                        other.getDefaultValue());
            }

            return sameType && sameRequirement && sameUniqueness
                    && sameDefaultValue;
        }
        return false;
    }

    /**
     * Provides a hash code for the {@link SchemaEntry} instance.
     * 
     * The hash code is based upon the key only.
     * 
     * @return The hash code for this object.
     */
    @Override
    public int hashCode() {

        int hash = 3;
        hash = 31 * hash + this.getKey().hashCode();
        return hash;
    }

    /**
     * Provides a string representation of the object.
     * 
     * @return A string representation of the object.
     */
    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        builder.append("<" + this.key + ">");

        builder.append("type=\"" + type + "\", ");
        builder.append("required=\"" + required + "\", ");
        builder.append("unique=\"" + unique + "\", ");
        builder.append("defaultValue=\"" + defaultValue + "\"");

        builder.append("</" + this.key + ">");

        return builder.toString();
    }


    /**
     * this method added by Shawn to allow getting a the String of teh value of
     * a default value so it can be properly displayed in the schema tables
     * 
     * @return the string representation of the default value'scale value
     */
    public String getDefaultValueString() {

        if (this.defaultValue != null && this.defaultValue.getValue() != null) {
            return this.defaultValue.getValue().toString();
        }
        else {
            return "";
        }
    }

    @Override
    public Object getValueForGraphObject(IGraphObject graphObject) {

        if (graphObject instanceof IMetadataContainer) {

            IMetadataContainer metadataContainer = (IMetadataContainer) graphObject;

            List<Metadata> metadataList = metadataContainer
                    .getMetadataProperty().getMetadata(this.getKey());

            List<Object> valueList = new LinkedList<>();

            if (metadataList != null) {
                for (Metadata metadata : metadataList) {

                    valueList.add(metadata.getValue());
                }
            }

            return valueList;
        }

        return null;
    }

    @Override
    public SchemaEntryDetails toDetails() {

        SchemaEntryDetails details = new SchemaEntryDetails(super.toDetails());

        details.setKey(this.getKey());
        details.setType(this.getType().name());
        details.setRequired(this.isRequired());
        details.setUnique(this.isUnique());
        if (this.getDefaultValue() != null) {

            details.setDefaultValue(this.getDefaultValue().toDetails());
        }

        return details;

    };

    /**
     * Returns a {@link SchemaEntry} corresponding to the provided details.
     * 
     * @param details
     *            the details.
     * @return a {@link SchemaEntry} corresponding to the provided details.
     */
    public static SchemaEntry fromDetails(final SchemaEntryDetails details) {

        return new SchemaEntry(details);
    }

    /**
     * Returns a {@link List} of {@link SchemaEntry SchemaEntries} corresponding
     * to the provided details.
     * 
     * @param details
     *            the details.
     * @return a {@link List} of {@link SchemaEntry SchemaEntries} corresponding
     *         to the provided details.
     */
    public static List<SchemaEntry> fromDetails(
            final List<SchemaEntryDetails> details) {

        List<SchemaEntry> entries = new ArrayList<>(details.size());

        for (SchemaEntryDetails detail : details) {

            entries.add(new SchemaEntry(detail));
        }

        return entries;
    }


}
