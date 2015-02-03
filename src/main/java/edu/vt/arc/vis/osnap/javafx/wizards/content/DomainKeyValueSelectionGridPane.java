package edu.vt.arc.vis.osnap.javafx.wizards.content;


//@formatter:off
/*
 * _
 * The Open Semantic Network Analysis Platform (OSNAP)
 * _
 * Copyright (C) 2012 - 2015 Visionarium at Virginia Tech
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

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.GridPane;

import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;
import org.jutility.javafx.control.ListViewWithSearchPanel;
import org.jutility.javafx.control.labeled.LabeledComboBox;
import org.jutility.javafx.control.validation.ValidationGroup;

import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObjectBasedValueTypeContainer;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.IMetadataContainer;
import edu.vt.arc.vis.osnap.javafx.stringConverters.GraphObjectBasedValueTypeContainerStringConverter;


/**
 * The {@code DomainKeyValueSelectionGridPane} class provides a view for the
 * selection of the domain key and values for a mapping.
 *
 * @author Shawn P. Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class DomainKeyValueSelectionGridPane
        extends GridPane {

    private final ValidationGroup                                              validationGroup;

    private final LabeledComboBox<String>                                      typeSelectionCB;
    private final ListViewWithSearchPanel<IGraphObjectBasedValueTypeContainer> domainKeysLV;
    private final ListViewWithSearchPanel<Object>                              domainValuesLV;

    private final ObservableList<IGraphObject>                                 restriction;


    /**
     * Returns the {@link ValidationGroup} of this view.
     * 
     * @return the {@link ValidationGroup} of this view.
     */
    public ValidationGroup validationGroup() {

        return this.validationGroup;
    }


    /**
     * Returns the restriction.
     * 
     * @return the restriction.
     */
    public ObservableList<IGraphObject> getRestriction() {

        return this.restriction;
    }


    /**
     * Sets the restriction.
     * 
     * @param restriction
     *            the restriction.
     */
    public void setRestriction(final Collection<IGraphObject> restriction) {

        this.restriction.clear();
        this.restriction.addAll(restriction);
    }

    /**
     * Returns the selected domain key.
     * 
     * @return the selected domain key.
     */
    public IGraphObjectBasedValueTypeContainer getDomainKey() {

        return this.domainKeysLV.getSelectedItem();
    }



    /**
     * Returns the selected domain values.
     * 
     * @return the selected domain values.
     */
    public List<Object> getDomainValues() {

        return this.domainValuesLV.getSelectedItems();
    }

    /**
     * Creates a new instance of the {@link DomainKeyValueSelectionGridPane}
     * class.
     */
    public DomainKeyValueSelectionGridPane() {

        this.setStyle("-fx-padding:10; -fx-background-color: honeydew; -fx-border-color: derive(honeydew, -30%); -fx-border-width: 3;");

        this.setHgap(25);
        this.setVgap(10);
        this.setPadding(new Insets(10, 25, 25, 25));

        this.validationGroup = new ValidationGroup();

        this.restriction = FXCollections.observableArrayList();

        this.typeSelectionCB = new LabeledComboBox<>("Property Type");
        this.typeSelectionCB.getItems().addAll("Metadata",
                "Graph Object Property");

        this.domainKeysLV = new ListViewWithSearchPanel<>("Available Keys",
                new GraphObjectBasedValueTypeContainerStringConverter<>());
        this.domainKeysLV.setVisible(false);


        this.domainValuesLV = new ListViewWithSearchPanel<>("Available Values");
        this.domainValuesLV.setVisible(false);
        this.domainValuesLV.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE);


        this.add(this.typeSelectionCB, 0, 0);
        this.add(this.domainKeysLV, 1, 0);
        this.add(this.domainValuesLV, 2, 0);


        this.setupEventHandlers();
        this.setupValidation();
    }


    private Set<?> createDomainValueList(
            final IGraphObjectBasedValueTypeContainer domainKey) {

        final Set<Object> values = new TreeSet<>();

        for (final IGraphObject graphObject : this.restriction) {

            final Object value = domainKey.getValueForGraphObject(graphObject);
            if (value instanceof Collection<?>) {

                values.addAll((Collection<?>) value);
            }
            else {

                values.add(value);
            }
        }
        return values;
    }


    private void populateSchema(final IMetadataContainer container) {

        this.domainKeysLV.clear();
        this.domainKeysLV.getItems().addAll(
                container.getMetadataProperty().getSchema().getEntries());
    }

    private void populateProperties(final IGraphObject graphObject) {

        this.domainKeysLV.clear();
        this.domainKeysLV.getItems().addAll(graphObject.hasGraphProperties());
    }



    private void setupEventHandlers() {


        this.domainKeysLV.visibleProperty().bind(
                this.typeSelectionCB.getSelectionModel().selectedItemProperty()
                        .isNotNull());
        this.domainValuesLV.visibleProperty().bind(
                this.domainKeysLV.selectedItemProperty().isNotNull());


        this.restriction.addListener((Observable observable) -> {

            this.typeSelectionCB.getSelectionModel().clearSelection();
            this.typeSelectionCB.setVisible(!this.restriction.isEmpty());
        });

        this.typeSelectionCB
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) -> {

                            this.domainKeysLV.clear();
                            this.domainKeysLV.clearSelection();

                            if (newValue != null && !this.restriction.isEmpty()) {

                                IGraphObject graphObject = this.restriction
                                        .iterator().next();

                                switch (newValue) {

                                    case "Metadata":

                                        if (graphObject instanceof IMetadataContainer) {

                                            this.populateSchema((IMetadataContainer) graphObject);
                                        }
                                        break;
                                    case "Graph Object Property":

                                        this.populateProperties(graphObject);
                                        break;
                                    default:
                                        break;
                                }
                            }
                        });


        this.domainKeysLV.selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {

                    this.domainValuesLV.clear();
                    this.domainValuesLV.clearSelection();

                    if (newValue != null) {

                        this.domainValuesLV.getItems().addAll(
                                this.createDomainValueList(newValue));
                    }
                });
    }

    private void setupValidation() {

        this.typeSelectionCB.registerValidator(Validator
                .createPredicateValidator(value -> {

                    return value != null;
                }, "You need to select a value!"));
        this.typeSelectionCB
                .setValidationDecorator(new GraphicValidationDecoration());
        this.typeSelectionCB.setErrorDecorationEnabled(true);
        this.validationGroup.registerSubValidation(this.typeSelectionCB,
                this.typeSelectionCB.validationSupport());

        this.domainKeysLV.registerValidator(Validator.createPredicateValidator(
                value -> {

                    return value != null;
                }, "You need to select a value!"));
        this.domainKeysLV
                .setValidationDecorator(new GraphicValidationDecoration());
        this.domainKeysLV.setErrorDecorationEnabled(true);
        this.validationGroup.registerSubValidation(this.domainKeysLV,
                this.domainKeysLV.validationSupport());

        this.domainValuesLV.registerValidator(Validator
                .createPredicateValidator(value -> {

                    return value != null;
                }, "You need to select a value!"));
        this.domainValuesLV
                .setValidationDecorator(new GraphicValidationDecoration());
        this.domainValuesLV.setErrorDecorationEnabled(true);
        this.validationGroup.registerSubValidation(this.domainValuesLV,
                this.domainValuesLV.validationSupport());
    }
}
