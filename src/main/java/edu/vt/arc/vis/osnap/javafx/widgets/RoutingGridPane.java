package edu.vt.arc.vis.osnap.javafx.widgets;


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


import java.util.LinkedHashSet;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.scene.layout.GridPane;

import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;
import org.jutility.javafx.control.labeled.LabeledComboBox;
import org.jutility.javafx.control.labeled.LabeledTextField;
import org.jutility.javafx.control.validation.ValidationGroup;
import org.jutility.javafx.control.validation.ValidationUtils;

import edu.vt.arc.vis.osnap.core.domain.layout.common.CoordinateComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ICoordinateLayout;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ILayout;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;


/**
 * The {@code RoutingGridPane} provides a view to set up the routing of the
 * {@link CoordinateComponent CoordinateComponents} of a
 * {@link ICoordinateLayout CoordinateLayout}.
 * 
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 1.0.0
 */
public class RoutingGridPane
        extends GridPane {

    private final ValidationGroup                      validationGroup;

    private final ObjectProperty<ILayout>              layoutProperty;
    private final ObservableSet<CoordinateComponent>   supportedComponents;
    private final ObservableSet<VisualProperty>        enabledProperties;

    private final LabeledComboBox<CoordinateComponent> xCoordinate;
    private final LabeledComboBox<CoordinateComponent> yCoordinate;
    private final LabeledComboBox<CoordinateComponent> zCoordinate;


    private final LabeledTextField                     firstComponentScale;
    private final LabeledTextField                     secondComponentScale;
    private final LabeledTextField                     thirdComponentScale;


    /**
     * Returns the {@link ValidationGroup} of this view.
     * 
     * @return the {@link ValidationGroup} of this view.
     */
    public ValidationGroup validationGroup() {

        return this.validationGroup;
    }


    /**
     * Returns the supported {@link CoordinateComponent CoordinateComponents}.
     * 
     * @return the supported {@link CoordinateComponent CoordinateComponents}.
     */
    public ObservableSet<CoordinateComponent> supportedComponents() {

        return this.supportedComponents;
    }

    /**
     * Returns the enabled {@link VisualProperty VisualProperties}.
     * 
     * @return the enabled {@link VisualProperty VisualProperties}.
     */
    public ObservableSet<VisualProperty> enabledProperties() {

        return this.enabledProperties;
    }


    /**
     * Returns the layout component property.
     *
     * @return the layout component property.
     */
    public ObjectProperty<ILayout> layoutProperty() {

        return this.layoutProperty;
    }

    /**
     * Returns the value of the layout property.
     *
     * @return the value of the layout property.
     */
    public ILayout getLayout() {

        return this.layoutProperty.get();
    }

    /**
     * Sets the value of the layout property.
     *
     * @param layout
     *            the value of the layout property.
     */
    public void setLayout(final ILayout layout) {

        this.layoutProperty.set(layout);
    }

    /**
     * Returns the {@link LabeledComboBox} {@code <}{@link CoordinateComponent}
     * {@code >} for the x coordinate.
     *
     * @return the {@link LabeledComboBox} {@code <}{@link CoordinateComponent}
     *         {@code >} for the x coordinate.
     */
    public LabeledComboBox<CoordinateComponent> getXCoordinate() {

        return this.xCoordinate;
    }

    /**
     * Returns the {@link LabeledComboBox} {@code <}{@link CoordinateComponent}
     * {@code >} for the y coordinate.
     *
     * @return the {@link LabeledComboBox} {@code <}{@link CoordinateComponent}
     *         {@code >} for the y coordinate.
     */
    public LabeledComboBox<CoordinateComponent> getYCoordinate() {

        return this.yCoordinate;
    }

    /**
     * Returns the {@link LabeledComboBox} {@code <}{@link CoordinateComponent}
     * {@code >} for the z coordinate.
     *
     * @return the {@link LabeledComboBox} {@code <}{@link CoordinateComponent}
     *         {@code >} for the z coordinate.
     */
    public LabeledComboBox<CoordinateComponent> getZCoordinate() {

        return this.zCoordinate;
    }

    /**
     * Returns the X {@link CoordinateComponent Output}.
     * 
     * @return the X {@link CoordinateComponent Output}.
     */
    public CoordinateComponent getXOutput() {

        return this.xCoordinate.getValue();
    }

    /**
     * Returns the Y {@link CoordinateComponent Output}.
     * 
     * @return the Y {@link CoordinateComponent Output}.
     */
    public CoordinateComponent getYOutput() {

        return this.yCoordinate.getValue();
    }

    /**
     * Returns the Z {@link CoordinateComponent Output}.
     * 
     * @return the Z {@link CoordinateComponent Output}.
     */
    public CoordinateComponent getZOutput() {

        return this.zCoordinate.getValue();
    }


    /**
     * Returns the scale of the first {@link CoordinateComponent}.
     * 
     * @return the scale of the first {@link CoordinateComponent}.
     */
    public float getFirstComponentScale() {

        if (!this.firstComponentScale.validationSupport().isInvalid()) {

            return Float.valueOf(this.firstComponentScale.getText());
        }

        return 1;
    }

    /**
     * Sets the scale of the first {@link CoordinateComponent}.
     * 
     * @param value
     *            the scale of the first {@link CoordinateComponent}.
     */
    public void setFirstComponentScale(final float value) {

        this.firstComponentScale.setText("" + value);
    }


    /**
     * Returns the scale of the second {@link CoordinateComponent}.
     * 
     * @return the scale of the second {@link CoordinateComponent}.
     */
    public float getSecondComponentScale() {

        if (!this.secondComponentScale.validationSupport().isInvalid()) {

            return Float.valueOf(this.secondComponentScale.getText());
        }

        return 1;
    }

    /**
     * Sets the scale of the second {@link CoordinateComponent}.
     * 
     * @param value
     *            the scale of the second {@link CoordinateComponent}.
     */
    public void setSecondComponentScale(final float value) {

        this.secondComponentScale.setText("" + value);
    }


    /**
     * Returns the scale of the third {@link CoordinateComponent}.
     * 
     * @return the scale of the third {@link CoordinateComponent}.
     */
    public float getThirdComponentScale() {

        if (!this.thirdComponentScale.validationSupport().isInvalid()) {

            return Float.valueOf(this.thirdComponentScale.getText());
        }

        return 1;
    }

    /**
     * Sets the scale of the third {@link CoordinateComponent}.
     * 
     * @param value
     *            the scale of the third {@link CoordinateComponent}.
     */
    public void setThirdComponentScale(final float value) {

        this.thirdComponentScale.setText("" + value);
    }

    /**
     * Creates a new instance of the {@link RoutingGridPane} class.
     */
    public RoutingGridPane() {

        this.setHgap(25);
        this.setVgap(10);

        this.validationGroup = new ValidationGroup();

        this.supportedComponents = FXCollections
                .observableSet(new LinkedHashSet<>());
        this.enabledProperties = FXCollections
                .observableSet(new LinkedHashSet<>());
        this.layoutProperty = new SimpleObjectProperty<>();

        this.xCoordinate = new LabeledComboBox<>("Output for X");
        this.xCoordinate.setHgap(10);
        this.yCoordinate = new LabeledComboBox<>("Output for Y");
        this.yCoordinate.setHgap(10);
        this.zCoordinate = new LabeledComboBox<>("Output for Z");
        this.zCoordinate.setHgap(10);

        this.firstComponentScale = new LabeledTextField("scaled by");
        this.firstComponentScale.setHgap(10);
        this.secondComponentScale = new LabeledTextField("scaled by");
        this.secondComponentScale.setHgap(10);
        this.thirdComponentScale = new LabeledTextField("scaled by");
        this.thirdComponentScale.setHgap(10);


        this.xCoordinate.getItems().addAll(CoordinateComponent.values());
        this.yCoordinate.getItems().addAll(CoordinateComponent.values());
        this.zCoordinate.getItems().addAll(CoordinateComponent.values());

        this.add(this.xCoordinate, 0, 0);
        this.add(this.yCoordinate, 0, 1);
        this.add(this.zCoordinate, 0, 2);

        this.add(this.firstComponentScale, 1, 0);
        this.add(this.secondComponentScale, 1, 1);
        this.add(this.thirdComponentScale, 1, 2);

        this.xCoordinate.setDisable(true);
        this.yCoordinate.setDisable(true);
        this.zCoordinate.setDisable(true);

        this.firstComponentScale.setDisable(true);
        this.secondComponentScale.setDisable(true);
        this.thirdComponentScale.setDisable(true);

        this.xCoordinate.setVisible(false);
        this.yCoordinate.setVisible(false);
        this.zCoordinate.setVisible(false);

        this.firstComponentScale.setVisible(false);
        this.secondComponentScale.setVisible(false);
        this.thirdComponentScale.setVisible(false);

        this.setupEventHandlers();
        this.setupValidation();
    }



    private void setupEventHandlers() {

        this.supportedComponents.addListener((Observable observable) -> {

            this.xCoordinate.getItems().clear();
            this.yCoordinate.getItems().clear();
            this.zCoordinate.getItems().clear();

            this.xCoordinate.setVisible(false);
            this.yCoordinate.setVisible(false);
            this.zCoordinate.setVisible(false);

            this.firstComponentScale.setVisible(false);
            this.secondComponentScale.setVisible(false);
            this.thirdComponentScale.setVisible(false);

            if (!this.supportedComponents().isEmpty()) {

                this.xCoordinate.setVisible(true);
                this.yCoordinate.setVisible(true);
                this.zCoordinate.setVisible(true);

                this.firstComponentScale.setVisible(true);
                this.secondComponentScale.setVisible(true);
                this.thirdComponentScale.setVisible(true);


                this.xCoordinate.getItems().addAll(this.supportedComponents);
                this.yCoordinate.getItems().addAll(this.supportedComponents);
                this.zCoordinate.getItems().addAll(this.supportedComponents);
            }

        });

        this.enabledProperties
                .addListener((Observable observable) -> {

                    this.xCoordinate.setDisable(true);
                    this.yCoordinate.setDisable(true);
                    this.zCoordinate.setDisable(true);

                    this.firstComponentScale.setDisable(true);
                    this.secondComponentScale.setDisable(true);
                    this.thirdComponentScale.setDisable(true);


                    if (this.enabledProperties
                            .contains(VisualProperty.NODE_X_POSITION)) {

                        this.xCoordinate.setDisable(false);
                        this.firstComponentScale.setDisable(false);
                    }
                    if (this.enabledProperties
                            .contains(VisualProperty.NODE_Y_POSITION)) {

                        this.yCoordinate.setDisable(false);
                        this.secondComponentScale.setDisable(false);
                    }
                    if (this.enabledProperties
                            .contains(VisualProperty.NODE_Z_POSITION)) {

                        this.zCoordinate.setDisable(false);
                        this.thirdComponentScale.setDisable(false);
                    }
                });

        this.layoutProperty
                .addListener((observable, oldValue, newValue) -> {


                    this.supportedComponents.clear();
                    this.enabledProperties.clear();

                    if ((newValue != null)
                            && (newValue instanceof ICoordinateLayout)) {

                        this.enabledProperties.addAll(newValue
                                .enabledCapabilities());

                        final ICoordinateLayout layoutComponent = (ICoordinateLayout) newValue;

                        this.supportedComponents.addAll(layoutComponent
                                .providesComponents());

                        this.xCoordinate.getSelectionModel().select(
                                layoutComponent.getXOutput());
                        this.yCoordinate.getSelectionModel().select(
                                layoutComponent.getYOutput());
                        this.zCoordinate.getSelectionModel().select(
                                layoutComponent.getZOutput());

                        this.firstComponentScale.setText(""
                                + layoutComponent.getFirstComponentScale());
                        this.secondComponentScale.setText(""
                                + layoutComponent.getSecondComponentScale());
                        this.thirdComponentScale.setText(""
                                + layoutComponent.getThirdComponentScale());
                    }
                });

        this.xCoordinate
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) -> {

                            if (newValue != null) {

                                final ILayout layout = this.getLayout();

                                if ((layout != null)
                                        && (layout instanceof ICoordinateLayout)) {

                                    final ICoordinateLayout component = (ICoordinateLayout) layout;

                                    switch (newValue) {
                                        case FIRST_COMPONENT:
                                        case SECOND_COMPONENT:
                                        case THIRD_COMPONENT:
                                        case NO_COMPONENT:
                                            component.setXOutput(newValue);
                                            break;
                                        default:
                                            component
                                                    .setXOutput(CoordinateComponent.NO_COMPONENT);
                                            break;
                                    }
                                }
                            }
                        });
        this.yCoordinate
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) -> {

                            if (newValue != null) {

                                final ILayout layout = this.getLayout();

                                if ((layout != null)
                                        && (layout instanceof ICoordinateLayout)) {

                                    final ICoordinateLayout component = (ICoordinateLayout) layout;

                                    switch (newValue) {
                                        case FIRST_COMPONENT:
                                        case SECOND_COMPONENT:
                                        case THIRD_COMPONENT:
                                        case NO_COMPONENT:
                                            component.setYOutput(newValue);
                                            break;
                                        default:
                                            component
                                                    .setYOutput(CoordinateComponent.NO_COMPONENT);
                                            break;
                                    }
                                }
                            }
                        });
        this.zCoordinate
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) -> {

                            if (newValue != null) {

                                final ILayout layout = this.getLayout();

                                if ((layout != null)
                                        && (layout instanceof ICoordinateLayout)) {

                                    final ICoordinateLayout component = (ICoordinateLayout) layout;

                                    switch (newValue) {
                                        case FIRST_COMPONENT:
                                        case SECOND_COMPONENT:
                                        case THIRD_COMPONENT:
                                        case NO_COMPONENT:
                                            component.setZOutput(newValue);
                                            break;
                                        default:
                                            component
                                                    .setZOutput(CoordinateComponent.NO_COMPONENT);
                                            break;
                                    }
                                }
                            }
                        });
    }


    private void setupValidation() {

        this.xCoordinate.registerValidator(Validator.createPredicateValidator(
                value -> {

                    return value != null;
                }, "Please select a value!"));
        this.xCoordinate
                .setValidationDecorator(new GraphicValidationDecoration());
        this.xCoordinate.setErrorDecorationEnabled(true);
        this.validationGroup.registerSubValidation(this.xCoordinate,
                this.xCoordinate.validationSupport());


        this.yCoordinate.registerValidator(Validator.createPredicateValidator(
                value -> {

                    return value != null;
                }, "Please select a value!"));
        this.yCoordinate
                .setValidationDecorator(new GraphicValidationDecoration());
        this.yCoordinate.setErrorDecorationEnabled(true);
        this.validationGroup.registerSubValidation(this.yCoordinate,
                this.yCoordinate.validationSupport());


        this.zCoordinate.registerValidator(Validator.createPredicateValidator(
                value -> {

                    return value != null;
                }, "Please select a value!"));
        this.zCoordinate
                .setValidationDecorator(new GraphicValidationDecoration());
        this.zCoordinate.setErrorDecorationEnabled(true);
        this.validationGroup.registerSubValidation(this.zCoordinate,
                this.zCoordinate.validationSupport());


        this.firstComponentScale.registerValidator(Validator.combine(Validator
                .createEmptyValidator("Value cannot be empty!"),
                ValidationUtils.createNumberFormatValidator(Float.class,
                        "Value must be a valid float!")));
        this.firstComponentScale
                .setValidationDecorator(new GraphicValidationDecoration());
        this.firstComponentScale.setErrorDecorationEnabled(true);
        this.validationGroup.registerSubValidation(this.firstComponentScale,
                this.firstComponentScale.validationSupport());


        this.secondComponentScale.registerValidator(Validator.combine(Validator
                .createEmptyValidator("Value cannot be empty!"),
                ValidationUtils.createNumberFormatValidator(Float.class,
                        "Value must be a valid float!")));
        this.secondComponentScale
                .setValidationDecorator(new GraphicValidationDecoration());
        this.secondComponentScale.setErrorDecorationEnabled(true);
        this.validationGroup.registerSubValidation(this.secondComponentScale,
                this.secondComponentScale.validationSupport());


        this.thirdComponentScale.registerValidator(Validator.combine(Validator
                .createEmptyValidator("Value cannot be empty!"),
                ValidationUtils.createNumberFormatValidator(Float.class,
                        "Value must be a valid float!")));
        this.thirdComponentScale
                .setValidationDecorator(new GraphicValidationDecoration());
        this.thirdComponentScale.setErrorDecorationEnabled(true);
        this.validationGroup.registerSubValidation(this.thirdComponentScale,
                this.thirdComponentScale.validationSupport());
    }
}
