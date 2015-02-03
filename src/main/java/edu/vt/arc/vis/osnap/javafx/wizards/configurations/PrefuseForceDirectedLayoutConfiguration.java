package edu.vt.arc.vis.osnap.javafx.wizards.configurations;


import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.PrefuseForceDirectedLayout;


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
// @formatter:on



/**
 * The {@code PrefuseForceDirectedLayoutConfiguration} class provides the
 * {@link IConfiguration Configuration} for the
 * {@link PrefuseForceDirectedLayout}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.3
 * @since 0.5.0
 */
public class PrefuseForceDirectedLayoutConfiguration
        extends PrefuseLayoutConfiguration<PrefuseForceDirectedLayout> {

    private int     iterations;
    private boolean enforceBounds;


    /**
     * Returns the number of iterations.
     *
     * @return the number of iterations.
     */
    public int getIterations() {

        return this.iterations;
    }

    /**
     * Sets the number of iterations.
     *
     * @param iterations
     *            the number of iterations.
     */
    public void setIterations(int iterations) {

        this.iterations = iterations;
    }


    /**
     * @return the enforceBounds
     */
    public boolean enforcesBounds() {

        return enforceBounds;
    }

    /**
     * @param enforceBounds
     *            the enforceBounds to set
     */
    public void setEnforceBounds(boolean enforceBounds) {

        this.enforceBounds = enforceBounds;
    }



    /**
     * Creates a new instance of the
     * {@code PrefuseForceDirectedLayoutConfiguration} class.
     */
    public PrefuseForceDirectedLayoutConfiguration() {

        super(PrefuseForceDirectedLayout.name(), PrefuseForceDirectedLayout
                .description());
        
        this.iterations = 100;
        this.enforceBounds = false;
    }



    @Override
    public PrefuseForceDirectedLayout createConfiguredObject() {

        return new PrefuseForceDirectedLayout(this.enforcesBounds(),
                this.getIterations());
    }
}
