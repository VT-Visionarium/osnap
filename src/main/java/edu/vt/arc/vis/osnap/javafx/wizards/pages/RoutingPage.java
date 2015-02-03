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


import java.util.Set;

import javafx.scene.layout.GridPane;
import edu.vt.arc.vis.osnap.core.domain.layout.common.CoordinateComponent;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ICoordinateLayout;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualNode;
import edu.vt.arc.vis.osnap.javafx.widgets.RoutingGridPane;
import edu.vt.arc.vis.osnap.javafx.wizards.Wizard;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.ICoordinateLayoutConfiguration;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes.ICoordinateLayoutConfigurationView;


/**
 * The {@link RoutingPage} class provides a
 * {@link LayoutConfigurationWizardPage} for routing coordinate output of
 * {@link ICoordinateLayout CoordinateLayouts} to the x, y, or z
 * coordinate(scale) of a {@link VisualNode}.
 * 
 * @param <O>
 *            the type of the {@link ICoordinateLayout}.
 * @param <C>
 *            the type of the {@link ICoordinateLayoutConfiguration
 *            Configuration}.
 * @param <T>
 *            the type of the {@link ICoordinateLayoutConfigurationView
 *            ConfigurationView}.
 * 
 * @author Peter J. Radics
 * @version 1.2.0
 * @since 1.0.0
 */
public class RoutingPage<O extends ICoordinateLayout, C extends ICoordinateLayoutConfiguration<O>, T extends GridPane & ICoordinateLayoutConfigurationView<O, C>>
        extends LayoutConfigurationWizardPage<O, C, T> {


    private RoutingGridPane routingGridPane;


    /**
     * Creates a new instance of the {@code RoutingPage} class.
     *
     * @param configurationView
     *            the {@link ICoordinateLayoutConfigurationView} .
     * @param components
     *            the {@link CoordinateComponent CoordinateComponents}
     *            supported.
     */
    public RoutingPage(final T configurationView,
            final Set<CoordinateComponent> components) {

        super("Route Coordinate Outputs", configurationView);

        this.routingGridPane = new RoutingGridPane();
        this.routingGridPane.supportedComponents().addAll(components);

        this.getContentGridPane().add(this.routingGridPane, 0, 0);

        this.setupValidation();
    }


    private void setupValidation() {

        this.validationGroup().registerSubValidation(this.routingGridPane,
                this.routingGridPane.validationGroup());
    }

    @Override
    public void onEnteringPage(Wizard wizard) {

        super.onEnteringPage(wizard);

        if (this.getConfiguration() != null) {

            this.routingGridPane.enabledProperties().clear();
            this.routingGridPane.enabledProperties().addAll(
                    this.getConfiguration().getEnabledVisualProperties());

            this.routingGridPane.setFirstComponentScale(this.getConfiguration()
                    .getFirstComponentScale());
            this.routingGridPane.setSecondComponentScale(this
                    .getConfiguration().getSecondComponentScale());
            this.routingGridPane.setThirdComponentScale(this.getConfiguration()
                    .getThirdComponentScale());

            if (this.getConfiguration().getXOutput() != null) {

                this.routingGridPane.getXCoordinate().getSelectionModel()
                        .select(this.getConfiguration().getXOutput());
            }
            if (this.getConfiguration().getYOutput() != null) {

                this.routingGridPane.getYCoordinate().getSelectionModel()
                        .select(this.getConfiguration().getYOutput());
            }

            if (this.getConfiguration().getZOutput() != null) {

                this.routingGridPane.getZCoordinate().getSelectionModel()
                        .select(this.getConfiguration().getZOutput());
            }
        }
    }

    @Override
    public void onExitingPage(Wizard wizard) {

        super.onExitingPage(wizard);


        if (this.getConfiguration() != null) {

            this.getConfiguration().setXOutput(
                    this.routingGridPane.getXOutput());
            this.getConfiguration().setYOutput(
                    this.routingGridPane.getYOutput());
            this.getConfiguration().setZOutput(
                    this.routingGridPane.getZOutput());

            this.getConfiguration().setFirstComponentScale(
                    this.routingGridPane.getFirstComponentScale());
            this.getConfiguration().setSecondComponentScale(
                    this.routingGridPane.getSecondComponentScale());
            this.getConfiguration().setThirdComponentScale(
                    this.routingGridPane.getThirdComponentScale());
        }
    }
}
