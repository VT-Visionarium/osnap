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

/**
 *
 */
package edu.vt.arc.vis.osnap.javafx;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import org.controlsfx.control.action.Action;
import org.jutility.javafx.control.ListViewWithSearchPanel;

import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.core.domain.graph.metadata.Schema;
import edu.vt.arc.vis.osnap.javafx.dialogs.UniverseDialog;
import edu.vt.arc.vis.osnap.javafx.dialogs.UniversePropertiesDialog;
import edu.vt.arc.vis.osnap.javafx.events.MetadataChangedEvent;
import edu.vt.arc.vis.osnap.javafx.stringConverters.GraphObjectStringConverter;
import edu.vt.arc.vis.osnap.javafx.stringConverters.GraphObjectStringConverterConfiguration;
import edu.vt.arc.vis.osnap.javafx.widgets.MetaDataTable;
import edu.vt.arc.vis.osnap.javafx.widgets.SchemaTableView;


/**
 * @author Shawn P Neuman, Peter J. Radics
 * 
 */
public class UniverseDetailsVBox
        extends VBox {

    private final ObjectProperty<Universe>    universe;
    private ListViewWithSearchPanel<Universe> universeListView;
    private MetaDataTable                     uniMetaDataTable;
    private SchemaTableView                   uniSchemaTableView;
    private Schema                            uniSchema;

    private Action                            removeUniverse;
    private Action                            editUniverse;
    private Action                            universeProperties;

    /**
     * Returns the universe property.
     * 
     * @return the universe property.
     */
    public ObjectProperty<Universe> universe() {

        return this.universe;
    }

    /**
     * Returns the universe.
     * 
     * @return the universe.
     */
    public Universe getUniverse() {

        return this.universe.get();
    }

    /**
     * Sets the universe.
     * 
     * @param universe
     *            the universe.
     */
    public void setUniverse(Universe universe) {

        this.universe.set(universe);
    }

    /**
     * Creates a new instance of the {@link UniverseDetailsVBox} class.
     */
    public UniverseDetailsVBox() {

        this.universe = new SimpleObjectProperty<>();

        this.setStyle("-fx-background-color: cornsilk");

        VBox graphBox = new VBox();
        graphBox.setStyle("-fx-border-color: #000000; -fx-border-width: 1px");
        graphBox.setAlignment(Pos.TOP_LEFT);
        graphBox.setPadding(new Insets(10, 10, 10, 10));
        Text text = new Text();
        text.setFont(new Font(20));
        text.setStyle("-fx-font-weight: bold");
        text.setTextAlignment(TextAlignment.LEFT);
        text.setText("Universe Information");

        graphBox.getChildren().addAll(text);

        this.universeListView = new ListViewWithSearchPanel<>("Universe",
                new GraphObjectStringConverter<Universe>(
                        GraphObjectStringConverterConfiguration.ID));

        // inner grid pane to house universe info

        GridPane graphGrid = new GridPane();
        graphGrid.setHgap(25);
        graphGrid.setVgap(10);
        graphGrid.setPadding(new Insets(10, 10, 10, 10));

        uniMetaDataTable = new MetaDataTable("Universe Metadata");
        uniMetaDataTable.setVisible(true);

        uniSchemaTableView = new SchemaTableView("Universe Schema");
        uniSchemaTableView.setVisible(true);

        graphGrid.add(universeListView, 0, 1);
        graphGrid.add(uniMetaDataTable, 1, 1);
        graphGrid.add(uniSchemaTableView, 0, 2, 2, 1);

        graphBox.getChildren().add(graphGrid);
        this.getChildren().add(graphBox);

        this.setUpEventHandlers();
        this.setUpContextMenus();
    }

    /**
     * @param universe
     *            the universe being passed in, either from a file, or from
     *            creation
     */
    private void populate(Universe universe) {

        this.universeListView.getItems().clear();
        this.universeListView.getItems().add(this.getUniverse());

        this.uniSchema = universe.getUniverseSchema();
        this.uniMetaDataTable.iterateCollection(universe.getMetadataProperty(),
                this.uniSchema);

        this.uniSchemaTableView.iterateCollection(this.uniSchema);
    }

    /**
     * Clears all list views
     */
    private void clear() {

        universeListView.clear();
        uniMetaDataTable.clear();
        uniSchemaTableView.clear();
    }

    private void setUpEventHandlers() {

        this.universe.addListener((observable, oldValue, newValue) -> {

            this.clear();

            boolean universeEmpty = newValue == null;

            if (!universeEmpty) {

                this.populate(newValue);
            }
        });

        this.universeListView.selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {

                    boolean universeEmpty = newValue == null;
                    this.removeUniverse.disabledProperty().set(universeEmpty);
                    this.editUniverse.disabledProperty().set(universeEmpty);
                    this.universeProperties.disabledProperty().set(
                            universeEmpty);


                });
    }


    private void setUpContextMenus() {

        Action addUniverse = new Action("Add", actionEvent -> {

            new UniverseDialog(this, "Create Universe", null).showAndWait()
                    .ifPresent(newUniverse -> {

                        this.setUniverse(newUniverse);
                    });

        });

        this.editUniverse = new Action("Edit", actionEvent -> {

            Universe universe = this.universeListView.getSelectedItem();

            new UniverseDialog(this, "Edit Universe", universe).showAndWait()
                    .ifPresent(editedUniverse -> {

                        this.universeListView.clear();
                        this.universeListView.getItems().add(editedUniverse);
                    });
        });


        this.removeUniverse = new Action("Remove", actionEvent -> {

            Universe universe = this.universeListView.getSelectedItem();
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirm removal!");
            alert.setContentText("Are you sure you want to remove universe "
                    + universe.getId() + "?");


            alert.showAndWait().filter(buttonType -> {
                return buttonType == ButtonType.OK;
            }).ifPresent(param -> {

                this.universeListView.getItems().remove(universe);
                this.universe.set(null);
            });
        });

        this.universeProperties = new Action("Properties", actionEvent -> {

            new UniversePropertiesDialog(this, this.getUniverse()).show();
        });


        this.removeUniverse.disabledProperty().set(true);
        this.editUniverse.disabledProperty().set(true);
        this.universeProperties.disabledProperty().set(true);

        this.universeListView.contextMenuActions().addAll(addUniverse,
                editUniverse, removeUniverse, universeProperties);

        this.universeListView.addEventHandler(
                MetadataChangedEvent.METADATA_CHANGED,
                metadataEvent -> {

                    MetadataChangedEvent.Change change = metadataEvent
                            .getChange();

                    switch (change) {
                        case ADD:
                            uniSchemaTableView.iterateCollection(this
                                    .getUniverse().getNodeSchema());
                            break;
                        case REMOVE:
                        default:
                            break;
                    }

                });
    }
}
