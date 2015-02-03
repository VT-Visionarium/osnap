package edu.vt.arc.vis.osnap.javafx.wizards.configurations;


import edu.vt.arc.vis.osnap.core.domain.layout.complexComponents.SphericalLayout;


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

/**
 * The {@code SphericalLayoutConfiguration} class provides the
 * {@link IConfiguration Configuration} for the {@link SphericalLayout}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class SphericalLayoutConfiguration
        extends CoordinateLayoutConfiguration<SphericalLayout> {


    /**
     * Creates a new instance of the {@code SphericalLayoutConfiguration} class.
     */
    public SphericalLayoutConfiguration() {

        super(SphericalLayout.name(), SphericalLayout.description());
    }



    @Override
    public SphericalLayout createConfiguredObject() {

        return new SphericalLayout(this.getFirstComponentScale(),
                this.getSecondComponentScale(), this.getThirdComponentScale());
    }
}
