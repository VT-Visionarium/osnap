package edu.vt.arc.vis.osnap.core.domain.visualization;


//@formatter:off
/*
* _
* The Open Semantic Network Analysis Platform (OSNAP)
* _
* Copyright (C) 2012 - 2015 Visionarium at Virginia Tech
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
//@formatter:on


import javafx.scene.paint.Color;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.jutility.math.geometry.ScaleFactor;

import edu.vt.arc.vis.osnap.core.domain.IValueTypeContainer;


/**
 * The {@code VisualProperty} enum provides the possible visual characteristics
 * of graph objects.
 * 
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 0.1.0
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
    NODE_SHAPE("graphVis:node.shape", "Node Shape", true, false, false,
            Shape.class),
    /**
     * The color of a node.
     */
    @XmlEnumValue(value = "graphVis:node.color")
    NODE_COLOR("graphVis:node.color", "Node Color", true, false, false,
            Color.class),
    /**
     * The scale of a node.
     */
    @XmlEnumValue(value = "graphVis:node.scale")
    NODE_SCALE("graphVis:node.scale", "Node Scale", true, false, false,
            ScaleFactor.class),
    /**
     * The label text of a node.
     */
    @XmlEnumValue(value = "graphVis:node.label")
    NODE_LABEL_TEXT("graphVis:node.label", "Node Label Text", true, false,
            false, String.class),
    /**
     * The x position of a node.
     */
    @XmlEnumValue(value = "graphVis:node.xPosition")
    NODE_X_POSITION("graphVis:node.xPosition", "Node X-Position", true, false,
            false, Float.class),
    /**
     * The y position of a node.
     */
    @XmlEnumValue(value = "graphVis:node.yPosition")
    NODE_Y_POSITION("graphVis:node.yPosition", "Node Y-Position", true, false,
            false, Float.class),
    /**
     * The z position of a node.
     */
    @XmlEnumValue(value = "graphVis:node.zPosition")
    NODE_Z_POSITION("graphVis:node.zPosition", "Node Z-Position", true, false,
            false, Float.class),


    /**
     * The shape of an edge.
     */
    @XmlEnumValue(value = "graphVis:edge.shape")
    EDGE_SHAPE("graphVis:edge.shape", "Edge Shape", false, true, false,
            Shape.class),
    /**
     * The color of an edge.
     */
    @XmlEnumValue(value = "graphVis:edge.color")
    EDGE_COLOR("graphVis:edge.color", "Edge Color", false, true, false,
            Color.class),
    /**
     * The scale of an edge.
     */
    @XmlEnumValue(value = "graphVis:edge.scale")
    EDGE_SCALE("graphVis:edge.scale", "Edge Scale", false, true, false,
            ScaleFactor.class),
    /**
     * The label text of an edge.
     */
    @XmlEnumValue(value = "graphVis:edge.label")
    EDGE_LABEL_TEXT("graphVis:edge.label", "Edge Label Text", false, true,
            false, String.class),

    /**
     * The shape of a hyperedge.
     */
    @XmlEnumValue(value = "graphVis:hyperEdge.shape")
    HYPEREDGE_SHAPE("graphVis:hyperEdge.shape", "Hyperedge Shape", false,
            false, true, Shape.class),
    /**
     * The color of a hyperedge.
     */
    @XmlEnumValue(value = "graphVis:hyperEdge.color")
    HYPEREDGE_COLOR("graphVis:hyperEdge.color", "Hyperedge Color", false,
            false, true, Color.class),
    /**
     * The scale of a hyperedge.
     */
    @XmlEnumValue(value = "graphVis:hyperEdge.scale")
    HYPEREDGE_SCALE("graphVis:hyperEdge.scale", "Hyperedge Scale", false,
            false, true, ScaleFactor.class),
    /**
     * The label text of a hyperedge.
     */
    @XmlEnumValue(value = "graphVis:hyperEdge.label")
    HYPEREDGE_LABEL_TEXT("graphVis:hyperEdge.label", "Hyperedge Label Text",
            false, false, true, String.class),


    /**
     * A Viewpoint in the Universe.
     */
    @XmlEnumValue(value = "graphVis:universe.viewpoint")
    UNIVERSE_VIEWPOINT("graphVis:universe.viewpoint", "Viewpoint", true, true,
            false, Viewpoint.class);


    @XmlAttribute(name = "key")
    private final String   key;
    @XmlAttribute(name = "name")
    private final String   name;
    @XmlAttribute
    private final boolean  nodeProperty;
    @XmlAttribute
    private final boolean  edgeProperty;
    @XmlAttribute
    private final boolean  hyperedgeProperty;

    @XmlAttribute
    private final Class<?> valueType;


    private VisualProperty(String key, String name, boolean nodeProperty,
            boolean edgeProperty, boolean hyperedgeProperty, Class<?> valueType) {

        this.key = key;
        this.name = name;

        this.nodeProperty = nodeProperty;
        this.edgeProperty = edgeProperty;
        this.hyperedgeProperty = hyperedgeProperty;

        this.valueType = valueType;
    }

    @Override
    public String getKey() {

        return key;
    }


    /**
     * Returns the name.
     * 
     * @return the name.
     */
    public String getName() {

        return this.name;
    }

    @Override
    public String toString() {

        return this.name;
    }


    @Override
    public Class<?> getValueType() {

        return this.valueType;
    }

    /**
     * Determines whether a {@code VisualProperty} belongs to a node.
     * 
     * @return {@code true} if the {@code VisualPropery} is a node property;
     *         {@code false} otherwise.
     */
    public boolean isNodeProperty() {

        return nodeProperty;
    }

    /**
     * Determines whether a {@code VisualProperty} belongs to an edge.
     * 
     * @return {@code true} if the {@code VisualPropery} is an edge property;
     *         {@code false} otherwise.
     */
    public boolean isEdgeProperty() {

        return edgeProperty;
    }

    /**
     * Determines whether a {@code VisualProperty} belongs to a hyperedge.
     * 
     * @return {@code true} if the {@code VisualPropery} is a hyperedge
     *         property; {@code false} otherwise.
     */
    public boolean isHyperedgeProperty() {

        return hyperedgeProperty;
    }
}
