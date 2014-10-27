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
import edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents.SimpleCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.RoutingPage;
import edu.vt.arc.vis.osnap.javafx.wizards.statusobjects.BaseStatusObject;
import edu.vt.arc.vis.osnap.javafx.wizards.statuspanes.CoordinateStatusPane;
import javafx.stage.Stage;



/**
 * Wizard for selecting components for this layout type
 * 
 * @author Shawn P Neuman
 * 
 */
public class CoordinateLayoutWizard
        extends BaseLayoutWizard {


    /**
     * The wizard for creating a layout with specified coordinates
     * 
     * @param universe
     *            the universe ontology
     */
    public CoordinateLayoutWizard(Universe universe) {

        this(null, universe);
    }

    /**
     * @param primary
     * @param uni
     */
    public CoordinateLayoutWizard(Stage primary, Universe uni) {

        super(primary, new CoordinateStatusPane("Default Coordinate Layout"),
                new BaseStatusObject(), SimpleCoordinateLayoutComponent
                        .capabilities(), uni, new RoutingPage());

        this.getStatusObject().setLayoutComponent(
                new SimpleCoordinateLayoutComponent());

        this.getStatusObject().addObserver((RoutingPage) this.getPage(2));

    }


}
