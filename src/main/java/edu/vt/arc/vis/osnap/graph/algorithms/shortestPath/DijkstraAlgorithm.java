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


package edu.vt.arc.vis.osnap.graph.algorithms.shortestPath;


import edu.vt.arc.vis.osnap.graph.algorithms.IWeight;
import edu.vt.arc.vis.osnap.graph.common.IEdge;
import edu.vt.arc.vis.osnap.graph.common.IGraph;
import edu.vt.arc.vis.osnap.graph.common.INode;
import edu.vt.arc.vis.osnap.graph.common.IUniverse;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 
 * @author Peter J. Radics
 * @version 0.1
 */
public class DijkstraAlgorithm {


    private final Map<INode, Integer>     distances;
    private final Map<INode, List<INode>> shortestPaths;
    private final Map<INode, INode>       previousNodeAlongShortestPath;

    private final INode                   source;
    private final INode                   target;

    private final IWeight                 weight;

    private final Boolean                 traverseDirected;

    private final Collection<? extends INode>       nodes;


    /**
     * Creates a new instance with the provided graph, source, target, weight,
     * and directedness.
     * 
     * @param graph
     *            the graph to analyze.
     * @param source
     *            the source node.
     * @param target
     *            the target node.
     * @param weight
     *            the edge weight.
     * @param directed
     *            whether or not edges are only discovered in one direction.
     */
    public DijkstraAlgorithm(IGraph graph, INode source, INode target,
            IWeight weight, Boolean directed) {

        this(graph.getNodes(), source, target, weight, directed);
    }

    /**
     * Creates a new instance with the provided universe, source, target,
     * weight, and directedness.
     * 
     * @param universe
     *            the universe to analyze.
     * @param source
     *            the source node.
     * @param target
     *            the target node.
     * @param weight
     *            the edge weight.
     * @param directed
     *            whether or not edges are only discovered in one direction.
     */
    public DijkstraAlgorithm(IUniverse universe, INode source, INode target,
            IWeight weight, Boolean directed) {

        this(universe.getNodes(), source, target, weight, directed);
    }

    /**
     * Creates a new instance with the provided nodes, source, target, weight,
     * and directedness.
     * 
     * @param nodes
     *            the nodes to analyze.
     * @param source
     *            the source node.
     * @param target
     *            the target node.
     * @param weight
     *            the edge weight.
     * @param directed
     *            whether or not edges are only discovered in one direction.
     */
    public DijkstraAlgorithm(Collection<? extends INode> nodes, INode source,
            INode target, IWeight weight, Boolean directed) {

        this.nodes = nodes;

        this.source = source;
        this.target = target;

        this.weight = weight;

        this.traverseDirected = directed;

        this.distances = new HashMap<>();
        this.shortestPaths = new HashMap<>();
        this.previousNodeAlongShortestPath = new HashMap<>();

    }

    private void initializeDistances() {

        for (INode node : nodes) {
            this.distances.put(node, Integer.MAX_VALUE);
        }
    }

    /**
     * Returns the distance of the node from the source.
     * 
     * @param node
     *            the node.
     * @return the distance.
     */
    public Integer getDistanceFromSource(INode node) {

        // If the target is null, the entire graph has been traversed and a
        // valid distance is available for all nodes.
        if (this.target == null) {
            if (this.distances.size() != this.nodes.size()) {

                this.findShortestPath();
            }

            return this.distances.get(node);
        }
        // If the target is not null, a valid distance can only be guaranteed
        // for the target. Therefore, a value is only returned for the target.
        else if (target.equals(node)) {
            if (this.distances.size() != this.nodes.size()) {

                this.findShortestPath();

            }
            return this.distances.get(node);
        }
        // No value is returned in the case that a target node exists and the
        // queried node is
        else {
            throw new IllegalArgumentException(
                    "Cannot return valid distance "
                            + "value for the node since a target node exists and is not"
                            + "equal to the node in question. A valid distance can only"
                            + "be guaranteed for the target node!");
        }


    }

    /**
     * Returns the shortest path from the source to the node.
     * 
     * @param node
     *            the node.
     * @return the shortest path.
     */
    public List<INode> getShortestPathFromSource(INode node) {

        if (this.shortestPaths.containsKey(node)) {

            return this.shortestPaths.get(node);

        }
        else {

            return this.createShortestPathForNode(node);

        }
    }


    private List<INode> createShortestPathForNode(INode node) {

        List<INode> path = null;

        // Termination of the recursion.
        // Creating a list containing the source node.
        if (node.equals(this.source)) {

            path = new LinkedList<>();

            path.add(0, node);


        }
        else {
            INode previousNode = this.previousNodeAlongShortestPath.get(node);

            // Checking whether there is a path to the source. If not, the
            // previous node is null.
            if (previousNode != null) {

                List<INode> previousPath;

                // Checking whether the path for the previous node has already
                // been created.
                if (this.shortestPaths.containsKey(previousNode)) {

                    // path for previous node is available.
                    previousPath = this.shortestPaths.get(previousNode);

                }

                else {
                    // path for previous node needs to be created.
                    previousPath = createShortestPathForNode(previousNode);

                }

                // Making a copy of the previous path
                path = new LinkedList<>(previousPath);

                // Adding the current node to the previous path.
                this.shortestPaths.put(node, path);
            }
        }

        // saving the shortest path for the node for future use.
        // Note: if the previous node is null, the path itself will be null as
        // well!
        this.shortestPaths.put(node, path);

        return path;
    }


    private void findShortestPath() {

        this.initializeDistances();

        distances.put(source, 0);


        Set<INode> candidateNodes = new HashSet<>(nodes);

        INode currentNode = source;
        while (!candidateNodes.isEmpty()) {
            Integer smallestDistance = Integer.MAX_VALUE;

            // Finding the node with the shortest distance to the source node.
            // Initially, this will be the source node itself.
            // O(|V|)
            for (INode candidateNode : candidateNodes) {
                if (distances.get(candidateNode) < smallestDistance) {
                    currentNode = candidateNode;
                    smallestDistance = distances.get(candidateNode);
                }
            }

            // Removing the current node from the pool of candidate nodes.
            candidateNodes.remove(currentNode);

            // Check to see whether there is no node left that is connected to
            // the source.
            if (smallestDistance == Integer.MAX_VALUE) {
                break;
            }

            // Recalculating the distance from the source for the current node'scale
            // neighbors.
            // O(|currentNode.getEdges()|)
            for (IEdge edge : currentNode.getEdges()) {

                Boolean currentNodeIsTarget = false;
                INode candidateNode;

                if (currentNode.equals(edge.getSource())) {
                    candidateNode = edge.getTarget();

                }
                else {
                    candidateNode = edge.getSource();
                    currentNodeIsTarget = true;
                }

                // We don't have to change anything if the traversal is directed
                // and the current node is not the source of the edge.
                if (candidateNodes.contains(candidateNode)
                        && !(traverseDirected && currentNodeIsTarget)) {

                    Integer tentativeDistance = distances.get(currentNode)
                            + weight.getWeight(edge);

                    if (tentativeDistance < distances.get(candidateNode)) {
                        distances.put(candidateNode, tentativeDistance);

                        // We're setting the previous node on the shortest path
                        // from the source so we can reconstruct the shortest
                        // path for each node.
                        previousNodeAlongShortestPath.put(candidateNode,
                                currentNode);
                    }
                }

            }

            // If we are only interested in the shortest path to the target, we
            // quit looping once we reach it.
            if (currentNode.equals(target)) {
                break;
            }

        }
    }

}
