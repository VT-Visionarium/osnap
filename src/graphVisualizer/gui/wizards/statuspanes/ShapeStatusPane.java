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
package graphVisualizer.gui.wizards.statuspanes;


import graphVisualizer.gui.wizards.statusobjects.ShapeStatus;

import java.util.Observable;

import javafx.scene.text.Text;


/**
 * @author Shawn P Neuman
 * 
 */
public class ShapeStatusPane
        extends BaseStatusPane {

    private Text shape;
    private Text shapeTF;

    /**
     * @param lbl
     */
    public ShapeStatusPane(String lbl) {

        super(lbl);

        shape = new Text("Shape:");
        shapeTF = new Text();
        shapeTF.setStyle("-fx-font-weight: bold");

        getGrid().add(shape, 0, 2);
        getGrid().add(shapeTF, 1, 2);

    }

    /*
     * updates the fields in the status pane from the information held in the
     * status object
     */
    @Override
    public void update(Observable arg0, Object arg1) {

        ShapeStatus status = (ShapeStatus) arg0;

        super.update(arg0, arg1);

        if (status.getShape() != null) {
            shapeTF.setText(status.getShape().toString());
        }
        else {
            shapeTF.setText("");
        }

    }

}
