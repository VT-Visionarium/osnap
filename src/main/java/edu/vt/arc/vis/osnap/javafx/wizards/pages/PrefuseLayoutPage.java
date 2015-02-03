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



import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;
import org.jutility.javafx.control.labeled.LabeledTextField;
import org.jutility.javafx.control.validation.ValidationUtils;

import edu.vt.arc.vis.osnap.core.domain.layout.prefuseComponents.IPrefuseLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.Wizard;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.IPrefuseLayoutConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes.ILayoutConfigurationView;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes.IPrefuseLayoutConfigurationView;


/**
 * The {@code PrefuseLayoutPage} provides a
 * {@link LayoutConfigurationWizardPage} for Prefuse-specific
 * {@link IPrefuseLayoutConfiguration Configuration} items.
 *
 * @param <O>
 *            the type of the {@link IPrefuseLayout}.
 * @param <C>
 *            the type of the {@link IPrefuseLayoutConfiguration}.
 * @param <T>
 *            the type of the {@link IPrefuseLayoutConfigurationView}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class PrefuseLayoutPage<O extends IPrefuseLayout, C extends IPrefuseLayoutConfiguration<O>, T extends GridPane & IPrefuseLayoutConfigurationView<O, C>>
        extends LayoutConfigurationWizardPage<O, C, T> {

    private final LabeledTextField durationTF;

    private final Label            anchorLabel;
    private final LabeledTextField xAnchorTF;
    private final LabeledTextField yAnchorTF;

    private final Label            boundsLabel;
    private final LabeledTextField layoutBoundsXTF;
    private final LabeledTextField layoutBoundsYTF;
    private final LabeledTextField layoutBoundsWidthTF;
    private final LabeledTextField layoutBoundsHeightTF;


    private final Label            marginLabel;
    private final LabeledTextField topMarginTF;
    private final LabeledTextField bottomMarginTF;
    private final LabeledTextField leftMarginTF;
    private final LabeledTextField rightMarginTF;


    /**
     * Creates a new instance of the {@code PrefuseLayoutPage} class.
     *
     * @param configurationView
     *            the {@link ILayoutConfigurationView}.
     */
    public PrefuseLayoutPage(final T configurationView) {

        super("Set Prefuse-specific configuration items", configurationView);


        this.durationTF = new LabeledTextField("Layout Duration");
        this.durationTF.getLabel().setStyle("-fx-font-weight: bold");

        this.anchorLabel = new Label("Layout Anchor");
        anchorLabel.setStyle("-fx-font-weight: bold");
        this.xAnchorTF = new LabeledTextField("X");
        this.yAnchorTF = new LabeledTextField("Y");


        this.boundsLabel = new Label("Layout Bounds");
        boundsLabel.setStyle("-fx-font-weight: bold");
        this.layoutBoundsXTF = new LabeledTextField("X (top left corner)");
        this.layoutBoundsYTF = new LabeledTextField("Y (top left corner)");
        this.layoutBoundsWidthTF = new LabeledTextField("Width");
        this.layoutBoundsHeightTF = new LabeledTextField("Height");

        this.marginLabel = new Label("Layout Margins");
        marginLabel.setStyle("-fx-font-weight: bold");
        this.topMarginTF = new LabeledTextField("Top");
        this.bottomMarginTF = new LabeledTextField("Bottom");
        this.leftMarginTF = new LabeledTextField("Left");
        this.rightMarginTF = new LabeledTextField("Right");


        this.getContentGridPane().add(this.durationTF, 0, 0);

        this.getContentGridPane().add(this.anchorLabel, 0, 2);
        this.getContentGridPane().add(this.xAnchorTF, 0, 3);
        this.getContentGridPane().add(this.yAnchorTF, 1, 3);

        this.getContentGridPane().add(this.boundsLabel, 0, 5);
        this.getContentGridPane().add(this.layoutBoundsXTF, 0, 6);
        this.getContentGridPane().add(this.layoutBoundsYTF, 1, 6);
        this.getContentGridPane().add(this.layoutBoundsWidthTF, 0, 7);
        this.getContentGridPane().add(this.layoutBoundsHeightTF, 1, 7);

        this.getContentGridPane().add(this.marginLabel, 0, 9);
        this.getContentGridPane().add(this.topMarginTF, 0, 10);
        this.getContentGridPane().add(this.bottomMarginTF, 1, 10);
        this.getContentGridPane().add(this.leftMarginTF, 0, 11);
        this.getContentGridPane().add(this.rightMarginTF, 1, 11);


        this.setupValidation();
    }


    private void setupValidation() {

        this.durationTF.registerValidator(Validator.combine(Validator
                .createEmptyValidator("Value cannot be empty!"),
                ValidationUtils.createNumberFormatValidator(Long.class,
                        "Value must be a valid long integer!")));
        this.durationTF
                .setValidationDecorator(new GraphicValidationDecoration());
        this.durationTF.setErrorDecorationEnabled(true);
        this.validationGroup().registerSubValidation(this.durationTF,
                this.durationTF.validationSupport());


        this.xAnchorTF.registerValidator(Validator.combine(Validator
                .createEmptyValidator("Value cannot be empty!"),
                ValidationUtils.createNumberFormatValidator(Double.class,
                        "Value must be a valid double!")));
        this.xAnchorTF
                .setValidationDecorator(new GraphicValidationDecoration());
        this.xAnchorTF.setErrorDecorationEnabled(true);
        this.validationGroup().registerSubValidation(this.xAnchorTF,
                this.xAnchorTF.validationSupport());


        this.yAnchorTF.registerValidator(Validator.combine(Validator
                .createEmptyValidator("Value cannot be empty!"),
                ValidationUtils.createNumberFormatValidator(Double.class,
                        "Value must be a valid double!")));
        this.yAnchorTF
                .setValidationDecorator(new GraphicValidationDecoration());
        this.yAnchorTF.setErrorDecorationEnabled(true);
        this.validationGroup().registerSubValidation(this.yAnchorTF,
                this.yAnchorTF.validationSupport());


        this.layoutBoundsXTF.registerValidator(Validator.combine(Validator
                .createEmptyValidator("Value cannot be empty!"),
                ValidationUtils.createNumberFormatValidator(Double.class,
                        "Value must be a valid double!")));
        this.layoutBoundsXTF
                .setValidationDecorator(new GraphicValidationDecoration());
        this.layoutBoundsXTF.setErrorDecorationEnabled(true);
        this.validationGroup().registerSubValidation(this.layoutBoundsXTF,
                this.layoutBoundsXTF.validationSupport());


        this.layoutBoundsYTF.registerValidator(Validator.combine(Validator
                .createEmptyValidator("Value cannot be empty!"),
                ValidationUtils.createNumberFormatValidator(Double.class,
                        "Value must be a valid double!")));
        this.layoutBoundsYTF
                .setValidationDecorator(new GraphicValidationDecoration());
        this.layoutBoundsYTF.setErrorDecorationEnabled(true);
        this.validationGroup().registerSubValidation(this.layoutBoundsYTF,
                this.layoutBoundsYTF.validationSupport());


        this.layoutBoundsWidthTF.registerValidator(Validator.combine(Validator
                .createEmptyValidator("Value cannot be empty!"),
                ValidationUtils.createNumberFormatValidator(Double.class,
                        "Value must be a valid double!")));
        this.layoutBoundsWidthTF
                .setValidationDecorator(new GraphicValidationDecoration());
        this.layoutBoundsWidthTF.setErrorDecorationEnabled(true);
        this.validationGroup().registerSubValidation(this.layoutBoundsWidthTF,
                this.layoutBoundsWidthTF.validationSupport());


        this.layoutBoundsHeightTF.registerValidator(Validator.combine(Validator
                .createEmptyValidator("Value cannot be empty!"),
                ValidationUtils.createNumberFormatValidator(Double.class,
                        "Value must be a valid double!")));
        this.layoutBoundsHeightTF
                .setValidationDecorator(new GraphicValidationDecoration());
        this.layoutBoundsHeightTF.setErrorDecorationEnabled(true);
        this.validationGroup().registerSubValidation(this.layoutBoundsHeightTF,
                this.layoutBoundsHeightTF.validationSupport());


        this.topMarginTF.registerValidator(Validator.combine(Validator
                .createEmptyValidator("Value cannot be empty!"),
                ValidationUtils.createNumberFormatValidator(Integer.class,
                        "Value must be a valid integer!")));
        this.topMarginTF
                .setValidationDecorator(new GraphicValidationDecoration());
        this.topMarginTF.setErrorDecorationEnabled(true);
        this.validationGroup().registerSubValidation(this.topMarginTF,
                this.topMarginTF.validationSupport());


        this.bottomMarginTF.registerValidator(Validator.combine(Validator
                .createEmptyValidator("Value cannot be empty!"),
                ValidationUtils.createNumberFormatValidator(Integer.class,
                        "Value must be a valid integer!")));
        this.bottomMarginTF
                .setValidationDecorator(new GraphicValidationDecoration());
        this.bottomMarginTF.setErrorDecorationEnabled(true);
        this.validationGroup().registerSubValidation(this.bottomMarginTF,
                this.bottomMarginTF.validationSupport());


        this.leftMarginTF.registerValidator(Validator.combine(Validator
                .createEmptyValidator("Value cannot be empty!"),
                ValidationUtils.createNumberFormatValidator(Integer.class,
                        "Value must be a valid integer!")));
        this.leftMarginTF
                .setValidationDecorator(new GraphicValidationDecoration());
        this.leftMarginTF.setErrorDecorationEnabled(true);
        this.validationGroup().registerSubValidation(this.leftMarginTF,
                this.leftMarginTF.validationSupport());


        this.rightMarginTF.registerValidator(Validator.combine(Validator
                .createEmptyValidator("Value cannot be empty!"),
                ValidationUtils.createNumberFormatValidator(Integer.class,
                        "Value must be a valid integer!")));
        this.rightMarginTF
                .setValidationDecorator(new GraphicValidationDecoration());
        this.rightMarginTF.setErrorDecorationEnabled(true);
        this.validationGroup().registerSubValidation(this.rightMarginTF,
                this.rightMarginTF.validationSupport());

    }

    @Override
    public void onEnteringPage(final Wizard wizard) {

        super.onEnteringPage(wizard);

        this.durationTF.clear();

        this.xAnchorTF.clear();
        this.yAnchorTF.clear();

        this.layoutBoundsXTF.clear();
        this.layoutBoundsYTF.clear();
        this.layoutBoundsWidthTF.clear();
        this.layoutBoundsHeightTF.clear();

        if (this.getConfiguration() != null) {

            this.durationTF.setText("" + this.getConfiguration().getDuration());


            this.xAnchorTF.setText(this.getConfiguration().getLayoutAnchor()
                    .getX().toString());
            this.yAnchorTF.setText(this.getConfiguration().getLayoutAnchor()
                    .getY().toString());

            this.layoutBoundsXTF.setText(this.getConfiguration()
                    .getLayoutBounds().getTopLeftCorner().getX().toString());
            this.layoutBoundsYTF.setText(this.getConfiguration()
                    .getLayoutBounds().getTopLeftCorner().getY().toString());

            final double width = this.getConfiguration().getLayoutBounds()
                    .getTopRightCorner().getX()
                    - this.getConfiguration().getLayoutBounds()
                            .getTopLeftCorner().getX();
            final double height = this.getConfiguration().getLayoutBounds()
                    .getBottomLeftCorner().getY()
                    - this.getConfiguration().getLayoutBounds()
                            .getTopLeftCorner().getY();

            this.layoutBoundsWidthTF.setText("" + width);
            this.layoutBoundsHeightTF.setText("" + height);


            this.topMarginTF.setText(""
                    + this.getConfiguration().getTopMargin());
            this.bottomMarginTF.setText(""
                    + this.getConfiguration().getBottomMargin());
            this.leftMarginTF.setText(""
                    + this.getConfiguration().getLeftMargin());
            this.rightMarginTF.setText(""
                    + this.getConfiguration().getRightMargin());
        }
    }

    @Override
    public void onExitingPage(final Wizard wizard) {

        super.onExitingPage(wizard);

        if (this.getConfiguration() != null) {


            this.getConfiguration().setDuration(
                    Long.valueOf(this.durationTF.getText()));

            this.getConfiguration().setLayoutAnchor(
                    Double.valueOf(this.xAnchorTF.getText()),
                    Double.valueOf(this.yAnchorTF.getText()));

            this.getConfiguration().setLayoutBounds(
                    Double.valueOf(this.layoutBoundsXTF.getText()),
                    Double.valueOf(this.layoutBoundsYTF.getText()),
                    Double.valueOf(this.layoutBoundsWidthTF.getText()),
                    Double.valueOf(this.layoutBoundsHeightTF.getText()));

            this.getConfiguration().setMargins(
                    Integer.valueOf(this.topMarginTF.getText()),
                    Integer.valueOf(this.bottomMarginTF.getText()),
                    Integer.valueOf(this.leftMarginTF.getText()),
                    Integer.valueOf(this.rightMarginTF.getText()));
        }
    }
}
