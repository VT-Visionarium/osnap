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

import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;
import org.jutility.javafx.control.validation.ValidationUtils;
import org.jutility.javafx.control.wrapper.TextFieldWrapper;

import edu.vt.arc.vis.osnap.core.domain.layout.complexComponents.TieredOrbitalLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.Wizard;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.TieredOrbitalLayoutConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes.TieredOrbitalLayoutConfigurationView;

/**
 * The {@code TieredOrbitalLayoutPage} provides a
 * {@link LayoutConfigurationWizardPage} for specifying the settings of a
 * {@link TieredOrbitalLayout}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class TieredOrbitalLayoutPage
		extends
		LayoutConfigurationWizardPage<TieredOrbitalLayout, TieredOrbitalLayoutConfiguration, TieredOrbitalLayoutConfigurationView> {

	private TextFieldWrapper ignoreEdgeDirectionTF;
	private TextFieldWrapper invertPathToRootTF;
	private TextFieldWrapper minimumDistanceTF;
	private TextFieldWrapper maximumNodeRadiusTF;
	private TextFieldWrapper depthModifierTF;

	/**
	 * Creates a new instance of the {@code TieredOrbitalLayoutPage} class.
	 *
	 * @param configurationView
	 *            the {@link TieredOrbitalLayoutConfigurationView} .
	 */
	public TieredOrbitalLayoutPage(
			final TieredOrbitalLayoutConfigurationView configurationView) {

		super("Set Tiered Orbital Layout Settings", configurationView);

		ignoreEdgeDirectionTF = new TextFieldWrapper();
		Label ignoreEdgeDirectionLabel = new Label("Ignore Edge Direction");
		ignoreEdgeDirectionLabel.setLabelFor(this.ignoreEdgeDirectionTF);
		ignoreEdgeDirectionLabel.setStyle("-fx-font-weight: bold");

		invertPathToRootTF = new TextFieldWrapper();
		Label invertPathToRootLabel = new Label("Invert Path to Root Node");
		invertPathToRootLabel.setLabelFor(this.invertPathToRootTF);
		invertPathToRootLabel.setStyle("-fx-font-weight: bold");

		minimumDistanceTF = new TextFieldWrapper();
		Label minimumDistanceLabel = new Label("Minimum Distance between Nodes");
		minimumDistanceLabel.setLabelFor(this.minimumDistanceTF);
		minimumDistanceLabel.setStyle("-fx-font-weight: bold");

		maximumNodeRadiusTF = new TextFieldWrapper();
		Label maximumNodeRadiusLabel = new Label("Maximum Node Radius");
		maximumNodeRadiusLabel.setLabelFor(this.maximumNodeRadiusTF);
		maximumNodeRadiusLabel.setStyle("-fx-font-weight: bold");

		depthModifierTF = new TextFieldWrapper();
		Label depthModifierLabel = new Label(
				"Depth Modifier (Distance between tiers)");
		depthModifierLabel.setLabelFor(this.depthModifierTF);
		depthModifierLabel.setStyle("-fx-font-weight: bold");

		this.getContentGridPane().add(ignoreEdgeDirectionLabel, 0, 0);
		this.getContentGridPane().add(ignoreEdgeDirectionTF, 1, 0);
		this.getContentGridPane().add(invertPathToRootLabel, 0, 1);
		this.getContentGridPane().add(invertPathToRootTF, 1, 1);
		this.getContentGridPane().add(minimumDistanceLabel, 0,2);
		this.getContentGridPane().add(minimumDistanceTF, 1, 2);
		this.getContentGridPane().add(maximumNodeRadiusLabel, 0, 3);
		this.getContentGridPane().add(maximumNodeRadiusTF, 1, 3);
		this.getContentGridPane().add(depthModifierLabel, 0, 4);
		this.getContentGridPane().add(depthModifierTF, 1, 4);

		this.setupValidation();
	}

	private void setupValidation() {

		this.ignoreEdgeDirectionTF
				.registerValidator(Validator.combine(
						Validator
								.createEmptyValidator("Value cannot be empty!"),
						ValidationUtils
								.createBooleanFormatValidator("Boolean value required!")));
		this.invertPathToRootTF
				.registerValidator(Validator.combine(
						Validator
								.createEmptyValidator("Value cannot be empty!"),
						ValidationUtils
								.createBooleanFormatValidator("Boolean value required!")));

		this.minimumDistanceTF.registerValidator(Validator.combine(Validator
				.createEmptyValidator("Value cannot be empty!"),
				ValidationUtils.createNumberFormatValidator(Float.class,
						"Float value required!")));
		this.maximumNodeRadiusTF.registerValidator(Validator.combine(Validator
				.createEmptyValidator("Value cannot be empty!"),
				ValidationUtils.createNumberFormatValidator(Float.class,
						"Float value required!")));
		this.depthModifierTF.registerValidator(Validator.combine(Validator
				.createEmptyValidator("Value cannot be empty!"),
				ValidationUtils.createNumberFormatValidator(Float.class,
						"Float value required!")));

		this.ignoreEdgeDirectionTF
				.setValidationDecorator(new GraphicValidationDecoration());
		this.invertPathToRootTF
				.setValidationDecorator(new GraphicValidationDecoration());
		this.minimumDistanceTF
				.setValidationDecorator(new GraphicValidationDecoration());
		this.maximumNodeRadiusTF
				.setValidationDecorator(new GraphicValidationDecoration());
		this.depthModifierTF
				.setValidationDecorator(new GraphicValidationDecoration());

		this.ignoreEdgeDirectionTF.setErrorDecorationEnabled(true);
		this.invertPathToRootTF.setErrorDecorationEnabled(true);
		this.minimumDistanceTF.setErrorDecorationEnabled(true);
		this.maximumNodeRadiusTF.setErrorDecorationEnabled(true);
		this.depthModifierTF.setErrorDecorationEnabled(true);

		this.validationGroup().registerSubValidation(
				this.ignoreEdgeDirectionTF,
				this.ignoreEdgeDirectionTF.validationSupport());
		this.validationGroup().registerSubValidation(this.invertPathToRootTF,
				this.invertPathToRootTF.validationSupport());
		this.validationGroup().registerSubValidation(this.minimumDistanceTF,
				this.minimumDistanceTF.validationSupport());
		this.validationGroup().registerSubValidation(this.maximumNodeRadiusTF,
				this.maximumNodeRadiusTF.validationSupport());
		this.validationGroup().registerSubValidation(this.depthModifierTF,
				this.depthModifierTF.validationSupport());
	}

	@Override
	public void onEnteringPage(Wizard wizard) {

		super.onEnteringPage(wizard);

		if (this.getConfiguration() != null) {

			TieredOrbitalLayoutConfiguration config = this.getConfiguration();

			this.invertPathToRootTF.setText(""
					+ config.isInvertPathToRootNode());

			this.ignoreEdgeDirectionTF.setText(""
					+ config.isIgnoreEdgeDirection());

			this.minimumDistanceTF.setText("" + config.getMinimalDistance());

			this.maximumNodeRadiusTF
					.setText("" + config.getMaximumNodeRadius());

			this.depthModifierTF.setText("" + config.getDepthModifier());
		}
	}

	@Override
	public void onExitingPage(Wizard wizard) {

		super.onExitingPage(wizard);

		if (this.getConfiguration() != null) {

			this.getConfiguration().setIgnoreEdgeDirection(
					Boolean.valueOf(this.ignoreEdgeDirectionTF.getText()));
			this.getConfiguration().setInvertPathToRootNode(
					Boolean.valueOf(this.invertPathToRootTF.getText()));
			this.getConfiguration().setMinimalDistance(
					Float.valueOf(this.minimumDistanceTF.getText()));
			this.getConfiguration().setMaximumNodeRadius(
					Float.valueOf(this.maximumNodeRadiusTF.getText()));
			this.getConfiguration().setDepthModifier(
					Float.valueOf(this.depthModifierTF.getText()));
		}
	}
}
