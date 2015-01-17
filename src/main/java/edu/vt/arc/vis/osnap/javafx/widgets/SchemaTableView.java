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


package edu.vt.arc.vis.osnap.javafx.widgets;


import java.util.Arrays;
import java.util.Collection;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import org.controlsfx.control.action.Action;
import org.controlsfx.control.action.ActionUtils;

import edu.vt.arc.vis.osnap.core.domain.graph.metadata.Metadata;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.Schema;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.SchemaEntry;
import edu.vt.arc.vis.osnap.javafx.dialogs.MetadataDialog;
import edu.vt.arc.vis.osnap.javafx.dialogs.SchemaEntryDialog;


/**
 * panel contains a table of schema entry values, and is search-able by key
 *
 * @author Shawn P Neuman, Peter J. Radics
 *
 */
public class SchemaTableView
        extends VBox {

    private Schema                            schema;
    private final Text                        tableLabel;
    private final TableView<SchemaEntry>      schemaTable;
    private final ObservableList<SchemaEntry> schemaList;

    private String                            filterString;
    private Collection<SchemaEntry>           collection;
    private final SearchPanel                 searchPanel;


    private Action                            createSchemaEntry;
    private Action                            editSchemaEntry;
    private Action                            removeSchemaEntry;
    private Action                            addDefaultValue;
    private Action                            removeDefaultValue;

    /**
     * constructor, parameterized label allows for re-usability panel is re used
     * for graph schema, node schema, and edge schema
     *
     * @param label
     *            panel title
     */
    @SuppressWarnings("unchecked")
    public SchemaTableView(final String label) {

        this.tableLabel = new Text(label);
        this.tableLabel.setFont(Font.font("verdana", 16));
        this.schemaTable = new TableView<>();


        final TableColumn<SchemaEntry, String> key = new TableColumn<>("Key");
        key.setMinWidth(150);
        key.setPrefWidth(165);
        final TableColumn<SchemaEntry, String> type = new TableColumn<>("Type");
        type.setMinWidth(70);
        type.setPrefWidth(70);
        type.setMaxWidth(70);

        final TableColumn<SchemaEntry, String> defVal = new TableColumn<>(
                "Default Value");
        defVal.setMinWidth(95);
        defVal.setPrefWidth(100);

        final TableColumn<SchemaEntry, String> required = new TableColumn<>(
                "Required");
        required.setMinWidth(70);
        required.setPrefWidth(70);
        required.setMaxWidth(70);

        final TableColumn<SchemaEntry, String> unique = new TableColumn<>(
                "Unique");
        unique.setMinWidth(55);
        unique.setPrefWidth(55);
        unique.setMaxWidth(55);


        // the quoted item must match the id given to the corresponding
        // portion of the object
        key.setCellValueFactory(new PropertyValueFactory<SchemaEntry, String>(
                "key"));
        type.setCellValueFactory(new PropertyValueFactory<SchemaEntry, String>(
                "type"));
        defVal.setCellValueFactory(new PropertyValueFactory<SchemaEntry, String>(
                "defaultValueString"));
        required.setCellValueFactory(new PropertyValueFactory<SchemaEntry, String>(
                "required"));
        unique.setCellValueFactory(new PropertyValueFactory<SchemaEntry, String>(
                "unique"));

        this.schemaList = FXCollections.observableArrayList();

        this.schemaTable.getItems().addAll(this.schemaList);
        this.schemaTable.getColumns().addAll(key, type, defVal, required,
                unique);



        this.searchPanel = new SearchPanel();



        this.getChildren().addAll(this.tableLabel, this.schemaTable);



        this.setupContextMenu();
        this.setupEventHandlers();
    }

    /**
     * iterate over a collection of schema entries
     *
     * @param schema
     *            collection of schema entries
     */
    public void iterateCollection(final Schema schema) {

        this.schema = schema;
        this.collection = schema.getEntries();
        this.schemaTable.getItems().removeAll(this.schemaTable.getItems());

        this.schemaList.clear();
        for (final SchemaEntry entry : this.collection) {

            this.populate(entry);
        }

        this.schemaTable.getItems().addAll(this.schemaList);
    }

    /**
     * populate table with a schema entry from iterate collection
     *
     * @param entry
     *            schema entry to add to list
     */
    public void populate(final SchemaEntry entry) {

        if (this.filterString == null) {
            this.schemaList.add(entry);
        }

        else if (entry.getKey().contains(this.filterString)) {
            this.schemaList.add(entry);
        }


    }

    /**
     * clears table
     */
    public void clear() {

        this.schemaTable.getItems().removeAll(this.schemaList);
        this.schemaList.clear();
    }


    private void setupContextMenu() {

        this.createSchemaEntry = new Action("Add", actionEvent -> {

            this.createSchemaEntry();
        });

        this.editSchemaEntry = new Action("Edit", actionEvent -> {

            this.editSchemaEntry();
        });

        this.removeSchemaEntry = new Action("Remove", actionEvent -> {

            this.removeSchemaEntry();
        });

        this.addDefaultValue = new Action("Add Default Value", actionEvent -> {

            this.addDefaultValue();
        });

        this.removeDefaultValue = new Action("Remove Default Value",
                actionEvent -> {

                    this.removeDefaultValue();
                });


        this.schemaTable.setContextMenu(ActionUtils.createContextMenu(Arrays
                .asList(this.createSchemaEntry, this.editSchemaEntry,
                        this.removeSchemaEntry, ActionUtils.ACTION_SEPARATOR,
                        this.addDefaultValue, this.removeDefaultValue)));

        this.editSchemaEntry.disabledProperty().bind(
                this.schemaTable.getSelectionModel().selectedItemProperty()
                        .isNull());
        this.removeSchemaEntry.disabledProperty().bind(
                this.schemaTable.getSelectionModel().selectedItemProperty()
                        .isNull());
        this.addDefaultValue.disabledProperty().bind(
                this.schemaTable.getSelectionModel().selectedItemProperty()
                        .isNull());
        this.removeDefaultValue.disabledProperty().bind(
                this.schemaTable.getSelectionModel().selectedItemProperty()
                        .isNull());
    }

    private void setupEventHandlers() {

        this.searchPanel.getClose().addEventHandler(
                ActionEvent.ACTION,
                arg0 -> {

                    if (SchemaTableView.this.getChildren().contains(
                            SchemaTableView.this.searchPanel)) {
                        SchemaTableView.this.getChildren().remove(
                                SchemaTableView.this.searchPanel);
                    }


                });

        this.searchPanel
                .getFilterString()
                .addListener(
                        (ChangeListener<String>) (observable, oldVal, newVal) -> {

                            SchemaTableView.this.filterString = newVal;
                            if (SchemaTableView.this.collection != null) {
                                SchemaTableView.this
                                        .iterateCollection(SchemaTableView.this.schema);
                            }

                        });

        this.addEventHandler(KeyEvent.KEY_PRESSED, event -> {

            if ((event.getCode().toString() == "F") && event.isControlDown()) {

                if (!this.getChildren().contains(this.searchPanel)) {

                    this.getChildren().add(SchemaTableView.this.searchPanel);
                    this.searchPanel.setTextFocus();
                }

            }
        });

    }


    private void createSchemaEntry() {

        SchemaEntryDialog dialog = new SchemaEntryDialog(this,
                "Create Schema Entry", null);

        dialog.showAndWait().ifPresent(schemaEntry -> {

            this.schema.addEntry(schemaEntry);
        });
    }

    private void editSchemaEntry() {

        SchemaEntry selectedItem = this.schemaTable.getSelectionModel()
                .getSelectedItem();


        SchemaEntryDialog dialog = new SchemaEntryDialog(this,
                "Create Schema Entry", selectedItem);

        dialog.showAndWait().ifPresent(updatedSchemaEntry -> {

            this.schema.removeEntry(selectedItem);
            this.schema.addEntry(updatedSchemaEntry);
        });
    }


    private void removeSchemaEntry() {

        SchemaEntry selectedItem = this.schemaTable.getSelectionModel()
                .getSelectedItem();

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm Removal");
        alert.setContentText("Are you sure you want to remove "
                + selectedItem.getId());

        alert.showAndWait().filter(buttonType -> {

            return buttonType == ButtonType.OK;
        }).ifPresent(param -> {

            this.schema.removeEntry(selectedItem);
            this.iterateCollection(this.schema);
        });
    }

    private void addDefaultValue() {

        SchemaEntry selectedItem = this.schemaTable.getSelectionModel()
                .getSelectedItem();

        MetadataDialog dialog = new MetadataDialog(this, "Add Default Value",
                Metadata.createMetadata(selectedItem.getKey(),
                        selectedItem.getType(), null), this.schema);

        dialog.showAndWait().ifPresent(defaultValue -> {

            selectedItem.setDefaultValue(defaultValue);
            this.iterateCollection(SchemaTableView.this.schema);

            this.schemaTable.getColumns().get(2).setVisible(false);
            this.schemaTable.getColumns().get(2).setVisible(true);
        });

    }

    private void removeDefaultValue() {

        if (this.schemaTable.getSelectionModel().getSelectedItem()
                .getDefaultValue() != null) {

            final SchemaEntry entry = this.schema.getEntry(this.schemaTable
                    .getSelectionModel().getSelectedItem().getKey());

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirm Removal");
            alert.setContentText("Are you sure you want to remove "
                    + entry.getDefaultValueString());

            alert.showAndWait().filter(buttonType -> {

                return buttonType == ButtonType.OK;
            }).ifPresent(param -> {

                entry.setDefaultValue(null);
                this.iterateCollection(this.schema);
                this.schemaTable.getColumns().get(2).setVisible(false);
                this.schemaTable.getColumns().get(2).setVisible(true);
            });

        }
    }
}
