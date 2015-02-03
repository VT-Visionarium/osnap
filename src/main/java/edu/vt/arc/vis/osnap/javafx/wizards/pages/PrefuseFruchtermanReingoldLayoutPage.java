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

import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.PrefuseFruchtermanReingoldLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.Wizard;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.PrefuseFruchtermanReingoldLayoutConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes.PrefuseFruchtermanReingoldLayoutConfigurationView;


/**
 * The {@code PrefuseFruchtermanReingoldLayoutPage} provides a
 * {@link LayoutConfigurationWizardPage} for setting the parameters of a
 * {@link PrefuseFruchtermanReingoldLayout Prefuse Fruchterman-Reingold Layout}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class PrefuseFruchtermanReingoldLayoutPage
        extends
        LayoutConfigurationWizardPage<PrefuseFruchtermanReingoldLayout, PrefuseFruchtermanReingoldLayoutConfiguration, PrefuseFruchtermanReingoldLayoutConfigurationView> {



    private final LabeledTextField maximumIterationsTF;

    /**
     * Creates a new instance of the
     * {@code PrefuseFruchtermanReingoldLayoutPage} class.
     *
     * @param configurationView
     *            the {@link PrefuseFruchtermanReingoldLayoutConfigurationView}
     *            .
     */
    public PrefuseFruchtermanReingoldLayoutPage(
            final PrefuseFruchtermanReingoldLayoutConfigurationView configurationView) {

        super("Set Prefuse Fruchterman-Reingold layout parameters",
                configurationView);

        this.maximumIterationsTF = new LabeledTextField("Maximum Iterations");
        this.maximumIterationsTF.getLabel().setStyle("-fx-font-weight: bold");

        this.getContentGridPane().add(this.maximumIterationsTF, 0, 1);

        this.setupValidation();
    }

    private void setupValidation() {

        this.maximumIterationsTF.registerValidator(Validator.combine(Validator
                .createEmptyValidator("Value cannot be empty!"),
                ValidationUtils.createNumberFormatValidator(Integer.class,
                        "Value must be a valid integer!")));
        this.maximumIterationsTF
                .setValidationDecorator(new GraphicValidationDecoration());
        this.maximumIterationsTF.setErrorDecorationEnabled(true);
        this.validationGroup().registerSubValidation(this.maximumIterationsTF,
                this.maximumIterationsTF.validationSupport());
    }

    @Override
    public void onEnteringPage(final Wizard wizard) {

        super.onEnteringPage(wizard);

        this.maximumIterationsTF.setText(""
                + this.getConfiguration().getMaximumIterations());
    }

    @Override
    public void onExitingPage(final Wizard wizard) {

        super.onExitingPage(wizard);

        this.getConfiguration().setMaxIterations(
                Integer.valueOf(this.maximumIterationsTF.getText()));
    }
}
