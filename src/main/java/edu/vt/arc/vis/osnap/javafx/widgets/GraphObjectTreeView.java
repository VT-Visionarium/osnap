package edu.vt.arc.vis.osnap.javafx.widgets;


//@formatter:off
/*
* _
* The Open Semantic Network Analysis Platform (OSNAP)
* _
* Copyright (C) 2012 - 2015 Visionarium at Virginia Tech
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
//@formatter:on



import java.util.Collection;

import edu.vt.arc.vis.osnap.core.domain.graph.Graph;
import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IEdge;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraph;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IHyperEdge;
import edu.vt.arc.vis.osnap.core.domain.graph.common.INode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;


/**
 * Tree view, to view the hierarchy of all graphs in a universe, all nodes in a
 * graph, and all edges connected to a showNodes. Also shows all nodes and edges
 * in the universe listed below the tree hierarchy
 * 
 * @author Shawn P Neuman
 * @version 1.2.0
 * @since 0.5.0
 */
public class GraphObjectTreeView
        extends TreeView<Object> {

    private TreeItem<Object> root;
    private TreeItem<Object> tree_graphs;
    private TreeItem<Object> tree_nodes;
    private TreeItem<Object> tree_edges;
    private TreeItem<Object> tree_hyper_edges;

    private boolean          showNodes;
    private boolean          showEdges;
    private boolean          showHyperEdges;

    private Universe         universe;


    /**
     * constructor for tree
     * 
     */
    public GraphObjectTreeView() {

        this.showNodes = false;
        this.showEdges = false;
        this.showHyperEdges = false;

        this.root = new TreeItem<>("Universe");
        this.setRoot(root);
    }


    // ----------------------------------------------------------
    /**
     * Reads and parses the file information line by line
     * 
     * @param universe
     *            the universe used to populate the tree
     * @param filter
     *            the filter string.
     * @param showNodes
     *            whether or not to show the nodes.
     * @param showEdges
     *            whether or not to show the edges.
     * @param showHyperEdges
     *            whether or not to show the hyperedges.
     */
    public void populate(Universe universe, String filter, boolean showNodes,
            boolean showEdges, boolean showHyperEdges) {

        this.root = new TreeItem<>(universe);
        this.setRoot(root);

        this.showNodes = showNodes;
        this.showEdges = showEdges;
        this.showHyperEdges = showHyperEdges;


        this.root.getChildren().clear();

        // parsing file and creating a collection of graphs
        this.universe = universe;

        tree_graphs = new TreeItem<>("Graphs");
        root.getChildren().add(tree_graphs);
        if (this.showNodes) {

            tree_nodes = new TreeItem<>("Nodes (" + universe.getNodes().size()
                    + ")");
            root.getChildren().add(tree_nodes);
        }
        if (this.showEdges) {
            tree_edges = new TreeItem<>("Edges (" + universe.getEdges().size()
                    + ")");
            root.getChildren().add(tree_edges);
        }
        if (this.showHyperEdges) {
            tree_hyper_edges = new TreeItem<>("HyperEdges ("
                    + universe.getHyperEdges().size() + ")");
            root.getChildren().add(tree_hyper_edges);
        }


        Collection<Graph> graphs = universe.getGraphs();
        for (Graph graph : graphs) {

            parseGraph(graph, tree_graphs, filter);
        }

        if (showNodes) {

            for (INode node : universe.getNodes()) {

                parseNode(node, tree_nodes, filter);
            }
        }

        if (showEdges) {

            for (IEdge edge : universe.getEdges()) {

                parseEdge(edge, tree_edges, filter);
            }
        }
        if (showHyperEdges) {

            for (IHyperEdge hyperEdge : universe.getHyperEdges()) {

                parseHyperEdge(hyperEdge, tree_hyper_edges, filter);
            }
        }


    }

    /**
     * parses the nodes and edges associated with the graph selected
     * 
     * @param graph
     *            the graph object used to populate nodes and edges
     * @param root
     *            the tree root
     * @param filter
     *            the filter string.
     */
    public void parseGraph(IGraph graph, TreeItem<Object> root, String filter) {


        TreeItem<Object> graphItem = new TreeItem<>(graph);

        root.getChildren().add(graphItem);
        if (this.showNodes) {
            TreeItem<Object> nodeTI = new TreeItem<>(graph + " Nodes ("
                    + graph.getNodes().size() + ")");
            graphItem.getChildren().add(nodeTI);

            for (INode node : graph.getNodes()) {

                if (node.getId().contains(filter)) {

                    this.parseNode(node, nodeTI, filter);
                }
            }
        }

        if (this.showEdges) {

            TreeItem<Object> edgeTI = new TreeItem<>(graph + " Edges ("
                    + graph.getEdges().size() + ")");
            graphItem.getChildren().add(edgeTI);

            for (IEdge edge : graph.getEdges()) {

                if (edge.toString().contains(filter)) {

                    this.parseEdge(edge, edgeTI, filter);
                }
            }
        }
        if (this.showHyperEdges) {
            TreeItem<Object> hyperTI = new TreeItem<>(graph + "Hyperedges ("
                    + graph.getHyperEdges().size() + ")");
            graphItem.getChildren().add(hyperTI);
            for (IHyperEdge he : graph.getHyperEdges()) {

                if (he.getId().contains(filter)) {

                    this.parseHyperEdge(he, hyperTI, filter);
                }
            }
        }
    }

    /**
     * parses the edges associated with the selected showNodes
     * 
     * @param node
     *            the node being passed in
     * @param root
     *            the root of the tree
     * @param filter
     *            the filter string.
     */
    public void parseNode(INode node, TreeItem<Object> root, String filter) {

        TreeItem<Object> nodeItem = new TreeItem<>(node);
        root.getChildren().add(nodeItem);

        if (this.showEdges) {

            TreeItem<Object> edgeTI = new TreeItem<>(node + "Edges ("
                    + node.getEdges().size() + ")");
            nodeItem.getChildren().add(edgeTI);

            for (IEdge edge : node.getEdges()) {

                this.parseEdge(edge, edgeTI, filter);
            }
        }
        if (this.showHyperEdges) {
            TreeItem<Object> hyperItem = new TreeItem<>(node + "HyperEdges ("
                    + node.getHyperEdges().size() + ")");
            nodeItem.getChildren().add(hyperItem);

            for (IHyperEdge he : node.getHyperEdges()) {

                this.parseHyperEdge(he, hyperItem, filter);
            }
        }

    }

    /**
     * parses all the showEdges values associated with an showEdges
     * 
     * @param edge
     *            edge being passed in
     * @param root
     *            tree root
     * @param filter
     *            the filter string.
     */
    public void parseEdge(IEdge edge, TreeItem<Object> root, String filter) {

        TreeItem<Object> edgeItem = new TreeItem<>(edge);
        root.getChildren().add(edgeItem);

    }

    /**
     * parses hyper showEdges values
     * 
     * @param hyperedge
     *            hyperedge to be parsed
     * @param root
     *            tree root
     * @param filter
     *            the filter string.
     */
    public void parseHyperEdge(IHyperEdge hyperedge, TreeItem<Object> root,
            String filter) {

        TreeItem<Object> hyperEdgeItem = new TreeItem<>(hyperedge);
        root.getChildren().add(hyperEdgeItem);

    }

    /**
     * clears old nodes, and populates tree based on search filter
     * 
     * @param filter
     *            the filter string from the search panel
     */
    public void filterNodes(String filter) {


        tree_graphs.getChildren().clear();
        populate(universe, filter, this.showNodes, this.showEdges,
                this.showHyperEdges);


        tree_nodes.getChildren().clear();
        for (INode node : universe.getNodes()) {
            if (node.getId().contains(filter)) {
                parseNode(node, tree_nodes, filter);
            }
        }

    }

    /**
     * clears old nodes, and populates tree based on search filter
     * 
     * @param filter
     *            the filter string from the search panel
     */
    public void filterEdges(String filter) {


        tree_graphs.getChildren().clear();
        populate(universe, filter, this.showNodes, this.showEdges,
                this.showHyperEdges);


        tree_edges.getChildren().clear();
        for (IEdge edge : universe.getEdges()) {
            if (edge.toString().contains(filter)) {
                parseEdge(edge, tree_edges, filter);
            }
        }

    }

    /**
     * Clears the selection.
     */
    public void clearSelections() {

        this.getSelectionModel().clearSelection();
    }

}
