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


package edu.vt.arc.vis.osnap.core.domain.graph.common;


import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;


/**
 * The {@code GraphObjectProperty} enum provides all properties that of any
 * object within a graph universe. </p> This includes the properties of
 * {@code Node}scale, {@code Edge}scale, {@code HyperEdge}scale, {@code Graph}
 * scale, and {@code Universe}scale.
 * 
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 0.1.0
 */
@XmlEnum
public enum GraphObjectProperty
        implements IGraphObjectBasedValueTypeContainer {

    /**
     * The node degree signifies the amount of edges and hyperedges that contain
     * the node.
     */
    @XmlEnumValue(value = "graphVis:degree")
    DEGREE("graphVis:degree", Long.class),
    /**
     * The node degree (edges) signifies the amount of edges that contain the
     * node.
     */
    @XmlEnumValue(value = "graphVis:degree.edges")
    DEGREE_EDGES("graphVis.degree.edges", Long.class),
    /**
     * The node degree (hyperedges) signifies the amount of hyperedges that
     * contain the node.
     */
    @XmlEnumValue(value = "graphVis:degree.hyperedges")
    DEGREE_HYPEREDGES("graphVis:degree.hyperedges", Long.class),
    /**
     * The node in-degree signifies the amount of incoming edges that contain
     * the node.
     */
    @XmlEnumValue(value = "graphVis:degree.in")
    IN_DEGREE("graphVis:degree.in", Long.class),
    /**
     * The node in-degree (edges) signifies the amount of incoming edges that
     * contain the node.
     */
    @XmlEnumValue(value = "graphVis:degree.in.edges")
    IN_DEGREE_EDGES("graphVis:degree.in.edges", Long.class),
    /**
     * The node in-degree (hyperedges) signifies the amount of incoming
     * hyperedges that contain the node.
     */
    @XmlEnumValue(value = "graphVis:degree.in.hyperedges")
    IN_DEGREE_HYPEREDGES("graphVis:degree.in.hyperedges", Long.class),
    /**
     * The node out-degree signifies the amount of outgoing edges that contain
     * the node.
     */
    @XmlEnumValue(value = "graphVis:degree.out")
    OUT_DEGREE("graphVis:degree.out", Long.class),
    /**
     * The node out-degree (edges) signifies the amount of outgoing edges that
     * contain the node.
     */
    @XmlEnumValue(value = "graphVis:degree.out.edges")
    OUT_DEGREE_EDGES("graphVis:degree.out.edges", Long.class),
    /**
     * The node out-degree (hyperedges) signifies the amount of outgoing
     * hyperedges that contain the node.
     */
    @XmlEnumValue(value = "graphVis:degree.out.hyperedges")
    OUT_DEGREE_HYPEREDGES("graphVis:degree.out.hyperedges", Long.class),


    /**
     * Signifies whether or not an edge is directed.
     */
    @XmlEnumValue(value = "graphVis:directed")
    DIRECTED("graphVis:directed", Boolean.class),


    /**
     * Signifies whether or not an endpoint is incoming.
     */
    @XmlEnumValue(value = "graphVis:incoming")
    INCOMING("graphVis:incoming", Boolean.class),

    /**
     * Signifies whether or not an endpoint is outgoing.
     */
    @XmlEnumValue(value = "graphVis:outgoing")
    OUTGOING("graphVis:outgoing", Boolean.class),


    /**
     * Signifies the number of nodes of a hyperedge.
     */
    @XmlEnumValue(value = "graphVis:cardinality")
    CARDINALITY("graphVis:hyperEdge.cardinality", Long.class),


    /**
     * The graph size signifies the number of edges in the graph.
     */
    @XmlEnumValue(value = "graphVis:size")
    SIZE("graphVis:size", Long.class),
    /**
     * The graph size (edges) signifies the number of edges in the graph.
     */
    @XmlEnumValue(value = "graphVis:size.edges")
    SIZE_EDGES("graphVis:size.edges", Long.class),
    /**
     * /** The graph size (hyperedges) signifies the number of hyperedges in the
     * graph.
     */
    @XmlEnumValue(value = "graphVis:size.hyperedges")
    SIZE_HYPEREDGES("graphVis:size.hyperedges", Long.class),
    /**
     * The graph rank signifies the maximum cardinality of any edge.
     */
    @XmlEnumValue(value = "graphVis:rank")
    RANK("graphVis:rank", Long.class),
    /**
     * The graph order size signifies the number of nodes in the graph.
     */
    @XmlEnumValue(value = "graphVis:order")
    ORDER("graphVis:order", Long.class),


    /**
     * The maximum degree of a graph signifies the greatest number of edges and
     * hyperedges of any node in the graph.
     */
    @XmlEnumValue(value = "graphVis:maxDegree")
    MAX_DEGREE("graphVis:maxDegree", Long.class),
    /**
     * The maximum edge degree of a graph signifies the greatest number of edges
     * of any node in the graph.
     */
    @XmlEnumValue(value = "graphVis:maxDegree.edges")
    MAX_DEGREE_EDGES("graphVis:maxDegree.edges", Long.class),
    /**
     * The maximum hyperedge degree of a graph signifies the greatest number of
     * hyperedges of any node in the graph.
     */
    @XmlEnumValue(value = "graphVis:maxDegree.hyperedges")
    MAX_DEGREE_HYPEREDGES("graphVis:maxDegree.hyperedges", Long.class),
    /**
     * The maximum in-degree of a graph signifies the greatest number of
     * incoming edges and hyperedges of any node in the graph.
     */
    @XmlEnumValue(value = "graphVis:maxDegree.in")
    MAX_IN_DEGREE("graphVis:maxDegree.in", Long.class),
    /**
     * The maximum edge in-degree of a graph signifies the greatest number of
     * incoming edges of any node in the graph.
     */
    @XmlEnumValue(value = "graphVis:maxDegree.in.edges")
    MAX_IN_DEGREE_EDGES("graphVis:maxDegree.in.edges", Long.class),
    /**
     * The maximum hyperedge in-degree of a graph signifies the greatest number
     * of incoming hyperedges of any node in the graph.
     */
    @XmlEnumValue(value = "graphVis:maxDegree.in.hyperedges")
    MAX_IN_DEGREE_HYPEREDGES("graphVis:maxDegree.in.hyperedges", Long.class),
    /**
     * The maximum out-degree of a graph signifies the greatest number of
     * outgoing edges and hyperedges of any node in the graph.
     */
    @XmlEnumValue(value = "graphVis:maxDegree.out")
    MAX_OUT_DEGREE("graphVis:maxDegree.out", Long.class),
    /**
     * The maximum edge out-degree of a graph signifies the greatest number of
     * outgoing edges of any node in the graph.
     */
    @XmlEnumValue(value = "graphVis:maxDegree.out.edges")
    MAX_OUT_DEGREE_EDGES("graphVis:maxDegree.out.edges", Long.class),
    /**
     * The maximum hyperedge out-degree of a graph signifies the greatest number
     * of outgoing hyperedges of any node in the graph.
     */
    @XmlEnumValue(value = "graphVis:maxDegree.out.hyperedges")
    MAX_OUT_DEGREE_HYPEREDGES("graphVis:maxDegree.out.hyperedges", Long.class),
    /**
     * The minimum degree of a graph signifies the smallest number of edges and
     * hyperedges of any node in the graph.
     */
    @XmlEnumValue(value = "graphVis:minDegree")
    MIN_DEGREE("graphVis:minDegree", Long.class),
    /**
     * The minimum edge degree of a graph signifies the smallest number of edges
     * of any node in the graph.
     */
    @XmlEnumValue(value = "graphVis:minDegree.edges")
    MIN_DEGREE_EDGES("graphVis:minDegree.edges", Long.class),
    /**
     * The minimum hyperedge degree of a graph signifies the smallest number of
     * hyperedges of any node in the graph.
     */
    @XmlEnumValue(value = "graphVis:minDegree.hyperedges")
    MIN_DEGREE_HYPEREDGES("graphVis:minDegree.hyperedges", Long.class),
    /**
     * The minimum in-degree of a graph signifies the smallest number of
     * incoming edges and hyperedges of any node in the graph.
     */
    @XmlEnumValue(value = "graphVis:minDegree.in")
    MIN_IN_DEGREE("graphVis:minDegree.in", Long.class),
    /**
     * The minimum edge in-degree of a graph signifies the smallest number of
     * incoming edges of any node in the graph.
     */
    @XmlEnumValue(value = "graphVis:minDegree.in.edges")
    MIN_IN_DEGREE_EDGES("graphVis:minDegree.in.edges", Long.class),
    /**
     * The minimum hyperedge in-degree of a graph signifies the smallest number
     * of incoming hyperedges of any node in the graph.
     */
    @XmlEnumValue(value = "graphVis:minDegree.in.hyperedges")
    MIN_IN_DEGREE_HYPEREDGES("graphVis:minDegree.in.hyperedges", Long.class),
    /**
     * The minimum out-degree of a graph signifies the smallest number of
     * outgoing edges and hyperedges of any node in the graph.
     */
    @XmlEnumValue(value = "graphVis:minDegree.out")
    MIN_OUT_DEGREE("graphVis:minDegree.out", Long.class),
    /**
     * The minimum edge out-degree of a graph signifies the smallest number of
     * outgoing edges of any node in the graph.
     */
    @XmlEnumValue(value = "graphVis:minDegree.out.edges")
    MIN_OUT_DEGREE_EDGES("graphVis:minDegree.out.edges", Long.class),
    /**
     * The minimum hyperedge out-degree of a graph signifies the smallest number
     * of outgoing hyperedges of any node in the graph.
     */
    @XmlEnumValue(value = "graphVis:minDegree.out.hyperedges")
    MIN_OUT_DEGREE_HYPEREDGES("graphVis:minDegree.out.hyperedges", Long.class),
    /**
     * Specifies whether or not the graph is uniform (i.e., all edges have the
     * same cardinality).
     */
    @XmlEnumValue(value = "graphVis:uniform")
    UNIFORM("graphVis:uniform", Boolean.class),
    /**
     * Specifies the cardinality of the edges, if the graph is uniform.
     */
    @XmlEnumValue(value = "graphVis:uniform.k")
    UNIFORM_K("graphVis:uniform.k", Long.class),
    /**
     * Specifies whether or not the graph is regular (i.e., all vertices have
     * the same degree).
     */
    @XmlEnumValue(value = "graphVis:regular")
    REGULAR("graphVis:regular", Boolean.class),
    /**
     * Specifies the degree of the nodes, if the graph is regular.
     */
    @XmlEnumValue(value = "graphVis:regular.k")
    REGULAR_K("graphVis:regular.k", Long.class),


    /**
     * Specifies the number of graphs in a graph universe.
     */
    @XmlEnumValue(value = "graphVis:numberOfGraphs")
    NUMBER_OF_GRAPHS("graphVis:numberOfGraphs", Long.class);


    private final String   key;

    private final Class<?> valueType;


    private GraphObjectProperty(String name, Class<?> valueType) {

        this.key = name;
        this.valueType = valueType;
    }

    @Override
    public String getKey() {

        return this.key;
    }

    @Override
    public Class<?> getValueType() {

        return this.valueType;
    }


    @Override
    public String toString() {

        return this.key;
    }

    /**
     * Returns the GraphObjectProperty with the provided string, or {@code null}
     * .
     * 
     * @param string
     *            the String representation of a GraphObjectProperty
     * @return the GraphObjectProperty with the provided string, or {@code null}
     *         .
     */
    public static GraphObjectProperty fromString(String string) {

        for (GraphObjectProperty property : GraphObjectProperty.values()) {
            if (property.key.equals(string)) {
                return property;
            }
        }
        throw new IllegalArgumentException(" No enum constant " + string);
    }

    @Override
    public Object getValueForGraphObject(IGraphObject graphObject) {

        return graphObject.getValueOfGraphProperty(this);
    }

}
