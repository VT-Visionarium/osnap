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
import org.jutility.javafx.control.wrapper.TextAreaWrapper;
import org.jutility.javafx.control.wrapper.TextFieldWrapper;

import edu.vt.arc.vis.osnap.core.domain.layout.common.ILayout;
import edu.vt.arc.vis.osnap.javafx.wizards.Wizard;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.ILayoutConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes.ILayoutConfigurationView;


/**
 * The {@code NamingPage} provides a {@link LayoutConfigurationWizardPage} for
 * naming and describing {@link ILayout Layout Components}.
 *
 * @param <O>
 *            the type of the {@link ILayout}.
 * @param <C>
 *            the type of the {@link ILayoutConfiguration}.
 * @param <T>
 *            the type of the {@link ILayoutConfigurationView}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class NamingPage<O extends ILayout, C extends ILayoutConfiguration<O>, T extends GridPane & ILayoutConfigurationView<O, C>>
        extends LayoutConfigurationWizardPage<O, C, T> {

    private final TextFieldWrapper nameTF;
    private final TextAreaWrapper  descriptionTF;



    /**
     * Creates a new instance of the {@code NamingPage} class.
     *
     * @param configurationView
     *            the {@link ILayoutConfigurationView}.
     */
    public NamingPage(final T configurationView) {

        super("Name the Layout", configurationView);


        this.nameTF = new TextFieldWrapper();
        Label nameLabel = new Label("Provide a name for this layout");
        nameLabel.setLabelFor(this.nameTF);
        nameLabel.setStyle("-fx-font-weight: bold");
        
        this.descriptionTF = new TextAreaWrapper();
        this.descriptionTF.setPrefRowCount(10);
        this.descriptionTF.setPrefColumnCount(20);
        this.descriptionTF.setWrapText(true);
        Label descriptionLabel = new Label(
                "Provide a Description for this layout");
        descriptionLabel.setLabelFor(this.descriptionTF);
        descriptionLabel.setStyle("-fx-font-weight: bold");

//        GridPane.setValignment(this.descriptionTF.getLabel(), VPos.TOP);

        this.getContentGridPane().add(nameLabel, 0, 0);
        this.getContentGridPane().add(this.nameTF, 1, 0);
        this.getContentGridPane().add(descriptionLabel, 0, 1);
        this.getContentGridPane().add(this.descriptionTF, 1, 1);

        this.setupValidation();
    }

    private void setupValidation() {

        this.nameTF.registerValidator(Validator
                .createEmptyValidator("Name of Layout cannot be empty!"));
        this.nameTF.setValidationDecorator(new GraphicValidationDecoration());
        this.nameTF.setErrorDecorationEnabled(true);

        this.descriptionTF
                .registerValidator(Validator
                        .createEmptyValidator("Description of Layout cannot be empty!"));
        this.descriptionTF
                .setValidationDecorator(new GraphicValidationDecoration());
        this.descriptionTF.setErrorDecorationEnabled(true);

        this.validationGroup().registerSubValidation(this.nameTF,
                this.nameTF.validationSupport());
        this.validationGroup().registerSubValidation(this.descriptionTF,
                this.descriptionTF.validationSupport());
    }

    @Override
    public void onEnteringPage(final Wizard wizard) {

        super.onEnteringPage(wizard);

        this.nameTF.setText(this.getConfigurationView().getConfiguration()
                .getName());
        this.descriptionTF.setText(this.getConfigurationView()
                .getConfiguration().getDescription());
    }

    @Override
    public void onExitingPage(final Wizard wizard) {

        super.onExitingPage(wizard);

        this.getConfigurationView().getConfiguration()
                .setName(this.nameTF.getText());
        this.getConfigurationView().getConfiguration()
                .setDescription(this.descriptionTF.getText());
    }
}
