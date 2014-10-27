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
import edu.vt.arc.vis.osnap.core.domain.graph.Endpoint;
import edu.vt.arc.vis.osnap.core.domain.graph.Graph;
import edu.vt.arc.vis.osnap.core.domain.graph.HyperEdge;
import edu.vt.arc.vis.osnap.core.domain.graph.common.GraphObjectProperty;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IEndpoint;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraph;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IHyperEdge;
import edu.vt.arc.vis.osnap.core.domain.graph.common.INode;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * The abstract {@link HyperEdgeBase} class provides a base implementation of
 * the {@link IHyperEdge} interface.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 * @see IGraph
 * @see INode
 * @see IEndpoint
 */
@XmlType(name = "HyperEdgeBase")
@XmlSeeAlso({ HyperEdge.class, Endpoint.class })
@XmlAccessorType(XmlAccessType.NONE)
public abstract class HyperEdgeBase
        extends DomainObject
        implements IHyperEdge {


    @XmlIDREF
    @XmlElements({ @XmlElement(name = "Graph", type = Graph.class) })
    @XmlSchemaType(name = "IDREF")
    private List<IGraph>         graph;

    @XmlElementWrapper(name = "Endpoints")
    @XmlElements({ @XmlElement(name = "EndpointBaseDetails", type = Endpoint.class) })
    private final Set<IEndpoint> endpoints;


    @Override
    public void setId(String id) {

        if (this.getGraph().getUniverse().changeID(this, id)) {

            super.setId(id);
        }
    }

    /**
     * Creates a new instance of the {@link HyperEdgeBase} class. (Serialization
     * Constructor).
     */
    @SuppressWarnings("unused")
    private HyperEdgeBase() {

        this(null, null, null, true);
    }

    /**
     * Creates a new instance of the {@link HyperEdgeBase} class.
     * 
     * This constructor creates a hyperedge within the given graph between the
     * provided nodes with the provided unique identifier.
     * 
     * The hyperedge contains the provided metadata.
     * 
     * @param id
     *            The unique identifier of the hyperedge.
     * @param graph
     *            The graph containing the hyperedge.
     * @param endpoints
     *            The node of the hyperedge.
     */
    protected HyperEdgeBase(String id, IGraph graph, Set<IEndpoint> endpoints) {

        this(id, graph, endpoints, false);
    }

    /**
     * Creates a new instance of the {@link HyperEdgeBase} class.
     * 
     * This constructor creates a hyperedge within the given graph between the
     * provided nodes with the provided unique identifier.
     * 
     * The hyperedge contains the provided metadata.
     * 
     * @param id
     *            The unique identifier of the hyperedge.
     * @param graph
     *            The graph containing the hyperedge.
     * @param endpoints
     *            The node of the hyperedge.
     * @param serialization
     *            whether or not the constructor is invoked during
     *            serialization.
     */
    protected HyperEdgeBase(String id, IGraph graph, Set<IEndpoint> endpoints,
            boolean serialization) {

        super(id, serialization);

        this.graph = new LinkedList<>();
        this.graph.add(graph);

        this.endpoints = new HashSet<>();

        if (endpoints != null) {
            this.endpoints.addAll(endpoints);
        }
    }


    /**
     * Sets the graph that contains this hyperedge.
     * 
     * @param graph
     *            the graph.
     */
    protected void setGraph(IGraph graph) {

        this.graph.clear();
        this.graph.add(graph);
    }


    /**
     * Adds an endpoint to this hyperedge.
     * 
     * @param endpoint
     *            the endpoint to add.
     */
    protected void addEndpoint(IEndpoint endpoint) {

        if (endpoint != null) {
            this.endpoints.add(endpoint);
        }
    }

    /**
     * Removes an endpoint from this hyperedge.
     * 
     * @param endpoint
     *            the endpoint to remove.
     */
    protected void removeEndpoint(IEndpoint endpoint) {

        this.endpoints.remove(endpoint);
    }

    /**
     * Clears the endpoints.
     */
    protected void clearEndpoints() {

        this.endpoints.clear();
    }


    /*
     * (non-Javadoc)
     * 
     * @see edu.vt.arc.vis.osnap.core.domain.graph.common.IHyperEdge#getNodes()
     */
    @Override
    public Set<? extends INode> getNodes() {

        Set<INode> nodes = new HashSet<>();

        for (IEndpoint endpoint : this.endpoints) {
            nodes.add(endpoint.getNode());
        }

        return Collections.unmodifiableSet(nodes);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * edu.vt.arc.vis.osnap.core.domain.graph.common.IHyperEdge#getEndpoints()
     */
    @Override
    public Set<? extends IEndpoint> getEndpoints() {

        return new LinkedHashSet<>(Collections.unmodifiableSet(this.endpoints));
    }


    @Override
    public long cardinality() {

        Integer size = this.endpoints.size();

        return size.longValue();
    }


    @Override
    public Set<GraphObjectProperty> hasGraphProperties() {

        return Collections.unmodifiableSet(EnumSet
                .of(GraphObjectProperty.CARDINALITY));
    }


    @Override
    public Object getValueOfGraphProperty(
            GraphObjectProperty graphObjectProperty) {

        switch (graphObjectProperty) {
            case CARDINALITY:
                return this.cardinality();

            default:
                throw new UnsupportedOperationException("GraphObjectProperty "
                        + graphObjectProperty
                        + " is not supported by this hyperedge.");

        }
    }

    @Override
    public IGraph getGraph() {

        if (!this.graph.isEmpty()) {

            return this.graph.get(0);
        }
        return null;
    }

    @Override
    public boolean isIdentical(IGraphObject other) {

        if (other instanceof IHyperEdge) {

            return this.isIdentical((IHyperEdge) other, true);
        }
        return false;
    }

    @Override
    public boolean isIdentical(IHyperEdge other, boolean recurse) {

        if (this.equals(other)) {


            if (!recurse) {
                return this.getEndpoints().equals(other.getEndpoints());
            }


            for (IEndpoint endpoint : this.getEndpoints()) {

                boolean identical = false;
                for (IEndpoint otherEndpoint : other.getEndpoints()) {

                    identical = endpoint.isIdentical(otherEndpoint, recurse);
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
