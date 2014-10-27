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


package edu.vt.arc.vis.osnap.gui.wizards;


import edu.vt.arc.vis.osnap.graph.Universe;
import edu.vt.arc.vis.osnap.gui.wizards.pages.NodeLinkTreeComponentsPage;
import edu.vt.arc.vis.osnap.gui.wizards.pages.PrefuseComponentPage;
import edu.vt.arc.vis.osnap.gui.wizards.pages.RootNodePage;
import edu.vt.arc.vis.osnap.gui.wizards.pages.RoutingPage;
import edu.vt.arc.vis.osnap.gui.wizards.statusobjects.NodeLinkTreeStatus;
import edu.vt.arc.vis.osnap.gui.wizards.statuspanes.NodeLinkTreeStatusPane;
import edu.vt.arc.vis.osnap.layout.prefuseComponents.NodeLinkTreeCoordinateLayoutComponent;
import javafx.stage.Stage;


/**
 * Wizard for selecting components for this layout type
 * 
 * @author Shawn P Neuman
 * 
 */
public class NodeLinkTreeWizard
        extends BaseLayoutWizard {


    private RootNodePage rootNodePage;


    /**
     * The wizard for creating a node link tree layout
     * @param universe
     */
    public NodeLinkTreeWizard(Universe universe) {

        this(null, universe);
    }
    
    /**
     * @param primary
     * @param uni
     */
    public NodeLinkTreeWizard(Stage primary, Universe uni) {

        super(primary, new NodeLinkTreeStatusPane("Node Link Tree Layout"),
                new NodeLinkTreeStatus(), NodeLinkTreeCoordinateLayoutComponent
                        .capabilities(), 2, uni, new RootNodePage(), new RoutingPage(),
                new NodeLinkTreeComponentsPage(), new PrefuseComponentPage());


        this.rootNodePage = (RootNodePage) this.getPage(2);

        this.getStatusObject().addObserver(rootNodePage);
        this.getStatusObject().addObserver((RoutingPage)this.getPage(3));
        this.getStatusObject().addObserver(
                (PrefuseComponentPage) this.getPage(5));

    }

    @Override
    public NodeLinkTreeStatus getStatusObject() {

        if (super.getStatusObject() instanceof NodeLinkTreeStatus) {
            return (NodeLinkTreeStatus) super.getStatusObject();
        }
        return null;
    }


}
