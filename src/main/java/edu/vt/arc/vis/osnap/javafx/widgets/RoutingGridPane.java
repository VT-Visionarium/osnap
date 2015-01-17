/*******************************************************************************
 * Copyright 2014 Virginia Tech Visionarium
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/


/**
 *
 */
package edu.vt.arc.vis.osnap.javafx.widgets;



import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

import org.jutility.javafx.control.labeled.LabeledComboBox;

import edu.vt.arc.vis.osnap.core.domain.layout.common.CoordinateComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.common.I1DCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.common.I2DCoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ICoordinateLayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ILayoutComponent;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;


/**
 * @author Peter J. Radics
 *
 */
public class RoutingGridPane
        extends GridPane {

    private final ObjectProperty<ILayoutComponent>     layoutComponent;

    private final LabeledComboBox<CoordinateComponent> xCoordinate;
    private final LabeledComboBox<CoordinateComponent> yCoordinate;
    private final LabeledComboBox<CoordinateComponent> zCoordinate;


    /**
     * Returns the layout component property.
     *
     * @return the layout component property.
     */
    public ObjectProperty<ILayoutComponent> layoutComponent() {

        return this.layoutComponent;
    }

    /**
     * Returns the value of the layout component property.
     *
     * @return the value of the layout component property.
     */
    public ILayoutComponent getLayoutComponent() {

        return this.layoutComponent.get();
    }

    /**
     * Sets the value of the layout component property.
     *
     * @param layoutComponent
     *            the value of the layout component property.
     */
    public void setLayoutComponent(final ILayoutComponent layoutComponent) {

        this.layoutComponent.set(layoutComponent);
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
     * Creates a new instance of the {@link RoutingGridPane} class.
     */
    public RoutingGridPane() {

        this.setHgap(25);
        this.setVgap(10);
        this.setPadding(new Insets(10, 10, 10, 10));

        this.layoutComponent = new SimpleObjectProperty<>();

        this.xCoordinate = new LabeledComboBox<>(
                "Output to use for X Coordinate");
        this.yCoordinate = new LabeledComboBox<>(
                "Output to use for Y Coordinate");
        this.zCoordinate = new LabeledComboBox<>(
                "Output to use for Z Coordinate");

        this.xCoordinate.getItems().addAll(CoordinateComponent.values());
        this.yCoordinate.getItems().addAll(CoordinateComponent.values());
        this.zCoordinate.getItems().addAll(CoordinateComponent.values());

        this.add(this.xCoordinate, 0, 0);
        this.add(this.yCoordinate, 0, 1);
        this.add(this.zCoordinate, 0, 2);

        this.xCoordinate.setDisable(true);
        this.yCoordinate.setDisable(true);
        this.zCoordinate.setDisable(true);

        this.xCoordinate.setVisible(false);
        this.yCoordinate.setVisible(false);
        this.zCoordinate.setVisible(false);

        this.setUpEventHandlers();
    }

    private void setUpEventHandlers() {

        this.layoutComponent
                .addListener((observable, oldValue, newValue) -> {

                    this.xCoordinate.setDisable(true);
                    this.yCoordinate.setDisable(true);
                    this.zCoordinate.setDisable(true);

                    this.xCoordinate.setVisible(false);
                    this.yCoordinate.setVisible(false);
                    this.zCoordinate.setVisible(false);

                    if ((newValue != null)
                            && (newValue instanceof ICoordinateLayoutComponent)) {

                        this.xCoordinate.setVisible(true);
                        this.yCoordinate.setVisible(true);
                        this.zCoordinate.setVisible(true);

                        if (newValue.isEnabled(VisualProperty.NODE_X_POSITION)) {

                            this.xCoordinate.setDisable(false);
                        }
                        if (newValue.isEnabled(VisualProperty.NODE_Y_POSITION)) {

                            this.yCoordinate.setDisable(false);
                        }
                        if (newValue.isEnabled(VisualProperty.NODE_Z_POSITION)) {

                            this.zCoordinate.setDisable(false);
                        }

                        final ICoordinateLayoutComponent layoutComponent = (ICoordinateLayoutComponent) newValue;

                        this.xCoordinate.getSelectionModel().select(
                                layoutComponent.getXOutput());
                        this.yCoordinate.getSelectionModel().select(
                                layoutComponent.getYOutput());
                        this.zCoordinate.getSelectionModel().select(
                                layoutComponent.getZOutput());

                        if (layoutComponent instanceof I1DCoordinateLayoutComponent) {

                            this.xCoordinate.getItems().removeAll(
                                    CoordinateComponent.SECOND_COMPONENT,
                                    CoordinateComponent.THIRD_COMPONENT);
                            this.yCoordinate.getItems().removeAll(
                                    CoordinateComponent.SECOND_COMPONENT,
                                    CoordinateComponent.THIRD_COMPONENT);
                            this.zCoordinate.getItems().removeAll(
                                    CoordinateComponent.SECOND_COMPONENT,
                                    CoordinateComponent.THIRD_COMPONENT);
                        }
                        else if (layoutComponent instanceof I2DCoordinateLayoutComponent) {


                            this.xCoordinate.getItems().removeAll(
                                    CoordinateComponent.THIRD_COMPONENT);
                            this.yCoordinate.getItems().removeAll(
                                    CoordinateComponent.THIRD_COMPONENT);
                            this.zCoordinate.getItems().removeAll(
                                    CoordinateComponent.THIRD_COMPONENT);
                        }

                    }
                });

        this.xCoordinate
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) -> {

                            final ILayoutComponent layoutComponent = this
                                    .getLayoutComponent();

                            if ((layoutComponent != null)
                                    && (layoutComponent instanceof ICoordinateLayoutComponent)) {

                                final ICoordinateLayoutComponent component = (ICoordinateLayoutComponent) layoutComponent;

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

                        });
        this.yCoordinate
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) -> {

                            final ILayoutComponent layoutComponent = this
                                    .getLayoutComponent();

                            if ((layoutComponent != null)
                                    && (layoutComponent instanceof ICoordinateLayoutComponent)) {

                                final ICoordinateLayoutComponent component = (ICoordinateLayoutComponent) layoutComponent;

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

                        });
        this.zCoordinate
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) -> {

                            final ILayoutComponent layoutComponent = this
                                    .getLayoutComponent();

                            if ((layoutComponent != null)
                                    && (layoutComponent instanceof ICoordinateLayoutComponent)) {

                                final ICoordinateLayoutComponent component = (ICoordinateLayoutComponent) layoutComponent;

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

                        });
    }
}
