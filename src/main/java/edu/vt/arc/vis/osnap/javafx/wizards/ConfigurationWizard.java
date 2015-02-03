package edu.vt.arc.vis.osnap.javafx.wizards;


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
//@formatter:on


import javafx.stage.Window;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.IConfiguration;


/**
 * The abstract {@code ConfigurationWizard} class provides a base for wizards
 * that create a {@link IConfiguration}.
 * 
 * @param <O>
 *            the type of the configurable object.
 * @param <C>
 *            the type of the {@link IConfiguration}.
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public abstract class ConfigurationWizard<O, C extends IConfiguration<O>>
        extends Wizard
        implements IConfigurationWizard<O, C> {


    /**
     * Creates a new instance of the {@code ConfigurationWizard} class.
     * 
     * @param owner
     *            the owner of this {@code ConfigurationWizard}.
     * @param title
     *            the title of this {@code ConfigurationWizard}.
     */
    public ConfigurationWizard(final Window owner, final String title) {

        super(owner, title);
    }
}
