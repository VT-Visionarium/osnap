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


package edu.vt.arc.vis.osnap.javafx.wizards.statuspanes;

/*
 * _
 * The Open Semantic Network Analysis Platform (OSNAP)
 * _
 * Copyright (C) 2012 - 2014 Visionarium at Virginia Tech
 * _
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * _
 */


import edu.vt.arc.vis.osnap.javafx.wizards.statusobjects.NodeLinkTreeStatus;

import java.util.Observable;

import javafx.scene.text.Text;


/**
 * Status pane to show user selections from wizard for this layout
 *
 * @author Shawn P Neuman
 *
 */
public class NodeLinkTreeStatusPane
        extends PrefuseTreeStatusPane {

    private Text depthSpacing;
    private Text depthSpacingTF;
    private Text siblingSpacing;
    private Text siblingSpacingTF;
    private Text subTreeSpacing;
    private Text subTreeSpacingTF;
    private Text orientation;
    private Text orientationTF;



    /**
     * @param lbl
     */
    public NodeLinkTreeStatusPane(String lbl) {

        super(lbl, 4);

        orientation = new Text("Orientation: ");
        orientationTF = new Text();
        orientationTF.setStyle("-fx-font-weight: bold");

        depthSpacing = new Text("Depth Spacing: ");
        depthSpacingTF = new Text();
        depthSpacingTF.setStyle("-fx-font-weight: bold");

        siblingSpacing = new Text("Space Between Siblings: ");
        siblingSpacingTF = new Text();
        siblingSpacingTF.setStyle("-fx-font-weight: bold");

        subTreeSpacing = new Text("Space Between Neighboring Sub-Trees: ");
        subTreeSpacingTF = new Text();
        subTreeSpacingTF.setStyle("-fx-font-weight: bold");


        getGrid().add(orientation, 0, 3);
        getGrid().add(orientationTF, 1, 3);
        getGrid().add(depthSpacing, 0, 4);
        getGrid().add(depthSpacingTF, 1, 4);
        getGrid().add(siblingSpacing, 0, 5);
        getGrid().add(siblingSpacingTF, 1, 5);
        getGrid().add(subTreeSpacing, 0, 6);
        getGrid().add(subTreeSpacingTF, 1, 6);

    }


    @Override
    public void update(Observable changedObject, Object changedProperty) {

        super.update(changedObject, changedProperty);

        if (changedObject instanceof NodeLinkTreeStatus) {
            NodeLinkTreeStatus status = (NodeLinkTreeStatus) changedObject;

            if (status.getDepthSpacing() != null) {
                depthSpacingTF.setText(status.getDepthSpacing().toString());
            }
            else {
                depthSpacingTF.setText("");
            }

            if (status.getOrientation() != null) {

                orientationTF.setText(status.getOrientation().toString());
            }
            else {
                orientationTF.setText("");
            }

            if (status.getSpaceBetweenSiblings() != null) {

                siblingSpacingTF.setText(status.getSpaceBetweenSiblings()
                        .toString());
            }
            else {
                siblingSpacingTF.setText("");
            }

            if (status.getSpaceBetweenNeighboringSubtrees() != null) {
                subTreeSpacingTF.setText(status
                        .getSpaceBetweenNeighboringSubtrees().toString());
            }
            else {
                subTreeSpacingTF.setText("");
            }

        }
    }

}
