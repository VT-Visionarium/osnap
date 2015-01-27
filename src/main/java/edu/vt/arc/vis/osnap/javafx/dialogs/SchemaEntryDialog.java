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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;

import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;
import org.jutility.javafx.control.labeled.LabeledComboBox;
import org.jutility.javafx.control.labeled.LabeledTextField;
import org.jutility.javafx.control.validation.ValidationGroup;

import edu.vt.arc.vis.osnap.core.domain.graph.metadata.MetadataType;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.SchemaEntry;


/**
 * The {@code SchemaEntryDialog} class provides a {@link Dialog} for creating
 * and editing {@link SchemaEntry SchemaEntries}.
 *
 * @author Peter J. Radics
 * @version 1.1.1
 * @since 0.1.0
 */
public class SchemaEntryDialog
        extends Dialog<SchemaEntry> {

    private final ValidationGroup               validationGroup;

    private SchemaEntry                         schemaEntry;

    private final GridPane                      content;

    private final LabeledTextField              keyTF;
    private final LabeledComboBox<MetadataType> typeCB;
    private final CheckBox                      requiredCkB;
    private final CheckBox                      uniqueCkB;


    /**
     * Creates a new instance of the {@code SchemaEntryDialog} class.
     *
     * @param owner
     *            the owner of this {@code SchemaEntryDialog}.
     * @param title
     *            the title of this {@code SchemaEntryDialog}
     * @param schemaEntry
     *            the {@link SchemaEntry} to edit or {@code null}.
     */
    public SchemaEntryDialog(final Node owner, final String title,
            final SchemaEntry schemaEntry) {

        this(owner == null ? null : owner.getScene().getWindow(), title,
                schemaEntry);
    }

    /**
     * Creates a new instance of the {@code SchemaEntryDialog} class.
     *
     * @param owner
     *            the owner of this {@code SchemaEntryDialog}.
     * @param title
     *            the title of this {@code SchemaEntryDialog}
     * @param schemaEntry
     *            the {@link SchemaEntry} to edit or {@code null}.
     */
    public SchemaEntryDialog(final Window owner, final String title,
            final SchemaEntry schemaEntry) {

        super();

        this.initOwner(owner.getScene().getWindow());
        this.setTitle(title);

        this.validationGroup = new ValidationGroup();

        this.schemaEntry = schemaEntry;

        this.content = new GridPane();
        this.content.setVgap(10);
        this.content.setHgap(10);

        this.getDialogPane().setContent(this.content);

        this.keyTF = new LabeledTextField("Key");
        this.keyTF.setHgap(10);

        this.typeCB = new LabeledComboBox<>("Type");
        this.typeCB.getItems().addAll(MetadataType.values());
        this.typeCB.setHgap(10);

        this.requiredCkB = new CheckBox("Required");
        this.requiredCkB.setSelected(false);
        this.uniqueCkB = new CheckBox("Unique");
        this.uniqueCkB.setSelected(false);

        if (this.schemaEntry != null) {

            this.keyTF.setText(this.schemaEntry.getKey());
            this.keyTF.setDisable(true);
            this.typeCB.getSelectionModel().select(this.schemaEntry.getType());
            this.typeCB.setDisable(true);
            this.requiredCkB.setSelected(this.schemaEntry.isRequired());
            this.uniqueCkB.setSelected(this.schemaEntry.isUnique());
        }


        this.content.add(this.keyTF, 0, 0);
        this.content.add(this.typeCB, 0, 1);
        this.content.add(this.requiredCkB, 0, 2);
        this.content.add(this.uniqueCkB, 0, 3);

        this.setupValidation();

        this.getDialogPane().getButtonTypes()
                .addAll(ButtonType.OK, ButtonType.CANCEL);

        this.getDialogPane().lookupButton(ButtonType.OK).disableProperty()
                .bind(this.validationGroup.invalidProperty());


        this.setResultConverter(buttonType -> {

            if (buttonType == ButtonType.OK) {

                if (this.schemaEntry != null) {

                    this.schemaEntry.setRequired(this.requiredCkB.isSelected());
                    this.schemaEntry.setUnique(this.uniqueCkB.isSelected());
                }
                else {

                    this.schemaEntry = new SchemaEntry(this.keyTF.getText(),
                            this.typeCB.getValue(), null, this.requiredCkB
                                    .isSelected(), this.uniqueCkB.isSelected());
                }


                return this.schemaEntry;
            }

            return null;
        });

        Platform.runLater(() -> {

            if (!this.keyTF.isDisabled()) {

                this.keyTF.requestFocus();
            }
            else {

                this.requiredCkB.requestFocus();
            }
        });
    }


    private void setupValidation() {

        this.keyTF.registerValidator(Validator
                .createEmptyValidator("Key cannot be empty!"));
        this.keyTF.setValidationDecorator(new GraphicValidationDecoration());
        this.keyTF.setErrorDecorationEnabled(true);
        this.validationGroup.registerSubValidation(this.keyTF,
                this.keyTF.validationSupport());

        this.typeCB.registerValidator(Validator.createPredicateValidator(
                value -> {

                    return value != null;

                }, "Type cannot be empty!"));
        this.typeCB.setValidationDecorator(new GraphicValidationDecoration());
        this.typeCB.setErrorDecorationEnabled(true);
        this.validationGroup.registerSubValidation(this.typeCB,
                this.typeCB.validationSupport());
    }

}
