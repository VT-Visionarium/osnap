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
import org.jutility.javafx.control.labeled.LabeledComboBox;
import org.jutility.javafx.control.labeled.LabeledTextField;
import org.jutility.javafx.control.validation.ValidationUtils;

import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.PrefuseForceDirectedLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.Wizard;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.PrefuseForceDirectedLayoutConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes.PrefuseForceDirectedLayoutConfigurationView;


/**
 * The {@code PrefuseForceDirectedLayoutPage} provides a
 * {@link LayoutConfigurationWizardPage} for setting the parameters of a
 * {@link PrefuseForceDirectedLayout Prefuse Force-Directed Layout}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class PrefuseForceDirectedLayoutPage
        extends
        LayoutConfigurationWizardPage<PrefuseForceDirectedLayout, PrefuseForceDirectedLayoutConfiguration, PrefuseForceDirectedLayoutConfigurationView> {



    private final LabeledComboBox<Boolean> enforceBoundsCB;
    private final LabeledTextField         iterationsTF;

    /**
     * Creates a new instance of the {@code PrefuseForceDirectedLayoutPage}
     * class.
     *
     * @param configurationView
     *            the {@link PrefuseForceDirectedLayoutConfigurationView} .
     */
    public PrefuseForceDirectedLayoutPage(
            final PrefuseForceDirectedLayoutConfigurationView configurationView) {

        super("Set Prefuse force-directed layout parameters", configurationView);

        this.enforceBoundsCB = new LabeledComboBox<>("Enforce Bounds");
        this.enforceBoundsCB.getItems().addAll(Boolean.TRUE, Boolean.FALSE);
        this.enforceBoundsCB.getLabel().setStyle("-fx-font-weight: bold");

        this.iterationsTF = new LabeledTextField("Number of Iterations");
        this.iterationsTF.getLabel().setStyle("-fx-font-weight: bold");

        this.getContentGridPane().add(this.enforceBoundsCB, 0, 1);
        this.getContentGridPane().add(this.iterationsTF, 0, 2);

        this.setupValidation();
    }

    private void setupValidation() {

        this.enforceBoundsCB.registerValidator(Validator
                .createPredicateValidator(value -> {

                    return value != null;
                }, "You need to select a choice!"));
        this.enforceBoundsCB
                .setValidationDecorator(new GraphicValidationDecoration());
        this.enforceBoundsCB.setErrorDecorationEnabled(true);
        this.validationGroup().registerSubValidation(this.enforceBoundsCB,
                this.enforceBoundsCB.validationSupport());

        this.iterationsTF.registerValidator(Validator.combine(Validator
                .createEmptyValidator("Value cannot be empty!"),
                ValidationUtils.createNumberFormatValidator(Integer.class,
                        "Value must be a valid integer!")));
        this.iterationsTF
                .setValidationDecorator(new GraphicValidationDecoration());
        this.iterationsTF.setErrorDecorationEnabled(true);
        this.validationGroup().registerSubValidation(this.iterationsTF,
                this.iterationsTF.validationSupport());
    }

    @Override
    public void onEnteringPage(final Wizard wizard) {

        super.onEnteringPage(wizard);

        this.enforceBoundsCB.getSelectionModel().select(
                this.getConfiguration().enforcesBounds());

        this.iterationsTF.setText(""
                + this.getConfiguration().getIterations());
    }

    @Override
    public void onExitingPage(final Wizard wizard) {

        super.onExitingPage(wizard);

        this.getConfiguration().setEnforceBounds(
                this.enforceBoundsCB.getValue());
        this.getConfiguration().setIterations(
                Integer.valueOf(this.iterationsTF.getText()));
    }
}
