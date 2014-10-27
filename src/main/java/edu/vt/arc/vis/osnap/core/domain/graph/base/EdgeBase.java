package edu.vt.arc.vis.osnap.core.domain.graph.base;

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


import edu.vt.arc.vis.osnap.core.domain.DomainObject;
import edu.vt.arc.vis.osnap.core.domain.graph.Edge;
import edu.vt.arc.vis.osnap.core.domain.graph.Endpoint;
import edu.vt.arc.vis.osnap.core.domain.graph.Graph;
import edu.vt.arc.vis.osnap.core.domain.graph.common.GraphObjectProperty;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IEdge;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IEndpoint;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraph;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IHyperEdge;
import edu.vt.arc.vis.osnap.core.domain.graph.common.INode;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.Schema;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * The abstract <CODE>EdgeBase</CODE> class provides a base implementation of
 * an edge between two nodes.
 * 
 * Edges consist of a (required) unique identifier, are associated with a graph
 * (required), require source and target nodes, and (optionally) can be directed
 * and/or contain metadata.
 * 
 * <p>
 * Edges can only be created from within a graph.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 * @see IGraph
 * @see INode
 * @see IEndpoint
 * @see IHyperEdge
 * @see Schema
 */
@XmlType(name = "EdgeBase")
@XmlSeeAlso({ Edge.class, Endpoint.class })
@XmlAccessorType(XmlAccessType.NONE)
public abstract class EdgeBase
        extends DomainObject
        implements IEdge {

    @XmlIDREF
    @XmlElements({ @XmlElement(name = "Graph", type = Graph.class) })
    @XmlSchemaType(name = "IDREF")
    private List<IGraph> graph;

    @XmlElement(name = "SourceEndpoint", type = Endpoint.class)
    private IEndpoint    sourceEndpoint;
    @XmlElement(name = "TargetEndpoint", type = Endpoint.class)
    private IEndpoint    targetEndpoint;

    @XmlAttribute(name = "directed")
    private Boolean      isDirected;


    @Override
    public void setId(String id) {

        if (this.getGraph().getUniverse().changeID(this, id)) {

            super.setId(id);
        }
    }

    @Override
    public IGraph getGraph() {

        if (!this.graph.isEmpty()) {

            return this.graph.get(0);
        }
        return null;
    }


    @SuppressWarnings("unused")
    private EdgeBase() {

        this(null, null, null, null, null, true);
    }

    /**
     * Creates a new instance of the EdgeBase class.
     * 
     * This constructor creates an undirected edge within the given graph from
     * the source to the target node with the provided unique identifier.
     * 
     * Edges are not directed by default.
     * 
     * @param id
     *            The unique identifier of the edge.
     * @param graph
     *            The graph containing the edge.
     * @param sourceEndpoint
     *            The source endpoint of the edge.
     * @param targetEndpoint
     *            The target endpoint of the edge.
     */
    protected EdgeBase(String id, IGraph graph, IEndpoint sourceEndpoint,
            IEndpoint targetEndpoint) {

        this(id, graph, sourceEndpoint, targetEndpoint, false);
    }

    /**
     * Creates a new instance of the EdgeBase class.
     * 
     * This constructor creates an undirected or directed edge within the given
     * graph from the source to the target node with the provided unique
     * identifier.
     * 
     * @param id
     *            The unique identifier of the edge.
     * @param graph
     *            The graph containing the edge.
     * @param sourceEndpoint
     *            The source node of the edge.
     * @param targetEndpoint
     *            The target node of the edge.
     * @param isDirected
     *            Specifies whether the edge should be directed.
     */
    protected EdgeBase(String id, IGraph graph, IEndpoint sourceEndpoint,
            IEndpoint targetEndpoint, boolean isDirected) {

        this(id, graph, sourceEndpoint, targetEndpoint, isDirected, false);
    }

    /**
     * Creates a new instance of the EdgeBase class.
     * 
     * This constructor creates an undirected or directed edge within the given
     * graph from the source to the target node with the provided unique
     * identifier.
     * 
     * @param id
     *            The unique identifier of the edge.
     * @param graph
     *            The graph containing the edge.
     * @param sourceEndpoint
     *            The source node of the edge.
     * @param targetEndpoint
     *            The target node of the edge.
     * @param isDirected
     *            Specifies whether the edge should be directed.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    protected EdgeBase(String id, IGraph graph, IEndpoint sourceEndpoint,
            IEndpoint targetEndpoint, Boolean isDirected, boolean serialization) {

        super(id, serialization);

        this.graph = new LinkedList<>();
        this.graph.add(graph);
        // this.graph = graph;


        this.sourceEndpoint = sourceEndpoint;
        this.targetEndpoint = targetEndpoint;

        this.isDirected = isDirected;
    }


    /**
     * Sets the graph of this edge.
     * 
     * @param graph
     *            the graph.
     */
    protected void setGraph(IGraph graph) {


        this.graph.clear();
        this.graph.add(graph);
    }

    /**
     * Returns the source (or first) endpoint of this edge.
     * 
     * @return the source endpoint.
     */
    protected IEndpoint getSourceEndpoint() {

        return this.sourceEndpoint;
    }

    /**
     * Sets the source (or first) endpoint of this edge.
     * 
     * @param sourceEndpoint
     *            the new source endpoint.
     */
    protected void setSourceEndpoint(IEndpoint sourceEndpoint) {

        if (sourceEndpoint == null) {
            throw new IllegalArgumentException("An edge must have a source "
                    + "node.");
        }

        this.sourceEndpoint = sourceEndpoint;
    }

    /**
     * Returns the target (or second) endpoint.
     * 
     * @return the target endpoint.
     */
    protected IEndpoint getTargetEndpoint() {

        return this.targetEndpoint;
    }

    /**
     * Sets the target (or second) endpoint of this edge.
     * 
     * @param targetEndpoint
     *            the new target endpoint.
     */
    protected void setTargetEndpoint(IEndpoint targetEndpoint) {

        if (targetEndpoint == null) {
            throw new IllegalArgumentException("An edge must have a source "
                    + "node.");
        }

        this.targetEndpoint = targetEndpoint;
    }

    /**
     * Switches this edge to a directed edge.
     */
    protected void setDirected() {

        this.isDirected = true;
    }

    /**
     * Switches this edge to an undirected edge.
     */
    protected void setUndirected() {

        this.isDirected = false;
    }


    @Override
    public INode getSource() {

        return this.sourceEndpoint.getNode();
    }

    @Override
    public INode getTarget() {

        return this.targetEndpoint.getNode();
    }


    @Override
    public Set<? extends IEndpoint> getEndpoints() {

        Set<IEndpoint> endpoints = new HashSet<>();

        endpoints.add(sourceEndpoint);
        endpoints.add(targetEndpoint);

        return Collections.unmodifiableSet(endpoints);
    }

    @Override
    public Set<? extends INode> getNodes() {

        Set<INode> nodes = new LinkedHashSet<>();

        nodes.add(sourceEndpoint.getNode());
        nodes.add(targetEndpoint.getNode());

        return Collections.unmodifiableSet(nodes);
    }


    /**
     * The getter for the isDirected field.
     * 
     * @return <CODE>true</CODE>, if the edge is directed, otherwise
     *         <CODE>false</CODE>.
     */
    @Override
    public Boolean isDirected() {

        return isDirected;
    }


    @Override
    public long cardinality() {

        return 2L;
    }


    @Override
    public Set<GraphObjectProperty> hasGraphProperties() {

        return Collections.unmodifiableSet(EnumSet.of(
                GraphObjectProperty.DIRECTED, GraphObjectProperty.CARDINALITY));
    }


    @Override
    public Object getValueOfGraphProperty(
            GraphObjectProperty graphObjectProperty) {

        switch (graphObjectProperty) {
            case DIRECTED:
                return this.isDirected();
            case CARDINALITY:
                return this.cardinality();
            default:
                throw new UnsupportedOperationException("GraphObjectProperty "
                        + graphObjectProperty
                        + " is not supported by this edge.");

        }
    }

    @Override
    public boolean isIdentical(IGraphObject other) {

        if (other instanceof IEdge) {

            return this.isIdentical((IEdge) other, true);
        }
        return false;
    }

    @Override
    public boolean isIdentical(IHyperEdge other, boolean recurse) {

        if (other instanceof IEdge) {

            return this.isIdentical((IEdge) other, recurse);
        }

        return false;
    }


    @Override
    public boolean isIdentical(IEdge other, boolean recurse) {

        if (this.equals(other)) {

            boolean bothDirected = this.isDirected() == other.isDirected();
            if (bothDirected && other instanceof EdgeBase) {

                EdgeBase otherEdgeBase = (EdgeBase) other;

                boolean sourcesIdentical = this
                        .getSourceEndpoint()
                        .isIdentical(otherEdgeBase.getSourceEndpoint(), recurse);

                boolean targetsIdentical = this
                        .getTargetEndpoint()
                        .isIdentical(otherEdgeBase.getTargetEndpoint(), recurse);

                return sourcesIdentical && targetsIdentical;
            }

        }

        return false;
    }

}
