package edu.vt.arc.vis.osnap.javafx.wizards.configurations.statuspanes;


// @formatter:off
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

import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import edu.vt.arc.vis.osnap.core.domain.graph.common.IGraphObject;
import edu.vt.arc.vis.osnap.core.domain.layout.common.ILayout;
import edu.vt.arc.vis.osnap.core.domain.visualization.VisualProperty;
import edu.vt.arc.vis.osnap.javafx.wizards.configurations.ILayoutConfiguration;


/**
 * The abstract {@code LayoutConfigurationView} class provides the base for all
 * views for {@link ILayoutConfiguration LayoutConfigurations}.
 *
 * @param <O>
 *            the type of the {@link ILayout Layout}.
 * @param <C>
 *            the type of the {@link ILayoutConfiguration LayoutConfiguration}.
 * @author Shawn P Neuman, Peter J. Radics
 * @version 1.2.0
 * @since 0.5.0
 */
public abstract class LayoutConfigurationView<O extends ILayout, C extends ILayoutConfiguration<O>>
        extends ConfigurationView<O, C>
        implements ILayoutConfigurationView<O, C> {

    private final Label                  propertyType;
    private final Text                   propertyTypeTF;
    private final Label                  forTheseObjects;
    private final ListView<IGraphObject> restrictionList;


    /**
     * Creates a new instance of the {@code LayoutConfigurationView} class.
     *
     * @param title
     *            the title for this {@link IConfigurationView}.
     * @param defaultConfiguration
     *            the default configuration.
     */
    public LayoutConfigurationView(final String title,
            final C defaultConfiguration) {

        super(title, defaultConfiguration);

        this.propertyType = new Label("Visual Property:");
        this.propertyTypeTF = new Text();
        this.propertyTypeTF.setStyle("-fx-font-weight: bold");

        this.forTheseObjects = new Label("For:");
        this.restrictionList = new ListView<>();

        GridPane.setHgrow(this.propertyType, Priority.ALWAYS);
        GridPane.setHgrow(this.propertyTypeTF, Priority.ALWAYS);
        GridPane.setValignment(this.forTheseObjects, VPos.TOP);
        GridPane.setHgrow(this.restrictionList, Priority.NEVER);

        this.add(this.propertyType, 0, super.rowsUsed());
        this.add(this.propertyTypeTF, 1, super.rowsUsed());
        this.add(this.forTheseObjects, 0, super.rowsUsed() + 1);
        this.add(this.restrictionList, 1, super.rowsUsed() + 1);
    }

    @Override
    protected int rowsUsed() {

        return super.rowsUsed() + 2;
    }

    @Override
    public void refreshView() {

        this.propertyTypeTF.setText("");
        this.restrictionList.getItems().clear();

        if (this.getConfiguration() != null) {

            if (!this.getConfiguration().getEnabledVisualProperties().isEmpty()) {

                StringBuilder enabledProperties = new StringBuilder();

                int i = 0;
                for (VisualProperty visualProperty : this.getConfiguration()
                        .getEnabledVisualProperties()) {

                    if (i > 0) {
                        enabledProperties.append(", ");
                    }

                    enabledProperties.append(visualProperty.getName());
                    i++;
                }

                this.propertyTypeTF.setText(enabledProperties.toString());
            }

            if (!this.getConfiguration().getRestriction().isEmpty()) {

                this.restrictionList.getItems().addAll(
                        this.getConfiguration().getRestriction());
            }
        }
    }
}
