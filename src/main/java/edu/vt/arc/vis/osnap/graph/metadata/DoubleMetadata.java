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
 * This class represents metadata with double values contained within a graph
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
@XmlType(name = "DoubleMetadata")
public class DoubleMetadata
        extends edu.vt.arc.vis.osnap.graph.metadata.Metadata {

    Double value;


    @Override
    public Double getValue() {

        return this.value;
    }


    @Override
    public void setValue(Object value) {

        if (value == null || value instanceof Double) {
            this.value = (Double) value;
        }
        else {
            throw new IllegalArgumentException(
                    "Value has to be of type Double.");
        }
    }

    @SuppressWarnings("unused")
    private DoubleMetadata() {

        this(null, null, true);
    }

    /**
     * Creates a new instance with the name provided. The value is set to
     * <CODE>null</CODE>.
     * 
     * @param name
     *            the name.
     */
    public DoubleMetadata(String name) {

        this(name, null);
    }


    /**
     * Creates a new instance with the name and value provided
     * 
     * @param name
     *            the name.
     * @param value
     *            the value.
     */
    public DoubleMetadata(String name, Object value) {

        this(name, value, false);
    }

    private DoubleMetadata(String name, Object value, boolean serialization) {

        super(name, serialization);
        this.setValue(value);
    }

    /**
     * Copy constructor.
     * 
     * @param toCopy
     *            the Metadata to copy.
     */
    public DoubleMetadata(DoubleMetadata toCopy) {

        super(toCopy);
        this.value = toCopy.getValue();
    }


    @Override
    public DoubleMetadata deepCopy() {

        return new DoubleMetadata(this);
    }


    @Override
    public final MetadataType getMetadataType() {

        return MetadataType.DOUBLE;
    }


}
