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


import java.util.Observable;

import edu.vt.arc.vis.osnap.gui.wizards.statusobjects.PrefuseStatusObject;
import javafx.scene.text.Text;


/**
 * Status pane to show user selections from wizard for this layout
 *
 * @author Shawn P Neuman
 *
 */
public class PrefuseStatusPane
        extends BaseStatusPane {

    private Text durationTXT;
    private Text anchorTXT;
    private Text layoutBoundsTXT;
    private Text marginsTXT;

    private Text durationTF;
    private Text anchorTF;
    private Text layoutBoundsTF;
    private Text marginsTF;

    /**
     * @param lbl
     * @param offset
     */
    public PrefuseStatusPane(String lbl, int offset) {

        super(lbl);

        durationTXT = new Text("Layout Duration");
        anchorTXT = new Text("X, Y positions for anchor");
        layoutBoundsTXT = new Text("X, Y, W, H for layout boundaries");
        marginsTXT = new Text("T, B, L, R layout margins");

        durationTF = new Text();
        anchorTF = new Text();
        layoutBoundsTF = new Text();
        marginsTF = new Text();


        // Content starts at row 2, since BaseStatusPane contains the
        // VisualProperty and the restriction.
        int row = 2 + offset;
        getGrid().add(durationTXT, 0, row);
        getGrid().add(durationTF, 1, row);
        row++;
        getGrid().add(anchorTXT, 0, row);
        getGrid().add(anchorTF, 1, row);
        row++;
        getGrid().add(layoutBoundsTXT, 0, row);
        getGrid().add(layoutBoundsTF, 1, row);
        row++;
        getGrid().add(marginsTXT, 0, row);
        getGrid().add(marginsTF, 1, row);
    }

    @Override
    public void update(Observable changedObject, Object changedPropertyValue) {

        super.update(changedObject, changedPropertyValue);

        if (changedObject instanceof PrefuseStatusObject) {

            PrefuseStatusObject status = (PrefuseStatusObject) changedObject;

            if (status.getDuration() != null) {

                durationTF.setText(status.getDuration().toString());
            }
            else {
                durationTF.setText("");
            }

            if (status.getLayoutAnchor() != null) {
                anchorTF.setText(status.getLayoutAnchor().toString());
            }
            else {
                anchorTF.setText("");
            }
            if (status.getLayoutBounds() != null) {

                double topLeftX = status.getLayoutBounds().getTopLeftCorner()
                        .getX();
                double topLeftY = status.getLayoutBounds().getTopLeftCorner()
                        .getY();

                double width = status.getLayoutBounds().getBottomRightCorner()
                        .getX()
                        - topLeftX;
                double height = status.getLayoutBounds().getBottomRightCorner()
                        .getY()
                        - topLeftY;
                layoutBoundsTF.setText(topLeftX + ", " + topLeftY + ", "
                        + width + ", " + height);
            }

            else {
                layoutBoundsTF.setText("");
            }
            if (status.areMarginsSet()) {
                marginsTF.setText("" + status.getTopMargin() + ", "
                        + status.getBottomMargin() + ", "
                        + status.getLeftMargin() + ", "
                        + status.getRightMargin());
            }
            else {
                marginsTF.setText("");
            }
        }
    }
}
