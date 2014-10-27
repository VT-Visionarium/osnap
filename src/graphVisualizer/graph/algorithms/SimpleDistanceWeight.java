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


package graphVisualizer.graph.algorithms;


import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import graphVisualizer.graph.common.IGraph;

import graphVisualizer.graph.common.IUniverse;

import graphVisualizer.graph.common.IEdge;
import graphVisualizer.graph.common.IHyperEdge;
import graphVisualizer.graph.common.INode;


/**
 * The {@link SimpleDistanceWeight} class provides an implementation of the
 * {@link IWeight} interface, setting the weight of {@link INode Nodes} and
 * {@link IEdge Edges} based on their distance to a provided {@link INode root
 * node}. {@link IHyperEdge Hyperedges} are currently not supported.
 * <p/>
 * Note: The algorithm assumes unweighted edges, i.e., that a path of length n
 * is always shorter than a path of length n+1.
 *
 * @author Peter J. Radics
 * @version 0.1
 */
public class SimpleDistanceWeight
        implements IWeight {

    private final List<INode>         nodes;
    private final List<IEdge>         edges;

    private final INode               rootNode;

    private final Map<INode, Integer> nodeWeightMap;
    private final Map<IEdge, Integer> edgeWeightMap;

    private final boolean             directed;
    private final boolean             invertPath;

    private boolean                   initialized;




    /**
     * Creates a new instance of the {@link SimpleDistanceWeight} class with the
     * provided parameters.
     *
     * @param rootNode
     *            the root node.
     * @param universe
     *            the universe containing the nodes and edges to use for
     *            calculation.
     */
    public SimpleDistanceWeight(INode rootNode, IUniverse universe) {

        this(rootNode, universe.getNodes(), universe.getEdges());
    }

    /**
     * Creates a new instance of the {@link SimpleDistanceWeight} class with the
     * provided parameters.
     *
     * @param rootNode
     *            the root node.
     * @param universe
     *            the universe containing the nodes and edges to use for
     *            calculation.
     * @param directed
     *            whether or not edges are directed.
     * @param invertPath
     *            whether or not to invert the direction of the path from the
     *            root node to each node.
     */
    public SimpleDistanceWeight(INode rootNode, IUniverse universe,
            boolean directed, boolean invertPath) {

        this(rootNode, universe.getNodes(), universe.getEdges(), directed,
                invertPath);
    }

    /**
     * Creates a new instance of the {@link SimpleDistanceWeight} class with the
     * provided parameters.
     *
     * @param rootNode
     *            the root node.
     * @param graph
     *            the graph containing the nodes and edges to use for
     *            calculation.
     */
    public SimpleDistanceWeight(INode rootNode, IGraph graph) {

        this(rootNode, graph.getNodes(), graph.getEdges());
    }

    /**
     * Creates a new instance of the {@link SimpleDistanceWeight} class with the
     * provided parameters.
     *
     * @param rootNode
     *            the root node.
     * @param graph
     *            the graph containing the nodes and edges to use for
     *            calculation.
     * @param directed
     *            whether or not edges are directed.
     * @param invertPath
     *            whether or not to invert the direction of the path from the
     *            root node to each node.
     */
    public SimpleDistanceWeight(INode rootNode, IGraph graph, boolean directed,
            boolean invertPath) {

        this(rootNode, graph.getNodes(), graph.getEdges(), directed, invertPath);
    }


    /**
     * Creates a new instance of the {@link SimpleDistanceWeight} class with the
     * provided parameters.
     *
     * @param rootNode
     *            the root node.
     * @param nodes
     *            the nodes to use for calculation.
     * @param edges
     *            the edges to use for calculation.
     */
    public SimpleDistanceWeight(INode rootNode, Collection<? extends INode> nodes,
            Collection<? extends IEdge> edges) {

        this(rootNode, nodes, edges, true, false);
    }

    /**
     * Creates a new instance of the {@link SimpleDistanceWeight} class with the
     * provided parameters.
     *
     * @param rootNode
     *            the root node.
     * @param nodes
     *            the nodes to use for calculation.
     * @param edges
     *            the edges to use for calculation.
     * @param directed
     *            whether or not edges are directed.
     * @param invertPath
     *            whether or not to invert the direction of the path from the
     *            root node to each node.
     */
    public SimpleDistanceWeight(INode rootNode, Collection<? extends INode> nodes,
            Collection<? extends IEdge> edges, boolean directed, boolean invertPath) {

        this.nodes = new LinkedList<>(nodes);
        this.edges = new LinkedList<>(edges);

        this.rootNode = rootNode;

        this.nodeWeightMap = new LinkedHashMap<>();
        this.edgeWeightMap = new LinkedHashMap<>();

        this.initialized = false;

        this.directed = directed;
        this.invertPath = invertPath;
    }

    // ----------------------------------------------------------
    @Override
    public Integer getWeight(IEdge edge) {

        if (!this.initialized) {
            this.initialize();
        }
        if (this.edgeWeightMap.containsKey(edge)) {

            return this.edgeWeightMap.get(edge);
        }
        return null;
    }

    // ----------------------------------------------------------
    @Override
    public Integer getWeight(IHyperEdge hyperedge) {

        throw new UnsupportedOperationException(
                "Hyperedges currently not supported!");
    }

    // ----------------------------------------------------------
    @Override
    public Integer getWeight(INode node) {

        if (!this.initialized) {
            this.initialize();
        }
        if (this.nodeWeightMap.containsKey(node)) {

            return this.nodeWeightMap.get(node);
        }

        return null;
    }

    private void initialize() {

        this.initialized = true;
        this.nodeWeightMap.put(this.rootNode, 0);



        LinkedList<INode> queue = new LinkedList<>();

        queue.push(rootNode);
        this.nodes.remove(rootNode);



        while (!queue.isEmpty()) {

            INode node = queue.removeFirst();
            this.calculateWeight(node, queue);
        }


    }

    private void calculateWeight(final INode node, LinkedList<INode> queue) {

        Integer nodeWeight = this.getWeight(node);
        Integer childWeight = nodeWeight + 1;

        for (IEdge edge : node.getEdges()) {
            if (this.edges.remove(edge)) {


                INode connectedNode = this.getConnectedNode(edge, node);
                if (this.nodes.remove(connectedNode)) {

                    queue.addLast(connectedNode);
                    this.nodeWeightMap.put(connectedNode, childWeight);
                    this.edgeWeightMap.put(edge, childWeight);
                }
            }
        }
    }

    private INode getConnectedNode(IEdge edge, INode node) {


        if (this.directed) {

            if (!this.invertPath) {
                if (edge.getSource().equals(node)) {

                    return edge.getTarget();
                }
            }
            else {
                if (edge.getTarget().equals(node)) {

                    return edge.getSource();
                }
            }
        }
        else {
            if (edge.getSource().equals(node)) {

                return edge.getTarget();
            }
            return edge.getSource();
        }

        return null;
    }
}
