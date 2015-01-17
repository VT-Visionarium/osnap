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

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.Metadata;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.MetadataMapProperty;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.Schema;
import edu.vt.arc.vis.osnap.javafx.dialogs.MetadataDialog;


/**
 * The {@code MetaDataTable} class provides a view for {@link Metadata}
 * information on a selected {@link IGraphObject GraphObject}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 *
 */
public class MetaDataTable
        extends VBox {


    private final Text                     tableLabel;
    private final TableView<Metadata>      metaDataTable;
    private final ObservableList<Metadata> metaDataList;
    private String                         filterString;
    private MetadataMapProperty            mdmp;
    private Collection<Metadata>           data;
    private ContextMenu                    cm;
    private Schema                         schema;
    private final SearchPanel              searchPanel;

    /**
     * Creates a new instance of the {@code MetaDataTable} class with the
     * provided title.
     * 
     * @param title
     *            the title.
     */
    public MetaDataTable(final String title) {


        // this.setStyle("-fx-background-color: #778899");
        // this.setStyle("-fx-border-color: #778899; -fx-border-width: 10px");
        this.tableLabel = new Text(title);
        this.tableLabel.setFont(Font.font("verdana", 16));
        // tableLabel.setFill(Color.WHITE);
        this.metaDataTable = new TableView<>();
        final TableColumn<Metadata, String> key = new TableColumn<>("Key");
        key.setPrefWidth(125);

        final TableColumn<Metadata, String> metadataType = new TableColumn<>(
                "Type");

        final TableColumn<Metadata, String> value = new TableColumn<>("Value");
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

        this.metaDataList = FXCollections.observableArrayList();

        this.metaDataTable.setItems(this.metaDataList);
        this.metaDataTable.getColumns().add(key);
        this.metaDataTable.getColumns().add(value);
        this.metaDataTable.getColumns().add(metadataType);
        metadataType.setVisible(false);

        this.searchPanel = new SearchPanel();

        this.getChildren().addAll(this.tableLabel, this.metaDataTable);

        this.searchPanel.getClose().addEventHandler(
                ActionEvent.ACTION,
                arg0 -> {

                    if (MetaDataTable.this.getChildren().contains(
                            MetaDataTable.this.searchPanel)) {
                        MetaDataTable.this.getChildren().remove(
                                MetaDataTable.this.searchPanel);
                    }


                });

        this.searchPanel.getFilterString().addListener(
                (ChangeListener<String>) (observable, oldVal, newVal) -> {

                    MetaDataTable.this.filterString = newVal;
                    if (MetaDataTable.this.mdmp != null) {
                        MetaDataTable.this.iterateCollection(
                                MetaDataTable.this.mdmp,
                                MetaDataTable.this.schema);
                    }

                });

        this.metaDataTable.addEventHandler(
                ContextMenuEvent.CONTEXT_MENU_REQUESTED, event -> {

                    final double screenx = event.getScreenX();
                    final double screeny = event.getScreenY();
                    if (MetaDataTable.this.cm != null) {
                        MetaDataTable.this.cm.hide();
                    }
                    MetaDataTable.this.setupContextMenu(screenx, screeny);

                });

        this.metaDataTable.setOnMouseClicked(event -> {

            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if (MetaDataTable.this.cm != null) {
                    MetaDataTable.this.cm.hide();
                }
            }

        });

        this.addEventHandler(
                KeyEvent.KEY_PRESSED,
                event -> {

                    if ((event.getCode().toString() == "F")
                            && event.isControlDown()) {
                        if (!MetaDataTable.this.getChildren().contains(
                                MetaDataTable.this.searchPanel)) {
                            MetaDataTable.this.getChildren().add(
                                    MetaDataTable.this.searchPanel);
                            MetaDataTable.this.searchPanel.setTextFocus();
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
    public void iterateCollection(final MetadataMapProperty data,
            final Schema graphSchema) {

        this.schema = graphSchema;
        this.mdmp = data;
        this.data = data.getMetadata();
        this.metaDataList.clear();
        for (final Metadata entry : this.data) {
            this.populate(entry);
        }
    }

    /**
     * populates the list from the entry passed in from iterate collection
     * method
     *
     * @param entry
     *            the entry to add to the list
     */
    public void populate(final Metadata entry) {

        if (this.filterString == null) {
            this.metaDataList.add(entry);
        }

        else if (entry.getKey().contains(this.filterString.toLowerCase())) {
            this.metaDataList.add(entry);

        }
        this.metaDataTable.setItems(this.metaDataList);

    }

    /**
     * clears the list
     */
    public void clear() {

        this.metaDataList.clear();
    }

    /**
     * creates a context menu to enable adding, editing, removing
     *
     * @param x
     *            x location to place menu
     * @param y
     *            y location to place menu
     */
    private void setupContextMenu(final double x, final double y) {

        if (this.cm != null) {
            this.cm.hide();
        }

        this.cm = new ContextMenu();

        final MenuItem addGraph = new MenuItem("Add");
        addGraph.setOnAction(actionEvent -> this.createMetadata());

        final MenuItem editGraph = new MenuItem("Edit");
        editGraph.setOnAction(actionEvent -> this.createEditDialog());

        final MenuItem remGraph = new MenuItem("Remove");
        remGraph.setOnAction(actionEvent -> this.removeMetadata());

        this.cm.getItems().addAll(addGraph, editGraph, remGraph);

        if (this.metaDataTable.getSelectionModel().getSelectedItem() == null) {

            remGraph.setDisable(true);
            editGraph.setDisable(true);
            // addDefaultValue.setDisable(true);
        }
        this.cm.show(this.metaDataTable, x, y);

    }

    private void removeMetadata() {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm Removal");
        alert.setContentText("Are you sure you want to remove "
                + this.getSelectedID());

        alert.showAndWait().filter(buttonType -> {
            return buttonType == ButtonType.OK;
        }).ifPresent(param -> {

            this.mdmp.removeMetadata(this.getSelectedID());
            this.iterateCollection(this.mdmp, this.schema);
        });
    }

    private void createEditDialog() {

        Metadata metadata = this.metaDataTable.getSelectionModel()
                .getSelectedItem();

        MetadataDialog dialog = new MetadataDialog(this, "Create Metadata",
                metadata, this.schema);

        dialog.showAndWait().ifPresent(updatedMetadata -> {

            this.mdmp.removeMetadata(metadata);
            this.mdmp.addMetadata(updatedMetadata);
            this.iterateCollection(this.mdmp, this.schema);
        });

    }

    private void createMetadata() {

        MetadataDialog dialog = new MetadataDialog(this, "Create Metadata",
                null, this.schema);

        dialog.showAndWait().ifPresent(metadata -> {
            this.mdmp.addMetadata(metadata);
            this.iterateCollection(this.mdmp, this.schema);

        });
    }

    /**
     * @return currently selected item id from list
     */
    public String getSelectedID() {

        return this.metaDataTable.getSelectionModel().getSelectedItem()
                .getKey();


    }

}
