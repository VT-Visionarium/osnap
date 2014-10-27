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
package edu.vt.arc.vis.osnap.javafx.wizards;

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


import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.core.domain.layout.complexComponents.TwoDGridCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.CoordinateScalePage;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.RoutingPage;
import edu.vt.arc.vis.osnap.javafx.wizards.statusobjects.GridStatusObject;
import edu.vt.arc.vis.osnap.javafx.wizards.statuspanes.CoordinateStatusPane;
import javafx.stage.Stage;


/**
 * @author Shawn P Neuman
 * 
 */
public class TwoDGridWizard
        extends BaseLayoutWizard {


    /**
     * The wizard for creating a layout in a 2d grid, not particularly useful at
     * this point
     * 
     * @param universe
     */
    public TwoDGridWizard(Universe universe) {

        this(null, universe);
    }

    /**
     * @param primary
     * @param uni
     */
    public TwoDGridWizard(Stage primary, Universe uni) {

        super(primary,
                new CoordinateStatusPane("Two D Grid Coordinate Layout"),
                new GridStatusObject(), TwoDGridCoordinateLayoutComponent
                        .capabilities(), 2, uni, new CoordinateScalePage(), new  RoutingPage());

        this.getStatusObject().setLayoutComponent(
                new TwoDGridCoordinateLayoutComponent());
        
        this.getStatusObject().addObserver((RoutingPage)this.getPage(3));
        this.getStatusObject().addObserver(
                (CoordinateStatusPane) this.getStatusPane());
        this.getStatusObject().addObserver(this.getNamingPage());

    }
    
    @Override
    public GridStatusObject getStatusObject() {
        if(super.getStatusObject() instanceof GridStatusObject) {
            return (GridStatusObject) super.getStatusObject();
        }
        return null;
    }

}
