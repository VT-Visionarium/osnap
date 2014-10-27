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

import edu.vt.arc.vis.osnap.javafx.wizards.statusobjects.BalloonTreeStatus;

import java.util.Observable;

import javafx.scene.text.Text;


/**
 * @author Shawn P Neuman
 * 
 */
public class SquarifiedTreeStatusPane
        extends BaseStatusPane {
    
    
    private Text rootNode;
    private Text rootNodeTF;

    
    /**
     * @param lbl
     */
    public SquarifiedTreeStatusPane(String lbl) {

        super(lbl);

        rootNode = new Text("Root Node:");
        rootNodeTF = new Text();
        rootNodeTF.setStyle("-fx-font-weight: bold");


        getGrid().add(rootNode, 0, 2);
        getGrid().add(rootNodeTF, 1, 2);

    }

    @Override
    public void update(Observable arg0, Object arg1) {

        BalloonTreeStatus status = (BalloonTreeStatus) arg0;

        super.update(arg0, arg1);

        if (status.getRootNode() != null) {
            rootNodeTF.setText(status.getRootNode().toString());
        }
        else {
            rootNodeTF.setText("");
        }


    }
}
