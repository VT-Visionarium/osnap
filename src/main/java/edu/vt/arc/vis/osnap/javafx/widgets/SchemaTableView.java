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


import java.util.Collection;

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog.Actions;
import org.controlsfx.dialog.Dialogs;

import edu.vt.arc.vis.osnap.core.domain.graph.metadata.Metadata;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.Schema;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.SchemaEntry;
import edu.vt.arc.vis.osnap.javafx.dialogs.AddMetadataDialog;
import edu.vt.arc.vis.osnap.javafx.dialogs.AddSchemaDialog;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


/**
 * panel contains a table of schema entry values, and is search-able by key
 * 
 * @author Shawn P Neuman
 * 
 */
public class SchemaTableView
        extends VBox {

    private Schema                      schema;
    private Text                        tableLabel;
    private TableView<SchemaEntry>      schemaTable;
    private ObservableList<SchemaEntry> schemaList;
    private String                      filterString;
    private Collection<SchemaEntry>     collection;
    private AddSchemaDialog             addDialog;
    private ContextMenu                 cm;
    private String                      id;
    private SearchPanel                 searchPanel;
    private AddMetadataDialog           addDefaultDialog;
    private Stage                       warning;


    /**
     * constructor, parameterized label allows for re-usability panel is re used
     * for graph schema, node schema, and edge schema
     * 
     * @param label
     *            panel title
     */
    @SuppressWarnings("unchecked")
    public SchemaTableView(String label) {

        // this.setStyle("-fx-background-color: #778899");
        tableLabel = new Text(label);
        tableLabel.setFont(Font.font("verdana", 16));
        schemaTable = new TableView<>();


        TableColumn<SchemaEntry, String> key = new TableColumn<>(
                "Key");
        key.setMinWidth(150);
        key.setPrefWidth(165);
        TableColumn<SchemaEntry, String> type = new TableColumn<>(
                "Type");
        type.setMinWidth(70);
        type.setPrefWidth(70);
        type.setMaxWidth(70);

        TableColumn<SchemaEntry, String> defVal = new TableColumn<>(
                "Default Value");
        defVal.setMinWidth(95);
        defVal.setPrefWidth(100);

        TableColumn<SchemaEntry, String> required = new TableColumn<>(
                "Required");
        required.setMinWidth(70);
        required.setPrefWidth(70);
        required.setMaxWidth(70);

        TableColumn<SchemaEntry, String> unique = new TableColumn<>(
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

        schemaList = FXCollections.observableArrayList();

        schemaTable.getItems().addAll(schemaList);
        schemaTable.getColumns().addAll(key, type, defVal, required, unique);

        schemaTable.addEventHandler(ContextMenuEvent.CONTEXT_MENU_REQUESTED,
                new EventHandler<ContextMenuEvent>() {

                    @Override
                    public void handle(ContextMenuEvent event) {

                        double screenx = event.getScreenX();
                        double screeny = event.getScreenY();
                        if (cm != null) {
                            cm.hide();
                        }
                        contextMenu(screenx, screeny);
                    }

                });
        schemaTable.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    if (cm != null) {
                        cm.hide();
                    }
                }

            }

        });

        searchPanel = new SearchPanel();

        searchPanel.getClose().addEventHandler(ActionEvent.ACTION,
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent arg0) {

                        if (SchemaTableView.this.getChildren().contains(
                                searchPanel)) {
                            SchemaTableView.this.getChildren().remove(
                                    SchemaTableView.this.searchPanel);
                        }


                    }

                });

        searchPanel.getFilterString().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldVal, String newVal) {

                filterString = newVal;
                if (collection != null) {
                    iterateCollection(schema);
                }

            }

        });

        this.getChildren().addAll(tableLabel, schemaTable);

        this.addEventHandler(KeyEvent.KEY_PRESSED,
                new EventHandler<KeyEvent>() {

                    @Override
                    public void handle(KeyEvent event) {

                        if (event.getCode().toString() == "F"
                                && event.isControlDown()) {
                            if (!SchemaTableView.this.getChildren().contains(
                                    searchPanel)) {
                                SchemaTableView.this.getChildren().add(
                                        SchemaTableView.this.searchPanel);
                                searchPanel.setTextFocus();
                            }

                        }
                    }

                });


    }

    /**
     * iterate over a collection of schema entries
     * 
     * @param schema
     *            collection of schema entries
     */
    public void iterateCollection(Schema schema) {

        this.schema = schema;
        collection = schema.getEntries();
        schemaTable.getItems().removeAll(schemaTable.getItems());

        schemaList.clear();
        for (SchemaEntry entry : collection) {

            populate(entry);
        }

        schemaTable.getItems().addAll(schemaList);
    }

    /**
     * populate table with a schema entry from iterate collection
     * 
     * @param entry
     *            schema entry to add to list
     */
    public void populate(SchemaEntry entry) {

        if (filterString == null) {
            schemaList.add(entry);
        }

        else if (entry.getKey().contains(filterString)) {
            schemaList.add(entry);
        }


    }

    /**
     * clears table
     */
    public void clear() {

        schemaTable.getItems().removeAll(schemaList);
        schemaList.clear();
    }

    /**
     * context menu for modifying schema values
     * 
     * @param x
     *            x axis location of menu
     * @param y
     *            y axis location of menu
     */
    private void contextMenu(double x, double y) {

        if (cm != null) {
            cm.hide();
        }

        cm = new ContextMenu();

        MenuItem addGraph = new MenuItem("Add");
        addGraph.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                createAddDialog();
            }

        });

        MenuItem addDefaultValue = new MenuItem("Add Default Value");
        addDefaultValue.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                createAddDefaultValueDialog();
            }

        });

        MenuItem remDefaultValue = new MenuItem("Remove Default Value");
        remDefaultValue.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                createRemoveDefaultValueDialog();
            }

        });

        // MenuItem editGraph = new MenuItem("Edit");
        // editGraph.setOnAction(new EventHandler<ActionEvent>() {
        //
        // @Override
        // public void handle(ActionEvent arg0) {
        //
        // createEditDialog();
        // }
        //
        // });

        MenuItem remGraph = new MenuItem("Remove");
        remGraph.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                createRemoveDialog();
            }

        });

        cm.getItems().addAll(addGraph, addDefaultValue, remDefaultValue,
                remGraph);

        if (this.schemaTable.getSelectionModel().getSelectedItem() == null) {
            remGraph.setDisable(true);
            addDefaultValue.setDisable(true);
            remDefaultValue.setDisable(true);
            // editGraph.setDisable(true);
        }

        cm.show(schemaTable, x, y);

    }

    /**
     * creates dialog to remove an item
     */
    private void createRemoveDialog() {

        id = schemaTable.getSelectionModel().getSelectedItem().getKey();
        Action result = Dialogs.create().title("Remove this item?")
                .message("Are you sure you want to remove " + id).showConfirm();

        if (result == Actions.OK || result == Actions.YES) {

            schema.removeEntry(id);
            iterateCollection(schema);
        }

    }

    /**
     * creates a dialog to remove a default value
     */
    private void createRemoveDefaultValueDialog() {

        if (schemaTable.getSelectionModel().getSelectedItem().getDefaultValue() != null) {

            final SchemaEntry entry = schema.getEntry(schemaTable
                    .getSelectionModel().getSelectedItem().getKey());

            Action result = Dialogs
                    .create()
                    .title("Remove this item?")
                    .message(
                            "Are you sure you want to remove "
                                    + entry.getDefaultValueString())
                    .showConfirm();

            if (result == Actions.OK || result == Actions.YES) {

                entry.setDefaultValue(null);
                iterateCollection(schema);
                schemaTable.getColumns().get(2).setVisible(false);
                schemaTable.getColumns().get(2).setVisible(true);
            }

        }
    }

    /**
     * creates dialog to add an item
     */
    private void createAddDialog() {

        addDialog = new AddSchemaDialog(false);
        addDialog.setOnHiding(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {

                if (addDialog.getAdd()) {
                    schema.createEntry(addDialog.getKeyField(),
                            addDialog.getType(), null,
                            addDialog.getRequiredField(),
                            addDialog.getUniqueField());
                    iterateCollection(schema);
                }

            }
        });

        addDialog.show();
    }

    // /**
    // * creates dialog to edit an item. currently not functional
    // */
    // private void createEditDialog() {
    //
    // // editDialog = new
    // // EditDialog(listView.getSelectionModel().getSelectedItem());
    // System.out.println("I'm about to edit something");
    //
    //
    // }

    /**
     * creates dialog to add a default value
     */
    private void createAddDefaultValueDialog() {

        if (schemaTable.getSelectionModel().getSelectedItem() == null) {
            createWarningDialog("An entry must be selected");
        }

        else {
            addDefaultDialog = new AddMetadataDialog(
                    schemaTable.getSelectionModel().getSelectedItem().getKey(),
                    schemaTable.getSelectionModel().getSelectedItem().getType(),
                    schema);
            addDefaultDialog.setOnHiding(new EventHandler<WindowEvent>() {

                @Override
                public void handle(WindowEvent event) {

                    Metadata dv = Metadata.createMetadata(
                            addDefaultDialog.getKey(),
                            addDefaultDialog.getType(),
                            addDefaultDialog.getValue());

                    schemaTable.getSelectionModel().getSelectedItem()
                            .setDefaultValue(dv);


                    iterateCollection(schema);
                    schemaTable.getColumns().get(2).setVisible(false);
                    schemaTable.getColumns().get(2).setVisible(true);
                }

            });
            addDefaultDialog.show();
        }
    }

    /**
     * creates a warning dialog for bad entries the warn string allows for
     * different messages to be passed in
     * 
     * @param warn
     *            the warning message
     */
    private void createWarningDialog(String warn) {

        warning = new Stage();
        warning.setTitle("Warning !!");
        warning.initModality(Modality.APPLICATION_MODAL);
        Scene warnScene = new Scene(new VBox());
        HBox box = new HBox();
        GridPane grid2 = new GridPane();
        grid2.setAlignment(Pos.CENTER);
        grid2.setPadding(new Insets(25, 25, 25, 25));
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(0, 0, 25, 0));
        Text warnText = new Text(warn);
        warnText.setTextAlignment(TextAlignment.CENTER);
        warnText.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        warnText.setFill(Color.BLACK);

        Button okButton = new Button("OK");
        okButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

                warning.close();

            }

        });
        okButton.setTextAlignment(TextAlignment.CENTER);

        box.getChildren().add(okButton);

        grid2.add(warnText, 0, 0, 3, 1);

        ((VBox) warnScene.getRoot()).getChildren().addAll(grid2, box);

        warning.setScene(warnScene);

        warning.show();

    }



}
