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

import javafx.stage.Stage;
import javafx.stage.Window;
import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents.SimpleColorLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents.SimpleLabelTextLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.ILayoutConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.SimpleLabelTextLayoutConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes.SimpleLabelTextLayoutConfigurationView;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.CapabilitySelectionPage;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.GraphObjectSelectionPage;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.NamingPage;

/**
 * The {@code SimpleLabelTextLayoutWizard} provides a wizard for creating the
 * {@link SimpleLabelTextLayoutConfiguration Configuration} of the
 * {@link SimpleLabelTextLayout}.
 * 
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class SimpleLabelTextLayoutWizard
		extends
		LayoutConfigurationWizard<SimpleLabelTextLayout, SimpleLabelTextLayoutConfiguration> {

	private final SimpleLabelTextLayoutConfigurationView configurationView;

	/**
	 * Creates a new instance of the {@code SimpleLabelTextLayoutWizard} class.
	 * 
	 * @param owner
	 *            the owner of this wizard.
	 * @param universe
	 *            the {@link Universe} for which the
	 *            {@link ILayoutConfiguration LayoutConfiguration} is created.
	 */
	public SimpleLabelTextLayoutWizard(Stage owner, Universe universe) {
		this((Window) owner, universe);
	}

	/**
	 * Creates a new instance of the {@code SimpleLabelTextLayoutWizard} class.
	 * 
	 * @param owner
	 *            the owner of this wizard.
	 * @param universe
	 *            the {@link Universe} for which the
	 *            {@link ILayoutConfiguration LayoutConfiguration} is created.
	 */
	public SimpleLabelTextLayoutWizard(Window owner, Universe universe) {

		super(owner, "Configure " + SimpleLabelTextLayout.name(), universe);

		this.configurationView = new SimpleLabelTextLayoutConfigurationView(
				"Configuration");
		WizardPane page1 = new CapabilitySelectionPage<>(configurationView,
				SimpleColorLayout.capabilities(), 1);

		WizardPane page2 = new GraphObjectSelectionPage<>(configurationView,
				universe);

		WizardPane page3 = new NamingPage<>(configurationView);

		this.setFlow(new LinearFlow(page1, page2, page3));
	}

	@Override
	public SimpleLabelTextLayoutConfiguration getConfiguration() {

		return this.configurationView.getConfiguration();
	}

}
