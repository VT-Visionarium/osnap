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


package edu.vt.arc.vis.osnap.graph.typedMetadata;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlType;


/**
 * @author Peter J. Radics
 * @version 1.0
 * @param <T>
 *            the type of the metadata.
 * 
 */
@XmlType(name = "MetadataBase")
public class MetadataBase<T> {



    @XmlIDREF
    @XmlAttribute(name = "schemaEntry")
    private final SchemaEntry<T> schemaEntry;


    /**
     * The getter for the metadata's schemaEntry.
     * 
     * @return The schemaEntry of the metadata.
     */
    public String getKey() {

        return this.schemaEntry.getKey();
    }

    /**
     * Returns the schema entry.
     * @return the schame entry
     */
    protected SchemaEntry<T> getSchemaEntry() {

        return this.schemaEntry;
    }


    @SuppressWarnings("unused")
    private MetadataBase() {

        this(null, true);
    }

    /**
     * Creates a new instance of the Metadata class.
     * 
     * 
     * @param schemaEntry
     *            The schemaEntry of the new metadata instance.
     */
    protected MetadataBase(final SchemaEntry<T> schemaEntry) {

        this(schemaEntry, false);
    }


    /**
     * @param schemaEntry
     *            the schemaEntry of the metadata
     * @param serialization
     *            flag determining whether or not constructor is used in
     *            serialization.
     */
    protected MetadataBase(final SchemaEntry<T> schemaEntry,
            final boolean serialization) {

        if (schemaEntry == null && !serialization) {
            throw new IllegalArgumentException(
                    "Metadata must be associated with a SchemaEntry!");
        }

        this.schemaEntry = schemaEntry;
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
    public MetadataBase(MetadataBase<T> toCopy) {

        this(toCopy.getSchemaEntry());

    }



    /**
     * Creates a deep copy of the metadata object.
     * 
     * @return A deep copy of the metadata object.
     */
    public MetadataBase<T> deepCopy() {

        return new MetadataBase<>(this);
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

        if (obj != null && obj instanceof MetadataBase<?>) {

            final MetadataBase<?> other = (MetadataBase<?>) obj;

            boolean keysEqual = this.getKey().equals(other.getKey());

            return keysEqual;
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
        return hash;
    }

    /**
     * Provides a string representation of the object.
     * 
     * @return A string representation of the object.
     */
    @Override
    public String toString() {

        return "<" + this.getKey() + "/>";
    }


}
