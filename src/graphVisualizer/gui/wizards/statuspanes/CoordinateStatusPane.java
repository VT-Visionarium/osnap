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


import graphVisualizer.gui.wizards.statusobjects.GridStatusObject;
import graphVisualizer.gui.wizards.statusobjects.SphereLayoutStatus;

import java.util.Observable;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;


/**
 * Status pane to show user selections from wizard for this layout
 * 
 * @author Shawn P Neuman
 * 
 */
public class CoordinateStatusPane
        extends BaseStatusPane {


    private Text      xScale;
    private Text      yScale;
    private Text      zScale;
    private TextField xScaleTF;
    private TextField yScaleTF;
    private TextField zScaleTF;

    /**
     * @param lbl
     * 
     */
    public CoordinateStatusPane(String lbl) {

        super(lbl);

        xScale = new Text("Scale on X: ");
        yScale = new Text("Scale on Y: ");
        zScale = new Text("Scale on Z: ");

        xScaleTF = new TextField();
        xScaleTF.setStyle("-fx-font-weight: bold");
        yScaleTF = new TextField();
        yScaleTF.setStyle("-fx-font-weight: bold");
        zScaleTF = new TextField();
        zScaleTF.setStyle("-fx-font-weight: bold");

        getGrid().add(xScale, 0, 2);
        getGrid().add(xScaleTF, 1, 2);
        getGrid().add(yScale, 0, 3);
        getGrid().add(yScaleTF, 1, 3);
        getGrid().add(zScale, 0, 4);
        getGrid().add(zScaleTF, 1, 4);
    }

    @Override
    public void update(Observable statusObject, Object changedItem) {

        super.update(statusObject, changedItem);

        if (statusObject instanceof SphereLayoutStatus) {
            SphereLayoutStatus status = (SphereLayoutStatus) statusObject;

            xScaleTF.setText("" + status.getxScale());
            yScaleTF.setText("" + status.getyScale());
            zScaleTF.setText("" + status.getzScale());
        }
        else if( statusObject instanceof GridStatusObject) {
            GridStatusObject status = (GridStatusObject) statusObject;
            xScaleTF.setText("" + status.getxScale());
            yScaleTF.setText("" + status.getyScale());
            zScaleTF.setText("1");
        }
    }



}
