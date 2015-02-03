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

import org.controlsfx.validation.decoration.GraphicValidationDecoration;
import org.jutility.javafx.control.labeled.LabeledTextField;
import org.jutility.javafx.control.validation.ValidationUtils;

import edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents.SimpleScaleLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.Wizard;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.SimpleScaleLayoutConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes.SimpleScaleLayoutConfigurationView;


/**
 * The {@code SimpleScaleSelectionPage} provides a
 * {@link LayoutConfigurationWizardPage} for selecting the scale of a
 * {@link SimpleScaleLayout simple ScaleLayout}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class SimpleScaleSelectionPage
        extends
        LayoutConfigurationWizardPage<SimpleScaleLayout, SimpleScaleLayoutConfiguration, SimpleScaleLayoutConfigurationView> {


    private LabeledTextField scaleTF;

    /**
     * Creates a new instance of the {@link SimpleShapeSelectionPage} class.
     * 
     * @param configurationView
     *            the {@link SimpleScaleLayoutConfigurationView} .
     */
    public SimpleScaleSelectionPage(
            final SimpleScaleLayoutConfigurationView configurationView) {

        super("Set Scale", configurationView);

        scaleTF = new LabeledTextField("Enter Scale Value");

        this.getContentGridPane().add(scaleTF, 0, 0);

        this.setupValidation();
    }

    private void setupValidation() {

        this.scaleTF.registerValidator(ValidationUtils
                .createNumberFormatValidator(Float.class,
                        "Scale must be a Float!"));
        this.scaleTF.setValidationDecorator(new GraphicValidationDecoration());
        this.scaleTF.setErrorDecorationEnabled(true);


        this.validationGroup().registerSubValidation(this.scaleTF,
                this.scaleTF.validationSupport());
    }

    @Override
    public void onEnteringPage(Wizard wizard) {

        super.onEnteringPage(wizard);

        if (this.getConfigurationView().getConfiguration() != null) {

            this.scaleTF.setText(this.getConfigurationView().getConfiguration()
                    .getScale().getScaleFactorX().toString());
        }
    }

    @Override
    public void onExitingPage(Wizard wizard) {

        super.onExitingPage(wizard);

        if (this.getConfigurationView().getConfiguration() != null) {

            Float scale = Float.valueOf(this.scaleTF.getText());

            this.getConfigurationView().getConfiguration()
                    .setUniformScale(scale);
        }
    }

}
