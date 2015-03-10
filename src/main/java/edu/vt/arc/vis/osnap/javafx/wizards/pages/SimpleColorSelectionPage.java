package edu.vt.arc.vis.osnap.javafx.wizards.pages;


//@formatter:off
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


import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents.SimpleColorLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.Wizard;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.SimpleColorLayoutConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes.SimpleColorLayoutConfigurationView;


/**
 * The {@code SimpleColorSelectionPage} provides a
 * {@link LayoutConfigurationWizardPage} for selecting the color of a
 * {@link SimpleColorLayout simple ColorLayout}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class SimpleColorSelectionPage
        extends
        LayoutConfigurationWizardPage<SimpleColorLayout, SimpleColorLayoutConfiguration, SimpleColorLayoutConfigurationView> {

    private final ColorPicker colorPicker;

    /**
     * Creates a new instance of the {@code SimpleColorSelectionPage} class.
     *
     * @param configurationView
     *            the {@link SimpleColorLayoutConfigurationView} .
     */
    public SimpleColorSelectionPage(
            final SimpleColorLayoutConfigurationView configurationView) {

        super("Select a Color", configurationView);

        this.colorPicker = new ColorPicker(Color.WHITE);
        this.colorPicker.getStyleClass().add("split_button");
        this.getContentGridPane().add(this.colorPicker, 0, 0);
    }


    @Override
    public void onEnteringPage(final Wizard wizard) {

        super.onEnteringPage(wizard);

        this.colorPicker.setValue(this.getConfigurationView()
                .getConfiguration().getColor());
    }

    @Override
    public void onExitingPage(final Wizard wizard) {

        super.onExitingPage(wizard);

        this.getConfigurationView().getConfiguration()
                .setColor(this.colorPicker.getValue());
    }
}
