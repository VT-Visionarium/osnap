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

import graphVisualizer.graph.Graph;
import graphVisualizer.graph.Node;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Window;



/**
 * The {@link NodeDialog} class provides a {@link Dialog} for creating or
 * editing {@link Node Nodes}.
 * 
 * @author Peter J. Radics
 * @version 1.0
 */
public class NodeDialog
        extends Dialog {

    private final Graph graph;
    private Node        node;
    private TextField   nodeIdTF;
    private Action      confirmAction;
    private GridPane    content;



    /**
     * Creates a new instance of the {@link NodeDialog} class.
     * 
     * @param owner
     *            the owner of the dialog.
     * @param title
     *            the title of the dialog.
     * @param graph
     *            the {@link Graph} containing the node.
     * @param node
     *            the {@link Node} to modify (or {@code null} to create a new
     *            node).
     */
    public NodeDialog(Window owner, String title, Graph graph, Node node) {

        super(owner, title);

        this.graph = graph;
        this.node = node;

        final boolean nodeProvided = node != null;

        this.content = new GridPane();
        this.content.setHgap(10);
        this.content.setVgap(10);
        this.setContent(content);

        nodeIdTF = new TextField();
        nodeIdTF.setPromptText("Enter Node ID (cannot be empty)");
        if (nodeProvided) {

            nodeIdTF.setText(node.getID());
        }

        nodeIdTF.setMaxHeight(Double.MAX_VALUE);
        nodeIdTF.setMaxWidth(Double.MAX_VALUE);

        content.add(new Label("Node ID"), 0, 0);
        content.add(nodeIdTF, 1, 0);
        GridPane.setHgrow(nodeIdTF, Priority.ALWAYS);


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

                        if (nodeProvided) {
                            NodeDialog.this.node.setID(NodeDialog.this.nodeIdTF
                                    .getText());
                        }
                        else {
                            NodeDialog.this.node = NodeDialog.this.graph
                                    .getUniverse().createNode(
                                            NodeDialog.this.nodeIdTF.getText(),
                                            NodeDialog.this.graph.getID());
                        }
                        dlg.setResult(NodeDialog.this.confirmAction);
                    }
                }
            }
        };
        this.confirmAction.disabledProperty().set(!nodeProvided);
        this.getActions().addAll(this.confirmAction, Dialog.Actions.CANCEL);
        // request focus on the username field by default (so the user can
        // type immediately without having to click first)
        Platform.runLater(new Runnable() {

            @Override
            public void run() {

                nodeIdTF.requestFocus();
            }
        });
        ChangeListener<String> changeListener = new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                NodeDialog.this.validate();
            }
        };
        this.nodeIdTF.textProperty().addListener(changeListener);
    }

    private void validate() {

        confirmAction.disabledProperty().set(
                nodeIdTF.getText() == null
                        || nodeIdTF.getText().trim().isEmpty()
                        || NodeDialog.this.graph.getUniverse().containsID(
                                NodeDialog.this.nodeIdTF.getText()));

    }

    /**
     * Creates and shows a dialog for creating a new node or modifying the ID of
     * a provided node.
     * 
     * @param owner
     *            the owner.
     * @param graph
     *            the {@link Graph}.
     * @param node
     *            the {@link Node} to modify (or {@code null} to create a new
     *            node).
     * @return the newly created (or modified) {@link Node}.
     */
    public static Node showNodeDialog(Window owner, Graph graph, Node node) {

        NodeDialog dialog;

        if (graph == null) {

            dialog = new NodeDialog(owner, "Create New Node", graph, node);
        }
        else {

            dialog = new NodeDialog(owner, "Edit Node", graph, node);
        }

        Action result = dialog.show();

        if (result == dialog.confirmAction) {

            return dialog.node;
        }
        return null;
    }
}
