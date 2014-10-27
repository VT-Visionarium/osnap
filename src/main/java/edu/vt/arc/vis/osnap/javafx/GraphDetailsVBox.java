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


package edu.vt.arc.vis.osnap.javafx;


import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.DefaultDialogAction;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;
import org.jutility.javafx.control.ListViewWithSearchPanel;

import edu.vt.arc.vis.osnap.core.domain.graph.Edge;
import edu.vt.arc.vis.osnap.core.domain.graph.Graph;
import edu.vt.arc.vis.osnap.core.domain.graph.Node;
import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.Schema;
import edu.vt.arc.vis.osnap.javafx.dialogs.EdgeDialog;
import edu.vt.arc.vis.osnap.javafx.dialogs.EdgePropertiesDialog;
import edu.vt.arc.vis.osnap.javafx.dialogs.GraphDialog;
import edu.vt.arc.vis.osnap.javafx.dialogs.GraphPropertiesDialog;
import edu.vt.arc.vis.osnap.javafx.dialogs.NodeDialog;
import edu.vt.arc.vis.osnap.javafx.dialogs.NodePropertiesDialog;
import edu.vt.arc.vis.osnap.javafx.events.MetadataChangedEvent;
import edu.vt.arc.vis.osnap.javafx.stringConverters.GraphObjectStringConverter;
import edu.vt.arc.vis.osnap.javafx.stringConverters.GraphObjectStringConverterConfiguration;
import edu.vt.arc.vis.osnap.javafx.widgets.MetaDataTable;
import edu.vt.arc.vis.osnap.javafx.widgets.SchemaTableView;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


/**
 * Container for all the main display panes for universe elements, including
 * list views of graphs, nodes, edges, meta data, schema entries
 * 
 * @author Shawn P Neuman, Peter J. Radics
 * 
 */
