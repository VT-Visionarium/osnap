/*******************************************************************************
 * Copyright 2014 Virginia Tech Visionarium
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/


package edu.vt.arc.vis.osnap.gui.wizards.content;


import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.GridPane;

import org.jutility.javafx.control.LabeledComboBox;
import org.jutility.javafx.control.ListViewWithSearchPanel;

import edu.vt.arc.vis.osnap.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.graph.common.IGraphObjectBasedValueTypeContainer;
import edu.vt.arc.vis.osnap.graph.metadata.IMetadataContainer;
import edu.vt.arc.vis.osnap.gui.stringConverters.GraphObjectBasedValueTypeContainerStringConverter;
import edu.vt.arc.vis.osnap.gui.wizards.pages.DisplayPropertiesPage;


/**
 * This panel contains all of the panes for choosing and editing display
 * properties
 * 
 * @author Shawn P. Neuman, Peter J. Radics
 * 
 */
public class DisplayPropertiesGridPane
        extends GridPane {


    private LabeledComboBox<String>                                      typeSelectionComboBox;
    private ListViewWithSearchPanel<IGraphObjectBasedValueTypeContainer> domainKeys;
    private ListViewWithSearchPanel<Object>                              valueListView;

    private Collection<IGraphObject>                                     selectedGraphObjects;

    /**
     * Creates a new instance of the {@link DisplayPropertiesGridPane} class;
     */
    public DisplayPropertiesGridPane() {

        this.setStyle("-fx-padding:10; -fx-background-color: honeydew; -fx-border-color: derive(honeydew, -30%); -fx-border-width: 3;");

        this.setHgap(25);
        this.setVgap(10);
        this.setPadding(new Insets(10, 25, 25, 25));

        typeSelectionComboBox = new LabeledComboBox<>("Property Type");
        typeSelectionComboBox.items().addAll("Metadata",
                "Graph Object Property");

        domainKeys = new ListViewWithSearchPanel<>(
                "Available Keys",
                new GraphObjectBasedValueTypeContainerStringConverter<>());
        domainKeys.setVisible(false);


        valueListView = new ListViewWithSearchPanel<>("Available Values");
        valueListView.setVisible(false);
        valueListView.setSelectionMode(SelectionMode.MULTIPLE);


        this.add(typeSelectionComboBox, 0, 0);
        this.add(domainKeys, 1, 0);
        this.add(valueListView, 2, 0);


        this.setUpEventHandlers();
    }


    private Set<?> createValueList(IGraphObjectBasedValueTypeContainer domainKey) {

        Set<Object> values = new TreeSet<>();

        for (IGraphObject graphObject : this.selectedGraphObjects) {

            Object value = domainKey.getValueForGraphObject(graphObject);
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
    private void populateSchema(IMetadataContainer container) {

        domainKeys.clear();
        domainKeys.items().addAll(
                container.getMetadataProperty().getSchema().getEntries());
        domainKeys.setVisible(true);
    }

    /**
     * populates the property list view
     * 
     * @param graphObject
     * 
     */
    private void populateProperties(IGraphObject graphObject) {

        domainKeys.clear();
        domainKeys.items().addAll(graphObject.hasGraphProperties());
        domainKeys.setVisible(true);
    }



    /**
     * @return the combo box choice
     */
    public String getComboBoxChoice() {

        return typeSelectionComboBox.getSelectedItem();
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

        return valueListView.getSelectedItems();
    }



    /**
     * clears selections
     */
    public void clear() {

        this.typeSelectionComboBox.clearSelection();

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
            Collection<IGraphObject> selectedGraphObjects) {

        this.selectedGraphObjects = selectedGraphObjects;
    }


    private void setUpEventHandlers() {

        typeSelectionComboBox.selectedItemProperty().addListener(
                new ChangeListener<String>() {

                    @Override
                    public void changed(
                            ObservableValue<? extends String> observable,
                            String oldValue, String newValue) {

                        domainKeys.clearSelection();
                        valueListView.clear();
                        toggleNextButton();
                        valueListView.setVisible(false);

                        if (newValue != null) {

                            switch (newValue) {

                                case "Metadata":
                                    if (!selectedGraphObjects.isEmpty()) {

                                        IGraphObject graphObject = selectedGraphObjects
                                                .iterator().next();

                                        if (graphObject instanceof IMetadataContainer) {

                                            populateSchema((IMetadataContainer) graphObject);
                                        }
                                    }
                                    break;
                                case "Graph Object Property":
                                    if (!selectedGraphObjects.isEmpty()) {

                                        IGraphObject graphObject = selectedGraphObjects
                                                .iterator().next();
                                        populateProperties(graphObject);
                                    }
                                    break;
                                default:
                                    clear();
                                    break;
                            }
                        }
                    }
                });

        domainKeys.selectedItemProperty().addListener(
                new ChangeListener<IGraphObjectBasedValueTypeContainer>() {

                    @Override
                    public void changed(
                            ObservableValue<? extends IGraphObjectBasedValueTypeContainer> observable,
                            IGraphObjectBasedValueTypeContainer oldValue,
                            IGraphObjectBasedValueTypeContainer newValue) {

                        valueListView.clear();
                        toggleNextButton();
                        if (newValue != null) {

                            valueListView.setVisible(true);
                            valueListView.items().addAll(
                                    createValueList(newValue));
                        }
                        else {
                            valueListView.setVisible(false);
                        }

                    }
                });

        valueListView.selectedItemProperty().addListener(
                new ChangeListener<Object>() {

                    @Override
                    public void changed(
                            ObservableValue<? extends Object> observable,
                            Object oldValue, Object newValue) {

                        toggleNextButton();

                    }
                });
    }
    
    private void toggleNextButton() {
        DisplayPropertiesPage par = (DisplayPropertiesPage) DisplayPropertiesGridPane.this
                .getParent().getParent();

        par.enableNextButton(!(valueListView.getSelectedItems() == null || valueListView
                .getSelectedItems().isEmpty()));
    }
}
