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


package edu.vt.arc.vis.osnap.graph;


import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import edu.vt.arc.vis.osnap.graph.base.EdgeBase;
import edu.vt.arc.vis.osnap.graph.common.IEdge;
import edu.vt.arc.vis.osnap.graph.common.IEndpoint;
import edu.vt.arc.vis.osnap.graph.common.IGraph;
import edu.vt.arc.vis.osnap.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.graph.common.INode;
import edu.vt.arc.vis.osnap.graph.metadata.IMetadataContainer;
import edu.vt.arc.vis.osnap.graph.metadata.MetadataMapProperty;
import edu.vt.arc.vis.osnap.graph.metadata.Schema;


/**
 * This class represents an edge between two nodes.
 * 
 * Edges consist of a (required) unique identifier, are associated with a graph
 * (required), require source and target nodes, and (optionally) can be directed
 * and/or contain schema.
 * 
 * <p/>
 * Edges can only be created from within a graph.
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 * @see edu.vt.arc.vis.osnap.graph.Graph
 * @see edu.vt.arc.vis.osnap.graph.Node
 * @see edu.vt.arc.vis.osnap.graph.metadata.Schema
 */
@XmlType(name = "Edge")
public class Edge
        extends EdgeBase
        implements IMetadataContainer, IGraphObject {


    @XmlElement(name = "Metadata")
    private final MetadataMapProperty metadata;


    @Override
    public void setID(String id) {

        super.setID(id);
    }

    @SuppressWarnings("unused")
    private Edge() {

        this(null, null, null, null, null, null, true);
    }

    /**
     * Creates a new instance of the Edge class.
     * 
     * This constructor creates an undirected edge within the given graph from
     * the source to the target node with the provided unique identifier.
     * 
     * The edge contains the provided schema.
     * 
     * @param id
     *            The unique identifier of the edge.
     * @param graph
     *            The graph containing the edge.
     * @param source
     *            The source node of the edge.
     * @param target
     *            The target node of the edge.
     * @param schema
     *            The schema associated with the edge.
     */
    protected Edge(String id, IGraph graph, INode source, INode target,
            Schema schema) {

        this(id, graph, source, target, false, schema);
    }


    /**
     * Creates a new instance of the Edge class.
     * 
     * This constructor creates an undirected or directed edge within the given
     * graph from the source to the target node with the provided unique
     * identifier.
     * 
     * The edge contains the provided schema.
     * 
     * @param id
     *            The unique identifier of the edge.
     * @param graph
     *            The graph containing the edge.
     * @param source
     *            The source node of the edge.
     * @param target
     *            The target node of the edge.
     * @param isDirected
     *            Specifies whether the edge should be directed.
     * @param schema
     *            The schema associated with the edge.
     */
    protected Edge(String id, IGraph graph, INode source, INode target,
            Boolean isDirected, Schema schema) {

        this(id, graph, source, target, isDirected, schema, false);
    }

    /**
     * @param id
     * @param graph
     * @param source
     * @param target
     * @param isDirected
     * @param schema
     * @param serialization
     */
    private Edge(String id, IGraph graph, INode source, INode target,
            Boolean isDirected, Schema schema, boolean serialization) {

        super(id, graph, new Endpoint(id, source, false, isDirected,
                serialization), new Endpoint(id, target, isDirected, false,
                serialization), isDirected, serialization);


        if (id == null && !serialization) {
            throw new IllegalArgumentException("An edge must have an id.");
        }

        if (graph == null && !serialization) {
            throw new IllegalArgumentException("An edge must be associated "
                    + "with a graph.");
        }


        if (schema == null && !serialization) {

            throw new IllegalArgumentException("An edge must have a schema.");
        }

        if (schema != null) {

            this.metadata = new MetadataMapProperty(schema);
        }
        else {
            this.metadata = null;
        }
    }


    @Override
    public void setDirected() {

        super.setDirected();

        if (this.getSourceEndpoint() instanceof Endpoint) {
            Endpoint sourceEndpoint = (Endpoint) this.getSourceEndpoint();
            sourceEndpoint.setOutgoing();
        }
        if (this.getTargetEndpoint() instanceof Endpoint) {
            Endpoint targetEndpoint = (Endpoint) this.getTargetEndpoint();
            targetEndpoint.setIncoming();
        }
    }

    @Override
    public void setUndirected() {

        super.setUndirected();

        if (this.getSourceEndpoint() instanceof Endpoint) {
            Endpoint sourceEndpoint = (Endpoint) this.getSourceEndpoint();
            sourceEndpoint.setNotOutgoing();
            sourceEndpoint.setNotIncoming();
        }
        if (this.getTargetEndpoint() instanceof Endpoint) {
            Endpoint targetEndpoint = (Endpoint) this.getTargetEndpoint();
            targetEndpoint.setNotOutgoing();
            targetEndpoint.setNotIncoming();
        }
    }


    @Override
    public String toString() {

        if (this.isDirected()) {
            return this.getID() + ": " + this.getSource() + "->"
                    + this.getTarget();
        }
        return this.getID() + ": " + this.getSource() + "<->"
                + this.getTarget();
    }

    @Override
    public MetadataMapProperty getMetadataProperty() {

        return this.metadata;
    }


    @Override
    public boolean isIdentical(IEdge other, boolean recurse) {

        if (super.isIdentical(other, recurse)) {

            if (other instanceof Edge) {

                Edge otherEdge = (Edge) other;

                boolean sameMetadata = this.getMetadataProperty().isIdentical(
                        (otherEdge.getMetadataProperty()));

                return sameMetadata;

            }
        }

        return false;
    }

    @Override
    public Graph getGraph() {

        if (super.getGraph() instanceof Graph) {

            return (Graph) super.getGraph();
        }

        return null;
    }

    @Override
    public Node getSource() {

        if (super.getSource() instanceof Node) {

            return (Node) super.getSource();
        }
        return null;
    }

    @Override
    public Node getTarget() {

        if (super.getTarget() instanceof Node) {

            return (Node) super.getTarget();
        }
        return null;
    }

    @Override
    public Set<Node> getNodes() {

        Set<Node> nodes = new LinkedHashSet<>();

        for (INode node : super.getNodes()) {

            if (node instanceof Node) {

                nodes.add((Node) node);
            }
        }

        return nodes;
    }

    @Override
    public Set<Endpoint> getEndpoints() {

        Set<Endpoint> endpoints = new LinkedHashSet<>();

        for (IEndpoint endpoint : super.getEndpoints()) {

            if (endpoint instanceof Endpoint) {

                endpoints.add((Endpoint) endpoint);
            }
        }

        return endpoints;
    }
}
