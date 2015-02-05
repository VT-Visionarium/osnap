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



import javafx.geometry.Pos;
import javafx.scene.control.Label;

import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;
import org.jutility.javafx.control.labeled.LabeledComboBox;
import org.jutility.javafx.control.labeled.LabeledTextField;
import org.jutility.javafx.control.validation.ValidationUtils;
import org.jutility.javafx.control.wrapper.ComboBoxWrapper;
import org.jutility.javafx.control.wrapper.TextFieldWrapper;

import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.PrefuseNodeLinkTreeLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.PrefuseNodeLinkTreeLayout.Orientation;
import edu.vt.arc.vis.osnap.javafx.wizards.Wizard;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.PrefuseNodeLinkTreeLayoutConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes.PrefuseNodeLinkTreeLayoutConfigurationView;



/**
 * The {@code PrefuseNodeLinkTreeLayoutPage} provides a
 * {@link LayoutConfigurationWizardPage} for setting configuration itmes of a
 * {@link PrefuseNodeLinkTreeLayout Prefuse RadialTreeLayout}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class PrefuseNodeLinkTreeLayoutPage
        extends
        LayoutConfigurationWizardPage<PrefuseNodeLinkTreeLayout, PrefuseNodeLinkTreeLayoutConfiguration, PrefuseNodeLinkTreeLayoutConfigurationView> {


    private final TextFieldWrapper             depthSpacingTF;
    private final TextFieldWrapper             siblingSpacingTF;
    private final TextFieldWrapper             subTreeSpacingTF;
    private final ComboBoxWrapper<Orientation> orientationBox;



    /**
     * Creates a new instance of the {@code PrefuseNodeLinkTreeLayoutPage}
     * class.
     *
     * @param configurationView
     *            the {@link PrefuseNodeLinkTreeLayoutConfigurationView} .
     */
    public PrefuseNodeLinkTreeLayoutPage(
            final PrefuseNodeLinkTreeLayoutConfigurationView configurationView) {

        super("Set Node Link Tree configuration items", configurationView);


        this.orientationBox = new ComboBoxWrapper<>();
        this.orientationBox.getItems().addAll(Orientation.values());
        this.orientationBox.getSelectionModel().select(2);
        Label orientationLabel = new Label("Orientation");
        orientationLabel.setStyle("-fx-font-weight: bold");
        orientationLabel.setLabelFor(this.orientationBox);


        this.depthSpacingTF = new TextFieldWrapper();
        this.depthSpacingTF.setHgap(10);
        Label depthSpacingLabel = new Label("Depth Spacing");
        depthSpacingLabel.setStyle("-fx-font-weight: bold");

        this.siblingSpacingTF = new TextFieldWrapper();
        this.siblingSpacingTF.setHgap(10);
        Label siblingSpacingLabel = new Label("Space Between Siblings");
        siblingSpacingLabel.setStyle("-fx-font-weight: bold");

        this.subTreeSpacingTF = new TextFieldWrapper();
        Label subTreeSpacingLabel = new Label(
                "Space Between Neighboring Sub-Trees");
        subTreeSpacingLabel.setStyle("-fx-font-weight: bold");


        this.getContentGridPane().add(orientationLabel, 0, 0);
        this.getContentGridPane().add(this.orientationBox, 1, 0);
        this.getContentGridPane().add(depthSpacingLabel, 0, 1);
        this.getContentGridPane().add(this.depthSpacingTF, 1, 1);
        this.getContentGridPane().add(siblingSpacingLabel, 0, 2);
        this.getContentGridPane().add(this.siblingSpacingTF, 1, 2);
        this.getContentGridPane().add(subTreeSpacingLabel, 0, 3);
        this.getContentGridPane().add(this.subTreeSpacingTF, 1, 3);

        this.setupValidation();
    }

    private void setupValidation() {

        this.depthSpacingTF.registerValidator(Validator.combine(Validator
                .createEmptyValidator("Value cannot be empty!"),
                ValidationUtils.createNumberFormatValidator(Double.class,
                        "Value must be a valid double!")));
        this.depthSpacingTF
                .setValidationDecorator(new GraphicValidationDecoration());
        this.depthSpacingTF.setErrorDecorationEnabled(true);
        this.validationGroup().registerSubValidation(this.depthSpacingTF,
                this.depthSpacingTF.validationSupport());

        this.siblingSpacingTF.registerValidator(Validator.combine(Validator
                .createEmptyValidator("Value cannot be empty!"),
                ValidationUtils.createNumberFormatValidator(Double.class,
                        "Value must be a valid double!")));
        this.siblingSpacingTF
                .setValidationDecorator(new GraphicValidationDecoration());
        this.siblingSpacingTF.setErrorDecorationEnabled(true);
        this.validationGroup().registerSubValidation(this.siblingSpacingTF,
                this.siblingSpacingTF.validationSupport());

        this.subTreeSpacingTF.registerValidator(Validator.combine(Validator
                .createEmptyValidator("Value cannot be empty!"),
                ValidationUtils.createNumberFormatValidator(Double.class,
                        "Value must be a valid double!")));
        this.subTreeSpacingTF
                .setValidationDecorator(new GraphicValidationDecoration());
        this.subTreeSpacingTF.setErrorDecorationEnabled(true);
        this.validationGroup().registerSubValidation(this.subTreeSpacingTF,
                this.subTreeSpacingTF.validationSupport());


        this.orientationBox.registerValidator(Validator
                .createPredicateValidator(value -> {

                    return value != null;
                }, "You need to select a value!"));
        this.orientationBox
                .setValidationDecorator(new GraphicValidationDecoration());
        this.orientationBox.setErrorDecorationEnabled(true);
        this.validationGroup().registerSubValidation(this.orientationBox,
                this.orientationBox.validationSupport());
    }

    @Override
    public void onEnteringPage(final Wizard wizard) {

        super.onEnteringPage(wizard);

        if (this.getConfiguration() != null) {

            this.depthSpacingTF.setText(""
                    + this.getConfiguration().getDepthSpacing());
            this.siblingSpacingTF.setText(""
                    + this.getConfiguration().getSpaceBetweenSiblings());
            this.subTreeSpacingTF.setText(""
                    + this.getConfiguration()
                            .getSpaceBetweenNeighboringSubtrees());

            this.orientationBox.getSelectionModel().select(
                    this.getConfiguration().getOrientation());
        }
    }

    @Override
    public void onExitingPage(final Wizard wizard) {

        super.onExitingPage(wizard);

        if (this.getConfiguration() != null) {

            this.getConfiguration().setDepthSpacing(
                    Double.valueOf(this.depthSpacingTF.getText()));
            this.getConfiguration().setSpaceBetweenSiblings(
                    Double.valueOf(this.siblingSpacingTF.getText()));
            this.getConfiguration().setSpaceBetweenNeighboringSubtrees(
                    Double.valueOf(this.subTreeSpacingTF.getText()));

            this.getConfiguration().setOrientation(
                    this.orientationBox.getValue());
        }
    }
}
