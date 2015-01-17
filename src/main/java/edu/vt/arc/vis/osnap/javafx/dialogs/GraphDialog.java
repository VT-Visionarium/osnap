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
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Window;

import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;
import org.jutility.javafx.control.labeled.LabeledTextField;
import org.jutility.javafx.control.validation.ValidationGroup;

import edu.vt.arc.vis.osnap.core.domain.graph.Graph;
import edu.vt.arc.vis.osnap.core.domain.graph.Universe;



/**
 * The {@link GraphDialog} class provides a {@link Dialog} for creating or
 * editing {@link Graph Graphs}.
 *
 * @author Peter J. Radics
 * @version 1.1.1
 * @since 0.1.0
 */
public class GraphDialog
        extends Dialog<Graph> {

    private final ValidationGroup  validationGroup;

    private final Universe         universe;

    private Graph                  graph;

    private final LabeledTextField graphIdTF;



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
    public GraphDialog(final Node owner, final String title,
            final Universe universe, final Graph graph) {

        this(owner == null ? null : owner.getScene().getWindow(), title,
                universe, graph);
    }

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
    public GraphDialog(final Window owner, final String title,
            final Universe universe, final Graph graph) {

        super();

        this.initOwner(owner);
        this.setTitle(title);

        this.validationGroup = new ValidationGroup();

        this.universe = universe;
        this.graph = graph;

        final boolean graphProvided = graph != null;


        this.graphIdTF = new LabeledTextField("Graph ID");
        this.graphIdTF.setHgap(10);
        this.graphIdTF.setPromptText("Enter Graph ID (cannot be empty)");
        if (graphProvided) {

            this.graphIdTF.setText(graph.getId());
        }

        this.graphIdTF.setMaxHeight(Double.MAX_VALUE);
        this.graphIdTF.setMaxWidth(Double.MAX_VALUE);
        this.getDialogPane().setContent(this.graphIdTF);


        this.setupValidation();

        this.getDialogPane().getButtonTypes()
                .addAll(ButtonType.OK, ButtonType.CANCEL);

        this.getDialogPane().lookupButton(ButtonType.OK).disableProperty()
                .bind(this.validationGroup.invalidProperty());


        this.setResultConverter(buttonType -> {

            if (buttonType == ButtonType.OK) {

                if (this.graph != null) {

                    this.graph.setId(this.graphIdTF.getText());
                }
                else {

                    this.graph = this.universe.createGraph(this.graphIdTF
                            .getText());
                }
                return this.graph;
            }
            return null;
        });

        Platform.runLater(() -> {

            this.graphIdTF.requestFocus();
        });
    }

    private void setupValidation() {

        this.graphIdTF.registerValidator(Validator.combine(
                Validator.createEmptyValidator("Graph id cannot be empty!"),
                Validator.createPredicateValidator(text -> {

                    if (text != null) {

                        return !this.universe.containsID(text.toString());
                    }
                    return false;
                }, "ID is already in use!")));
        this.graphIdTF
                .setValidationDecorator(new GraphicValidationDecoration());
        this.graphIdTF.setErrorDecorationEnabled(true);
        this.validationGroup.registerSubValidationSupport(this.graphIdTF,
                this.graphIdTF.validationSupport());

    }
}
