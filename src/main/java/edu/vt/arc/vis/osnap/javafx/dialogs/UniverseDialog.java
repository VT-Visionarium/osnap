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


package edu.vt.arc.vis.osnap.javafx.dialogs;



import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Window;

import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;
import org.jutility.javafx.control.labeled.LabeledTextField;
import org.jutility.javafx.control.validation.ValidationGroup;

import edu.vt.arc.vis.osnap.core.domain.graph.Universe;



/**
 * The {@link UniverseDialog} class provides a {@link Dialog} for creating or
 * editing {@link Universe Universes}.
 *
 * @author Peter J. Radics
 * @version 1.1.1
 * @since 0.1.0
 */
public class UniverseDialog
        extends Dialog<Universe> {

    private final ValidationGroup  validationGroup;

    private Universe               universe;

    private final LabeledTextField universeIdTF;


    /**
     * Creates a new instance of the {@link UniverseDialog} class.
     *
     * @param owner
     *            the owner of this dialog.
     * @param title
     *            the title of the dialog.
     * @param universe
     *            the {@link Universe}.
     */
    public UniverseDialog(final Node owner, final String title,
            final Universe universe) {

        this(owner == null ? null : owner.getScene().getWindow(), title,
                universe);
    }

    /**
     * Creates a new instance of the {@link UniverseDialog} class.
     *
     * @param owner
     *            the owner of this dialog.
     * @param title
     *            the title of the dialog.
     * @param universe
     *            the {@link Universe}.
     */
    public UniverseDialog(final Window owner, final String title,
            final Universe universe) {

        super();

        this.initOwner(owner);
        this.setTitle(title);

        this.validationGroup = new ValidationGroup();

        this.universe = universe;

        final boolean projectProvided = universe != null;


        this.universeIdTF = new LabeledTextField("Universe ID");
        this.universeIdTF.setHgap(10);
        this.universeIdTF.setPromptText("Enter Universe ID (cannot be empty)");
        if (projectProvided) {

            this.universeIdTF.setText(universe.getId());
        }

        this.universeIdTF.setMaxHeight(Double.MAX_VALUE);
        this.universeIdTF.setMaxWidth(Double.MAX_VALUE);

        this.getDialogPane().setContent(this.universeIdTF);

        this.getDialogPane().getButtonTypes()
                .addAll(ButtonType.OK, ButtonType.CANCEL);

        this.setupValidation();

        this.setResultConverter(buttonType -> {

            if (buttonType == ButtonType.OK) {

                if (this.universe != null) {

                    this.universe.setId(this.universeIdTF.getText());
                }
                else {

                    this.universe = new Universe(this.universeIdTF.getText());
                }

                return this.universe;
            }
            return null;
        });

        Platform.runLater(() -> {

            this.universeIdTF.requestFocus();
        });
    }

    private void setupValidation() {

        this.universeIdTF.registerValidator(Validator
                .createEmptyValidator("Universe id cannot be empty!"));
        this.universeIdTF
                .setValidationDecorator(new GraphicValidationDecoration());
        this.universeIdTF.setErrorDecorationEnabled(true);
        this.validationGroup.registerSubValidation(this.universeIdTF,
                this.universeIdTF.validationSupport());

        this.getDialogPane().lookupButton(ButtonType.OK).disableProperty()
                .bind(this.validationGroup.invalidProperty());
    }
}
