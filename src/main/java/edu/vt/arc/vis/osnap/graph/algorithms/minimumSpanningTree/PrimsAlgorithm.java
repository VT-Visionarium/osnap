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


package edu.vt.arc.vis.osnap.graph.algorithms.minimumSpanningTree;


import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;

import org.jutility.common.datatype.tree.Tree;

import edu.vt.arc.vis.osnap.graph.algorithms.IWeight;
import edu.vt.arc.vis.osnap.graph.common.IEdge;
import edu.vt.arc.vis.osnap.graph.common.IGraph;
import edu.vt.arc.vis.osnap.graph.common.INode;
import edu.vt.arc.vis.osnap.graph.common.IUniverse;


/**
 * Prim'scale algorithm for calculating the minimum spanning tree of a graph.
 * <p/>
 * Input: A non-empty connected weighted graph with vertices V and edges E (the
 * weights can be negative). <br/>
 * Initialize: Vnew = {x}, where x is an arbitrary node (starting point) from V,
 * Enew = {} <br/>
 * Repeat until Vnew = V:
 * <ul>
 * <li>Choose an edge {u, v} with minimal weight such that u is in Vnew and v is
 * not (if there are multiple edges with the same weight, any of them may be
 * picked)</li>
 * <li>Add v to Vnew, and {u, v} to Enew</li>
 * </ul>
 * Output: Vnew and Enew describe a minimal spanning tree
 * <p/>
 * Thread Safety: immutable
 * 
 * @author Peter J. Radics
 * @version 1.0
 */
public class PrimsAlgorithm {

    private final Tree<INode> minimumSpanningTree;

    private final IWeight     weight;
    private final List<INode> nodes;
    private final List<IEdge> edges;

    private INode             rootNode;

    private boolean           isInitialized;



    /**
     * Returns the minimum spanning tree.
     * 
     * @return the minimum spanning tree.
     */
    public Tree<INode> getMinimumSpanningTree() {

        this.initialize();

        return this.minimumSpanningTree;
    }

    /**
     * Creates a new instance with the provided graph and weight.
     * 
     * @param graph
     *            the graph to analyze.
     * @param weight
     *            the weight of the edges.
     * @param rootNode
     *            the root node of the spanning tree. (If null, the first node
     *            of the collection of nodes is used as root.)
     */
    public PrimsAlgorithm(final IGraph graph, final IWeight weight,
            INode rootNode) {

        this(graph.getNodes(), graph.getEdges(), weight, rootNode);
    }


    /**
     * Creates a new instance with the provided universe and weight.
     * 
     * @param universe
     *            the universe to analyze.
     * @param weight
     *            the weight of the edges.
     * @param rootNode
     *            the root node of the spanning tree. (If null, the first node
     *            of the collection of nodes is used as root.)
     */
    public PrimsAlgorithm(final IUniverse universe, final IWeight weight,
            INode rootNode) {

        this(universe.getNodes(), universe.getEdges(), weight, rootNode);
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
     * @param rootNode
     *            the root node of the spanning tree. (If null, the first node
     *            of the collection of nodes is used as root.)
     */
    public PrimsAlgorithm(final Collection<? extends INode> nodes,
            final Collection<? extends IEdge> edges, final IWeight weight,
            final INode rootNode) {

        if (nodes == null || nodes.isEmpty()) {
            throw new IllegalArgumentException(
                    "Cannot execute Prim'scale algorithm without a "
                            + "set of nodes!");
        }
        if (edges == null || edges.isEmpty()) {
            throw new IllegalArgumentException(
                    "Cannot execute Prim'scale algorithm without a "
                            + "set of edges!");
        }

        if (weight == null) {
            throw new IllegalArgumentException(
                    "Cannot execute Prim'scale algorithm without "
                            + "edge/node weights!");
        }

        this.nodes = new LinkedList<>(nodes);
        this.edges = new LinkedList<>(edges);
        this.weight = weight;

        this.minimumSpanningTree = new Tree<>();

        if (rootNode == null) {
            this.rootNode = nodes.iterator().next();
        }
        else {
            this.rootNode = rootNode;
        }

        this.isInitialized = false;
    }


    private void initialize() {

        if (!this.isInitialized) {

            this.calculateMinimumSpanningTree();

            this.isInitialized = true;
        }
    }

    private void calculateMinimumSpanningTree() {

        Comparator<IEdge> comparator = new Comparator<IEdge>() {

            @Override
            public int compare(IEdge lhs, IEdge rhs) {

                Integer lhsWeight = weight.getWeight(lhs);
                Integer rhsWeight = weight.getWeight(rhs);
                return lhsWeight.compareTo(rhsWeight);
            }
        };
        LinkedList<IEdge> sortedEdges = new LinkedList<>();
        this.minimumSpanningTree.add(this.rootNode);
        this.nodes.remove(this.rootNode);
        sortedEdges.addAll(this.getWeightedEdges(this.rootNode.getEdges()));


        while (!sortedEdges.isEmpty()) {

            Collections.sort(sortedEdges, comparator);
            IEdge lightestEdge = sortedEdges.removeFirst();
            INode source = lightestEdge.getSource();
            INode target = lightestEdge.getTarget();

            INode treeNode = null;
            INode newNode = null;
            if (this.minimumSpanningTree.contains(source)) {
                treeNode = source;
                if (this.minimumSpanningTree.contains(target)) {

                    throw new RuntimeException("Both endpoints of edge "
                            + lightestEdge
                            + " are already contained in the Minimal "
                            + "Spanning Tree!");
                }
                else {
                    newNode = target;
                }
            }
            else if (this.minimumSpanningTree.contains(target)) {
                treeNode = target;
                if (this.minimumSpanningTree.contains(source)) {

                    throw new RuntimeException("Both endpoints of edge "
                            + lightestEdge
                            + " are already contained in the Minimal "
                            + "Spanning Tree!");
                }
                else {
                    newNode = source;
                }
            }
            else {

                throw new RuntimeException("Neither of the endpoints of edge "
                        + lightestEdge
                        + " are contained in the Minimal Spanning Tree!");
            }

            if (treeNode != null && newNode != null) {

                this.minimumSpanningTree.addChild(treeNode, newNode);
                sortedEdges.addAll(this.getWeightedEdges(newNode.getEdges()));

            }
        }
    }

    private List<IEdge> getWeightedEdges(Collection<? extends IEdge> edges) {

        LinkedList<IEdge> weightedEdges = new LinkedList<>();
        for (IEdge edge : edges) {
            if (this.weight.getWeight(edge) != null && this.edges.remove(edge)) {

                weightedEdges.add(edge);
            }
        }
        return weightedEdges;
    }
}
