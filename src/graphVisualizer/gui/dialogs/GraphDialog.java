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


import org.controlsfx.control.ButtonBar;
import org.controlsfx.control.ButtonBar.ButtonType;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.DefaultDialogAction;
import org.controlsfx.dialog.Dialog;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Window;
import graphVisualizer.graph.Graph;
import graphVisualizer.graph.Universe;



/**
 * The {@link GraphDialog} class provides a {@link Dialog} for creating or
 * editing {@link Graph Graphs}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 */
public class GraphDialog
        extends Dialog {

    private final Universe universe;
    private Graph          graph;
    private TextField      graphIdTF;
    private Action         confirmAction;
    private GridPane       content;



    /**
     * Creates a new instance of the {@link GraphDialog} class.
     * 
     * @param owner
     *            the owner of this dialog.
     * @param title
     *            the title of the dialog.
     * @param universe
     *            the {@link Universe} containing the {@link Graph}.
     * @param graph
     *            the {@link Graph} to edit (or {@code null} to create a new
     *            graph).
     */
    public GraphDialog(Window owner, String title, Universe universe,
            Graph graph) {

        super(owner, title);

        this.universe = universe;
        this.graph = graph;

        final boolean graphProvided = graph != null;

        this.content = new GridPane();
        this.content.setHgap(10);
        this.content.setVgap(10);
        this.setContent(content);

        graphIdTF = new TextField();
        graphIdTF.setPromptText("Enter Graph ID (cannot be empty)");
        if (graphProvided) {

            graphIdTF.setText(graph.getID());
        }

        graphIdTF.setMaxHeight(Double.MAX_VALUE);
        graphIdTF.setMaxWidth(Double.MAX_VALUE);

        content.add(new Label("Graph ID"), 0, 0);
        content.add(graphIdTF, 1, 0);
        GridPane.setHgrow(graphIdTF, Priority.ALWAYS);


        confirmAction = new DefaultDialogAction("Ok") {

            {
                ButtonBar.setType(this, ButtonType.OK_DONE);
            }

            // This method is called when the login button is clicked...
            @Override
            public void handle(ActionEvent ae) {

                if (!isDisabled()) {

                    if (ae.getSource() instanceof Dialog) {

                        Dialog dlg = (Dialog) ae.getSource();

                        if (graphProvided) {
                            GraphDialog.this.graph
                                    .setID(GraphDialog.this.graphIdTF.getText());
                        }
                        else {
                            GraphDialog.this.graph = GraphDialog.this.universe
                                    .createGraph(GraphDialog.this.graphIdTF
                                            .getText());
                        }
                        dlg.setResult(GraphDialog.this.confirmAction);
                    }
                }
            }
        };
        this.confirmAction.disabledProperty().set(!graphProvided);
        this.getActions().addAll(this.confirmAction, Dialog.Actions.CANCEL);
        // request focus on the username field by default (so the user can
        // type immediately without having to click first)
        Platform.runLater(new Runnable() {

            @Override
            public void run() {

                graphIdTF.requestFocus();
            }
        });
        ChangeListener<String> changeListener = new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                GraphDialog.this.validate();
            }
        };
        this.graphIdTF.textProperty().addListener(changeListener);
    }

    private void validate() {

        confirmAction.disabledProperty().set(
                graphIdTF.getText() == null
                        || graphIdTF.getText().trim().isEmpty()
                        || GraphDialog.this.universe
                                .containsID(GraphDialog.this.graphIdTF
                                        .getText()));

    }

    /**
     * Creates and shows a dialog for creating a new graph or modifying the ID
     * of a provided graph.
     * 
     * @param owner
     *            the owner.
     * @param universe
     *            the {@link Universe}.
     * @param graph
     *            the {@link Graph} to modify (or {@code null} to create a new
     *            graph).
     * @return the newly created (or modified) {@link Graph}.
     */
    public static Graph showGraphDialog(Window owner, Universe universe,
            Graph graph) {

        GraphDialog dialog;

        if (graph == null) {

            dialog = new GraphDialog(owner, "Create New Graph", universe, graph);
        }
        else {

            dialog = new GraphDialog(owner, "Edit Graph ID", universe, graph);
        }

        Action result = dialog.show();

        if (result == dialog.confirmAction) {

            return dialog.graph;
        }
        return null;
    }
}
