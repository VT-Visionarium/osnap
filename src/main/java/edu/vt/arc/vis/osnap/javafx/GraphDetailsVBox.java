package edu.vt.arc.vis.osnap.javafx;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import org.controlsfx.control.action.Action;
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


        this.graphListView.getItems().addAll(universe.getGraphs());
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

        this.universe.addListener((observable, oldValue, newValue) -> {

            this.clear();
            if (newValue != null) {

                this.populate(newValue);
            }
        });



        this.graphListView
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) -> {

                            if (newValue != null) {

                                this.nodeListView.clear();
                                this.nodeListView.getItems().addAll(
                                        newValue.getNodes());
                                this.nodeListView.setVisible(true);
                                this.nodeSchemaTableView.setVisible(true);
                                this.graphMetaDataTable.iterateCollection(
                                        newValue.getMetadataProperty(),
                                        this.graphSchema);
                                this.graphMetaDataTable.setVisible(true);
                            }
                            else {
                                this.nodeListView.clear();
                                this.edgeListView.clear();
                                this.graphMetaDataTable.clear();
                                this.nodeMetaDataTable.clear();
                                this.edgeMetaDataTable.clear();

                                this.graphMetaDataTable.setVisible(false);
                                this.nodeMetaDataTable.setVisible(false);
                                this.edgeMetaDataTable.setVisible(false);
                                this.nodeListView.setVisible(false);
                                this.edgeListView.setVisible(false);
                                this.nodeSchemaTableView.setVisible(false);
                                this.edgeSchemaTableView.setVisible(false);
                            }

                        });



        this.nodeListView
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) -> {

                            if (newValue != null) {

                                this.edgeListView.clear();
                                this.edgeListView.getItems().addAll(
                                        newValue.getEdges());

                                this.edgeListView.setVisible(true);
                                this.nodeMetaDataTable.iterateCollection(
                                        newValue.getMetadataProperty(),
                                        this.nodeSchema);
                                this.nodeMetaDataTable.setVisible(true);
                                this.edgeSchemaTableView.setVisible(true);

                            }

                            else {
                                this.edgeListView.clear();
                                this.nodeMetaDataTable.clear();
                                this.edgeMetaDataTable.clear();
                                this.nodeMetaDataTable.setVisible(false);
                                this.edgeMetaDataTable.setVisible(false);
                                this.edgeListView.setVisible(false);
                                this.edgeSchemaTableView.setVisible(false);
                            }
                        });



        this.edgeListView
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) -> {

                            if (newValue != null) {

                                this.edgeMetaDataTable.iterateCollection(
                                        newValue.getMetadataProperty(),
                                        this.edgeSchema);
                                this.edgeMetaDataTable.setVisible(true);
                            }

                            else {

                                this.edgeMetaDataTable.clear();
                                this.edgeMetaDataTable.setVisible(false);
                            }

                        });



    }

    private void setUpContextMenus() {

        Action addGraph = new Action("Add", actionEvent -> {

            new GraphDialog(this, "Create Graph", this.getUniverse(), null)
                    .showAndWait().ifPresent(newGraph -> {
                        this.graphListView.getItems().add(newGraph);
                    });


        });

        Action editGraph = new Action("Edit", actionEvent -> {

            Graph graph = this.graphListView.getSelectedItem();

            new GraphDialog(this, "Edit Graph", this.getUniverse(), graph)
                    .showAndWait().ifPresent(
                            editedGraph -> {

                                this.graphListView.getItems().set(
                                        this.graphListView.getItems().indexOf(
                                                graph), editedGraph);
                            });

        });

        Action removeGraph = new Action("Remove", actionEvent -> {

            Graph graph = this.graphListView.getSelectedItem();
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirm removal!");
            alert.setContentText("Are you sure you want to remove graph "
                    + graph.getId() + "?");

            alert.showAndWait().filter(buttonType -> {
                return buttonType == ButtonType.OK;
            }).ifPresent(param -> {

                this.getUniverse().removeGraph(graph);
                this.graphListView.getItems().remove(graph);
            });
        });


        Action graphProperties = new Action("Properties", actionEvent -> {

            Graph graph = this.graphListView.getSelectedItem();
            new GraphPropertiesDialog(this, graph).show();
        });

        this.graphListView.contextMenuActions().addAll(addGraph, editGraph,
                removeGraph, graphProperties);



        this.graphListView.addEventHandler(
                MetadataChangedEvent.METADATA_CHANGED,
                metadataEvent -> {

                    MetadataChangedEvent.Change change = metadataEvent
                            .getChange();

                    switch (change) {
                        case ADD:
                            graphSchemaTableView.iterateCollection(this
                                    .getUniverse().getNodeSchema());
                            break;
                        case REMOVE:
                        default:
                            break;
                    }
                });



        Action addNode = new Action("Add", actionEvent -> {


            Graph graph = this.graphListView.getSelectedItem();
            new NodeDialog(this, "Create Node", graph, null).showAndWait()
                    .ifPresent(newNode -> {

                        this.nodeListView.getItems().add(newNode);
                    });

        });

        Action editNode = new Action("Edit", actionEvent -> {

            Node node = this.nodeListView.getSelectedItem();

            Graph graph = this.graphListView.getSelectedItem();

            new NodeDialog(this, "Edit Node", graph, node).showAndWait()
                    .ifPresent(
                            editedNode -> {

                                this.nodeListView.getItems().set(
                                        this.nodeListView.getItems().indexOf(
                                                node), editedNode);
                            });
        });

        Action removeNode = new Action("Remove", actionEvent -> {

            Node node = this.nodeListView.getSelectedItem();
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirm removal!");
            alert.setContentText("Are you sure you want to remove node "
                    + node.getId() + "?");

            alert.showAndWait().filter(buttonType -> {
                return buttonType == ButtonType.OK;
            }).ifPresent(param -> {

                this.getUniverse().removeNode(node);
                this.nodeListView.getItems().remove(node);
            });
        });


        Action nodeProperties = new Action("Properties", actionEvent -> {

            Node node = this.nodeListView.getSelectedItem();
            new NodePropertiesDialog(this, node).show();
        });

        this.nodeListView.contextMenuActions().addAll(addNode, editNode,
                removeNode, nodeProperties);


        this.nodeListView.addEventHandler(
                MetadataChangedEvent.METADATA_CHANGED,
                metadataEvent -> {

                    MetadataChangedEvent.Change change = metadataEvent
                            .getChange();

                    switch (change) {
                        case ADD:
                            nodeSchemaTableView.iterateCollection(this
                                    .getUniverse().getNodeSchema());
                            break;
                        case REMOVE:
                        default:
                            break;

                    }


                });



        Action addEdge = new Action("Add", actionEvent -> {


            Graph graph = this.graphListView.getSelectedItem();

            new EdgeDialog(this, "Create Edge", graph, null).showAndWait()
                    .ifPresent(newEdge -> {

                        this.edgeListView.getItems().add(newEdge);
                    });

        });

        Action editEdge = new Action("Edit", actionEvent -> {

            Edge edge = this.edgeListView.getSelectedItem();

            Graph graph = this.graphListView.getSelectedItem();

            new EdgeDialog(this, "Edit Edge", graph, edge).showAndWait()
                    .ifPresent(
                            editedEdge -> {
                                this.edgeListView.getItems().set(
                                        this.edgeListView.getItems().indexOf(
                                                edge), editedEdge);
                            });

        });

        Action removeEdge = new Action("Remove", actionEvent -> {


            Edge edge = this.edgeListView.getSelectedItem();
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirm removal!");
            alert.setContentText("Are you sure you want to remove edge "
                    + edge.getId() + "?");

            alert.showAndWait().filter(buttonType -> {
                return buttonType == ButtonType.OK;
            }).ifPresent(param -> {

                this.getUniverse().removeEdge(edge);
                this.edgeListView.getItems().remove(edge);
            });
        });


        Action edgeProperties = new Action("Properties", actionEvent -> {

            Edge edge = this.edgeListView.getSelectedItem();
            EdgePropertiesDialog epd = new EdgePropertiesDialog(this, edge);
            epd.show();
        });

        this.edgeListView.contextMenuActions().addAll(addEdge, editEdge,
                removeEdge, edgeProperties);



        this.edgeListView.addEventHandler(
                MetadataChangedEvent.METADATA_CHANGED,
                metadataEvent -> {

                    MetadataChangedEvent.Change change = metadataEvent
                            .getChange();

                    switch (change) {
                        case ADD:
                            edgeSchemaTableView.iterateCollection(this
                                    .getUniverse().getNodeSchema());
                            break;
                        case REMOVE:
                        default:
                            break;
                    }
                });
    }
}
