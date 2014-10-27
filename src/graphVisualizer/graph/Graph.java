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


import java.util.Collection;
import java.util.LinkedList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlType;

import graphVisualizer.graph.base.GraphBase;
import graphVisualizer.graph.common.*;
import graphVisualizer.graph.metadata.IMetadataContainer;
import graphVisualizer.graph.metadata.MetadataMapProperty;
import graphVisualizer.graph.metadata.Schema;


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
@XmlType(name = "Graph")
public class Graph
        extends GraphBase
        implements IMetadataContainer {


    @XmlElement(name = "Metadata")
    private final MetadataMapProperty metadata;


    @Override
    public void setID(String id) {

        super.setID(id);
    }

    @Override
    @XmlIDREF
    @XmlAttribute
    public Universe getUniverse() {

        if (super.getUniverse() instanceof Universe) {

            return (Universe) super.getUniverse();
        }
        return null;
    }

    /**
     * Sets the universe.
     * 
     * @param universe
     *            the universe.
     */
    protected void setUniverse(Universe universe) {

        super.setUniverse(universe);
    }

    @SuppressWarnings("unused")
    private Graph() {

        this(null, null, null, true);
    }

    /**
     * Creates an instance of the Graph class.
     * 
     * The graph created contains neither nodes, nor edges, nor metadata.
     * 
     * @param id
     *            The graph'scale unique identifier.
     * @param universe
     *            The universe the graph belongs to.
     */
    protected Graph(String id, IUniverse universe) {

        this(id, universe, null);
    }

    /**
     * Creates an instance of the Graph class.
     * 
     * The graph created contains neither nodes, nor edges.
     * 
     * @param id
     *            The graph'scale unique identifier.
     * @param universe
     *            The universe the graph belongs to.
     * @param schema
     *            The schema assigned to this graph.
     */
    protected Graph(String id, IUniverse universe, Schema schema) {

        this(id, universe, schema, false);
    }

    private Graph(String id, IUniverse universe, Schema schema,
            boolean serialization) {

        super(id, universe, serialization);

        if (id == null && !serialization) {
            throw new IllegalArgumentException("Graph must have an id.");
        }

        if (universe == null && !serialization) {
            throw new IllegalArgumentException(
                    "Graph must belong to a universe.");
        }
        if (schema == null && !serialization) {
            throw new IllegalArgumentException("A graph must have a schema.");
        }
        if (schema != null) {

            this.metadata = new MetadataMapProperty(schema);
        }
        else {
            this.metadata = null;
        }
    }


    /**
     * Getter for the Graph'scale metadata.
     * 
     * @return The graph'scale metadata in a MetadataMapProperty.
     */
    @Override
    public MetadataMapProperty getMetadataProperty() {

        return this.metadata;
    }


    @Override
    protected void addNode(INode node) {

        super.addNode(node);
    }

    @Override
    protected void deleteNode(INode node) {

        super.deleteNode(node);
    }

    @Override
    protected void clearNodeMap() {

        super.clearNodeMap();
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

    @Override
    protected void addEdge(IEdge edge) {

        super.addEdge(edge);
    }

    @Override
    protected void deleteEdge(IEdge edge) {

        super.deleteEdge(edge);
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

    @Override
    protected void clearEdgeMap() {

        super.clearEdgeMap();
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
    protected void clearHyperEdgeMap() {

        super.clearHyperEdgeMap();
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

    @Override
    public String toString() {

        return this.getID();
    }

    @Override
    public boolean isIdentical(IGraphObject other) {

        if (super.isIdentical(other)) {

            if (other instanceof Graph) {

                Graph otherGraph = (Graph) other;

                boolean sameMetadata = this.getMetadataProperty().isIdentical(
                        (otherGraph.getMetadataProperty()));

                return sameMetadata;

            }
        }

        return false;
    }
}
