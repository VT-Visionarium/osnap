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


import edu.vt.arc.vis.osnap.javafx.wizards.statusobjects.RadialTreeStatus;

import java.util.Observable;

import javafx.scene.text.Text;


/**
 * Status pane to show user selections from wizard for this layout
 *
 * @author Shawn P Neuman
 *
 */
public class RadialTreeStatusPane
        extends PrefuseTreeStatusPane {

    private Text minRadius;
    private Text minRadiusTF;

    /**
     * @param lbl
     */
    public RadialTreeStatusPane(String lbl) {

        super(lbl, 1);


        minRadius = new Text("Minimum Radius:");
        minRadiusTF = new Text();
        minRadiusTF.setStyle("-fx-font-weight: bold");

        getGrid().add(minRadius, 0, 3);
        getGrid().add(minRadiusTF, 1, 3);

    }

    @Override
    public void update(Observable changedObject, Object changedProperty) {

        super.update(changedObject, changedProperty);

        if (changedObject instanceof RadialTreeStatus) {
            RadialTreeStatus status = (RadialTreeStatus) changedObject;

            if (status.getRadius() != null) {
                minRadiusTF.setText("" + status.getRadius());
            }
            else {
                minRadiusTF.setText("");
            }
        }
    }

}
