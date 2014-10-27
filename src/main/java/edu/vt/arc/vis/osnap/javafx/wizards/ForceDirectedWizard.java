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
import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.ForceDirectedCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.ForceDirectedComponentsPage;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.PrefuseComponentPage;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.RoutingPage;
import edu.vt.arc.vis.osnap.javafx.wizards.statusobjects.ForceDirectedStatus;
import edu.vt.arc.vis.osnap.javafx.wizards.statusobjects.FruchtermanReingoldStatus;
import edu.vt.arc.vis.osnap.javafx.wizards.statuspanes.ForceDirectedStatusPane;
import javafx.stage.Stage;


/**
 * Wizard for selecting components for this layout type
 * 
 * @author Shawn P Neuman
 * 
 */
public class ForceDirectedWizard
        extends BaseLayoutWizard {


    /**
     * The wizard for creating a Force Directed layout utilizes constructor
     * chaining as the wizard for Force Directed and Fruchterman-Reingold are
     * the same wizard.
     * 
     * @param universe
     *            the universe ontology
     */
    public ForceDirectedWizard(Universe universe) {

        this(null, universe);
    }

    /**
     * The wizard for creating a Force Directed layout with the stage known
     * 
     * @param primary
     * @param uni
     */
    public ForceDirectedWizard(Stage primary, Universe uni) {

        this(primary, uni, true);
    }

    /**
     * @param primary
     * @param uni
     * @param forceDirectedLayout
     *            this flag tells the difference between force directed and
     *            FruchtermanReingold. True for force directed, false for
     *            FruchtermanReingold
     */
    protected ForceDirectedWizard(Stage primary, Universe uni,
            boolean forceDirectedLayout) {

        super(primary, new ForceDirectedStatusPane("Force Directed Layout",
                forceDirectedLayout),
                (forceDirectedLayout ? new ForceDirectedStatus()
                        : new FruchtermanReingoldStatus()),
                ForceDirectedCoordinateLayoutComponent.capabilities(), 2, uni,
                new RoutingPage(), new ForceDirectedComponentsPage(),
                new PrefuseComponentPage());


        this.getStatusObject().addObserver((RoutingPage) this.getPage(2));
        this.getStatusObject().addObserver(
                (ForceDirectedComponentsPage) this.getPage(3));
        this.getStatusObject().addObserver(
                (PrefuseComponentPage) this.getPage(4));


    }



}
