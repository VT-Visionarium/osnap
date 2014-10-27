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


import graphVisualizer.gui.wizards.statusobjects.LabelStatus;

import java.util.Observable;

import javafx.scene.text.Text;


/**
 * Status pane to show user selections from wizard for this layout
 * 
 * @author Shawn P Neuman
 * 
 */
public class LabelStatusPane
        extends BaseStatusPane {


    private Text                   labelText;
    private Text                   labelTF;

    /**
     * @param lbl
     * 
     */
    public LabelStatusPane(String lbl) {


        super(lbl);

        labelText = new Text("Label:");
        labelTF = new Text();
        labelTF.setStyle("-fx-font-weight: bold");

        getGrid().add(labelText, 0, 3);
        getGrid().add(labelTF, 1, 3);

    }

    @Override
    public void update(Observable arg0, Object arg1) {

        LabelStatus status = (LabelStatus) arg0;

        super.update(arg0, arg1);

        if (status.getLabelText() != null) {
            labelTF.setText(status.getLabelText());
        }
        else {
            labelTF.setText("");
        }
    }



}
