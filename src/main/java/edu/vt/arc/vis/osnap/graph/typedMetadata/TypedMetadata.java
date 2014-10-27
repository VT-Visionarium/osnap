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



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * This abstract class represents metadata contained within a graph universe.
 *
 * Metadata are key value pairs, where the keys are strings and the values are
 * defined in the concrete implementations of the class.
 *
 * @author Peter J. Radics
 * @version 0.1
 * @param <T>
 *            The type of the metadata.
 *
 * @see edu.vt.arc.vis.osnap.graph.Graph
 * @see edu.vt.arc.vis.osnap.graph.Node
 * @see edu.vt.arc.vis.osnap.graph.Edge
 * @see edu.vt.arc.vis.osnap.graph.HyperEdge
 * @see edu.vt.arc.vis.osnap.graph.metadata.Schema
 */
@XmlType(name = "Metadata")
@XmlAccessorType(XmlAccessType.NONE)
public class TypedMetadata<T> {


    @XmlAttribute(name = "key")
    private final String key;

    private T            value;


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
    public T getValue() {

        return this.value;
    }

    /**
     * Setter for the value of the metdata.
     *
     * @param value
     *            The value to be set.
     */
    public void setValue(T value) {

        this.value = value;
    }

    @SuppressWarnings("unused")
    private TypedMetadata() {

        this(null, null, false);
    }

    /**
     * Creates a new instance of the Metadata class.
     *
     *
     * @param key
     *            The key of the new metadata instance.
     */
    protected TypedMetadata(String key) {

        this(key, null, false);
    }

    /**
     * Creates a new TypedMetadata object with the provided name and value.
     *
     * @param name
     *            the name.
     * @param value
     *            the value.
     */
    protected TypedMetadata(String name, T value) {

        this(name, value, false);
    }

    /**
     * @param key
     *            the key of the metadata
     * @param value
     *            the value of this metadata.F
     * @param serialization
     *            flag determining whether or not constructor is used in
     *            serialization.
     */
    protected TypedMetadata(String key, T value, boolean serialization) {

        // System.err.println("\t\t\tMetadata Constructor");
        if (key == null && !serialization) {
            throw new IllegalArgumentException("Metadata must have a name.");
        }

        this.key = key;
        this.value = value;
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
    public TypedMetadata(TypedMetadata<T> toCopy) {

        this(toCopy.getKey());

        this.value = toCopy.getValue();
    }



    /**
     * Creates a deep copy of the metadata object.
     *
     * @return A deep copy of the metadata object.
     */
    public TypedMetadata<T> deepCopy() {

        return new TypedMetadata<>(this);
    }



    /**
     * Provides a test to check the equality of a string with the key of the
     * metadata object.
     *
     * @param key
     *            The string to compare the key to.
     *
     * @return <CODE>true</CODE>, if the string is equal to the key. Otherwise
     *         <CODE>false</CODE>.
     */
    public boolean equalsKey(String key) {

        return this.key.equals(key);
    }

    /**
     * Returns the <CODE>MetadataType</CODE> of this instance.
     *
     * @return the <CODE>MetadataType</CODE>.
     */
    public Class<?> getValueType() {

        return this.value.getClass();
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

        if (obj != null && obj instanceof TypedMetadata) {

            final TypedMetadata<?> other = (TypedMetadata<?>) obj;

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


}
