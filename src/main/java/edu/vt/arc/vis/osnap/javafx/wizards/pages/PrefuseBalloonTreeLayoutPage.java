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

import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;
import org.jutility.javafx.control.labeled.LabeledTextField;
import org.jutility.javafx.control.validation.ValidationUtils;

import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.PrefuseBalloonTreeLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.Wizard;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.PrefuseBalloonTreeLayoutConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes.PrefuseBalloonTreeLayoutConfigurationView;



/**
 * The {@code PrefuseBalloonTreeLayoutPage} provides a
 * {@link LayoutConfigurationWizardPage} for setting the minimum radius of a
 * {@link PrefuseBalloonTreeLayout Prefuse BalloonTreeLayout}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class PrefuseBalloonTreeLayoutPage
        extends
        LayoutConfigurationWizardPage<PrefuseBalloonTreeLayout, PrefuseBalloonTreeLayoutConfiguration, PrefuseBalloonTreeLayoutConfigurationView> {

    private LabeledTextField minimumRadiusTF;

    /**
     * Creates a new instance of the {@code PrefuseBalloonTreeLayoutPage} class.
     *
     * @param configurationView
     *            the {@link PrefuseBalloonTreeLayoutConfigurationView} .
     */
    public PrefuseBalloonTreeLayoutPage(
            final PrefuseBalloonTreeLayoutConfigurationView configurationView) {

        super("Set minimum radius of the Balloon Tree Layout",
                configurationView);

        minimumRadiusTF = new LabeledTextField("Minimum Radius");

        this.getContentGridPane().add(minimumRadiusTF, 0, 0);

        this.setupValidation();
    }


    private void setupValidation() {


        this.minimumRadiusTF.registerValidator(Validator.combine(Validator
                .createEmptyValidator("Value cannot be empty!"),
                ValidationUtils.createNumberFormatValidator(Integer.class,
                        "Value must be a valid integer!")));
        this.minimumRadiusTF
                .setValidationDecorator(new GraphicValidationDecoration());
        this.minimumRadiusTF.setErrorDecorationEnabled(true);
        this.validationGroup().registerSubValidation(this.minimumRadiusTF,
                this.minimumRadiusTF.validationSupport());
    }

    @Override
    public void onEnteringPage(Wizard wizard) {

        super.onEnteringPage(wizard);

        if (this.getConfiguration() != null) {

            this.minimumRadiusTF.setText(""
                    + this.getConfiguration().getMinimumRadius());
        }
    }

    @Override
    public void onExitingPage(Wizard wizard) {

        super.onExitingPage(wizard);

        if (this.getConfiguration() != null) {

            this.getConfiguration().setMinimumRadius(
                    Integer.valueOf(this.minimumRadiusTF.getText()));
        }
    }
}
