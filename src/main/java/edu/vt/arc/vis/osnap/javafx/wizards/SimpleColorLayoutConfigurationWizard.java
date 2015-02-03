package edu.vt.arc.vis.osnap.javafx.wizards;


// @formatter:off
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
import edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents.SimpleColorLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.ILayoutConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.SimpleColorLayoutConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes.SimpleColorLayoutConfigurationView;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.CapabilitySelectionPage;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.GraphObjectSelectionPage;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.NamingPage;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.SimpleColorSelectionPage;


/**
 * The {@code SimpleColorLayoutConfigurationWizard} provides a wizard for
 * creating the {@link SimpleColorLayoutConfiguration Configuration} of the
 * {@link SimpleColorLayout}.
 * 
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class SimpleColorLayoutConfigurationWizard
        extends
        LayoutConfigurationWizard<SimpleColorLayout, SimpleColorLayoutConfiguration> {

    private final SimpleColorLayoutConfigurationView configurationView;

    /**
     * Creates a new instance of the
     * {@code SimpleColorLayoutConfigurationWizard} class.
     * 
     * @param owner
     *            the owner of this wizard.
     * @param universe
     *            the {@link Universe} for which the
     *            {@link ILayoutConfiguration LayoutConfiguration} is created.
     */
    public SimpleColorLayoutConfigurationWizard(Window owner, Universe universe) {

        super(owner, "Configure " + SimpleColorLayout.name(), universe);

        this.configurationView = new SimpleColorLayoutConfigurationView(
                "Configuration");
        WizardPane page1 = new CapabilitySelectionPage<>(configurationView,
                SimpleColorLayout.capabilities(), 1);

        WizardPane page2 = new GraphObjectSelectionPage<>(configurationView,
                universe);

        SimpleColorSelectionPage page3 = new SimpleColorSelectionPage(
                configurationView);

        WizardPane page4 = new NamingPage<>(configurationView);

        this.setFlow(new LinearFlow(page1, page2, page3, page4));
    }

    @Override
    public SimpleColorLayoutConfiguration getConfiguration() {

        return this.configurationView.getConfiguration();
    }
}
