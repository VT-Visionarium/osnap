package edu.vt.arc.vis.osnap.javafx.wizards.configurations;


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


import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.PrefuseRadialTreeLayout;


/**
 * The {@code RadialTreeConfiguration} class provides the {@link IConfiguration
 * Configuration} for the {@link PrefuseRadialTreeLayout}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.3
 * @since 0.5.0
 */
public class PrefuseRadialTreeLayoutConfiguration
        extends
        PrefuseTreeLayoutConfiguration<PrefuseRadialTreeLayout> {

    private int radiusIncrement;



    /**
     * Returns the radius increment.
     * 
     * @return the radius increment.
     */
    public int getRadiusIncrement() {

        return this.radiusIncrement;
    }

    /**
     * Sets the radius increment.
     * 
     * @param radiusIncrement
     *            the radius increment.
     */
    public void setRadiusIncrement(final int radiusIncrement) {

        this.radiusIncrement = radiusIncrement;
    }



    /**
     * Creates a new instance of the {@code RadialTreeStatus} class.
     */
    public PrefuseRadialTreeLayoutConfiguration() {

        super(PrefuseRadialTreeLayout.name(),
                PrefuseRadialTreeLayout.description());
    }



    @Override
    public PrefuseRadialTreeLayout createConfiguredObject() {

        return new PrefuseRadialTreeLayout(this.getRootNode(),
                this.getRadiusIncrement());
    }

}
