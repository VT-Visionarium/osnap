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


import java.util.Observable;

import edu.vt.arc.vis.osnap.javafx.wizards.statusobjects.ForceDirectedStatus;
import edu.vt.arc.vis.osnap.javafx.wizards.statusobjects.FruchtermanReingoldStatus;
import javafx.scene.text.Text;


/**
 * Status pane to show user selections from wizard for this layout
 *
 * @author Shawn P Neuman
 *
 */
public class ForceDirectedStatusPane
        extends PrefuseStatusPane {

    private Text enforceBounds;
    private Text enforceBoundsTF;

    private Text maxIterations;
    private Text maxIterationsTF;


    /**
     * @param lbl
     * @param forceDirectedLayout
     */
    public ForceDirectedStatusPane(String lbl, boolean forceDirectedLayout) {

        super(lbl, (forceDirectedLayout ? 2 : 1));

        maxIterations = new Text("Maximum Iterations: ");
        maxIterationsTF = new Text();
        maxIterationsTF.setStyle("-fx-font-weight: bold");

        getGrid().add(maxIterations, 0, 2);
        getGrid().add(maxIterationsTF, 1, 2);

        if (forceDirectedLayout) {
            maxIterations.setText("Iterations");

            enforceBounds = new Text("Enforce Bounds? ");
            enforceBoundsTF = new Text();
            enforceBoundsTF.setStyle("-fx-font-weight: bold");

            getGrid().add(enforceBounds, 0, 3);
            getGrid().add(enforceBoundsTF, 1, 3);
        }
    }

    @Override
    public void update(Observable changedObject, Object changedProperty) {

        super.update(changedObject, changedProperty);
        if (changedObject instanceof FruchtermanReingoldStatus) {
            FruchtermanReingoldStatus status = (FruchtermanReingoldStatus) changedObject;

            if (status.getMaxIterations() != null) {
                maxIterationsTF.setText(status.getMaxIterations().toString());
            }
            else {
                maxIterationsTF.setText("");
            }

            if (changedObject instanceof ForceDirectedStatus) {
                ForceDirectedStatus fdstatus = (ForceDirectedStatus) changedObject;

                if (fdstatus.enforcesBounds() != null) {

                    enforceBoundsTF.setText(fdstatus.enforcesBounds()
                            .toString());
                }
                else {

                    enforceBoundsTF.setText("");
                }
            }
        }
    }
}
