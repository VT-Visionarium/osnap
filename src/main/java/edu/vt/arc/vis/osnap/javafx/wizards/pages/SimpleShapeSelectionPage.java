package edu.vt.arc.vis.osnap.javafx.wizards.pages;


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

import org.jutility.javafx.control.labeled.LabeledComboBox;

import edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents.SimpleShapeLayout;
import edu.vt.arc.vis.osnap.core.domain.visualization.Shape;
import edu.vt.arc.vis.osnap.javafx.wizards.Wizard;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.SimpleShapeLayoutConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes.SimpleShapeLayoutConfigurationView;
import edu.vt.arc.vis.osnap.javafx.wizards.pages.LayoutConfigurationWizardPage;


/**
 * The {@code SimpleShapeSelectionPage} provides a
 * {@link LayoutConfigurationWizardPage} for selecting the color of a
 * {@link SimpleShapeLayout simple ShapeLayout}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class SimpleShapeSelectionPage
        extends
        LayoutConfigurationWizardPage<SimpleShapeLayout, SimpleShapeLayoutConfiguration, SimpleShapeLayoutConfigurationView> {


    private LabeledComboBox<Shape> shapeBox;


    /**
     * Creates a new instance of the {@link SimpleShapeSelectionPage} class.
     * 
     * @param configurationView
     *            the {@link SimpleShapeLayoutConfigurationView} .
     */
    public SimpleShapeSelectionPage(
            final SimpleShapeLayoutConfigurationView configurationView) {


        super("Select a Shape", configurationView);


        this.shapeBox = new LabeledComboBox<>("Choose a Shape");

        this.shapeBox.getItems().addAll(Shape.values());

        this.getContentGridPane().add(this.shapeBox, 0, 0);

        this.setupValidation();
    }


    private void setupValidation() {

        // TODO: implement
    }

    @Override
    public void onEnteringPage(Wizard wizard) {

        super.onEnteringPage(wizard);

        if (this.getConfiguration() != null) {

            this.shapeBox.getSelectionModel().select(
                    this.getConfiguration().getShape());
        }
    }

    public void onExitingPage(Wizard wizard) {

        if (this.getConfiguration() != null) {

            this.getConfiguration().setShape(
                    this.shapeBox.getSelectionModel().getSelectedItem());
        }
    };
}
