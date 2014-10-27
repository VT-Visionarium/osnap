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


package graphVisualizer.gui.dialogs;


import java.util.LinkedList;

import org.controlsfx.control.ButtonBar;
import org.controlsfx.control.ButtonBar.ButtonType;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.DefaultDialogAction;
import org.controlsfx.dialog.Dialog;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Window;
import graphVisualizer.graph.Edge;
import graphVisualizer.graph.Graph;
import graphVisualizer.graph.Node;
import graphVisualizer.graph.Universe;



/**
 * The {@link EdgeDialog} class provides a {@link Dialog} for creating or
 * editing {@link Edge Edges}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 */
public class EdgeDialog
        extends Dialog {

    private final Universe universe;
    private final Graph    graph;
    private Edge           edge;
    private TextField      edgeIdTF;
    private Action         confirmAction;
    private GridPane       content;
    private ComboBox<Node> sourceNodeCB;
    private ComboBox<Node> targetNodeCB;


    /**
     * Creates a new instance of the {@link EdgeDialog} class.
     * 
     * @param owner
     *            the owner of this dialog.
     * @param title
     *            the title of the dialog
     * @param graph
     *            the {@link Graph} containing the edge.
     * @param edge
     *            the {@link Edge} to modify (or {@code null} if an edge should
     *            be created).
     */
    public EdgeDialog(Window owner, String title, Graph graph, Edge edge) {

        super(owner, title);

        this.graph = graph;
        this.universe = this.graph.getUniverse();
        this.edge = edge;

        final boolean edgeProvided = edge != null;

        this.content = new GridPane();
        this.content.setHgap(10);
        this.content.setVgap(10);
        this.setContent(content);

        this.edgeIdTF = new TextField();
        this.edgeIdTF.setPromptText("Enter Edge ID (cannot be empty)");
        if (edgeProvided) {

            this.edgeIdTF.setText(edge.getID());
        }

        this.edgeIdTF.setMaxHeight(Double.MAX_VALUE);
        this.edgeIdTF.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(this.edgeIdTF, Priority.ALWAYS);

        Label edgeLabel = new Label("Edge ID");
        edgeLabel.setLabelFor(edgeIdTF);
        this.content.add(edgeLabel, 0, 0);
        this.content.add(edgeIdTF, 1, 0);


        this.sourceNodeCB = new ComboBox<>(
                FXCollections.observableList(new LinkedList<>(universe
                        .getNodes())));
        this.sourceNodeCB.setMaxHeight(Double.MAX_VALUE);
        this.sourceNodeCB.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(this.sourceNodeCB, Priority.ALWAYS);



        this.targetNodeCB = new ComboBox<>(
                FXCollections.observableList(new LinkedList<>(universe
                        .getNodes())));
        this.targetNodeCB.setMaxHeight(Double.MAX_VALUE);
        this.targetNodeCB.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(this.targetNodeCB, Priority.ALWAYS);



        Label sourceLabel = new Label("Source Node");
        sourceLabel.setLabelFor(sourceNodeCB);
        Label targetLabel = new Label("Target Node");
        targetLabel.setLabelFor(targetNodeCB);

        this.content.add(sourceLabel, 0, 1);
        this.content.add(this.sourceNodeCB, 1, 1);

        this.content.add(targetLabel, 0, 2);
        this.content.add(this.targetNodeCB, 1, 2);



        this.confirmAction = new DefaultDialogAction("Ok") {

            {
                ButtonBar.setType(this, ButtonType.OK_DONE);
            }

            @Override
            public void handle(ActionEvent ae) {

                if (!isDisabled()) {

                    if (ae.getSource() instanceof Dialog) {

                        Dialog dlg = (Dialog) ae.getSource();

                        if (edgeProvided) {

                            EdgeDialog.this.edge.setID(EdgeDialog.this.edgeIdTF
                                    .getText());
                        }
                        else {
                            EdgeDialog.this.edge = EdgeDialog.this.graph
                                    .getUniverse().createEdge(

                                            EdgeDialog.this.edgeIdTF.getText(),
                                            EdgeDialog.this.graph.getID(),
                                            EdgeDialog.this.sourceNodeCB
                                                    .getSelectionModel()
                                                    .getSelectedItem().getID(),
                                            EdgeDialog.this.targetNodeCB
                                                    .getSelectionModel()
                                                    .getSelectedItem().getID());
                        }
                        dlg.setResult(EdgeDialog.this.confirmAction);
                    }
                }
            }
        };
        this.confirmAction.disabledProperty().set(!edgeProvided);
        this.getActions().addAll(this.confirmAction, Dialog.Actions.CANCEL);

        // Selects the source and target node (if an edge was provided). Then
        // request focus on the edge id field by default (so the user can
        // type immediately without having to click first)
        Platform.runLater(new Runnable() {

            @Override
            public void run() {

                if (EdgeDialog.this.edge != null) {

                    EdgeDialog.this.sourceNodeCB.getSelectionModel().select(
                            EdgeDialog.this.edge.getSource());
                    EdgeDialog.this.targetNodeCB.getSelectionModel().select(
                            EdgeDialog.this.edge.getTarget());
                }

                EdgeDialog.this.edgeIdTF.requestFocus();
            }
        });


        ChangeListener<String> changeListener = new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                EdgeDialog.this.validate();
            }
        };
        this.edgeIdTF.textProperty().addListener(changeListener);


        ChangeListener<Node> nodeChangeListener = new ChangeListener<Node>() {

            @Override
            public void changed(ObservableValue<? extends Node> observable,
                    Node oldValue, Node newValue) {

                EdgeDialog.this.validate();
            }
        };
        this.sourceNodeCB.getSelectionModel().selectedItemProperty()
                .addListener(nodeChangeListener);
        this.targetNodeCB.getSelectionModel().selectedItemProperty()
                .addListener(nodeChangeListener);
    }

    private void validate() {

        confirmAction.disabledProperty().set(
                EdgeDialog.this.edgeIdTF.getText() == null
                        || EdgeDialog.this.edgeIdTF.getText().trim().isEmpty()
                        || EdgeDialog.this.universe
                                .containsID(EdgeDialog.this.edgeIdTF.getText())
                        || EdgeDialog.this.sourceNodeCB.getSelectionModel()
                                .getSelectedItem() == null
                        || EdgeDialog.this.targetNodeCB.getSelectionModel()
                                .getSelectedItem() == null);

    }

    /**
     * Creates and shows a dialog for creating or modifying an edge.
     * 
     * @param owner
     *            the owner.
     * @param graph
     *            the {@link Graph}.
     * @param edge
     *            the {@link Edge} to modify (or {@code null} to create a new
     *            edge).
     * @return the newly created (or modified) {@link Edge}.
     */
    public static Edge showEdgeDialog(Window owner, Graph graph, Edge edge) {

        EdgeDialog dialog;

        if (graph == null) {

            dialog = new EdgeDialog(owner, "Create New Edge", graph, edge);
        }
        else {

            dialog = new EdgeDialog(owner, "Edit Edge", graph, edge);
        }

        Action result = dialog.show();

        if (result == dialog.confirmAction) {

            return dialog.edge;
        }
        return null;
    }
}
