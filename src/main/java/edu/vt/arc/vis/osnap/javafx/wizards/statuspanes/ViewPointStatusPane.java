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



import edu.vt.arc.vis.osnap.core.domain.layout.mappedComponents.MappedViewpointLayoutComponent;
import edu.vt.arc.vis.osnap.javafx.wizards.statusobjects.ViewPointStatusObject;

import java.util.Observable;

import javafx.scene.text.Text;


/**
 * @author Shawn P Neuman
 * 
 */
public class ViewPointStatusPane
        extends BaseStatusPane {

    private Text cameraOffset;
    private Text xOffset;
    private Text yOffset;
    private Text zOffset;

    /**
     * @param lbl
     */
    public ViewPointStatusPane(String lbl) {

        super(lbl);


        cameraOffset = new Text("Viewpoint Camera Offsets: x, y, z");
        xOffset = new Text("0.0");
        yOffset = new Text("0.0");
        zOffset = new Text("20.0");

        getGrid().add(cameraOffset, 0, 2);
        getGrid().add(xOffset, 0, 3);
        getGrid().add(yOffset, 1, 3);
        getGrid().add(zOffset, 2, 3);

    }

    @Override
    public void update(Observable arg0, Object arg1) {

//        System.out.println("update called in view point status pane");
        ViewPointStatusObject status = (ViewPointStatusObject) arg0;

        super.update(arg0, arg1);

        xOffset.setText(""
                + ((MappedViewpointLayoutComponent) status.getLayoutComponent())
                        .getOffset().getX());
        yOffset.setText(""
                + ((MappedViewpointLayoutComponent) status.getLayoutComponent())
                        .getOffset().getY());
        zOffset.setText(""
                + ((MappedViewpointLayoutComponent) status.getLayoutComponent())
                        .getOffset().getZ());
    }

}
