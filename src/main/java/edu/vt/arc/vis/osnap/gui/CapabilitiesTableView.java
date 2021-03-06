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


package edu.vt.arc.vis.osnap.gui;


import java.util.Set;

import edu.vt.arc.vis.osnap.gui.wizards.content.CapabilitiesObject;
import edu.vt.arc.vis.osnap.visualization.VisualProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 * creates a default table for the capabilities of a given component
 * @author Shawn P Neuman
 * 
 */
public class CapabilitiesTableView
        extends VBox {

    private Text                                          tableLabel;
    private TableView<CapabilitiesObject>      dispTable;
    private ObservableList<CapabilitiesObject> dsList;

    /**
     * @param label the title of this page
     * 
     */
    @SuppressWarnings("unchecked")
    public CapabilitiesTableView(String label) {

        this.setStyle("-fx-background-color: cornsilk");
        tableLabel = new Text(label);
        this.tableLabel.setFont(Font.font("verdana", 16));
        dispTable = new TableView<>();

        TableColumn<CapabilitiesObject, VisualProperty> visualProperty = new TableColumn<>(
                "Visual Property");
        visualProperty.setMinWidth(125);
        TableColumn<CapabilitiesObject, Boolean> enabled = new TableColumn<>(
                "Current MappingStatus");
        enabled.setMinWidth(125);

        visualProperty
                .setCellValueFactory(new PropertyValueFactory<CapabilitiesObject, VisualProperty>(
                        "visualProperty"));
        enabled.setCellValueFactory(new PropertyValueFactory<CapabilitiesObject, Boolean>(
                "enabled"));


        dsList = FXCollections.observableArrayList();


        dispTable.getColumns().addAll(visualProperty, enabled);
        this.getChildren().addAll(tableLabel, dispTable);


    }

    /**
     * @param caps the capabilities of the IGraph Object
     */
    public void populateCapabilities(Set<CapabilitiesObject> caps) {

        clearList();
        for (CapabilitiesObject entry : caps) {
            dsList.add(entry);
        }

        dispTable.setItems(dsList);
    }
    
    /**
     * allows for clearing table
     */
    public void clearList() {
        dsList.clear();
    }

}
