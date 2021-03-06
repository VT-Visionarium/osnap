/*******************************************************************************
 * Copyright 2014 Virginia Tech Visionarium
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/


package edu.vt.arc.vis.osnap.visualization;


import javafx.scene.paint.Color;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.jutility.math.geometry.Scalef;

import edu.vt.arc.vis.osnap.common.IValueTypeContainer;


/**
 * The <code>VisualProperty</code> enum provides the possible visual
 * characteristics of graph objects.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
@XmlEnum
@XmlRootElement(name = "VisualProperty")
@XmlType(name = "VisualProperty")
public enum VisualProperty
        implements IValueTypeContainer {

    /**
     * The shape of a node.
     */
    @XmlEnumValue(value = "graphVis:node.shape")
    NODE_SHAPE("graphVis:node.shape", true, false, false, Shape.class),
    /**
     * The color of a node.
     */
    @XmlEnumValue(value = "graphVis:node.color")
    NODE_COLOR("graphVis:node.color", true, false, false, Color.class),
    /**
     * The scale of a node.
     */
    @XmlEnumValue(value = "graphVis:node.scale")
    NODE_SCALE("graphVis:node.scale", true, false, false, Scalef.class),
    /**
     * The label text of a node.
     */
    @XmlEnumValue(value = "graphVis:node.label")
    NODE_LABEL_TEXT("graphVis:node.label", true, false, false, String.class),
    /**
     * The x position of a node.
     */
    @XmlEnumValue(value = "graphVis:node.xPosition")
    NODE_X_POSITION("graphVis:node.xPosition", true, false, false, Float.class),
    /**
     * The y position of a node.
     */
    @XmlEnumValue(value = "graphVis:node.yPosition")
    NODE_Y_POSITION("graphVis:node.yPosition", true, false, false, Float.class),
    /**
     * The z position of a node.
     */
    @XmlEnumValue(value = "graphVis:node.zPosition")
    NODE_Z_POSITION("graphVis:node.zPosition", true, false, false, Float.class),


    /**
     * The shape of an edge.
     */
    @XmlEnumValue(value = "graphVis:edge.shape")
    EDGE_SHAPE("graphVis:edge.shape", false, true, false, Shape.class),
    /**
     * The color of an edge.
     */
    @XmlEnumValue(value = "graphVis:edge.color")
    EDGE_COLOR("graphVis:edge.color", false, true, false, Color.class),
    /**
     * The scale of an edge.
     */
    @XmlEnumValue(value = "graphVis:edge.scale")
    EDGE_SCALE("graphVis:edge.scale", false, true, false, Scalef.class),
    /**
     * The label text of an edge.
     */
    @XmlEnumValue(value = "graphVis:edge.label")
    EDGE_LABEL_TEXT("graphVis:edge.label", false, true, false, String.class),

    /**
     * The shape of a hyperedge.
     */
    @XmlEnumValue(value = "graphVis:hyperEdge.shape")
    HYPEREDGE_SHAPE("graphVis:hyperEdge.shape", false, false, true, Shape.class),
    /**
     * The color of a hyperedge.
     */
    @XmlEnumValue(value = "graphVis:hyperEdge.color")
    HYPEREDGE_COLOR("graphVis:hyperEdge.color", false, false, true, Color.class),
    /**
     * The scale of a hyperedge.
     */
    @XmlEnumValue(value = "graphVis:hyperEdge.scale")
    HYPEREDGE_SCALE("graphVis:hyperEdge.scale", false, false, true,
            Scalef.class),
    /**
     * The label text of a hyperedge.
     */
    @XmlEnumValue(value = "graphVis:hyperEdge.label")
    HYPEREDGE_LABEL_TEXT("graphVis:hyperEdge.label", false, false, true,
            String.class),


    /**
     * A Viewpoint in the Universe.
     */
    @XmlEnumValue(value = "graphVis:universe.viewpoint")
    UNIVERSE_VIEWPOINT("graphVis:universe.viewpoint", true, true, false,
            Viewpoint.class);


    @XmlAttribute(name = "name")
    private final String   key;
    @XmlAttribute
    private final boolean  nodeProperty;
    @XmlAttribute
    private final boolean  edgeProperty;
    @XmlAttribute
    private final boolean  hyperedgeProperty;

    @XmlAttribute
    private final Class<?> valueType;


    private VisualProperty(String key, boolean nodeProperty,
            boolean edgeProperty, boolean hyperedgeProperty, Class<?> valueType) {

        this.key = key;

        this.nodeProperty = nodeProperty;
        this.edgeProperty = edgeProperty;
        this.hyperedgeProperty = hyperedgeProperty;

        this.valueType = valueType;
    }

    @Override
    public String getKey() {

        return key;
    }

    @Override
    public String toString() {

        return key;
    }


    @Override
    public Class<?> getValueType() {

        return this.valueType;
    }

    /**
     * Determines whether a <code>VisualProperty</code> belongs to a node.
     * 
     * @return <code>true</code> if the <code>VisualPropery</code> is a node
     *         property; <code>false</code> otherwise.
     */
    public boolean isNodeProperty() {

        return nodeProperty;
    }

    /**
     * Determines whether a <code>VisualProperty</code> belongs to an edge.
     * 
     * @return <code>true</code> if the <code>VisualPropery</code> is an edge
     *         property; <code>false</code> otherwise.
     */
    public boolean isEdgeProperty() {

        return edgeProperty;
    }

    /**
     * Determines whether a <code>VisualProperty</code> belongs to a hyperedge.
     * 
     * @return <code>true</code> if the <code>VisualPropery</code> is a
     *         hyperedge property; <code>false</code> otherwise.
     */
    public boolean isHyperedgeProperty() {

        return hyperedgeProperty;
    }
}
