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


package edu.vt.arc.vis.osnap.graph.base;


import edu.vt.arc.vis.osnap.graph.Edge;
import edu.vt.arc.vis.osnap.graph.Graph;
import edu.vt.arc.vis.osnap.graph.HyperEdge;
import edu.vt.arc.vis.osnap.graph.Node;
import edu.vt.arc.vis.osnap.graph.common.GraphObjectProperty;
import edu.vt.arc.vis.osnap.graph.common.IEdge;
import edu.vt.arc.vis.osnap.graph.common.IGraph;
import edu.vt.arc.vis.osnap.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.graph.common.IHyperEdge;
import edu.vt.arc.vis.osnap.graph.common.INode;
import edu.vt.arc.vis.osnap.graph.common.IUniverse;

import java.util.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

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
@XmlType(name = "GraphBase", propOrder = { "serializableNodes",
        "serializableEdges", "serializableHyperEdges" })
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({ Graph.class, NodeBase.class, Node.class, EdgeBase.class,
        Edge.class, HyperEdgeBase.class, HyperEdge.class })
public abstract class GraphBase
        extends GraphObjectBase
        implements IGraph {


    private IUniverse                     universe;

    private final Map<String, INode>      nodeMap;
    private final Map<String, IEdge>      edgeMap;
    private final Map<String, IHyperEdge> hyperEdgeMap;



    @Override
    public void setID(String id) {

        if (this.getUniverse().changeID(this, id)) {

            super.setID(id);
        }
    }

    /**
     * Getter for the node map.
     * 
     * The node map maps a node'scale id to the actual node for quick access.
     * 
     * @return The node map.
     */
    public Map<String, INode> getNodeMap() {

        return Collections.unmodifiableMap(this.nodeMap);
    }

    /**
     * Getter for the edge map.
     * 
     * The edge map maps an edge'scale id to the actual edge for quick access.
     * 
     * @return The edge map.
     */
    public Map<String, IEdge> getEdgeMap() {

        return Collections.unmodifiableMap(this.edgeMap);
    }

    /**
     * Getter for the hyperEdge map.
     * 
     * The edge map maps an hyperEdge'scale id to the actual hyperedge for quick
     * access.
     * 
     * @return The hyperEdge map.
     */
    public Map<String, IHyperEdge> getHyperEdgeMap() {

        return Collections.unmodifiableMap(this.hyperEdgeMap);
    }

    @SuppressWarnings("unused")
    private GraphBase() {

        this(null, null, true);
    }

    /**
     * Creates a new instance of the {@link GraphBase} class.
     * 
     * The graph created contains no {@link INode nodes}, {@link IEdge edges},
     * or {@link IHyperEdge hyperedges}.
     * 
     * @param id
     *            The graph'scale unique identifier.
     * @param universe
     *            The {@link IUniverse universe} the {@link GraphBase graph}
     *            belongs to.
     */
    protected GraphBase(String id, IUniverse universe) {

        this(id, universe, false);
    }

    /**
     * Creates a new instance of the {@link GraphBase} class.
     * 
     * The graph created contains no {@link INode nodes}, {@link IEdge edges},
     * or {@link IHyperEdge hyperedges}.
     * 
     * @param id
     *            The graph'scale unique identifier.
     * @param universe
     *            The {@link IUniverse universe} the {@link GraphBase graph}
     *            belongs to.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    protected GraphBase(String id, IUniverse universe, boolean serialization) {

        super(id, serialization);

        this.universe = universe;


        this.nodeMap = new LinkedHashMap<>();
        this.edgeMap = new LinkedHashMap<>();
        this.hyperEdgeMap = new LinkedHashMap<>();
    }


    /**
     * Sets the universe that contains this graph.
     * 
     * @param universe
     *            the universe.
     */
    protected void setUniverse(IUniverse universe) {

        this.universe = universe;
    }

    /**
     * Adds a node.
     * 
     * @param node
     *            the node to add.
     */
    protected void addNode(INode node) {

        if (node != null) {
            this.nodeMap.put(node.getID(), node);
        }
    }

    /**
     * Deletes a node
     * 
     * @param node
     *            the node to delete.
     */
    protected void deleteNode(INode node) {

        if (node != null) {
            this.nodeMap.remove(node.getID());
        }
    }

    /**
     * Clears the nodes.
     */
    protected void clearNodeMap() {

        this.nodeMap.clear();
    }

    /**
     * Adds an edge.
     * 
     * @param edge
     *            the edge to add.
     */
    protected void addEdge(IEdge edge) {

        if (edge != null) {
            this.edgeMap.put(edge.getID(), edge);
        }
    }

    /**
     * Deletes an edge.
     * 
     * @param edge
     *            the edge to delete.
     */
    protected void deleteEdge(IEdge edge) {

        if (edge != null) {
            this.edgeMap.remove(edge.getID());
        }
    }

    /**
     * Clears the edges.
     */
    protected void clearEdgeMap() {

        this.edgeMap.clear();
    }

    /**
     * Adds a hyperedge.
     * 
     * @param hyperedge
     *            the hyperedge to add.
     */
    protected void addHyperEdge(IHyperEdge hyperedge) {

        if (hyperedge != null) {
            this.hyperEdgeMap.put(hyperedge.getID(), hyperedge);
        }
    }

    /**
     * Deletes a hyperedge.
     * 
     * @param hyperedge
     *            the hyperedge to delete.
     */
    protected void deleteHyperEdge(IHyperEdge hyperedge) {

        if (hyperedge != null) {
            this.hyperEdgeMap.remove(hyperedge.getID());
        }
    }

    /**
     * Clears the hyperedges.
     */
    protected void clearHyperEdgeMap() {

        this.hyperEdgeMap.clear();
    }


    /**
     * Getter for the nodes.
     * 
     * @return An unmodifiable collection of the nodes.
     */
    @Override
    public Collection<? extends INode> getNodes() {

        return new LinkedList<>(Collections.unmodifiableCollection(this.nodeMap
                .values()));
    }

    @XmlIDREF
    @XmlElements({ @XmlElement(name = "Node", type = Node.class) })
    @XmlElementWrapper(name = "Nodes")
    private Collection<? extends INode> getSerializableNodes() {

        return new ListMapWrapper<>(this.nodeMap, "getID", String.class);
    }


    /**
     * Getter for the edges.
     * 
     * 
     * @return An unmodifiable collection of the edges.
     */
    @Override
    public Collection<? extends IEdge> getEdges() {

        return new LinkedList<>(Collections.unmodifiableCollection(this.edgeMap
                .values()));
    }

    @XmlIDREF
    @XmlElements({ @XmlElement(name = "Edge", type = Edge.class) })
    @XmlElementWrapper(name = "Edges")
    private Collection<? extends IEdge> getSerializableEdges() {

        return new ListMapWrapper<>(this.edgeMap, "getID", String.class);
    }

    /**
     * Getter for the hyperedges.
     * 
     * 
     * @return An unmodifiable collection of the hyperedges.
     */
    @Override
    public Collection<? extends IHyperEdge> getHyperEdges() {

        return new LinkedList<>(
                Collections.unmodifiableCollection(this.hyperEdgeMap.values()));
    }


    @XmlIDREF
    @XmlElements({ @XmlElement(name = "HyperEdge", type = HyperEdge.class) })
    @XmlElementWrapper(name = "HyperEdges")
    private Collection<? extends IHyperEdge> getSerializableHyperEdges() {

        return new ListMapWrapper<>(this.hyperEdgeMap, "getID", String.class);
    }

    @Override
    public IEdge getEdge(String id) {

        return this.edgeMap.get(id);
    }

    @Override
    public IHyperEdge getHyperEdge(String id) {

        return this.hyperEdgeMap.get(id);
    }

    @Override
    public INode getNode(String id) {

        return this.nodeMap.get(id);
    }

    @Override
    public long maxDegree() {

        long edgeDegree = this.maxDegreeEdges();
        long hyperEdgeDegree = this.maxDegreeHyperEdges();

        if (edgeDegree > hyperEdgeDegree) {
            return edgeDegree;
        }
        else {
            return hyperEdgeDegree;
        }
    }

    @Override
    public long maxDegreeEdges() {

        long max = 0L;

        for (INode node : this.getNodes()) {

            long nodeDegree = node.edgeDegree();
            if (nodeDegree > max) {

                max = nodeDegree;
            }
        }

        return max;
    }

    @Override
    public long maxDegreeHyperEdges() {

        long max = 0L;

        for (INode node : this.getNodes()) {

            long nodeDegree = node.hyperEdgeDegree();
            if (nodeDegree > max) {

                max = nodeDegree;
            }
        }

        return max;
    }

    @Override
    public long maxInDegree() {

        long edgeDegree = this.maxInDegreeEdges();
        long hyperEdgeDegree = this.maxInDegreeHyperEdges();

        if (edgeDegree > hyperEdgeDegree) {
            return edgeDegree;
        }
        else {
            return hyperEdgeDegree;
        }
    }

    @Override
    public long maxInDegreeEdges() {

        long max = 0L;

        for (INode node : this.getNodes()) {
            long nodeDegree = node.edgeInDegree();
            if (nodeDegree > max) {
                max = nodeDegree;
            }
        }

        return max;
    }

    @Override
    public long maxInDegreeHyperEdges() {

        long max = 0L;

        for (INode node : this.getNodes()) {
            long nodeDegree = node.hyperEdgeInDegree();
            if (nodeDegree > max) {
                max = nodeDegree;
            }
        }

        return max;
    }

    @Override
    public long maxOutDegree() {

        long edgeDegree = this.maxOutDegreeEdges();
        long hyperEdgeDegree = this.maxOutDegreeHyperEdges();

        if (edgeDegree > hyperEdgeDegree) {
            return edgeDegree;
        }
        else {
            return hyperEdgeDegree;
        }
    }

    @Override
    public long maxOutDegreeEdges() {

        long max = 0L;

        for (INode node : this.getNodes()) {
            long nodeDegree = node.edgeOutDegree();
            if (nodeDegree > max) {
                max = nodeDegree;
            }
        }

        return max;
    }

    @Override
    public long maxOutDegreeHyperEdges() {

        long max = 0L;

        for (INode node : this.getNodes()) {
            long nodeDegree = node.hyperEdgeOutDegree();
            if (nodeDegree > max) {
                max = nodeDegree;
            }
        }

        return max;
    }

    @Override
    public long minDegree() {

        long edgeDegree = this.minDegreeEdges();
        long hyperEdgeDegree = this.minDegreeHyperEdges();

        if (edgeDegree < hyperEdgeDegree) {
            return edgeDegree;
        }
        else {
            return hyperEdgeDegree;
        }
    }

    @Override
    public long minDegreeEdges() {

        long min = Long.MAX_VALUE;

        if (this.getNodes().isEmpty()) {

            return 0L;
        }
        for (INode node : this.getNodes()) {
            long nodeDegree = node.edgeDegree();
            if (nodeDegree < min) {
                min = nodeDegree;
            }
        }

        return min;
    }

    @Override
    public long minDegreeHyperEdges() {

        long min = Long.MAX_VALUE;

        if (this.getNodes().isEmpty()) {

            return 0L;
        }
        for (INode node : this.getNodes()) {
            long nodeDegree = node.hyperEdgeDegree();
            if (nodeDegree < min) {
                min = nodeDegree;
            }
        }


        return min;
    }

    @Override
    public long minInDegree() {

        long edgeDegree = this.minInDegreeEdges();
        long hyperEdgeDegree = this.minInDegreeHyperEdges();

        if (edgeDegree < hyperEdgeDegree) {
            return edgeDegree;
        }
        else {
            return hyperEdgeDegree;
        }
    }

    @Override
    public long minInDegreeEdges() {

        long min = Long.MAX_VALUE;

        if (this.getNodes().isEmpty()) {

            return 0L;
        }
        for (INode node : this.getNodes()) {
            long nodeDegree = node.edgeInDegree();
            if (nodeDegree < min) {
                min = nodeDegree;
            }
        }

        return min;
    }

    @Override
    public long minInDegreeHyperEdges() {

        long min = Long.MAX_VALUE;
        if (this.getNodes().isEmpty()) {

            return 0L;
        }
        for (INode node : this.getNodes()) {
            long nodeDegree = node.hyperEdgeInDegree();
            if (nodeDegree < min) {
                min = nodeDegree;
            }
        }

        return min;
    }

    @Override
    public long minOutDegree() {

        long edgeDegree = this.minOutDegreeEdges();
        long hyperEdgeDegree = this.minOutDegreeHyperEdges();

        if (edgeDegree < hyperEdgeDegree) {
            return edgeDegree;
        }
        else {
            return hyperEdgeDegree;
        }
    }

    @Override
    public long minOutDegreeEdges() {

        long min = Long.MAX_VALUE;
        if (this.getNodes().isEmpty()) {

            return 0L;
        }
        for (INode node : this.getNodes()) {
            long nodeDegree = node.edgeOutDegree();
            if (nodeDegree < min) {
                min = nodeDegree;
            }
        }

        return min;
    }

    @Override
    public long minOutDegreeHyperEdges() {

        long min = Long.MAX_VALUE;
        if (this.getNodes().isEmpty()) {

            return 0L;
        }
        for (INode node : this.getNodes()) {
            long nodeDegree = node.hyperEdgeOutDegree();
            if (nodeDegree < min) {
                min = nodeDegree;
            }
        }

        return min;
    }

    @Override
    public long order() {

        return this.nodeMap.size();
    }

    @Override
    public long size() {

        return this.sizeEdges() + this.sizeHyperEdges();
    }

    @Override
    public long sizeEdges() {

        return this.edgeMap.size();
    }

    @Override
    public long sizeHyperEdges() {

        return this.hyperEdgeMap.size();
    }

    @Override
    public long rank() {

        long rank = 0L;

        if (!this.edgeMap.isEmpty()) {
            rank = 2L;
        }
        for (IHyperEdge hyperEdge : this.getHyperEdges()) {
            if (hyperEdge.cardinality() > rank) {
                rank = hyperEdge.cardinality();
            }
        }

        return rank;
    }

    @Override
    public Boolean isUniform() {

        return (this.uniformK() < 0L);
    }

    @Override
    public long uniformK() {

        long uniformK = -2L;

        if (!this.edgeMap.isEmpty()) {
            uniformK = 2L;
        }

        for (IHyperEdge hyperEdge : this.getHyperEdges()) {
            if (uniformK == -2L) {
                uniformK = hyperEdge.cardinality();
            }
            else if (hyperEdge.cardinality() != uniformK) {
                return -1L;
            }
        }

        return uniformK;
    }

    @Override
    public Boolean isRegular() {

        return (this.regularK() < 0L);
    }

    @Override
    public long regularK() {

        long regularK = -2L;

        for (INode node : this.getNodes()) {
            if (regularK == -2L) {
                regularK = node.degree();
            }
            else if (node.degree() != regularK) {
                return -1L;
            }
        }

        return regularK;
    }


    @Override
    public Set<GraphObjectProperty> hasGraphProperties() {

        return Collections.unmodifiableSet(EnumSet.of(GraphObjectProperty.SIZE,
                GraphObjectProperty.SIZE_EDGES,
                GraphObjectProperty.SIZE_HYPEREDGES, GraphObjectProperty.ORDER,
                GraphObjectProperty.RANK, GraphObjectProperty.MAX_DEGREE,
                GraphObjectProperty.MAX_DEGREE_EDGES,
                GraphObjectProperty.MAX_DEGREE_HYPEREDGES,
                GraphObjectProperty.MAX_IN_DEGREE,
                GraphObjectProperty.MAX_IN_DEGREE_EDGES,
                GraphObjectProperty.MAX_IN_DEGREE_HYPEREDGES,
                GraphObjectProperty.MAX_OUT_DEGREE,
                GraphObjectProperty.MAX_OUT_DEGREE_EDGES,
                GraphObjectProperty.MAX_OUT_DEGREE_HYPEREDGES,
                GraphObjectProperty.MIN_DEGREE,
                GraphObjectProperty.MIN_DEGREE_EDGES,
                GraphObjectProperty.MIN_DEGREE_HYPEREDGES,
                GraphObjectProperty.MIN_IN_DEGREE,
                GraphObjectProperty.MIN_IN_DEGREE_EDGES,
                GraphObjectProperty.MIN_IN_DEGREE_HYPEREDGES,
                GraphObjectProperty.MIN_OUT_DEGREE,
                GraphObjectProperty.MIN_OUT_DEGREE_EDGES,
                GraphObjectProperty.MIN_OUT_DEGREE_HYPEREDGES,
                GraphObjectProperty.UNIFORM, GraphObjectProperty.UNIFORM_K,
                GraphObjectProperty.REGULAR, GraphObjectProperty.REGULAR_K));
    }


    @Override
    public Object getValueOfGraphProperty(
            GraphObjectProperty graphObjectProperty) {

        switch (graphObjectProperty) {
            case SIZE:
                return this.size();
            case SIZE_EDGES:
                return this.sizeEdges();
            case SIZE_HYPEREDGES:
                return this.sizeHyperEdges();
            case ORDER:
                return this.order();
            case RANK:
                return this.rank();
            case MAX_DEGREE:
                return this.maxDegree();
            case MAX_DEGREE_EDGES:
                return this.maxDegreeEdges();
            case MAX_DEGREE_HYPEREDGES:
                return this.maxDegreeHyperEdges();
            case MAX_IN_DEGREE:
                return this.maxInDegree();
            case MAX_IN_DEGREE_EDGES:
                return this.maxInDegreeEdges();
            case MAX_IN_DEGREE_HYPEREDGES:
                return this.maxInDegreeHyperEdges();
            case MAX_OUT_DEGREE:
                return this.maxOutDegree();
            case MAX_OUT_DEGREE_EDGES:
                return this.maxOutDegreeEdges();
            case MAX_OUT_DEGREE_HYPEREDGES:
                return this.maxOutDegreeHyperEdges();
            case MIN_DEGREE:
                return this.minDegree();
            case MIN_DEGREE_EDGES:
                return this.minDegreeEdges();
            case MIN_DEGREE_HYPEREDGES:
                return this.minDegreeHyperEdges();
            case MIN_IN_DEGREE:
                return this.minInDegree();
            case MIN_IN_DEGREE_EDGES:
                return this.minInDegreeEdges();
            case MIN_IN_DEGREE_HYPEREDGES:
                return this.minInDegreeHyperEdges();
            case MIN_OUT_DEGREE:
                return this.minOutDegree();
            case MIN_OUT_DEGREE_EDGES:
                return this.minOutDegreeEdges();
            case MIN_OUT_DEGREE_HYPEREDGES:
                return this.minOutDegreeHyperEdges();
            case UNIFORM:
                return this.isUniform();
            case UNIFORM_K:
                return this.uniformK();
            case REGULAR:
                return this.isRegular();
            case REGULAR_K:
                return this.regularK();
            default:
                throw new UnsupportedOperationException("GraphObjectProperty "
                        + graphObjectProperty
                        + " is not supported by this graph.");

        }
    }


    @Override
    public IUniverse getUniverse() {

        return this.universe;
    }

    @Override
    public boolean isIdentical(IGraphObject other) {


        if (other instanceof IGraph) {

            IGraph otherGraph = (IGraph) other;


            boolean sameNumberOfNodes = this.getNodes().size() == otherGraph
                    .getNodes().size();
            boolean sameNumberOfEdges = this.getEdges().size() == otherGraph
                    .getEdges().size();
            boolean sameNumberOfHyperEdges = this.getHyperEdges().size() == otherGraph
                    .getHyperEdges().size();

            if (!(sameNumberOfNodes && sameNumberOfEdges && sameNumberOfHyperEdges)) {
                return false;
            }

            for (INode node : this.getNodes()) {

                boolean identical = false;
                for (INode otherNode : otherGraph.getNodes()) {

                    identical = node.isIdentical(otherNode);
                    if (identical) {

                        break;
                    }
                }
                if (!identical) {

                    return false;
                }

            }


            for (IEdge edge : this.getEdges()) {

                boolean identical = false;
                for (IEdge otherEdge : otherGraph.getEdges()) {

                    identical = edge.isIdentical(otherEdge);
                    if (identical) {

                        break;
                    }
                }
                if (!identical) {

                    return false;
                }

            }


            for (IHyperEdge hyperedge : this.getHyperEdges()) {

                boolean identical = false;
                for (IHyperEdge otherHyperEdge : otherGraph.getHyperEdges()) {

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
}
