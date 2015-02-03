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

import edu.vt.arc.vis.osnap.core.domain.layout.simpleComponents.SimpleViewpointLayout;
import edu.vt.arc.vis.osnap.javafx.wizards.Wizard;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.SimpleViewpointLayoutConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes.SimpleViewpointLayoutConfigurationView;


/**
 * The {@code SimpleViewpointSelectionPage} provides a
 * {@link LayoutConfigurationWizardPage} for selecting the scale of a
 * {@link SimpleViewpointLayout simple ViewpointLayout}.
 *
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public class SimpleViewpointSelectionPage
        extends
        LayoutConfigurationWizardPage<SimpleViewpointLayout, SimpleViewpointLayoutConfiguration, SimpleViewpointLayoutConfigurationView> {


    private LabeledTextField xOffsetTF;
    private LabeledTextField yOffsetTF;
    private LabeledTextField zOffsetTF;


    /**
     * Creates a new instance of the {@link SimpleViewpointSelectionPage} class.
     * 
     * @param configurationView
     *            the {@link SimpleViewpointLayoutConfigurationView} .
     */
    public SimpleViewpointSelectionPage(
            final SimpleViewpointLayoutConfigurationView configurationView) {

        super("Set Viewpoint Offsets", configurationView);


        xOffsetTF = new LabeledTextField("X Offset");
        yOffsetTF = new LabeledTextField("Y Offset");
        zOffsetTF = new LabeledTextField("Z Offset");

        this.getContentGridPane().add(xOffsetTF, 0, 0);
        this.getContentGridPane().add(yOffsetTF, 0, 1);
        this.getContentGridPane().add(zOffsetTF, 0, 2);

        this.setupValidation();
    }


    private void setupValidation() {

        this.xOffsetTF.registerValidator(Validator.combine(Validator
                .createEmptyValidator("X offset cannot be empty!"),
                ValidationUtils.createNumberFormatValidator(Float.class,
                        "Offset must be a float!")));
        this.xOffsetTF
                .setValidationDecorator(new GraphicValidationDecoration());
        this.xOffsetTF.setErrorDecorationEnabled(true);
        this.validationGroup().registerSubValidation(this.xOffsetTF,
                this.xOffsetTF.validationSupport());


        this.yOffsetTF.registerValidator(Validator.combine(Validator
                .createEmptyValidator("Y offset cannot be empty!"),
                ValidationUtils.createNumberFormatValidator(Float.class,
                        "Offset must be a float!")));
        this.yOffsetTF
                .setValidationDecorator(new GraphicValidationDecoration());
        this.yOffsetTF.setErrorDecorationEnabled(true);
        this.validationGroup().registerSubValidation(this.yOffsetTF,
                this.yOffsetTF.validationSupport());


        this.zOffsetTF.registerValidator(Validator.combine(Validator
                .createEmptyValidator("Z offset cannot be empty!"),
                ValidationUtils.createNumberFormatValidator(Float.class,
                        "Offset must be a float!")));
        this.zOffsetTF
                .setValidationDecorator(new GraphicValidationDecoration());
        this.zOffsetTF.setErrorDecorationEnabled(true);
        this.validationGroup().registerSubValidation(this.zOffsetTF,
                this.zOffsetTF.validationSupport());
    }

    @Override
    public void onEnteringPage(Wizard wizard) {

        super.onEnteringPage(wizard);

        if (this.getConfiguration() != null) {

            this.xOffsetTF.setText("" + this.getConfiguration().getOffsetX());
            this.yOffsetTF.setText("" + this.getConfiguration().getOffsetY());
            this.zOffsetTF.setText("" + this.getConfiguration().getOffsetZ());
        }
    }

    @Override
    public void onExitingPage(Wizard wizard) {

        super.onExitingPage(wizard);

        if (this.getConfiguration() != null) {

            this.getConfiguration().setOffsetX(
                    Float.valueOf(this.xOffsetTF.getText()));
            this.getConfiguration().setOffsetY(
                    Float.valueOf(this.yOffsetTF.getText()));
            this.getConfiguration().setOffsetZ(
                    Float.valueOf(this.zOffsetTF.getText()));

        }
    }
}
