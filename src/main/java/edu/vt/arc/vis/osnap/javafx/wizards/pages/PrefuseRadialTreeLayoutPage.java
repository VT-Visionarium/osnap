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


import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;
import org.jutility.javafx.control.labeled.LabeledTextField;
import org.jutility.javafx.control.validation.ValidationUtils;

import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.PrefuseRadialTreeLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.Wizard;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.PrefuseRadialTreeLayoutConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes.PrefuseRadialTreeLayoutConfigurationView;



/**
 * The {@code PrefuseRadialTreeLayoutPage} provides a
 * {@link LayoutConfigurationWizardPage} for setting the radius increment of a
 * {@link PrefuseRadialTreeLayout Prefuse RadialTreeLayout}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class PrefuseRadialTreeLayoutPage
        extends
        LayoutConfigurationWizardPage<PrefuseRadialTreeLayout, PrefuseRadialTreeLayoutConfiguration, PrefuseRadialTreeLayoutConfigurationView> {

    private final LabeledTextField radiusIncrementTF;

    /**
     * Creates a new instance of the {@code PrefuseRadialTreeLayoutPage} class.
     *
     * @param configurationView
     *            the {@link PrefuseRadialTreeLayoutConfigurationView} .
     */
    public PrefuseRadialTreeLayoutPage(
            final PrefuseRadialTreeLayoutConfigurationView configurationView) {

        super("Set radius increment of the Radial Tree Layout",
                configurationView);

        this.radiusIncrementTF = new LabeledTextField("Radius Increment");

        this.getContentGridPane().add(this.radiusIncrementTF, 0, 0);

        this.setupValidation();
    }


    private void setupValidation() {


        this.radiusIncrementTF.registerValidator(Validator.combine(Validator
                .createEmptyValidator("Value cannot be empty!"),
                ValidationUtils.createNumberFormatValidator(Integer.class,
                        "Value must be a valid integer!")));
        this.radiusIncrementTF
                .setValidationDecorator(new GraphicValidationDecoration());
        this.radiusIncrementTF.setErrorDecorationEnabled(true);
        this.validationGroup().registerSubValidation(this.radiusIncrementTF,
                this.radiusIncrementTF.validationSupport());
    }

    @Override
    public void onEnteringPage(final Wizard wizard) {

        super.onEnteringPage(wizard);

        if (this.getConfiguration() != null) {

            this.radiusIncrementTF.setText(""
                    + this.getConfiguration().getRadiusIncrement());
        }
    }

    @Override
    public void onExitingPage(final Wizard wizard) {

        super.onExitingPage(wizard);

        if (this.getConfiguration() != null) {

            this.getConfiguration().setRadiusIncrement(
                    Integer.valueOf(this.radiusIncrementTF.getText()));
        }
    }
}
