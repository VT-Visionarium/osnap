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


package graphVisualizer.graph;


import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import graphVisualizer.graph.base.NodeBase;
import graphVisualizer.graph.common.IEdge;
import graphVisualizer.graph.common.IGraph;
import graphVisualizer.graph.common.IHyperEdge;
import graphVisualizer.graph.common.INode;
import graphVisualizer.graph.metadata.IMetadataContainer;
import graphVisualizer.graph.metadata.MetadataMapProperty;
import graphVisualizer.graph.metadata.Schema;


/**
 * This class represents a node contained within a graph.
 * 
 * Nodes consist of a (required) unique identifier, are associated with a graph
 * (required), and can (optionally) have edges to other nodes (including
 * themselves), and/or contain metadata.
 * 
 * <p/>
 * Nodes can only be created from within a graph.
 * 
 * 
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 * @see graphVisualizer.graph.Graph
 * @see graphVisualizer.graph.Edge
 * @see graphVisualizer.graph.HyperEdge
 * @see graphVisualizer.graph.metadata.Schema
 */
@XmlType(name = "Node")
@XmlSeeAlso({ NodeBase.class })
public class Node
        extends NodeBase
        implements IMetadataContainer {


    @XmlElement(name = "Metadata")
    private final MetadataMapProperty metadata;

    @Override
    public void setID(String id) {

        super.setID(id);
    }

    @SuppressWarnings("unused")
    private Node() {

        this(null, null, null, true);
    }

    /**
     * Creates a new instance of the Node class.
     * 
     * The created node only has a unique identifier and is associated with a
     * graph. It does not contain any metadata.
     * 
     * @param id
     *            The node'scale unique identifier.
     * @param graph
     *            The graph containing the node.
     */
    protected Node(String id, IGraph graph) {

        this(id, graph, null);
    }

    /**
     * Creates a new instance of the Node class.
     * 
     * The created node only has a unique identifier and is associated with a
     * graph and contain metadata.
     * 
     * @param id
     *            The node'scale unique identifier.
     * @param graph
     *            The graph containing the node.
     * @param schema
     *            The node'scale metadata.
     */
    protected Node(String id, IGraph graph, Schema schema) {

        this(id, graph, schema, false);

    }

    private Node(String id, IGraph graph, Schema schema, boolean serialization) {

        super(id, graph, null, null, serialization);

        if (id == null && !serialization) {

            throw new IllegalArgumentException("A node must have an id.");
        }

        if (graph == null && !serialization) {
            throw new IllegalArgumentException(
                    "A node must be associated with a graph.");
        }

        if (schema == null && !serialization) {

            throw new IllegalArgumentException("A node must have a schema.");
        }
        if (schema != null) {

            this.metadata = new MetadataMapProperty(schema);
        }
        else {
            this.metadata = null;
        }
    }

    @Override
    public MetadataMapProperty getMetadataProperty() {

        return this.metadata;
    }


    @Override
    public Graph getGraph() {

        if (super.getGraph() instanceof Graph) {

            return (Graph) super.getGraph();
        }

        return null;
    }


    @Override
    public Set<Edge> getEdges() {

        LinkedHashSet<Edge> edges = new LinkedHashSet<>();

        for (IEdge edge : super.getEdges()) {

            if (edge instanceof Edge) {

                edges.add((Edge) edge);
            }
        }

        return edges;
    }

    @Override
    protected void addEdge(IEdge edge) {

        super.addEdge(edge);
    }


    @Override
    protected void deleteEdge(IEdge edge) {

        super.deleteEdge(edge);

    }

    @Override
    protected void clearEdges() {

        super.clearEdges();
    }


    @Override
    public Set<HyperEdge> getHyperEdges() {

        LinkedHashSet<HyperEdge> hyperEdges = new LinkedHashSet<>();

        for (IHyperEdge hyperEdge : super.getHyperEdges()) {

            if (hyperEdge instanceof HyperEdge) {

                hyperEdges.add((HyperEdge) hyperEdge);
            }
        }

        return hyperEdges;
    }


    @Override
    protected void addHyperEdge(IHyperEdge hyperedge) {

        super.addHyperEdge(hyperedge);
    }


    @Override
    protected void deleteHyperEdge(IHyperEdge hyperedge) {

        super.deleteHyperEdge(hyperedge);

    }

    @Override
    protected void clearHyperEdges() {

        super.clearHyperEdges();
    }


    @Override
    public boolean isIdentical(INode other, boolean recurse) {

        if (super.isIdentical(other, recurse)) {

            if (other instanceof Node) {

                Node otherNode = (Node) other;

                boolean sameMetadata = this.getMetadataProperty().isIdentical(
                        (otherNode.getMetadataProperty()));

                return sameMetadata;

            }
        }

        return false;
    }

}
