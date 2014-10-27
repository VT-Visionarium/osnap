package edu.vt.arc.vis.osnap.core.domain.graph.base;


import edu.vt.arc.vis.osnap.core.domain.DomainObject;
import edu.vt.arc.vis.osnap.core.domain.graph.Edge;
import edu.vt.arc.vis.osnap.core.domain.graph.Endpoint;
import edu.vt.arc.vis.osnap.core.domain.graph.Graph;
import edu.vt.arc.vis.osnap.core.domain.graph.HyperEdge;
import edu.vt.arc.vis.osnap.core.domain.graph.Node;
import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.core.domain.graph.common.*;
import edu.vt.arc.vis.osnap.events.domain.graph.UniverseDetails;

import java.util.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import org.jutility.common.datatype.map.ListMapWrapper;


/**
 * This class represents a graph universe, containing multiple graphs and
 * metadata pertaining to this universe.
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
@XmlRootElement(name = "UniverseBase")
@XmlSeeAlso({ Universe.class, GraphBase.class, Graph.class, NodeBase.class,
        Node.class, EdgeBase.class, Edge.class, HyperEdgeBase.class,
        HyperEdge.class, EndpointBase.class, Endpoint.class })
@XmlType(name = "UniverseBase", propOrder = { "serializableGraphs",
        "serializableNodes", "serializableEdges", "serializableHyperEdges" })
@XmlAccessorType(XmlAccessType.NONE)
public abstract class UniverseBase
        extends DomainObject
        implements IUniverse {

    private final Set<String>             ids;


    private final Map<String, IGraph>     graphMap;

    private final Map<String, INode>      nodeMap;
    private final Map<String, IEdge>      edgeMap;
    private final Map<String, IHyperEdge> hyperEdgeMap;

    @Override
    public void setId(String id) {

        if (this.changeID(this, id)) {

            super.setId(id);
        }
    }


    @Override
    public boolean changeID(IGraphObject graphObject, String newValue) {

        if (graphObject != null) {

            if (newValue == null) {

                throw new IllegalArgumentException("ID cannot be null!");
            }

            String oldValue = graphObject.getId();

            if (newValue.equals(oldValue)) {

                return false;
            }

            if (this.containsID(newValue)) {

                throw new IllegalArgumentException("ID " + newValue
                        + " is already in use!");
            }


            this.ids.remove(oldValue);
            this.ids.add(newValue);

            return true;
        }

        return false;
    }

    @Override
    public boolean containsID(String id) {

        return this.ids.contains(id);
    }

    /**
     * Getter for the graph map.
     * 
     * The graph map maps a graph'scale id to the actual graph for quick access.
     * 
     * @return The graph map (unmodifiable).
     */
    public Map<String, ? extends IGraph> getGraphMap() {

        return Collections.unmodifiableMap(graphMap);
    }

    /**
     * Getter for the node map.
     * 
     * The node map maps a node'scale id to the actual node for quick access.
     * 
     * @return The node map (unmodifiable).
     */
    public Map<String, INode> getNodeMap() {

        return Collections.unmodifiableMap(nodeMap);
    }

    /**
     * Getter for the edge map.
     * 
     * The edge map maps a edge'scale id to the actual edge for quick access.
     * 
     * @return The edge map (unmodifiable).
     */
    public Map<String, IEdge> getEdgeMap() {

        return Collections.unmodifiableMap(edgeMap);
    }

    /**
     * Getter for the hyperedge map.
     * 
     * The hyperedge map maps a hyperedge'scale id to the actual hyperedge for
     * quick access.
     * 
     * @return The hyper map (unmodifiable).
     */
    public Map<String, IHyperEdge> getHyperEdgeMap() {

        return Collections.unmodifiableMap(hyperEdgeMap);
    }


    @SuppressWarnings("unused")
    private UniverseBase() {

        this(null, true);
    }

    /**
     * Creates a new instance with the provided id.
     * 
     * @param id
     *            the id.
     */
    public UniverseBase(String id) {

        this(id, false);
    }


    private UniverseBase(String id, boolean serialization) {

        super(id, serialization);

        this.ids = new HashSet<>();
        if (id != null) {

            this.ids.add(id);
        }

        graphMap = new LinkedHashMap<>();
        nodeMap = new LinkedHashMap<>();
        edgeMap = new LinkedHashMap<>();
        hyperEdgeMap = new LinkedHashMap<>();

    }

    /**
     * Creates a new instance of the {@link UniverseBase} class from the
     * provided details.
     * 
     * @param details
     *            the details.
     */
    public UniverseBase(final UniverseDetails details) {

        super(details);

        this.ids = new HashSet<>();

        if (this.getId() != null) {

            this.ids.add(this.getId());
        }
        graphMap = new LinkedHashMap<>();
        nodeMap = new LinkedHashMap<>();
        edgeMap = new LinkedHashMap<>();
        hyperEdgeMap = new LinkedHashMap<>();
    }

    /**
     * Adds the provided graph to the collection of graphs.
     * 
     * @param graph
     *            the graph to add.
     */
    public void addGraph(IGraph graph) {

        if (graph != null) {

            this.ids.add(graph.getId());
            this.graphMap.put(graph.getId(), graph);
        }
    }

    /**
     * Deletes the provided graph from the collection of graphs.
     * 
     * @param graph
     *            the graph to delete.
     */
    public void deleteGraph(IGraph graph) {

        if (graph != null) {

            this.ids.remove(graph.getId());
            this.graphMap.remove(graph.getId());
        }
    }

    /**
     * Clears the collection of graphs.
     */
    protected void clearGraphMap() {

        for (String id : this.graphMap.keySet()) {

            this.ids.remove(id);
        }
        this.graphMap.clear();
    }

    /**
     * Adds a node to the collection of nodes.
     * 
     * @param node
     *            the node to add.
     */
    public void addNode(INode node) {

        if (node != null) {

            this.ids.add(node.getId());
            this.nodeMap.put(node.getId(), node);
        }
    }

    /**
     * Deletes a node from the collection of nodes.
     * 
     * @param node
     *            the node to delete.
     */
    public void deleteNode(INode node) {

        if (node != null) {

            this.ids.remove(node.getId());
            this.nodeMap.remove(node.getId());
        }
    }

    /**
     * Clears the collection of nodes.
     */
    protected void clearNodeMap() {

        for (String id : this.nodeMap.keySet()) {

            this.ids.remove(id);
        }
        this.nodeMap.clear();
    }

    /**
     * Adds an edge to the collection of edges.
     * 
     * @param edge
     *            the edge to add.
     */
    public void addEdge(IEdge edge) {

        if (edge != null) {

            this.ids.add(edge.getId());
            this.edgeMap.put(edge.getId(), edge);
        }
    }

    /**
     * Deletes an edge from the collection of edges.
     * 
     * @param edge
     *            the edge to delete.
     */
    public void deleteEdge(IEdge edge) {

        if (edge != null) {

            this.ids.remove(edge.getId());
            this.edgeMap.remove(edge.getId());
        }
    }

    /**
     * Clears the collection of edges.
     */
    protected void clearEdgeMap() {

        for (String id : this.edgeMap.keySet()) {

            this.ids.remove(id);
        }
        this.edgeMap.clear();
    }

    /**
     * Adds a hyperedge to the collection of hyperedges.
     * 
     * @param hyperedge
     *            the hyperedge to add.
     */
    public void addHyperEdge(IHyperEdge hyperedge) {

        if (hyperedge != null) {

            this.ids.add(hyperedge.getId());
            this.hyperEdgeMap.put(hyperedge.getId(), hyperedge);
        }
    }

    /**
     * Deletes a hyperedge from the collection of hyperedges.
     * 
     * @param hyperedge
     *            the hyperedge to delete.
     */
    public void deleteHyperEdge(IHyperEdge hyperedge) {

        if (hyperedge != null) {

            this.ids.remove(hyperedge.getId());
            this.hyperEdgeMap.remove(hyperedge.getId());
        }
    }

    /**
     * Clears the collection of hyperedges.
     */
    protected void clearHyperEdgeMap() {

        for (String id : this.hyperEdgeMap.keySet()) {

            this.ids.remove(id);
        }
        this.hyperEdgeMap.clear();
    }


    /**
     * Getter for the graphs.
     * 
     * @return An unmodifiable collection of the graphs.
     */
    @Override
    public Collection<? extends IGraph> getGraphs() {

        return new LinkedList<>(
                Collections.unmodifiableCollection(this.graphMap.values()));
    }

    @XmlElements({ @XmlElement(name = "Graph", type = Graph.class) })
    @XmlElementWrapper(name = "Graphs")
    private Collection<? extends IGraph> getSerializableGraphs() {

        return new ListMapWrapper<>(this.graphMap, "getId", String.class);
    }

    @Override
    public IGraph getGraph(String id) {

        return this.getGraphMap().get(id);
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


    @XmlElements({ @XmlElement(name = "Node", type = Node.class) })
    @XmlElementWrapper(name = "Nodes")
    private Collection<? extends INode> getSerializableNodes() {

        return new ListMapWrapper<>(this.nodeMap, "getId", String.class);
    }



    @Override
    public INode getNode(String id) {

        return this.getNodeMap().get(id);
    }

    /**
     * Getter for the edges.
     * 
     * @return An unmodifiable collection of the edges.
     */
    @Override
    public Collection<? extends IEdge> getEdges() {

        return new LinkedList<>(Collections.unmodifiableCollection(this.edgeMap
                .values()));
    }

    @XmlElements({ @XmlElement(name = "Edge", type = Edge.class) })
    @XmlElementWrapper(name = "Edges")
    private Collection<? extends IEdge> getSerializableEdges() {

        return new ListMapWrapper<>(this.edgeMap, "getId", String.class);
    }


    @Override
    public IEdge getEdge(String id) {

        return this.getEdgeMap().get(id);
    }

    /**
     * Getter for the hyperedges.
     * 
     * @return An unmodifiable collection of the hyperedges.
     */
    @Override
    public Collection<? extends IHyperEdge> getHyperEdges() {

        return new LinkedList<>(
                Collections.unmodifiableCollection(this.hyperEdgeMap.values()));
    }


    @XmlElements({ @XmlElement(name = "HyperEdge", type = HyperEdge.class) })
    @XmlElementWrapper(name = "HyperEdges")
    private Collection<? extends IHyperEdge> getSerializableHyperEdges() {

        return new ListMapWrapper<>(this.hyperEdgeMap, "getId", String.class);
    }


    @Override
    public IHyperEdge getHyperEdge(String id) {

        return this.getHyperEdgeMap().get(id);
    }

    @Override
    public long numberOfGraphs() {

        return this.graphMap.size();
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

        return Collections.unmodifiableSet(EnumSet.of(
                GraphObjectProperty.NUMBER_OF_GRAPHS, GraphObjectProperty.SIZE,
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
            case NUMBER_OF_GRAPHS:
                return this.numberOfGraphs();
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
                        + " is not supported by this universe.");

        }
    }


    @Override
    public boolean isIdentical(IGraphObject other) {

        if (this.equals(other)) {
            if (other instanceof IUniverse) {

                IUniverse otherUniverse = (IUniverse) other;

                boolean sameNumberOfGraphs = this.getGraphs().size() == otherUniverse
                        .getGraphs().size();
                // System.out.println("Graph sizes: this="
                // + this.getGraphs().size() + ", other="
                // + otherUniverse.getGraphs().size());
                boolean sameNumberOfNodes = this.getNodes().size() == otherUniverse
                        .getNodes().size();
                boolean sameNumberOfEdges = this.getEdges().size() == otherUniverse
                        .getEdges().size();
                boolean sameNumberOfHyperEdges = this.getHyperEdges().size() == otherUniverse
                        .getHyperEdges().size();

                if (!(sameNumberOfGraphs && sameNumberOfNodes
                        && sameNumberOfEdges && sameNumberOfHyperEdges)) {
                    // System.out.println("Sizes don't match in universe!");
                    return false;
                }

                for (IGraph graph : this.getGraphs()) {

                    boolean identical = false;
                    for (IGraph otherGraph : otherUniverse.getGraphs()) {

                        identical = graph.isIdentical(otherGraph);
                        if (identical) {

                            break;
                        }
                    }
                    if (!identical) {

                        return false;
                    }

                }


                for (INode node : this.getNodes()) {

                    boolean identical = false;
                    for (INode otherNode : otherUniverse.getNodes()) {

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
                    for (IEdge otherEdge : otherUniverse.getEdges()) {

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
                    for (IHyperEdge otherHyperEdge : otherUniverse
                            .getHyperEdges()) {

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
        }
        return false;
    }

    @Override
    public UniverseDetails toDetails() {

        UniverseDetails details = new UniverseDetails(super.toDetails());

        details.setIds(this.ids);
        
        return details;
    }
}
