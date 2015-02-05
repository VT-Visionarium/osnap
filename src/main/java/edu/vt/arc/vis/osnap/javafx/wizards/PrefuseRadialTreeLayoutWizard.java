package edu.vt.arc.vis.osnap.javafx.wizards;


//@formatter:off
/*
* _
* The Open Semantic Network Analysis Platform (OSNAP)
* _
* Copyright (C) 2012 - 2015 Visionarium at Virginia Tech
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
//@formatter:on


import javafx.stage.Window;
import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.PrefuseRadialTreeLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.ILayoutConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.PrefuseRadialTreeLayoutConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes.PrefuseRadialTreeLayoutConfigurationView;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.CapabilitySelectionPage;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.GraphObjectSelectionPage;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.NamingPage;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.PrefuseLayoutPage;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.PrefuseRadialTreeLayoutPage;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.RootNodeSelectionPage;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.RoutingPage;



/**
 * The {@code PrefuseRadialTreeLayoutWizard} provides a wizard for creating the
 * {@link PrefuseRadialTreeLayoutConfiguration Configuration} of the
 * {@link PrefuseRadialTreeLayout}.
 * 
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class PrefuseRadialTreeLayoutWizard
        extends
        LayoutConfigurationWizard<PrefuseRadialTreeLayout, PrefuseRadialTreeLayoutConfiguration> {

    private final PrefuseRadialTreeLayoutConfigurationView configurationView;


    /**
     * Creates a new instance of the {@code PrefuseRadialTreeLayoutWizard}
     * class.
     * 
     * @param owner
     *            the owner of this wizard.
     * @param universe
     *            the {@link Universe} for which the
     *            {@link ILayoutConfiguration LayoutConfiguration} is created.
     */
    public PrefuseRadialTreeLayoutWizard(Window owner, Universe universe) {

        super(owner, "Configure " + PrefuseRadialTreeLayout.name(), universe);

        this.configurationView = new PrefuseRadialTreeLayoutConfigurationView(
                "Configuration");
        WizardPane page1 = new CapabilitySelectionPage<>(
                this.configurationView, PrefuseRadialTreeLayout.capabilities());

        WizardPane page2 = new GraphObjectSelectionPage<>(
                this.configurationView, universe);

        WizardPane page3 = new RoutingPage<>(this.configurationView,
                PrefuseRadialTreeLayout.components());
        
        WizardPane page4 = new RootNodeSelectionPage<>(this.configurationView);

        WizardPane page5 = new PrefuseRadialTreeLayoutPage(
                this.configurationView);

        WizardPane page6 = new PrefuseLayoutPage<>(this.configurationView);

        WizardPane page7 = new NamingPage<>(this.configurationView);

        this.setFlow(new LinearFlow(page1, page2, page3, page4, page5, page6, page7));
    }

    @Override
    public PrefuseRadialTreeLayoutConfiguration getConfiguration() {

        return this.configurationView.getConfiguration();
    }

}
