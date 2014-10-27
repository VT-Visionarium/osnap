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


package edu.vt.arc.vis.osnap.gui.wizards.statuspanes;


import edu.vt.arc.vis.osnap.gui.wizards.statusobjects.SolarSystemStatus;

import java.util.Observable;

import javafx.scene.text.Text;


/**
 * Status pane to show user selections from wizard for this layout
 * 
 * @author Shawn P Neuman
 * 
 */
public class SolarSystemStatusPane
        extends BaseTreeStatusPane {

    private Text metadata;
    private Text metadataTF;

    private Text inversePath;
    private Text inversePathTF;

    private Text minDistance;
    private Text minDistanceTF;

    private Text maxNodeSize;
    private Text maxNodeSizeTF;

    private Text depthMod;
    private Text depthModTF;

    /**
     * @param lbl
     *            the label
     */
    public SolarSystemStatusPane(String lbl) {

        super(lbl);

        metadata = new Text("MetaData:");
        metadataTF = new Text();
        metadataTF.setStyle("-fx-font-weight: bold");

        inversePath = new Text("Inverse Path ?");
        inversePathTF = new Text();
        inversePathTF.setStyle("-fx-font-weight: bold");

        minDistance = new Text("Minimal Distance:");
        minDistanceTF = new Text();
        minDistanceTF.setStyle("-fx-font-weight: bold");

        maxNodeSize = new Text("Maximum Node Size:");
        maxNodeSizeTF = new Text();
        maxNodeSizeTF.setStyle("-fx-font-weight: bold");

        depthMod = new Text("Depth Modifier:");
        depthModTF = new Text();
        depthModTF.setStyle("-fx-font-weight: bold");

        getGrid().add(metadata, 0, 3);
        getGrid().add(metadataTF, 1, 3);
        getGrid().add(inversePath, 0, 4);
        getGrid().add(inversePathTF, 1, 4);
        getGrid().add(minDistance, 0, 5);
        getGrid().add(minDistanceTF, 1, 5);
        getGrid().add(maxNodeSize, 0, 6);
        getGrid().add(maxNodeSizeTF, 1, 6);
        getGrid().add(depthMod, 0, 7);
        getGrid().add(depthModTF, 1, 7);

    }

    @Override
    public void update(Observable changedObject, Object changedProperty) {

        super.update(changedObject, changedProperty);

        if (changedObject instanceof SolarSystemStatus) {

            SolarSystemStatus status = (SolarSystemStatus) changedObject;

            if (status.getLayoutComponent() != null) {

                if (status.isInvertPathToRootNode()) {
                    inversePathTF.setText("True");
                }
                else {
                    inversePathTF.setText("False");
                }

                if (status.getMinimalDistance() != null) {
                    minDistanceTF.setText("" + status.getMinimalDistance());
                }
                else {
                    minDistanceTF.setText("");
                }

                if (status.getMaximumNodeSize() != null) {
                    maxNodeSizeTF.setText("" + status.getMaximumNodeSize());
                }
                else {
                    maxNodeSizeTF.setText("");
                }

                if (status.getDepthModifier() != null) {
                    depthModTF.setText("" + status.getDepthModifier());
                }
                else {
                    depthModTF.setText("");
                }
            }
        }
    }
}
