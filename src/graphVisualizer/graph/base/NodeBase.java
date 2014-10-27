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


package graphVisualizer.graph.base;


import graphVisualizer.graph.Edge;
import graphVisualizer.graph.Graph;
import graphVisualizer.graph.HyperEdge;
import graphVisualizer.graph.Node;
import graphVisualizer.graph.common.IEndpoint;
import graphVisualizer.graph.common.IGraphObject;
import graphVisualizer.graph.common.INode;
import graphVisualizer.graph.common.IHyperEdge;
import graphVisualizer.graph.common.IEdge;
import graphVisualizer.graph.common.IGraph;
import graphVisualizer.graph.common.GraphObjectProperty;

import java.util.Collections;
import java.util.EnumSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * The abstract {@link NodeBase} class provides a base implementation of the
 * {@link INode} interface.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 * @see GraphBase
 * @see EdgeBase
 * @see HyperEdgeBase
 */
@XmlType(name = "NodeBase")
@XmlSeeAlso({ Node.class })
public abstract class NodeBase
        extends GraphObjectBase
        implements INode {


    @XmlIDREF
    @XmlElements({ @XmlElement(name = "Graph", type = Graph.class) })
    @XmlSchemaType(name = "IDREF")
    private List<IGraph>          graph;

    @XmlIDREF
    @XmlElementWrapper(name = "Edges")
    @XmlElements({ @XmlElement(name = "Edge", type = Edge.class) })
    @XmlSchemaType(name = "IDREF")
    private final Set<IEdge>      edges;

    @XmlIDREF
    @XmlElementWrapper(name = "HyperEdges")
    @XmlElements({ @XmlElement(name = "HyperEdge", type = HyperEdge.class) })
    @XmlSchemaType(name = "IDREF")
    private final Set<IHyperEdge> hyperedges;


    @Override
    public void setID(String id) {

        if (this.getGraph().getUniverse().changeID(this, id)) {

            super.setID(id);
        }
    }

    /**
     * Getter for the graph the node is contained in.
     * 
     * @return the graph the node is contained in.
     */
    @Override
    public IGraph getGraph() {

        if (!this.graph.isEmpty()) {

            return this.graph.get(0);
        }
        return null;
    }


    /**
     * Getter for the node'scale edges.
     * 
     * Returns the edges (both outgoing and incoming) of the node.
     * 
     * @return The node'scale edges.
     */
    @Override
    public Set<? extends IEdge> getEdges() {

        return new LinkedHashSet<>(Collections.unmodifiableSet(this.edges));
    }


    /**
     * Getter for the node'scale hyperedges.
     * 
     * Returns the hyperedges of the node.
     * 
     * @return The node'scale hyperedges.
     */
    @Override
    public Set<? extends IHyperEdge> getHyperEdges() {

        return new LinkedHashSet<>(Collections.unmodifiableSet(this.hyperedges));
    }


    /**
     * Creates a new instance of the {@link NodeBase} class. (Serialization
     * Constructor).
     */
    @SuppressWarnings("unused")
    private NodeBase() {

        this(null, null, null, null, true);
    }

    /**
     * Creates a new instance of the {@link NodeBase} class.
     * 
     * The created node only has a unique identifier and is associated with a
     * graph and contain metadata.
     * 
     * @param id
     *            The node'scale unique identifier.
     * @param graph
     *            The graph containing the node.
     */
    protected NodeBase(String id, IGraph graph) {

        this(id, graph, null, null);
    }


    /**
     * Creates a new instance of the {@link NodeBase} class.
     * 
     * The created node only has a unique identifier and is associated with a
     * graph and contain metadata.
     * 
     * @param id
     *            The node'scale unique identifier.
     * @param graph
     *            The graph containing the node.
     * @param edges
     *            The node'scale edges.
     * @param hyperEdges
     *            The node'scale hyperedges.
     */
    protected NodeBase(String id, IGraph graph, Set<IEdge> edges,
            Set<IHyperEdge> hyperEdges) {

        this(id, graph, edges, hyperEdges, false);
    }

    /**
     * Creates a new instance of the {@link NodeBase} class.
     * 
     * The created node only has a unique identifier and is associated with a
     * graph and contain metadata.
     * 
     * @param id
     *            The node'scale unique identifier.
     * @param graph
     *            The graph containing the node.
     * @param edges
     *            The node'scale edges.
     * @param hyperEdges
     *            The node'scale hyperedges.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    protected NodeBase(String id, IGraph graph, Set<IEdge> edges,
            Set<IHyperEdge> hyperEdges, boolean serialization) {

        super(id, serialization);

        this.graph = new LinkedList<>();
        this.graph.add(graph);

        if (edges != null) {
            this.edges = edges;
        }
        else {
            this.edges = new LinkedHashSet<>();
        }

        if (hyperEdges != null) {
            this.hyperedges = hyperEdges;
        }
        else {
            this.hyperedges = new LinkedHashSet<>();
        }
    }


    /**
     * Sets the graph of the node.
     * 
     * @param graph
     *            the graph containing the node.
     */
    protected void setGraph(IGraph graph) {

        this.graph.clear();
        this.graph.add(graph);
    }

    /**
     * Adds an edge to the node.
     * 
     * @param edge
     *            The edge to add.
     */
    protected void addEdge(IEdge edge) {

        this.edges.add(edge);
    }


    /**
     * Removes an edge from the node.
     * 
     * @param edge
     *            The edge to remove.
     */
    protected void deleteEdge(IEdge edge) {

        this.edges.remove(edge);

    }

    /**
     * Removes all the edges from the node.
     */
    protected void clearEdges() {

        this.edges.clear();
    }

    /**
     * Adds a hyperedge to the node.
     * 
     * @param hyperedge
     *            The edge to add.
     */
    protected void addHyperEdge(IHyperEdge hyperedge) {

        this.hyperedges.add(hyperedge);
    }


    /**
     * Removes a hyperedge from the node.
     * 
     * @param hyperedge
     *            The hyperedge to remove.
     */
    protected void deleteHyperEdge(IHyperEdge hyperedge) {

        this.hyperedges.remove(hyperedge);

    }

    /**
     * Removes all the hyperedges from the node.
     */
    protected void clearHyperEdges() {

        this.hyperedges.clear();
    }


    @Override
    public long degree() {

        return this.edgeDegree() + this.hyperEdgeDegree();
    }

    @Override
    public long edgeDegree() {

        Integer degree = this.edges.size();

        return degree.longValue();
    }

    @Override
    public long hyperEdgeDegree() {

        Integer degree = this.hyperedges.size();

        return degree.longValue();
    }

    @Override
    public long inDegree() {

        return this.edgeInDegree() + this.hyperEdgeInDegree();
    }

    @Override
    public long edgeInDegree() {

        Long inDegree = 0L;

        for (IEdge edge : this.edges) {
            if (edge.isDirected()) {
                if (this.equals(edge.getTarget())) {
                    inDegree++;
                }
            }
        }

        return inDegree;
    }

    @Override
    public long hyperEdgeInDegree() {

        Long inDegree = 0L;

        for (IHyperEdge hyperEdge : this.hyperedges) {
            for (IEndpoint endpoint : hyperEdge.getEndpoints()) {
                if (this.equals(endpoint.getNode()) && endpoint.isIncoming()) {
                    inDegree++;
                }
            }
        }

        return inDegree;
    }

    @Override
    public long outDegree() {

        return this.edgeOutDegree() + this.hyperEdgeOutDegree();
    }

    @Override
    public long edgeOutDegree() {

        Long outDegree = 0L;
        for (IEdge edge : this.edges) {
            if (edge.isDirected()) {
                if (this.equals(edge.getSource())) {
                    outDegree++;
                }
            }
        }

        return outDegree;
    }

    @Override
    public long hyperEdgeOutDegree() {

        Long outDegree = 0L;

        for (IHyperEdge hyperEdge : this.hyperedges) {
            for (IEndpoint endpoint : hyperEdge.getEndpoints()) {
                if (this.equals(endpoint.getNode()) && endpoint.isOutgoing()) {
                    outDegree++;
                }
            }
        }


        return outDegree;
    }


    @Override
    public Set<GraphObjectProperty> hasGraphProperties() {

        return Collections.unmodifiableSet(EnumSet.of(
                GraphObjectProperty.DEGREE, GraphObjectProperty.DEGREE_EDGES,
                GraphObjectProperty.DEGREE_HYPEREDGES,
                GraphObjectProperty.IN_DEGREE,
                GraphObjectProperty.IN_DEGREE_EDGES,
                GraphObjectProperty.IN_DEGREE_HYPEREDGES,
                GraphObjectProperty.OUT_DEGREE,
                GraphObjectProperty.OUT_DEGREE_EDGES,
                GraphObjectProperty.OUT_DEGREE_HYPEREDGES));
    }


    @Override
    public Object getValueOfGraphProperty(
            GraphObjectProperty graphObjectProperty) {

        switch (graphObjectProperty) {
            case DEGREE:
                return this.degree();
            case DEGREE_EDGES:
                return this.edgeDegree();
            case DEGREE_HYPEREDGES:
                return this.hyperEdgeDegree();
            case IN_DEGREE:
                return this.inDegree();
            case IN_DEGREE_EDGES:
                return this.edgeInDegree();
            case IN_DEGREE_HYPEREDGES:
                return this.hyperEdgeInDegree();
            case OUT_DEGREE:
                return this.outDegree();
            case OUT_DEGREE_EDGES:
                return this.edgeOutDegree();
            case OUT_DEGREE_HYPEREDGES:
                return this.hyperEdgeOutDegree();
            default:
                throw new UnsupportedOperationException("GraphObjectProperty "
                        + graphObjectProperty
                        + " is not supported by this node.");

        }
    }


    @Override
    public boolean isIdentical(IGraphObject other) {

        if (other instanceof INode) {

            return this.isIdentical((INode) other, true);
        }
        return false;
    }


    @Override
    public boolean isIdentical(INode other, boolean recurse) {

        if (this.equals(other)) {

            boolean sameNumberOfEdges = this.getEdges().size() == other
                    .getEdges().size();
            boolean sameNumberOfHyperEdges = this.getHyperEdges().size() == other
                    .getHyperEdges().size();

            if (sameNumberOfEdges && sameNumberOfHyperEdges) {

                if (!recurse) {

                    boolean edgesEqual = this.getEdges().equals(
                            other.getEdges());
                    boolean hyperedgesEqual = this.getHyperEdges().equals(
                            other.getHyperEdges());

                    return edgesEqual && hyperedgesEqual;
                }

                for (IEdge edge : this.edges) {

                    boolean identical = false;
                    for (IEdge otherEdge : other.getEdges()) {

                        identical = edge.isIdentical(otherEdge, false);
                        if (identical) {

                            break;
                        }
                    }
                    if (!identical) {

                        return false;
                    }

                }
                for (IHyperEdge hyperedge : this.hyperedges) {

                    boolean identical = false;
                    for (IHyperEdge otherHyperEdge : other.getHyperEdges()) {

                        identical = hyperedge
                                .isIdentical(otherHyperEdge, false);
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
        }

        return false;
    }
}
