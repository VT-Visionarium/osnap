package edu.vt.arc.vis.osnap.core.domain.graph;


import edu.vt.arc.vis.osnap.core.domain.graph.base.HyperEdgeBase;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IEndpoint;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraph;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IHyperEdge;
import edu.vt.arc.vis.osnap.core.domain.graph.common.INode;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.IMetadataContainer;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.MetadataMapProperty;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.Schema;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * This class represents a hyperedge between an arbitrary number of nodes.
 * 
 * Hyperedges consist of a (required) unique identifier, are associated with a
 * graph (required), require a set of nodes, and can (optionally) contain
 * metadata.
 * 
 * <p/>
 * Hyperedges can only be created from within a graph.
 * 
 * <p/>
 * <FONT COLOR="#cc6600">Not implemented!</FONT>
 * 
 * @author Peter J. Radics
 * @version 0.1
 * 
 * @see edu.vt.arc.vis.osnap.core.domain.graph.Graph
 * @see edu.vt.arc.vis.osnap.core.domain.graph.Node
 * @see edu.vt.arc.vis.osnap.core.domain.graph.metadata.Metadata
 */
@XmlType(name = "HyperEdge")
public class HyperEdge
        extends HyperEdgeBase
        implements IMetadataContainer {

    @XmlElement(name = "Metadata")
    private final MetadataMapProperty metadata;


    @Override
    public void setId(String id) {

        super.setId(id);
    }

    @SuppressWarnings("unused")
    private HyperEdge() {

        this(null, null, null, null, true);
    }


    /**
     * Creates a new instance of the HyperEdge class.
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
     * @param nodes
     *            The node of the hyperedge.
     * @param schema
     *            The schema associated with the hyperedge.
     */
    protected HyperEdge(String id, IGraph graph, Set<INode> nodes, Schema schema) {

        this(id, graph, nodes, schema, false);
    }


    private HyperEdge(String id, IGraph graph, Set<INode> nodes, Schema schema,
            boolean serialization) {

        super(id, graph, HyperEdge.EndpointCreator(id, nodes, serialization),
                serialization);


        if (id == null && !serialization) {

            throw new IllegalArgumentException("A hyperedge must have an id.");
        }

        if (graph == null && !serialization) {

            throw new IllegalArgumentException(
                    "A hyperedge must be associated " + "with a graph.");
        }


        if (schema == null && !serialization) {

            throw new IllegalArgumentException(
                    "A hyperedge must have a schema.");
        }

        if (schema != null) {

            this.metadata = new MetadataMapProperty(schema);
        }
        else {

            this.metadata = null;
        }
    }


    private static Set<IEndpoint> EndpointCreator(String id, Set<INode> nodes,
            boolean serialization) {

        if (id == null && !serialization) {

            throw new IllegalArgumentException("A hyperedge must have an id.");
        }

        if ((nodes == null || nodes.size() < 2) && !serialization) {

            throw new IllegalArgumentException(
                    "A hyperedge must have at least " + "two nodes.");
        }

        Set<IEndpoint> endpoints = new HashSet<>();

        if (nodes != null) {
            for (INode node : nodes) {

                Endpoint endpoint = new Endpoint(id + '_' + node.getId(), node,
                        Boolean.FALSE, Boolean.FALSE);

                endpoints.add(endpoint);
            }
        }

        return endpoints;
    }

    @Override
    public Graph getGraph() {

        if (super.getGraph() instanceof Graph) {

            return (Graph) super.getGraph();
        }

        return null;
    }

    /**
     * Adds a node to the HyperEdge.
     * 
     * @param node
     *            the node to add.
     */
    protected void addNode(INode node) {

        if (node != null) {
            Endpoint endpoint = new Endpoint(this.getId() + '_' + node.getId(),
                    node, Boolean.FALSE, Boolean.FALSE);
            if (!this.getEndpoints().contains(endpoint)) {
                this.addEndpoint(endpoint);
            }
        }
    }

    /**
     * Removes the node from the HyperEdge.
     * 
     * @param node
     *            the node to remove.
     */
    protected void removeNode(INode node) {

        for (IEndpoint endpoint : this.getEndpoints()) {
            if (endpoint.getNode().equals(node)) {
                this.removeEndpoint(endpoint);
            }
        }
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

    /**
     * Clears all the nodes from the HyperEdge.
     */
    protected void clearNodes() {

        this.clearEndpoints();
    }

    @Override
    public MetadataMapProperty getMetadataProperty() {

        return this.metadata;
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

    @Override
    protected void addEndpoint(IEndpoint endpoint) {

        super.addEndpoint(endpoint);
    }

    @Override
    protected void clearEndpoints() {

        super.clearEndpoints();
    }

    @Override
    protected void removeEndpoint(IEndpoint endpoint) {

        super.removeEndpoint(endpoint);
    }

    @Override
    public boolean isIdentical(IHyperEdge other, boolean recurse) {

        if (super.isIdentical(other, recurse) && other instanceof HyperEdge) {

            HyperEdge otherHyperedge = (HyperEdge) other;

            boolean sameMetadata = this.getMetadataProperty().isIdentical(
                    otherHyperedge.getMetadataProperty());

            return sameMetadata;
        }

        return false;
    }

}
