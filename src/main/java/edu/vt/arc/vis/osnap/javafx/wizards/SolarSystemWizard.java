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
import edu.vt.arc.vis.osnap.core.domain.layout.complexComponents.SolarSystemCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.RootNodePage;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.RoutingPage;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.SolarSystemComponentsPage;
import edu.vt.arc.vis.osnap.javafx.wizards.statusobjects.SolarSystemStatus;
import edu.vt.arc.vis.osnap.javafx.wizards.statuspanes.SolarSystemStatusPane;
import javafx.stage.Stage;


/**
 * Wizard for selecting components for this layout type
 * 
 * @author Shawn P Neuman
 * 
 */
public class SolarSystemWizard
        extends BaseLayoutWizard {



    private RootNodePage rootNodePage;

    /**
     * The wizard for creating a layout nested layout in 3d space, provides
     * hierarchy and connectivity information
     * 
     * @param universe
     *            the {@link Universe} to visualize.
     */
    public SolarSystemWizard(Universe universe) {

        this(null, universe);
    }

    /**
     * @param primary
     *            the stage in which this wizard resides.
     * @param uni
     *            the graph universe.
     * 
     */
    public SolarSystemWizard(Stage primary, Universe uni) {

        super(primary, new SolarSystemStatusPane(
                "Solar System Layout Selection"), new SolarSystemStatus(),
                SolarSystemCoordinateLayoutComponent.capabilities(), uni,
                new RootNodePage(), new RoutingPage(),
                new SolarSystemComponentsPage());

        this.rootNodePage = (RootNodePage) this.getPage(2);
        SolarSystemStatus status = this.getStatusObject();
        status.addObserver(rootNodePage);

        status.addObserver((RoutingPage) this.getPage(3));

        SolarSystemComponentsPage solarSystemPage = (SolarSystemComponentsPage) this
                .getPage(this.numberOfPages() - 2);

        status.addObserver(solarSystemPage);

    }


    @Override
    public SolarSystemStatus getStatusObject() {

        if (super.getStatusObject() instanceof SolarSystemStatus) {

            return (SolarSystemStatus) super.getStatusObject();
        }
        return null;
    }


}
