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


/**
 * This class is part of the edu.vt.arc.vis.osnap.graph package.metadatatypes.
 */
package edu.vt.arc.vis.osnap.graph.metadata;


import javax.xml.bind.annotation.XmlType;


/**
 * This class represents metadata with boolean values contained within a graph
 * universe.
 * 
 * This class implements the abstract Metadata class and can be part of
 * Schemata.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 * @see edu.vt.arc.vis.osnap.graph.metadata.Metadata
 * @see edu.vt.arc.vis.osnap.graph.metadata.Schema
 */
@XmlType(name = "BooleanMetadata")
public class BooleanMetadata
        extends Metadata {

    Boolean value;

    @Override
    public Boolean getValue() {

        return this.value;
    }


    @Override
    public void setValue(Object value) {

        if (value == null || value instanceof Boolean) {
            this.value = (Boolean) value;
        }
        else {
            throw new IllegalArgumentException(
                    "Value has to be of type Boolean.");
        }
    }

    @SuppressWarnings("unused")
    private BooleanMetadata() {

        this(null, null, true);
    }

    /**
     * Creates a new instance with the key provided. The value is set to
     * <CODE>null</CODE>.
     * 
     * @param key
     *            the name.
     */
    public BooleanMetadata(String key) {

        this(key, null);
    }


    /**
     * Creates a new instance with the key and value provided.
     * 
     * @param key
     *            the key.
     * @param value
     *            the value.
     */
    public BooleanMetadata(String key, Object value) {

        this(key, value, false);
    }

    private BooleanMetadata(String name, Object value, boolean serialization) {

        super(name, serialization);
        this.setValue(value);
    }

    /**
     * Copy constructor.
     * 
     * @param toCopy
     *            the Metadata to copy.
     */
    public BooleanMetadata(BooleanMetadata toCopy) {

        super(toCopy);
        this.value = toCopy.getValue();
    }


    @Override
    public BooleanMetadata deepCopy() {

        return new BooleanMetadata(this);
    }


    @Override
    public final MetadataType getMetadataType() {

        return MetadataType.BOOLEAN;
    }


}
