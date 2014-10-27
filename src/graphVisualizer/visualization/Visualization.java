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


package graphVisualizer.visualization;


import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import graphVisualizer.graph.Edge;
import graphVisualizer.graph.Graph;
import graphVisualizer.graph.HyperEdge;
import graphVisualizer.graph.Node;
import graphVisualizer.graph.Universe;
import graphVisualizer.graph.common.IGraphObject;

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
 * This class represents a graph universe, containing multiple graphs and
 * metadata pertaining to this universe.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "Visualization", propOrder = { "id", "universe",
        "serializableViewpoints", "serializableVisualGraphs",
        "serializableVisualNodes", "serializableVisualEdges",
        "serializableVisualHyperEdges" })
public class Visualization
        implements IVisualGraphObject {

    /**
     * The ID prefix of visual objects.
     */
    private static final String                PREFIX = "Visualization::";

    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    @XmlAttribute(name = "id")
    private final String                       id;



    @XmlAttribute
    private final Class<? extends Number>      precision;
    @XmlIDREF
    @XmlAttribute
    @XmlSchemaType(name = "IDREF")
    private final Universe                     universe;

    private final Map<String, VisualGraph>     visualGraphMap;

    private final Map<String, VisualNode>      visualNodeMap;
    private final Map<String, VisualEdge>      visualEdgeMap;
    private final Map<String, VisualHyperEdge> visualHyperEdgeMap;

    private final Map<String, Viewpoint>       viewpoints;


    @Override
    public String getID() {

        return this.id;
    }

    @Override
    public String getGraphObjectID() {

        return this.universe.getID();
    }

    /**
     * Returns the precision of the {@link VisualObject VisualObjects'}
     * numerical properties in this {@link Visualization}.
     * 
     * @return the precision.
     */
    public Class<? extends Number> getPrecision() {

        return this.precision;
    }

    /**
     * The universe of this visualization.
     * 
     * @return the universe.
     */
    public Universe getUniverse() {

        return this.universe;
    }

    @Override
    public Universe getGraphObject() {

        return this.getUniverse();
    }


    /**
     * Getter for the viewpoint map.
     * 
     * The viewpoint map maps a viewpoint'scale id to the actual viewpoint for quick
     * access.
     * 
     * @return The visual graph map (unmodifiable).
     */
    public Map<String, Viewpoint> getViewpointMap() {

        return Collections.unmodifiableMap(this.viewpoints);
    }

    /**
     * Getter for the viewpoints.
     * 
     * @return An unmodifiable collection of viewpoints.
     */
    public Collection<Viewpoint> getViewpoints() {

        return new LinkedList<>(
                Collections.unmodifiableCollection(this.viewpoints.values()));
    }


    @XmlElementWrapper(name = "Viewpoints")
    @XmlElement(name = "Viewpoint")
    private Collection<Viewpoint> getSerializableViewpoints() {

        return new ListMapWrapper<>(this.viewpoints, "getID", String.class);
    }

    /**
     * Adds the {@link Viewpoint} to the list of viewpoints.
     * 
     * @param viewpoint
     *            the {@link Viewpoint} to add.
     * @return the previous value associated with the {@link Viewpoint}'scale id, or
     *         null.
     */
    public Viewpoint addViewpoint(Viewpoint viewpoint) {

        return this.viewpoints.put(viewpoint.getID(), viewpoint);
    }

    /**
     * Removes the {@link Viewpoint} from the list of viewpoints.
     * 
     * @param viewpointID
     *            the id of the {@link Viewpoint} to remove.
     * @return the previous value associated with the id, or null.
     */
    public Viewpoint removeViewpoint(String viewpointID) {

        if (viewpointID != null) {

            return this.viewpoints.remove(viewpointID);
        }
        return null;
    }

    /**
     * Removes the {@link Viewpoint} from the list of viewpoints.
     * 
     * @param viewpoint
     *            the {@link Viewpoint} to remove.
     * @return the previous value associated with the {@link Viewpoint}'scale id, or
     *         null.
     */
    public Viewpoint removeViewpoint(Viewpoint viewpoint) {

        if (viewpoint != null) {

            return this.removeViewpoint(viewpoint.getID());
        }

        return null;
    }

    /**
     * Clears the {@link Viewpoint Viewpoints}.
     */
    public void clearViewpoints() {

        this.viewpoints.clear();
    }


    /**
     * Getter for the graph map.
     * 
     * The graph map maps a graph'scale id to the actual graph for quick access.
     * 
     * @return The visual graph map (unmodifiable).
     */
    public Map<String, VisualGraph> getVisualGraphMap() {

        return Collections.unmodifiableMap(this.visualGraphMap);
    }

    /**
     * Getter for the graphs.
     * 
     * @return An unmodifiable collection of visual graphs.
     */
    public Collection<VisualGraph> getVisualGraphs() {

        return new LinkedList<>(
                Collections.unmodifiableCollection(this.visualGraphMap.values()));
    }

    @XmlElementWrapper(name = "VisualGraphs")
    @XmlElement(name = "VisualGraph")
    private Collection<VisualGraph> getSerializableVisualGraphs() {

        return new ListMapWrapper<>(this.visualGraphMap, "getID", String.class);
    }

    /**
     * Getter for the visual node map.
     * 
     * The visual node map maps a visual node'scale id to the actual visual node for
     * quick access.
     * 
     * @return The visual node map (unmodifiable).
     */
    public Map<String, VisualNode> getVisualNodeMap() {

        return Collections.unmodifiableMap(this.visualNodeMap);
    }

    /**
     * Getter for the visual nodes.
     * 
     * @return An unmodifiable collection of visual nodes.
     */
    public Collection<VisualNode> getVisualNodes() {

        return new LinkedList<>(
                Collections.unmodifiableCollection(this.visualNodeMap.values()));
    }


    @XmlElementWrapper(name = "VisualNodes")
    @XmlElement(name = "VisualNode")
    private Collection<VisualNode> getSerializableVisualNodes() {

        return new ListMapWrapper<>(this.visualNodeMap, "getID", String.class);
    }

    /**
     * Getter for the visual edge map.
     * 
     * The visual edge map maps a visual edge'scale id to the actual visual edge for
     * quick access.
     * 
     * @return The visual edge map (unmodifiable).
     */
    public Map<String, VisualEdge> getVisualEdgeMap() {

        return Collections.unmodifiableMap(this.visualEdgeMap);
    }

    /**
     * Getter for the visual edges.
     * 
     * @return An unmodifiable collection of visual edges.
     */
    public Collection<VisualEdge> getVisualEdges() {

        return new LinkedList<>(
                Collections.unmodifiableCollection(this.visualEdgeMap.values()));
    }

    @XmlElementWrapper(name = "VisualEdges")
    @XmlElement(name = "VisualEdge")
    private Collection<VisualEdge> getSerializableVisualEdges() {

        return new ListMapWrapper<>(this.visualEdgeMap, "getID", String.class);
    }

    /**
     * Getter for the visual hyperedge map.
     * 
     * The visual hyperedge map maps a visual hyperedge'scale id to the actual
     * visual hyperedge for quick access.
     * 
     * @return The visual hyper map (unmodifiable).
     */
    public Map<String, VisualHyperEdge> getVisualHyperEdgeMap() {

        return Collections.unmodifiableMap(this.visualHyperEdgeMap);
    }

    /**
     * Getter for the visual hyperedges.
     * 
     * @return An unmodifiable collection of visual hyperedges.
     */
    public Collection<VisualHyperEdge> getVisualHyperEdges() {

        return new LinkedList<>(
                Collections.unmodifiableCollection(this.visualHyperEdgeMap
                        .values()));
    }

    @XmlElementWrapper(name = "VisualHyperEdges")
    @XmlElement(name = "VisualHyperEdge")
    private Collection<VisualHyperEdge> getSerializableVisualHyperEdges() {

        return new ListMapWrapper<>(this.visualHyperEdgeMap, "getID",
                String.class);
    }


    @SuppressWarnings("unused")
    private Visualization() {

        this(null, Float.class, true);
    }

    /**
     * Creates a new visualization for the provided universe.
     * 
     * @param universe
     *            the universe to visualize.
     */
    public Visualization(Universe universe) {

        this(universe, Float.class);
    }

    /**
     * Creates a new visualization for the provided universe.
     * 
     * @param universe
     *            the universe to visualize.
     * @param precision
     *            the precision of the visual nodes' numeric properties in this
     *            visualization.
     */
    public Visualization(Universe universe, Class<? extends Number> precision) {

        this(universe, precision, false);
    }

    private Visualization(Universe universe, Class<? extends Number> precision,
            boolean serialization) {

        if (universe == null && !serialization) {

            throw new IllegalArgumentException(
                    "Visual universe must be associated with a universe.");
        }

        this.precision = precision;

        this.viewpoints = new LinkedHashMap<>();

        this.visualGraphMap = new LinkedHashMap<>();
        this.visualNodeMap = new LinkedHashMap<>();
        this.visualEdgeMap = new LinkedHashMap<>();
        this.visualHyperEdgeMap = new LinkedHashMap<>();


        this.universe = universe;

        if (universe != null) {

            this.id = Visualization.createIdentifier(universe, serialization);

            for (Graph graph : universe.getGraphs()) {

                this.createVisualGraph(graph);
            }


            for (Node node : universe.getNodes()) {

                VisualGraph visualGraph = this.getVisualGraph(node.getGraph());

                this.createVisualNode(node, visualGraph);
            }

            for (Edge edge : universe.getEdges()) {

                VisualGraph visualGraph = this.getVisualGraph(edge.getGraph());

                this.createVisualEdge(edge, visualGraph);
            }

            for (HyperEdge hyperEdge : universe.getHyperEdges()) {

                VisualGraph visualGraph = this.getVisualGraph(hyperEdge
                        .getGraph());

                this.createVisualHyperEdge(hyperEdge, visualGraph);
            }


        }
        else {

            this.id = null;
        }


    }


    /**
     * Clears the visualization.
     */
    public void clear() {

        this.clearVisualGraphs();
    }


    /**
     * Clears the visual graphs from the visualization.
     */
    public void clearVisualGraphs() {

        Set<String> visualGraphIDs = new LinkedHashSet<>(
                visualGraphMap.keySet());
        for (String visualGraphID : visualGraphIDs) {

            this.visualGraphRemover(visualGraphID);
        }

        this.visualGraphMap.clear();
    }

    /**
     * Creates a new visual graph for the provided graph and adds it to the
     * visualization.
     * 
     * If a visual graph for the provided graph is already contained in the
     * visualization, the existing visual graph is retrieved.
     * 
     * @param graph
     *            The graph to be visualized.
     * @return A visual graph.
     */
    public VisualGraph createVisualGraph(Graph graph) {

        if (graph == null) {

            throw new IllegalArgumentException(
                    "Cannot create visual representation without existing graph.");
        }

        String visualGraphID = Visualization.createIdentifier(graph, false);

        if (this.visualGraphMap.containsKey(visualGraphID)) {

            return this.visualGraphMap.get(visualGraphID);
        }
        else {

            VisualGraph visualGraph = new VisualGraph(graph, this);

            this.visualGraphMap.put(visualGraphID, visualGraph);

            return visualGraph;
        }
    }

    /**
     * Removes the visual graph associated with the graph provided from the
     * visualization.
     * 
     * @param graph
     *            The graph to be removed.
     */
    public void removeVisualGraph(Graph graph) {

        String visualGraphID = Visualization.createIdentifier(graph, false);

        if (this.visualGraphMap.containsKey(visualGraphID)) {

            this.visualGraphRemover(visualGraphID);
        }
    }

    /**
     * Removes a visual graph from the visualization.
     * 
     * @param visualGraph
     *            The actual graph to be removed.
     */
    public void removeVisualGraph(VisualGraph visualGraph) {

        if (visualGraphMap.containsValue(visualGraph)) {

            this.visualGraphRemover(visualGraph.getID());
        }

    }

    /**
     * Returns the visual graph associated with the provided graph.
     * 
     * @param graph
     *            The graph.
     * @return The visual graph, if found. Otherwise null.
     */
    public VisualGraph getVisualGraph(Graph graph) {

        String visualGraphID = Visualization.createIdentifier(graph, false);

        return this.getVisualGraph(visualGraphID);
    }

    /**
     * Returns the visual graph with the provided id.
     * 
     * @param visualGraphID
     *            the ID of the visual graph.
     * @return The visual graph, if found. Otherwise null.
     */
    public VisualGraph getVisualGraph(String visualGraphID) {

        return this.visualGraphMap.get(visualGraphID);
    }


    /**
     * Clears the visual nodes from the visualization.
     * 
     * As a side-effect, all visual edges and visual hyperedges will be removed
     * from the visualization as well.
     */
    public void clearVisualNodes() {

        Set<String> visualNodeIDs = new LinkedHashSet<>(
                this.visualNodeMap.keySet());
        for (String visualNodeID : visualNodeIDs) {

            this.visualNodeRemover(visualNodeID);
        }

        this.visualNodeMap.clear();
    }

    /**
     * Removes all visual nodes from a visual graph (and the visualization).
     * 
     * @param visualGraph
     *            The visual graph from which to remove the visual nodes.
     */
    public void clearVisualNodesFromVisualGraph(VisualGraph visualGraph) {

        Set<String> visualNodeIDs = new LinkedHashSet<>(visualGraph
                .getVisualNodeMap().keySet());

        for (String visualNodeID : visualNodeIDs) {

            this.visualNodeRemover(visualNodeID);
        }

        visualGraph.clearVisualNodeMap();
        visualGraph.clearVisualEdgeMap();
    }

    /**
     * Creates a new visual node for the provided node and adds it to the visual
     * graph with the id provided.
     * 
     * If a visual node for the node provided is already contained in the
     * visualization, the existing visual node is retrieved.
     * 
     * @param node
     *            The node to be visualized.
     * @param visualGraphID
     *            The id of the visual graph to contain the visual node.
     * 
     * @return A visual node.
     */
    public VisualNode createVisualNode(Node node, String visualGraphID) {

        if (!this.visualGraphMap.containsKey(visualGraphID)) {

            throw new IllegalArgumentException(
                    "Visualization does not contain visual graph.");
        }

        if (node == null) {
            throw new IllegalArgumentException("Cannot create visual "
                    + "representation without existing node.");
        }

        VisualGraph visualGraph = this.visualGraphMap.get(visualGraphID);

        return this.visualNodeCreator(node, visualGraph);
    }

    /**
     * Creates a new visual node for the provided node and adds it to the visual
     * graph provided.
     * 
     * If a visual node for the node provided is already contained in the
     * visualization, the existing visual node is retrieved.
     * 
     * @param node
     *            The node to be visualized.
     * @param visualGraph
     *            The graph to contain this node.
     * 
     * @return A visual node.
     */
    public VisualNode createVisualNode(Node node, VisualGraph visualGraph) {


        if (!this.visualGraphMap.containsValue(visualGraph)) {

            throw new IllegalArgumentException(
                    "Visualization does not contain visual graph.");
        }

        if (node == null) {

            throw new IllegalArgumentException("Cannot create visual "
                    + "representation without existing node.");
        }

        return this.visualNodeCreator(node, visualGraph);
    }

    /**
     * Removes the visual node associated with the provided node from the visual
     * graph.
     * 
     * @param node
     *            The node associated with the visual node.
     */
    public void removeVisualNode(Node node) {

        String visualNodeID = Visualization.createIdentifier(node, false);

        if (this.visualNodeMap.containsKey(visualNodeID)) {

            this.visualNodeRemover(visualNodeID);
        }
    }

    /**
     * Removes a visual node from the visualization.
     * 
     * @param visualNode
     *            The visual node to be removed.
     */
    public void removeVisualNode(VisualNode visualNode) {

        if (this.visualNodeMap.containsValue(visualNode)) {

            this.visualNodeRemover(visualNode.getID());
        }

    }

    /**
     * Returns the visual node associated with the provided node.
     * 
     * @param node
     *            The node associated with the visual node.
     * @return The visual node, if found. Otherwise null.
     */
    public VisualNode getVisualNode(Node node) {

        String visualNodeID = Visualization.createIdentifier(node, false);
        return this.getVisualNode(visualNodeID);
    }

    /**
     * Returns the visual node with the provided ID.
     * 
     * @param visualNodeID
     *            The ID of the visual node.
     * @return The visual node, if found. Otherwise null.
     */
    public VisualNode getVisualNode(String visualNodeID) {

        return this.visualNodeMap.get(visualNodeID);
    }

    /**
     * Removes all visual edges from the visualization.
     */
    public void clearVisualEdges() {

        Set<String> visualEdgeIDs = new LinkedHashSet<>(
                this.visualEdgeMap.keySet());
        for (String visualEdgeID : visualEdgeIDs) {

            this.visualEdgeRemover(visualEdgeID);
        }

        this.visualEdgeMap.clear();
    }

    /**
     * Removes all visual edges from a visual graph (as well as its visual nodes
     * and the visualization).
     * 
     * @param visualGraph
     *            The visual graph from which to remove the visual edges.
     */
    public void clearVisualEdgesFromVisualGraph(VisualGraph visualGraph) {

        Set<String> visualEdgeIDs = new LinkedHashSet<>(visualGraph
                .getVisualEdgeMap().keySet());

        for (String visualEdgeID : visualEdgeIDs) {

            this.visualEdgeRemover(visualEdgeID);
        }

        visualGraph.clearVisualEdgeMap();
    }

    /**
     * Removes all visual edges from a visual node (as well as its visual graph
     * and the visualization).
     * 
     * @param visualNode
     *            The visual node from which to remove the visual edges.
     */
    public void clearVisualEdgesFromVisualNode(VisualNode visualNode) {

        Set<VisualEdge> visualEdges = new LinkedHashSet<>(
                visualNode.getVisualEdges());

        for (VisualEdge visualEdge : visualEdges) {

            this.visualEdgeRemover(visualEdge.getID());
        }

        visualNode.clearVisualEdges();
    }

    /**
     * Creates a visual edge for the edge provided , then adds it to the visual
     * graph.
     * 
     * If a visual edge with the exact parameters provided already exists in the
     * graph, that visual edge will be returned.
     * 
     * @param edge
     *            The edge to visualize.
     * @param visualGraphID
     *            The visual graph that will contain the visual edge.
     * @return A visual edge.
     */
    public VisualEdge createVisualEdge(Edge edge, String visualGraphID) {

        String visualSourceNodeID = Visualization.createIdentifier(
                edge.getSource(), false);
        String visualTargetNodeID = Visualization.createIdentifier(
                edge.getTarget(), false);

        if (!this.visualNodeMap.containsKey(visualSourceNodeID)) {

            throw new IllegalArgumentException(
                    "Visualization does not contain source node.");
        }

        if (!this.visualNodeMap.containsKey(visualTargetNodeID)) {

            throw new IllegalArgumentException(
                    "Visualization does not contain target node.");
        }

        if (!this.visualGraphMap.containsKey(visualGraphID)) {
            throw new IllegalArgumentException(
                    "Visualization does not contain visual graph.");
        }

        VisualGraph visualGraph = this.visualGraphMap.get(visualGraphID);

        return this.visualEdgeCreator(edge, visualGraph);
    }

    /**
     * Creates a visual edge for the edge provided , then adds it to the
     * provided visual graph.
     * 
     * If a visual edge with the exact parameters provided already exists in the
     * graph, that edge will be returned.
     * 
     * @param edge
     *            The edge to visualize.
     * @param visualGraph
     *            The visual graph that is to contain the visual edge.
     * 
     * @return A visual edge.
     */
    public VisualEdge createVisualEdge(Edge edge, VisualGraph visualGraph) {

        String visualSourceNodeID = Visualization.createIdentifier(
                edge.getSource(), false);
        String visualTargetNodeID = Visualization.createIdentifier(
                edge.getTarget(), false);

        if (!this.visualNodeMap.containsKey(visualSourceNodeID)) {
            throw new IllegalArgumentException(
                    "Universe does not contain source node.");
        }

        if (!this.visualNodeMap.containsKey(visualTargetNodeID)) {
            throw new IllegalArgumentException(
                    "Universe does not contain target node "
                            + visualTargetNodeID + "!");
        }

        if (!this.visualGraphMap.containsValue(visualGraph)) {

            throw new IllegalArgumentException(
                    "Universe does not contain graph.");
        }

        return this.visualEdgeCreator(edge, visualGraph);
    }

    /**
     * Removes the visual edge associated with the provided edge from the visual
     * graph.
     * 
     * @param edge
     *            The edge to be removed.
     */
    public void removeVisualEdge(Edge edge) {

        String visualEdgeID = Visualization.createIdentifier(edge, false);

        if (visualEdgeMap.containsKey(visualEdgeID)) {

            this.visualEdgeRemover(visualEdgeID);
        }
    }

    /**
     * Removes the provided visual edge from the visualization.
     * 
     * @param visualEdge
     *            The edge to be removed.
     */
    public void removeVisualEdge(VisualEdge visualEdge) {

        if (this.visualEdgeMap.containsValue(visualEdge)) {

            this.visualEdgeRemover(visualEdge.getID());
        }
    }

    /**
     * Returns the visual edge containing the provided edge.
     * 
     * @param edge
     *            The edge to be found.
     * @return The visual edge, if it is found. Otherwise null.
     */
    public VisualEdge getVisualEdge(Edge edge) {

        String visualEdgeID = Visualization.createIdentifier(edge, false);

        return this.getVisualEdge(visualEdgeID);
    }

    /**
     * Returns the visual edge with the provided ID.
     * 
     * @param visualEdgeID
     *            The ID of the visual edge.
     * @return The visual edge, if it is found. Otherwise null.
     */
    public VisualEdge getVisualEdge(String visualEdgeID) {

        return this.visualEdgeMap.get(visualEdgeID);
    }

    /**
     * Removes all visual hyperedges from the visualization.
     */
    public void clearVisualHyperEdges() {

        Set<String> visualHyperEdgeIDs = new LinkedHashSet<>(
                this.visualHyperEdgeMap.keySet());

        for (String visualHyperEdgeID : visualHyperEdgeIDs) {

            this.visualHyperEdgeRemover(visualHyperEdgeID);
        }

        this.visualHyperEdgeMap.clear();
    }

    /**
     * Removes all visual hyperedges from a visual graph (as well as its visual
     * nodes and the visualization).
     * 
     * @param visualGraph
     *            The visual graph from which to remove the visual hyperedges.
     */
    public void clearVisualHyperEdgesFromVisualGraph(VisualGraph visualGraph) {

        Set<String> visualHyperEdgeIDs = new LinkedHashSet<>(visualGraph
                .getVisualHyperEdgeMap().keySet());

        for (String visualHyperEdgeID : visualHyperEdgeIDs) {

            this.visualHyperEdgeRemover(visualHyperEdgeID);
        }

        visualGraph.clearVisualHyperEdgeMap();
    }

    /**
     * Removes all visual hyperedges from a visual node (as well as its visual
     * graph and the visualization, if applicable).
     * 
     * @param visualNode
     *            The visual node from which to remove the visual hyperedges.
     */
    public void clearVisualHyperEdgesFromVisualNode(VisualNode visualNode) {

        Set<VisualHyperEdge> visualHyperEdges = new LinkedHashSet<>(
                visualNode.getVisualHyperEdges());

        for (VisualHyperEdge visualHyperEdge : visualHyperEdges) {

            if (visualHyperEdge.getVisualNodes().size() < 3) {

                this.visualHyperEdgeRemover(visualHyperEdge.getID());
            }
            else {

                visualHyperEdge.removeVisualNode(visualNode);
            }
        }

        visualNode.clearVisualHyperEdges();
    }


    /**
     * Creates a visual hyperedge and adds it to the graph.
     * 
     * If a visual hyperedge with the exact parameters provided already exists
     * in the visual graph, that visual hyperedge will be returned.
     * 
     * 
     * @param hyperEdge
     *            The hyperedge to visualize.
     * @param visualGraphID
     *            The unique identifier of the visual graph to contain the
     *            visual hyperedge.
     * 
     * @return A visual hyperedge.
     */
    public VisualHyperEdge createVisualHyperEdge(HyperEdge hyperEdge,
            String visualGraphID) {

        for (Node node : hyperEdge.getNodes()) {

            String visualNodeID = Visualization.createIdentifier(node, false);

            if (!this.visualNodeMap.containsKey(visualNodeID)) {

                throw new IllegalArgumentException(
                        "Visualization does not contain visual node "
                                + visualNodeID + ".");
            }
        }

        if (!this.visualGraphMap.containsKey(visualGraphID)) {

            throw new IllegalArgumentException(
                    "Visualization does not contain visual graph "
                            + visualGraphID + ".");
        }

        VisualGraph visualGraph = this.visualGraphMap.get(visualGraphID);


        return this.visualHyperEdgeCreator(hyperEdge, visualGraph);
    }

    /**
     * Creates a visual hyperedge and adds it to the visual graph.
     * 
     * If a visual hyperedge with the exact parameters provided already exists
     * in the visual graph, that visual hyperedge will be returned.
     * 
     * 
     * @param hyperEdge
     *            The hyperedge to visualize.
     * @param visualGraph
     *            The visual graph to contain the visual hyperedge.
     * 
     * @return A visual hyperedge.
     */
    public VisualHyperEdge createVisualHyperEdge(HyperEdge hyperEdge,
            VisualGraph visualGraph) {

        for (Node node : hyperEdge.getNodes()) {

            String visualNodeID = Visualization.createIdentifier(node, false);

            if (!this.visualNodeMap.containsKey(visualNodeID)) {

                throw new IllegalArgumentException(
                        "Visualization does not contain visual node "
                                + visualNodeID + "!");
            }
        }

        if (!this.visualGraphMap.containsValue(visualGraph)) {

            throw new IllegalArgumentException(
                    "Visualization does not contain visual graph "
                            + visualGraph.getID() + "!");
        }


        return this.visualHyperEdgeCreator(hyperEdge, visualGraph);
    }

    /**
     * Removes the visual hyperEdge associated with the provided hyperEdge from
     * the visualization.
     * 
     * @param hyperEdge
     *            The hyperEdge to be removed.
     */
    public void removeVisualHyperEdge(HyperEdge hyperEdge) {

        String visualHyperEdgeID = Visualization.createIdentifier(hyperEdge,
                false);

        if (this.visualHyperEdgeMap.containsKey(visualHyperEdgeID)) {

            this.visualHyperEdgeRemover(visualHyperEdgeID);
        }
    }

    /**
     * Removes the provided visual hyperedge from the visualization.
     * 
     * @param visualHyperEdge
     *            The visual hyperEdge to be removed.
     */
    public void removeVisualHyperEdge(VisualHyperEdge visualHyperEdge) {

        if (this.visualHyperEdgeMap.containsValue(visualHyperEdge)) {

            this.visualHyperEdgeRemover(visualHyperEdge.getID());
        }
    }


    /**
     * Returns the visual hyperEdge associated with the provided hyperEdge.
     * 
     * @param hyperEdge
     *            The hyperEdge.
     * @return The visual hyperEdge, if it is found. Otherwise null.
     */
    public VisualHyperEdge getVisualHyperEdge(HyperEdge hyperEdge) {

        String visualHyperEdgeID = Visualization.createIdentifier(hyperEdge,
                false);

        return this.getVisualHyperEdge(visualHyperEdgeID);
    }

    /**
     * Returns the visual hyperEdge with the provided ID.
     * 
     * @param visualHyperEdgeID
     *            The ID of the visual hyperEdge.
     * @return The visual hyperEdge, if it is found. Otherwise null.
     */
    public VisualHyperEdge getVisualHyperEdge(String visualHyperEdgeID) {

        return this.visualHyperEdgeMap.get(visualHyperEdgeID);
    }

    /**
     * Helper method for the removal of visual graphs.
     * 
     * This method removes all the visual edges and visual nodes of the visual
     * graph.
     * 
     * Finally, the visual graph itself is removed from the visualization.
     * 
     * @param visualGraphID
     *            the ID of the visual graph.
     */
    private void visualGraphRemover(String visualGraphID) {

        VisualGraph visualGraph = this.visualGraphMap.get(visualGraphID);

        // clears all nodes from the graph (and, as a side-effect, also all
        // edges and hyperedges
        this.clearVisualNodesFromVisualGraph(visualGraph);


        // Remove from the universe
        this.visualGraphMap.remove(visualGraphID);
    }


    /**
     * Helper method for the creation of visual nodes.
     * 
     * It checks whether a visual node associated with the provided node exists.
     * If so, it checks for the equality of the parameters and returns the found
     * visual node if they are equal. Otherwise, it throws an exception.
     * 
     * In case the visual node is not yet contained in the visualization, the
     * visual node is created, added to the visual graph, and then returned.
     * 
     * @param node
     *            The node to visualize.
     * @param visualGraph
     *            The visual graph that is to contain the visual node.
     * @return The created (or existing) visual node.
     */
    private VisualNode visualNodeCreator(Node node, VisualGraph visualGraph) {

        String visualNodeID = Visualization.createIdentifier(node, false);


        if (this.visualNodeMap.containsKey(visualNodeID)) {

            VisualNode containedNode = visualNodeMap.get(visualNodeID);

            if (containedNode.getVisualGraph().equals(visualGraph)) {

                return containedNode;
            }
            else {

                throw new IllegalArgumentException(
                        "A visual node with the id \"" + visualNodeID
                                + "\" already exists in another graph.");
            }
        }

        if (!node.equals(visualGraph.getGraph().getNode(node.getID()))) {

            throw new IllegalArgumentException(
                    "Graph associated with the provided visual graph does not "
                            + "contain provided node.");
        }

        VisualNode visualNode = new VisualNode(node, visualGraph,
                this.getPrecision());


        // Add to visualization
        this.visualNodeMap.put(visualNodeID, visualNode);

        // Add to visual graph
        visualGraph.addVisualNode(visualNode);


        return visualNode;
    }


    /**
     * Helper method for the removal of visual nodes.
     * 
     * This method goes through all the visual edges of the visual node and
     * removes them.
     * 
     * It commences to go through all visual hyperedges, checking whether the
     * visual hyperedge would have only one visual node if the visual node is
     * removed. It removes the visual node from the visual hyperedge if that is
     * not the case, otherwise the visual hyperedge is removed.
     * 
     * Finally, the visual node itself is removed from its visual graph and the
     * visualization.
     * 
     * @param visualNodeID
     *            The unique identifier of the visual node.
     */
    private void visualNodeRemover(String visualNodeID) {

        VisualNode visualNode = this.visualNodeMap.get(visualNodeID);

        // Remove all edges of this node
        Set<VisualEdge> visualEdges = visualNode.getVisualEdges();
        for (VisualEdge visualEdge : visualEdges) {

            this.removeVisualEdge(visualEdge);
        }

        // Removes the visual node'scale involvement in any visual hyperedges.
        // Should a visual hyperedge have only one visual node as a result, the
        // visual hyperedge is removed as well.
        Set<VisualHyperEdge> visualHyperedges = visualNode
                .getVisualHyperEdges();
        for (VisualHyperEdge visualHyperEdge : visualHyperedges) {

            if (visualHyperEdge.getVisualNodes().size() < 3) {

                this.removeVisualHyperEdge(visualHyperEdge);
            }
            else {

                visualHyperEdge.removeVisualNode(visualNode);
            }
        }

        // Delete it from the visual graph
        visualNode.getVisualGraph().deleteVisualNode(visualNode);

        // Removes it from the visualization
        this.visualNodeMap.remove(visualNodeID);

    }


    /**
     * Helper method for the creation of visualedges.
     * 
     * It checks whether a visual edge exists for the provided edge. If so, it
     * checks for the equality of the parameters and returns the found visual
     * edge if they are equal. Otherwise, it throws an exception.
     * 
     * In case the visual edge is not yet contained in the visual graph, the
     * visual edge is created, added to the visual graph, and then returned.
     * 
     * @param edge
     *            The the edge to visualize.
     * @param visualGraph
     *            The graph that contains the edge.
     * 
     * @return The created edge (or the existing edge).
     */
    private VisualEdge visualEdgeCreator(Edge edge, VisualGraph visualGraph) {

        String visualEdgeID = Visualization.createIdentifier(edge, false);

        if (this.visualEdgeMap.containsKey(visualEdgeID)) {

            return this.visualEdgeMap.get(visualEdgeID);
        }

        String visualSourceNodeID = Visualization.createIdentifier(
                edge.getSource(), false);
        // System.out.println("Edge: " + edge);
        VisualNode visualSourceNode = visualGraph
                .getVisualNode(visualSourceNodeID);
        if (visualSourceNode == null) {

            visualSourceNode = this.getVisualNode(visualSourceNodeID);
        }

        String visualTargetNodeID = Visualization.createIdentifier(
                edge.getTarget(), false);
        VisualNode visualTargetNode = visualGraph
                .getVisualNode(visualTargetNodeID);
        if (visualTargetNode == null) {

            visualTargetNode = this.getVisualNode(visualTargetNodeID);
        }

        // System.out.println("  Source: " + source + "\n  Target: " + target);

        if (!edge.equals(visualGraph.getGraph().getEdge(edge.getID()))) {

            throw new IllegalArgumentException(
                    "Graph associated with provided visual graph does not "
                            + "contain provided edge.");
        }

        if (visualSourceNode == null) {

            throw new IllegalArgumentException(
                    "Source node does not have visual representation.");
        }
        if (visualTargetNode == null) {

            throw new IllegalArgumentException(
                    "Target node does not have visual representation.");
        }


        VisualEdge visualEdge = new VisualEdge(edge, visualGraph,
                visualSourceNode, visualTargetNode, this.getPrecision());


        // Add to universe
        this.visualEdgeMap.put(visualEdgeID, visualEdge);

        // Add to graph
        visualGraph.addVisualEdge(visualEdge);

        // Add to nodes
        visualSourceNode.addEdge(visualEdge);
        visualTargetNode.addEdge(visualEdge);

        return visualEdge;
    }


    /**
     * Helper method for the removal of visual edges.
     * 
     * Removes any occurrence of the visual edge from any visual nodes, visual
     * graphs, and the visualization.
     * 
     * @param visualEdgeID
     *            The id of the visual edge to be removed
     */
    private void visualEdgeRemover(String visualEdgeID) {

        VisualEdge visualEdge = this.visualEdgeMap.get(visualEdgeID);

        VisualNode source = visualEdge.getVisualSourceNode();
        VisualNode target = visualEdge.getVisualTargetNode();

        source.deleteEdge(visualEdge);
        target.deleteEdge(visualEdge);

        // Delete it from the graph
        visualEdge.getVisualGraph().deleteVisualEdge(visualEdge);

        // Removes it from the universe
        this.visualEdgeMap.remove(visualEdgeID);

    }


    /**
     * Helper method for the creation of visual hyperedges.
     * 
     * It checks whether a visual hyperedge with the provided id exists. If so,
     * it checks for the equality of the parameters and returns the found visual
     * hyperedge if they are equal. Otherwise, it throws an exception.
     * 
     * In case the visual hyperedge is not yet contained in the visual graph,
     * the visual hyperedge is created, added to the visual graph, and then
     * returned.
     * 
     * @param hyperEdge
     *            The hyperedge to visualize.
     * @param visualGraph
     *            The visual graph that is to contain the visual hyperedge.
     * 
     * @return The created (or existing) visual hyperedge.
     */
    private VisualHyperEdge visualHyperEdgeCreator(HyperEdge hyperEdge,
            VisualGraph visualGraph) {

        String visualHyperEdgeID = Visualization.createIdentifier(hyperEdge,
                false);

        if (this.visualHyperEdgeMap.containsKey(visualHyperEdgeID)) {

            return this.visualHyperEdgeMap.get(visualHyperEdgeID);
        }

        Boolean containedInGraph = true;
        Set<VisualNode> visualNodes = new LinkedHashSet<>();

        for (Node node : hyperEdge.getNodes()) {

            String visualNodeID = Visualization.createIdentifier(node, false);

            if (!this.visualNodeMap.containsKey(visualNodeID)) {

                containedInGraph = false;
                break;
            }
            else {

                visualNodes.add(this.visualNodeMap.get(visualNodeID));
            }
        }


        if (!containedInGraph) {

            throw new IllegalArgumentException(
                    "Provided graph does not contain all of the provided nodes!");
        }


        VisualHyperEdge visualHyperEdge = new VisualHyperEdge(hyperEdge,
                visualGraph, visualNodes, this.getPrecision());


        // Add to visualization
        this.visualHyperEdgeMap.put(visualHyperEdgeID, visualHyperEdge);

        // Add to visual graph
        visualGraph.addVisualHyperEdge(visualHyperEdge);

        // Add to visual nodes
        for (VisualNode visualNode : visualNodes) {

            visualNode.addVisualHyperEdge(visualHyperEdge);
        }

        return visualHyperEdge;
    }


    /**
     * Helper method for the removal of visual hyperedges.
     * 
     * Removes any occurrence of the visual hyperedge from any visual nodes,
     * visual graphs, and the visualization.
     * 
     * @param visualHyperEdgeID
     *            The id of the visual hyperedge to be removed
     */
    private void visualHyperEdgeRemover(String visualHyperEdgeID) {

        VisualHyperEdge visualHyperedge = this.visualHyperEdgeMap
                .get(visualHyperEdgeID);

        for (VisualNode visualNode : visualHyperedge.getVisualNodes()) {

            visualNode.deleteHyperEdge(visualHyperedge);
        }

        // Delete it from the graph
        visualHyperedge.getVisualGraph().deleteVisualHyperEdge(visualHyperedge);

        // Removes it from the visualization
        this.visualHyperEdgeMap.remove(visualHyperEdgeID);

    }

    /**
     * Creates an identifier for the visual object to be associated with the
     * graph object.
     * 
     * @param graphObject
     *            the graph object to visualize.
     * @param serialization
     *            whether or not this method is called during serialization.
     * @return the new identifier or <code>null</code> during serialization.
     */
    public static String createIdentifier(final IGraphObject graphObject,
            final boolean serialization) {

        if (graphObject == null && !serialization) {

            throw new IllegalArgumentException(
                    "Could not determine identifier of graph object: Graph object provided is null.");
        }
        if (serialization) {

            return null;
        }
        else {

            return Visualization.PREFIX + graphObject.getID();
        }
    }

    /**
     * Determines whether or not this <code>Visualization</code> is identical to
     * the one provided.
     * 
     * @param other
     *            the other <code>Visualization</code>.
     * @return <code>true</code> if they are identical; <code>false</code>
     *         otherwise.
     */
    public boolean isIdentical(Visualization other) {

        if (this.equals(other)) {

            boolean identicalUniverse = this.getUniverse().isIdentical(
                    other.getUniverse());
            boolean sameNumberOfGraphs = this.getVisualGraphs().size() == other
                    .getVisualGraphs().size();
            boolean sameNumberOfNodes = this.getVisualNodes().size() == other
                    .getVisualNodes().size();
            boolean sameNumberOfEdges = this.getVisualEdges().size() == other
                    .getVisualEdges().size();
            boolean sameNumberOfHyperEdges = this.getVisualHyperEdges().size() == other
                    .getVisualHyperEdges().size();

            if (!(identicalUniverse && sameNumberOfGraphs && sameNumberOfNodes
                    && sameNumberOfEdges && sameNumberOfHyperEdges)) {
                return false;
            }

            for (VisualGraph graph : this.getVisualGraphs()) {

                boolean identical = false;
                for (VisualGraph otherGraph : other.getVisualGraphs()) {

                    identical = graph.isIdentical(otherGraph);
                    if (identical) {

                        break;
                    }
                }
                if (!identical) {

                    return false;
                }

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

        if (this == obj) {
            return true;
        }
        if (obj != null && obj instanceof Visualization) {
            Visualization other = (Visualization) obj;

            boolean sameID = this.getID() == other.getID();
            boolean sameUniverse = this.getUniverse().equals(
                    other.getUniverse());

            if (!sameID && this.getID() != null) {

                sameID = this.getID().equals(other.getID());
            }

            return sameID && sameUniverse;
        }

        return false;
    }

    @Override
    public int hashCode() {

        return this.getID().hashCode();
    }
}