public class GraphDetailsVBox
        extends VBox {


    private GridPane                       grid1 = null;
    private final ObjectProperty<Universe> universe;
    private ListViewWithSearchPanel<Graph> graphListView;
    private ListViewWithSearchPanel<Node>  nodeListView;
    private ListViewWithSearchPanel<Edge>  edgeListView;

    private MetaDataTable                  graphMetaDataTable;
    private MetaDataTable                  nodeMetaDataTable;
    private MetaDataTable                  edgeMetaDataTable;

    private SchemaTableView                graphSchemaTableView;
    private SchemaTableView                nodeSchemaTableView;
    private SchemaTableView                edgeSchemaTableView;

    private Schema                         graphSchema;
    private Schema                         nodeSchema;
    private Schema                         edgeSchema;



    /**
     * Returns the universe property.
     * 
     * @return the universe property.
     */
    public ObjectProperty<Universe> universe() {

        return this.universe;
    }

    /**
     * Returns the universe.
     * 
     * @return the universe.
     */
    public Universe getUniverse() {

        return this.universe.get();
    }


    /**
     * Sets the universe.
     * 
     * @param universe
     *            the universe.
     */
    public void setUniverse(Universe universe) {

        this.universe.set(universe);
    }


    /**
     * constructor
     */
    public GraphDetailsVBox() {

        this.universe = new SimpleObjectProperty<>();

        this.setStyle("-fx-background-color: cornsilk");
        // main grid pane
        grid1 = new GridPane();
        grid1.setPadding(new Insets(10, 10, 10, 10));

        // first inner v box to contain graph information
        // using v box to layout label, and then inner grid pane to arrange
        // list views and tables

        VBox graphBox = new VBox();
        graphBox.setStyle("-fx-border-color: #000000; -fx-border-width: 1px");
        graphBox.setAlignment(Pos.CENTER);
        graphBox.setPadding(new Insets(10, 10, 10, 10));
        Text text = new Text();
        text.setFont(new Font(20));
        text.setStyle("-fx-font-weight: bold");
        text.setTextAlignment(TextAlignment.CENTER);
        text.setText("Graph Information");

        graphBox.getChildren().add(text);

        // inner grid pane to house graph info

        GridPane graphGrid = new GridPane();
        graphGrid.setHgap(25);
        graphGrid.setVgap(10);
        graphGrid.setPadding(new Insets(10, 10, 10, 10));

        // houses node info

        VBox nodeBox = new VBox();
        nodeBox.setStyle("-fx-border-color: #000000; -fx-border-width: 1px");
        nodeBox.setAlignment(Pos.CENTER);
        nodeBox.setPadding(new Insets(10, 10, 10, 10));
        GridPane nodeGrid = new GridPane();
        nodeGrid.setHgap(25);
        nodeGrid.setVgap(10);
        nodeGrid.setPadding(new Insets(10, 10, 10, 10));
        Text text1 = new Text();
        text1.setFont(new Font(20));
        text1.setStyle("-fx-font-weight: bold");
        text1.setTextAlignment(TextAlignment.CENTER);
        text1.setText("Node Information");
        nodeBox.getChildren().add(text1);

        // houses edge info
        VBox edgeBox = new VBox();
        edgeBox.setStyle("-fx-border-color: #000000; -fx-border-width: 1px");
        edgeBox.setAlignment(Pos.CENTER);
        edgeBox.setPadding(new Insets(10, 10, 10, 10));
        GridPane edgeGrid = new GridPane();
        edgeGrid.setHgap(25);
        edgeGrid.setVgap(10);
        edgeGrid.setPadding(new Insets(10, 10, 10, 10));
        Text text2 = new Text();
        text2.setFont(new Font(20));
        text2.setStyle("-fx-font-weight: bold");
        text2.setTextAlignment(TextAlignment.CENTER);
        text2.setText("Edge Information");
        edgeBox.getChildren().add(text2);



        graphListView = new ListViewWithSearchPanel<>("Graphs",
                new GraphObjectStringConverter<Graph>(
                        GraphObjectStringConverterConfiguration.ID));
        graphListView.setVisible(false);


        nodeListView = new ListViewWithSearchPanel<>("Nodes",
                new GraphObjectStringConverter<Node>(
                        GraphObjectStringConverterConfiguration.ID));
        nodeListView.setVisible(false);


        edgeListView = new ListViewWithSearchPanel<>("Edges",
                new GraphObjectStringConverter<Edge>(
                        GraphObjectStringConverterConfiguration.ID));
        edgeListView.setVisible(false);


        graphMetaDataTable = new MetaDataTable("Graph Metadata");
        graphMetaDataTable.setVisible(false);

        nodeMetaDataTable = new MetaDataTable("Node Metadata");
        nodeMetaDataTable.setVisible(false);

        edgeMetaDataTable = new MetaDataTable("Edge Metadata");
        edgeMetaDataTable.setVisible(false);

        graphSchemaTableView = new SchemaTableView("Graph Schema");
        graphSchemaTableView.setVisible(false);

        nodeSchemaTableView = new SchemaTableView("Node Schema");
        nodeSchemaTableView.setVisible(false);

        edgeSchemaTableView = new SchemaTableView("Edge Schema");
        edgeSchemaTableView.setVisible(false);


        graphGrid.add(graphListView, 0, 1);
        graphGrid.add(graphMetaDataTable, 1, 1);
        graphGrid.add(graphSchemaTableView, 0, 2, 2, 1);

        graphBox.getChildren().add(graphGrid);

        nodeGrid.add(nodeListView, 0, 1);
        nodeGrid.add(nodeMetaDataTable, 1, 1);
        nodeGrid.add(nodeSchemaTableView, 0, 2, 2, 1);

        nodeBox.getChildren().add(nodeGrid);

        edgeGrid.add(edgeListView, 0, 1);
        edgeGrid.add(edgeMetaDataTable, 1, 1);
        edgeGrid.add(edgeSchemaTableView, 0, 2, 2, 1);

        edgeBox.getChildren().add(edgeGrid);


        grid1.add(graphBox, 0, 0);
        grid1.add(nodeBox, 1, 0);
        grid1.add(edgeBox, 2, 0);
        this.getChildren().addAll(grid1);


        this.setUpEventHandlers();
        this.setUpContextMenus();
    }



    /**
     * @param universe
     *            the universe being passed in, either from a file, or from
     *            creation
     */
    private void populate(Universe universe) {


        this.graphListView.items().addAll(universe.getGraphs());
        this.graphListView.setVisible(true);

        this.graphSchema = universe.getGraphSchema();
        this.nodeSchema = universe.getNodeSchema();
        this.edgeSchema = universe.getEdgeSchema();

        this.graphSchemaTableView.iterateCollection(this.graphSchema);
        this.graphSchemaTableView.setVisible(true);

        this.nodeSchemaTableView.iterateCollection(nodeSchema);
        this.edgeSchemaTableView.iterateCollection(edgeSchema);
    }

    /**
     * clears all list views
     */
    private void clear() {

        this.graphListView.clear();
        this.nodeListView.clear();
        this.edgeListView.clear();

        this.graphMetaDataTable.clear();
        this.nodeMetaDataTable.clear();
        this.edgeMetaDataTable.clear();

        this.graphSchemaTableView.clear();
        this.nodeSchemaTableView.clear();
        this.edgeSchemaTableView.clear();

        graphListView.setVisible(false);
        nodeListView.setVisible(false);
        edgeListView.setVisible(false);

        graphMetaDataTable.setVisible(false);
        nodeMetaDataTable.setVisible(false);
        edgeMetaDataTable.setVisible(false);

        graphSchemaTableView.setVisible(false);
        nodeSchemaTableView.setVisible(false);
        edgeSchemaTableView.setVisible(false);

    }

    private void setUpEventHandlers() {

        this.universe.addListener(new ChangeListener<Universe>() {

            @Override
            public void changed(ObservableValue<? extends Universe> observable,
                    Universe oldValue, Universe newValue) {

                GraphDetailsVBox.this.clear();
                if (newValue != null) {

                    GraphDetailsVBox.this.populate(newValue);
                }

            }
        });



        this.graphListView.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<Graph>() {

                    @Override
                    public void changed(
                            ObservableValue<? extends Graph> observable,
                            Graph oldValue, Graph newValue) {

                        if (newValue != null) {

                            GraphDetailsVBox.this.nodeListView.clear();
                            GraphDetailsVBox.this.nodeListView.items().addAll(
                                    newValue.getNodes());
                            GraphDetailsVBox.this.nodeListView.setVisible(true);
                            GraphDetailsVBox.this.nodeSchemaTableView
                                    .setVisible(true);
                            GraphDetailsVBox.this.graphMetaDataTable
                                    .iterateCollection(
                                            newValue.getMetadataProperty(),
                                            GraphDetailsVBox.this.graphSchema);
                            GraphDetailsVBox.this.graphMetaDataTable
                                    .setVisible(true);
                        }
                        else {
                            GraphDetailsVBox.this.nodeListView.clear();
                            GraphDetailsVBox.this.edgeListView.clear();
                            GraphDetailsVBox.this.graphMetaDataTable.clear();
                            GraphDetailsVBox.this.nodeMetaDataTable.clear();
                            GraphDetailsVBox.this.edgeMetaDataTable.clear();

                            GraphDetailsVBox.this.graphMetaDataTable
                                    .setVisible(false);
                            GraphDetailsVBox.this.nodeMetaDataTable
                                    .setVisible(false);
                            GraphDetailsVBox.this.edgeMetaDataTable
                                    .setVisible(false);
                            GraphDetailsVBox.this.nodeListView
                                    .setVisible(false);
                            GraphDetailsVBox.this.edgeListView
                                    .setVisible(false);
                            GraphDetailsVBox.this.nodeSchemaTableView
                                    .setVisible(false);
                            GraphDetailsVBox.this.edgeSchemaTableView
                                    .setVisible(false);
                        }

                    }
                });



        nodeListView.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<Node>() {

                    @Override
                    public void changed(
                            ObservableValue<? extends Node> observable,
                            Node oldValue, Node newValue) {

                        if (newValue != null) {

                            GraphDetailsVBox.this.edgeListView.clear();
                            GraphDetailsVBox.this.edgeListView.items().addAll(
                                    newValue.getEdges());

                            GraphDetailsVBox.this.edgeListView.setVisible(true);
                            GraphDetailsVBox.this.nodeMetaDataTable
                                    .iterateCollection(
                                            newValue.getMetadataProperty(),
                                            GraphDetailsVBox.this.nodeSchema);
                            GraphDetailsVBox.this.nodeMetaDataTable
                                    .setVisible(true);
                            GraphDetailsVBox.this.edgeSchemaTableView
                                    .setVisible(true);

                        }

                        else {
                            GraphDetailsVBox.this.edgeListView.clear();
                            GraphDetailsVBox.this.nodeMetaDataTable.clear();
                            GraphDetailsVBox.this.edgeMetaDataTable.clear();
                            GraphDetailsVBox.this.nodeMetaDataTable
                                    .setVisible(false);
                            GraphDetailsVBox.this.edgeMetaDataTable
                                    .setVisible(false);
                            GraphDetailsVBox.this.edgeListView
                                    .setVisible(false);
                            GraphDetailsVBox.this.edgeSchemaTableView
                                    .setVisible(false);
                        }
                    }
                });



        this.edgeListView.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<Edge>() {

                    @Override
                    public void changed(
                            ObservableValue<? extends Edge> observable,
                            Edge oldValue, Edge newValue) {

                        if (newValue != null) {

                            edgeMetaDataTable.iterateCollection(
                                    newValue.getMetadataProperty(), edgeSchema);
                            edgeMetaDataTable.setVisible(true);
                        }

                        else {
                            edgeMetaDataTable.clear();
                            edgeMetaDataTable.setVisible(false);
                        }

                    }
                });



    }

    private void setUpContextMenus() {

        Action addGraph = new DefaultDialogAction("Add") {

            @Override
            public void handle(ActionEvent actionEvent) {


                Graph newGraph = GraphDialog.showGraphDialog(
                        GraphDetailsVBox.this.getScene().getWindow(),
                        GraphDetailsVBox.this.getUniverse(), null);

                if (newGraph != null) {

                    GraphDetailsVBox.this.graphListView.items().add(newGraph);
                    GraphDetailsVBox.this.graphListView.update();
                }

            }
        };

        Action editGraph = new DefaultDialogAction("Edit") {

            @Override
            public void handle(ActionEvent actionEvent) {

                Graph graph = GraphDetailsVBox.this.graphListView
                        .getSelectedItem();

                Graph editedGraph = GraphDialog.showGraphDialog(
                        GraphDetailsVBox.this.getScene().getWindow(),
                        GraphDetailsVBox.this.getUniverse(), graph);

                if (editedGraph != null) {

                    GraphDetailsVBox.this.graphListView.update();
                }

            }
        };

        Action removeGraph = new DefaultDialogAction("Remove") {

            @Override
            public void handle(ActionEvent actionEvent) {

                Graph graph = GraphDetailsVBox.this.graphListView
                        .getSelectedItem();
                Action result = Dialogs
                        .create()
                        .title("Confirm removal!")
                        .message(
                                "Are you sure you want to remove graph "
                                        + graph.getId() + "?").showConfirm();

                if (result == Dialog.Actions.YES) {

                    GraphDetailsVBox.this.getUniverse().removeGraph(graph);
                    GraphDetailsVBox.this.graphListView.items().remove(graph);
                    GraphDetailsVBox.this.graphListView.update();
                }
            }
        };


        Action graphProperties = new DefaultDialogAction("Properties") {

            @Override
            public void handle(ActionEvent actionEvent) {

                Graph graph = GraphDetailsVBox.this.graphListView
                        .getSelectedItem();
                GraphPropertiesDialog gpd = new GraphPropertiesDialog(graph);
                gpd.show();
            }
        };

        this.graphListView.contextMenuActions().addAll(addGraph, editGraph,
                removeGraph, graphProperties);



        graphListView.addEventHandler(MetadataChangedEvent.METADATA_CHANGED,
                new EventHandler<MetadataChangedEvent>() {

                    @Override
                    public void handle(MetadataChangedEvent metadataEvent) {

                        MetadataChangedEvent.Change change = metadataEvent
                                .getChange();

                        switch (change) {
                            case ADD:
                                graphSchemaTableView
                                        .iterateCollection(GraphDetailsVBox.this
                                                .getUniverse().getNodeSchema());
                                break;
                            case REMOVE:
                            default:
                                break;
                        }
                    }
                });



        Action addNode = new DefaultDialogAction("Add") {

            @Override
            public void handle(ActionEvent actionEvent) {


                Graph graph = GraphDetailsVBox.this.graphListView
                        .getSelectedItem();

                Node newNode = NodeDialog.showNodeDialog(GraphDetailsVBox.this
                        .getScene().getWindow(), graph, null);

                if (newNode != null) {
                    GraphDetailsVBox.this.nodeListView.items().add(newNode);
                    GraphDetailsVBox.this.nodeListView.update();
                }
            }
        };

        Action editNode = new DefaultDialogAction("Edit") {

            @Override
            public void handle(ActionEvent actionEvent) {

                Node node = GraphDetailsVBox.this.nodeListView
                        .getSelectedItem();

                Graph graph = GraphDetailsVBox.this.graphListView
                        .getSelectedItem();

                Node editedNode = NodeDialog.showNodeDialog(
                        GraphDetailsVBox.this.getScene().getWindow(), graph,
                        node);

                if (editedNode != null) {

                    GraphDetailsVBox.this.nodeListView.update();
                }
            }
        };

        Action removeNode = new DefaultDialogAction("Remove") {

            @Override
            public void handle(ActionEvent actionEvent) {

                Node node = GraphDetailsVBox.this.nodeListView
                        .getSelectedItem();
                Action result = Dialogs
                        .create()
                        .title("Confirm removal!")
                        .message(
                                "Are you sure you want to remove node "
                                        + node.getId() + "?").showConfirm();

                if (result == Dialog.Actions.YES) {

                    GraphDetailsVBox.this.getUniverse().removeNode(node);
                    GraphDetailsVBox.this.nodeListView.items().remove(node);
                    GraphDetailsVBox.this.nodeListView.update();
                }
            }
        };


        Action nodeProperties = new DefaultDialogAction("Properties") {

            @Override
            public void handle(ActionEvent actionEvent) {

                Node node = GraphDetailsVBox.this.nodeListView
                        .getSelectedItem();
                NodePropertiesDialog npd = new NodePropertiesDialog(node);
                npd.show();
            }
        };

        this.nodeListView.contextMenuActions().addAll(addNode, editNode,
                removeNode, nodeProperties);


        nodeListView.addEventHandler(MetadataChangedEvent.METADATA_CHANGED,
                new EventHandler<MetadataChangedEvent>() {

                    @Override
                    public void handle(MetadataChangedEvent metadataEvent) {

                        MetadataChangedEvent.Change change = metadataEvent
                                .getChange();

                        switch (change) {
                            case ADD:
                                nodeSchemaTableView
                                        .iterateCollection(GraphDetailsVBox.this
                                                .getUniverse().getNodeSchema());
                                break;
                            case REMOVE:
                            default:
                                break;

                        }

                    }

                });



        Action addEdge = new DefaultDialogAction("Add") {

            @Override
            public void handle(ActionEvent actionEvent) {


                Graph graph = GraphDetailsVBox.this.graphListView
                        .getSelectedItem();

                Edge newEdge = EdgeDialog.showEdgeDialog(GraphDetailsVBox.this
                        .getScene().getWindow(), graph, null);

                if (newEdge != null) {

                    GraphDetailsVBox.this.edgeListView.items().add(newEdge);
                    GraphDetailsVBox.this.edgeListView.update();
                }
            }
        };

        Action editEdge = new DefaultDialogAction("Edit") {

            @Override
            public void handle(ActionEvent actionEvent) {

                Edge edge = GraphDetailsVBox.this.edgeListView
                        .getSelectedItem();

                Graph graph = GraphDetailsVBox.this.graphListView
                        .getSelectedItem();

                Edge editedNode = EdgeDialog.showEdgeDialog(
                        GraphDetailsVBox.this.getScene().getWindow(), graph,
                        edge);

                if (editedNode != null) {

                    GraphDetailsVBox.this.nodeListView.update();
                }
            }
        };

        Action removeEdge = new DefaultDialogAction("Remove") {

            @Override
            public void handle(ActionEvent actionEvent) {


                Edge edge = GraphDetailsVBox.this.edgeListView
                        .getSelectedItem();
                Action result = Dialogs
                        .create()
                        .title("Confirm removal!")
                        .message(
                                "Are you sure you want to remove edge "
                                        + edge.getId() + "?").showConfirm();

                if (result == Dialog.Actions.YES) {

                    GraphDetailsVBox.this.getUniverse().removeEdge(edge);
                    GraphDetailsVBox.this.edgeListView.items().remove(edge);
                    GraphDetailsVBox.this.edgeListView.update();
                }
            }
        };


        Action edgeProperties = new DefaultDialogAction("Properties") {

            @Override
            public void handle(ActionEvent actionEvent) {

                Edge edge = GraphDetailsVBox.this.edgeListView
                        .getSelectedItem();
                EdgePropertiesDialog epd = new EdgePropertiesDialog(edge);
                epd.show();
            }
        };

        this.edgeListView.contextMenuActions().addAll(addEdge, editEdge,
                removeEdge, edgeProperties);



        edgeListView.addEventHandler(MetadataChangedEvent.METADATA_CHANGED,
                new EventHandler<MetadataChangedEvent>() {

                    @Override
                    public void handle(MetadataChangedEvent metadataEvent) {

                        MetadataChangedEvent.Change change = metadataEvent
                                .getChange();

                        switch (change) {
                            case ADD:
                                edgeSchemaTableView
                                        .iterateCollection(GraphDetailsVBox.this
                                                .getUniverse().getNodeSchema());
                                break;
                            case REMOVE:
                            default:
                                break;

                        }
                    }
                });
    }
}
