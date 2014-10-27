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

import edu.vt.arc.vis.osnap.gui.wizards.statusobjects.PrefuseTreeStatusObject;
import javafx.scene.text.Text;


/**
 * Status pane to show user selections from wizard for this layout
 *
 * @author Shawn P Neuman
 *
 */
public class PrefuseTreeStatusPane
        extends PrefuseStatusPane {


    private Text rootNode;
    private Text rootNodeTF;

    /**
     * @param lbl
     * @param offset
     */
    public PrefuseTreeStatusPane(String lbl, int offset) {

        super(lbl, offset + 1);


        rootNode = new Text("Root Node:");
        rootNodeTF = new Text();
        rootNodeTF.setStyle("-fx-font-weight: bold");


        getGrid().add(rootNode, 0, 2);
        getGrid().add(rootNodeTF, 1, 2);


    }

    @Override
    public void update(Observable changedObject, Object changedPropertyValue) {

        super.update(changedObject, changedPropertyValue);

        if (changedObject instanceof PrefuseTreeStatusObject) {
            PrefuseTreeStatusObject status = (PrefuseTreeStatusObject) changedObject;


            if (status.getRootNode() != null) {
                rootNodeTF.setText(status.getRootNode().toString());
            }
            else {
                rootNodeTF.setText("");
            }
        }
    }
}
