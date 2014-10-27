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
import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.RadialTreeCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.PrefuseComponentPage;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.RadialTreeComponentsPage;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.RootNodePage;
import edu.vt.arc.vis.osnap.javafx.wizards.statusobjects.RadialTreeStatus;
import edu.vt.arc.vis.osnap.javafx.wizards.statuspanes.RadialTreeStatusPane;
import javafx.stage.Stage;


/**
 * Wizard for selecting components for this layout type
 * 
 * @author Shawn P Neuman
 * 
 */
public class RadialTreeWizard
        extends BaseLayoutWizard {

    private RootNodePage rootNodePage;

    /**
     * The wizard for creating a radial tree layout
     * @param universe
     */
    public RadialTreeWizard(Universe universe) {

        this(null, universe);
    }

    /**
     * @param primary
     * @param uni
     */
    public RadialTreeWizard(Stage primary, Universe uni) {

        super(primary, new RadialTreeStatusPane("Radial Tree Layout"),
                new RadialTreeStatus(), RadialTreeCoordinateLayoutComponent
                        .capabilities(), 2, uni, new RootNodePage(),
                new RadialTreeComponentsPage(), new PrefuseComponentPage());

        this.rootNodePage = (RootNodePage) this.getPage(2);
        
        this.getStatusObject().addObserver(rootNodePage);
        this.getStatusObject().addObserver(
                (RadialTreeComponentsPage) this.getPage(3));
        this.getStatusObject().addObserver(
                (PrefuseComponentPage) this.getPage(4));

    }



    @Override
    public RadialTreeStatus getStatusObject() {

        if (super.getStatusObject() instanceof RadialTreeStatus) {
            return (RadialTreeStatus) super.getStatusObject();
        }
        return null;
    }
}
