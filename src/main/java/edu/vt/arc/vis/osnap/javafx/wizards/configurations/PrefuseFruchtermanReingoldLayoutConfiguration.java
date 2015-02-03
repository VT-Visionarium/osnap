package edu.vt.arc.vis.osnap.javafx.wizards.configurations;


// @formatter:off
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
// @formatter:on

import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.PrefuseFruchtermanReingoldLayout;



/**
 * The {@code PrefuseFruchtermanReingoldLayoutConfiguration} class provides the
 * {@link IConfiguration Configuration} for the
 * {@link PrefuseFruchtermanReingoldLayout}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.3
 * @since 0.5.0
 */
public class PrefuseFruchtermanReingoldLayoutConfiguration
        extends
        PrefuseLayoutConfiguration<PrefuseFruchtermanReingoldLayout> {

    private int maximumIterations;

    /**
     * Returns the maximum number of iterations.
     *
     * @return the maximum number of iterations.
     */
    public int getMaximumIterations() {

        return this.maximumIterations;
    }

    /**
     * Sets the maximum number of iterations.
     *
     * @param maximumIterations
     *            the maximum number of iterations.
     */
    public void setMaxIterations(int maximumIterations) {

        this.maximumIterations = maximumIterations;
    }



    /**
     * Creates a new instance of the
     * {@code PrefuseFruchtermanReingoldLayoutConfiguration} class.
     */
    public PrefuseFruchtermanReingoldLayoutConfiguration() {

        super(PrefuseFruchtermanReingoldLayout.name(),
                PrefuseFruchtermanReingoldLayout.description());
        
        this.maximumIterations = 700;
    }



    @Override
    public PrefuseFruchtermanReingoldLayout createConfiguredObject() {

        return new PrefuseFruchtermanReingoldLayout(
                this.getMaximumIterations());
    }
}
