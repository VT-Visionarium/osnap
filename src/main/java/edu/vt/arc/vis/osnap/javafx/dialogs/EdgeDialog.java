package edu.vt.arc.vis.osnap.javafx.dialogs;


//@formatter:off
/*
* _
* The Open Semantic Network Analysis Platform (OSNAP)
* _
* Copyright (C) 2012 - 2014 Visionarium at Virginia Tech
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


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Window;

import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;
import org.jutility.javafx.control.labeled.LabeledComboBox;
import org.jutility.javafx.control.labeled.LabeledTextField;
import org.jutility.javafx.control.validation.ValidationGroup;

import edu.vt.arc.vis.osnap.core.domain.graph.Edge;
import edu.vt.arc.vis.osnap.core.domain.graph.Graph;
import edu.vt.arc.vis.osnap.core.domain.graph.Node;
import edu.vt.arc.vis.osnap.core.domain.graph.Universe;



/**
 * The {@link EdgeDialog} class provides a {@link Dialog} for creating or
 * editing {@link Edge Edges}.
 * 
 * @author Peter J. Radics
 * @version 1.1.1
 * @since 0.1.0
 */
public class EdgeDialog
        extends Dialog<Edge> {

    private final ValidationGroup       validationGroup;

    private final Universe              universe;
    private final Graph                 graph;
    private Edge                        edge;


    private final GridPane              content;


    private final LabeledTextField      edgeIdTF;

    private final LabeledComboBox<Node> sourceNodeCB;
    private final LabeledComboBox<Node> targetNodeCB;


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
    public EdgeDialog(javafx.scene.Node owner, String title, Graph graph,
            Edge edge) {

        this(owner == null ? null : owner.getScene().getWindow(), title, graph,
                edge);
    }

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

        super();

        this.initOwner(owner);
        this.setTitle(title);

        this.validationGroup = new ValidationGroup();

        this.graph = graph;
        this.universe = this.graph.getUniverse();
        this.edge = edge;

        final boolean edgeProvided = edge != null;

        this.content = new GridPane();
        this.content.setVgap(10);
        this.getDialogPane().setContent(content);

        this.edgeIdTF = new LabeledTextField("Edge ID");
        this.edgeIdTF.setPromptText("Enter Edge ID (cannot be empty)");


        if (edgeProvided) {

            this.edgeIdTF.setText(edge.getId());
        }
        this.edgeIdTF.setHgap(10);
        this.edgeIdTF.setMaxHeight(Double.MAX_VALUE);
        this.edgeIdTF.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(this.edgeIdTF, Priority.SOMETIMES);

        this.content.add(this.edgeIdTF, 0, 0);


        this.sourceNodeCB = new LabeledComboBox<>("Source Node");
        this.sourceNodeCB.setItems(FXCollections
                .observableArrayList(this.universe.getNodes()));

        this.sourceNodeCB.setHgap(10);
        this.sourceNodeCB.setMaxHeight(Double.MAX_VALUE);
        this.sourceNodeCB.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(this.sourceNodeCB, Priority.SOMETIMES);

        this.targetNodeCB = new LabeledComboBox<>("Target Node");

        this.targetNodeCB.setItems(FXCollections
                .observableArrayList(this.universe.getNodes()));
        this.targetNodeCB.setHgap(10);
        this.targetNodeCB.setMaxHeight(Double.MAX_VALUE);
        this.targetNodeCB.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(this.targetNodeCB, Priority.SOMETIMES);

        this.content.add(this.sourceNodeCB, 0, 1);
        this.content.add(this.targetNodeCB, 0, 2);

        this.getDialogPane().getButtonTypes()
                .addAll(ButtonType.OK, ButtonType.CANCEL);

        this.setupValidation();

        this.setResultConverter(param -> {

            if (param == ButtonType.OK) {

                if (edgeProvided) {

                    this.edge.setId(EdgeDialog.this.edgeIdTF.getText());
                }
                else {
                    this.edge = this.universe.createEdge(

                    this.edgeIdTF.getText(), this.graph.getId(),
                            this.sourceNodeCB.getValue().getId(),
                            this.targetNodeCB.getValue().getId());
                }
                return this.edge;
            }
            return null;
        });

        Platform.runLater(() -> {

            if (edgeProvided) {

                this.sourceNodeCB.getSelectionModel().select(
                        this.edge.getSource());
                this.targetNodeCB.getSelectionModel().select(
                        this.edge.getTarget());
            }

            this.edgeIdTF.requestFocus();
        });
    }

    private void setupValidation() {

        this.edgeIdTF.registerValidator(Validator.combine(
                Validator.createEmptyValidator("Id cannot be empty!"),
                Validator.createPredicateValidator(text -> {

                    if (text != null) {

                        return !this.universe.containsID(text.toString());
                    }

                    return false;
                }, "Id is already in use!")));
        this.edgeIdTF.setValidationDecorator(new GraphicValidationDecoration());
        this.edgeIdTF.setErrorDecorationEnabled(true);
        this.validationGroup.registerSubValidationSupport(this.edgeIdTF,
                this.edgeIdTF.validationSupport());


        this.sourceNodeCB.registerValidator(Validator.createPredicateValidator(
                node -> {
                    return node != null;
                }, "Source node cannot be empty!"));
        this.sourceNodeCB
                .setValidationDecorator(new GraphicValidationDecoration());
        this.sourceNodeCB.setErrorDecorationEnabled(true);
        this.validationGroup.registerSubValidationSupport(this.sourceNodeCB,
                this.sourceNodeCB.validationSupport());


        this.targetNodeCB.registerValidator(Validator.createPredicateValidator(
                node -> {
                    return node != null;
                }, "Target node cannot be empty!"));
        this.targetNodeCB
                .setValidationDecorator(new GraphicValidationDecoration());
        this.targetNodeCB.setErrorDecorationEnabled(true);
        this.validationGroup.registerSubValidationSupport(this.targetNodeCB,
                this.targetNodeCB.validationSupport());

        this.getDialogPane().lookupButton(ButtonType.OK).disableProperty()
                .bind(this.validationGroup.invalidProperty());
    }
}
