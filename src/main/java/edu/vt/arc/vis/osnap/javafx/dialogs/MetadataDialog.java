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
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;

import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;
import org.jutility.javafx.control.labeled.LabeledComboBox;
import org.jutility.javafx.control.labeled.LabeledTextField;
import org.jutility.javafx.control.validation.ValidationGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.vt.arc.vis.osnap.core.domain.graph.metadata.Metadata;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.MetadataType;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.Schema;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.SchemaEntry;
import edu.vt.arc.vis.osnap.javafx.stringconverters.GraphObjectBasedValueTypeContainerStringConverter;


/**
 * The {@code MetadataDialog} provides a {@link Dialog} for creating or editing
 * {@link Metadata}.
 * 
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.1.1
 * @since 0.1.0
 */
public class MetadataDialog
        extends Dialog<Metadata> {

    private static Logger                      LOG = LoggerFactory
                                                           .getLogger(MetadataDialog.class);

    private final ValidationGroup              validationGroup;

    private final Schema                       schema;

    private Metadata                           metadata;

    private final LabeledComboBox<SchemaEntry> keyCB;
    private final LabeledTextField             valueTF;
    private final LabeledTextField             typeTF;

    private final GridPane                     content;


    /**
     * Creates a new instance of the {@code AddMetadataDialog} class with the
     * provided key, {@link MetadataType Type}, and {@link Schema}.
     * 
     * @param owner
     *            the node calling the dialog.
     * @param title
     *            the title of the dialog.
     * @param metadata
     *            the {@link Metadata}.
     * @param schema
     *            the {@link Schema}.
     */
    public MetadataDialog(final Node owner, final String title,
            final Metadata metadata, final Schema schema) {

        this(owner == null ? null : owner.getScene().getWindow(), title,
                metadata, schema);
    }

    /**
     * Creates a new instance of the {@code AddMetadataDialog} class with the
     * provided key, {@link MetadataType Type}, and {@link Schema}.
     * 
     * @param owner
     *            the node calling the dialog.
     * @param title
     *            the title of the dialog.
     * @param metadata
     *            the {@link Metadata}.
     * @param schema
     *            the {@link Schema}.
     */
    public MetadataDialog(final Window owner, final String title,
            final Metadata metadata, final Schema schema) {

        super();

        if (owner != null) {

            this.initOwner(owner.getScene().getWindow());
        }
        this.setTitle(title);

        Objects.nonNull(schema);

        this.schema = schema;
        this.metadata = metadata;


        this.validationGroup = new ValidationGroup();

        this.content = new GridPane();
        this.content.setVgap(10);

        this.getDialogPane().setContent(this.content);

        this.keyCB = new LabeledComboBox<>("Key");
        this.keyCB.setHgap(10);

        this.keyCB.getItems().addAll(this.schema.getEntries());
        this.keyCB
                .setConverter(new GraphObjectBasedValueTypeContainerStringConverter<SchemaEntry>());


        this.typeTF = new LabeledTextField("Type");
        this.typeTF.setHgap(10);
        this.typeTF.setDisable(true);


        this.valueTF = new LabeledTextField("Value");
        this.valueTF.setHgap(10);


        this.content.add(this.keyCB, 0, 0);
        this.content.add(this.typeTF, 0, 1);
        this.content.add(this.valueTF, 0, 2);

        this.getDialogPane().getButtonTypes()
                .addAll(ButtonType.OK, ButtonType.CANCEL);



        this.setResultConverter(param -> {

            if (param == ButtonType.OK) {

                if (this.metadata == null) {

                    this.metadata = Metadata.createMetadata(this.keyCB
                            .getValue().getKey(), this.keyCB.getValue()
                            .getType(), this.valueTF.getText());
                }
                else {

                    this.metadata.setValue(this.valueTF.getText());
                }
                return this.metadata;
            }
            return null;
        });

        this.select();

        this.setupValidation();

        Platform.runLater(() -> {

            if (this.keyCB.isDisabled()) {

                this.valueTF.requestFocus();
            }
            else {

                this.keyCB.requestFocus();
            }
        });
    }

    private void setupValidation() {

        this.keyCB.registerValidator(Validator.createPredicateValidator(
                value -> {

                    return value != null;
                }, "Key cannot be empty!"));
        this.keyCB.setValidationDecorator(new GraphicValidationDecoration());
        this.keyCB.setErrorDecorationEnabled(true);
        this.validationGroup.registerSubValidation(this.keyCB,
                this.keyCB.validationSupport());

        this.valueTF
                .registerValidator(Validator.combine(Validator
                        .createEmptyValidator("Value cannot be empty!"),
                        Validator.createPredicateValidator(
                                value -> {
                                    if (value != null
                                            && this.keyCB.getValue() != null) {

                                        return MetadataType.compareValueType(
                                                value.toString(), this.keyCB
                                                        .getValue().getType());
                                    }

                                    return false;
                                }, "Value does not have correct format!")));

        this.valueTF.setValidationDecorator(new GraphicValidationDecoration());
        this.valueTF.setErrorDecorationEnabled(true);
        this.validationGroup.registerSubValidation(this.valueTF,
                this.valueTF.validationSupport());


        this.getDialogPane().lookupButton(ButtonType.OK).disableProperty()
                .bind(this.validationGroup.invalidProperty());
    }

    private void select() {

        this.keyCB
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) -> {

                            this.typeTF.clear();

                            if (newValue != null) {

                                this.typeTF.setText(newValue.getType()
                                        .toString());
                            }

                            LOG.debug("KeyCB invalid: "
                                    + this.keyCB.validationSupport()
                                            .isInvalid());
                            LOG.debug("valueTF invalid: "
                                    + this.valueTF.validationSupport()
                                            .isInvalid());
                            LOG.debug("validationGroup invalid: "
                                    + this.validationGroup.isInvalid());

                        });

        if (this.metadata != null) {

            SchemaEntry entry = this.schema.getEntry(metadata.getKey());
            this.keyCB.getSelectionModel().select(entry);

            if (this.metadata.getValue() != null) {

                this.valueTF.setText(this.metadata.getValue().toString());
            }
            this.keyCB.setDisable(true);
        }
        else if (!this.schema.getEntries().isEmpty()) {

            this.keyCB.getSelectionModel().select(0);
        }
    }
}
