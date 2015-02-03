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
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObjectBasedValueTypeContainer;
import edu.vt.arc.vis.osnap.core.domain.layout.common.BaseMappedLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.common.IMappedLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.ILayoutConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.IMappedLayoutConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.MappedLayoutConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes.MappedLayoutConfigurationView;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.CapabilitySelectionPage;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.DomainKeyValueSelectionPage;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.GraphObjectSelectionPage;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.MappingPage;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.NamingPage;


/**
 * The {@code MappedLayoutWizard} provides a wizard for creating the
 * {@link MappedLayoutConfiguration Configuration} of a {@link IMappedLayout
 * MappedLayout}.
 * 
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class MappedLayoutWizard
        extends
        LayoutConfigurationWizard<IMappedLayout<? extends IGraphObjectBasedValueTypeContainer, ?, ?>, IMappedLayoutConfiguration> {

    private final MappedLayoutConfigurationView configurationView;


    /**
     * Creates a new instance of the {@code MappedLayoutWizard} class.
     * 
     * @param owner
     *            the owner of this wizard.
     * @param universe
     *            the {@link Universe} for which the
     *            {@link ILayoutConfiguration LayoutConfiguration} is created.
     */
    public MappedLayoutWizard(Window owner, Universe universe) {

        super(owner, "Configure " + BaseMappedLayout.name(), universe);

        this.configurationView = new MappedLayoutConfigurationView(
                "Configuration");


        WizardPane page1 = new CapabilitySelectionPage<>(
                this.configurationView);

        WizardPane page2 = new GraphObjectSelectionPage<>(
                this.configurationView, universe);

        WizardPane page3 = new DomainKeyValueSelectionPage(this.configurationView);

        WizardPane page4 = new MappingPage(this.configurationView);

        WizardPane page5 = new NamingPage<>(this.configurationView);

        this.setFlow(new LinearFlow(page1, page2, page3, page4, page5));
    }

    public IMappedLayoutConfiguration getConfiguration() {
        
        return this.configurationView.getConfiguration();
    };
}
