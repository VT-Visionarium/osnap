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


/**
 * 
 */
package edu.vt.arc.vis.osnap.gui.wizards.statuspanes;


import java.util.Observable;
import java.util.Observer;

import edu.vt.arc.vis.osnap.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.gui.wizards.statusobjects.BaseStatusObject;
//import edu.vt.arc.vis.osnap.gui.wizards.statusobjects.IStatus;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


/**
 * @author Shawn P Neuman
 * Base status pane from which other status panes will be derived
 * All status panes have these items in common
 * 
 */
public abstract class BaseStatusPane
        extends VBox 
        implements Observer{




    private GridPane grid1;
    private Text                   label;
    private Text                   propertyType;
    private Text                   propertyTypeTF;
    private Text                   forTheseObjects;
    private ListView<IGraphObject> graphObjectList;

    
    /**
     * @param lbl
     * the title for this status pane
     */
    public BaseStatusPane(String lbl) {
        
        this.setStyle("-fx-padding:10; -fx-background-color: honeydew; -fx-border-color: derive(honeydew, -30%); -fx-border-width: 3;");
        label = new Text(lbl);
        label.styleProperty().setValue(
                "-fx-font-weight: bold; -fx-padding: 0 0 5 0;");


        grid1 = new GridPane();
        grid1.setHgap(25);
        grid1.setVgap(10);
        grid1.setPadding(new Insets(10, 25, 0, 25));

        propertyType = new Text("Visual Property Selection:");
        propertyTypeTF = new Text();
        propertyTypeTF.setStyle("-fx-font-weight: bold");

        forTheseObjects = new Text("For these Objects:");
        graphObjectList = new ListView<>();


        grid1.add(propertyType, 0, 0);
        grid1.add(propertyTypeTF, 1, 0);
        grid1.add(forTheseObjects, 0, 1);
        grid1.add(graphObjectList, 1, 1);

        this.getChildren().addAll(label, grid1);
        
    }
    
    /**
     * @return this grid pane
     * used to add information in extended classes
     */
    protected GridPane getGrid() {
        return this.grid1;
    }
    
    /*
     * updates the fields in the status pane from the information held in the
     * status object
     */
    @Override
    public void update(Observable arg0, Object arg1) {

//        IStatus status = (IStatus) arg0;
        BaseStatusObject base = (BaseStatusObject) arg0;

        if (base.getVisualProperty() == null) {

            propertyTypeTF.setText("");
        }
        else {

            propertyTypeTF.setText(base.getVisualProperty().toString());
        }


        if (base.getGraphObjectList() != null) {

            graphObjectList.setItems(FXCollections.observableArrayList(base
                    .getGraphObjectList()));
        }
        else {

            graphObjectList.setItems(null);
        }
        
 

    }

}
