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


package graphVisualizer.gui.wizards;


import javafx.stage.Stage;
import graphVisualizer.graph.Universe;
import graphVisualizer.gui.wizards.pages.BalloonTreeComponentsPage;
import graphVisualizer.gui.wizards.pages.PrefuseComponentPage;
import graphVisualizer.gui.wizards.pages.RootNodePage;
import graphVisualizer.gui.wizards.pages.RoutingPage;
import graphVisualizer.gui.wizards.statusobjects.BalloonTreeStatus;
import graphVisualizer.gui.wizards.statuspanes.BalloonTreeStatusPane;
import graphVisualizer.layout.prefuseComponents.BalloonTreeCoordinateLayoutComponent;


/**
 * Wizard for selecting components for this layout type
 * 
 * @author Shawn P Neuman
 * 
 */
public class BalloonTreeWizard
        extends BaseLayoutWizard {

    private RootNodePage rootNodePage;


    /**
     * The wizard for creating a balloon tree layout
     * 
     * @param universe
     *            the universe ontology
     */
    public BalloonTreeWizard(Universe universe) {

        this(null, universe);
    }

    /**
     * @param primary
     * @param uni
     */
    public BalloonTreeWizard(Stage primary, Universe uni) {

        super(primary, new BalloonTreeStatusPane("Balloon Tree Layout"),
                new BalloonTreeStatus(), BalloonTreeCoordinateLayoutComponent
                        .capabilities(), 2, uni, new RootNodePage(),
                new RoutingPage(), new BalloonTreeComponentsPage(),
                new PrefuseComponentPage());

        this.rootNodePage = (RootNodePage) this.getPage(2);

        this.getStatusObject().addObserver(rootNodePage);
        this.getStatusObject().addObserver((RoutingPage) this.getPage(3));
        this.getStatusObject().addObserver(
                (BalloonTreeComponentsPage) this.getPage(4));
        this.getStatusObject().addObserver(
                (PrefuseComponentPage) this.getPage(5));

    }


    @Override
    public BalloonTreeStatus getStatusObject() {

        if (super.getStatusObject() instanceof BalloonTreeStatus) {
            return (BalloonTreeStatus) super.getStatusObject();
        }
        return null;
    }


}
