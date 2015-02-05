package edu.vt.arc.vis.osnap.javafx.wizards;


//@formatter:off
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
//@formatter:on

import java.util.Objects;

import javafx.stage.Window;
import edu.vt.arc.vis.osnap.core.domain.graph.Universe;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ILayout;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.ILayoutConfiguration;


/**
 * The abstract {@code LayoutConfigurationWizard} class provides a base for
 * wizards that create a {@link ILayoutConfiguration} for a {@link ILayout}.
 * 
 * @author Shawn P Neuman, Peter J. Radics
 * @param <O>
 *            the type of the {@link ILayout}.
 * @param <C>
 *            the type of the {@link ILayoutConfiguration}.
 * 
 */
public abstract class LayoutConfigurationWizard<O extends ILayout, C extends ILayoutConfiguration<O>>
        extends ConfigurationWizard<O, C>
        implements ILayoutConfigurationWizard<O, C> {

    private final Universe universe;



    /**
     * Returns the {@link Universe} for which the {@link ILayoutConfiguration
     * Layout Configuration} is created.
     * 
     * @return the {@link Universe}.
     */
    public Universe getUniverse() {

        return universe;
    }


    /**
     * Creates a new instance of the {@code LayoutConfigurationWizard} class.
     * 
     * @param owner
     *            the owner of this {@code ConfigurationWizard}.
     * @param title
     *            the title of this {@code ConfigurationWizard}.
     * @param universe
     *            the {@link Universe} for which the
     *            {@link ILayoutConfiguration Layout Configuration} is created.
     */
    public LayoutConfigurationWizard(final Window owner, final String title,
            final Universe universe) {

        super(owner, title);

        Objects.nonNull(universe);

        this.universe = universe;
    }
}
