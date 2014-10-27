package edu.vt.arc.vis.osnap.core.domain.graph;

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


import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import edu.vt.arc.vis.osnap.core.domain.graph.base.UniverseBase;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IEdge;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraph;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IHyperEdge;
import edu.vt.arc.vis.osnap.core.domain.graph.common.INode;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.IMetadataContainer;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.MetadataMapProperty;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.Schema;
import edu.vt.arc.vis.osnap.events.domain.graph.UniverseDetails;


/**
 * This class represents a graph universe, containing multiple graphs and
 * metadata pertaining to this universe.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
@XmlRootElement(name = "Universe",
        namespace = "edu.vt.arc.vis.osnap.core.domain.graph")
@XmlType(name = "Universe", propOrder = { "universeSchema", "graphSchema",
        "nodeSchema", "edgeSchema", "hyperEdgeSchema", "metadata" })
@XmlSeeAlso({ Schema.class })
public class Universe
        extends UniverseBase
        implements IMetadataContainer {


    @XmlElement(name = "Metadata")
    private final MetadataMapProperty metadata;

    @XmlElement(name = "UniverseSchema")
    private final Schema              universeSchema;

    @XmlElement(name = "GraphSchema")
    private final Schema              graphSchema;
    @XmlElement(name = "NodeSchema")
    private final Schema              nodeSchema;
    @XmlElement(name = "EdgeSchema")
    private final Schema              edgeSchema;
    @XmlElement(name = "HyperedgeSchema")
    private final Schema              hyperEdgeSchema;


    /**
     * Getter for the Universe'scale schema.
     * 
     * @return The universe'scale schema.
     */
    public Schema getUniverseSchema() {

        return this.universeSchema;
    }

    /**
     * Getter for the graphs' schema.
     * 
     * @return The graphs' schema
     */
    public Schema getGraphSchema() {

        return this.graphSchema;
    }


    /**
     * Getter for the nodes' schema.
     * 
     * @return The nodes' schema
     */
    public Schema getNodeSchema() {

        return this.nodeSchema;
    }

    /**
     * Getter for the edges' schema.
     * 
     * @return The edges' schema.
     */
    public Schema getEdgeSchema() {

        return this.edgeSchema;
    }

    /**
     * Getter for the hyperedges' schema.
     * 
     * @return The hyperedges' schema.
     */
    public Schema getHyperEdgeSchema() {

        return this.hyperEdgeSchema;
    }


    /**
     * Creates a new empty universe.
     */
    public Universe() {

        this(null, null, null, null, null);
    }

    /**
     * Creates a new universe with the provided id.
     * 
     * @param id
     *            the id.
     */
    public Universe(String id) {

        this(id, null, null, null, null, null);
    }

    /**
     * Creates a new universe with the provided Schemata.
     * 
     * @param universeSchema
     *            the universe'scale schema.
     * @param graphSchema
     *            the graph schema.
     * @param nodeSchema
     *            the node schema.
     * @param edgeSchema
     *            the edge schema.
     * @param hyperEdgeSchema
     *            the hyperedge schema.
     */
    public Universe(Schema universeSchema, Schema graphSchema,
            Schema nodeSchema, Schema edgeSchema, Schema hyperEdgeSchema) {

        this("default", universeSchema, graphSchema, nodeSchema, edgeSchema,
                hyperEdgeSchema);

    }

    /**
     * Creates a new universe with the provided id and Schemata.
     * 
     * @param id
     *            the id.
     * @param universeSchema
     *            the universe'scale schema.
     * @param graphSchema
     *            the graph schema.
     * @param nodeSchema
     *            the node schema.
     * @param edgeSchema
     *            the edge schema.
     * @param hyperEdgeSchema
     *            the hyperedge schema.
     */
    public Universe(String id, Schema universeSchema, Schema graphSchema,
            Schema nodeSchema, Schema edgeSchema, Schema hyperEdgeSchema) {

        super(id);


        if (universeSchema == null) {
            this.universeSchema = new Schema("UniverseSchema");
        }
        else {
            this.universeSchema = new Schema(universeSchema);
        }

        if (graphSchema == null) {
            this.graphSchema = new Schema("GraphSchema");
        }
        else {
            this.graphSchema = new Schema(graphSchema);
        }

        if (nodeSchema == null) {
            this.nodeSchema = new Schema("NodeSchema");
        }
        else {
            this.nodeSchema = new Schema(nodeSchema);
        }
        if (edgeSchema == null) {
            this.edgeSchema = new Schema("EdgeSchema");
        }
        else {
            this.edgeSchema = new Schema(edgeSchema);
        }

        if (hyperEdgeSchema == null) {
            this.hyperEdgeSchema = new Schema("HyperedgeSchema");
        }
        else {
            this.hyperEdgeSchema = new Schema(hyperEdgeSchema);
        }

        this.metadata = new MetadataMapProperty(this.universeSchema);
    }


    /*
     * (non-Javadoc)
     * 
     * @see edu.vt.arc.vis.osnap.core.domain.graph.base.UniverseBase#toDetails()
     */
    @Override
    public UniverseDetails toDetails() {

        UniverseDetails details = new UniverseDetails(super.toDetails());



        return details;
    }

    /**
     * public void synchronizeMetadataWithSchema() {
     * 
     * for (String name : universeSchema.getNames()) { if
     * (!metadata.containsName(name)) {
     * metadata.addElement(universeSchema.getElement(name)); } } }
     **/

    public void clear() {

        this.clearGraphs();

        this.universeSchema.clear();
        this.metadata.clear();

        this.edgeSchema.clear();
        this.hyperEdgeSchema.clear();
        this.nodeSchema.clear();
        this.graphSchema.clear();
    }


    /**
     * Clears the graphs from the universe.
     */
    public void clearGraphs() {

        Set<String> graphKeys = new HashSet<>(this.getGraphMap().keySet());
        for (String graphID : graphKeys) {
            this.graphRemover(graphID);
        }

        super.clearGraphMap();
    }

    /**
     * Creates a new graph with a provided identifier and adds it to the
     * universe.
     * 
     * If the provided id is already contained in the graph map, the existing
     * graph is retrieved.
     * 
     * @param id
     *            The unique identifier of the graph.
     * @return A new (or existing graph) with the provided unique identifier.
     */
    public Graph createGraph(String id) {

        if (this.getGraphMap().containsKey(id)) {

            return (Graph) this.getGraphMap().get(id);
        }
        if (this.containsID(id)) {

            throw new IllegalArgumentException("ID " + id
                    + " is already in use!");
        }
        Graph newGraph = new Graph(id, this, this.graphSchema);

        super.addGraph(newGraph);

        return newGraph;
    }

    @Override
    public Collection<Graph> getGraphs() {

        List<Graph> graphs = new LinkedList<>();

        for (IGraph graph : super.getGraphs()) {

            if (graph instanceof Graph) {

                graphs.add((Graph) graph);
            }
        }

        return graphs;
    }


    /**
     * Removes a graph from the universe.
     * 
     * @param graphID
     *            The unique identifier of the graph.
     */
    public void removeGraph(String graphID) {

        if (this.getGraphMap().containsKey(graphID)) {

            this.graphRemover(graphID);
        }
    }

    /**
     * Removes a graph from the universe.
     * 
     * @param graph
     *            The actual graph to be removed.
     */
    public void removeGraph(IGraph graph) {

        if (this.getGraphMap().containsValue(graph)) {

            this.graphRemover(graph.getId());
        }

    }


    @Override
    public Graph getGraph(String id) {

        return (Graph) this.getGraphMap().get(id);
    }

    @Override
    public Collection<Node> getNodes() {

        LinkedList<Node> nodes = new LinkedList<>();

        for (INode node : super.getNodes()) {

            if (node instanceof Node) {

                nodes.add((Node) node);
            }
        }

        return nodes;
    }

    /**
     * Clears the nodes from the universe.
     * 
     * As a side-effect, all edges and hyperedges will be removed from the
     * universe as well.
     */
    public void clearNodes() {

        Set<String> nodeKeys = new HashSet<>(this.getNodeMap().keySet());
        for (String nodeID : nodeKeys) {

            this.nodeRemover(nodeID);
        }

        super.clearNodeMap();
    }

    /**
     * Removes all nodes from a graph (and the universe).
     * 
     * @param graph
     *            The graph from which to remove the nodes.
     */
    public void clearNodesFromGraph(Graph graph) {

        Set<String> nodeKeys = new HashSet<>(graph.getNodeMap().keySet());

        for (String nodeID : nodeKeys) {

            this.nodeRemover(nodeID);
        }

        graph.clearNodeMap();
        graph.clearEdgeMap();
    }

    
    /**
     * Creates a new node with a provided identifier and adds it to the graph
     * provided.
     * 
     * If the provided id is already contained in the node map, the existing
     * node is retrieved.
     * 
     * @param id
     *            The unique identifier of the node.
     * @param graphID
     *            The graph to contain this node.
     * 
     * @return A new (or existing node) with the provided unique identifier.
     */
    public Node createNode(String id, String graphID) {

        if (!this.getGraphMap().containsKey(graphID)) {

            throw new IllegalArgumentException("Universe does not contain "
                    + "graph with id " + graphID + ".");
        }
        IGraph graph = this.getGraphMap().get(graphID);



        return this.nodeCreator(id, graph, nodeSchema);
    }

    /**
     * Creates a new node with a provided identifier and adds it to the graph
     * provided.
     * 
     * If the provided id is already contained in the node map, the existing
     * node is retrieved.
     * 
     * @param id
     *            The unique identifier of the node.
     * @param graph
     *            The graph to contain this node.
     * @return A new (or existing node) with the provided unique identifier.
     */
    public Node createNode(String id, IGraph graph) {


        if (!this.getGraphMap().containsValue(graph)) {

            throw new IllegalArgumentException(
                    "Universe does not contain graph.");
        }

        return this.nodeCreator(id, graph, nodeSchema);
    }

    /**
     * Removes a node from the graph.
     * 
     * @param id
     *            The unique identifier of the node.
     */
    public void removeNode(String id) {

        if (this.getNodeMap().containsKey(id)) {
            this.nodeRemover(id);
        }
    }

    /**
     * Removes a node from the graph.
     * 
     * @param node
     *            The actual node to be removed.
     */
    public void removeNode(INode node) {

        if (this.getNodeMap().containsValue(node)) {

            this.nodeRemover(node.getId());
        }

    }

    @Override
    public Node getNode(String id) {

        return (Node) this.getNodeMap().get(id);
    }

    @Override
    public Collection<Edge> getEdges() {

        LinkedList<Edge> edges = new LinkedList<>();

        for (IEdge edge : super.getEdges()) {

            if (edge instanceof Edge) {

                edges.add((Edge) edge);
            }
        }

        return edges;
    }

    /**
     * Removes all edges from the universe.
     */
    public void clearEdges() {

        Set<String> edgeKeys = new HashSet<>(this.getEdgeMap().keySet());
        for (String edgeID : edgeKeys) {

            this.edgeRemover(edgeID);
        }

        super.clearEdgeMap();
    }

    /**
     * Removes all edges from a graph (as well as its nodes and the universe).
     * 
     * @param graph
     *            The graph from which to remove the edges.
     */
    public void clearEdgesFromGraph(Graph graph) {

        Set<String> edgeKeys = new HashSet<>(graph.getEdgeMap().keySet());

        for (String edgeID : edgeKeys) {

            this.edgeRemover(edgeID);
        }

        graph.clearEdgeMap();
    }

    /**
     * Removes all edges from a node (as well as its graph and the universe).
     * 
     * @param node
     *            The node from which to remove the edges.
     */
    public void clearEdgesFromNode(Node node) {

        Set<IEdge> edges = new HashSet<>(node.getEdges());

        for (IEdge edge : edges) {

            this.edgeRemover(edge.getId());
        }

        node.clearEdges();
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
     * @param id
     *            The unique identifier of the edge.
     * @param graphID
     *            The unique identifier of the graph that will contain the edge.
     * @param sourceID
     *            The identifier of the source node of the edge.
     * @param targetID
     *            The identifier of the target node of the edge.
     * @return An edge from the source node to the target node.
     */
    public Edge createEdge(String id, String graphID, String sourceID,
            String targetID) {

        if (!this.getNodeMap().containsKey(sourceID)) {

            throw new IllegalArgumentException(
                    "Universe does not contain source node.");
        }

        if (!this.getNodeMap().containsKey(targetID)) {

            throw new IllegalArgumentException(
                    "Universe does not contain target node.");
        }

        if (!this.getGraphMap().containsKey(graphID)) {

            throw new IllegalArgumentException(
                    "Universe does not contain graph.");
        }


        IGraph theGraph = this.getGraphMap().get(graphID);

        INode sourceNode = this.getNodeMap().get(sourceID);
        INode targetNode = this.getNodeMap().get(targetID);

        return this.edgeCreator(id, theGraph, sourceNode, targetNode, false,
                edgeSchema);
    }

    /**
     * Creates an edge between source and target nodes with a provided
     * identifier, then adds it to the provided graph.
     * 
     * If an edge with the exact parameters provided already exists in the
     * graph, that edge will be returned instead of a new edge.
     * 
     * Please note that the edge is not directed by default!
     * 
     * @param id
     *            The unique identifier of the edge.
     * @param graph
     *            The graph that is to contain the edge.
     * @param source
     *            The the source node of the edge.
     * @param target
     *            The the target node of the edge.
     * 
     * @return An edge from the source node to the target node.
     */
    public Edge createEdge(String id, IGraph graph, INode source, INode target) {

        if (!this.getNodeMap().containsValue(source)) {

            throw new IllegalArgumentException(
                    "Graph does not contain source node.");
        }

        if (!this.getNodeMap().containsValue(target)) {

            throw new IllegalArgumentException(
                    "Graph does not contain target node.");
        }

        if (!this.getGraphMap().containsValue(graph)) {

            throw new IllegalArgumentException(
                    "Universe does not contain graph.");
        }


        return this.edgeCreator(id, graph, source, target, false, edgeSchema);
    }

    /**
     * Removes the edge with the provided identifier from the graph.
     * 
     * @param id
     *            The unique identifier of the edge to be removed.
     */
    public void removeEdge(String id) {

        if (this.getEdgeMap().containsKey(id)) {
            this.edgeRemover(id);
        }
    }

    /**
     * Removes the provided edge from the graph.
     * 
     * @param edge
     *            The edge to be removed.
     */
    public void removeEdge(IEdge edge) {

        if (this.getEdgeMap().containsValue(edge)) {

            this.edgeRemover(edge.getId());
        }
    }

    @Override
    public Edge getEdge(String id) {

        return (Edge) this.getEdgeMap().get(id);
    }


    @Override
    public Collection<HyperEdge> getHyperEdges() {

        LinkedList<HyperEdge> hyperEdges = new LinkedList<>();

        for (IHyperEdge hyperEdge : super.getHyperEdges()) {

            if (hyperEdge instanceof HyperEdge) {

                hyperEdges.add((HyperEdge) hyperEdge);
            }
        }

        return hyperEdges;
    }

    /**
     * Removes all hyperedges from the universe.
     */
    public void clearHyperEdges() {

        Set<String> hyperEdgeKeys = new HashSet<>(this.getHyperEdgeMap()
                .keySet());
        for (String hyperEdgeID : hyperEdgeKeys) {
            this.hyperEdgeRemover(hyperEdgeID);
        }

        super.clearHyperEdgeMap();
    }

    /**
     * Removes all hyperedges from a graph (as well as its nodes and the
     * universe).
     * 
     * @param graph
     *            The graph from which to remove the hyperedges.
     */
    public void clearHyperEdgesFromGraph(Graph graph) {

        Set<String> hyperEdgeKeys = new HashSet<>(graph.getHyperEdgeMap()
                .keySet());

        for (String hyperEdgeID : hyperEdgeKeys) {
            this.hyperEdgeRemover(hyperEdgeID);
        }

        graph.clearHyperEdgeMap();
    }

    /**
     * Removes all hyperedges from a node (as well as its graph and the universe
     * if applicable).
     * 
     * @param node
     *            The node from which to remove the hyperedges.
     */
    public void clearHyperEdgesFromNode(Node node) {

        Set<IHyperEdge> hyperedges = new HashSet<>(node.getHyperEdges());

        for (IHyperEdge hyperEdge : hyperedges) {
            if (hyperEdge.getNodes().size() < 3) {
                this.hyperEdgeRemover(hyperEdge.getId());
            }
            else {
                if (hyperEdge instanceof HyperEdge) {
                    HyperEdge theHyperEdge = (HyperEdge) hyperEdge;
                    theHyperEdge.removeNode(node);
                }

            }
        }

        node.clearHyperEdges();
    }


    /**
     * Creates a hyperedge between the nodes provided with a provided
     * identifier, then adds it to the graph.
     * 
     * If a hyperedge with the exact parameters provided already exists in the
     * graph, that hyperedge will be returned instead of a new hyperedge.
     * 
     * 
     * @param id
     *            The unique identifier of the hyperedge.
     * @param nodeIDs
     *            The identifiers of the nodes of the hyperedge.
     * @param graphID
     *            The identifier of the graph that is to contain the hyperedge.
     * 
     * @return A hyperedge between the nodes.
     */
    public HyperEdge createHyperEdgeFromIdentifiers(String id, String graphID,
            Set<String> nodeIDs) {

        for (String nodeID : nodeIDs) {
            if (!this.getNodeMap().containsKey(nodeID)) {
                throw new IllegalArgumentException("Universe does not contain "
                        + "node " + nodeID + ".");
            }
        }

        if (!this.getGraphMap().containsKey(graphID)) {
            throw new IllegalArgumentException(
                    "Universe does not contain graph.");
        }

        IGraph theGraph = this.getGraphMap().get(graphID);

        Set<INode> nodes = new HashSet<>();

        for (String nodeID : nodeIDs) {
            nodes.add(this.getNodeMap().get(nodeID));
        }


        return this.hyperEdgeCreator(id, theGraph, nodes, hyperEdgeSchema);
    }

    /**
     * Creates a hyperedge between the nodes provided with a provided
     * identifier, then adds it to the graph.
     * 
     * If a hyperedge with the exact parameters provided already exists in the
     * graph, that hyperedge will be returned instead of a new hyperedge.
     * 
     * 
     * @param id
     *            The unique identifier of the hyperedge.
     * @param nodes
     *            The nodes of the hyperedge.
     * @param graph
     *            The graph that is to contain the hyperedge.
     * 
     * @return A hyperedge between the nodes.
     */
    public HyperEdge createHyperEdge(String id, IGraph graph, Set<INode> nodes) {

        for (INode node : nodes) {
            if (!this.getNodeMap().containsValue(node)) {
                throw new IllegalArgumentException(
                        "Universe does not contain node " + node + ".");
            }
        }

        if (!this.getGraphMap().containsValue(graph)) {
            throw new IllegalArgumentException(
                    "Universe does not contain graph.");
        }


        return this.hyperEdgeCreator(id, graph, nodes, hyperEdgeSchema);
    }

    /**
     * Removes the hyperedge with the provided identifier from the graph.
     * 
     * @param id
     *            The unique identifier of the edge to be removed.
     */
    public void removeHyperEdge(String id) {

        if (this.getHyperEdgeMap().containsKey(id)) {
            this.hyperEdgeRemover(id);
        }
    }

    /**
     * Removes the provided hyperedge from the graph.
     * 
     * @param hyperedge
     *            The edge to be removed.
     */
    public void removeHyperEdge(IHyperEdge hyperedge) {

        if (this.getHyperEdgeMap().containsValue(hyperedge)) {
            this.hyperEdgeRemover(hyperedge.getId());
        }
    }


    @Override
    public HyperEdge getHyperEdge(String id) {

        return (HyperEdge) this.getHyperEdgeMap().get(id);
    }


    @Override
    public MetadataMapProperty getMetadataProperty() {

        return this.metadata;
    }


    /**
     * Helper method for the removal of graphs.
     * 
     * This method removes all the edges and nodes of the graph.
     * 
     * Finally, the graph itself is removed from the universe.
     * 
     * @param graphID
     *            the string ID for the graph to be removed
     */
    private void graphRemover(String graphID) {

        IGraph graph = this.getGraphMap().get(graphID);

        if (graph instanceof Graph) {
            // clears all nodes from the graph (and, as a side-effect, also all
            // edges and hyper edges
            this.clearNodesFromGraph((Graph) graph);

            // clears the meta data from the graph
            ((Graph) graph).getMetadataProperty().clear();
        }

        // Remove from the universe
        this.deleteGraph(graph);
    }


    /**
     * Helper method for the creation of nodes.
     * 
     * It checks whether a node with the provided id exists. If so, it checks
     * for the equality of the parameters and returns the found node if they are
     * equal. Otherwise, it throws an exception.
     * 
     * In case the node is not yet contained in the universe, the node is
     * created, added to the graph, and then returned.
     * 
     * @param id
     *            The unique identifier of the node to be added.
     * @param graph
     *            The graph that is to contain the node.
     * @param nodeSchema
     *            Provides a metadata schema for the node.
     * @return The created node (or the existing node).
     */
    private Node nodeCreator(String id, IGraph graph, Schema nodeSchema) {


        if (this.getNodeMap().containsKey(id)) {

            Node containedNode = (Node) this.getNodeMap().get(id);

            if (containedNode.getGraph().equals(graph)) {

                return containedNode;
            }
            throw new IllegalArgumentException("A node with the id \"" + id
                    + " already exists in another graph.");
        }

        if (this.containsID(id)) {

            throw new IllegalArgumentException("ID " + id
                    + " is already in use!");
        }

        Node newNode = new Node(id, graph, nodeSchema);


        // Add to universe
        this.addNode(newNode);

        if (graph instanceof Graph) {
            // Add to graph
            ((Graph) graph).addNode(newNode);
        }

        return newNode;
    }


    /**
     * Helper method for the removal of nodes.
     * 
     * This method goes through all the edges of the node and removes them.
     * 
     * It commences to go through all hyperedges, checking whether the hyperedge
     * would have only one node if the node is removed. It removes the node from
     * the hyperedge if that is not the case, otherwise the hyperedge is
     * removed.
     * 
     * Finally, the node itself is removed from its graph and the universe.
     * 
     * @param nodeID
     *            The unique identifier of the node.
     */
    private void nodeRemover(String nodeID) {

        INode node = this.getNodeMap().get(nodeID);

        // Remove all edges of this node
        Set<? extends IEdge> edges = node.getEdges();
        for (IEdge edge : edges) {

            this.removeEdge(edge);
        }

        // Removes the node'scale involvement in any hyperedges. Should a
        // hyperedge
        // have only one node as a result, the hyperedge is removed also.
        Set<? extends IHyperEdge> hyperedges = node.getHyperEdges();
        for (IHyperEdge hyperEdge : hyperedges) {

            if (hyperEdge.getNodes().size() < 3) {

                this.removeHyperEdge(hyperEdge);
            }
            else {
                if (hyperEdge instanceof HyperEdge) {

                    ((HyperEdge) hyperEdge).removeNode(node);
                }
            }
        }

        if (node.getGraph() instanceof Graph) {

            // Delete it from the graph
            ((Graph) node.getGraph()).deleteNode(node);
        }

        // Removes it from the universe
        super.deleteNode(node);

    }


    /**
     * Helper method for the creation of edges.
     * 
     * It checks whether an edge with the provided id exists. If so, it checks
     * for the equality of the parameters and returns the found edge if they are
     * equal. Otherwise, it throws an exception.
     * 
     * In case the edge is not yet contained in the graph, the edge is created,
     * added to the graph, and then returned.
     * 
     * @param id
     *            The unique identifier of the edge to be added.
     * @param graph
     *            The graph that contains the edge.
     * @param source
     *            The source node of the edge.
     * @param target
     *            The target node of the edge.
     * @param isDirected
     *            Specifies whether or not the edge is directed.
     * @param edgeSchema
     *            Provides a metadata schema for the edge.
     * @return The created edge (or the existing edge).
     */
    private Edge edgeCreator(String id, IGraph graph, INode source,
            INode target, Boolean isDirected, Schema edgeSchema) {

        if (this.getEdgeMap().containsKey(id)) {

            Edge containedEdge = (Edge) this.getEdgeMap().get(id);
            if (containedEdge.getSource().equals(source)
                    && containedEdge.getTarget().equals(target)) {

                return containedEdge;
            }
            throw new IllegalArgumentException("An edge with the id \"" + id
                    + " already exists.");
        }

        if (this.containsID(id)) {

            throw new IllegalArgumentException("ID " + id
                    + " is already in use!");
        }

        Edge newEdge = new Edge(id, graph, source, target, isDirected,
                edgeSchema);


        // Add to universe
        super.addEdge(newEdge);

        if (graph instanceof Graph) {

            // Add to graph
            ((Graph) graph).addEdge(newEdge);
        }

        if (source instanceof Node) {

            // Add to nodes
            ((Node) source).addEdge(newEdge);
        }
        if (target instanceof Node) {

            ((Node) target).addEdge(newEdge);
        }

        return newEdge;
    }


    /**
     * Helper method for the removal of edges.
     * 
     * Removes any occurrence of the edge from any nodes, graphs, and the
     * universe.
     * 
     * @param edgeID
     *            The id of the edge to be removed
     */
    private void edgeRemover(String edgeID) {

        IEdge edge = getEdgeMap().get(edgeID);

        INode source = edge.getSource();
        INode target = edge.getTarget();

        if (source instanceof Node) {

            ((Node) source).deleteEdge(edge);
        }
        if (target instanceof Node) {

            ((Node) target).deleteEdge(edge);
        }

        if (edge.getGraph() instanceof Graph) {

            // Delete it from the graph
            ((Graph) edge.getGraph()).deleteEdge(edge);
        }

        // Removes it from the universe
        super.deleteEdge(edge);

    }


    /**
     * Helper method for the creation of hyperedges.
     * 
     * It checks whether a hyperedge with the provided id exists. If so, it
     * checks for the equality of the parameters and returns the found hyperedge
     * if they are equal. Otherwise, it throws an exception.
     * 
     * In case the hyperedge is not yet contained in the graph, the hyperedge is
     * created, added to the graph, and then returned.
     * 
     * @param id
     *            The unique identifier of the hyperedge to be added.
     * @param graph
     *            The graph that contains the hyperedge.
     * @param nodes
     *            The nodes of the hyperedge.
     * @param hyperEdgeSchema
     *            Provides a metadata schema for the hyperedge.
     * 
     * @return The created (or existing) hyperedge.
     */
    private HyperEdge hyperEdgeCreator(String id, IGraph graph,
            Set<INode> nodes, Schema hyperEdgeSchema) {

        if (this.getHyperEdgeMap().containsKey(id)) {

            IHyperEdge containedHyperEdge = this.getHyperEdgeMap().get(id);

            if ((containedHyperEdge.getNodes().containsAll(nodes))
                    && (nodes.containsAll(containedHyperEdge.getNodes()))) {

                return (HyperEdge) containedHyperEdge;
            }
            throw new IllegalArgumentException("A hyperedge with the id \""
                    + id + " already exists.");
        }

        if (this.containsID(id)) {

            throw new IllegalArgumentException("ID " + id
                    + " is already in use!");
        }

        Boolean containedInGraph = false;
        for (INode node : nodes) {

            if (node.getGraph().equals(graph)) {

                containedInGraph = true;
                break;
            }
        }
        if (!containedInGraph) {

            throw new IllegalArgumentException("Provided graph contains none"
                    + " of the provided nodes!");
        }

        HyperEdge newHyperEdge = new HyperEdge(id, graph, nodes,
                hyperEdgeSchema);


        // Add to universe
        super.addHyperEdge(newHyperEdge);

        if (graph instanceof Graph) {

            // Add to graph
            ((Graph) graph).addHyperEdge(newHyperEdge);
        }

        // Add to nodes
        for (INode node : nodes) {

            if (node instanceof Node) {

                ((Node) node).addHyperEdge(newHyperEdge);
            }
        }

        return newHyperEdge;
    }


    /**
     * Helper method for the removal of hyperedges.
     * 
     * Removes any occurrence of the hyperedge from any nodes, graphs, and the
     * universe.
     * 
     * @param hyperEdgeID
     *            The id of the hyperedge to be removed
     */
    private void hyperEdgeRemover(String hyperEdgeID) {

        IHyperEdge hyperedge = getHyperEdgeMap().get(hyperEdgeID);

        for (INode node : hyperedge.getNodes()) {

            if (node instanceof Node) {

                ((Node) node).deleteHyperEdge(hyperedge);
            }
        }

        if (hyperedge.getGraph() instanceof Graph) {

            // Delete it from the graph
            ((Graph) hyperedge.getGraph()).deleteHyperEdge(hyperedge);
        }

        // Removes it from the universe
        super.deleteHyperEdge(hyperedge);

    }

    @Override
    public boolean isIdentical(IGraphObject other) {

        if (super.isIdentical(other)) {

            if (other instanceof Universe) {

                Universe otherUniverse = (Universe) other;

                boolean sameMetadata = this.getMetadataProperty().isIdentical(
                        (otherUniverse.getMetadataProperty()));

                return sameMetadata;

            }
        }

        return false;
    }


}
