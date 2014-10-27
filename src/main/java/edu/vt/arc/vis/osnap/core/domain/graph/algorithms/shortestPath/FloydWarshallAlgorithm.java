/*******************************************************************************
 * Copyright 2014 Virginia Tech Visionarium
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/


package edu.vt.arc.vis.osnap.core.domain.graph.algorithms.shortestPath;


import edu.vt.arc.vis.osnap.core.domain.graph.algorithms.IWeight;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IEdge;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraph;
import edu.vt.arc.vis.osnap.core.domain.graph.common.INode;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IUniverse;

import java.util.*;

import org.jutility.common.datatype.map.KeyValuePair;


/**
 * Floyd-Warshall algorithm for determining the shortest path between two nodes
 * in a graph.
 * <p/>
 * Thread Safety: immutable
 *
 * @author Peter J. Radics
 * @version 0.1
 */
public class FloydWarshallAlgorithm {

    private final Map<KeyValuePair<INode, INode>, Integer>     distances;
    private final Map<KeyValuePair<INode, INode>, List<INode>> shortestPaths;
    private final Map<KeyValuePair<INode, INode>, INode>       intermediateNodeAlongShortestPath;
    private boolean                                            isInitialized;
    private final IWeight                                      weight;
    private final Collection<? extends INode>                            nodes;
    private final Collection<? extends IEdge>                            edges;

    /**
     * Returns the distances between two INodes.
     *
     * @return the distances.
     */
    protected Map<KeyValuePair<INode, INode>, Integer> getDistances() {

        return this.distances;
    }


    /**
     * Returns the weight of the edges.
     *
     * @return the weight.
     */
    protected IWeight getWeight() {

        return this.weight;
    }

    /**
     * Returns the edges.
     *
     * @return the edges.
     */
    protected Collection<IEdge> getEdges() {

        return Collections.unmodifiableCollection(this.edges);
    }


    /**
     * Creates a new instance with the provided graph and weight.
     *
     * @param graph
     *            the graph to analyze.
     * @param weight
     *            the weight of the edges.
     */
    public FloydWarshallAlgorithm(final IGraph graph, final IWeight weight) {

        this(graph.getNodes(), graph.getEdges(), weight);
    }


    /**
     * Creates a new instance with the provided universe and weight.
     *
     * @param universe
     *            the universe to analyze.
     * @param weight
     *            the weight of the edges.
     */
    public FloydWarshallAlgorithm(final IUniverse universe, final IWeight weight) {

        this(universe.getNodes(), universe.getEdges(), weight);
    }


    /**
     * Create an instance of this class by describing the graph upon which it
     * will operate.
     *
     * @param nodes
     *            Collection of Nodes; must be completely populated
     * @param edges
     *            Collection of Edges, completely populated; order is not
     *            important
     * @param weight
     *            the weight of the edges.
     */
    public FloydWarshallAlgorithm(final Collection<? extends INode> nodes,
            final Collection<? extends IEdge> edges, final IWeight weight) {

        if (nodes == null || nodes.isEmpty()) {
            throw new IllegalArgumentException(
                    "Cannot execute the Floyd-Warshall algorithm without a "
                            + "set of nodes!");
        }
        if (edges == null || edges.isEmpty()) {
            throw new IllegalArgumentException(
                    "Cannot execute the Floyd-Warshall algorithm without a "
                            + "set of edges!");
        }

        if (weight == null) {
            throw new IllegalArgumentException(
                    "Cannot execute the Floyd-Warshall algorithm without "
                            + "edge/node weights!");
        }

        this.nodes = nodes;
        this.edges = edges;
        this.weight = weight;

        this.distances = new HashMap<>();
        this.intermediateNodeAlongShortestPath = new HashMap<>();
        this.shortestPaths = new HashMap<>();

        this.isInitialized = false;


    }


    /**
     * Initializes the distances.
     */
    protected void initializeDistances() {

        System.out.println("  Number of nodes: " + this.nodes.size());
        System.out.println("  Number of edges: " + this.edges.size());
        for (IEdge edge : edges) {
            KeyValuePair<INode, INode> edgePath = new KeyValuePair<>(
                    edge.getSource(), edge.getTarget());

            if (!this.distances.keySet().contains(edgePath)) {
                this.distances.put(edgePath, this.weight.getWeight(edge));
            }
        }
        System.out.println("Number of unique edges: " + this.distances.size());
    }

