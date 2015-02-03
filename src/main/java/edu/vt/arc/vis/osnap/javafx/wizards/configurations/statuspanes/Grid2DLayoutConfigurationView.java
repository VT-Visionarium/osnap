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

import edu.vt.arc.vis.osnap.core.domain.layout.complexComponents.Grid2DLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.Grid2DLayoutConfiguration;


/**
 * The {@code Grid2DLayoutConfigurationView} class provides a status panes for
 * {@link Grid2DLayoutConfiguration Grid2DLayoutConfigurations}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class Grid2DLayoutConfigurationView
        extends
        CoordinateLayoutConfigurationView<Grid2DLayout, Grid2DLayoutConfiguration> {


    /**
     * Creates a new instance of the {@code Grid2DLayoutConfigurationView}
     * class.
     * 
     * @param title
     *            the title for this {@link IConfigurationView}.
     */
    public Grid2DLayoutConfigurationView(final String title) {

        super(title, new Grid2DLayoutConfiguration());
    }
}
