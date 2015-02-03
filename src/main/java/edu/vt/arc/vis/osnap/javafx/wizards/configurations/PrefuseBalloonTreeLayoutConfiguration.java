package edu.vt.arc.vis.osnap.javafx.wizards.configurations;


// @ formatter:off
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
// @formatter:on

import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.PrefuseBalloonTreeLayout;


/**
 * The {@code BalloonTreeConfiguration} class provides the
 * {@link IConfiguration Configuration} for the
 * {@link PrefuseBalloonTreeLayout}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.3
 * @since 0.5.0
 */
public class PrefuseBalloonTreeLayoutConfiguration
        extends
        PrefuseTreeLayoutConfiguration<PrefuseBalloonTreeLayout> {

    private int minimumRadius;

    /**
     * Returns the minimum radius.
     * 
     * @return the minimum radius.
     */
    public int getMinimumRadius() {

        return this.minimumRadius;
    }

    /**
     * Sets the minimum radius.
     * 
     * @param minimumRadius
     *            the minimum radius.
     */
    public void setMinimumRadius(final int minimumRadius) {

        this.minimumRadius = minimumRadius;
    }



    /**
     * Creates a new instance of the {@code BalloonTreeConfiguration} class.
     */
    public PrefuseBalloonTreeLayoutConfiguration() {

        super(PrefuseBalloonTreeLayout.name(),
                PrefuseBalloonTreeLayout.description());
        
        this.minimumRadius = 2;
    }

    @Override
    public PrefuseBalloonTreeLayout createConfiguredObject() {

        PrefuseBalloonTreeLayout layoutComponent = new PrefuseBalloonTreeLayout(
                this.getRootNode(), this.getMinimumRadius());

        return layoutComponent;
    }
}
