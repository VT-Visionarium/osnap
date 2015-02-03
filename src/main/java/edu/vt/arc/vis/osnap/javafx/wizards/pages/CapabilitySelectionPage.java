package edu.vt.arc.vis.osnap.javafx.wizards.pages;


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

import java.util.EnumSet;
import java.util.Set;

import javafx.scene.layout.GridPane;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ILayout;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;
import edu.vt.arc.vis.osnap.javafx.wizards.Wizard;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.ILayoutConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes.ILayoutConfigurationView;
import edu.vt.arc.vis.osnap.javafx.wizards.content.VisualPropertyCheckBoxVBox;


/**
 * The {@code CapabilitySelectionPage} provides a page for selecting the
 * capabilities of a {@link ILayout} that should be enabled.
 * 
 * @param <O>
 *            the type of the {@link ILayout}.
 * @param <C>
 *            the type of the {@link ILayoutConfiguration}.
 * @param <T>
 *            the type of the {@link ILayoutConfigurationView}.
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class CapabilitySelectionPage<O extends ILayout, C extends ILayoutConfiguration<O>, T extends GridPane & ILayoutConfigurationView<O, C>>
        extends LayoutConfigurationWizardPage<O, C, T> {

    private VisualPropertyCheckBoxVBox checkBox;


    /**
     * Creates a new instance of the {@link CapabilitySelectionPage} class.
     * 
     * @param configurationView
     *            the {@link ILayoutConfigurationView}.
     */
    public CapabilitySelectionPage(final T configurationView) {

        this(configurationView, EnumSet.allOf(VisualProperty.class), 1);

    }


    /**
     * Creates a new instance of the {@link CapabilitySelectionPage} class,
     * reducing the choices to the {@link VisualProperty VisualProperties}
     * provided.
     * 
     * @param configurationView
     *            the {@link ILayoutConfigurationView}.
     * @param partialEnumSet
     *            the reduced set of {@link VisualProperty} choices.
     */
    public CapabilitySelectionPage(final T configurationView,
            Set<VisualProperty> partialEnumSet) {

        this(configurationView, partialEnumSet, partialEnumSet.size());
    }


    /**
     * Creates a new instance of the {@link CapabilitySelectionPage} class,
     * reducing the choices to the {@link VisualProperty VisualProperties}
     * provided and limiting the number of selectable properties to the number
     * provided.
     * 
     * @param configurationView
     *            the {@link ILayoutConfigurationView}.
     * @param partialEnumSet
     *            the reduced set of {@link VisualProperty} choices.
     * @param limit
     *            the limit on the number of selectable properties.
     */
    public CapabilitySelectionPage(final T configurationView,
            final Set<VisualProperty> partialEnumSet, final int limit) {

        super("Select Visual Property", configurationView);

        Set<VisualProperty> capabilities = partialEnumSet;
        if (capabilities == null) {

            capabilities = EnumSet.allOf(VisualProperty.class);
        }

        this.checkBox = new VisualPropertyCheckBoxVBox(capabilities, limit);


        this.getContentGridPane().add(this.checkBox, 0, 0);
    }

    @Override
    public void onEnteringPage(Wizard wizard) {

        super.onEnteringPage(wizard);
    }

    @Override
    public void onExitingPage(Wizard wizard) {

        super.onExitingPage(wizard);

        this.getConfigurationView().getConfiguration()
                .enableVisualProperties(this.checkBox.getList());
    }
}
