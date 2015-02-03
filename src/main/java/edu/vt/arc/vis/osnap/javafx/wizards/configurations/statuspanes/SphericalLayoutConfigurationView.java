package edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes;


// formatter:off
/*
 * _ The Open Semantic Network Analysis Platform (OSNAP) _ Copyright (C) 2012 -
 * 2014 Visionarium at Virginia Tech _ Licensed under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License. _
 */
// formatter:on

import edu.vt.arc.vis.osnap.core.domain.layout.complexComponents.SphericalLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.SphericalLayoutConfiguration;


/**
 * The {@code SphericalLayoutConfigurationView} class provides a status panes
 * for {@link SphericalLayoutConfiguration SphericalLayoutConfigurations}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class SphericalLayoutConfigurationView
        extends
        CoordinateLayoutConfigurationView<SphericalLayout, SphericalLayoutConfiguration> {


    /**
     * Creates a new instance of the {@code SphericalLayoutConfigurationView}
     * class.
     * 
     * @param title
     *            the title for this {@link IConfigurationView}.
     */
    public SphericalLayoutConfigurationView(final String title) {

        super(title, new SphericalLayoutConfiguration());
    }
}