    private void initialize() {

        if (!this.isInitialized) {

            System.out.print("Initializing distances.");
            this.initializeDistances();
            System.out.print(" Done.\nCalculating Distances.");
            this.calculateDistances();
            System.out.println(" Done.");

            this.isInitialized = true;
        }
    }

    /**
     * Determines the length of the shortest path from vertex A (source) to
     * vertex B (target), calculated by summing the weights of the edges
     * traversed.
     * <p>
     * Note that distance, like path, is not commutative. That is, distance(A,B)
     * is not necessarily equal to distance(B,A).
     *
     * @param source
     *            Start Node
     * @param target
     *            End Node
     *
     * @return The path length as the sum of the weights of the edges traversed,
     *         or <code>null</code> if there is no path
     */
    public Integer getShortestDistance(final INode source, final INode target) {


        this.initialize();


        KeyValuePair<INode, INode> path = new KeyValuePair<>(
                source, target);

        return this.distances.get(path);
    }


    /**
     * Describes the shortest path from vertex A (source) to vertex B (target)
     * by returning a collection of the vertices traversed, in the order
     * traversed. If there is no such path an empty collection is returned.
     * <p>
     * Note that because each IEdge applies only to one direction of traversal,
     * the path from A to B may not be the same as the path from B to A.
     *
     * @param source
     *            Start node
     * @param target
     *            End node
     *
     * @return A List (ordered Collection) of INode, possibly empty
     */
    public List<INode> getShortestPath(final INode source, final INode target) {

        this.initialize();


        KeyValuePair<INode, INode> edge = new KeyValuePair<>(
                source, target);

        // Check whether there is a path between the source and the target
        if (!this.distances.containsKey(edge)) {

            // There is no path. return an empty list.
            return new ArrayList<>();

        }

        // Check whether we have already found the shortest path
        if (this.shortestPaths.containsKey(edge)) {

            // return the shortest path if so
            return this.shortestPaths.get(edge);
        }


        final List<INode> path = new ArrayList<>();

        path.add(source);

        path.addAll(getIntermediatePath(source, target));

        path.add(target);

        this.shortestPaths.put(edge, path);

        return path;
    }

    private void calculateDistances() {

        for (INode intermediateNode : this.nodes) {

            for (INode sourceNode : this.nodes) {

                for (INode targetNode : this.nodes) {
                    KeyValuePair<INode, INode> sourceToIntermediate = new KeyValuePair<>(
                            sourceNode, intermediateNode);

                    KeyValuePair<INode, INode> intermediateToTarget = new KeyValuePair<>(
                            intermediateNode, targetNode);

                    KeyValuePair<INode, INode> sourceToTarget = new KeyValuePair<>(
                            sourceNode, targetNode);

                    // if there is an edge between source and intermediate node
                    // and if there is an edge between intermediate and target
                    // node and if the length of the path from the source node
                    // over the intermediate node to the target node is shorter
                    // than the direct path from source to target, set the
                    // distance from source to target to the length of the path
                    // from source over intermediate to target node. Finally,
                    // set the intermediate node for the path from source to
                    // node target
                    if (this.distances.containsKey(sourceToIntermediate)
                            && this.distances.containsKey(intermediateToTarget)) {

                        Integer distanceSourceIntermediateTarget = this.distances
                                .get(sourceToIntermediate)
                                + this.distances.get(intermediateToTarget);


                        Integer distanceSourceTarget = Integer.MAX_VALUE;

                        if (this.distances.containsKey(sourceToTarget)) {

                            distanceSourceTarget = this.distances
                                    .get(sourceToTarget);

                        }


                        if (distanceSourceIntermediateTarget < distanceSourceTarget) {

                            this.distances.put(sourceToTarget,
                                    distanceSourceIntermediateTarget);

                            this.intermediateNodeAlongShortestPath.put(
                                    sourceToTarget, intermediateNode);
                        }
                    }
                }

            }
        }

//        String formatted = String.format("    Iterations: %,9d", i);
//        System.out.println(formatted);
    }


    private List<INode> getIntermediatePath(final INode source,
            final INode target) {

        KeyValuePair<INode, INode> edge = new KeyValuePair<>(
                source, target);

        if (this.intermediateNodeAlongShortestPath.get(edge) == null) {

            return new ArrayList<>();

        }

        INode intermediateNode = this.intermediateNodeAlongShortestPath
                .get(edge);
        final List<INode> path = new ArrayList<>();

        path.addAll(getIntermediatePath(source, intermediateNode));

        path.add(intermediateNode);

        path.addAll(getIntermediatePath(intermediateNode, target));

        return path;
    }
}
