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


import graphVisualizer.gui.wizards.statusobjects.ColorStatus;

import java.util.Observable;

import javafx.scene.text.Text;


/**
 * Status pane to show user selections from wizard for this layout
 * @author Shawn P Neuman
 */
public class ColorStatusPane
        extends BaseStatusPane {


    private Text                   color;
    private Text                   colorTF;

    /**
     * @param lbl
     * 
     */
    public ColorStatusPane(String lbl) {


        super(lbl);

        color = new Text("Color:");
        colorTF = new Text();
        colorTF.setStyle("-fx-font-weight: bold");

        
        getGrid().add(color, 0, 2);
        getGrid().add(colorTF, 1, 2);

        
    }

    @Override
    public void update(Observable arg0, Object arg1) {

        ColorStatus status = (ColorStatus) arg0;
        

        super.update(arg0, arg1);
        
        if (status.getColor() != null) {
            colorTF.setText(status.getColor().toString());
        }
        else {
            colorTF.setText("");
        }
    }
    
   
}
