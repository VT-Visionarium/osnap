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


package graphVisualizer.gui.wizards.statuspanes;


import java.util.Observable;

import graphVisualizer.graph.metadata.SchemaEntry;
import graphVisualizer.gui.wizards.statusobjects.MappingStatus;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;


/**
 * Status pane to show user selections from wizard for mapping layout
 * @author Shawn P Neuman
 * 
 */
public class MappingStatusPane
extends BaseStatusPane {

    private Text                   metadataOrProperty;
    private Text                   selectedValues;
    private Text                   metadataOrPropertyTF;
    private Text                   value;
    private Text                   valueTF;
    private ListView<Object>       propertyValuesList;

    /**
     * @param lbl
     * 
     */
    public MappingStatusPane(String lbl) {


        super(lbl);

        metadataOrProperty = new Text("Using:");
        metadataOrPropertyTF = new Text();
        metadataOrPropertyTF.setStyle("-fx-font-weight: bold");

        value = new Text("With Value:");
        valueTF = new Text();
        valueTF.setStyle("-fx-font-weight: bold");

        selectedValues = new Text("Selected Values:");
        propertyValuesList = new ListView<>();

        getGrid().add(metadataOrProperty, 0, 2);
        getGrid().add(metadataOrPropertyTF, 1, 2);
        getGrid().add(value, 0, 3);
        getGrid().add(valueTF, 1, 3);
        getGrid().add(selectedValues, 0, 4);
        getGrid().add(propertyValuesList, 1, 4);



    }

    @Override
    public void update(Observable o, Object arg) {

        super.update(o, arg);

        MappingStatus ds = (MappingStatus) o;

        metadataOrPropertyTF.setText(ds.getProvider());

        if (ds.getKey() != null) {
            Object key = ds.getKey();
            if (key instanceof SchemaEntry) {

                SchemaEntry entry = (SchemaEntry) key;
                valueTF.setText(entry.getKey());
            }
            else {

                valueTF.setText(ds.getKey().toString());
            }
        }
        else {
            valueTF.setText("");
        }
        if (ds.getValuesList() != null) {
            propertyValuesList.setItems(FXCollections.observableArrayList(ds
                    .getValuesList()));
        }
        else {
            propertyValuesList.setItems(null);
        }

    }



}
