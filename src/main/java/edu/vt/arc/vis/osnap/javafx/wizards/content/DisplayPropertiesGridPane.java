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


package edu.vt.arc.vis.osnap.javafx.wizards.content;


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

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.GridPane;

import org.jutility.javafx.control.ListViewWithSearchPanel;
import org.jutility.javafx.control.labeled.LabeledComboBox;

import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObjectBasedValueTypeContainer;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.IMetadataContainer;
import edu.vt.arc.vis.osnap.javafx.stringConverters.GraphObjectBasedValueTypeContainerStringConverter;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.DisplayPropertiesPage;


/**
 * This panel contains all of the panes for choosing and editing display
 * properties
 *
 * @author Shawn P. Neuman, Peter J. Radics
 *
 */
public class DisplayPropertiesGridPane
extends GridPane {


    private final LabeledComboBox<String>                                      typeSelectionComboBox;
    private final ListViewWithSearchPanel<IGraphObjectBasedValueTypeContainer> domainKeys;
    private final ListViewWithSearchPanel<Object>                              valueListView;

    private Collection<IGraphObject>                                           selectedGraphObjects;

    /**
     * Creates a new instance of the {@link DisplayPropertiesGridPane} class;
     */
    public DisplayPropertiesGridPane() {

        this.setStyle("-fx-padding:10; -fx-background-color: honeydew; -fx-border-color: derive(honeydew, -30%); -fx-border-width: 3;");

        this.setHgap(25);
        this.setVgap(10);
        this.setPadding(new Insets(10, 25, 25, 25));

        this.typeSelectionComboBox = new LabeledComboBox<>("Property Type");
        this.typeSelectionComboBox.getItems().addAll("Metadata",
                "Graph Object Property");

        this.domainKeys = new ListViewWithSearchPanel<>("Available Keys",
                new GraphObjectBasedValueTypeContainerStringConverter<>());
        this.domainKeys.setVisible(false);


        this.valueListView = new ListViewWithSearchPanel<>("Available Values");
        this.valueListView.setVisible(false);
        this.valueListView.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE);


        this.add(this.typeSelectionComboBox, 0, 0);
        this.add(this.domainKeys, 1, 0);
        this.add(this.valueListView, 2, 0);


        this.setUpEventHandlers();
    }


    private Set<?> createValueList(
            final IGraphObjectBasedValueTypeContainer domainKey) {

        final Set<Object> values = new TreeSet<>();

        for (final IGraphObject graphObject : this.selectedGraphObjects) {

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

    /**
     * Populates the graph object schema list view
     *
     * @param container
     *
     */
    private void populateSchema(final IMetadataContainer container) {

        this.domainKeys.clear();
        this.domainKeys.getItems().addAll(
                container.getMetadataProperty().getSchema().getEntries());
        this.domainKeys.setVisible(true);
    }

    /**
     * populates the property list view
     *
     * @param graphObject
     *
     */
    private void populateProperties(final IGraphObject graphObject) {

        this.domainKeys.clear();
        this.domainKeys.getItems().addAll(graphObject.hasGraphProperties());
        this.domainKeys.setVisible(true);
    }



    /**
     * @return the combo box choice
     */
    public String getComboBoxChoice() {

        return this.typeSelectionComboBox.getSelectionModel().getSelectedItem();
    }



    /**
     * @return schema entry or property key
     */
    public IGraphObjectBasedValueTypeContainer getSchemaOrProperty() {

        return this.domainKeys.getSelectedItem();
    }



    /**
     * @return the choices from node property values list view
     */
    public List<Object> getValueChoices() {

        return this.valueListView.getSelectedItems();
    }



    /**
     * clears selections
     */
    public void clear() {

        this.typeSelectionComboBox.getSelectionModel().clearSelection();

        this.domainKeys.clear();
        this.domainKeys.setVisible(false);

        this.valueListView.clear();
        this.valueListView.setVisible(false);
    }


    /**
     * @param selectedGraphObjects
     *            list of selected IGraph objects
     */
    public void setSelectedGraphObjects(
            final Collection<IGraphObject> selectedGraphObjects) {

        this.selectedGraphObjects = selectedGraphObjects;
    }


    private void setUpEventHandlers() {

        this.typeSelectionComboBox
                .getSelectionModel()
                .selectedItemProperty()
        .addListener(
                        (ChangeListener<String>) (observable, oldValue,
                                newValue) -> {

                            DisplayPropertiesGridPane.this.domainKeys
                                    .clearSelection();
                            DisplayPropertiesGridPane.this.valueListView
                                    .clear();
                            DisplayPropertiesGridPane.this.toggleNextButton();
                            DisplayPropertiesGridPane.this.valueListView
                                    .setVisible(false);

                            if (newValue != null) {

                                switch (newValue) {

                                    case "Metadata":
                                        if (!DisplayPropertiesGridPane.this.selectedGraphObjects
                                                .isEmpty()) {

                                            final IGraphObject graphObject1 = DisplayPropertiesGridPane.this.selectedGraphObjects
                                                    .iterator().next();

                                            if (graphObject1 instanceof IMetadataContainer) {

                                                DisplayPropertiesGridPane.this
                                                        .populateSchema((IMetadataContainer) graphObject1);
                                            }
                                        }
                                        break;
                                    case "Graph Object Property":
                                        if (!DisplayPropertiesGridPane.this.selectedGraphObjects
                                                .isEmpty()) {

                                            final IGraphObject graphObject2 = DisplayPropertiesGridPane.this.selectedGraphObjects
                                                    .iterator().next();
                                            DisplayPropertiesGridPane.this
                                                    .populateProperties(graphObject2);
                                        }
                                        break;
                                    default:
                                        DisplayPropertiesGridPane.this.clear();
                                        break;
                                }
                            }
                        });

        this.domainKeys.selectedItemProperty().addListener(
                (ChangeListener<IGraphObjectBasedValueTypeContainer>) (
                        observable, oldValue, newValue) -> {

                    DisplayPropertiesGridPane.this.valueListView.clear();
                    DisplayPropertiesGridPane.this.toggleNextButton();
                    if (newValue != null) {

                                DisplayPropertiesGridPane.this.valueListView
                                .setVisible(true);
                                DisplayPropertiesGridPane.this.valueListView.getItems()
                                .addAll(DisplayPropertiesGridPane.this
                                        .createValueList(newValue));
                    }
                    else {
                                DisplayPropertiesGridPane.this.valueListView
                                .setVisible(false);
                    }

                });

        this.valueListView.selectedItemProperty()
                .addListener(
                        (ChangeListener<Object>) (observable, oldValue,
                                newValue) -> DisplayPropertiesGridPane.this
                                .toggleNextButton());
    }

    private void toggleNextButton() {

        final DisplayPropertiesPage par = (DisplayPropertiesPage) DisplayPropertiesGridPane.this
                .getParent().getParent();

        par.enableNextButton(!((this.valueListView.getSelectedItems() == null) || this.valueListView
                .getSelectedItems().isEmpty()));
    }
}
