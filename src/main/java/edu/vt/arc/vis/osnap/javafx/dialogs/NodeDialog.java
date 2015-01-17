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


import java.util.Objects;

import javafx.application.Platform;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Window;

import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;
import org.jutility.javafx.control.labeled.LabeledTextField;
import org.jutility.javafx.control.validation.ValidationGroup;

import edu.vt.arc.vis.osnap.core.domain.graph.Graph;
import edu.vt.arc.vis.osnap.core.domain.graph.Node;



/**
 * The {@link NodeDialog} class provides a {@link Dialog} for creating or
 * editing {@link Node Nodes}.
 *
 * @author Peter J. Radics
 * @version 1.1.1
 * @since 0.1.0
 */
public class NodeDialog
        extends Dialog<Node> {

    private final ValidationGroup  validationGroup;

    private final Graph            graph;

    private Node                   node;

    private final LabeledTextField nodeIdTF;


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
    public NodeDialog(final javafx.scene.Node owner, final String title,
            final Graph graph, final Node node) {

        this(owner == null ? null : owner.getScene().getWindow(), title, graph,
                node);
    }

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
    public NodeDialog(final Window owner, final String title,
            final Graph graph, final Node node) {

        super();


        this.initOwner(owner);
        this.setTitle(title);

        this.validationGroup = new ValidationGroup();

        Objects.nonNull(graph);
        this.graph = graph;
        this.node = node;

        final boolean nodeProvided = node != null;


        this.nodeIdTF = new LabeledTextField("Node ID");
        this.nodeIdTF.setHgap(10);
        this.nodeIdTF.setPromptText("Enter Node ID (cannot be empty)");
        if (nodeProvided) {

            this.nodeIdTF.setText(node.getId());
        }

        this.nodeIdTF.setMaxHeight(Double.MAX_VALUE);
        this.nodeIdTF.setMaxWidth(Double.MAX_VALUE);

        this.getDialogPane().setContent(this.nodeIdTF);

        this.getDialogPane().getButtonTypes()
                .addAll(ButtonType.OK, ButtonType.CANCEL);

        this.setupValidation();

        this.setResultConverter(buttonType -> {

            if (buttonType == ButtonType.OK) {

                if (this.node != null) {

                    this.node.setId(this.nodeIdTF.getText());
                }
                else {

                    this.node = this.graph.getUniverse().createNode(
                            this.nodeIdTF.getText(), this.graph.getId());
                }
                return this.node;
            }
            return null;
        });



        Platform.runLater(() -> {

            this.nodeIdTF.requestFocus();
        });
    }

    private void setupValidation() {

        this.nodeIdTF.registerValidator(Validator.combine(
                Validator.createEmptyValidator("Node id cannot be empty!"),
                Validator.createPredicateValidator(
                        text -> {

                            if (text != null) {

                                return !this.graph.getUniverse().containsID(
                                        text.toString());
                            }

                            return false;

                        }, "Id is already in use!")));
        this.nodeIdTF.setValidationDecorator(new GraphicValidationDecoration());
        this.nodeIdTF.setErrorDecorationEnabled(true);
        this.validationGroup.registerSubValidationSupport(this.nodeIdTF,
                this.nodeIdTF.validationSupport());

        this.getDialogPane().lookupButton(ButtonType.OK).disableProperty()
                .bind(this.validationGroup.invalidProperty());
    }
}
