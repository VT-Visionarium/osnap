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


import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * The <CODE>MetadataType</CODE> enum enumerates over the different supported
 * types of metadata.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
@XmlEnum
@XmlType(name = "MetadataType")
public enum MetadataType {
    /**
     * Boolean Metadata
     */
    @XmlEnumValue(value = "Boolean")
    BOOLEAN,
    /**
     * Double Metadata
     */
    @XmlEnumValue(value = "Double")
    DOUBLE,
    /**
     * Float Metadata
     */
    @XmlEnumValue(value = "Float")
    FLOAT,
    /**
     * Integer Metadata
     */
    @XmlEnumValue(value = "Integer")
    INTEGER,
    /**
     * Long Metadata
     */
    @XmlEnumValue(value = "Long")
    LONG,
    /**
     * String Metadata
     */
    @XmlEnumValue(value = "String")
    STRING;
}
