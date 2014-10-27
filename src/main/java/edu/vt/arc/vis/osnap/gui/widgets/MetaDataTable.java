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


package edu.vt.arc.vis.osnap.gui.widgets;


import edu.vt.arc.vis.osnap.graph.metadata.Metadata;
import edu.vt.arc.vis.osnap.graph.metadata.MetadataMapProperty;
import edu.vt.arc.vis.osnap.graph.metadata.Schema;
import edu.vt.arc.vis.osnap.gui.dialogs.AddMetadataDialog;

import java.util.Collection;

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

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog.Actions;
import org.controlsfx.dialog.Dialogs;


/**
 * component for viewing meta data information on a selected graph object
 * 
 * @author Shawn P Neuman
 * 
 */
public class MetaDataTable
        extends VBox {


    private Text                     tableLabel;
    private TableView<Metadata>      metaDataTable;
    private ObservableList<Metadata> metaDataList;
    private String                   filterString;
    private MetadataMapProperty      mdmp;
    private Collection<Metadata>           data;
    private ContextMenu              cm;
    private AddMetadataDialog        addDialog;
    private Metadata                 temp;
    private String                   id;
    private Schema                   graphSchema;
    private SearchPanel              searchPanel;
    private Stage                    warning;



    /**
     * constructor
     * 
     * @param label
     *            title value, changes based on which object meta data is
     *            associated with
     */
    public MetaDataTable(String label) {


        // this.setStyle("-fx-background-color: #778899");
        // this.setStyle("-fx-border-color: #778899; -fx-border-width: 10px");
        tableLabel = new Text(label);
        tableLabel.setFont(Font.font("verdana", 16));
        // tableLabel.setFill(Color.WHITE);
        metaDataTable = new TableView<>();
        TableColumn<Metadata, String> key = new TableColumn<>(
                "Key");
        key.setPrefWidth(125);

        TableColumn<Metadata, String> metadataType = new TableColumn<>(
                "Type");

        TableColumn<Metadata, String> value = new TableColumn<>(
                "Value");
        value.setPrefWidth(125);
        // the quoted item must match the id given to the corresponding
        // portion of the object
        key.setCellValueFactory(new PropertyValueFactory<Metadata, String>(
                "key"));
        metadataType
                .setCellValueFactory(new PropertyValueFactory<Metadata, String>(
                        "metadataType"));
        value.setCellValueFactory(new PropertyValueFactory<Metadata, String>(
                "value"));

        metaDataList = FXCollections.observableArrayList();

        metaDataTable.setItems(metaDataList);
        metaDataTable.getColumns().add(key);
        metaDataTable.getColumns().add(value);
        metaDataTable.getColumns().add(metadataType);
        metadataType.setVisible(false);

        searchPanel = new SearchPanel();

        this.getChildren().addAll(tableLabel, metaDataTable);

        searchPanel.getClose().addEventHandler(ActionEvent.ACTION,
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent arg0) {

                        if (MetaDataTable.this.getChildren().contains(
                                searchPanel)) {
                            MetaDataTable.this.getChildren().remove(
                                    MetaDataTable.this.searchPanel);
                        }


                    }

                });

        searchPanel.getFilterString().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldVal, String newVal) {

                filterString = newVal;
                if (mdmp != null) {
                    iterateCollection(mdmp, graphSchema);
                }

            }

        });

        metaDataTable.addEventHandler(ContextMenuEvent.CONTEXT_MENU_REQUESTED,
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

        metaDataTable.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    if (cm != null) {
                        cm.hide();
                    }
                }

            }

        });

        this.addEventHandler(KeyEvent.KEY_PRESSED,
                new EventHandler<KeyEvent>() {

                    @Override
                    public void handle(KeyEvent event) {

                        if (event.getCode().toString() == "F"
                                && event.isControlDown()) {
                            if (!MetaDataTable.this.getChildren().contains(
                                    searchPanel)) {
                                MetaDataTable.this.getChildren().add(
                                        MetaDataTable.this.searchPanel);
                                searchPanel.setTextFocus();
                            }

                        }
                    }

                });


    }

    /**
     * iterate over collection of graph objects
     * 
     * @param data
     *            the meta data property
     * @param graphSchema
     *            the collection of schema entries
     */
    public void iterateCollection(MetadataMapProperty data, Schema graphSchema) {

        this.graphSchema = graphSchema;
        mdmp = data;
        this.data = data.getMetadata();
        metaDataList.clear();
        for (Metadata entry : this.data) {
            populate(entry);
        }
    }

    /**
     * populates the list from the entry passed in from iterate collection
     * method
     * 
     * @param entry
     *            the entry to add to the list
     */
    public void populate(Metadata entry) {

        if (filterString == null) {
            metaDataList.add(entry);
        }

        else if (entry.getKey().contains(filterString.toLowerCase())) {
            metaDataList.add(entry);

        }
        metaDataTable.setItems(metaDataList);

    }

    /**
     * clears the list
     */
    public void clear() {

        metaDataList.clear();
    }

    /**
     * creates a context menu to enable adding, editing, removing
     * 
     * @param x
     *            x location to place menu
     * @param y
     *            y location to place menu
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

        MenuItem editGraph = new MenuItem("Edit");
        editGraph.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                createEditDialog();
            }

        });

        MenuItem remGraph = new MenuItem("Remove");
        remGraph.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                createRemoveDialog();
            }

        });

        cm.getItems().addAll(addGraph, editGraph, remGraph);

        if (this.metaDataTable.getSelectionModel().getSelectedItem() == null) {
            remGraph.setDisable(true);
            editGraph.setDisable(true);
            // addDefaultValue.setDisable(true);
        }
        cm.show(metaDataTable, x, y);

    }

    /**
     * create remove dialog
     */
    private void createRemoveDialog() {

        Action result = Dialogs
                .create()
                .title("Remove this item?")
                .message(
                        "Are you sure you want to remove "
                                + this.getSelectedID()).showConfirm();

        if (result == Actions.OK || result == Actions.YES) {

            mdmp.removeMetadata(id);
            iterateCollection(mdmp, graphSchema);
        }
    }

    /**
     * create edit dialog (currently not functioning)
     */
    private void createEditDialog() {


        // TODO: implement
    }

    /**
     * create add dialog
     */
    private void createAddDialog() {

        addDialog = new AddMetadataDialog(null, null, graphSchema);
        addDialog.setOnHiding(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {

                if (addDialog.getAdd()) {
                    if (addDialog.getKey().equals("New")) {
                        createDialog("to add a new entry, use the Schema table to add the new schema entry.  After adding, the new entry will appear on the list");
                    }
                    else {
                        switch (addDialog.getType()) {
                            case BOOLEAN:

                                temp = Metadata.createMetadata(
                                        addDialog.getKey(),
                                        addDialog.getType(),
                                        addDialog.getValue());
                                break;
                            case DOUBLE:

                                temp = Metadata.createMetadata(
                                        addDialog.getKey(),
                                        addDialog.getType(),
                                        (double) addDialog.getValue());
                                break;
                            case FLOAT:
                                temp = Metadata.createMetadata(
                                        addDialog.getKey(),
                                        addDialog.getType(),
                                        (float) addDialog.getValue());
                                break;
                            case INTEGER:
                                temp = Metadata.createMetadata(
                                        addDialog.getKey(),
                                        addDialog.getType(),
                                        (int) addDialog.getValue());
                                break;
                            case LONG:
                                temp = Metadata.createMetadata(
                                        addDialog.getKey(),
                                        addDialog.getType(),
                                        (long) addDialog.getValue());
                                break;
                            case STRING:
                                temp = Metadata.createMetadata(
                                        addDialog.getKey(),
                                        addDialog.getType(),
                                        (String) addDialog.getValue());
                                break;
                            default:
                                break;

                        }
                        mdmp.addMetadata(temp);
                        iterateCollection(mdmp, graphSchema);
                    }

                }

            }
        });

        addDialog.show();
    }

    /**
     * @return currently selected item id from list
     */
    public String getSelectedID() {

        return metaDataTable.getSelectionModel().getSelectedItem().getKey();


    }

    /**
     * creates a warning dialog for bad entries the warn string allows for
     * different messages to be passed in
     * 
     * @param warn
     *            the warning message
     */
    private void createDialog(String warn) {

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
