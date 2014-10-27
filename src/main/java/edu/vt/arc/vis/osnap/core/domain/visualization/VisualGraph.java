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


package edu.vt.arc.vis.osnap.core.domain.visualization;

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


import edu.vt.arc.vis.osnap.core.domain.graph.Edge;
import edu.vt.arc.vis.osnap.core.domain.graph.Graph;
import edu.vt.arc.vis.osnap.core.domain.graph.HyperEdge;
import edu.vt.arc.vis.osnap.core.domain.graph.Node;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.jutility.common.datatype.map.ListMapWrapper;


/**
 * This class represents a mixed hypergraph with metadata.
 * 
 * A mixed hypergraph can contain nodes, edges (both directed and undirected),
 * hyperedges (i.e., edges between more than two nodes), and loops (edges where
 * source and target are identical)
 * 
 * <p/>
 * Graphs also work as factories for creating nodes, edges, and hyperedges.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
@XmlType(name = "VisualGraph")
@XmlAccessorType(XmlAccessType.NONE)
public class VisualGraph
        implements IVisualGraphObject {

    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    @XmlAttribute(name = "id")
    private final String                       id;

    @XmlIDREF
    @XmlAttribute
    @XmlSchemaType(name = "IDREF")
    private final Visualization                visualization;

    @XmlIDREF
    @XmlAttribute
    @XmlSchemaType(name = "IDREF")
    private final Graph                        graph;

    private final Map<String, VisualNode>      visualNodeMap;
    private final Map<String, VisualEdge>      visualEdgeMap;
    private final Map<String, VisualHyperEdge> visualHyperEdgeMap;



    @Override
    public String getID() {

        return this.id;
    }

    @Override
    public String getGraphObjectID() {

        return this.getGraphObject().getId();
    }

    /**
     * Returns the {@link Graph} associated with this VisualGraph.
     * 
     * @return the graph.
     */
    public Graph getGraph() {

        return this.graph;
    }

    @Override
    public Graph getGraphObject() {

        return this.getGraph();
    }

    /**
     * Getter for the graph'scale visualization.
     * 
     * @return The graph'scale visualization.
     */
    public Visualization getVisualization() {

        return this.visualization;
    }


    /**
     * Getter for the node map.
     * 
     * The node map maps a node'scale id to the actual node for quick access.
     * 
     * @return The node map.
     */
    public Map<String, VisualNode> getVisualNodeMap() {

        return Collections.unmodifiableMap(this.visualNodeMap);
    }

    /**
     * Getter for the edge map.
     * 
     * The edge map maps an edge'scale id to the actual edge for quick access.
     * 
     * @return The edge map.
     */
    public Map<String, VisualEdge> getVisualEdgeMap() {

        return Collections.unmodifiableMap(this.visualEdgeMap);
    }

    /**
     * Getter for the hyperEdge map.
     * 
     * The edge map maps an hyperEdge'scale id to the actual hyperedge for quick
     * access.
     * 
     * @return The hyperEdge map.
     */
    public Map<String, VisualHyperEdge> getVisualHyperEdgeMap() {

        return Collections.unmodifiableMap(this.visualHyperEdgeMap);
    }

    /**
     * Getter for the nodes.
     * 
     * @return An unmodifiable collection of the nodes.
     */
    public Collection<VisualNode> getVisualNodes() {

        return new LinkedList<>(
                Collections.unmodifiableCollection(this.visualNodeMap.values()));
    }


    @XmlElementWrapper(name = "VisualNodes")
    @XmlElement(name = "VisualNode")
    @XmlIDREF
    private Collection<VisualNode> getSerializableVisualNodes() {

        return new ListMapWrapper<>(this.visualNodeMap, "getID", String.class);
    }

    /**
     * Getter for the edges.
     * 
     * 
     * @return An unmodifiable collection of the edges.
     */
    public Collection<VisualEdge> getVisualEdges() {

        return new LinkedList<>(
                Collections.unmodifiableCollection(this.visualEdgeMap.values()));
    }


    @XmlElementWrapper(name = "VisualEdges")
    @XmlElement(name = "VisualEdge")
    @XmlIDREF
    private Collection<VisualEdge> getSerializableVisualEdges() {

        return new ListMapWrapper<>(this.visualEdgeMap, "getID", String.class);
    }

    /**
     * Getter for the hyperedges.
     * 
     * 
     * @return An unmodifiable collection of the hyperedges.
     */
    public Collection<VisualHyperEdge> getVisualHyperEdges() {

        return new LinkedList<>(
                Collections.unmodifiableCollection(this.visualHyperEdgeMap
                        .values()));
    }


    @XmlElementWrapper(name = "VisualHyperEdges")
    @XmlElement(name = "VisualHyperEdge")
    @XmlIDREF
    private Collection<VisualHyperEdge> getSerializableVisualHyperEdges() {

        return new ListMapWrapper<>(this.visualHyperEdgeMap, "getID",
                String.class);
    }

    @SuppressWarnings("unused")
    private VisualGraph() {

        this(null, null, true);
    }

    /**
     * Creates an instance of the VisualGraph class.
     * 
     * The graph created contains neither nodes, nor edges, nor metadata.
     * 
     * @param graph
     *            The graph associated with this visual graph.
     * @param visualization
     *            The visualization the visualgraph belongs to.
     */
    protected VisualGraph(Graph graph, Visualization visualization) {

        this(graph, visualization, false);
    }

    private VisualGraph(Graph graph, Visualization visualization,
            boolean serialization) {

        if (graph == null && !serialization) {
            throw new IllegalArgumentException("Visual graph must have be "
                    + "associated with a graph.");
        }

        if (visualization == null && !serialization) {
            throw new IllegalArgumentException("Visual graph must belong to a "
                    + "visualization.");
        }

        this.graph = graph;
        this.visualization = visualization;

        if (graph != null) {
            this.id = Visualization.createIdentifier(graph, serialization);
        }
        else {
            this.id = null;
        }

        visualNodeMap = new HashMap<>();
        visualEdgeMap = new HashMap<>();
        visualHyperEdgeMap = new HashMap<>();


    }

    /**
     * Clears the contents of the graph, removing all nodes, edges, and
     * metadata.
     */
    public void clear() {

        // Clearing the nodes removes all edges and hyperedges.
        visualization.clearVisualNodesFromVisualGraph(this);


    }

    /**
     * Clears the nodes from the graph.
     * 
     * As a side-effect, all edges will be removed from the graph as well.
     */
    public void clearVisualNodes() {

        visualization.clearVisualNodesFromVisualGraph(this);
    }

    /**
     * Creates a new node with a provided identifier and adds it to the graph.
     * 
     * If the provided id is already contained in the node map, the existing
     * node is retrieved.
     * 
     * @param node
     *            The node.
     * @return A new (or existing node) with the provided unique identifier.
     */
    public VisualNode createVisualNode(Node node) {

        return visualization.createVisualNode(node, this);
    }

    /**
     * Removes a node from the graph.
     * 
     * @param node
     *            The node.
     */
    public void removeVisualNode(Node node) {

        visualization.removeVisualNode(node);
    }

    /**
     * Removes a node from the graph.
     * 
     * @param visualNode
     *            The actual node to be removed.
     */
    public void removeVisualNode(VisualNode visualNode) {

        visualization.removeVisualNode(visualNode);
    }

    /**
     * Queries the graph for the visual node with the provided identifier.
     * 
     * @param visualNodeID
     *            The unique identifier of the visual node.
     * @return The visual node, if found. Otherwise null.
     */
    public VisualNode getVisualNode(String visualNodeID) {

        return this.visualNodeMap.get(visualNodeID);
    }

    /**
     * Queries the graph for the visual node of the node provided.
     * 
     * @param node
     *            The node.
     * @return The visual node, if found. Otherwise null.
     */
    public VisualNode getVisualNode(Node node) {

        String visualNodeID = Visualization.createIdentifier(node, false);

        return this.getVisualNode(visualNodeID);
    }

    /**
     * Adds the provided VisualNode to the VisualGraph.
     * 
     * @param node
     *            the node to add.
     */
    protected void addVisualNode(VisualNode node) {

        this.visualNodeMap.put(node.getID(), node);
    }

    /**
     * Deletes the provided VisualNode from the VisualGraph.
     * 
     * @param node
     *            the node to delete.
     */
    protected void deleteVisualNode(VisualNode node) {

        this.visualNodeMap.remove(node.getID());
    }

    /**
     * Clears all VisualNodes from the VisualGraph.
     */
    protected void clearVisualNodeMap() {

        this.visualNodeMap.clear();
    }


    /**
     * Removes all edges from the graph.
     */
    public void clearVisualEdges() {

        visualization.clearVisualEdgesFromVisualGraph(this);
    }

    /**
     * Creates an edge between source and target nodes with a provided
     * identifier, then adds it to the graph.
     * 
     * If an edge with the exact parameters provided already exists in the
     * graph, that edge will be returned instead of a new edge.
     * 
     * Please note that the edge is not directed by default!
     * 
     * @param edge
     *            The unique identifier of the edge.
     * @return An edge from the source node to the target node.
     */
    public VisualEdge createVisualEdge(Edge edge) {

        return visualization.createVisualEdge(edge, this.getID());
    }

    /**
     * Removes the edge with the provided identifier from the graph.
     * 
     * @param edge
     *            The edge to be removed.
     */
    public void removeVisualEdge(Edge edge) {

        visualization.removeVisualEdge(edge);
    }

    /**
     * Removes the provided edge from the graph.
     * 
     * @param edge
     *            The edge to be removed.
     */
    public void removeVisualEdge(VisualEdge edge) {

        visualization.removeVisualEdge(edge);
    }

    /**
     * Queries the graph for the visual edge with the provided identifier.
     * 
     * @param visualEdgeID
     *            The unique identifier of the visual edge to be found.
     * @return The visual edge, if it is found. Otherwise null.
     */
    public VisualEdge getVisualEdge(String visualEdgeID) {

        return this.visualEdgeMap.get(visualEdgeID);
    }

    /**
     * Queries the graph for the visual edge of the provided edge.
     * 
     * @param edge
     *            The edge.
     * @return The visual edge, if it is found. Otherwise null.
     */
    public VisualEdge getVisualEdge(Edge edge) {

        String visualEdgeID = Visualization.createIdentifier(edge, false);

        return this.getVisualEdge(visualEdgeID);
    }

    /**
     * Adds the provided VisualEdge to the VisualGraph.
     * 
     * @param edge
     *            the edge to add.
     */
    protected void addVisualEdge(VisualEdge edge) {

        this.visualEdgeMap.put(edge.getID(), edge);
    }

    /**
     * Deletes the provided VisualEdge from the VisualGraph.
     * 
     * @param edge
     *            the edge to delete.
     */
    protected void deleteVisualEdge(VisualEdge edge) {

        this.visualEdgeMap.remove(edge.getID());
    }


    /**
     * Clears all VisualEdges from the VisualGraph.
     */
    protected void clearVisualEdgeMap() {

        this.visualEdgeMap.clear();
    }


    /**
     * Removes all edges from the graph.
     */
    public void clearVisualHyperEdges() {

        visualization.clearVisualHyperEdgesFromVisualGraph(this);
    }

    /**
     * Creates a hyperedge between the nodes provided with a provided
     * identifier, then adds it to the graph.
     * 
     * If a hyperedge with the exact parameters provided already exists in the
     * graph, that hyperedge will be returned instead of a new hyperedge.
     * 
     * 
     * @param hyperEdge
     *            The hyperedge.
     * 
     * @return A hyperedge between the nodes.
     */
    public VisualHyperEdge createVisualHyperEdge(HyperEdge hyperEdge) {

        return visualization.createVisualHyperEdge(hyperEdge, this.getID());
    }

    /**
     * Removes the hyperedge with the provided identifier from the graph.
     * 
     * @param hyperEdge
     *            The hyperedge to be removed.
     */
    public void removeVisualHyperEdge(HyperEdge hyperEdge) {

        visualization.removeVisualHyperEdge(hyperEdge);
    }

    /**
     * Removes the provided hyperedge from the graph.
     * 
     * @param hyperedge
     *            The edge to be removed.
     */
    public void removeVisualHyperEdge(VisualHyperEdge hyperedge) {

        visualization.removeVisualHyperEdge(hyperedge);
    }

    /**
     * Queries the graph for the edge with the provided identifier.
     * 
     * @param id
     *            The unique identifier of the edge to be found.
     * @return The edge, if it is found. Otherwise null.
     */
    public VisualHyperEdge getVisualHyperEdge(String id) {

        return this.visualHyperEdgeMap.get(id);
    }

    /**
     * Queries the graph for the visual hyperedge of the provided hyperedge.
     * 
     * @param hyperedge
     *            The hyperedge.
     * @return The visual edge, if it is found. Otherwise null.
     */
    public VisualHyperEdge getVisualHyperEdge(HyperEdge hyperedge) {

        String visualHyperEdgeID = Visualization.createIdentifier(hyperedge,
                false);

        return this.getVisualHyperEdge(visualHyperEdgeID);
    }

    /**
     * Adds the VisualHyperEdge provided to the VisualGraph.
     * 
     * @param hyperedge
     *            the hyperedge to add.
     */
    protected void addVisualHyperEdge(VisualHyperEdge hyperedge) {

        this.visualHyperEdgeMap.put(hyperedge.getID(), hyperedge);
    }

    /**
     * Deletes the VisualHyperEdge provided from the VisualGraph.
     * 
     * @param hyperedge
     *            the hyperedge to delete.
     */
    protected void deleteVisualHyperEdge(VisualHyperEdge hyperedge) {

        this.visualHyperEdgeMap.remove(hyperedge.getID());
    }

    /**
     * Clears all VisualHyperEdges from the VisualGraph.
     */
    protected void clearVisualHyperEdgeMap() {

        this.visualHyperEdgeMap.clear();
    }


    /**
     * Determines whether or not this <code>VisualGraph</code> is identical to
     * the one provided.
     * 
     * @param other
     *            the other <code>VisualGraph</code>.
     * @return <code>true</code> if they are identical; <code>false</code>
     *         otherwise.
     */
    public boolean isIdentical(VisualGraph other) {


        if (this.equals(other)) {

            boolean graphIdentical = this.getGraph().isIdentical(
                    other.getGraph());
            boolean sameNumberOfNodes = this.getVisualNodes().size() == other
                    .getVisualNodes().size();
            boolean sameNumberOfEdges = this.getVisualEdges().size() == other
                    .getVisualEdges().size();
            boolean sameNumberOfHyperEdges = this.getVisualHyperEdges().size() == other
                    .getVisualHyperEdges().size();

            if (!(graphIdentical && sameNumberOfNodes && sameNumberOfEdges && sameNumberOfHyperEdges)) {
                return false;
            }

            for (VisualNode node : this.getVisualNodes()) {

                boolean identical = false;
                for (VisualNode otherNode : other.getVisualNodes()) {

                    identical = node.isIdentical(otherNode);
                    if (identical) {

                        break;
                    }
                }
                if (!identical) {

                    return false;
                }

            }


            for (VisualEdge edge : this.getVisualEdges()) {

                boolean identical = false;
                for (VisualEdge otherEdge : other.getVisualEdges()) {

                    identical = edge.isIdentical(otherEdge);
                    if (identical) {

                        break;
                    }
                }
                if (!identical) {

                    return false;
                }

            }


            for (VisualHyperEdge hyperedge : this.getVisualHyperEdges()) {

                boolean identical = false;
                for (VisualHyperEdge otherHyperEdge : other
                        .getVisualHyperEdges()) {

                    identical = hyperedge.isIdentical(otherHyperEdge);
                    if (identical) {

                        break;
                    }
                }
                if (!identical) {

                    return false;
                }
            }

            return true;
        }
        return false;
    }



    @Override
    public boolean equals(Object obj) {

        if (obj != null && obj instanceof VisualGraph) {

            final VisualGraph other = (VisualGraph) obj;

            return this.getID().equals(other.getID())
                    && this.getGraph().equals(other.getGraph())
                    && this.getVisualization().equals(other.getVisualization());
        }
        return false;
    }

    @Override
    public int hashCode() {

        int hash = 7;
        hash += 87 * this.id.hashCode();
        hash = 87 * hash + (this.graph.hashCode());
        hash = 87 * hash + (this.visualization.hashCode());
        return hash;
    }

}
